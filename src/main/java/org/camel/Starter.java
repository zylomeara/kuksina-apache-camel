package org.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.camel.beans.Provider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Starter {
    public static void main(String[] args) throws Exception {
        CamelContext camel = new DefaultCamelContext();
        camel.getPropertiesComponent().setLocation("classpath:application.properties");
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        camel.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file:{{from}}")
                        .routeId("File processing")
                        .unmarshal(new BindyCsvDataFormat(Provider.class))
                        .process(exchange -> {
                            List<Provider> data = exchange.getIn().getBody(List.class);
                            session.beginTransaction();
                            data.forEach(session::save);
                            session.getTransaction().commit();
                        });
            }
        });
        camel.start();
        Thread.sleep(40_000_000);
        camel.stop();
    }

}