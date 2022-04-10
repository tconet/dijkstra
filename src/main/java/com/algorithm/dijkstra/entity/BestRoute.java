package com.algorithm.dijkstra.entity;

import lombok.Data;

@Data
public class BestRoute {

    private String from;
    private Integer distance = Integer.MAX_VALUE;
    private boolean isOpen = true;

    public void reset() {
        distance = Integer.MAX_VALUE;
        isOpen = true;
        from = null;
    }

    public void update(City from, Integer distance) {
        this.from = from.getName();
        this.distance = distance;
    }

    public void validate(City city, Integer distance) {
        if ( this.distance >= distance ) {
            update(city, distance);
        }
    }
}
