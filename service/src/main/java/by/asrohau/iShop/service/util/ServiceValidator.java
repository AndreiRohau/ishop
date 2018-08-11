package by.asrohau.iShop.service.util;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.bean.User;

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
        return reserve.getrUserId() != 0 && reserve.getrProductId() != 0;
    }

    public static boolean validation(long id) {
        return id >= 0;
    }

    public static boolean validation(int row) {
        return row >= 0;
    }


}
