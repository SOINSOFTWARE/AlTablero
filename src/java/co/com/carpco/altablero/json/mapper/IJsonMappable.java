/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.json.mapper;

import java.text.ParseException;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig;

/**
 *
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 31/03/2015
 */
public interface IJsonMappable<T> {
    
    static final ObjectMapper JSON_MAPPER = new ObjectMapper()
            .configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            .configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    
    public T getObjectFromJSON(String objectAsJSON);
    
    public Set<T> getObjectSetFromJSON(String objectAsJSON);
}