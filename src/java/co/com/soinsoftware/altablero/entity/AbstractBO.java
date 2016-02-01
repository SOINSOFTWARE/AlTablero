/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.entity;

import java.util.Date;
import java.util.Objects;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author carlosandres
 */
public class AbstractBO {
    
    @JsonProperty("id")
    protected Integer id;

    @JsonProperty("name")
    protected String name;

    @JsonProperty("creation")
    protected Date creation;

    @JsonProperty("updated")
    protected Date updated;

    @JsonProperty("enabled")
    protected boolean enabled;
    
    public AbstractBO() {
        super();
    }

    public AbstractBO(int id, String name, boolean enabled, Date creation, Date updated) {
        super();
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.creation = creation;
        this.updated = updated;
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the creation
     */
    public Date getCreation() {
        return creation;
    }

    /**
     * @param creation the creation to set
     */
    public void setCreation(Date creation) {
        this.creation = creation;
    }

    /**
     * @return the updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated the updated to set
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final AbstractBO other = (AbstractBO) obj;
        return Objects.equals(this.name, other.name);
    }
}
