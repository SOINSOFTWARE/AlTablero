/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.entity;

import java.io.Serializable;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
@XmlRootElement(name = "userTypes")
public class UserTypeBO extends AbstractWithCodeBO implements Serializable {

    private static final String PRINCIPAL = "RCTOR";
    private static final String COORDINATOR = "COORD";
    private static final String TEACHER = "PROFE";
    private static final String STUDENT = "ESTUD";
    private static final String GUARDIAN = "GUARD";

    private Set<AccessBO> accessSet;

    public UserTypeBO() {
        super();
    }

    public static final String getPrincipalCode() {
        return PRINCIPAL;
    }

    public static final String getCoordinatorCode() {
        return COORDINATOR;
    }

    public static final String getTeacherCode() {
        return TEACHER;
    }

    public static final String getStudentCode() {
        return STUDENT;
    }

    public static final String getGuardianCode() {
        return GUARDIAN;
    }

    public Set<AccessBO> getAccessSet() {
        return accessSet;
    }

    public void setAccessSet(Set<AccessBO> accessSet) {
        this.accessSet = accessSet;
    }

    public boolean canViewGradeMenu() {
        return this.code.equals(PRINCIPAL);
    }

    public boolean canViewSubjectMenu() {
        return this.code.equals(PRINCIPAL) || this.code.equals(COORDINATOR);
    }

    public boolean canViewTeacherMenu() {
        return this.code.equals(PRINCIPAL) || this.code.equals(COORDINATOR)
                || this.code.equals(TEACHER);
    }

    public boolean canViewStudentMenu() {
        return this.code.equals(PRINCIPAL) || this.code.equals(COORDINATOR)
                || this.code.equals(TEACHER) || this.code.equals(STUDENT)
                || this.code.equals(GUARDIAN);
    }
    
    public boolean isCoordinator() {
        return this.code.equals(COORDINATOR);
    }
    
    public boolean isGuardian() {
        return this.code.equals(GUARDIAN);
    }
    
    public boolean isStudent() {
        return this.code.equals(STUDENT);
    }
    
    public boolean isTeacher() {
        return this.code.equals(TEACHER);
    }

    @Override
    public String toString() {
        return "UserTypeBO [accessSet=" + accessSet + ", id=" + id + ", code="
                + code + ", name=" + name + ", creation=" + creation
                + ", updated=" + updated + ", enabled=" + enabled + "]";
    }
}
