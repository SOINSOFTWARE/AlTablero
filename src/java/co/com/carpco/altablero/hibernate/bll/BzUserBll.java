/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.bll;

import co.com.carpco.altablero.hibernate.dao.BzUserDao;
import co.com.carpco.altablero.hibernate.dao.IBzUserDao;
import co.com.carpco.altablero.hibernate.entities.BzUser;
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
import static javax.cache.expiry.Duration.ONE_HOUR;
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
public class BzUserBll implements IBzUserDao{
    
    static Cache<String, BzUser> cache;
    
    @Autowired
    private BzUserDao bzUserDao;

    private void initializeCache() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        
        MutableConfiguration<String, BzUser> config
                = new MutableConfiguration<String, BzUser>()
                .setTypes(String.class, BzUser.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(ONE_HOUR))
                .setWriteThrough(true)
                .setReadThrough(true)
                .setCacheLoaderFactory(new Factory<CacheLoader<String, BzUser>>() {

                    @Override
                    public CacheLoader<String, BzUser> create() {
                        return new CacheLoader<String, BzUser>() {

                            @Override
                            public BzUser load(String k) throws CacheLoaderException {
                                return bzUserDao.getUserByDocumentNumber(k);
                            }

                            @Override
                            public Map<String, BzUser> loadAll(Iterable<? extends String> itrbl) throws CacheLoaderException {
                                Map<String, BzUser> answer = new HashMap<>();
                                itrbl.forEach((String k) -> answer.put(k, load(k)));

                                return answer;
                            }
                        };
                    }
                })
                .setCacheWriterFactory(new Factory<CacheWriter<String, BzUser>>() {
                    @Override
                    public CacheWriter<String, BzUser> create() {
                        CacheWriter<String, BzUser> writer = new CacheWriter<String, BzUser>() {

                            @Override
                            public void write(Cache.Entry<? extends String, ? extends BzUser> entry) throws CacheWriterException {
                                
                            }

                            @Override
                            public void writeAll(Collection<Cache.Entry<? extends String, ? extends BzUser>> clctn) throws CacheWriterException {
                                
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
    
    @Override
    public BzUser getUserByDocumentNumber(String documentNumber) {
        if (cache == null) {
            this.initializeCache();
        }
        
        BzUser cached = cache.get(documentNumber);
        if (cached == null) {
            cached = bzUserDao.getUserByDocumentNumber(documentNumber);
        }
        return cached;
    }
}