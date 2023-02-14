package com.adventuretube.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    private String type;
    private double[] coordinates;

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("coordinates")
    public double[] getCoordinates() { return coordinates; }
    @JsonProperty("coordinates")
    public void setCoordinates(double[] value) { this.coordinates = value; }
}
