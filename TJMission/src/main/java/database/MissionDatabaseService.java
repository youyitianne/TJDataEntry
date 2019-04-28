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
public interface MissionDatabaseService {
    @Fluent
    MissionDatabaseService query(String sqlQuery, JsonArray jsonArray, Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    MissionDatabaseService queryWithoutParam(String sqlQuery, Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    MissionDatabaseService fetchDatas(String sqlQuery,JsonArray jsonArray, Handler<AsyncResult<List<JsonObject>>> resultHandler);

    @Fluent
    MissionDatabaseService listDatas(String sqlQuery, Handler<AsyncResult<List<JsonObject>>> resultHandler);

    @Fluent
    MissionDatabaseService batch(String sqlQuery,List<JsonArray> list,Handler<AsyncResult<Void>> resultHandler);

    static MissionDatabaseService create(List<String> lists, JDBCClient dbClient, Handler<AsyncResult<MissionDatabaseService>> readyHandler) {
        return new MissionDatabaseServiceImp(lists,dbClient,readyHandler);
    }

    static MissionDatabaseService createProxy(Vertx vertx, String address) {
        return new MissionDatabaseServiceVertxEBProxy(vertx, address);
    }
}
