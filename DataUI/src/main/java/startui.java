import io.vertx.core.Vertx;

public class startui {
    public static void main(String[] args) {
        Vertx vertx=Vertx.vertx();
        vertx.deployVerticle(UiServerVerticle .class.getName());
    }
}

