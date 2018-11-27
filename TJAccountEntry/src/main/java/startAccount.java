import io.vertx.core.Vertx;

public class startAccount {
    public static void main(String[] args) {
        Vertx vertx=Vertx.vertx();
        vertx.deployVerticle(AccountVerticle .class.getName());
    }
}

