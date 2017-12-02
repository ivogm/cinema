package com.ivohasablog.cinema.movieservice.cache;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MapLoader;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import com.ivohasablog.cinema.movieservice.cache.preloader.HallMapLoader;
import com.ivohasablog.cinema.movieservice.cache.preloader.MovieMapLoader;
import com.ivohasablog.cinema.movieservice.cache.serializable.MovieTheaterDSFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by Ivo on 18.11.2017 г..
 */
@Configuration
public class HazelcastConfig {

    /** Helper constants */
    private static final String HZ_MEMBERS_SEPARATOR = ",";

    /** Hazelcast Maps */
    public static final String MOVIE_MAP = "movieMap";
    public static final String HALL_MAP = "hallMap";
    public static final String PROJECTION_MAP = "projectionMap";
    public static final String TICKET_MAP = "seatMap";

    @Autowired
    private HazelcastProperties hzProperties;

    @Autowired
    private MovieMapLoader movieMapLoader;

    @Autowired
    private HallMapLoader hallMapLoader;

    @Bean
    public CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();

        //Cluster
        String clusterName = hzProperties.getGroup().getName();
        config.setInstanceName(clusterName);
        config.getGroupConfig().setName(clusterName);
        config.getGroupConfig().setPassword(hzProperties.getGroup().getPassword());

        //Properties
        config.getSerializationConfig().addDataSerializableFactory(1, new MovieTheaterDSFactory());
        config.setProperty("hazelcast.logging.type", hzProperties.getLogging().getType());
        config.setProperty("hazelcast.jmx", hzProperties.getJmx());

        //Network
        NetworkConfig network = config.getNetworkConfig();
        network.setPort(hzProperties.getNetwork().getPort());
        network.setPortAutoIncrement(hzProperties.getNetwork().isPortAutoIncrement());
        JoinConfig join = network.getJoin();
        join.getMulticastConfig().setEnabled(false);
        TcpIpConfig tcpIpConfig = join.getTcpIpConfig();
        tcpIpConfig.setEnabled(true);
        tcpIpConfig.setMembers(Arrays.asList(hzProperties.getCluster().getMembers().split(HZ_MEMBERS_SEPARATOR)));

        //Maps
        MapConfig movieMapConfig = configMovieMap(MOVIE_MAP, EvictionPolicy.LFU);
        MapConfig hallMapConfig = configMovieMap(HALL_MAP, EvictionPolicy.NONE);
        MapConfig projectionMapConfig = configMovieMap(PROJECTION_MAP, EvictionPolicy.LFU);
        MapConfig ticketMapConfig = configMovieMap(TICKET_MAP, EvictionPolicy.LFU);

        configMapStore(movieMapConfig, movieMapLoader);
        configMapStore(hallMapConfig, hallMapLoader);

        config.addMapConfig(movieMapConfig);
        config.addMapConfig(hallMapConfig);
        config.addMapConfig(projectionMapConfig);
        config.addMapConfig(ticketMapConfig);

        return Hazelcast.newHazelcastInstance(config);
    }

    private MapConfig configMovieMap(String mapName, EvictionPolicy evictionPolicy) {
        MapConfig movieMapConfig = new MapConfig();
        movieMapConfig.setName(mapName);
        movieMapConfig.setEvictionPolicy(evictionPolicy);
        movieMapConfig.addMapIndexConfig(new MapIndexConfig("id", false));
        return movieMapConfig;
    }

    private void configMapStore(MapConfig mapConfig, MapLoader mapLoader) {
        MapStoreConfig storeConfig = new MapStoreConfig();
        storeConfig.setImplementation(mapLoader);
        storeConfig.setEnabled(true);
        mapConfig.setMapStoreConfig(storeConfig);
    }
}
