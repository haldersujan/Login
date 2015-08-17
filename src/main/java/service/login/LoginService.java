/**
 * 
 */
package service.login;

/**
 * @author user
 *
 */
public interface LoginService {

	/**
	 * 
	 * @param username
	 * 		username of the user
	 * @param password
	 * 		password of the user
	 * @return boolean validated
	 */
	public boolean validate(String username,String password);
}
