package com.example.couchbase.config;

import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.query.Consistency;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCouchbaseRepositories(basePackages = {"com.example.couchbase.repository"})
public class MyCouchbaseConfig extends AbstractCouchbaseConfiguration {

    protected static final Logger logger = LoggerFactory.getLogger(MyCouchbaseConfig.class);

    @Autowired
    private CouchbaseSetting couchbaseSetting;

    @Override
    protected List<String> getBootstrapHosts() {
        logger.info("Registering host '{}' for couchbase cluster", couchbaseSetting.getHostName());
        return Arrays.asList(couchbaseSetting.getHostName());
    }

    @Override
    protected String getBucketName() {
        logger.info("Opening bucket '{}'", couchbaseSetting.getBucketName());
        return couchbaseSetting.getBucketName();
    }

    @Override
    protected String getBucketPassword() {
        logger.info("Get bucket password '{}'", couchbaseSetting.getPassword());
        return couchbaseSetting.getPassword();
    }

    @Override
    protected CouchbaseEnvironment getEnvironment() {
        DefaultCouchbaseEnvironment.builder().connectTimeout(60000) // by default 5 sec (5000 ms)
                .queryTimeout(20000) // by default 75 sec (75000 ms)
                .socketConnectTimeout(45000); // by default 1 sec (1000 ms)
        return super.getEnvironment();
    }

    @Override
    public Consistency getDefaultConsistency() {
        // By default, READ_YOUR_OWN_WRITES
        // Values: READ_YOUR_OWN_WRITES, STRONGLY_CONSISTENT, UPDATE_AFTER, EVENTUALLY_CONSISTENT
        return Consistency.READ_YOUR_OWN_WRITES;
    }

    @Override
    public String typeKey() {
        // By default, this attribute is named "_class".
        // Spring Data automatically adds to each document an attribute containing the full class name of the entity.
        // This field is the one used by N1QL queries to filter only documents corresponding to the repository’s entity.
        return "type";
    }

    /**
     * This is additional configuration if we want some other objects (PlaceDoc, PhoneDoc) to be stored in other bucket
     */
    @Override
    protected String getUsername() {
        logger.info("Get bucket username '{}'", couchbaseSetting.getUsername());
        return couchbaseSetting.getUsername();
    }
}
