/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
public class SubjectBO extends AbstractWithCodeBO implements Serializable {
    
    private static final long serialVersionUID = -3541220834853035946L;

    public SubjectBO() {
        super();
    }
    
    public SubjectBO(int id, String code, String name, boolean enabled, Date creation,
            Date updated) {
        super(id, code, name, enabled, creation, updated);
    }

    @Override
    public String toString() {
        return "SubjectBO [id=" + id + ", code=" + code + ", name=" + name
                + ", creation=" + creation + ", updated=" + updated
                + ", enabled=" + enabled + "]";
    }
}
