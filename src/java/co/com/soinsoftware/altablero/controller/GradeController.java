/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.controller;

import co.com.soinsoftware.altablero.bll.GradeBLL;
import co.com.soinsoftware.altablero.entity.GradeBO;
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
public class GradeController {

    @Autowired
    private GradeBLL gradeBLL;

    public List<GradeBO> findAll() throws IOException {
        final Set<GradeBO> gradeSet = gradeBLL.findAll();
        final List<GradeBO> gradeList = (gradeSet != null )
                ? new ArrayList<>(gradeSet) : new ArrayList<>();
        Collections.sort(gradeList);
        return gradeList;
    }
}
