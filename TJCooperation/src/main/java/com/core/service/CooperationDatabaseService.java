package com.core.service;

import com.core.service.imp.CooperationDatabaseServiceImp;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;

import java.util.List;

@ProxyGen
public interface CooperationDatabaseService {
    @Fluent
    CooperationDatabaseService query(String sqlQuery, JsonArray jsonArray, Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    CooperationDatabaseService queryWithoutParam(String sqlQuery, Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    CooperationDatabaseService fetchDatas(String sqlQuery, JsonArray jsonArray, Handler<AsyncResult<List<JsonObject>>> resultHandler);

    @Fluent
    CooperationDatabaseService listDatas(String sqlQuery, Handler<AsyncResult<List<JsonObject>>> resultHandler);

    @Fluent
    CooperationDatabaseService batch(String sqlQuery, List<JsonArray> list, Handler<AsyncResult<Void>> resultHandler);

    static CooperationDatabaseService create(List<String> lists, JDBCClient dbClient, Handler<AsyncResult<CooperationDatabaseService>> readyHandler) {
        return new CooperationDatabaseServiceImp(lists,dbClient,readyHandler);
    }

    static CooperationDatabaseService createProxy(Vertx vertx, String address) {
        return new CooperationDatabaseServiceVertxEBProxy(vertx, address);
    }
}
