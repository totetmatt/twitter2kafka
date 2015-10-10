package org.totetmatt.twitter2kafka.configuration.specialStream;

public class FirehoseConfiguration {
    private boolean useFirehose = false ;
    private int count = 0;
    public boolean isUseFirehose() {
        return useFirehose;
    }
    public void setUseFirehose(boolean useFirehose) {
        this.useFirehose = useFirehose;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    
    
}
