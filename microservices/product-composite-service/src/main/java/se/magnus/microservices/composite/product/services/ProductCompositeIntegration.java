package se.magnus.microservices.composite.product.services;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import se.magnus.api.core.product.Product;
import se.magnus.api.core.product.ProductService;
import se.magnus.api.core.recommendation.Recommendation;
import se.magnus.api.core.recommendation.RecommendationService;
import se.magnus.api.core.review.Review;
import se.magnus.api.core.review.ReviewService;
import se.magnus.api.exceptions.InvalidInputException;
import se.magnus.api.exceptions.NotFoundException;
import se.magnus.util.http.HttpErrorInfo;

public class ProductCompositeIntegration implements ProductService, RecommendationService, ReviewService {

	private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeIntegration.class);

	private final RestTemplate restTemplate;
	private final ObjectMapper mapper;
	private final String productServiceUrl;
	private final String recommendationServiceUrl;
	private final String reviewServiceUrl;

	@Autowired
	public ProductCompositeIntegration(RestTemplate restTemplate, ObjectMapper mapper,
			@Value("${app.product-service.host}") String productServiceHost,
			@Value("${app.product-service.port}") int productServicePort,
			@Value("${app.recommendation-service.host}") String recommendationServiceHost,
			@Value("${app.recommendation-service.port}") int recommendationServicePort,
			@Value("${app.review-service.host}") String reviewServiceHost,
			@Value("${app.review-service.port}") int reviewServicePort) {

		this.restTemplate = restTemplate;
		this.mapper = mapper;

		productServiceUrl = "http://" + productServiceHost + ":" + productServicePort + "/product/";
		recommendationServiceUrl = "http://" + recommendationServiceHost + ":" + recommendationServicePort
				+ "/recommendation?productId=";
		reviewServiceUrl = "http://" + reviewServiceHost + ":" + reviewServicePort + "/review?productId=";
	}

	public Product getProduct(int productId) {

	    try {
	      String url = productServiceUrl + productId;
	      LOG.debug("Will call getProduct API on URL: {}", url);

	      Product product = restTemplate.getForObject(url, Product.class);
	      LOG.debug("Found a product with id: {}", product.getProductId());

	      return product;

	    } catch (HttpClientErrorException ex) {

	      switch (HttpStatus.resolve(ex.getStatusCode().value())) {
	        case NOT_FOUND:
	          throw new NotFoundException(getErrorMessage(ex));

	        case UNPROCESSABLE_ENTITY:
	          throw new InvalidInputException(getErrorMessage(ex));

	        default:
	          LOG.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
	          LOG.warn("Error body: {}", ex.getResponseBodyAsString());
	          throw ex;
	      }
	    }
	  }

	private String getErrorMessage(HttpClientErrorException ex) {
		try {
			return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
		} catch (IOException e) {
			return e.getMessage();
		}
	}
	
	@Override
	public List<Recommendation> getRecommendations(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> getReviews(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
