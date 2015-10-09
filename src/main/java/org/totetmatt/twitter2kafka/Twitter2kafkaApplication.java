package org.totetmatt.twitter2kafka;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.totetmatt.twitter2kafka.configuration.KafkaConfiguration;
import org.totetmatt.twitter2kafka.configuration.QueryStreamConfiguration;
import org.totetmatt.twitter2kafka.configuration.TwitterConfiguration;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

@SpringBootApplication
public class Twitter2kafkaApplication {
    // private static final Logger logger =
    // LoggerFactory.getLogger(Twitter2kafkaApplication.class);
    @Autowired
    TwitterStream twitterStream;
    @Autowired
    ListenerPusher listener;
    @Autowired
    QueryStreamConfiguration queryStreamConfiguration;

    private boolean running = false;

    @Bean(name = "Producer")
    @Autowired
    public Producer<String, String> kafkaProducer(
            KafkaConfiguration kafkaConfig) {
        Properties props = new Properties();
        props.put("metadata.broker.list", kafkaConfig.getBrokers());
        props.put("serializer.class", kafkaConfig.getSerializerClass());

        ProducerConfig config = new ProducerConfig(props);
        final Producer<String, String> producer = new Producer<String, String>(
                config);
        return producer;
    }

    @Bean
    @Autowired
    public TwitterStream twitterStream(TwitterConfiguration twitterConfig) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(twitterConfig.getOAuthConsumerKey());
        cb.setOAuthConsumerSecret(twitterConfig.getOAuthConsumerSecret());
        cb.setOAuthAccessToken(twitterConfig.getOAuthAccessToken());
        cb.setOAuthAccessTokenSecret(twitterConfig.getOAuthAccessTokenSecret());
        cb.setJSONStoreEnabled(true);
        cb.setDebugEnabled(false);
        cb.setIncludeEntitiesEnabled(true);
        return new TwitterStreamFactory(cb.build()).getInstance();
    }

    @PostConstruct
    public void run() {

        twitterStream.addListener(listener);
        // this.start();

    }

    public void start() {
        if (!running) {
            if (queryStreamConfiguration.isUseSampleStream()) {
                twitterStream.sample();
            } else {
                FilterQuery fq = new FilterQuery();
                if (!queryStreamConfiguration.getWords().isEmpty()) {
                    fq.track(queryStreamConfiguration.filterQueryWords());
                }
                if (!queryStreamConfiguration.getUsers().isEmpty()) {
                    fq.follow(queryStreamConfiguration.filterQueryUsers());
                }
                twitterStream.filter(fq);
                running = true;
            }
        }
    }

    public void stop() {
        twitterStream.shutdown();
        running = false;
    }
    
    

    public QueryStreamConfiguration getQueryStreamConfiguration() {
        return queryStreamConfiguration;
    }

    public void setQueryStreamConfiguration(
            QueryStreamConfiguration queryStreamConfiguration) {
        this.queryStreamConfiguration = queryStreamConfiguration;
    }

    public static void main(String[] args) {
        SpringApplication.run(Twitter2kafkaApplication.class, args);
    }
}
