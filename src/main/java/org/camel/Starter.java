package org.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.camel.beans.Provider;

import java.util.List;

public class Starter {
    public static void main(String[] args) throws Exception {
        CamelContext camel = new DefaultCamelContext();
        camel.getPropertiesComponent().setLocation("classpath:application.properties");
//        val dataSource: DataSource = DriverManagerDataSource(
//            "jdbc:postgresql://localhost:5432/catalizator?user=postgres&password=123"
//        )
//        camel.registry.bind("catalizator", dataSource)
//        val template = camel.createProducerTemplate()

//        camel.addRoutes(object : RouteBuilder() {
//            override fun configure() {
//                from("file:{{from}}")
//                    .routeId("File processing")
//                    .convertBodyTo(String::class.java)
//                    .log(">>>> \${body}")
////                    .to("log:?showBody=true&showHeaders=true")
//                    .choice()
//                    .`when` { exchange: Exchange ->
//                        (exchange.getIn().body as String).contains("=a")
//                    }
//                    .to("file:{{toA}}")
//                    .`when` { exchange: Exchange ->
//                        (exchange.getIn().body as String).contains("=b")
//                    }
//                    .to("file:{{toB}}")
//                    .otherwise()
//                    .to("file:{{toA}}")
//            }
//        })
        camel.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file:{{from}}")
                        .routeId("File processing")
                        .unmarshal(new BindyCsvDataFormat(Provider.class))
                        .process(exchange -> {
                            List<Provider> data = exchange.getIn().getBody(List.class);
                            data.forEach(System.out::println);
                        });
            }
        });
//        camel.addRoutes(object : RouteBuilder() {
//            override fun configure() {
//                from("timer:base?period=60000")
//                    .routeId("JDBC route")
//                    .setHeader("key", constant(1))
//                    .setBody(simple("select id, data from message where id > :?key"))
//                    .to("jdbc:catalizator?useHeadersAsParameters=true")
//                    .log(">>> \${body}")
//                    .process { exchange: Exchange ->
//                        val `in` = exchange.getIn()
//                        val body = `in`.body
//                        val message = DefaultMessage(exchange)
//                        message.headers = `in`.headers
//                        message.setHeader("rnd", "kek")
//                        message.body = """
//                        $body
//                        ${`in`.headers}
//                        """.trimIndent()
//                        exchange.message = message
//                    }
//                    .toD("file://D:/dev/camel-app/files/toB?fileName=done-\${date:now:yyyyMMdd}-\${headers.rnd}.txt")
//            }
//        })
        camel.start();
//        template.sendBody(
//            "file://D:/dev/camel-app/files?filename=event-\${date:now:yyyyMMdd-HH-mm}.html",
//            "<hello>world!</hello>"
//        )
        Thread.sleep(40_000);
        camel.stop();
    }

}