/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.bo;

import co.com.carpco.altablero.hibernate.entities.BzGrade;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Carlos Rodriguez
 */
public class GradeBO implements Serializable {
    
    private Integer id;
    
    private String code;
    
    private String name;
    
    private Date creation;
    
    private Date updated;
    
    private boolean enabled;
    
    public GradeBO(BzGrade bzGrade) {
        super();
        this.id = bzGrade.getId();
        this.code = bzGrade.getCode();
        this.name = bzGrade.getName();
        this.creation = bzGrade.getCreation();
        this.updated = bzGrade.getUpdated();
        this.enabled = bzGrade.isEnabled();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.code);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.creation);
        hash = 41 * hash + Objects.hashCode(this.updated);
        hash = 41 * hash + (this.enabled ? 1 : 0);
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
        final GradeBO other = (GradeBO) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }
     
}
