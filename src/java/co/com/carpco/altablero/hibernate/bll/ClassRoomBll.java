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
import java.util.HashSet;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Rodriguez
 */
@Service
public class ClassRoomBll {
    
    static Cache<Integer, Set> cache;
    
    @Autowired
    private ClassRoomDao classRoomDao;
    
    private void initializeCache() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        
        MutableConfiguration<Integer, Set> config
                = new MutableConfiguration<Integer, Set>()
                .setTypes(Integer.class, Set.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(ONE_DAY))
                .setWriteThrough(true)
                .setReadThrough(true)
                .setCacheLoaderFactory(new Factory<CacheLoader<Integer, Set>>() {

                    @Override
                    public CacheLoader<Integer, Set> create() {
                        return new CacheLoader<Integer, Set>() {

                            @Override
                            public Set load(Integer key) throws CacheLoaderException {
                                final Set<ClassRoomBO> classRoomBOSet = new HashSet<>();
                                Set<BzClassRoom> bzClassRoomSet = classRoomDao.getClassRoomSet(key);
                                if (bzClassRoomSet != null && bzClassRoomSet.size() > 0) {
                                    bzClassRoomSet.forEach((BzClassRoom bzClassRoom) -> 
                                            classRoomBOSet.add(new ClassRoomBO(bzClassRoom)));
                                }
                                return classRoomBOSet;
                            }

                            @Override
                            public Map<Integer, Set> loadAll(Iterable<? extends Integer> itrbl) throws CacheLoaderException {
                                Map<Integer, Set> answer = new HashMap<>();
                                itrbl.forEach((Integer k) -> answer.put(k, load(k)));

                                return answer;
                            }
                        };
                    }
                })
                .setCacheWriterFactory(new Factory<CacheWriter<Integer, Set>>() {
                    @Override
                    public CacheWriter<Integer, Set> create() {
                        CacheWriter<Integer, Set> writer = new CacheWriter<Integer, Set>() {

                            @Override
                            public void write(Cache.Entry<? extends Integer, ? extends Set> entry) throws CacheWriterException {
                                
                            }

                            @Override
                            public void writeAll(Collection<Cache.Entry<? extends Integer, ? extends Set>> clctn) throws CacheWriterException {
                                
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
    
    public Set<ClassRoomBO> getClassRoomSet(int idSchool, String year, String grade) {
        if (cache == null) {
            this.initializeCache();
        }
        
        Set<ClassRoomBO> classRoomSet = cache.get(idSchool);
        Set<ClassRoomBO> finalclassRoomSet = new HashSet<>();
        for(ClassRoomBO classRoom : classRoomSet) {
            if (classRoom.getYearBO().getName().equals(year) && 
                    (grade.equals("0") || classRoom.getGradeBO().getId().toString().equals(grade))) {
                finalclassRoomSet.add(classRoom);
            }
        }
        
        return finalclassRoomSet;
    }
    
    public ClassRoomBO getClassRoom(int idClassRoom, int idSchool) {
        if (cache == null) {
            this.initializeCache();
        }
        
        Set<ClassRoomBO> classRoomSet = cache.get(idSchool);
        ClassRoomBO classRoomBO = null;
        if (classRoomSet != null && !classRoomSet.isEmpty()) {
            for(ClassRoomBO classRoom : classRoomSet) {
                if(classRoom.getId().equals(idClassRoom)) {
                    classRoomBO = classRoom;
                    break;
                }
            }
        }
        return classRoomBO;
    }
}
