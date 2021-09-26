# 网易云音乐的`MVP`架构

## 一、理论

### 1.1 整体框架

#### 1.1.1 框架图

<img src="https://gitee.com/tianyalusty/pic-go-repository/raw/master/img/202109262059546.png" alt="project_structure" style="zoom:80%;" />

#### 1.1.2 框架介绍

------------------------ 简化的版本
App壳 --->面向用户画面，用户的体验 （0231需求开发 迭代）

    |
    |
    |

业务模块 ---> 业务模块1，业务模块2，业务模块3，业务模块4，业务模块5，业务模块6 ...    （0231需求开发 迭代）

    |
    |
    |

核心层 ---> 技术人员 不停的优化，增加不停的封装 （例如：网络请求封装，native的封装）

    |
    |
    |

我们的代码是，写死的
通用层(放仓库的，还可以给 其他部门用，公用的，完全独立，没有依赖)

MVC　这种方式，会造成　Activity，Fragment　代码非常的臃肿　（原因：分层不清晰）
MVP　这种方式，分层很清晰了，（引发接口地狱问题）

Dagger到底有什么用？
答：　对象解耦，什么又是对象解耦？　举例：　Ｐ　和　Ｖ　的耦合关系


云音乐：
以前　和　目前：　MVP模式采用

Android未来发展方向：
Flutter，ReactNative（很多的坑），uiapp，　跨平台框架（一套代码：　写多端　iOS，Android，Web前端）
找工作：大部分情况下，都会有一条（会Flutter优先）
1.Flutter还是可以去学一下，因为目前最火。
2.Kotlin + JetPack + MVVM模式   Kotlin（安卓未来的语言），  如果搞JavaEE开发，打死都不会用Kotlin，Kotlin简洁过头了，没有必要
3.安卓的岗位要求更加夸张（iOS/Android）
4.物联网，智能家居(NDK), 尽量走系统层

以后　和　未来　可能：　MVVM　＋　JetPack

### 1.2 实现效果图

<img src="https://gitee.com/tianyalusty/pic-go-repository/raw/master/img/202109262106241.png" alt="project_structure" style="zoom:50%;" />

