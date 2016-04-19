# Cache-Support
Cache-Support model used on DAO or Service in the J2EE stack. Enhance the speed of user access.
The idea of cache-support is add annotation on the methods of DAO or Service. Decoupled from the business. 
So it is very flexible. besides, there provide a jsonUtil utility. 
So far, this cache-support only support redis. it will be extend more in the next phase.

## Configuration
edit the spring configuration file. add as follows:

```
    <bean id="redisTemplate" class="org.springframework.data.redis.core.RedisTemplate">
        <property name="connectionFactory" ref="connectionFactory"/>
    </bean>

    <bean id="redisStringCache" class="sun.support.cache.redis.RedisStringCache">
        <property name="redisTemplate" ref="redisTemplate"/>
    </bean>
```

## Usage
### Cache object
```
        
        @Cacheable(
                namespace = Constants.CACHE_NAMESPACE,
                fieldsKey = {"#targetCode", "#dept", "#id"}
        )
        @Override
        public MobileHomePageEntity queryMobileHomePage(String targetCode, String dept, Integer id) {
            // DAO 
        }

```

### Cache Collection
#### Cache List
```
    
    @Cacheable(
            namespace = BIConstants.CACHE_NAMESPACE,
            fieldsKey = {"#targetCode", "#dept", "#id"},
            genericType = {MobileHomePageEntity.class}
    )
    @Override
    public List<MobileHomePageEntity> queryMobileHomePage(String targetCode, String dept, Integer id) {
        // DAO or Service
    }


```
#### Cache Map
```
    
    @Cacheable(
            namespace = BIConstants.CACHE_NAMESPACE,
            fieldsKey = {"#targetCode", "#dept", "#id"},
            genericType = {String.class, MobileHomePageEntity.class}
    )
    @Override
    public Map<String, MobileHomePageEntity> queryMobileHomePage(String targetCode, String dept, Integer id) {
        // DAO or Service
    }

```
#### Cache Generic Type
```
    
    @Cacheable(
            namespace = BIConstants.CACHE_NAMESPACE,
            fieldsKey = {"#targetCode", "#dept", "#id"},
            genericType = {Foo.class}
    )
    @Override
    public Bar<Foo> queryMobileHomePage(String targetCode, String dept, Integer id) {
        // DAO or Service
    }
    
```
#### set expire time

```
    
    @Cacheable(
            namespace = BIConstants.CACHE_NAMESPACE,
            fieldsKey = {"#targetCode", "#dept", "#id"},
            expire = 5000,
            genericType = {Foo.class}
    )
    @Override
    public Bar<Foo> queryMobileHomePage(String targetCode, String dept, Integer id) {
        // DAO or Service
    }
    
```

