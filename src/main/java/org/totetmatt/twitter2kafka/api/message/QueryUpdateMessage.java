package org.totetmatt.twitter2kafka.api.message;

import java.util.ArrayList;
import java.util.List;

import org.totetmatt.twitter2kafka.configuration.QueryStreamConfiguration;
import org.totetmatt.twitter2kafka.configuration.specialStream.FirehoseConfiguration;
import org.totetmatt.twitter2kafka.configuration.specialStream.LinkStreamConfiguration;
import org.totetmatt.twitter2kafka.configuration.specialStream.SampleStreamConfiguration;
import org.totetmatt.twitter2kafka.configuration.specialStream.SiteStreamConfiguration;
import org.totetmatt.twitter2kafka.utils.Place;
import org.totetmatt.twitter2kafka.utils.User;

public class QueryUpdateMessage {

    private List<User> users        = new ArrayList<User>();
    private List<Place> locations   = new ArrayList<Place>();
    private List<String> words      = new ArrayList<String>();
  

    private SampleStreamConfiguration sampleStream  = new SampleStreamConfiguration();
    private FirehoseConfiguration firehose          = new FirehoseConfiguration();
    private LinkStreamConfiguration linkstream      = new LinkStreamConfiguration();
    private SiteStreamConfiguration sitestream      = new SiteStreamConfiguration();
    
    private boolean autostart;
    
    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Place> getLocations() {
        return locations;
    }

    public void setLocations(List<Place> locations) {
        this.locations = locations;
    }

    public SampleStreamConfiguration getSampleStream() {
        return sampleStream;
    }

    public void setSampleStream(SampleStreamConfiguration sampleStream) {
        this.sampleStream = sampleStream;
    }

    public FirehoseConfiguration getFirehose() {
        return firehose;
    }

    public void setFirehose(FirehoseConfiguration firehose) {
        this.firehose = firehose;
    }

    public LinkStreamConfiguration getLinkstream() {
        return linkstream;
    }

    public void setLinkstream(LinkStreamConfiguration linkstream) {
        this.linkstream = linkstream;
    }

    public SiteStreamConfiguration getSitestream() {
        return sitestream;
    }

    public void setSitestream(SiteStreamConfiguration sitestream) {
        this.sitestream = sitestream;
    }

    public boolean isAutostart() {
        return autostart;
    }

    public void setAutostart(boolean autostart) {
        this.autostart = autostart;
    }
    public static QueryUpdateMessage generateQueryStreamConfiguration(QueryUpdateMessage conf) {
        
        return null;
    }
    public QueryUpdateMessage(){}
    public QueryUpdateMessage(QueryStreamConfiguration conf){
        this.autostart = conf.isAutostart();
        
        this.firehose = conf.getFirehose();
        this.linkstream = conf.getLinkstream();
        this.sampleStream = conf.getSampleStream();
        this.sitestream = conf.getSitestream();
        
        this.locations = conf.getLocations();
        this.users = conf.getUsers();
        this.words = conf.getWords();
        
    }
    public QueryStreamConfiguration generateQueryStreamConfiguration(){
        QueryStreamConfiguration q = new QueryStreamConfiguration();
        q.setAutostart(autostart);
        q.setFirehose(firehose);
        q.setLinkstream(linkstream);
        q.setSampleStream(sampleStream);
        q.setSitestream(sitestream);
        
        q.setLocations(locations);
        q.setUsers(users);
        q.setWords(words);
        return q;
    }
    
    
}
