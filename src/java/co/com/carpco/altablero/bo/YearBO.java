/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.bo;

import co.com.carpco.altablero.hibernate.entities.BzYear;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Carlos Rodriguez
 */
public class YearBO implements Serializable {
    
    private Integer id;
    
    private String name;
    
    private Date creation;
    
    private Date updated;
    
    private boolean enabled;

    public YearBO(BzYear bzYear) {
        super();
        this.id = bzYear.getId();
        this.name = bzYear.getName();
        this.creation = bzYear.getCreation();
        this.updated = bzYear.getUpdated();
        this.enabled = bzYear.isEnabled();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.creation);
        hash = 83 * hash + Objects.hashCode(this.updated);
        hash = 83 * hash + (this.enabled ? 1 : 0);
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
        final YearBO other = (YearBO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
}
