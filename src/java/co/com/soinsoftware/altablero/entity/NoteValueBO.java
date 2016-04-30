/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
public class NoteValueBO implements Serializable {

    private static final long serialVersionUID = -1294581087156310419L;

    private int idStudent;

    private int idNoteDefinition;

    private BigDecimal value;

    private Date creation;

    private Date updated;

    private boolean enabled;

    public NoteValueBO() {
        super();
    }
    
    public NoteValueBO(final int idNoteDefinition, final int idStudent, final BigDecimal value) {
        super();
        this.idNoteDefinition = idNoteDefinition;
        this.idStudent = idStudent;
        this.value = value;
        this.creation = new Date();
        this.updated = new Date();
        this.enabled = true;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdNoteDefinition() {
        return idNoteDefinition;
    }

    public void setIdNoteDefinition(int idNoteDefinition) {
        this.idNoteDefinition = idNoteDefinition;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.idStudent;
        hash = 19 * hash + this.idNoteDefinition;
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
        final NoteValueBO other = (NoteValueBO) obj;
        if (this.idStudent != other.idStudent) {
            return false;
        }
        if (this.idNoteDefinition != other.idNoteDefinition) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "NoteValueBO [idStudent=" + idStudent
                + ", idNoteDefinition=" + idNoteDefinition
                + ", value=" + value + ", creation=" + creation 
                + ", updated=" + updated + ", enabled=" + enabled + "]";
    }
}
