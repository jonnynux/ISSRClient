package wsclientmanager;

import interfaces.StoreOperations;
import java.util.List;
import webservices.*;

/**
 *
 * @author jonny
 */
public class StoreManager implements StoreOperations {

    StoreWS serviceOperation = new StoreWS_Service().getStoreWSPort();

    @Override
    public List<Store> getStoreList(User executor) throws NoStoresException {
        return (List<Store>) serviceOperation.getStoreList(executor);
    }

    @Override
    public int orderProduct(User executor, String brand, String type, int quantity)
            throws OperationNotPermittedException, NoProductException, RequiredFieldEmptyException {

        return serviceOperation.orderProduct(executor, brand, type, quantity);
    }

    @Override
    public List<Log> getLogList(User executor) throws OperationNotPermittedException,
            NoLogException {

        return serviceOperation.getLogList(executor);
    }
}
