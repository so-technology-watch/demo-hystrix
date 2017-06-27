package fr.sogeti.verticles;

import com.github.kennedyoliveira.hystrix.contrib.vertx.metricsstream.EventMetricsStreamHandler;
import fr.sogeti.FailExampleCommand;
import fr.sogeti.FallBackCommand;
import fr.sogeti.HelloExampleCommand;
import fr.sogeti.TimeoutExampleCommand;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import static java.lang.String.format;
import static java.lang.System.out;

/**
 *
 * @author fduneau
 */
public class HelloVerticle extends AbstractVerticle {
    
    @Override
    public void start(){
        Router router = Router.router(vertx);
        
        router.route("/fail").handler(FailExampleCommand::handle);
        
        router.route("/hello").handler(HelloExampleCommand::handle);
        
        router.route("/timeout").handler(TimeoutExampleCommand::handle);
        
        router.route("/fallback").handler(FallBackCommand::handle);
        
        router.get(EventMetricsStreamHandler.DEFAULT_HYSTRIX_PREFIX)
              .handler(EventMetricsStreamHandler.createHandler());
        
        
        int port = 8181;
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(router::accept);
        server.listen(port);
        out.println(format("Running on port %d", port));
    }
    
    /**
    <servlet>
		<description></description>
		<display-name>HystrixMetricsStreamServlet</display-name>
		<servlet-name>HystrixMetricsStreamServlet</servlet-name>
		<servlet-class>com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet</servlet-class>
	</servlet>

	<servlet-mapping>
		<servlet-name>HystrixMetricsStreamServlet</servlet-name>
		<url-pattern>/hystrix.stream</url-pattern>
	</servlet-mapping>
     */
}
