package org.totetmatt.twitter2kafka;

import java.util.UUID;

import org.totetmatt.twitter2kafka.configuration.QueryStreamConfiguration;
import org.totetmatt.twitter2kafka.configuration.TwitterConfiguration;

import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class StreamSession {

    private String id;
    private String username;
    private TwitterConfiguration twitterConfiguration;
    private QueryStreamConfiguration queryStreamConfiguration;
    private TwitterStream twitterStream;
    private boolean running = false;

    public StreamSession(String username,
            TwitterConfiguration twitterConfiguration,
            QueryStreamConfiguration queryStreamConfiguration) {

        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.twitterConfiguration = twitterConfiguration;
        this.queryStreamConfiguration = queryStreamConfiguration;

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(twitterConfiguration.getOAuthConsumerKey());
        cb.setOAuthConsumerSecret(
                twitterConfiguration.getOAuthConsumerSecret());
        cb.setOAuthAccessToken(twitterConfiguration.getOAuthAccessToken());
        cb.setOAuthAccessTokenSecret(
                twitterConfiguration.getOAuthAccessTokenSecret());
        cb.setJSONStoreEnabled(true);
        cb.setDebugEnabled(false);
        cb.setIncludeEntitiesEnabled(true);
        twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        twitterStream.addListener(new ListenerPusher(this.username, this.id));

    }

    public TwitterConfiguration getTwitterConfiguration() {
        return twitterConfiguration;
    }

    public void setTwitterConfiguration(
            TwitterConfiguration twitterConfiguration) {
        this.twitterConfiguration = twitterConfiguration;
    }

    public QueryStreamConfiguration getQueryStreamConfiguration() {
        return queryStreamConfiguration;
    }

    public void setQueryStreamConfiguration(
            QueryStreamConfiguration queryStreamConfiguration) {
        this.queryStreamConfiguration = queryStreamConfiguration;
    }

    public String getId() {
        return id;
    }

    public void run() {

        this.start();

    }

    public void start() {

        if (!running) {
            if (queryStreamConfiguration.getFirehose().isUseFirehose()) {
                twitterStream.firehose(
                        queryStreamConfiguration.getFirehose().getCount());

            } else if (queryStreamConfiguration.getLinkstream()
                    .isUseLinkStream()) {
                twitterStream.links(
                        queryStreamConfiguration.getLinkstream().getCount());

            } else if (queryStreamConfiguration.getSitestream()
                    .isUseSiteStream()) {
                twitterStream.site(
                        queryStreamConfiguration.getSitestream()
                                .isWithFollowings(),
                        queryStreamConfiguration.filterQueryUsers());

            } else if (queryStreamConfiguration.getSampleStream()
                    .isUseSampleStream()) {
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
        twitterStream.cleanUp();
        running = false;
    }

}
