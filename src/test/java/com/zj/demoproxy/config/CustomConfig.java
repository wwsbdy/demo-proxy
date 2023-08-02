package com.zj.demoproxy.config;

import com.zj.demoproxy.annotation.Concat;
import com.zj.demoproxy.interfaces.BeanCreateRule;
import com.zj.demoproxy.pojo.AnnotationBeanMap;
import com.zj.demoproxy.service.BaseInterface;
import com.zj.demoproxy.service.impl.BaseInterfaceImpl;
import com.zj.demoproxy.template.BaseTemplate;
import com.zj.demoproxy.template.Getter;
import com.zj.demoproxy.template.handler.BaseTemplateImpl;
import com.zj.demoproxy.template.handler.SingerInvocationHandler;
import com.zj.demoproxy.utils.ClassHelper;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.description.modifier.FieldManifestation;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义配置
 */
@Configuration
public class CustomConfig {
    /**
     * 自定义@Concat注解配置
     * @return
     */
    @Bean
    AnnotationBeanMap annotationBean(){
        AnnotationBeanMap annotationBean = new AnnotationBeanMap();
        annotationBean.put(Concat.class, ((objects, annotation, clz) -> {
            if (String.class != clz){
                throw new  RuntimeException("@Concat修饰的方法返回值必须是String类型");
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (Object object : objects) {
                stringBuilder.append(object);
            }
            return stringBuilder.toString();
        }));
        return annotationBean;
    }

    @Bean
    public BeanCreateRule beanCreateRule(ClassHelper classHelper, SingerInvocationHandler singerInvocationHandler, BaseTemplateImpl baseTemplateImpl, BaseInterfaceImpl baseInterface){
        return new BeanCreateRule() {
            @Override
            public <T> T create(Class<T> clazz) {
                // 使用byte-buddy动态代理接口
                Object o = null;
                try {
                    Class<?>[] interfaces = clazz.getInterfaces();
                    DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<Object> receiverTypeDefinition = new ByteBuddy().with(new NamingStrategy.AbstractBase() {
                                @Override
                                protected String name(TypeDescription typeDescription) {
                                    return "com.zj.demoproxy.template.service.impl." + clazz.getSimpleName() + "Impl";
                                }
                            })
                            .subclass(Object.class)
                            .implement(clazz)
                            // 自定义方法用自定义拦截
                            .method(ElementMatchers.isDeclaredBy(clazz))
                            .intercept(InvocationHandlerAdapter.of(singerInvocationHandler))
                            .method(ElementMatchers.isDeclaredBy(BaseInterface.class))
                            .intercept(MethodDelegation.to(baseInterface));
                    // 拦截通用模板方法
                    for (Class<?> anInterface : interfaces) {
                        if (BaseTemplate.class == anInterface){
                            // 定义一个属性private final Class typeClz; 为BaseTemplate的泛型class
                            receiverTypeDefinition = receiverTypeDefinition.defineField("typeClz", Class.class, Visibility.PRIVATE, FieldManifestation.FINAL)
                                    // 通用模板方法用通用拦截
                                    .method(ElementMatchers.isDeclaredBy(BaseTemplate.class))
                                    .intercept(MethodDelegation.to(baseTemplateImpl))
                                    // 定义一个属性private final String dmsUrl;
                                    .defineField("dmsUrl", String.class, Visibility.PRIVATE, FieldManifestation.FINAL)
                                    // 覆写构造方法,赋值final属性
                                    .constructor(ElementMatchers.isDefaultConstructor())
                                    .intercept(MethodCall.invoke(Object.class.getDeclaredConstructor())
                                            .andThen(FieldAccessor.ofField("typeClz").setsValue(ClassHelper.getBaseTemplateTypeClass((Class<? extends BaseTemplate>) clazz)))
                                            .andThen(FieldAccessor.ofField("dmsUrl").setsValue(classHelper.getDmsUrl((Class<? extends BaseTemplate>) clazz)))
                                    )
                                    // 拦截get方法
                                    .method(ElementMatchers.isDeclaredBy(Getter.class))
                                    .intercept(FieldAccessor.ofBeanProperty());
                        }
                    }

                    o = receiverTypeDefinition.make()
                            .load(ClassLoader.getSystemClassLoader())
                            .getLoaded()
                            .newInstance();
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                return (T) o;
            }
        };
    }
}
