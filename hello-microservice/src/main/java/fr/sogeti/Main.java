package fr.sogeti;

import fr.sogeti.verticles.HelloVerticle;
import io.vertx.core.Vertx;

/**
 *
 * @author fduneau
 */
public class Main {
    
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloVerticle());
    }
}
