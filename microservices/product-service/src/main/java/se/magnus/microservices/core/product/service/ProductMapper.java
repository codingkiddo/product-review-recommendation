package se.magnus.microservices.core.product.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import se.magnus.api.core.product.Product;
import se.magnus.microservices.core.product.persistence.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	@Mappings({ @Mapping(target = "serviceAddress", ignore = true) })
	Product entityToApi(ProductEntity productEntity);

	@Mappings({ @Mapping(target = "id", ignore = true), @Mapping(target = "version", ignore = true) })
	ProductEntity apiToEntity(Product api);
}
