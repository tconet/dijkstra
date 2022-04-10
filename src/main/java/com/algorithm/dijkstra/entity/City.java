package com.algorithm.dijkstra.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class City {

    private String name;
    private BestRoute bestRoute = new BestRoute();

    public City (String name) {
        this.name = name;
    }

    /**
     * <p>
     * Check if this is the short path, if true, update the best route.
     * If we came from the same path, just ignore it.
     * @param from {@link City} The city where we came from.
     * @param distance The distance traveled
     */
    public void isBestRoute(City from, Integer distance) {

        /* Came from same path? just ignore it */
        if ( from.getName().equals(this.name) )
            return;

        /* Is first visit? then update best route */
        if ( Objects.isNull(this.bestRoute.getFrom()) ) {
            bestRoute.update(from, distance);
            return;
        }
        /* Validate if is the best route, if true, update it */
        bestRoute.validate(from, distance);
    }

    public Integer getEstimate() {
        return this.bestRoute.getDistance();
    }

    public boolean isOpen() {
        return this.bestRoute.isOpen();
    }

    public void reset() {
        this.bestRoute.reset();
    }
    public void setFirst() {
        this.bestRoute.setFrom(this.name);
        this.bestRoute.setDistance(0);
    }

    public void close() {
        bestRoute.setOpen(false);
    }

    @Override
    public boolean equals(Object o) {
        return this.name.equals( ((City)o).name );
    }

}
