package com.ip241k.skoryk;

import com.ip241k.skoryk.dao.UserDAO;
import com.ip241k.skoryk.resource.UserResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class MyApplication extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new MyApplication().run(args);
    }

    @Override
    public String getName() {
        return "JavaSpringLR4";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
        UserDAO userDAO = new UserDAO();
        UserResource userResource = new UserResource(userDAO);
        environment.jersey().register(userResource);
    }
}
