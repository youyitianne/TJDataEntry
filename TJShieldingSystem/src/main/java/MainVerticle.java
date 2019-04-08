import http.shieldingHttpVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MainVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Future<String> dbVerticleDeployment = Future.future();
        vertx.deployVerticle(new shieldingHttpVerticle(),dbVerticleDeployment);
        dbVerticleDeployment.setHandler(ar->{
            if (ar.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(ar.cause());
            }
        });
    }
}
