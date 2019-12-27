### Backend Base

后端的实现由上到下分为以下几层：

- controller，接受前端请求，调用service层，获得dto格式数据，包装成response
- service，填写业务逻辑
- dto，面向数据传输的实体，展示给前端的类
- repository，继承`JPARepository`的基本CRUD操作
- entity，映射的实体，对应数据库的表



#### 开发需知

现在已经有一个作为样例用的`TestController`，可以参照其进行开发，需要注意的是该controller目前没有加上**分页**(`PageRepository`)，后续开发需补上。

注解比较之重要（`@Autowerid` `@Service`等等）

还没试跨域

端口由`application.yml`指定