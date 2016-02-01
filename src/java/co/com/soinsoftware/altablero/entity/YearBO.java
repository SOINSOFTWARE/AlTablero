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
 *
 * @author Carlos Rodriguez
 */
@XmlRootElement(name = "year")
public class YearBO extends AbstractBO implements Serializable, Comparable<YearBO> {

    /**
     * Auto generated serial version
     */
    private static final long serialVersionUID = -6670213337879299160L;

    public YearBO() {
        super();
    }

    public YearBO(int id, String name, boolean enabled, Date creation, Date updated) {
        super(id, name, enabled, creation, updated);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "YearBO [id=" + id + ", name=" + name + ", creation=" + creation
                + ", updated=" + updated + ", enabled=" + enabled + "]";
    }

    @Override
    public int compareTo(YearBO other) {
        Integer thisName = Integer.parseInt(this.name);
        Integer otherName = Integer.parseInt(other.getName());
        return thisName.compareTo(otherName) * -1;
    }

}
