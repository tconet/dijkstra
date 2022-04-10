package com.algorithm.dijkstra.util;

import com.algorithm.dijkstra.entity.City;
import com.algorithm.dijkstra.entity.Route;
import com.algorithm.dijkstra.interfaces.IRouterBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class Loader {

    /**
     * <p>
     * Reads the file that represents all available routes. For now,
     * the file name must be routes.txt and must be placed into
     * resources project folder.
     * </>
     * @throws IOException
     */
    public Map<City, ArrayList<Route>> loadFromResourceFolder(IRouterBuilder builder) throws IOException {

        // Load the file from resource folder.
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("routes.txt").getFile());

        BufferedReader reader = new BufferedReader(new FileReader(file));
        return builder.buildFromFile(reader);
    }

}
