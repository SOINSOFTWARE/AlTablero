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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 */
@Service
public class GradeController {

    @Autowired
    private GradeBLL gradeBLL;

    public List<GradeBO> findAll() throws IOException {
        List<GradeBO> gradeBOList = new ArrayList<>(gradeBLL.findAll());
        Collections.sort(gradeBOList);
        return gradeBOList;
    }
}
