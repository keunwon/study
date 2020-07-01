package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String ... args) {
        new SpringApplication(TacoCloudApplication.class).run(args);
    }
}
