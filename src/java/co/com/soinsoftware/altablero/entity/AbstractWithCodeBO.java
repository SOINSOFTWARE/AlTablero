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
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
public class AbstractWithCodeBO extends AbstractBO{
    
    @JsonProperty("code")
    protected String code;
    
    public AbstractWithCodeBO() {
        super();
    }
    
    public AbstractWithCodeBO(final int id, final String code, final String name,
            final boolean enabled, final Date creation, final Date updated) {
        super();
        this.id = id;
        this.code = code;
        this.name = name;
        this.enabled = enabled;
        this.creation = creation;
        this.updated = updated;
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

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 61 * hash + Objects.hashCode(this.code);
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
        if (!super.equals(obj)) {
            return false;
        }
        final AbstractWithCodeBO other = (AbstractWithCodeBO) obj;
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        return "AbstractWithCodeBO{" + "code=" + code + '}';
    }
}
