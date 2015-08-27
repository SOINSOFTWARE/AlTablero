/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Carlos Rodriguez
 */
public class TimeBO extends AbstractBO implements Serializable,
        Comparable<TimeBO> {

    /**
     * Auto generated serial version
     */
    private static final long serialVersionUID = 5622827774843357933L;

    public TimeBO() {
        super();
    }

    public TimeBO(int id, String code, String name, boolean enabled, Date creation, Date updated) {
        super(id, code, name, enabled, creation, updated);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TimeBO [id=" + id + ", code=" + code + ", name=" + name
                + ", creation=" + creation + ", updated=" + updated
                + ", enabled=" + enabled + "]";
    }

    @Override
    public int compareTo(TimeBO other) {
        return this.name.compareTo(other.name);
    }

}