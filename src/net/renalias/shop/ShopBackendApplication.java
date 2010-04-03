package net.renalias.shop;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import net.renalias.shop.resources.*;

public class ShopBackendApplication extends Application {

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());  
  
        // Defines only one route  
        router.attach("/items", ItemsResource.class);  
        router.attach("/orders", OrdersResource.class);
        router.attach("/item", ItemResource.class);
        router.attach("/item/{item}", ItemResource.class);
  
        return router; 
    }
}
