package fr.sogeti;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import io.vertx.ext.web.RoutingContext;

/**
 *
 * @author fduneau
 */
public class HelloExampleCommand extends HystrixCommand<String> {
    private final RoutingContext ctx;
    
    private HelloExampleCommand(RoutingContext ctx) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.ctx = ctx;
    }
    
    @Override
    protected String run() throws Exception {
        return "hello";
    }
    
    public static void handle(RoutingContext ctx){
        String s = new HelloExampleCommand(ctx).execute();
        ctx.response().end(s);
    }
}
