import io.vertx.core.Vertx;

public class startdataentry {
    public static void main(String[] args) {
        Vertx vertx=Vertx.vertx();
        vertx.deployVerticle(MainVerticle .class.getName());
    }
}

