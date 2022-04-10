package com.algorithm.dijkstra.interfaces;

import com.algorithm.dijkstra.entity.City;
import com.algorithm.dijkstra.entity.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface IRouterBuilder {

    /**
     * <p> Builds the routes base on file </>
     * @param reader @{@link BufferedReader}
     * @return List of {@link Route}
     * @throws IOException
     */
    public Map<City, ArrayList<Route>> buildFromFile(BufferedReader reader) throws IOException;

}
