package ua.training.cashregister.controller;

import ua.training.cashregister.dao.DaoFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener  implements ServletContextListener {

        @Override
        public void contextInitialized(ServletContextEvent servletContextEvent) {
          //  DaoFactory.getInstance().createUserDao();

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
        }
}

