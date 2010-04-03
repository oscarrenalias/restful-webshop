package net.renalias.shop.resources;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Resource which has only one representation.
 * 
 */
public class OrdersResource extends ServerResource {

    @Get
    public String represent() {
        return "hello, world";
    }

}
