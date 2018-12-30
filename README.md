# easy-brancher

## 介绍
  一个便捷的业务分支构件模块。当你的业务需要同时支持A和B两种方式时，你可能用一个if..else...就解决了。但是当你需要同时支持持A、B、C、D..种业务，而且代码量非常感人时，难道你不觉得用if..else...来搞分支太辛苦了吗？你的代码会变得又臭又长，同时，可读性和可维护性极差。这时候，你需要easy-brancher来简化你的业务。

## 使用说明
### 你需要先定义多个分支业务类，分支业务实现如下：
```
@Brancher(key = "one", todo = { "" })
public class BranchHandlerOne extends AbstractBrancher{
     @Override
     public Object doService(EasyMap params) throws Exception {
	  System.out.println("111111:"+params);
	  return "11111";
     }
}
```
```
@Brancher(key = "two", todo = { "" })
public class BranchHandlerTwo extends AbstractBrancher{
     @Override
     public Object doService(EasyMap params) throws Exception {
	  System.out.println("22222:"+params);
	  return "22222";
     }
}
```

  如上所示，你需要在你的分支业务类上声明@Brancher注解，注解参数有两个，key是你的分支业务类唯一标识，同时会作为Bean的名称注入Spring容器。todo用来描述你的业务类作用，起到注释的作用；
  你需要让你的分支业务类继承AbstractBrancher类，然后实现doService方法。你可以从EasyMap（增强型HashMap）中取得你从主业务传递过来的业务参数，从而继续执行你的业务。
### 主业务调用分支业务
#### 1.spring容器注入的方式
```
@Autowired
private IBrancher brancher;

@Override
public void run(String... args) throws Exception {
    brancher.doService("one", EasyMap.create().add("姓名", "张三").add("年龄", "18"));
}
```
#### 2.通过分支工具调用
```
BranchUtils.doService("two", EasyMap.create().add("姓名", "张三").add("年龄", "18"));
```
## Maven引用(如果版本有变化，请自行去maven中央仓库引用)
```
<dependency>
    <groupId>io.github.xiaoyudeguang</groupId>
    <artifactId>easy-brancher</artifactId>
    <version>3.0.0-RELEASE</version>
</dependency>
```
  
