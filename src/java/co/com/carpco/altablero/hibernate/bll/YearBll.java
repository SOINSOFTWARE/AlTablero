/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.bll;

import co.com.carpco.altablero.bo.YearBO;
import co.com.carpco.altablero.hibernate.dao.YearDao;
import co.com.carpco.altablero.hibernate.entities.BzYear;
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
public class YearBll {
    
    static Cache<Integer, Set> cache;
    
    @Autowired
    private YearDao yearDao;
    
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
                                final Set<YearBO> yearBOSet = new HashSet<>();
                                Set<BzYear> bzYearSet = yearDao.getYearSet();
                                if (bzYearSet != null && bzYearSet.size() > 0) {
                                    bzYearSet.forEach((BzYear bzYear) -> 
                                            yearBOSet.add(new YearBO(bzYear)));
                                }
                                return yearBOSet;
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
        
        cache = cacheManager.createCache("yearCache", config);
        config.isReadThrough();
    }
    
    public Set<YearBO> getYearSet() {
        if (cache == null) {
            this.initializeCache();
        }
        
        return cache.get(1);
    }
}
