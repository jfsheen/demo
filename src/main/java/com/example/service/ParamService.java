package com.example.service;

import com.example.model.ParamVo;
import org.springframework.validation.BindingResult;

/**
 * Created by sjf on 17-3-23.
 */

public interface ParamService {

    String findParam1(ParamVo vo);
    String findParam2(ParamVo vo);
}
