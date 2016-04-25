/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.bll;

import co.com.soinsoftware.altablero.utils.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Carlos Rodriguez
 * @since 03/02/2015
 * @version 1.0
 */
public abstract class AbstractBLL {
    
    protected static final String MODULE_CLASS = "class/";
    protected static final String MODULE_CLASSROOM = "classroom/";
    protected static final String MODULE_GRADE = "grade/";
    protected static final String MODULE_PERIOD = "period/";
    protected static final String MODULE_SCHOOL = "school/";
    protected static final String MODULE_SUBJECT = "subject/";
    protected static final String MODULE_TIME = "time/";
    protected static final String MODULE_USER = "user/";
    protected static final String MODULE_USERTYPE = "usertype/";
    protected static final String MODULE_YEAR = "year/";
    
    protected static final String PATH_ALL = "all";
    protected static final String PATH_BY = "by";
    protected static final String PATH_BY_TYPE = "byType";
    protected static final String PATH_CURRENT_YEAR = "currentYear";
    protected static final String PATH_EXCLUDING_CLASS = "excludingClass";
    protected static final String PATH_SAVE = "save";
    protected static final String PATH_SAVE_CLASSROOM_X_STUDENT = "saveClassRoomxStudent";
    protected static final String PATH_STUDENTS_NOT_LINKED = "studentsNotLinked";
    protected static final String PATH_TEACHER_NOT_DIRECTORS = "teacherNotDirectors";
    protected static final String PATH_VALIDATE = "validate";
    
    protected static final String PARAMETER_CLASSROOM_ID = "classRoomId";
    protected static final String PARAMETER_CODE = "code";
    protected static final String PARAMETER_DOCUMENT_NUMBER = "documentNumber";
    protected static final String PARAMETER_GRADE = "grade";
    protected static final String PARAMETER_SCHOOL_ID = "schoolId";
    protected static final String PARAMETER_TIME = "time";
    protected static final String PARAMETER_TYPE_CODE = "userTypeCode";
    protected static final String PARAMETER_USER_ID = "userId";
    protected static final String PARAMETER_YEAR = "year";
    
    protected static final String ADD_PARAMETERS = "?";
    protected static final String CONCAT = "&";
    protected static final String WITH_VALUE = "=";
    
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractBLL.class);
    
    @Autowired
    protected HttpRequest httpRequest;
    
    protected String buildRequestParameter(final String init, final String param,
            final Object value) {
        final StringBuilder builder = new StringBuilder();
        builder.append(init);
        builder.append(param);
        builder.append(WITH_VALUE);
        builder.append(value);
        return builder.toString();
    }    
}
