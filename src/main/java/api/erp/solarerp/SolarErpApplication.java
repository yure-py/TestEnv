package api.erp.solarerp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SolarErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolarErpApplication.class, args);
    }

}
