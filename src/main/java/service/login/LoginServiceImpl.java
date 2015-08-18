/**
 * 
 */
package service.login;

import org.springframework.stereotype.Service;

/**
 * @author isapsjd
 *
 */
@Service
public class LoginServiceImpl implements LoginService{

    /**
     * {@inheritDoc}
     */
	public boolean validate(String userName, String password){
		return true;
	}
}
