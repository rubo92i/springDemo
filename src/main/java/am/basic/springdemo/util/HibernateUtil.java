package am.basic.springdemo.util;

import am.basic.springdemo.model.*;
import am.basic.springdemo.model.Card;
import am.basic.springdemo.model.Role;
import am.basic.springdemo.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtil {


    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Map<String, String> settings = new HashMap<>();
            settings.put("connection.driver_class", "com.mysql.cj.jdbc.Driver");
            settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
            settings.put("hibernate.connection.username", "root");
            settings.put("hibernate.connection.password", "");

            settings.put("hibernate.show_sql", "true");
            settings.put("hibernate.format_sql", "true");
            settings.put("hibernate.hbm2ddl.auto", "update");


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(settings).build();

            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            metadataSources.addAnnotatedClass(User.class);
            metadataSources.addAnnotatedClass(Comment.class);
            metadataSources.addAnnotatedClass(Card.class);
            metadataSources.addAnnotatedClass(Role.class);
            Metadata metadata = metadataSources.buildMetadata();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }

        return sessionFactory;
    }
}
