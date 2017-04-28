package fr.sogeti;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import io.vertx.ext.web.RoutingContext;

/**
 *
 * @author fduneau
 */
public class TimeoutExampleCommand extends HystrixCommand<String> {
    private final RoutingContext ctx;
    
    private TimeoutExampleCommand(RoutingContext ctx) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.ctx = ctx;
    }
    
    @Override
    protected String run() throws Exception {
        ctx.response().end("timeout");
        return null;
    }
    
    public static void handle(RoutingContext ctx){
        new TimeoutExampleCommand(ctx).execute();
    }
}
