/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.bll;

import co.com.carpco.altablero.bo.UserBO;
import co.com.carpco.altablero.hibernate.dao.UserDao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Factory;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import static javax.cache.expiry.Duration.ONE_DAY;
import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriter;
import javax.cache.integration.CacheWriterException;
import javax.cache.spi.CachingProvider;
/**
 *
 * @author Carlos
 */
@Service
public class UserBll {
    
    static Cache<String, UserBO> cache;
    
    @Autowired
    private UserDao userDao;

    private void initializeCache() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        
        MutableConfiguration<String, UserBO> config
                = new MutableConfiguration<String, UserBO>()
                .setTypes(String.class, UserBO.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(ONE_DAY))
                .setWriteThrough(true)
                .setReadThrough(true)
                .setCacheLoaderFactory(new Factory<CacheLoader<String, UserBO>>() {

                    @Override
                    public CacheLoader<String, UserBO> create() {
                        return new CacheLoader<String, UserBO>() {

                            @Override
                            public UserBO load(String k) throws CacheLoaderException {
                                return new UserBO(userDao.getUserByDocumentNumber(k));
                            }

                            @Override
                            public Map<String, UserBO> loadAll(Iterable<? extends String> itrbl) throws CacheLoaderException {
                                Map<String, UserBO> answer = new HashMap<>();
                                itrbl.forEach((String k) -> answer.put(k, load(k)));

                                return answer;
                            }
                        };
                    }
                })
                .setCacheWriterFactory(new Factory<CacheWriter<String, UserBO>>() {
                    @Override
                    public CacheWriter<String, UserBO> create() {
                        CacheWriter<String, UserBO> writer = new CacheWriter<String, UserBO>() {

                            @Override
                            public void write(Cache.Entry<? extends String, ? extends UserBO> entry) throws CacheWriterException {
                                
                            }

                            @Override
                            public void writeAll(Collection<Cache.Entry<? extends String, ? extends UserBO>> clctn) throws CacheWriterException {
                                
                            }

                            @Override
                            public void delete(Object o) throws CacheWriterException {
                                
                            }

                            @Override
                            public void deleteAll(Collection<?> clctn) throws CacheWriterException {
                                
                            }

                        };
                        return writer;
                    }
                })
                .setStatisticsEnabled(true);
        
        cache = cacheManager.createCache("personCache", config);
        config.isReadThrough();
    }
    
    public UserBO getUserByDocumentNumber(String documentNumber) {
        if (cache == null) {
            this.initializeCache();
        }
        
        return cache.get(documentNumber);
    }
}