package cn.citi.svc.impl;

import cn.citi.cache.CustomCacheable;
import cn.citi.svc.StudentCacheSvc;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Carson
 * @created 2025/3/23 星期日 上午 11:56
 */
@Service
public class StudentCacheSvcImpl implements StudentCacheSvc {
    @Override
    @CustomCacheable(cacheName = "studentSvcCache", key = "#p0", expire = 10)
    public String say(String name) {
        return "say method called in StudentCacheSvcImpl: " + name;
    }

    @Override
    @CustomCacheable(cacheName = "studentSvcCache1", key = "#p0", expire = 30)
    public String say2(String name) {
        return "say2 method called in StudentCacheSvcImpl: " + name;
    }

    @Override
    @Cacheable(key = "'studentSvcCache3:' + #name", value = "cacheManager")
    public String say3(String name) {
        return "say2 method called in StudentCacheSvcImpl: " + name;
    }
}

