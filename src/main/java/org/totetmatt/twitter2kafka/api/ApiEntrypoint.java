package org.totetmatt.twitter2kafka.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.totetmatt.twitter2kafka.Twitter2kafkaApplication;
import org.totetmatt.twitter2kafka.api.message.QueryUpdateMessage;

@Controller
@RequestMapping(value = "/api")
public class ApiEntrypoint {
    @Autowired
    Twitter2kafkaApplication application;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody QueryUpdateMessage index() {
        return new QueryUpdateMessage(application.getQueryStreamConfiguration());
    }
    @RequestMapping(value = "/", method = RequestMethod.POST,consumes="application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody QueryUpdateMessage index( @RequestBody QueryUpdateMessage newConfig) {
        application.setQueryStreamConfiguration(newConfig.generateQueryStreamConfiguration());
        return new QueryUpdateMessage(application.getQueryStreamConfiguration());
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
