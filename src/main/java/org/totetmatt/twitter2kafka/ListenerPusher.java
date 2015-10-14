package org.totetmatt.twitter2kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.totetmatt.twitter2kafka.configuration.KafkaConfiguration;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterObjectFactory;

@Component
public class ListenerPusher implements StatusListener {

    @Autowired
    @Qualifier("Producer")
    Producer<String, String> producer;

    @Autowired
    KafkaConfiguration configuration;

    private String username;
    private String sessionid;
    
    
    
    public ListenerPusher(String username, String sessionid) {
        super();
        this.username = username;
        this.sessionid = sessionid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    @Override
    public void onException(Exception arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onScrubGeo(long arg0, long arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStallWarning(StallWarning arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatus(Status status) {
        
        KeyedMessage<String, String> data = new KeyedMessage<String, String>(
                configuration.getKafkaTopic(),
                TwitterObjectFactory.getRawJSON(status));
        producer.send(data);

    }

    @Override
    public void onTrackLimitationNotice(int arg0) {
        // TODO Auto-generated method stub

    }

}
