package org.totetmatt.twitter2kafka.configuration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.totetmatt.twitter2kafka.configuration.specialStream.FirehoseConfiguration;
import org.totetmatt.twitter2kafka.configuration.specialStream.LinkStreamConfiguration;
import org.totetmatt.twitter2kafka.configuration.specialStream.SampleStreamConfiguration;
import org.totetmatt.twitter2kafka.configuration.specialStream.SiteStreamConfiguration;
import org.totetmatt.twitter2kafka.utils.Place;
import org.totetmatt.twitter2kafka.utils.User;
import org.totetmatt.twitter2kafka.utils.UserIdResolver;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

@Component

@Configuration
@ConfigurationProperties(locations = { "file:./streamquery.yml" })
public class QueryStreamConfiguration {
    public class JsonQueryStreamConfiguration {
        public List<User> users = new ArrayList<User>();
        public List<Place> locations = new ArrayList<Place>();
        public List<String> words = new ArrayList<String>();

        public JsonQueryStreamConfiguration(QueryStreamConfiguration conf) {

            this.words      = conf.getWords();
    
        }

    }

    List<User> users        = new ArrayList<User>();
    List<Place> locations   = new ArrayList<Place>();
    List<String> words      = new ArrayList<String>();
  

    SampleStreamConfiguration sampleStream  = new SampleStreamConfiguration();
    FirehoseConfiguration firehose          = new FirehoseConfiguration();
    LinkStreamConfiguration linkstream      = new LinkStreamConfiguration();
    SiteStreamConfiguration sitestream      = new SiteStreamConfiguration();
    
    boolean autostart;
    
    
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

    public String[] filterQueryWords() {
        return words.stream().toArray(String[]::new);
    }

    public long[] filterQueryUsers() {
        long[] list = new long[users.size()];
        int i = 0;
        for (User u : users) {
            list[i] = u.getId();
            i++;
        }
        return list;
    }

    public List<Place> getLocations() {
        return locations;
    }

    public void setLocations(List<Place> locations) {
        this.locations = locations;
    }


    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public List<User> getUsers() {
        return users;
    }

    public ArrayList<String> getScreenNames() {
        ArrayList<String> screenNames = new ArrayList<String>();
        users.stream()
                .forEach(u -> screenNames.add(u.getScreenName().toLowerCase()));
        return screenNames;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void sloveUser() {
        this.users.stream().parallel().forEach((u) -> {
            if (u.getId() == null) {
                try {
                    u.setId(UserIdResolver.resolve(u.getScreenName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JsonQueryStreamConfiguration getJsonQueryStreamConfiguration() {
        return new JsonQueryStreamConfiguration(this);
    }

    public void save() {
        Representer representer = new Representer();

        representer.addClassTag(QueryStreamConfiguration.class, Tag.MAP);
        Yaml yaml = new Yaml(representer, new DumperOptions());
        try {
            yaml.dump(this, new FileWriter("./streamquery.yml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
