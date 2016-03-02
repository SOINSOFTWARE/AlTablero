/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
@XmlRootElement(name = "classrooms")
public class ClassRoomBO extends AbstractWithCodeBO implements Comparable<ClassRoomBO>, Serializable {

    private int idGrade;

    @JsonProperty("grade")
    private GradeBO gradeBO;

    private int idSchool;

    @JsonProperty("school")
    private SchoolBO schoolBO;

    private int idTime;

    @JsonProperty("time")
    private TimeBO timeBO;

    private int idUser;

    @JsonProperty("teacher")
    private UserBO userBO;

    private int idYear;

    @JsonProperty("year")
    private YearBO yearBO;

    private Set<ClassBO> classSet;

    private Set<UserBO> studentSet;

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

    public GradeBO getGradeBO() {
        return gradeBO;
    }

    public void setGradeBO(GradeBO gradeBO) {
        this.gradeBO = gradeBO;
    }

    public SchoolBO getSchoolBO() {
        return schoolBO;
    }

    public void setSchoolBO(SchoolBO schoolBO) {
        this.schoolBO = schoolBO;
    }

    public TimeBO getTimeBO() {
        return timeBO;
    }

    public void setTimeBO(TimeBO timeBO) {
        this.timeBO = timeBO;
    }

    public UserBO getUserBO() {
        return userBO;
    }

    public void setUserBO(UserBO userBO) {
        this.userBO = userBO;
    }

    public YearBO getYearBO() {
        return yearBO;
    }

    public void setYearBO(YearBO yearBO) {
        this.yearBO = yearBO;
    }

    public Set<ClassBO> getClassSet() {
        return classSet;
    }

    public void setClassSet(final Set<ClassBO> classSet) {
        this.classSet = classSet;
    }

    public Set<UserBO> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(final Set<UserBO> studentSet) {
        this.studentSet = studentSet;
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
        return (this.gradeBO.compareTo(other.getGradeBO()) * this.name.compareTo(other.getName())) * -1;
    }
}
