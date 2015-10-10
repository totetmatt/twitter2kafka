package org.totetmatt.twitter2kafka.utils;

public class Place {
    private double southWestLat;
    private double southWestLong;
    private double northEastLat;
    private double northEastLong;
    
    private String label;
   
    
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public double getSouthWestLat() {
        return southWestLat;
    }
    public void setSouthWestLat(double southWestLat) {
        this.southWestLat = southWestLat;
    }
    public double getSouthWestLong() {
        return southWestLong;
    }
    public void setSouthWestLong(double southWestLong) {
        this.southWestLong = southWestLong;
    }
    public double getNorthEastLat() {
        return northEastLat;
    }
    public void setNorthEastLat(double northEastLat) {
        this.northEastLat = northEastLat;
    }
    public double getNorthEastLong() {
        return northEastLong;
    }
    public void setNorthEastLong(double northEastLong) {
        this.northEastLong = northEastLong;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "[ swLat:" + this.southWestLat + ", swLong" + this.southWestLong + ";" +
                " neLat:" + this.northEastLat + ", neLong" + this.northEastLong +"]";
    }
    
    
    
    
}
