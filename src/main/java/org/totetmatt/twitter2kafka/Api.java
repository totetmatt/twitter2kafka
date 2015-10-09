package org.totetmatt.twitter2kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.totetmatt.twitter2kafka.configuration.QueryStreamConfiguration.JsonQueryStreamConfiguration;

@Controller
@RequestMapping(value = "/api")
public class Api {
    @Autowired
    Twitter2kafkaApplication application;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody JsonQueryStreamConfiguration index() {
        return application.getQueryStreamConfiguration().getJsonQueryStreamConfiguration();
    }
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public @ResponseBody String startStream() {
        application.start();
        return "Run";
    }
    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public @ResponseBody String stopStream() {
        application.stop();
        return "Stopped";
    }
}
