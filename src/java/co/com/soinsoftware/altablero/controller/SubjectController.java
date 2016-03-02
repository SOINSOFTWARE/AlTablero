/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.controller;

import co.com.soinsoftware.altablero.bll.SubjectBLL;
import co.com.soinsoftware.altablero.entity.SubjectBO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
@Service
public class SubjectController {

    @Autowired
    private SubjectBLL subjectBLL;

    public List<SubjectBO> findExcludingClass(final int idClassRoom)
            throws IOException {
        final Set<SubjectBO> subjectSet = this.subjectBLL.findExcludingClass(idClassRoom);
        final List<SubjectBO> subjectList = (subjectSet != null)
                ? new ArrayList<>(subjectSet) : new ArrayList<>();
        Collections.sort(subjectList);
        return subjectList;
    }
}
