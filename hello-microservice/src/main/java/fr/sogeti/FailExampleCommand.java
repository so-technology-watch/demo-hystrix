package fr.sogeti;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import io.vertx.ext.web.RoutingContext;

/**
 *
 * @author fduneau
 */
public class FailExampleCommand extends HystrixCommand<String> {
    private final RoutingContext ctx;
    
    private FailExampleCommand(RoutingContext ctx) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.ctx = ctx;
    }
    
    @Override
    protected String run() throws Exception {
        throw new IllegalArgumentException("you requested a fail");
    }
    
    public static void handle(RoutingContext ctx){
        String s = new FailExampleCommand(ctx).execute();
        ctx.response().end(s);
    }
}
