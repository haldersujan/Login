package resource.login;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.LoginResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import service.login.LoginService;

@Component
@Path("customer")
public class LoginResource {

	/** The login service. */
	@Autowired
	private LoginService loginService;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoginResource.class);

	/**
	 * Login using standard HTML form post data.
	 * 
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @return the response - this will always be a 302 redirect, in which case
	 *         a 200 response code will be returned
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login")
	public Response login(@FormParam("username") String username,
			@FormParam("password") String password) {
		LOGGER.debug("Entering login {} , {}", username, password);
		LOGGER.debug("loginService {} ", loginService);

		boolean validated = loginService.validate(username, password);
		LoginResponse response = new LoginResponse();
		response.setValidated(validated);

		return Response.ok(response).build();

	}

	/**
	 * Login using standard HTML form post data.
	 * 
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @return the response - this will always be a 302 redirect, in which case
	 *         a 200 response code will be returned
	 */
	@GET
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("test")
	public Response test() {
		LOGGER.debug("Entering test");
		return Response.status(200).build();

	}
}
