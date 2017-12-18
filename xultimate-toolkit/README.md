# xultimate-toolkit #

The X-Ultimate Toolkit provides a JavaEE application reference architecture based Spring Framework.
	
	
## xultimate-core ##

* 整合日志，包括commons-logging、JUL、log4j。统一使用slf4j + log4j2，包含Async和Sync两种配置方式。
* 日期处理，包括joda-time、prettytime的使用；添加农历日历(ChineseCalendarGB)。
* 封装commons-lang3，添加CompareToBuilderUtils、EqualsBuilderUtils、HashCodeBuilderUtils、ToStringBuilderUtils。
* 添加MapBuilder、ListBuilder、SetBuilder。
* 添加消息摘要(DigestUtils)、AES/RSA加密解密(CipherUtils)。
* 添加RandomNumberUtils。
* 一个秒表类StopWatch，并封装实现性能检测(PerformanceMonitor)。
* 封装pinyin4j，添加PinyinUtils。
* 封装Spring(AnnotationUtils、BeanUtils、ClassUtils、Assert)，对BeanUtils添加map转object、object转map支持，添加复制指定属性功能。
* 整合其他utils。包括commons-io、commons-collections、commons-codec等。
* 添加UnicodeInputStream/UnicodeReader解决读取ANSI文本文件乱码。
* 提供JSONTemplate，包括fastjson和jackson的封装；提供JsonUtils生成JSOUP格式。
* 提供Serializer和Deserializer，包括对Java、Kryo、Protobuf、Protostuff的封装。
* 提供AbstractObjectInput和AbstractObjectOutput，包含Java、Kryo、Protobuf、Protostuff的实现。
* 提供Compressor和Decompressor，包括对GZIP和Snappy的封装。
* 对tamper、cglib、reflectasm的使用。
* 包含大部分相关功能的测试类。
* 测试类中所有使用过PerformanceMonitor的都包含相关代码的性能测试。


## xultimate-context ##

* 提供CharsetDetector，包括对icu4j、jchardet、cpdetector、juniversalchardet的封装。
* 提供Encoder和Decoder，包括对Base64、Hex、Url的封装。
* 提供Encryptor和Decryptor，包括封装CipherUtils进一步实现AES/RSA加密解密。
* 提供MessageUtils工具类，内部使用Spring国际化(i18n)，用于JSON交互。
* 图片处理工具ImageUtils。是对Java图片处理的封装，包含一些可单独使用的功能，如生成文字图片等。
* 图片处理工具ImageCompositeTemplate，包括对加水印等的功能处理，分别对im4java和java进行封装。
* 图片处理工具ImageCropTemplate，包括对剪裁等的功能处理，分别对im4java和java进行封装。
* 图片处理工具ImageInfoTemplate，包括对格式转换等的功能处理，分别对im4java和java进行封装。
* 图片处理工具ImageResizeTemplate，包括对改变大小等的功能处理，分别对im4java和java进行封装。
* 以上图片处理为第一版，现增加第二版，主要用于支持BufferedImage和File两种方式。取出了getImageInfo，改为在DefaultImageResource内提供，目前先使用第一种，之后视情况在做合并。
* 提供BootstrapFactoryBean、ServerBootstrapFactoryBean，通过Spring管理Netty中Bootstrap、ServerBootstrap对象的生命周期。ShowCase见xultimate-crawler。
* 提供SocketChannelInitializerFactoryBean，通过Spring管理Netty中ChannelInitializer\<SocketChannel\>对象的生命周期。ShowCase见xultimate-crawler。
* 提供RpcKryoObjectCodec、JavaObjectCodec，通过Kryo、Java默认的序列化/解序列化方式，支持以单例形式在Netty中使用，可通过Spring管理；ShowCase见xultimate-crawler。
* 提供RpcProtobufObjectCodec、RpcProtostuffObjectCodec，通过Protobuf、Protostuff的序列化/解序列化方式，支持以单例形式在Netty中使用，可通过Spring管理；ShowCase见xultimate-crawler。
* 性能检测类PerformanceMonitorForEachMethodInterceptor，是对StopWatch的AOP行为处理，用于检测一个调用链中的每个标记方法，每个方法单独输出。
* 性能检测类PerformanceMonitorForOneMethodInterceptor，是对StopWatch的AOP行为处理，用于检测一个调用链中的每个标记方法，每个线程开始后第一个标记方法作为总输出。
* 加密属性置换器EncryptPropertyPlaceholderConfigurer，通过扩展Spring的属性置换器，提供加密功能。
* 提供工具类BeanFactoryContext/ApplicationContextUtils。
* 包括一些Spring中部分功能的使用，如自定义属性编辑器、事件。
* 包含大部分相关功能的测试类。
* 测试类中所有使用过PerformanceMonitor的都包含相关代码的性能测试。


## xultimate-web ##

* ResourceUtils，是对Spring Resource的封装。
* 提供StringToFileArrayPropertyEditor，是String到File[]的自定义属性编辑器。
* GzipCompressFilter，用于压缩响应流。
* LoggerHostListener，用于配置Slf4j MDC。
* 服务类BrowserCacheGenerator用于处理浏览器端缓存。
* 服务类CookieGenerator用于处理浏览器Cookie。
* HtmlUtils、ServletRequestUtils、WebUtils，主要内容是对Spring(Utils的封装)。
* 服务类ShortURLGenerator，用于生成短域名。
* 服务类RememberMeService，用于实现自动登陆功能。
* HttpClient 4.3的Spring Bean配置形式，同时提供一个RestTemplate的使用。
* 待用：MobileChecker
* 包含大部分相关功能的测试类。


## xultimate-jdbc ##

* 使用druid数据源配置，包含和c3p0的对比测试。
* 提供MySQL主键生成器(基于表)，通过重写Spring的MySQLMaxValueIncrementer，添加step功能。即可部署在不同机器/表中同时主键不会重复。
* 提供序列主键生成器(基于序列)，通过重写Spring的AbstractSequenceMaxValueIncrementer，添加step功能。即可部署在不同机器/表中同时主键不会重复。包含Oracle和H2的各自实现。
* 提供主键生成器AbstractKeyMaxValueIncrementer(基于Key/Value)，即可部署在不同机器/Key中同时主键不会重复。包括JedisMaxValueIncrementer和ShardedJedisMaxValueIncrementer实现，见xultimate-context-support。
* 提供StateSet，使用TINYINT替代MySQL中BIT和SET数据类型，支持"="和"FIND_IN_SET"的需求并且会通过索引进行匹配。原来使用数字，目前使用枚举代替数字。见测试类理解，具体使用见xultimate-mybatis。
* 提供RoutingDataSource和DataSourceContext。用于实现数据源切换功能(分库)。分表见下xultimate-hibernate、xultimate-shard。
* 添加ChainedTransactionManager，采用Best Efforts 1PC模式处理多事物。代码拷贝自spring-data-commons项目。无法配合RoutingDataSource完成分布式事物功能。
* 添加RoutingDataSourceTransactionManager, 采用Best Efforts 1PC模式处理多事物。使用懒加载的方式，实现分布式事物。见测试。
* 添加RoutingProxyDataSourceFactoryBean，利用cglib和DataSourceContext实现RoutingDataSource的功能，配合RoutingDataSourceTransactionManager利用Spring事物注解从使用习惯上满足分布式事物处理。
* 包含大部分相关功能的测试类。
* 测试类中所有使用过PerformanceMonitor的都包含相关代码的性能测试。


## xultimate-context-support ##

* 包括HTTL(HTTLEngineUtils)、Freemarker、StringTemeplateV3、StringTemplateV4、Velocity的模板处理Utils类。
* 提供Formatter，包括对HTTL、Spel、FreeMarker、MessageFormat、StringTemplateV3、StrpingTemplateV4、Velocity的封装。
* 包括对FastDFS Java Api的封装，主要有ClientGlobalInitializer、StorageClientTemplate、FastDFSUtils等。
* 提供XMemcachedClientTemplate，是对XMemcached的封装，重写SerializingTranscoder，使其支持多种序列化机制Java、Kryo、Protobuf、Protostuff；同时具备压缩功能，可配置为GZIP、Snappy。
* 提供XMemcachedCacheManager用于支持Spring Cache和Spring缓存注解。
* 提供MemcachedClientMutex，内部使用add实现分布式互斥锁。
* 提供MemcachedClientLimiter，用于限制访问记录。
* 提供JedisTemplate/ShardedJedisTemplate，是对Jedis的封装，不支持内部修改序列化。
* 提供序列主键生成器(基于Redis)，包括ShardedJedisMaxValueIncrementer/JedisMaxValueIncrementer。见xultimate-jdbc。
* 提供quartz的集群实例确保高可用，同时，支持类似与JobDetailFactoryBean的Spring Bean配置形式。
* 提供Spring Mail的使用ShowCase，包括各种使用情况、如用HTTL模板做邮件模板、加密SMTP用户密码、收件人等默认值、包含昵称、使用TaskExecutor异步处理等。
* 提供EmailAddressBuilder代替new InternetEmail(...).toString()形式。
* 包含大部分相关功能的测试类。
* 测试类中所有使用过PerformanceMonitor的都包含相关代码的性能测试。


## xultimate-hibernate ##

* 使用Spring Data JPA实现，包含一个使用了扩展DAO的ShowCase。
* 提供ProxyInitializer初始化器，用于初始化代理对象，包括Hibernate(HibernateProxyInitializer)实现。
* 提供DatabaseNamingStrategy/DatabaseNamingStrategyModel，可以实现可配置的数据库命名策略。
* 封装了一套JPA形式的动态查询，采用面向对象方式实现，支持and、or、not、eq、not_eq、like(带有%的不同形式的模糊查询)、not_like、gt、lt、ge、le、in、between操作。面向后台动态查询页面使用(强烈推荐)，只需要配置对应参数并通过面向对象方式构建即可。
* 提供ItemsSpecification(动态查询)，配合Sort、Pageable支持排序、分页等。
* 提供IDEntity，是泛型ID实体类，重写了equals、hashCode、toString方法。
* 提供VersionEntity，继承IDEntity，包含了version，有Hibernate自动维护。
* 提供DateEntity，继承VersionEntity，包含了createTime和updateTime，并通过监听器自动维护两个字段。
* 提供NormEntity、继承DateEntity，包含了boost，监听器只维护为空值的情况。
* 提供GenericDAO，包含动态查询借口和动态查询配合排序或分页的接口。
* 提供GenericBiz/DefaultGenericBiz，已经都封装好，便于重写自己的Biz层和封装Service层。
* 测试类中CacheUsage展示了Hibernate3的二级缓存各种出现形式、便于理解一级、二级(查询)缓存。不过因为hibernate-memcached暂未升级到Hibernate4，而且觉得二级缓存控制起来不方便、而自己在Biz层手动控制或通过Spring缓存支持memcached等效果更好、更灵活。
* 提供SessionFactoryUtils，用于转换为SessionFactory实例。通过SessionFactoryUtils.invokeSession(sessionFactory, interceptor, callback)添加拦截器形式改变表名称实现分区表功能。
* SimpleJpaRepository类的方法都采用了@Transactional，因此如果Service曾方法标明NOT_SUPPORT，调用到SimpleJpaRepository的方法还是会开启事物。
* 注意：监听注解@PostPersist标注的方法中若改变值会引发UPDATE。INSERT后触发@PostPersist，若改变了值且在一个持久化上下文中(一级缓存)，提交前触发@PreUpdate，然后执行UPDATE。基于此建议慎用，若需要发布事件可采用异步处理方式。
* 注意：监听注解@PreUpdate在事物准备提交和事物真正提交之间触发，会影响持久化上下文(一级缓存)。
* 注意：在监听注解@PostUpdate改变值则没有意义，反而会导致数据库和应用端映射不一致，在清空持久化上下文(一级缓存)后，事物真正提交前触发。慎用。
* 包含大部分相关功能的测试类。


## xultimate-mybatis ##

* 重构SqlSessionFactoryBean，去除configLocation和configurationProperties参数，直接提供Configuration类型的注入。
* 提供@MyBatisRepository，用于MapperScannerConfigurer扫描使用。
* 提供动态查询的操作实体，解析见测试，用法上没有Hibernate提供的易用，推荐只面向后台动态查询页面使用。
* 提供BigDecimalTypeHandler，数据端使用BIGINT/UNSIGNED INT存储。
* 提供BooleanTypeHandler，数据库使用CHAR(0)存储。""表示true、NULL表示false。缺点是无法建立索引，因此不适合用于高效检索条件部分。
* 提供StateSetTypeHandler，数据库使用TINYINT存储。用来代替BIT/SET。支持部分匹配和完全匹配功能，解决BIT/SET部分匹配无法利用索引问题。(原来支持8个类型，但查询数量多，现在支持4个，这样最多有15个组合)
* 提供IntegerNullParameterTypeHandler、LongNullParameterTypeHandler，主要用于设参时将Java端的null值处理为数据端的-1。展示是将数据端的-1处理为Java端的null。可以更好的建立高效索引。-1为默认值，可继承修改。
* 提供StringNullParameterTypeHandler、ClobNullParameterTypeHandler，主要用于设参时将Java端的null值处理为数据端的""。展示是将数据端的""处理为Java端的null。可以更好的建立高效索引。""为默认值，可继承修改。
* 提供ReverseStringNullParameterTypeHandler，主要用于将字符串反转，实现后缀索引。同时设参时将Java端的null值处理为数据端的""。展示是将数据端的""处理为Java端的null。可以更好的建立高效索引。""为默认值，可继承修改。
* 去除类似Hibernate那样的复杂映射，将模型定义从领域模型改为POJO，采用最直接的数据库-实体类的映射，保持简单映射。
* 提供了PerformanceTest，测试不使用事物注解、使用事物注解(NOT_SUPPORT)、使用事物注解(REQUIRED,readOnly=false)、使用事物注解(REQUIRED,readOnly=true)的执行效率。
* 包含大部分相关功能的测试类。
* 注意：Hibernate中实体模型为do，MyBatis中实体模型为POJO。
* 注意：对于单条查询，执行效率：不使用事物注解 > 使用事物注解(NOT_SUPPORT) > 使用事物注解(REQUIRED,readOnly=false) > 使用事物注解(REQUIRED,readOnly=true)。
* 注意：@Transactional中的readOnly不是控制是否开启事物的，而是类似于MySQL中的readOnly配置参数的效果的，如即便执行INSERT/UPDATE，也会忽略命令到binlog中(只是猜测)。
* 注意：如果是企业级项目，可采用Hibernate。如果对高性能、可控性、灵活性等各方面有要求推荐采用mybatis(强烈推荐)，也可用JdbcTemplate，代码可控性、灵活性更强，性能也更好。分表见xultimate-shard。

## xultimate-shard ##

* 主要根据一篇文章进行的重新设计，将概念进行了重新的抽象，结构也进行了修改。用于垂直拆分(分库)、水平拆分(分表)的规则处理。
* 提供ShardInfoGenerator，属于公众接口，按此方式应用可以分布式部署。
* 提供ShardUtils，用于处理分库、分表后结果处理的排序、分页、AVG、SUM、COUNT、去重等操作。
* 添加po包，包含了最小粒度的数据模型。分表规则目前先采用数据库存放。
* MyBatisShardInfoGenerator为其ShardInfoGenerator的默认实现方式。
* 优势: 数据划分规则集中处理。实现单表到多库多表的处理，采用HASH和范围结合的方式，同时具备无需数据迁移且避免存在热点表的问题。
* 优势: 使用数据库存储，BIZ层使用Memcached做缓存，之所以没有默认采用Redis是因为数据库的可视化客户端能让我们更清晰的看到关联关系。
* 优势: 作为服务端，可分布式部署，客户端只需要调用即可。即客户端内部不需要包含分表分库逻辑。
* 优势: 如果需要读写分离、软负载、双主维护的支持，可扩展ShardInfo，或添加ShardInfoHandler服务。
* 注: xultimate-hibernate工程采用的是拦截器的实现分区表方式，结合此功能也可实现单表划分成多库多表形式，只是没有xultimate-mybatis的灵活性高。
* 注：xultimate-jdbc现已实现分布式事物处理。


## xultimate-webmvc ##

* 提供BrowserCacheFilter，通过BrowserCacheGenerator实现，用于生成浏览器缓存。
* 提供BrowseLimiterFilter，通过MemcachedClientLimiter实现，用于进行访问限制。
* 扩展HttlViewResolver，通过Spring中Properties的形式配置HTTL，或通过属性置换器实现外部文件配置。


## xultimate-test ##

* 提供一套Spring MVC的使用ShowCase，包括可能的各种配置。
* Session跨域处理。
* 通过memcached-session-manager实现Session共享集群。
* TProfiler性能监测工具的topmethod.log日志文件的图形分析。
* 通过sh/collect.sh数据，由sh/analyze.sh提取数据，最后完成MYSQL QPS的图形分析。
* 包括线程部分中volatile、AtomicInteger的具体效果，以及concurrentlinkdhashmap、forkjoin、disruptor、CountDownLatch、Semaphore、CyclicBarrier等的ShowCase。
* 收集的类，包括<<深入理解Java虚拟机>>部分的案例，以及自己的几个扩充。
* 包括JDK中，NIO、AIO的API使用的ShowCase，没考虑线程池部分。只介绍API。
* Mockito、JUnit、对Spring MVC测试的ShowCase，。

### 打算(Memcached) ###

* 将MemcahcedCallback进一步细分，处理成专用于集合或实体的模板方法模式。


### 打算(分布式事物) ###
* RoutingProxyDataSourceFactoryBean和RoutingDataSourceTransactionManager初步完成，进行了简单的测试，但考虑的情况不多。应该在细微的考虑一下。