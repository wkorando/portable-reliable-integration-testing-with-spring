package com.bk.movie;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;

@SpringJUnitConfig
@AutoConfigureStubRunner(ids = "com.bk.movie:movie-service:+:8081", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class TestContract {

	private RestTemplate restTemplate = new RestTemplate();

	@MockBean
	private MovieService servic;
	@MockBean
	private MovieRepo repo;
	@MockBean
	private DataSource datasource;
	
	@Test
	public void testCallFindAllMovies() {
		Movie[] movies = restTemplate.getForObject("http://localhost:8081/movies", Movie[].class);

		for (Movie movie : movies) {
			System.out.println(movie.toString());
		}
	}

}
