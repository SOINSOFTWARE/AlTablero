/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Rodriguez
 */
@XmlRootElement(name = "userTypes")
public class UserTypeBO extends AbstractWithCodeBO implements Serializable{
    
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserTypeBO other = (UserTypeBO) obj;
        return Objects.equals(this.code, other.code);
    }
}
