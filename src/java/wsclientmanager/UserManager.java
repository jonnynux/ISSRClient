package wsclientmanager;

import interfaces.UserOperations;
import java.util.Date;
import java.util.List;
import utils.XMLGCC;
import webservices.*;

/**
 *
 * @author jonny
 */
public class UserManager implements UserOperations {

    UserWS serviceOperation = new UserWS_Service().getUserWSPort();

    @Override
    public List<Role> getRoles(User executor) throws OperationNotPermittedException,
            NoRolesException {

        return serviceOperation.getRoles(executor);
    }

    @Override
    public boolean createUpdateUser(User executor, int idUser, int idStore, int idRole,
            String login, String namesurname, String email, Date pwexpiration, Date regexpiration,
            int sessionduration) throws OperationNotPermittedException, RequiredFieldEmptyException,
            LoginNotValidException, EmailNotValidException, RoleNotValidException, NoStoresException {

        return serviceOperation.createUpdateUser(executor, idUser, idStore, idRole,
                login, namesurname, email, XMLGCC.convertDate(pwexpiration), XMLGCC.convertDate(regexpiration),
                sessionduration);
    }

    @Override
    public List<User> getUserList(User executor) throws OperationNotPermittedException,
            NoUsersException {

        return serviceOperation.getUserList(executor);
    }

    @Override
    public boolean removeUser(User executor, int idUser) throws OperationNotPermittedException,
            RoleNotValidException, NoUsersException {

        return serviceOperation.removeUser(executor, idUser);
    }
}
