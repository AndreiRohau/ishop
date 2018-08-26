package by.asrohau.iShop.controller.listener;

import by.asrohau.iShop.controller.command.CommandFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        CommandFactory.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
