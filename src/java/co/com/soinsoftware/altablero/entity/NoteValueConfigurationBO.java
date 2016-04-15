/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 15/04/2016
 */
@XmlRootElement(name = "noteValueConf")
public class NoteValueConfigurationBO extends AbstractWithCodeBO implements
        Comparable<NoteValueConfigurationBO> {

    private static final long serialVersionUID = 5622827774843357933L;

    private BigDecimal rangeStart;

    private BigDecimal rangeEnd;

    public NoteValueConfigurationBO() {
        super();
    }

    public NoteValueConfigurationBO(final int id, final String code,
            final String name, final BigDecimal rangeStart,
            final BigDecimal rangeEnd, final boolean enabled,
            final Date creation, final Date updated) {
        super(id, code, name, enabled, creation, updated);
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
    }

    public BigDecimal getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(BigDecimal rangeStart) {
        this.rangeStart = rangeStart;
    }

    public BigDecimal getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(BigDecimal rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    @Override
    public String toString() {
        return "NoteValueConfigurationBO [rangeStart=" + rangeStart
                + ", rangeEnd=" + rangeEnd + ", code=" + code + ", id=" + id
                + ", name=" + name + ", creation=" + creation + ", updated="
                + updated + ", enabled=" + enabled + "]";
    }

    @Override
    public int compareTo(final NoteValueConfigurationBO other) {
        return this.name.compareTo(other.name);
    }
}
