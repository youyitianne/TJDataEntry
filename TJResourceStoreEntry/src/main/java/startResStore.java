import io.vertx.core.Vertx;

public class startResStore {
    public static void main(String[] args) {
        Vertx vertx=Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }
}
