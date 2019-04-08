package database;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementService {
    Logger logger= LoggerFactory.getLogger(AdvertisementService.class);
//    private final Vertx vertx;
//    private final JsonObject config;
//    private final JDBCClient client;
//
//    /**
//     * jdbcservice初始化
//     *
//     * @param vertx
//     * @param config
//     */
//    public AdvertisementService(Vertx vertx, JsonObject config) {
//        this.vertx = vertx;
//        this.config = config;
//        this.client = JDBCClient.createShared(vertx, config);
//    }
//
//    /**
//     * 获取connection
//     *
//     * @param future
//     * @param handler
//     * @return
//     */
//    public Handler<AsyncResult<SQLConnection>> connHandler(Future future, Handler<SQLConnection> handler) {
//        return conn -> {
//            if (conn.succeeded()) {
//                final SQLConnection connection = conn.result();
//                handler.handle(connection);
//            } else {
//                future.fail(conn.cause());
//            }
//        };
//    }

    /**
     * 多条sql批量插入
     *
     * @param sql
     * @return
     */
    public Future<Boolean> batch2(JDBCClient client,List<String> sql) {
        Future<Boolean> future = Future.future();
        client.getConnection(result->{
            if(result.succeeded()){
                SQLConnection conn=result.result();
                conn.batch(sql, rs -> {
                    if (rs.succeeded()) {
                        future.complete(true);
                    } else {
                        logger.error("执行sql失败",rs.cause());
                        future.fail(rs.cause());
                    }
                    conn.close();
                });
            }else {
                logger.error("获取数据库连接失败",result.cause());
            }
        });
        return future;
    }

    /**
     * 单sql多参数批量插入
     *
     * @param sql
     * @return
     */
    public Future<Boolean> batchWithParams1(JDBCClient client,String sql, List<JsonArray> values) {
        Future<Boolean> future = Future.future();
        client.getConnection(result->{
            if(result.succeeded()) {
                SQLConnection conn = result.result();
                conn.batchWithParams(sql, values, rs -> {
                    if (rs.succeeded()) {
                        future.complete(true);
                    } else {
                        future.fail(rs.cause());
                    }
                    conn.close();
                });
            }
            });
        return future;
    }

    /**
     * 查找表内重复数据得到id，再用sql删除，
     * 只保留id最大的
     *
     * @param sel 查询语句
     * @param del 删除语句
     * @return  Boolean
     */
    public Future<Boolean> removeRepeat(JDBCClient client,String sel, String del) {
        Future<Boolean> future = Future.future();
        System.out.println(1);
        client.query(sel, rs -> {
            System.out.println(13);
            if (rs.succeeded()) {
                List<JsonObject> list = rs.result().getRows();
                if (list.size() == 0) {
                    future.complete(true);
                } else {
                    StringBuffer buffer = new StringBuffer();
                    for (int i = 0; i < list.size(); i++) {
                        if (i == 0) {
                            buffer.append("(");
                        }
                        buffer.append(list.get(i).getValue("id"));
                        if (i == (list.size() - 1)) {
                            buffer.append(");");
                        } else {
                            buffer.append(",");
                        }
                    }
                    String delete = del + buffer;
                    System.out.println(2);
                    client.update(delete, result -> {
                        System.out.println(3);
                        future.complete(true);
                    });
                }
            } else {
                future.complete(true);
            }
        });

//        client.getConnection(conn->{
//            if (conn.succeeded()){
//                SQLConnection connection=conn.result();
//                connection.query(sel, rs -> {
//                    if (rs.succeeded()) {
//                        List<JsonObject> list = rs.result().getRows();
//                        if (list.size() == 0) {
//                            future.complete(true);
//                        } else {
//                            StringBuffer buffer = new StringBuffer();
//                            for (int i = 0; i < list.size(); i++) {
//                                if (i == 0) {
//                                    buffer.append("(");
//                                }
//                                buffer.append(list.get(i).getValue("id"));
//                                if (i == (list.size() - 1)) {
//                                    buffer.append(");");
//                                } else {
//                                    buffer.append(",");
//                                }
//                            }
//                            String delete = del + buffer;
//                            connection.update(delete, result -> {
//                                connection.close();
//                                future.complete(true);
//                            });
//                        }
//                    } else {
//                        future.complete(true);
//                    }
//                });
//
//            }else {
//
//            }
//        });

        return future;
    }

    /**
     * 查找所有游戏名称
     */
    public Future<JsonObject> findone(JDBCClient client,String sql,String value) {
        Future<JsonObject> future = Future.future();
        JsonObject jsonObject = new JsonObject();
        client.query(sql, conn -> {
            if (conn.succeeded()) {
                StringBuffer name = new StringBuffer();
                for (int i = 0; i < conn.result().getRows().size(); i++) {
                    name.append(conn.result().getRows().get(i).getValue(value));
                    if (i != conn.result().getRows().size() - 1) {
                        name.append(",");
                    }
                }
                jsonObject.put("gamename", name);
                future.complete(jsonObject);
            } else {
                future.fail(conn.cause());
            }
        });
        return future;
    }

    /**
     * 查询数据表----多个查询结果
     *
     * @param sql sql
     * @return <List<UserVO>>
     */
    public Future<List<JsonObject>> findDatasWithParams(JDBCClient client,String sql, JsonArray jsonArray) {
        Future<List<JsonObject>> future = Future.future();
        client.queryWithParams(sql, jsonArray, conn -> {
            if (conn.succeeded()) {
                ResultSet rs = conn.result();
                List<JsonObject> jsonObjects = new ArrayList<>();
                for (int i = 0; i < rs.getRows().size(); i++) {
                    JsonObject jsonObject = rs.getRows().get(i);
                    jsonObjects.add(jsonObject);
                }
                future.complete(jsonObjects);
            } else {
                future.fail(conn.cause());
            }
        });
        return future;
    }


    /**
     * 预编译条件查找
     */
    public Future<List<JsonObject>> query(JDBCClient client,String sql) {
        Future<List<JsonObject>> future = Future.future();
        client.query(sql, conn -> {
            if (conn.succeeded()) {
                ResultSet rs = conn.result();
                List<JsonObject> jsonObjects = new ArrayList<>();
                for (int i = 0; i < rs.getRows().size(); i++) {
                    JsonObject jsonObject = rs.getRows().get(i);
                    jsonObjects.add(jsonObject);
                }
                future.complete(jsonObjects);
            } else {
                future.fail(conn.cause());
            }
        });
        return future;
    }

    /**
     * 之星sql 无参数返回
     */
    public Future<Boolean> queryNoResult(JDBCClient client,String sql) {
        Future<Boolean> future = Future.future();
        client.query(sql, conn -> {
            if (conn.succeeded()) {
                future.complete(true);
            } else {
                future.fail(conn.cause());
            }
        });
        return future;
    }

    /**
     * 预编译条件insert/update/delete
     */
    public Future<Boolean> update(JDBCClient client,String sql, JsonArray value) {
        Future<Boolean> future = Future.future();
        client.updateWithParams(sql,value, conn -> {
            if (conn.succeeded()) {
                future.complete(true);
            } else {
                future.fail(conn.cause());
            }
        });
        return future;
    }
}