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

/**
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
@XmlRootElement(name = "users")
public class UserBO extends AbstractBO implements Serializable,
        Comparable<UserBO> {

    public static final String maleAvatar = "avatar5";
    public static final String maleGender = "Masculino";
    public static final String femaleAvatar = "avatar2";
    public static final String femaleGender = "Femenino";

    private String documentNumber;

    private String documentType;

    private String lastName;

    private Date born;

    private String address;

    private long phone1;

    private Long phone2;

    private String password;

    private String gender;

    private String photo;

    private UserBO guardian1;

    private UserBO guardian2;

    private Set<SchoolBO> schoolSet;

    private Set<UserTypeBO> userTypeSet;
    
    private ClassRoomBO lastClassRoom;

    public UserBO() {
        super();
    }

    /**
     * @return the documentNumber
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * @param documentNumber the documentNumber to set
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * @return the documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * @param documentType the documentType to set
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the born
     */
    public Date getBorn() {
        return born;
    }

    /**
     * @param born the born to set
     */
    public void setBorn(Date born) {
        this.born = born;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone1
     */
    public long getPhone1() {
        return phone1;
    }

    /**
     * @param phone1 the phone1 to set
     */
    public void setPhone1(long phone1) {
        this.phone1 = phone1;
    }

    /**
     * @return the phone2
     */
    public Long getPhone2() {
        return phone2;
    }

    /**
     * @param phone2 the phone2 to set
     */
    public void setPhone2(Long phone2) {
        this.phone2 = phone2;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public UserBO getGuardian1() {
        return guardian1;
    }

    public void setGuardian1(UserBO guardian1) {
        this.guardian1 = guardian1;
    }

    public UserBO getGuardian2() {
        return guardian2;
    }

    public void setGuardian2(UserBO guardian2) {
        this.guardian2 = guardian2;
    }

    public Set<SchoolBO> getSchoolSet() {
        return schoolSet;
    }

    public void setSchoolSet(Set<SchoolBO> schoolSet) {
        this.schoolSet = schoolSet;
    }

    public Set<UserTypeBO> getUserTypeSet() {
        return userTypeSet;
    }

    public void setUserTypeSet(Set<UserTypeBO> userTypeSet) {
        this.userTypeSet = userTypeSet;
    }
    
    public ClassRoomBO getLastClassRoom() {
        return lastClassRoom;
    }

    public void setLastClassRoom(ClassRoomBO lastClassRoom) {
        this.lastClassRoom = lastClassRoom;
    }

    public boolean canViewGradeMenu() {
        boolean canViewMenu = false;
        for (UserTypeBO userType : this.userTypeSet) {
            canViewMenu = userType.canViewGradeMenu();
            if (canViewMenu) {
                break;
            }
        }
        return canViewMenu;
    }

    public boolean canViewSubjectMenu() {
        boolean canViewMenu = false;
        for (UserTypeBO userType : this.userTypeSet) {
            canViewMenu = userType.canViewSubjectMenu();
            if (canViewMenu) {
                break;
            }
        }
        return canViewMenu;
    }

    public boolean canViewTeacherMenu() {
        boolean canViewMenu = false;
        for (UserTypeBO userType : this.userTypeSet) {
            canViewMenu = userType.canViewTeacherMenu();
            if (canViewMenu) {
                break;
            }
        }
        return canViewMenu;
    }

    public boolean canViewStudentMenu() {
        boolean canViewMenu = false;
        for (UserTypeBO userType : this.userTypeSet) {
            canViewMenu = userType.canViewStudentMenu();
            if (canViewMenu) {
                break;
            }
        }
        return canViewMenu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.documentNumber);
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
        final UserBO other = (UserBO) obj;
        if (!Objects.equals(this.documentNumber, other.documentNumber)) {
            return false;
        }
        return Objects.equals(this.documentType, other.documentType);
    }

    @Override
    public int compareTo(UserBO o) {
        return this.lastName.compareToIgnoreCase(o.getLastName());
    }
}
