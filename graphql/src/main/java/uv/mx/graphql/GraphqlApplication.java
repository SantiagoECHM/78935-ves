package uv.mx.graphql;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(MaestroRepository maestroRepository, SalonRepository salonRepository) {
		return args -> {
			Maestro carlos = maestroRepository.save(new Maestro(null, "Carlos"));
			Maestro juan = maestroRepository.save(new Maestro(null, "Juan"));
			salonRepository.saveAll(List.of(
					new Salon("cc3", "C", carlos),
					new Salon("F402", "A", juan),
					new Salon("102", "B", carlos)));
		};
	}

}
