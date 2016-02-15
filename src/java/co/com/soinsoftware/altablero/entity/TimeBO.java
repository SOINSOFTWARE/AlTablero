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
public class TimeBO extends AbstractWithCodeBO implements Serializable,
        Comparable<TimeBO> {

    private static final long serialVersionUID = 5622827774843357933L;

    public TimeBO() {
        super();
    }

    public TimeBO(final int id, final String code, final String name,
            final boolean enabled, final Date creation, final Date updated) {
        super(id, code, name, enabled, creation, updated);
    }

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