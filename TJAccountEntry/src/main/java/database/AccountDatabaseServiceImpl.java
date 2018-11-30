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
import java.util.stream.Collectors;

public class AccountDatabaseServiceImpl implements AccountDatabaseService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AccountDatabaseServiceImpl.class);
    private final JDBCClient dbClient;


    public AccountDatabaseServiceImpl(List<String> lists, JDBCClient dbClient, Handler<AsyncResult<AccountDatabaseService>> readyHandler) {
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
                        readyHandler.handle(Future.succeededFuture(this));
                    }
                });
            }
        });
    }

    @Override
    public AccountDatabaseService fetchAll(String sqlQuery, Handler<AsyncResult<JsonArray>> resultHandler) {
        dbClient.query(sqlQuery, res -> {
            if (res.succeeded()) {
                JsonArray pages = new JsonArray(res.result()
                        .getResults()
                        .stream()
                        .map(json -> json.getString(0))
                        .sorted()
                        .collect(Collectors.toList()));
                resultHandler.handle(Future.succeededFuture(pages));
            } else {
                LOGGER.error("Database query error", res.cause());
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
        return this;
    }

    @Override
    public AccountDatabaseService fetch(String sqlQuery, JsonArray jsonArray, Handler<AsyncResult<JsonObject>> resultHandler) {
        dbClient.queryWithParams(sqlQuery, jsonArray, res -> {
            if (res.succeeded()) {
                resultHandler.handle(Future.succeededFuture(res.result().getRows().get(0)));
            } else {
                LOGGER.error("Database query error", res.cause());
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
        return this;
    }

    @Override
    public AccountDatabaseService query(String sqlQuery, JsonArray jsonArray, Handler<AsyncResult<Void>> resultHandler) {
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
    public AccountDatabaseService fetchAllData(String sqlQuery, Handler<AsyncResult<List<JsonObject>>> resultHandler) {
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


    @Override
    public AccountDatabaseService fetchDatas(String sqlQuery,JsonArray jsonArray, Handler<AsyncResult<List<JsonObject>>> resultHandler) {
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
    public AccountDatabaseService batch(String sqlQuery,List<JsonArray> list, Handler<AsyncResult<Void>> resultHandler) {
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
