package wsclientmanager;

import interfaces.LoginOperations;
import webservices.LoginErrorException;
import webservices.LoginWS;
import webservices.LoginWS_Service;
import webservices.User;

/**
 *
 * @author jonny
 */
public class LoginManager implements LoginOperations {

    LoginWS serviceOperation = new LoginWS_Service().getLoginWSPort();

    @Override
    public User login(String login, String password, int idStore) throws LoginErrorException {
        return serviceOperation.login(login, password, idStore);
    }
}
