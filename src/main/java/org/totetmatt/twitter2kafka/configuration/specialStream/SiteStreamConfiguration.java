package org.totetmatt.twitter2kafka.configuration.specialStream;

public class SiteStreamConfiguration {
    private boolean useSiteStream = false;
    private boolean withFollowings = true;
    public boolean isUseSiteStream() {
        return useSiteStream;
    }
    public void setUseSiteStream(boolean useSiteStream) {
        this.useSiteStream = useSiteStream;
    }
    public boolean isWithFollowings() {
        return withFollowings;
    }
    public void setWithFollowings(boolean withFollowings) {
        this.withFollowings = withFollowings;
    }
    
    
    

}
