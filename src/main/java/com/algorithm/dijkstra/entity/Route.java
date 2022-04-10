package com.algorithm.dijkstra.entity;

import lombok.Data;

@Data
public class Route {

    private String from;
    private String to;
    private Integer distance = Integer.MAX_VALUE;

    public static Route build(String[] content) {
        Route route = new Route();
        route.setFrom(content[0]);
        route.setTo(content[1]);
        route.setDistance(Integer.parseInt(content[2]));
        return route;
    }
}
