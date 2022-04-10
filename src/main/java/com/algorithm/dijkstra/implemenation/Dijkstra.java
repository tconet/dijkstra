package com.algorithm.dijkstra.implemenation;

import com.algorithm.dijkstra.entity.City;
import com.algorithm.dijkstra.entity.Route;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;

@Log
public class Dijkstra {

    private Map<City, ArrayList<Route>> routes;
    private ArrayList<City> cities;

    public void run(Map<City, ArrayList<Route>> routes, String from, String to) {
        this.routes = routes;
        // CONFIG. Build cities from routes.
        buildCities();
        // EXECUTION. Calculates the best route.
        executeAlgorithm(findByName(from));
        // PRINT. Prints the best route
        print(from, to);
    }

    /**
     * <p>
     * We must follow the Dijkstra rules, so to accomplish this task, we
     * must go through 3 Steps.
     * 1 - STEP: Marque the first City (from), and Reset the other.
     * 2 - STEP: Find the lower estimate, if exists, close the city em move to third step
     * 3 - STEP: Visit each route and update for the better estimate, after that back
     *     to second step until has no opened city.
     * @param from {@link City} Represents the start point
     */
    private void executeAlgorithm(City from) {
        // Step 1. RESET
        // Marque the first City and reset the others
        reset(from);
        // Step 2. FIND LOWER ESTIMATE
        // Search for the opened city with the lower estimate
        City lower = null;
        while ( (lower = findLowerEstimate()) != null ) {
            // Step 3. VISIT EACH ROUTE
            // Visit each route and update the best estimate
            visitEachRoute(lower);
        }
    }

    /**
     * <p>
     * Mark the from city as the first, for the rest of the cities, we must
     * reset theirs best route. This is part of the initialization process
     * to run the Dijkstra algorithm.
     * </>
     * @param from {@link  City}
     */
    private void reset(City from) {
        for ( City city: cities ) {
            if ( city.equals(from)) {
                city.setFirst();
                continue;
            }
            city.reset();
        }
    }

    /**
     * <p>
     * Search for the opened city with the better estimate, if exist, otherwise
     * return null, which means we have reach the end of the routes.
     * @return city {@link City} The city with the lowest estimate
     *         or null if there is no more opened city.
     */
    private City findLowerEstimate() {
        City lowerEstimateCity = null;
        Integer estimate = Integer.MAX_VALUE;
        for ( City city: cities ) {
            if ( city.isOpen() && (city.getEstimate() <= estimate) ) {
                lowerEstimateCity = city;
                estimate = city.getEstimate();
            }
        }
        if ( Objects.isNull(lowerEstimateCity) )
            return null;
        lowerEstimateCity.close();
        return lowerEstimateCity;
    }

    /**
     * <p>
     * Visit each route from the informed city, if exists. When reach
     * the destini, we must update the best route considering the path
     * already traveled
     * </>
     * @param city {@link  City} The one that we must visit the routes
     */
    private void visitEachRoute(City city) {
        ArrayList<Route> paths = routes.get(city);
        paths.forEach( (path -> {
            City to = findByName(path.getTo());
            to.isBestRoute(city, city.getEstimate() + path.getDistance());
        }));

    }

    private void print(String from, String to) {

        City toCity = findByName(to);
        boolean hasCity = true;
        while ( hasCity ) {
            log.log(Level.INFO, toCity.getName() + " --> " +  toCity.getBestRoute().getFrom());
            toCity = findByName(toCity.getBestRoute().getFrom());
            if ( toCity.getName().equals(from)) {
                return;
            }
        }
    }

    private City findByName(String name) {
        for ( City city: cities ) {
            if ( city.getName().equals(name))
                return city;
        }
        return null;
    }

    private void buildCities() {
        cities = new ArrayList<>();
        for ( City city: routes.keySet() ) {
            cities.add( city );
        }
    }

}
