package com.core;

import io.vertx.core.Vertx;

public class startcooperationVerticle {
    public static void main(String[] args) {
        Vertx vertx=Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }
}
