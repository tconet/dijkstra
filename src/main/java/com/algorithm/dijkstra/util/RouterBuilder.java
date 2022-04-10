package com.algorithm.dijkstra.util;

import com.algorithm.dijkstra.entity.City;
import com.algorithm.dijkstra.entity.Route;
import com.algorithm.dijkstra.interfaces.IRouterBuilder;
import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

@Log
public class RouterBuilder implements IRouterBuilder  {

    /**
     * <p>
     *     Builds the routes base on file. In this case, the file content must have
     *     the following pattern.
     *     Each line must have: City (From), City (To) and Distance
     *     Example: V0,V1,10
     *     All values must be separated by comma (',') and no space between them.
     * @param reader @{@link BufferedReader}
     * @return List of {@link Route}
     * @throws IOException
     */
    @Override
    public Map<City, ArrayList<Route>> buildFromFile(BufferedReader reader) throws IOException {

        /* Initialization */
        String line;
        Map<City, ArrayList<Route>> routes = new HashMap<>();
        String cityName = "";
        ArrayList<Route> rts = null;

        /* Stars read each line and build the routes */
        while ((line = reader.readLine()) != null) {
            log.log(Level.INFO, line);

            /* Split the line content */
            String[] content = line.split(",");
            validatePattern(line, content);

            String name = content[0];
            if ( !cityName.equals(name) ) {
                cityName = name;
                rts = new ArrayList<>();
                routes.put(new City(name), rts);
            }
            rts.add(Route.build(content));
        }
        return routes;
    }

    /**
     * <p>
     * Validates the the informed line has the desired format.
     * @param line Must have 3 information: City (From), City (To), Distance
     * @param content
     */
    private void validatePattern(String line, String[] content) {
        if ( content.length < 3 ) {
            String error = "The line content is out of the pattern: " + line;
            log.log(Level.SEVERE, error);
            throw new RuntimeException(error);
        }
    }
}
