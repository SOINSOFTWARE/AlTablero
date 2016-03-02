/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.controller;

import co.com.soinsoftware.altablero.bll.UserBLL;
import co.com.soinsoftware.altablero.entity.SchoolBO;
import co.com.soinsoftware.altablero.entity.UserBO;
import co.com.soinsoftware.altablero.entity.UserTypeBO;
import co.com.soinsoftware.altablero.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
@Service
public class UserController {

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private UserBLL userBLL;

    public List<UserBO> findTeachersNotGroupDirector(final int idSchool,
            final UserBO currentDirector) throws IOException {
        Set<UserBO> teacherAvailableSet = this.userBLL.findTeacherNoDirectors(idSchool);
        if (currentDirector != null) {
            if (teacherAvailableSet == null) {
                teacherAvailableSet = new HashSet<>();
            }
            teacherAvailableSet.add(currentDirector);
        }
        return sortUserSet(teacherAvailableSet);
    }

    public List<UserBO> findUsersByUserType(final int idSchool, final String cdUserType)
            throws IOException {
        final Set<UserBO> userSet = userBLL.findUsersByUserType(idSchool, cdUserType);
        return sortUserSet(userSet);
    }

    public List<UserBO> findStudentsNotLinked(final int idSchool, final Integer idGrade,
            final Integer idClassRoom) throws IOException {
        final Set<UserBO> userSet = userBLL.findStudentsNotLinked(idSchool, idGrade, idClassRoom);
        return sortUserSet(userSet);
    }

    public UserBO findUserByDocument(final String documentNumber) throws IOException {
        return userBLL.findUserByDocument(documentNumber);
    }

    public UserBO findUserByIdentifier(final int idUser) throws IOException {
        return userBLL.findUserByIdentifier(idUser);
    }

    public List<UserBO> sortUserSet(final Set<UserBO> userSet) {
        final List<UserBO> userList = (userSet != null)
                ? new ArrayList<>(userSet) : new ArrayList<>();
        Collections.sort(userList);
        return userList;
    }

    public boolean isValidDocumentNumber(final Integer idUser,
            final String documentNumber, final int idSchool) throws IOException {
        return userBLL.isValidDocumentNumber(idUser, documentNumber, idSchool);
    }

    public UserBO save(final UserBO user) throws IOException {
        return this.userBLL.save(user);
    }

    public UserBO buildUserBO(final Integer userId, final String docType,
            final String docNumber, final String name, final String lastName,
            final String bornDate, final String address, String phone1, String phone2,
            final String gender, final MultipartFile file, final SchoolBO school,
            final Set<UserTypeBO> userTypeSet)
            throws ParseException {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        final int id = (userId == null) ? 0 : userId;
        phone1 = phone1.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
        phone2 = phone2.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
        final UserBO user = new UserBO();
        user.setId(id);
        user.setDocumentType(docType);
        user.setDocumentNumber(docNumber);
        user.setName(name);
        user.setLastName(lastName);
        user.setBorn(dateFormat.parse(bornDate));
        user.setAddress(address);
        user.setPhone1(Long.valueOf(phone1));
        if (!phone2.equals("")) {
            user.setPhone2(Long.valueOf(phone2));
        }
        user.setGender(gender);
        user.addSchoolToSet(school);
        user.setUserTypeSet(userTypeSet);
        user.setEnabled(true);
        final String photoName = this.getPhotoName(docNumber, school.getId(), File.separator);
        if (fileUtils.savePhotoInServer(file, photoName)) {
            final String path = fileUtils.getFilePath();
            user.setPhoto(path + photoName);
        }
        return user;
    }
    
    public String getHttpPath(final UserBO user, final int idSchool) {
        final String photoName = this.getPhotoName(user.getDocumentNumber(), idSchool, "/");
        return this.fileUtils.getHttpPath() + photoName;
    }

    private String getPhotoName(final String docNumber, final int idSchool, final String separator) {
        final StringBuilder builder = new StringBuilder();
        builder.append(idSchool);
        builder.append(separator);
        builder.append(docNumber);
        builder.append(".png");
        return builder.toString();
    }
}
