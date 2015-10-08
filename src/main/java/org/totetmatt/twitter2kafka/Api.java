package org.totetmatt.twitter2kafka;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
public class Api {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody String index() {
        return "Working";
    }
}
