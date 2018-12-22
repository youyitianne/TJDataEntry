import database.SdkDatabaseVerticle;
import http.SdkHttpVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MainVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Future<String> dbVerticleDeployment = Future.future();
        vertx.deployVerticle(new SdkDatabaseVerticle(), dbVerticleDeployment);
        dbVerticleDeployment.compose(id -> {
            Future<String> httpVerticleDeployment = Future.future();
            vertx.deployVerticle(new SdkHttpVerticle(),httpVerticleDeployment.completer());
            return httpVerticleDeployment;
        }).setHandler(ar -> {
            if (ar.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(ar.cause());
            }
        });
    }
}
