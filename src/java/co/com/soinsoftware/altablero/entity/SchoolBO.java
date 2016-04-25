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
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
@XmlRootElement(name = "schools")
public class SchoolBO extends AbstractWithCodeBO implements Serializable {

    private String photo;
    
    private Integer period;

    private NoteConfigurationBO note;

    public SchoolBO() {
        super();
    }

    public SchoolBO(final int id, final String code, final String name,
            final boolean enabled, final Date creation, final Date updated) {
        super(id, code, name, enabled, creation, updated);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public NoteConfigurationBO getNote() {
        return note;
    }

    public void setNote(NoteConfigurationBO note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "SchoolBO [id=" + id + ", code=" + code + ", name=" + name
                + ", creation=" + creation + ", updated=" + updated
                + ", enabled=" + enabled + "photo=" + photo + ", note=" + note + "]";
    }
}
