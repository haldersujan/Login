package util.service;

import java.net.URI;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

@Service
public class ServiceUtil {

    /**
     * Build a response with a HTTP status of 302, instructing the browser to perform a redirect to the specified
     * location.
     * 
     * @param redirectUrl
     *            the redirect url
     * @return the redirect on error response
     */
    public static Response buildRedirectOnErrorResponse(String redirectUrl) {
        StringBuilder redirectLocation = new StringBuilder();
        redirectLocation.append(redirectUrl);
        return Response.status(302)
            .location(URI.create(redirectLocation.toString())).build();
    }
}
