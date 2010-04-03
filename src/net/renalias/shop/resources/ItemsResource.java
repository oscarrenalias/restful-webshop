package net.renalias.shop.resources;

import java.util.List;

import javax.jdo.PersistenceManager;

import net.renalias.shop.models.Item;
import net.renalias.shop.models.ModelManager;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import flexjson.JSONSerializer;

/**
 * Resource which has only one representation.
 * 
 */
public class ItemsResource extends ServerResource {

    @Get
    public Representation getList() {
        
    	PersistenceManager pm = ModelManager.get().getPersistenceManager();
    	String query = "SELECT FROM " + Item.class.getName();
    	List<Item> items = (List<Item>) pm.newQuery(query).execute();
    	
    	//JsonRepresentation rep = new JsonRepresentation(items);
    	
    	String jsonData = new JSONSerializer().serialize(items);
    	Representation rep = new StringRepresentation(jsonData);
    	
        getResponse().setStatus(Status.SUCCESS_OK);
        
        return( rep );
    }

}
