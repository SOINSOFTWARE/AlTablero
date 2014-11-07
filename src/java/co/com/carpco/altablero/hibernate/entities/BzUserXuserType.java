package co.com.carpco.altablero.hibernate.entities;
// Generated 30-oct-2014 21:20:19 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * BzUserXuserType generated by hbm2java
 */
public class BzUserXuserType  implements java.io.Serializable {


     private BzUserXuserTypeId id;
     private BzUser bzUser;
     private CnUserType cnUserType;
     private Date creation;
     private Date updated;
     private boolean enabled;

    public BzUserXuserType() {
    }

    public BzUserXuserType(BzUserXuserTypeId id, BzUser bzUser, CnUserType cnUserType, Date creation, Date updated, boolean enabled) {
       this.id = id;
       this.bzUser = bzUser;
       this.cnUserType = cnUserType;
       this.creation = creation;
       this.updated = updated;
       this.enabled = enabled;
    }
   
    public BzUserXuserTypeId getId() {
        return this.id;
    }
    
    public void setId(BzUserXuserTypeId id) {
        this.id = id;
    }
    public BzUser getBzUser() {
        return this.bzUser;
    }
    
    public void setBzUser(BzUser bzUser) {
        this.bzUser = bzUser;
    }
    public CnUserType getCnUserType() {
        return this.cnUserType;
    }
    
    public void setCnUserType(CnUserType cnUserType) {
        this.cnUserType = cnUserType;
    }
    public Date getCreation() {
        return this.creation;
    }
    
    public void setCreation(Date creation) {
        this.creation = creation;
    }
    public Date getUpdated() {
        return this.updated;
    }
    
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }




}


