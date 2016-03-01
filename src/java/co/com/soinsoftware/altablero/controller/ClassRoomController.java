/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.controller;

import co.com.soinsoftware.altablero.bll.ClassRoomBLL;
import co.com.soinsoftware.altablero.entity.ClassRoomBO;
import co.com.soinsoftware.altablero.entity.UserBO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
@Service
public class ClassRoomController {

    @Autowired
    private ClassRoomBLL classRoomBLL;

    @Autowired
    private YearController yearController;

    public ClassRoomBO buildClassRoom(final int idClassRoom, final String code,
            final String name, final int idSchool, final int idYear,
            final int idGrade, final int idUser, final int idTime) {
        return new ClassRoomBO(idClassRoom, code, name, idSchool, idYear, idGrade,
                idUser, idTime, new Date(), new Date(), true);
    }

    public ClassRoomBO saveClassRoom(final ClassRoomBO classRoom)
            throws IOException {
        final ClassRoomBO savedCR = classRoomBLL.saveClassRoom(classRoom);
        return (savedCR != null) ? savedCR : classRoom;
    }

    public ClassRoomBO findClassRoom(final int idSchool, final Integer idClassRoom)
            throws IOException {
        ClassRoomBO classRoomBO = null;
        final Set<ClassRoomBO> classRoomBOSet = classRoomBLL.findClassRooms(
                idSchool, null, null, null, idClassRoom);
        if (classRoomBOSet != null && !classRoomBOSet.isEmpty()) {
            classRoomBO = classRoomBOSet.iterator().next();
        }
        return classRoomBO;
    }

    public Set<ClassRoomBO> findClassRooms(final String year,
            final Integer grade, final int idSchool) throws IOException {
        final String defYear = (year == null || year.isEmpty())
                ? yearController.getCurrentYearString() : year;
        return this.findClassRooms(idSchool, defYear, grade);
    }

    public boolean isValidCode(final int idSchool, final int idClassRoom,
            final String code) throws IOException {
        return classRoomBLL.isValidCode(idSchool, idClassRoom, code);
    }
    
    public Set<ClassRoomBO> saveClassRoomXStudent(final List<ClassRoomBO> classRoomList)
            throws IOException {
        return classRoomBLL.saveClassRoomXStudent(classRoomList);
    }

    public List<ClassRoomBO> buildStudentsLinkedToClassRoomList(String objectsStr) {
        final List<ClassRoomBO> classRoomList = new ArrayList<>();
        if (objectsStr != null && !objectsStr.equals("{}")) {
            do {
                final int initIndex = objectsStr.indexOf("[");
                final int finalIndex = objectsStr.indexOf("]");
                final String objectStr = objectsStr.substring(initIndex + 1, finalIndex);
                final ClassRoomBO classRoom = buildStudentsLinkedToClassRoom(objectStr);
                if (classRoom != null) {
                    classRoomList.add(classRoom);
                }
                objectsStr = objectsStr.substring(finalIndex + 1);
            } while (objectsStr.contains("["));
        }
        return classRoomList;
    }
    
    private Set<ClassRoomBO> findClassRooms(final int idSchool, final String year,
            final Integer grade) throws IOException {
        return classRoomBLL.findClassRooms(idSchool, year, grade, null, null);
    }

    private ClassRoomBO buildStudentsLinkedToClassRoom(final String objectStr) {
        ClassRoomBO classRoom = null;
        final String[] properties = objectStr.split(";");
        int idClassRoom = 0;
        int idStudent = 0;
        for (int i = 0; i < properties.length; i++) {
            final String[] property = properties[i].split("=");
            switch (property[0]) {
                case "idClassRoom":
                    idClassRoom = Integer.valueOf(property[1]);
                    break;
                case "idStudent":
                    idStudent = Integer.valueOf(property[1]);
                    break;
            }
        }
        if (idClassRoom > 0 && idStudent > 0) {
            final UserBO student = new UserBO();
            student.setId(idStudent);
            final Set<UserBO> studentSet = new HashSet<>();
            studentSet.add(student);
            classRoom = new ClassRoomBO();
            classRoom.setId(idClassRoom);
            classRoom.setStudentSet(studentSet);
        }
        return classRoom;
    }
}
