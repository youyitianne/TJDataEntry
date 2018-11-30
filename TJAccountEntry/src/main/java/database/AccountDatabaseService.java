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
public interface AccountDatabaseService {
    @Fluent
    AccountDatabaseService fetchAll(String sqlQuery,Handler<AsyncResult<JsonArray>> resultHandler);

    @Fluent
    AccountDatabaseService fetchAllData(String sqlQuery,Handler<AsyncResult<List<JsonObject>>> resultHandler);

    @Fluent
    AccountDatabaseService fetchDatas(String sqlQuery,JsonArray jsonArray, Handler<AsyncResult<List<JsonObject>>> resultHandler);

    @Fluent
    AccountDatabaseService batch(String sqlQuery,List<JsonArray> list, Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    AccountDatabaseService fetch(String sqlQuery,JsonArray jsonArray, Handler<AsyncResult<JsonObject>> resultHandler);

    @Fluent
    AccountDatabaseService query(String sqlQuery,JsonArray jsonArray, Handler<AsyncResult<Void>> resultHandler);

    static AccountDatabaseService create(List<String> lists, JDBCClient dbClient, Handler<AsyncResult<AccountDatabaseService>> readyHandler) {
        return new AccountDatabaseServiceImpl(lists,dbClient,readyHandler);
    }

    static AccountDatabaseService createProxy(Vertx vertx, String address) {
        return new AccountDatabaseServiceVertxEBProxy(vertx, address);
    }
}
