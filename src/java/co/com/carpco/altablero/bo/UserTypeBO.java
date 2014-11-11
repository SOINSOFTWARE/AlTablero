/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.bo;

import co.com.carpco.altablero.hibernate.entities.CnUserType;
import co.com.carpco.altablero.hibernate.entities.CnUsertTypeXaccess;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Carlos Rodriguez
 */
public class UserTypeBO implements Serializable{

    private Integer id;
    
    private String code;
    
    private String name;
    
    private Date creation;
    
    private Date updated;
    
    private boolean enabled;
    
    private Set<AccessBO> accessSet;

    public UserTypeBO(CnUserType cnUserType) {
        super();
        this.id = cnUserType.getId();
        this.code = cnUserType.getCode();
        this.name = cnUserType.getName();
        this.creation = cnUserType.getCreation();
        this.updated = cnUserType.getUpdated();
        this.enabled = cnUserType.isEnabled();
        this.accessSet = new HashSet<>();
        
        Set<CnUsertTypeXaccess> cnUserTypeXAccessSet = cnUserType.getCnUsertTypeXaccesses();
        cnUserTypeXAccessSet.stream().forEach((cnUserTypeXAccess) -> {
            accessSet.add(new AccessBO(cnUserTypeXAccess.getCnAccess()));
        });
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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
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

    /**
     * @return the accessSet
     */
    public Set<AccessBO> getAccessSet() {
        return accessSet;
    }

    /**
     * @param accessSet the accessSet to set
     */
    public void setAccessSet(Set<AccessBO> accessSet) {
        this.accessSet = accessSet;
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
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }
}
