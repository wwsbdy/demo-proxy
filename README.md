# 1. 简介

## 1.1该服务的主要用途：

仿照mybayis-plus的baseMapper实现的接口增强。只是一个demo，可根据自己的需求扩展功能

## 1.2主要思路：

动态代理继承了接口BaseTemplate的接口，并注入ioc容器

## 1.3使用主要技术：

1.springboot
2.bytebuddy：动态代理

# 2. 快速开始

## 2.1 maven依赖

```xml
<dependency>
    <groupId>com.zj.demoproxy</groupId>
    <artifactId>demo-proxy</artifactId>
    <version>X.X.X</version>
</dependency>
```
如果jar包放在resources下：
```xml
<dependency>
    <groupId>com.zj.demoproxy</groupId>
    <artifactId>demo-proxy</artifactId>
    <version>X.X.X</version>
    <scope>system</scope>
    <systemPath>${basedir}/src/main/resources/demo-proxy-X.X.X.jar</systemPath>
</dependency>
```

## 2.2 编写代码

在启动类上添加@EnableAutoProxyInterface注解，允许自动代理继承BaseTemplate的接口

```java
@EnableAutoProxyInterface
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```

编写一个接口继承BaseTemplate<T>，同时必须加上@Scan注解才支持代理（Scan的value对应配置文件的属性）

```java
@Scan("dms.url[0]")
public interface TestService extends BaseTemplate<TestDo> {
}
```

注入接口，调用BaseTemplate的方法

```java
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class DemoProxyApplicationTests {
    @Autowired
    private DmsTestService dmsTestService;

    @Test
    public void test001() {
        String dmsUrl = dmsTestService.getDmsUrl();
        Class<DmsTestDo> typeClz = dmsTestService.getTypeClz();
        List<DmsTestDo> list = dmsTestService.getList(2, () -> {
            DmsTestDo dmsTestDo = new DmsTestDo();
            dmsTestDo.setName("zjiddd");
            return dmsTestDo;
        });
        System.out.println(dmsUrl);
    }

}
```

# 4. 版本说明

## v1.1.0 

### 支持自定义方法和自定义方法注解

系统自带的注解方法有： @Sout @Sub

在接口层的方法上加上这些注解

```java
@Scan("dms.zj-demo")
public interface DmsTestService extends BaseTemplate<DmsTestDo> {
    
    @Sout("打印soutSome")
    void soutSome();
    
}
```

除了系统自带，还可以自定义注解

如@Concat，拼接入参，返回字符串

```java
@Configuration
public class CustomConfig {
    /**
     * 自定义@Concat注解配置
     * @return
     */
    @Bean
    AnnotationBeanMap annotationBean() {
        AnnotationBeanMap annotationBean = new AnnotationBeanMap();
        annotationBean.put(Concat.class, ((objects, annotation, clz) -> {
            if (String.class != clz) {
                throw new RuntimeException("@Concat修饰的方法返回值必须是String类型");
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (Object object : objects) {
                stringBuilder.append(object);
            }
            return stringBuilder.toString();
        }));
        return annotationBean;
    }
}
```

### 自定义父类接口及拦截方法

自定义定义一个接口

```java
public interface BaseInterface {
    String getValue();
}
```

继承BaseInterface

```java
@Scan
public interface CkTestService extends BaseTemplate<String>, BaseInterface {
}
```

实现拦截策略

```java
@Component
public class BaseInterfaceImpl implements BaseInterface {
    @Value("${zj.name}")
    private String value;

    @Override
    public String getValue() {
        return value;
    }
}
```

配置进策略集合

```java
@Configuration
public class CustomConfig {
    @Bean
    public StrategyBeanMap strategyBeanMap(BaseTemplateImpl baseTemplateImpl, BaseInterfaceImpl baseInterface){
        StrategyBeanMap strategyBeanMap = new StrategyBeanMap(baseTemplateImpl);
        // 添加自定义父级接口
        strategyBeanMap.put(BaseInterface.class, baseInterface);
        return strategyBeanMap;
    }
}
```


# 5. 后续迭代方向

## 1.可代理自定义的方法

## 2.可通过@Configuration灵活配置代理类

