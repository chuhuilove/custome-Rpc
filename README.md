# 概述

手写一个Rpc框架,只是简单的实现服务提供者,服务消费者,和注册中心三个功能,至于其他的诸如服务治理的功能,先不考虑


主要分为几个模块:

* `customer`: 服务生产者模块
* `framework`: 和框架有关的通用工具模块
* `protocol`: 传输协议(http和netty)模块
* `provider`: 服务提供者模块
* `register`: 注册中心模块
* `interfaces`: 生产者和消费共同拥有的模块






