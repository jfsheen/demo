package com.example.model;

import com.example.valid.ValidCommons;
import com.example.valid.ValidImportant;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by sjf on 17-3-23.
 */
public class ParamVo {

    @NotBlank(groups = {ValidCommons.class})
    private String p1;

    @NotBlank(groups = {ValidImportant.class})
    private String p2;


    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {

        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }
}
