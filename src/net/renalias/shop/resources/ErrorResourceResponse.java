package net.renalias.shop.resources;

public class ErrorResourceResponse extends ResourceResponse {

	public ErrorResourceResponse(String message) {
		super();
		setSuccess(false);
		setData("message", message);
	}
}
