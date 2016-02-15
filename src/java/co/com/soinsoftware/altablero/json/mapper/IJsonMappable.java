/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.json.mapper;

import java.util.Set;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Carlos Rodriguez
 * @since 31/03/2015
 * @version 1.0
 */
public interface IJsonMappable<T> {

    static final ObjectMapper JSON_MAPPER = new ObjectMapper()
            .configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            .configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    static final ObjectWriter JSON_WRITER = new ObjectMapper().writer().withDefaultPrettyPrinter();

    static final Logger LOGGER = LoggerFactory.getLogger(IJsonMappable.class);

    public T getObjectFromJSON(String objectAsJSON);

    public Set<T> getObjectSetFromJSON(String objectAsJSON);
}
