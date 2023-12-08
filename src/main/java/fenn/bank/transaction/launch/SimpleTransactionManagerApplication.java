package fenn.bank.transaction.launch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages ="fenn.bank")
@EntityScan("fenn.bank.transaction.entities")
@EnableJpaRepositories("fenn.bank.transaction.entities.repository")
@EnableSwagger2
public class SimpleTransactionManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleTransactionManagerApplication.class, args);
	}

}
