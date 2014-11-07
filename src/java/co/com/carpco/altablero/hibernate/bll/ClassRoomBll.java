/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.bll;

import co.com.carpco.altablero.bo.ClassRoomBO;
import co.com.carpco.altablero.bo.User;
import static co.com.carpco.altablero.hibernate.bll.BzUserBll.cache;
import co.com.carpco.altablero.hibernate.dao.ClassRoomDao;
import co.com.carpco.altablero.hibernate.entities.BzClassRoom;
import java.util.Calendar;
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
    
    static Cache<Integer, ClassRoomBO> cache;
    
    @Autowired
    private ClassRoomDao classRoomDao;
    
    private void initializeCache() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        
        MutableConfiguration<Integer, ClassRoomBO> config
                = new MutableConfiguration<Integer, ClassRoomBO>()
                .setTypes(Integer.class, ClassRoomBO.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(ONE_DAY))
                .setWriteThrough(true)
                .setReadThrough(true)
                .setCacheLoaderFactory(new Factory<CacheLoader<Integer, ClassRoomBO>>() {

                    @Override
                    public CacheLoader<Integer, ClassRoomBO> create() {
                        return new CacheLoader<Integer, ClassRoomBO>() {

                            @Override
                            public ClassRoomBO load(Integer key) throws CacheLoaderException {
                                return new ClassRoomBO(classRoomDao.getClassRoom(key));
                            }

                            @Override
                            public Map<Integer, ClassRoomBO> loadAll(Iterable<? extends Integer> itrbl) throws CacheLoaderException {
                                Map<Integer, ClassRoomBO> answer = new HashMap<>();
                                if (itrbl != null && itrbl.iterator().hasNext())
                                {
                                    itrbl.forEach((Integer key) -> answer.put(key, load(key)));
                                } else 
                                {
                                    
                                }

                                return answer;
                            }
                        };
                    }
                })
                .setCacheWriterFactory(new Factory<CacheWriter<Integer, ClassRoomBO>>() {
                    @Override
                    public CacheWriter<Integer, ClassRoomBO> create() {
                        CacheWriter<Integer, ClassRoomBO> writer = new CacheWriter<Integer, ClassRoomBO>() {

                            @Override
                            public void write(Cache.Entry<? extends Integer, ? extends ClassRoomBO> entry) throws CacheWriterException {
                                
                            }

                            @Override
                            public void writeAll(Collection<Cache.Entry<? extends Integer, ? extends ClassRoomBO>> clctn) throws CacheWriterException {
                                
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
        
        cache = cacheManager.createCache("classRoomCache", config);
        config.isReadThrough();
    }
    
    public Set<ClassRoomBO> getClassRoomSet(String year, String grade) {
        if (cache == null) {
            this.initializeCache();
        }
        
        final Set<ClassRoomBO> classRoomBOSet = new ArraySet<>();
        Set<BzClassRoom> bzClassRoomSet = classRoomDao.getClassRoomSet(year, grade);
        if (bzClassRoomSet != null && bzClassRoomSet.size() > 0) {
            bzClassRoomSet.forEach((BzClassRoom bzClassRoom) -> 
                    classRoomBOSet.add(new ClassRoomBO(bzClassRoom)));
        }
        
        return classRoomBOSet;
    }
}
