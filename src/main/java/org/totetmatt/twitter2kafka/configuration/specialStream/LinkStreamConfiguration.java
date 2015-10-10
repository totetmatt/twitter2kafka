package org.totetmatt.twitter2kafka.configuration.specialStream;

public class LinkStreamConfiguration {
    private boolean useLinkStream = false;
    private int count= 0 ;
   
    public boolean isUseLinkStream() {
        return useLinkStream;
    }
    public void setUseLinkStream(boolean useLinkStream) {
        this.useLinkStream = useLinkStream;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    
    
}
