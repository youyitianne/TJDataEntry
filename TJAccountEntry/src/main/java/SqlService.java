import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.ext.sql.SQLConnection;

import java.util.ArrayList;
import java.util.List;


public class SqlService {
    Logger logger= LoggerFactory.getLogger(SqlService.class.getName());

    /**
     * 无参查找
     * @param jdbcClient
     * @param sql
     * @return
     */
    public Future<List<JsonObject>> find(JDBCClient jdbcClient, String sql){
        Future<List<JsonObject>> future= Future.future();
        jdbcClient.getConnection(conn->{
            if (conn.succeeded()){
                SQLConnection connection=conn.result();
                connection.query(sql,rs->{
                    if (rs.succeeded()){
                        connection.close();
                        ResultSet result = rs.result();
                        List<JsonObject> jsonObjects = new ArrayList<>();
                        for (int i = 0; i < result.getRows().size(); i++) {
                            JsonObject jsonObject = result.getRows().get(i);
                            jsonObjects.add(jsonObject);
                        }
                        future.complete(jsonObjects);
                    }else {
                        logger.error("查找失败",rs.cause());
                        future.fail(rs.cause());
                    }
                });
            }else {
                logger.error("获取数据库连接失败",conn.cause());
                future.fail(conn.cause());
            }
        });
        return future;
    }


    /**
     * 带参执行
     * @param jdbcClient
     * @param sql
     * @return
     */
    public Future<Boolean> excuteWithParams(JDBCClient jdbcClient, String sql,JsonArray jsonArray){
        Future<Boolean> future= Future.future();
        jdbcClient.getConnection(conn->{
            if (conn.succeeded()){
                SQLConnection connection=conn.result();
                connection.updateWithParams(sql,jsonArray,rs->{
                    if (rs.succeeded()){
                        connection.close();
                        future.complete(true);
                    }else {
                        future.fail(rs.cause());
                    }
                });
            }else {
                logger.error("获取数据库连接失败",conn.cause());
                future.fail(conn.cause());
            }
        });
        return future;
    }

    private Future<List<JsonObject>> findWithParams(JDBCClient jdbcClient, String sql, JsonArray jsonArray){
        Future<List<JsonObject>> future= Future.future();
        jdbcClient.getConnection(conn->{
            if (conn.succeeded()){
                SQLConnection connection=conn.result();
                connection.queryWithParams(sql,jsonArray,rs->{

                });
            }else {
                logger.error("获取数据库连接失败",conn.cause());
                future.fail(conn.cause());
            }
        });
        return future;
    }

}
