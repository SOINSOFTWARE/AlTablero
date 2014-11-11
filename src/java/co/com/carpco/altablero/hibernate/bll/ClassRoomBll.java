/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.bll;

import co.com.carpco.altablero.bo.ClassRoomBO;
import co.com.carpco.altablero.hibernate.dao.ClassRoomDao;
import co.com.carpco.altablero.hibernate.entities.BzClassRoom;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
import org.jboss.weld.util.collections.ArraySet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Rodriguez
 */
@Service
public class ClassRoomBll {
    
    static Cache<String, Set> cache;
    
    @Autowired
    private ClassRoomDao classRoomDao;
    
    private void initializeCache() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        
        MutableConfiguration<String, Set> config
                = new MutableConfiguration<String, Set>()
                .setTypes(String.class, Set.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(ONE_DAY))
                .setWriteThrough(true)
                .setReadThrough(true)
                .setCacheLoaderFactory(new Factory<CacheLoader<String, Set>>() {

                    @Override
                    public CacheLoader<String, Set> create() {
                        return new CacheLoader<String, Set>() {

                            @Override
                            public Set load(String key) throws CacheLoaderException {
                                final Set<ClassRoomBO> classRoomBOSet = new ArraySet<>();
                                Set<BzClassRoom> bzClassRoomSet = classRoomDao.getClassRoomSet(key, "");
                                if (bzClassRoomSet != null && bzClassRoomSet.size() > 0) {
                                    bzClassRoomSet.forEach((BzClassRoom bzClassRoom) -> 
                                            classRoomBOSet.add(new ClassRoomBO(bzClassRoom)));
                                }
                                return classRoomBOSet;
                            }

                            @Override
                            public Map<String, Set> loadAll(Iterable<? extends String> itrbl) throws CacheLoaderException {
                                Map<String, Set> answer = new HashMap<>();
                                itrbl.forEach((String k) -> answer.put(k, load(k)));

                                return answer;
                            }
                        };
                    }
                })
                .setCacheWriterFactory(new Factory<CacheWriter<String, Set>>() {
                    @Override
                    public CacheWriter<String, Set> create() {
                        CacheWriter<String, Set> writer = new CacheWriter<String, Set>() {

                            @Override
                            public void write(Cache.Entry<? extends String, ? extends Set> entry) throws CacheWriterException {
                                
                            }

                            @Override
                            public void writeAll(Collection<Cache.Entry<? extends String, ? extends Set>> clctn) throws CacheWriterException {
                                
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
        
        cache = cacheManager.createCache("classRoomXYearCache", config);
        config.isReadThrough();
    }
    
    public Set<ClassRoomBO> getClassRoomSet(String year, String grade) {
        if (cache == null) {
            this.initializeCache();
        }
        
        return cache.get(year);
    }
}
