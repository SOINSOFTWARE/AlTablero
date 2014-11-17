/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.bo;

import co.com.carpco.altablero.hibernate.entities.BzClassRoom;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Carlos Rodriguez
 */
public class ClassRoomBO implements Serializable {
    
    private Integer id;
    
    private GradeBO gradeBO;
    
    private YearBO yearBO;
    
    private UserBO userBO;
    
    private String code;
    
    private String name;
    
    private Date creation;
    
    private Date updated;
    
    private boolean enabled;
    
    public ClassRoomBO(BzClassRoom bzClassRoom) {
        super();
        this.id = bzClassRoom.getId();
        this.code = bzClassRoom.getCode();
        this.name = bzClassRoom.getName();
        this.creation = bzClassRoom.getCreation();
        this.updated = bzClassRoom.getUpdated();
        this.enabled = bzClassRoom.isEnabled();
        this.gradeBO = new GradeBO(bzClassRoom.getBzGrade());
        this.yearBO = new YearBO(bzClassRoom.getBzYear());
        this.userBO = new UserBO(bzClassRoom.getBzUser());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GradeBO getGradeBO() {
        return gradeBO;
    }

    public void setGradeBO(GradeBO gradeBO) {
        this.gradeBO = gradeBO;
    }

    public YearBO getYearBO() {
        return yearBO;
    }

    public void setYearBO(YearBO yearBO) {
        this.yearBO = yearBO;
    }

    public UserBO getUserBO() {
        return userBO;
    }

    public void setUserBO(UserBO userBO) {
        this.userBO = userBO;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.gradeBO);
        hash = 97 * hash + Objects.hashCode(this.yearBO);
        hash = 97 * hash + Objects.hashCode(this.userBO);
        hash = 97 * hash + Objects.hashCode(this.code);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.creation);
        hash = 97 * hash + Objects.hashCode(this.updated);
        hash = 97 * hash + (this.enabled ? 1 : 0);
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
        if (!Objects.equals(this.gradeBO, other.gradeBO)) {
            return false;
        }
        if (!Objects.equals(this.yearBO, other.yearBO)) {
            return false;
        }
        if (!Objects.equals(this.userBO, other.userBO)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
