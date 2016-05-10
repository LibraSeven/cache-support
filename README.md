# Cache-Support
Cache-Support model used on DAO or Service in the J2EE stack. Enhance the speed of user access.
The idea of cache-support is add annotation on the methods of DAO or Service. Decoupled from the business. 
So it is very flexible. besides, there provide a jsonUtil utility. 
So far, this cache-support only support redis. it will be extend more in the next phase.

## Configuration
edit the spring configuration file. add as follows:
Redis Config Support
```
       <!-- Redis Config -->
       <bean id="redisConnectionFactory" class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory">
           <property name="poolConfig" ref="poolConfig"/>
           <property name="hostName" value="#{conf['redis.host']}"/>
           <property name="port" value="#{conf['redis.port']}"/>
           <property name="usePool" value="true"/>
           <property name="database" value="1"/>
       </bean>
   
       <bean id="redisTemplate" class="org.springframework.data.redis.core.RedisTemplate">
           <property name="connectionFactory" ref="redisConnectionFactory"/>
           <property name="keySerializer">
               <bean class="org.springframework.data.redis.serializer.StringRedisSerializer"/>
           </property>
           <property name="valueSerializer">
               <bean class="org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer"/>
           </property>
       </bean>
   
       <bean id="redisStringCache" class="sun.support.cache.redis.RedisStringCache">
           <property name="redisTemplate" ref="redisTemplate"/>
       </bean>
   
   ```
Codis Config Support
```
        <!-- Codis Config -->
        <bean id="codisConnectionFactory" class="sun.support.cache.codis.CodisConnectionFactory">
            <property name="database" value="0"/> <!-- codis does not support db-select -->
            <property name="uris" value="sunyameng:2181"/>
            <property name="zkProxyDir" value="/zk/codis/db_test/proxy"/>
            <property name="timeout" value="30000"/>
            <property name="poolConfig" ref="poolConfig"/>
            <property name="usePool" value="true"/>
        </bean>
    
        <bean id="redisTemplate" class="org.springframework.data.redis.core.RedisTemplate">
            <property name="connectionFactory" ref="codisConnectionFactory"/>
            <property name="keySerializer">
                <bean class="org.springframework.data.redis.serializer.StringRedisSerializer"/>
            </property>
            <property name="valueSerializer">
                <bean class="org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer"/>
            </property>
        </bean>
    
        <bean id="codisStringCache" class="sun.support.cache.redis.CodisStringCache">
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
            namespace = Constants.CACHE_NAMESPACE,
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
            namespace = Constants.CACHE_NAMESPACE,
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
            namespace = Constants.CACHE_NAMESPACE,
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
            namespace = Constants.CACHE_NAMESPACE,
            fieldsKey = {"#targetCode", "#dept", "#id"},
            expire = 5000,
            genericType = {Foo.class}
    )
    @Override
    public Bar<Foo> queryMobileHomePage(String targetCode, String dept, Integer id) {
        // DAO or Service
    }
    
```

