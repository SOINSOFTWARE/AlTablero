/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.entity;

import java.util.Date;
import java.util.Set;

/**
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
public class NoteDefinitionBO extends AbstractBO {

    private static final long serialVersionUID = -7810207741825223847L;

    private String description;

    private Integer value;

    private PeriodBO period;
    
    private int idClass;
    
    private int idPeriod;

    private Set<NoteValueBO> noteValueSet;

    public NoteDefinitionBO() {
        super();
    }
    
    public NoteDefinitionBO(final int id, final String name, final String description,
            final int value, final boolean enabled, final int idClass, final int idPeriod) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
        this.creation = new Date();
        this.updated = new Date();
        this.enabled = enabled;
        this.idClass = idClass;
        this.idPeriod = idPeriod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public PeriodBO getPeriod() {
        return period;
    }

    public void setPeriod(PeriodBO period) {
        this.period = period;
    }

    public Set<NoteValueBO> getNoteValueSet() {
        return noteValueSet;
    }

    public void setNoteValueSet(Set<NoteValueBO> noteValueSet) {
        this.noteValueSet = noteValueSet;
    }
    
    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public int getIdPeriod() {
        return idPeriod;
    }

    public void setIdPeriod(int idPeriod) {
        this.idPeriod = idPeriod;
    }

    @Override
    public String toString() {
        return "NoteDefinitionBO [description=" + description + ", value="
                + value + ", period=" + period + ", noteValueSet="
                + noteValueSet + ", id=" + id + ", name=" + name
                + ", creation=" + creation + ", updated=" + updated
                + ", enabled=" + enabled + "]";
    }
}
