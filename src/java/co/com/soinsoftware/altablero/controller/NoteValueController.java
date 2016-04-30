/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.controller;

import co.com.soinsoftware.altablero.entity.NoteValueBO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 25/04/2016
 * @version 1.0
 */
@Service
public class NoteValueController {
    
    public List<NoteValueBO> buildNoteValueListFromString(String objectsStr) {
        final List<NoteValueBO> noteValueList = new ArrayList<>();
        if (objectsStr != null && !objectsStr.equals("{}")) {
            do {
                final int initIndex = objectsStr.indexOf("[");
                final int finalIndex = objectsStr.indexOf("]");
                final String objectStr = objectsStr.substring(initIndex + 1, finalIndex);
                final NoteValueBO noteValue = this.buildNoteValueFromString(objectStr);
                if (noteValue != null) {
                    noteValueList.add(noteValue);
                }
                objectsStr = objectsStr.substring(finalIndex + 1);
            } while (objectsStr.contains("["));
        }
        return noteValueList;
    }
    
    private NoteValueBO buildNoteValueFromString(final String objectStr) {
        NoteValueBO noteValue = null;
        final String[] properties = objectStr.split(";");
        int idNoteDefinition = 0;
        int idStudent = 0;
        BigDecimal value = null;
        for (int i = 0; i < properties.length; i++) {
            final String[] property = properties[i].split("=");
            switch (property[0]) {
                case "idNoteDefinition":
                    idNoteDefinition = Integer.valueOf(property[1]);
                    break;
                case "idStudent":
                    idStudent = Integer.valueOf(property[1]);
                    break;
                case "value":
                    value = BigDecimal.valueOf(Double.valueOf(property[1].replace("_", "")));
                    break;
            }
            if (idNoteDefinition > 0 && idStudent > 0 && value != null) {
                noteValue = new NoteValueBO(idNoteDefinition, idStudent, value);
            }
        }
        return noteValue;
    }
}
