/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.controller;

import co.com.soinsoftware.altablero.bll.ClassBLL;
import co.com.soinsoftware.altablero.entity.ClassBO;
import co.com.soinsoftware.altablero.entity.NoteDefinitionBO;
import co.com.soinsoftware.altablero.entity.NoteValueBO;
import co.com.soinsoftware.altablero.entity.SubjectBO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 05/02/2016
 * @version 1.0
 */
@Service
public class ClassController {

    @Autowired
    private SubjectController subjectController;

    @Autowired
    private ClassBLL classBLL;

    public List<ClassBO> findClasses(final int idSchool, final int idClassRoom,
            final int idTeacher, final boolean addDefaultData) throws IOException {
        Set<ClassBO> classSet = classBLL.findClasses(idSchool, idClassRoom, idTeacher);
        if (classSet == null) {
            classSet = new HashSet<>();
        }
        final List<SubjectBO> subjectList = (addDefaultData) ? subjectController
                .findExcludingClass(idClassRoom) : new ArrayList<>();        
        for (final SubjectBO subject : subjectList) {
            final ClassBO classBO = new ClassBO(0, subject.getName(), false,
                    new Date(), new Date());
            classBO.setSubject(subject);
            classSet.add(classBO);
        }
        final List<ClassBO> classList = new ArrayList<>(classSet);
        Collections.sort(classList);
        return classList;
    }

    public Set<ClassBO> saveClasses(final List<ClassBO> classList)
            throws IOException {
        return classBLL.saveClasses(classList);
    }
    
    public Set<NoteDefinitionBO> findNoteDefinitionByClass(final int idClass,
            final int idPeriod) throws IOException {
        return this.classBLL.findNoteDefinitionByClass(idClass, idPeriod);
    }
    
    public ClassBO saveNoteDefinitionByClass(final List<NoteDefinitionBO> noteDefList)
            throws IOException {
        return this.classBLL.saveNoteDefinitionByClass(noteDefList);
    }
    
    public ClassBO saveNoteValue(final List<NoteValueBO> noteValueList)
            throws IOException {
        return this.classBLL.saveNoteValue(noteValueList);
    }

    public List<ClassBO> buildClassBOListFromString(final int idClassRoom,
            String objectsStr) {
        final List<ClassBO> classList = new ArrayList<>();
        if (objectsStr != null && !objectsStr.equals("{}")) {
            do {
                final int initIndex = objectsStr.indexOf("[");
                final int finalIndex = objectsStr.indexOf("]");
                final String objectStr = objectsStr.substring(initIndex + 1, finalIndex);
                final ClassBO classBO = buildClassBOFromString(idClassRoom, objectStr);
                if (classBO != null) {
                    classList.add(classBO);
                }
                objectsStr = objectsStr.substring(finalIndex + 1);
            } while (objectsStr.contains("["));
        }
        return classList;
    }

    private ClassBO buildClassBOFromString(final int idClassRoom, final String objectStr) {
        ClassBO classBO = null;
        final String[] properties = objectStr.split(";");
        int idClass = 0;
        int idSubject = 0;
        int idTeacher = 0;
        String name = null;
        boolean enabled = false;
        for (int i = 0; i < properties.length; i++) {
            final String[] property = properties[i].split("=");
            switch (property[0]) {
                case "idClass":
                    idClass = Integer.valueOf(property[1]);
                    break;
                case "idSubject":
                    idSubject = Integer.valueOf(property[1]);
                    break;
                case "idTeacher":
                    idTeacher = Integer.valueOf(property[1]);
                    break;
                case "name":
                    name = property[1];
                    break;
                case "enabled":
                    enabled = Boolean.valueOf(property[1]);
                    break;
            }
        }
        if (idSubject > 0 && idTeacher > 0 && name != null) {
            classBO = new ClassBO(idClass, name, enabled, new Date(), new Date());
            classBO.setIdClassRoom(idClassRoom);
            classBO.setIdSubject(idSubject);
            classBO.setIdTeacher(idTeacher);
        }
        return classBO;
    }
}
