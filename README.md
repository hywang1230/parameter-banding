### 介绍
用于Spring MVC的数据绑定

### 场景
使用Bean接收参数时,前端传过来的参数是下划线的命名规则,后端使用的驼峰规则命名的Bean属性,此时参数无法与Bean匹配,需要进行转换绑定

### 使用方法

1. 在要绑定的字段上使用@ParamName()
2. 在Spring的配置文件中配置
```
    <mvc:annotation-driven>
        <mvc:argument-resolvers>
            <bean class="name.wangrong.binder.RenamingProcessor">
                <constructor-arg name="annotationNotRequired" value="true"/>
            </bean>
        </mvc:argument-resolvers>
    </mvc:annotation-driven>
```
不支持<mvc:argument-resolvers></mvc:argument-resolvers>标签的请把Spring的XML检验文件改成3.1以上

### 注意项
和继承PropertyEditorSupport等类型转换共同使用有问题,类型转换会不生效,请使用Converter接口做类型转换