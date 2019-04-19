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
public interface RedDatabaseService {
    @Fluent
    RedDatabaseService queryWithid(String sqlQuery1,String sqlQuery2,JsonArray jsonArray, Handler<AsyncResult<JsonObject>> resultHandler);

    @Fluent
    RedDatabaseService query(String sqlQuery, JsonArray jsonArray, Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    RedDatabaseService queryWithoutParam(String sqlQuery, Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    RedDatabaseService fetchDatas(String sqlQuery, JsonArray jsonArray, Handler<AsyncResult<List<JsonObject>>> resultHandler);

    @Fluent
    RedDatabaseService listDatas(String sqlQuery, Handler<AsyncResult<List<JsonObject>>> resultHandler);

    @Fluent
    RedDatabaseService queryWithids(String sqlQuery1,String sqlQuery2,JsonArray jsonArray, Handler<AsyncResult<List<JsonObject>>> resultHandler);

    @Fluent
    RedDatabaseService batch(String sqlQuery, List<JsonArray> list, Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    RedDatabaseService batchWithoutParam(List<String> sqlQuery, Handler<AsyncResult<Void>> resultHandler);

    static RedDatabaseService create(List<String> lists, JDBCClient dbClient, Handler<AsyncResult<RedDatabaseService>> readyHandler) {
        return new RedDatabaseServiceImpl(lists,dbClient,readyHandler);
    }

    static RedDatabaseService createProxy(Vertx vertx, String address) {
        return new RedDatabaseServiceVertxEBProxy(vertx, address);
    }
}
