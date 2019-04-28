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
public interface ResStoreDatabaseService {
    @Fluent
    ResStoreDatabaseService query(String sqlQuery, JsonArray jsonArray, Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    ResStoreDatabaseService queryWithoutParam(String sqlQuery, Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    ResStoreDatabaseService fetchDatas(String sqlQuery, JsonArray jsonArray, Handler<AsyncResult<List<JsonObject>>> resultHandler);

    @Fluent
    ResStoreDatabaseService listDatas(String sqlQuery, Handler<AsyncResult<List<JsonObject>>> resultHandler);

    @Fluent
    ResStoreDatabaseService batch(String sqlQuery, List<JsonArray> list, Handler<AsyncResult<Void>> resultHandler);

    static ResStoreDatabaseService create(List<String> lists, JDBCClient dbClient, Handler<AsyncResult<ResStoreDatabaseService>> readyHandler) {
        return new ResStoreDatabaseServiceImp(lists,dbClient,readyHandler);
    }

    static ResStoreDatabaseService createProxy(Vertx vertx, String address) {
        return new ResStoreDatabaseServiceVertxEBProxy(vertx, address);
    }
}
