/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.bll;

import co.com.carpco.altablero.bo.User;
import co.com.carpco.altablero.hibernate.dao.BzUserDao;
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
public class BzUserBll {
    
    static Cache<String, User> cache;
    
    @Autowired
    private BzUserDao bzUserDao;

    private void initializeCache() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        
        MutableConfiguration<String, User> config
                = new MutableConfiguration<String, User>()
                .setTypes(String.class, User.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(ONE_DAY))
                .setWriteThrough(true)
                .setReadThrough(true)
                .setCacheLoaderFactory(new Factory<CacheLoader<String, User>>() {

                    @Override
                    public CacheLoader<String, User> create() {
                        return new CacheLoader<String, User>() {

                            @Override
                            public User load(String k) throws CacheLoaderException {
                                return new User(bzUserDao.getUserByDocumentNumber(k));
                            }

                            @Override
                            public Map<String, User> loadAll(Iterable<? extends String> itrbl) throws CacheLoaderException {
                                Map<String, User> answer = new HashMap<>();
                                itrbl.forEach((String k) -> answer.put(k, load(k)));

                                return answer;
                            }
                        };
                    }
                })
                .setCacheWriterFactory(new Factory<CacheWriter<String, User>>() {
                    @Override
                    public CacheWriter<String, User> create() {
                        CacheWriter<String, User> writer = new CacheWriter<String, User>() {

                            @Override
                            public void write(Cache.Entry<? extends String, ? extends User> entry) throws CacheWriterException {
                                
                            }

                            @Override
                            public void writeAll(Collection<Cache.Entry<? extends String, ? extends User>> clctn) throws CacheWriterException {
                                
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
    
    public User getUserByDocumentNumber(String documentNumber) {
        if (cache == null) {
            this.initializeCache();
        }
        
        User cached = cache.get(documentNumber);
        if (cached == null) {
            cached = new User(bzUserDao.getUserByDocumentNumber(documentNumber));
        }
        return cached;
    }
}