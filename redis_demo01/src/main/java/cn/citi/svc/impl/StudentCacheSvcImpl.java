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
    @CustomCacheable(cacheName = "studentSvcCache1", key = "#p0", expire = 30)
    public String say1(String name) {
        return "say1 method called in StudentCacheSvcImpl: " + name;
    }

    @Override
    @CustomCacheable(cacheName = "studentSvcCache2", key = "#p0", expire = 30)
    public String say2(String name) {
        return "say2 method called in StudentCacheSvcImpl: " + name;
    }

    @Override
    @Cacheable(value = "studentSvcCache3", key = "#name")
    public String say3(String name) {
        return "say3 method called in StudentCacheSvcImpl: " + name;
    }

    @Override
    @Cacheable(value = "studentSvcCache4#30" ,key = "#name")
    public String say4(String name) {
        return "say4 method called in StudentCacheSvcImpl: " + name;
    }
}

