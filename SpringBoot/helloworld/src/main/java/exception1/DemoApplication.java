package exception1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
   public static void main(String[] args) {
      SpringApplication.run(DemoApplication.class, args);
   }
}
/*
 * postman
 * http://localhost:8080/products/3
 * choose put, body, raw, json
 * {
    "name":"Dat"
    }
 */

