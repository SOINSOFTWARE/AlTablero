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

    private UserBO student;

    private int idNoteDefinition;

    private NoteDefinitionBO noteDefinition;

    private BigDecimal value;

    private Date creation;

    private Date updated;

    private boolean enabled;

    public NoteValueBO() {
        super();
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public UserBO getStudent() {
        return student;
    }

    public void setStudent(UserBO student) {
        this.student = student;
    }

    public int getIdNoteDefinition() {
        return idNoteDefinition;
    }

    public void setIdNoteDefinition(int idNoteDefinition) {
        this.idNoteDefinition = idNoteDefinition;
    }

    public NoteDefinitionBO getNoteDefinition() {
        return noteDefinition;
    }

    public void setNoteDefinition(NoteDefinitionBO noteDefinition) {
        this.noteDefinition = noteDefinition;
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
    public String toString() {
        return "NoteValueBO [idStudent=" + idStudent + ", student=" + student
                + ", idNoteDefinition=" + idNoteDefinition
                + ", noteDefinition=" + noteDefinition + ", value=" + value
                + ", creation=" + creation + ", updated=" + updated
                + ", enabled=" + enabled + "]";
    }
}
