/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.bo;

import co.com.carpco.altablero.hibernate.entities.BzSchool;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Carlos Rodriguez
 */
public class SchoolBO implements Serializable {

    private Integer id;
    
    private String code;
    
    private String name;
    
    private Date creation;
    
    private Date updated;
    
    private boolean enabled;
    
    public SchoolBO(BzSchool bzSchool) {
        super();
        this.id = bzSchool.getId();
        this.code = bzSchool.getCode();
        this.name = bzSchool.getName();
        this.creation = bzSchool.getCreation();
        this.updated = bzSchool.getUpdated();
        this.enabled = bzSchool.isEnabled();
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
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.code);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.creation);
        hash = 89 * hash + Objects.hashCode(this.updated);
        hash = 89 * hash + (this.enabled ? 1 : 0);
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
        final SchoolBO other = (SchoolBO) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }
}
