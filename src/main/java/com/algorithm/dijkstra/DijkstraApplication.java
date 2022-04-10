package com.algorithm.dijkstra;

import com.algorithm.dijkstra.entity.City;
import com.algorithm.dijkstra.entity.Route;
import com.algorithm.dijkstra.implemenation.Dijkstra;
import com.algorithm.dijkstra.interfaces.IRouterBuilder;
import com.algorithm.dijkstra.util.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Map;

@SpringBootApplication
public class DijkstraApplication implements CommandLineRunner {

	@Autowired
	IRouterBuilder routerBuilder;

	public static void main(String[] args) {
		SpringApplication.run(DijkstraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Loader loader = new Loader();
		Map<City, ArrayList<Route>> routes = loader.loadFromResourceFolder( routerBuilder );

		Dijkstra dijkstra = new Dijkstra();
		dijkstra.run( routes, "V0", "V5");
	}
}
