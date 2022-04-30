package com.tars.capitalmarket;

import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
//        Micronaut.run(Application.class, args)
        Micronaut.build(args)
                .eagerInitConfiguration(true)
                .eagerInitSingletons(true)
                .start();
    }
}
