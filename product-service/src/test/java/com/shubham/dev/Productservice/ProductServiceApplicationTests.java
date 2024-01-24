package com.shubham.dev.Productservice;

import com.shubham.dev.Productservice.dto.productRequest;
import com.shubham.dev.Productservice.dto.productResponse;
import com.shubham.dev.Productservice.repository.productRepository;
import com.shubham.dev.Productservice.service.productService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@RequiredArgsConstructor
class ProductServiceApplicationTests {
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private productRepository ProductRepository;

	@MockBean
	private productService ProductService;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
	}



	@Test
	void shouldcreateProduct() throws Exception {

		productRequest ProductResponse =  getProductRequest();

		String ProductRequestString = objectMapper.writeValueAsString(ProductResponse);


		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content(ProductRequestString))  // Assuming ProductRequestString is the JSON payload
				.andExpect(status().isCreated());
		Assertions.assertEquals(0,ProductRepository.findAll().size());

	}

	private productRequest getProductRequest() {
		return productRequest.builder()
				.name("Iphone 12")
				.description("Iphone 12")
				.price(BigDecimal.valueOf(109000))
				.build();
	}

	@Test
	void shouldGetAllProducts() throws Exception {
		// Arrange
		List<productResponse> mockProductList = Arrays.asList(
				new productResponse("1", "Product1", "Description1", BigDecimal.valueOf(100.0)),
				new productResponse("2", "Product2", "Description2", BigDecimal.valueOf(200.0))
				// Add more mock products as needed
		);

		// Mock the ProductService to return the mockProductList when getAllProducts is called
		when(ProductService.getAllProducts()).thenReturn(mockProductList);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.size()").value(mockProductList.size()))
				.andExpect(jsonPath("$[0].id").value(mockProductList.get(0).getId()))
				.andExpect(jsonPath("$[0].name").value(mockProductList.get(0).getName()))
				.andExpect(jsonPath("$[0].description").value(mockProductList.get(0).getDescription()))
				.andExpect(jsonPath("$[0].price").value(mockProductList.get(0).getPrice().doubleValue()))
				.andExpect(jsonPath("$[1].id").value(mockProductList.get(1).getId()))
				.andExpect(jsonPath("$[1].name").value(mockProductList.get(1).getName()))
				.andExpect(jsonPath("$[1].description").value(mockProductList.get(1).getDescription()))
				.andExpect(jsonPath("$[1].price").value(mockProductList.get(1).getPrice().doubleValue()));
	}

}
