package se.magnus.microservices.core.product;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;
	
//	@Test
//	public void get_product_by_id() {
//		int productId = 1;
//
//		webTestClient.get()
//	      .uri("/product/" + productId)
//	      .accept(APPLICATION_JSON)
//	      .exchange()
//	      .expectStatus().isOk()
//	      .expectHeader().contentType(APPLICATION_JSON)
//	      .expectBody()
//	        .jsonPath("$.productId").isEqualTo(productId);
//	}

}
