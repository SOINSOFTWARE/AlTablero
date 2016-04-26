/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.controller;

import co.com.soinsoftware.altablero.entity.NoteDefinitionBO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 25/04/2016
 * @version 1.0
 */
@Service
public class NoteDefinitionController {
    
    public List<NoteDefinitionBO> buildNoteDefinitionListFromString(final int idClass,
            final int idPeriod, String objectsStr) {
        final List<NoteDefinitionBO> noteDefList = new ArrayList<>();
        if (objectsStr != null && !objectsStr.equals("{}")) {
            do {
                final int initIndex = objectsStr.indexOf("[");
                final int finalIndex = objectsStr.indexOf("]");
                final String objectStr = objectsStr.substring(initIndex + 1, finalIndex);
                final NoteDefinitionBO noteDefinition = this.buildNoteDefinitionFromString(
                        idClass, idPeriod, objectStr);
                if (noteDefinition != null) {
                    noteDefList.add(noteDefinition);
                }
                objectsStr = objectsStr.substring(finalIndex + 1);
            } while (objectsStr.contains("["));
        }
        return noteDefList;
    }
    
    private NoteDefinitionBO buildNoteDefinitionFromString(final int idClass,
            final int idPeriod, final String objectStr) {
        NoteDefinitionBO noteDefinition = null;
        final String[] properties = objectStr.split(";");
        int idNoteDefinition = 0;
        String name = null;
        String description = null;
        int value = 0;
        boolean enabled = false;
        for (int i = 0; i < properties.length; i++) {
            final String[] property = properties[i].split("=");
            switch (property[0]) {
                case "idNoteDefinition":
                    idNoteDefinition = Integer.valueOf(property[1]);
                    break;
                case "name":
                    name = property[1];
                    break;
                case "description":
                    description = property[1];
                    break;
                case "value":
                    value = Integer.valueOf(property[1].replace("_", ""));
                    break;
                case "enabled":
                    enabled = Boolean.valueOf(property[1]);
                    break;
            }
            if (name != null && description != null && value > 0) {
                noteDefinition = new NoteDefinitionBO(idNoteDefinition, name,
                        description, value, enabled, idClass, idPeriod);
            }
        }
        return noteDefinition;
    }
}
