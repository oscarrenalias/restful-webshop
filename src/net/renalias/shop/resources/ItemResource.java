package net.renalias.shop.resources;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import net.renalias.shop.models.Item;
import net.renalias.shop.models.ModelManager;
import net.renalias.shop.resources.ResourceResponse;
import net.renalias.shop.resources.ErrorResourceResponse;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.*;

import flexjson.JSONSerializer;

/**
 * Resource which has only one representation.
 * 
 */
public class ItemResource extends ServerResource {

    /** 
     * Handle PUT requests. 
     *  
     * @throws IOException 
     */  
    @Put
    @Post
    public Representation addItem(Representation entity) throws IOException  {    	
    	// get the request data  
         Form form = new Form(entity);
         
         // create the model, update its fields and save to the datastore
         Item i = new Item(form.getFirstValue("code"), form.getFirstValue("name"));
         i.setDescription(form.getFirstValue("description"));
         i.setImageURL(form.getFirstValue("imageURL"));
         i.setPrice(Integer.parseInt(form.getFirstValue("price").trim()));
         
         try {
        	 PersistenceManager m = ModelManager.get().getPersistenceManager();
        	 m.makePersistent(i);
        	 m.close();

        	 HashMap hm = new HashMap();
        	 hm.put("item", i);
        	 ResourceResponse resp = new ResourceResponse(true, "Item successfully created", hm);
      
        	 return(resp.asJSon());
         } catch( Exception e ) {        	 
        	 getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);
        	 
        	 ErrorResourceResponse resp = new ErrorResourceResponse("There was an error saving the item");
        	 return(resp.asJSon());
         }
         
    }
    
    @Get
    public Representation getItem() {
    	PersistenceManager m = ModelManager.get().getPersistenceManager();
    	
    	String itemCode = (String)getRequestAttributes().get("item");
    	
    	Query q = m.newQuery(Item.class, "itemCode == itemCodeParam");
    	q.declareParameters("String itemCodeParam");
    	List<Item> i = (List<Item>)q.execute(itemCode);
    	
    	if(i.isEmpty()) {
    		ErrorResourceResponse resp = new ErrorResourceResponse("Item object not found");
    		getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
    		
    		return(resp.asJSon());    		
    	}
    	else {
    		ResourceResponse resp = new ResourceResponse();    		
    		resp.setSuccess(true);
    		resp.setData("item", i.get(0));
    		getResponse().setStatus(Status.SUCCESS_OK);    		
    		
    		return(resp.asJSon());    		
    	}
    }
    
	@Delete
    public Representation deleteItem() {
    	PersistenceManager m = ModelManager.get().getPersistenceManager();
    	
    	String itemCode = (String)getRequestAttributes().get("item");
    	Query q = m.newQuery(Item.class, "itemCode == itemCodeParam");
    	q.declareParameters("String itemCodeParam");
    	List<Item> i = (List<Item>)q.execute(itemCode);
    	
    	if(i.isEmpty()) {
    		ErrorResourceResponse resp = new ErrorResourceResponse("Item object not found");
    		getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
    		
    		return(resp.asJSon());
    	}
    	else {
    		// delete the entity
    		m.deletePersistent(i.get(0));
    		
    		ResourceResponse resp = new ResourceResponse();    		
    		resp.setSuccess(true);
    		resp.setData("item", i.get(0));
    		
    		return(resp.asJSon());    		
    	}    	
    }
}
