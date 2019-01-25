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
public interface PayDatabaseService {
    @Fluent
    PayDatabaseService queryByParams(String sqlQuery, JsonArray jsonArray, Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    PayDatabaseService query(String sqlQuery, Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    PayDatabaseService fetchDatas(String sqlQuery, JsonArray jsonArray, Handler<AsyncResult<List<JsonObject>>> resultHandler);

    @Fluent
    PayDatabaseService listDatas(String sqlQuery, Handler<AsyncResult<List<JsonObject>>> resultHandler);

    @Fluent
    PayDatabaseService batch(String sqlQuery, List<JsonArray> list, Handler<AsyncResult<Void>> resultHandler);

    static PayDatabaseService create(List<String> lists, JDBCClient dbClient, Handler<AsyncResult<PayDatabaseService>> readyHandler) {
        return new PayDatabaseServiceImpl(lists,dbClient,readyHandler);
    }

    static PayDatabaseService createProxy(Vertx vertx, String address) {
        return new PayDatabaseServiceVertxEBProxy(vertx, address);
    }
}
