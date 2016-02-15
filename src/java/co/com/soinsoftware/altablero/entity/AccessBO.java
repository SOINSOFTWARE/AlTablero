/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.entity;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
@XmlRootElement(name = "access")
public class AccessBO extends AbstractWithCodeBO implements Serializable {

    public AccessBO() {
        super();
    }

    public AccessBO(final int id, final String code, final String name,
            final boolean enabled, final Date creation, final Date updated) {
        super(id, code, name, enabled, creation, updated);
    }

    @Override
    public String toString() {
        return "AccessBO [id=" + id + ", code=" + code + ", name=" + name
                + ", creation=" + creation + ", updated=" + updated
                + ", enabled=" + enabled + "]";
    }
}
