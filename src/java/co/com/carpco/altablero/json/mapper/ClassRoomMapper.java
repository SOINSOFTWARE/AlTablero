/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.json.mapper;

import co.com.carpco.altablero.entity.ClassRoomBO;
import co.com.carpco.altablero.utils.JsonDateFormat;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 17/03/2015
 */
@Service
public class ClassRoomMapper implements IJsonMappable<ClassRoomBO> {
    
    protected static final Logger LOGGER = LoggerFactory.getLogger(ClassRoomMapper.class);
    
    @Autowired
    private JsonDateFormat dateFormat;

    @Override
    public ClassRoomBO getObjectFromJSON(String objectAsJSON) {
        ClassRoomBO classRoom = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                classRoom = JSON_MAPPER.readValue(objectAsJSON, ClassRoomBO.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return classRoom;
    }
    
    @Override
    public Set<ClassRoomBO> getObjectSetFromJSON(String objectAsJSON) {
        Set<ClassRoomBO> classRoomSet = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                Map<String, List<ClassRoomBO>> classRoomMap =
                    JSON_MAPPER.readValue(objectAsJSON, new TypeReference<Map<String, List<ClassRoomBO>>>() { });
                if (classRoomMap != null && !classRoomMap.isEmpty()) {
                    classRoomSet = new HashSet(classRoomMap.values().iterator().next());
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return classRoomSet;
    }
}
