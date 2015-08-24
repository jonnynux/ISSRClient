package wsclientmanager;

import interfaces.ProductOperations;
import java.util.Date;
import java.util.List;
import utils.XMLGCC;
import webservices.*;

/**
 *
 * @author jonny
 */
public class ProductManager implements ProductOperations {

    ProductWS serviceOperation = new ProductWS_Service().getProductWSPort();

    @Override
    public boolean createProductType(User executor, String brand, String type)
            throws OperationNotPermittedException, ProductTypeAlreadyPresentException,
            RequiredFieldEmptyException {

        return serviceOperation.createProductType(executor, brand, type);
    }

    @Override
    public List<Product> getProductTypes(User executor) throws OperationNotPermittedException,
            NoProductException {

        return serviceOperation.getProductTypes(executor);
    }

    @Override
    public boolean insertLot(User executor, int idProduct, String barcode, int quantity,
            Date expiration) throws OperationNotPermittedException, RequiredFieldEmptyException,
            BarcodeNotValidException, ExpirationDateNotValidException, NoProductException {

        return serviceOperation.insertLot(executor, idProduct, barcode, quantity,
                XMLGCC.convertDate(expiration));
    }

    @Override
    public List<Lot> getSellProducts(User executor) throws OperationNotPermittedException,
            NoProductException {

        return serviceOperation.getSellProducts(executor);
    }

    @Override
    public List<Lot> getExpiredProducts(User executor) throws OperationNotPermittedException,
            NoProductException {

        return serviceOperation.getExpiredProducts(executor);
    }

    @Override
    public List<Lot> getReturnProducts(User executor) throws OperationNotPermittedException,
            NoProductException {

        return serviceOperation.getReturnProducts(executor);
    }

    @Override
    public Lot getLotByBarcode(User executor, String barcode) throws OperationNotPermittedException,
            NoProductException, BarcodeNotValidException {

        return serviceOperation.getLotByBarcode(executor, barcode);
    }

    @Override
    public String sellProducts(User executor, String barcode, int quantity)
            throws OperationNotPermittedException, NoProductException,
            BarcodeNotValidException, QuantityNotValidException, SellingExpiredProduct {

        return serviceOperation.sellProducts(executor, barcode, quantity);
    }

    @Override
    public String removeExpiredProducts(User executor, String barcode, int quantity)
            throws OperationNotPermittedException, NoProductException, BarcodeNotValidException,
            QuantityNotValidException, RemovingNotExpiredProduct {

        return serviceOperation.removeExpiredProducts(executor, barcode, quantity);
    }

    @Override
    public String returnProducts(User executor, String barcode, int quantity)
            throws OperationNotPermittedException, NoProductException, BarcodeNotValidException,
            QuantityNotValidException {

        return serviceOperation.returnProducts(executor, barcode, quantity);
    }

    @Override
    public List<Index> getLocalIndexes(User executor) throws OperationNotPermittedException,
            NoProductException {

        return serviceOperation.getLocalIndexes(executor);
    }

    @Override
    public List<Index> getGlobalIndexes(User executor) throws OperationNotPermittedException,
            NoProductException {

        return serviceOperation.getGlobalIndexes(executor);
    }
}
