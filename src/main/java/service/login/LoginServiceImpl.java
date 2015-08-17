/**
 * 
 */
package service.login;

import org.springframework.stereotype.Component;

/**
 * @author isapsjd
 *
 */
@Component
public class LoginServiceImpl implements LoginService{

    /**
     * {@inheritDoc}
     */
	public boolean validate(String userName, String password){
		return true;
	}
}
