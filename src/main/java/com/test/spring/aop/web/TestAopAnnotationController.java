package com.test.spring.aop.web;

import com.test.spring.aop.domain.entiry.Person;
import com.test.spring.aop.domain.exception.InvalidParamException;
import com.test.spring.aop.domain.service.IPersonDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Learning and test spring aop by using customized annotation.
 * Created by pact on 2017/3/9.
 */
@RestController()
@RequestMapping("/ann/")
public class TestAopAnnotationController {

    @Autowired
    private IPersonDetailService personDetailService;


    @RequestMapping(value = "method", method = RequestMethod.GET)
    public Person onMethod( String name){

        return personDetailService.getDetailByName(name);
    }

    @RequestMapping(value = "class", method = RequestMethod.GET)
    public Person onClass(String id){
        return personDetailService.getPersonById(id);
    }
}
