package org.totetmatt.twitter2kafka.utils;

public class User{
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    private String screenName;
    private Long id;
    public String getScreenName() {
        return screenName;
    }
    public void setScreenName(String screename) {
        this.screenName = screename;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return this.id + "@"+this.screenName;
    }
    
    
}