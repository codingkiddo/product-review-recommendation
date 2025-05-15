package se.magnus.microservices.core.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.magnus.api.core.product.Product;
import se.magnus.api.core.product.ProductService;

public class ProductServiceImpl implements ProductService {

	private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Override
	public Product getProduct(int productid) {
		// TODO Auto-generated method stub
		return null;
	}
}
