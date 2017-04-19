package com.example.service;

import com.example.model.ParamVo;
import com.example.valid.ValidCommons;
import com.example.valid.ValidImportant;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Created by sjf on 17-3-23.
 */

@Service
public class ParamServiceImpl implements ParamService {


    @Override
    public String findParam1(@Validated(ValidCommons.class) ParamVo vo) {

        return vo.getP1();
    }

    @Override
    public String findParam2(@Validated(ValidImportant.class) ParamVo vo) {

        return vo.getP2();
    }
}
