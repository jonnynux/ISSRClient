package interfaces;

import java.util.List;
import webservices.*;

/**
 * Interface for all the store operations
 *
 * @author jonny
 */
public interface StoreOperations {

    public List<Store> getStoreList(User executor) throws NoStoresException;

    public int orderProduct(User executor, String brand, String type, int quantity)
            throws OperationNotPermittedException, NoProductException, RequiredFieldEmptyException;

    public List<Log> getLogList(User executor) throws OperationNotPermittedException,
            NoLogException;
}
