package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.datatype.XMLGregorianCalendar;
import webservices.Index;
import webservices.Product;

/**
 * Class for utility purpose
 *
 * @author jonny
 */
public class Utility {

    private Utility() {
    }

    public static String GeneratePassword() {
        return "pass";
    }

    public static boolean BarcodeIsValid(String barcode) {

        Pattern pattern = Pattern.compile("\\d{8,14}$");

        Matcher matcher = pattern.matcher(barcode);

        return matcher.matches();
    }

    public static boolean EmailIsValid(String email) {

        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean LoginIsValid(String login) {

        Pattern pattern = Pattern.compile("^[a-z0-9]*$");

        Matcher matcher = pattern.matcher(login);

        return matcher.matches();
    }

    public static String ConvertDate(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");

        return dateFormat.format(date);
    }

    public static String ConvertDateTime(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy; H:m:s");

        return dateFormat.format(date);
    }

    public static String ConvertDate(XMLGregorianCalendar gregDate) {

        return ConvertDate(XMLGCC.convertDate(gregDate));
    }

    public static String ConvertDateTime(XMLGregorianCalendar gregDate) {

        return ConvertDateTime(XMLGCC.convertDate(gregDate));
    }

    public static double Trunc(double x) {

        long y = (long) (x * 1000);
        return (double) y / 1000;
    }

    public static List<Index> CalculateLocalIndexes(List<Product> products, boolean global) {

        double totalSold = 0;
        double totalTempSold = 0;
        for (Product i : products) {
            totalSold += i.getTotsold();
            totalTempSold += i.getTempsold();
        }

        List<Index> indexes = new ArrayList();
        for (Product i : products) {
            Index index = new Index();

            index.setBrand(i.getBrand());

            index.setType(i.getType());

            index.setReturnedIndex((i.getTotsold() > 0) ? (double) i.getTotreturned()
                    / (double) i.getTotsold() : 0);

            index.setSuccessIndex((i.getTotrequested() > 0) ? (double) i.getTotsold()
                    / (double) i.getTotrequested() : 0);

            index.setExpiredIndex((i.getTotstored() > 0) ? (double) i.getTotexpired()
                    / (double) i.getTotstored() : 0);

            index.setApprovalIndex((totalSold > 0) ? (double) i.getTotsold()
                    / (double) totalSold : 0);

            if (!global) {
                index.setReturnedTempIndex((i.getTempsold() > 0) ? (double) i.getTempreturned()
                        / (double) i.getTempsold() : 0);

                index.setSuccessTempIndex((i.getTemprequested() > 0) ? (double) i.getTempsold()
                        / (double) i.getTemprequested() : 0);

                index.setExpiredTempIndex((i.getTempstored() > 0) ? (double) i.getTempexpired()
                        / (double) i.getTempstored() : 0);

                index.setApprovalTempIndex((totalTempSold > 0) ? (double) i.getTempsold()
                        / (double) totalTempSold : 0);
            }

            indexes.add(index);
        }

        return indexes;
    }

    public static List<Index> CalculateGlobalIndexes(List<Product> products) {

        return CalculateLocalIndexes(aggregate(products), true);
    }

    private static List<Product> aggregate(List<Product> products) {

        List<Product> aggregated = new ArrayList();
        for (Product i : products) {

            boolean found = false;
            for (Product j : aggregated) {
                if (j.getBrand().equals(i.getBrand()) && j.getType().equals(i.getType())) {
                    found = true;

                    j.setTotstored(j.getTotstored() + i.getTotstored());

                    j.setTotrequested(j.getTotrequested() + i.getTotrequested());

                    j.setTotsold(j.getTotsold() + i.getTotsold());

                    j.setTotexpired(j.getTotexpired() + i.getTotexpired());

                    j.setTotreturned(j.getTotreturned() + i.getTotreturned());

                    break;
                }
            }

            if (!found) {

                aggregated.add(i);
            }
        }

        return aggregated;
    }
}
