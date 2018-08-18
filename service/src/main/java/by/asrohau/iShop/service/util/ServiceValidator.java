package by.asrohau.iShop.service.util;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.entity.Reserve;
import by.asrohau.iShop.entity.User;

public class ServiceValidator {

    public static boolean validation(User user) {
        return !"".equals(user.getLogin()) && !"".equals(user.getPassword());
    }

    public static boolean validation(String login) {
        return !"".equals(login);
    }

    public static boolean validation(Product product) {
        return (!"".equals(product.getCompany()) &&
                !"".equals(product.getName()) &&
                !"".equals(product.getType()) &&
                !"".equals(product.getPrice()));
    }

    public static boolean validation(Reserve reserve) {
        return reserve.getUserId() > 0 && reserve.getProductId() > 0;
    }

    public static boolean validation(long id) {
        return id > 0;
    }

    public static boolean validation(int row) {
        return row >= 0;
    }


}
