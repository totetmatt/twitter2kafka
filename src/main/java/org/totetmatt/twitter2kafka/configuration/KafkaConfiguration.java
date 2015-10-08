package org.totetmatt.twitter2kafka.configuration;

import java.util.List;
import java.util.StringJoiner;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component

@Configuration
@ConfigurationProperties( prefix = "kafka" )
public class KafkaConfiguration {
    private List<String> brokerList;
    private String serializerClass;
    private String  requestRequieredAcks;
    private String kafkaTopic;
    
    public String getBrokers(){
        StringJoiner sj = new StringJoiner(",");
        brokerList.stream().forEach(s->sj.add(s));
        return sj.toString();
    }
    public List<String> getBrokerList() {
        return brokerList;
    }
    public void setBrokerList(List<String> brokerList) {
        this.brokerList = brokerList;
    }
    public String getSerializerClass() {
        return serializerClass;
    }
    public void setSerializerClass(String serializerClass) {
        this.serializerClass = serializerClass;
    }
    public String isRequestRequieredAcks() {
        return requestRequieredAcks;
    }
    public void setRequestRequieredAcks(String requestRequieredAcks) {
        this.requestRequieredAcks = requestRequieredAcks;
    }
    public String getKafkaTopic() {
        return kafkaTopic;
    }
    public void setKafkaTopic(String kafkaTopic) {
        this.kafkaTopic = kafkaTopic;
    }

    

}
