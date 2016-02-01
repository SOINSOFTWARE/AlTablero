/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 15/07/2015
 */
@Service
public class JsonDateFormat {
    
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssX";
    
    private final SimpleDateFormat simpleDateFormat;

    public JsonDateFormat() {
        simpleDateFormat = new SimpleDateFormat(JsonDateFormat.DATE_FORMAT);
    }
    
    public Date format(String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }
}
