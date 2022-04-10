package com.algorithm.dijkstra.configurations;

import com.algorithm.dijkstra.interfaces.IRouterBuilder;
import com.algorithm.dijkstra.util.RouterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public IRouterBuilder routerBuilder() {
        return new RouterBuilder();
    }
}
