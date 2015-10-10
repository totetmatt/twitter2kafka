package org.totetmatt.twitter2kafka.configuration.specialStream;

public class SampleStreamConfiguration {
    private boolean useSampleStream = false;
    private String lang = "";
    
    public boolean isUseSampleStream() {
        return useSampleStream;
    }
    public void setUseSampleStream(boolean useSampleStream) {
        this.useSampleStream = useSampleStream;
    }
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }
   
    
}
