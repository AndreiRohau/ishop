package by.asrohau.iShop.service.util;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.bean.User;

public class ServiceValidator {

    public static boolean validation(User user) {
        return !"".equals(user.getLogin()) && !"".equals(user.getPassword());
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


}
