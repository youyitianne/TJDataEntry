import io.vertx.core.Vertx;

public class startMission {
    public static void main(String[] args) {
        Vertx vertx=Vertx.vertx();
        vertx.deployVerticle(MainVerticle.class.getName());
    }
}
