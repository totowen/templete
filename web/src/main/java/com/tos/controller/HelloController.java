package com.tos.controller;

import com.tos.web.MyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by qq136 on 2017/9/24.
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello",method = {RequestMethod.GET,RequestMethod.POST})
    public String index(String str) throws MyException {
        System.out.println(str);
        throw new MyException("我错的了");
    }

    @RequestMapping(value = "/site",method = RequestMethod.GET)
    public String site(@RequestParam(required = true) List<String> stringList){

        if(null!=stringList&&stringList.size()>0){
            for (String str:
                    stringList) {
                System.out.println(str);
            }
        }

        return "site";
    }

    @RequestMapping(value = "/getResult",method = {RequestMethod.GET,RequestMethod.POST})
    public void getResult(String string){
        System.out.println(string);
    }


}
