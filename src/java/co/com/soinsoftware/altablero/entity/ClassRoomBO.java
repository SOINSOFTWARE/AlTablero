/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
@XmlRootElement(name = "classrooms")
public class ClassRoomBO extends AbstractWithCodeBO implements Comparable<ClassRoomBO>, Serializable {

    @JsonProperty("grade")
    private GradeBO gradeBO;

    @JsonProperty("school")
    private SchoolBO schoolBO;

    @JsonProperty("time")
    private TimeBO timeBO;

    @JsonProperty("teacher")
    private UserBO userBO;

    @JsonProperty("year")
    private YearBO yearBO;

    private int idGrade;

    private int idSchool;

    private int idTime;

    private int idUser;

    private int idYear;

    public ClassRoomBO() {
        super();
    }

    public ClassRoomBO(final int id, final String code, final String name,
            final boolean enabled, final Date creation, final Date updated) {
        super(id, code, name, enabled, creation, updated);
    }

    public ClassRoomBO(final int id, final String code, final String name,
            final int idSchool, final int idYear, final int idGrade, final int idUser,
            final int idTime, final Date creation, final Date updated,
            final boolean enabled) {
        super(id, code, name, enabled, creation, updated);
        this.idGrade = idGrade;
        this.idSchool = idSchool;
        this.idTime = idTime;
        this.idUser = idUser;
        this.idYear = idYear;
    }

    public int getIdGrade() {
        return this.idGrade;
    }

    public int getIdSchool() {
        return this.idSchool;
    }

    public int getIdTime() {
        return this.idTime;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public int getIdYear() {
        return this.idYear;
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

    public TimeBO getTimeBO() {
        return timeBO;
    }

    public void setTimeBO(TimeBO timeBO) {
        this.timeBO = timeBO;
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
        hash = 71 * hash + Objects.hashCode(this.timeBO);
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
        return Objects.equals(this.timeBO, other.timeBO);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ClassRoomBO [gradeBO=" + gradeBO + ", yearBO=" + yearBO + ", timeBO=" + timeBO
                + ", userBO=" + userBO + ", schoolBO=" + schoolBO + ", id="
                + id + ", code=" + code + ", name=" + name + ", creation="
                + creation + ", updated=" + updated + ", enabled=" + enabled
                + "]";
    }

    @Override
    public int compareTo(ClassRoomBO other) {
        final GradeBO itsGrade = this.gradeBO;
        final GradeBO otherGrade = other.getGradeBO();
        return (itsGrade.compareTo(otherGrade) * this.name.compareTo(other.getName())) * -1;
    }
}