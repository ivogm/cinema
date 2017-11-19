package com.ivohasablog.cinema.movieservice.cache;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
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

    @Autowired
    private HazelcastProperties hzProperties;

    @Autowired
    private MovieMapLoader movieMapLoader;

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
        config.addMapConfig(configMovieMap());

        return Hazelcast.newHazelcastInstance(config);
    }

    private MapConfig configMovieMap() {
        MapConfig movieMapConfig = new MapConfig();
        movieMapConfig.setName(MOVIE_MAP);
        movieMapConfig.setEvictionPolicy(EvictionPolicy.NONE);
        movieMapConfig.addMapIndexConfig(new MapIndexConfig("id", false));

        MapStoreConfig movieStoreConfig = new MapStoreConfig();
        movieStoreConfig.setImplementation(movieMapLoader);
        movieStoreConfig.setEnabled(true);
        movieMapConfig.setMapStoreConfig(movieStoreConfig);

        return movieMapConfig;
    }
}
