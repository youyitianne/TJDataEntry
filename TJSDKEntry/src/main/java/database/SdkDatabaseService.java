package database;

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
public interface SdkDatabaseService {
    @Fluent
    SdkDatabaseService query(String sqlQuery, JsonArray jsonArray, Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    SdkDatabaseService fetchDatas(String sqlQuery,JsonArray jsonArray, Handler<AsyncResult<List<JsonObject>>> resultHandler);

    @Fluent
    SdkDatabaseService listDatas(String sqlQuery, Handler<AsyncResult<List<JsonObject>>> resultHandler);

    @Fluent
    SdkDatabaseService batch(String sqlQuery,List<JsonArray> list,Handler<AsyncResult<Void>> resultHandler);

    static SdkDatabaseService create(List<String> lists, JDBCClient dbClient, Handler<AsyncResult<SdkDatabaseService>> readyHandler) {
        return new SdkDatabaseServiceImpl(lists,dbClient,readyHandler);
    }

    static SdkDatabaseService createProxy(Vertx vertx, String address) {
        return new SdkDatabaseServiceVertxEBProxy(vertx, address);
    }
}
