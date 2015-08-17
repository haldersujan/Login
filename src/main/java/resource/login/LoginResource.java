package resource.login;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import service.login.LoginService;

@Component
@Path("customer")
public class LoginResource {

    /** The login service. */
    @Autowired
    private LoginService loginService;
    
	/**
	 * Login using standard HTML form post data.
	 * 
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @return the response - this will always be a 302 redirect, 
	 *         in which case a 200 response code will be returned
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	@Path("login")
	public Response login(
			@FormParam("username") String username,
			@FormParam("password") String password) {
		
		boolean validated = loginService.validate(username, password);
		if (validated){
			return Response.status(200).build();
		} else {
			return Response.status(401).build();
		}

	}
}
