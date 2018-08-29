package by.asrohau.iShop.dao;

public interface DatabaseConfigReader {

    /**
     * object should have a map of better Properties.class instance, so it could use "key/name" to return value
     * @param name is the name of property, or key for map
     * @return value
     */
    String get(String name);

}
