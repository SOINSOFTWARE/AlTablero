/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.entity;

import java.util.Date;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 15/04/2016
 */
@XmlRootElement(name = "noteConf")
public class NoteConfigurationBO extends AbstractWithCodeBO implements
        Comparable<NoteConfigurationBO> {

    private static final long serialVersionUID = 5622827774843357933L;

    private Set<NoteValueConfigurationBO> noteValueSet;

    public NoteConfigurationBO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public NoteConfigurationBO(final int id, final String code, final String name,
            final boolean enabled, final Date creation, final Date updated,
            final Set<NoteValueConfigurationBO> noteValueSet) {
        super(id, code, name, enabled, creation, updated);
        this.noteValueSet = noteValueSet;
    }

    public Set<NoteValueConfigurationBO> getNoteValueSet() {
        return noteValueSet;
    }

    public void setNoteValueSet(Set<NoteValueConfigurationBO> noteValueSet) {
        this.noteValueSet = noteValueSet;
    }

    @Override
    public String toString() {
        return "NoteConfigurationBO [noteValueSet=" + noteValueSet + ", code="
                + code + ", id=" + id + ", name=" + name + ", creation="
                + creation + ", updated=" + updated + ", enabled=" + enabled
                + "]";
    }

    @Override
    public int compareTo(final NoteConfigurationBO other) {
        return this.name.compareTo(other.name);
    }
}
