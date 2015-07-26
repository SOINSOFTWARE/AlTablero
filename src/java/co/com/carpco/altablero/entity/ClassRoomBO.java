/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Rodriguez
 */
@XmlRootElement(name = "classrooms")
public class ClassRoomBO extends AbstractBO implements Comparable<ClassRoomBO>, Serializable {

    private GradeBO gradeBO;

    private YearBO yearBO;

    private UserBO userBO;

    private SchoolBO schoolBO;

    public ClassRoomBO() {
        super();
    }
    
    public ClassRoomBO(int id, String name, boolean enabled, Date creation, Date updated) {
        super(id, name, enabled, creation, updated);
    }
    
    public ClassRoomBO(int id, String name, boolean enabled, Date creation, Date updated,
            GradeBO gradeBO, YearBO yearBO, UserBO userBO, SchoolBO schoolBO) {
        super(id, name, enabled, creation, updated);
        this.gradeBO = gradeBO;
        this.yearBO = yearBO;
        this.userBO = userBO;
        this.schoolBO = schoolBO;
    }

    /**
     * @return the grade
     */
    public GradeBO getGradeBO() {
        return gradeBO;
    }

    /**
     * @param gradeBO the grade to set
     */
    public void setGradeBO(GradeBO gradeBO) {
        this.gradeBO = gradeBO;
    }

    /**
     * @return the year
     */
    public YearBO getYearBO() {
        return yearBO;
    }

    /**
     * @param yearBO the year to set
     */
    public void setYearBO(YearBO yearBO) {
        this.yearBO = yearBO;
    }

    /**
     * @return the user
     */
    public UserBO getUserBO() {
        return userBO;
    }

    /**
     * @param userBO the user to set
     */
    public void setUserBO(UserBO userBO) {
        this.userBO = userBO;
    }

    /**
     * @return the schoolBO
     */
    public SchoolBO getSchoolBO() {
        return schoolBO;
    }

    /**
     * @param schoolBO the schoolBO to set
     */
    public void setSchoolBO(SchoolBO schoolBO) {
        this.schoolBO = schoolBO;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.code);
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.creation);
        hash = 71 * hash + Objects.hashCode(this.updated);
        hash = 71 * hash + (this.enabled ? 1 : 0);
        hash = 71 * hash + Objects.hashCode(this.gradeBO);
        hash = 71 * hash + Objects.hashCode(this.yearBO);
        hash = 71 * hash + Objects.hashCode(this.userBO);
        hash = 71 * hash + Objects.hashCode(this.schoolBO);
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
        final ClassRoomBO other = (ClassRoomBO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.creation, other.creation)) {
            return false;
        }
        if (!Objects.equals(this.updated, other.updated)) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (!Objects.equals(this.gradeBO, other.gradeBO)) {
            return false;
        }
        if (!Objects.equals(this.yearBO, other.yearBO)) {
            return false;
        }
        if (!Objects.equals(this.userBO, other.userBO)) {
            return false;
        }
        if (!Objects.equals(this.schoolBO, other.schoolBO)) {
            return false;
        }
        return true;
    }
    
    

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ClassRoomBO [gradeBO=" + gradeBO + ", yearBO=" + yearBO
                + ", userBO=" + userBO + ", schoolBO=" + schoolBO + ", id="
                + id + ", code=" + code + ", name=" + name + ", creation="
                + creation + ", updated=" + updated + ", enabled=" + enabled
                + "]";
    }

    @Override
    public int compareTo(ClassRoomBO other) {
        Integer thisGradeCode = Integer.parseInt(this.gradeBO.getCode());
        Integer otherGradeCode = Integer.parseInt(other.getGradeBO().getCode());
        return thisGradeCode.compareTo(otherGradeCode) + this.code.compareTo(other.getCode());
    }
}
