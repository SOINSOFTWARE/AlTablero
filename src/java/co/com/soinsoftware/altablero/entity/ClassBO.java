/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.entity;

import java.util.Date;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
@XmlRootElement(name = "classes")
public class ClassBO extends AbstractBO implements Comparable<ClassBO> {

    private static final long serialVersionUID = -39758306494005636L;

    private int idClassRoom;

    private int idSubject;

    private int idTeacher;

    @JsonProperty("classRoom")
    private ClassRoomBO classRoom;

    @JsonProperty("subject")
    private SubjectBO subject;

    @JsonProperty("teacher")
    private UserBO teacher;

    private Set<NoteDefinitionBO> noteDefinitionSet;

    public ClassBO() {
        super();
    }

    public ClassBO(final int id, final String name, final boolean enabled,
            final Date creation, final Date updated) {
        super(id, name, enabled, creation, updated);
    }

    public ClassRoomBO getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoomBO classRoom) {
        this.classRoom = classRoom;
    }

    public SubjectBO getSubject() {
        return subject;
    }

    public void setSubject(SubjectBO subject) {
        this.subject = subject;
    }

    public UserBO getTeacher() {
        return teacher;
    }

    public void setTeacher(UserBO teacher) {
        this.teacher = teacher;
    }

    public Set<NoteDefinitionBO> getNoteDefinitionSet() {
        return noteDefinitionSet;
    }

    public void setNoteDefinitionSet(Set<NoteDefinitionBO> noteDefinitionSet) {
        this.noteDefinitionSet = noteDefinitionSet;
    }

    public int getIdClassRoom() {
        return idClassRoom;
    }

    public void setIdClassRoom(int idClassRoom) {
        this.idClassRoom = idClassRoom;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    @Override
    public String toString() {
        return "ClassBO [subject=" + subject + ", teacher=" + teacher
                + ", noteDefinitionSet=" + noteDefinitionSet + ", id=" + id
                + ", name=" + name + ", creation=" + creation + ", updated="
                + updated + ", enabled=" + enabled + "]";
    }

    @Override
    public int compareTo(ClassBO other) {
        return this.name.compareToIgnoreCase(other.getName());
    }
}
