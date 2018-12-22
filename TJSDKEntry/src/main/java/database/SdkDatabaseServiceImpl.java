package database;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SdkDatabaseServiceImpl implements SdkDatabaseService{
    public static final Logger LOGGER = LoggerFactory.getLogger(SdkDatabaseServiceImpl.class);
    private final JDBCClient dbClient;

    @Override
    public SdkDatabaseService listDatas(String sqlQuery, Handler<AsyncResult<List<JsonObject>>> resultHandler) {
        dbClient.query(sqlQuery, queryResult -> {
            if (queryResult.succeeded()) {
                resultHandler.handle(Future.succeededFuture(queryResult.result().getRows()));
            } else {
                LOGGER.error("Database query error", queryResult.cause());
                resultHandler.handle(Future.failedFuture(queryResult.cause()));
            }
        });
        return this;
    }


    public SdkDatabaseServiceImpl(List<String> lists, JDBCClient dbClient, Handler<AsyncResult<SdkDatabaseService>> readyHandler) {
        this.dbClient = dbClient;
        dbClient.getConnection(ar -> {
            if (ar.failed()) {
                LOGGER.error("Could not open a database connection", ar.cause());
                readyHandler.handle(Future.failedFuture(ar.cause()));
            } else {
                SQLConnection connection = ar.result();
                connection.batch(lists, create -> {
                    connection.close();
                    if (create.failed()) {
                        LOGGER.error("Database preparation err", create.cause());
                        readyHandler.handle(Future.failedFuture(create.cause()));
                    } else {
                        readyHandler.handle(Future. succeededFuture(this));
                    }
                });
            }
        });
    }

    @Override
    public SdkDatabaseService query(String sqlQuery, JsonArray jsonArray, Handler<AsyncResult<Void>> resultHandler) {
        dbClient.updateWithParams(sqlQuery, jsonArray, res -> {
            if (res.succeeded()) {
                resultHandler.handle(Future.succeededFuture());
            } else {
                LOGGER.error("Database query error", res.cause());
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
        return this;
    }

    @Override
    public SdkDatabaseService fetchDatas(String sqlQuery, JsonArray jsonArray, Handler<AsyncResult<List<JsonObject>>> resultHandler) {
        dbClient.queryWithParams(sqlQuery,jsonArray, queryResult -> {
            if (queryResult.succeeded()) {
                resultHandler.handle(Future.succeededFuture(queryResult.result().getRows()));
            } else {
                LOGGER.error("Database query error", queryResult.cause());
                resultHandler.handle(Future.failedFuture(queryResult.cause()));
            }
        });
        return this;
    }

    @Override
    public SdkDatabaseService batch(String sqlQuery, List<JsonArray> list, Handler<AsyncResult<Void>> resultHandler) {
        dbClient.getConnection(conn->{
            if (conn.succeeded()){
                SQLConnection connection=conn.result();
                connection.batchWithParams(sqlQuery,list,rs->{
                    if (rs.succeeded()) {
                        connection.close();
                        resultHandler.handle(Future.succeededFuture());
                    }else {
                        connection.close();
                        resultHandler.handle(Future.failedFuture(rs.cause()));
                    }
                });
            }else {
                resultHandler.handle(Future.failedFuture(conn.cause()));
            }
        });
        return this;
    }
}
