package com.tharindumunaweera.springbootreactive;

import com.tharindumunaweera.springbootreactive.controller.ProductController;
import com.tharindumunaweera.springbootreactive.dto.ProductDto;
import com.tharindumunaweera.springbootreactive.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
class SpringbootReactiveApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private ProductService productService;

	@Test
	public void addProductTest() {
		Mono<ProductDto> productDTOMono = Mono.just(new ProductDto("102", "mobile", 1, 1000));
		Mockito.when(productService.saveProduct(productDTOMono)).thenReturn(productDTOMono);

		webTestClient.post().uri("/products")
				.body(Mono.just(productDTOMono), ProductDto.class)
				.exchange()
				.expectStatus().isOk();
	}

	@Test
	public void getProductTest() {
		Flux<ProductDto> productDtoFlux = Flux.just(new ProductDto("102", "mobile", 1, 1000), new ProductDto("103", "mobilepixel", 2, 2000));
		Mockito.when(productService.getProducts()).thenReturn(productDtoFlux);

		Flux<ProductDto> responseBody = webTestClient.get().uri("/products")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ProductDto.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(new ProductDto("102", "mobile", 1, 1000))
				.expectNext(new ProductDto("103", "mobilepixel", 2, 2000))
				.verifyComplete();

	}

	@Test
	public void getProductById() {
		Mono<ProductDto> prodctDtoMono = Mono.just(new ProductDto("102", "mobile", 1, 1000));
		Mockito.when(productService.getProduct("1")).thenReturn(prodctDtoMono);

		Flux<ProductDto> responseBody = webTestClient.get().uri("/products/1")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ProductDto.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(new ProductDto("102", "mobile", 1, 1000))
				.verifyComplete();
	}

}
