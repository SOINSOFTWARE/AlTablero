/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 24/02/2016
 */
@Service
public class FileUtils {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);
    
    private static final String HTTP_PATH = "http://localhost:8080/AlTableroIMG/";
    //private static final String HTTP_PATH = "http://198.12.153.71:81/AlTableroIMG/";
    
    private static final String PATH = "C:\\apache-tomcat-8.0.14\\webapps\\AlTableroIMG\\";
    //private static final String PATH = "/usr/local/tomcat8/webapps/AlTableroIMG/";
    
    public boolean savePhotoInServer(final MultipartFile file, final String name) {
        boolean saved = false;
        final String fileName = PATH + name;
        if (file != null && !file.isEmpty()) {
            try {
                final String directory = fileName.substring(0, fileName.lastIndexOf(File.separator));
                final File dirFile = new File(directory);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
                final byte[] bytes = file.getBytes();
                final OutputStream os = new FileOutputStream(new File(fileName));
                try (final BufferedOutputStream stream = new BufferedOutputStream(os)) {
                    stream.write(bytes);
                    saved = true;
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        return saved;
    }
    
    public String getFilePath() {
        return PATH;
    }
    
    public String getHttpPath() {
        return HTTP_PATH;
    }
}
