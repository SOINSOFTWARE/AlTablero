/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.entity;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Rodriguez
 */
@XmlRootElement(name = "grades")
public class GradeBO extends AbstractBO implements Serializable, Comparable<GradeBO> {

    /**
     * Auto generated serial version
     */
    private static final long serialVersionUID = 5622827774843357933L;

    public GradeBO() {
        super();
    }
    
    public GradeBO(int id, String code, String name, boolean enabled, Date creation, Date updated) {
        super(id, code, name, enabled, creation, updated);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GradeBO [id=" + id + ", code=" + code + ", name=" + name
                + ", creation=" + creation + ", updated=" + updated
                + ", enabled=" + enabled + "]";
    }

    @Override
    public int compareTo(GradeBO other) {
        Integer thisCode = Integer.parseInt(this.code);
        Integer otherCode = Integer.parseInt(other.getCode());
        return thisCode.compareTo(otherCode);
    }

}
