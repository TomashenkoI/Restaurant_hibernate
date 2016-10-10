package ua.goit.java.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Map;

/**
 * Created by 7 on 21.09.2016.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model){

        model.put("currentTime", new Date().toString());

        return "index";
    }

}
