package com.tharindumunaweera.springbootreactive.repository;

import com.tharindumunaweera.springbootreactive.dto.ProductDto;
import com.tharindumunaweera.springbootreactive.entity.ProductEntity;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {
    Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);
}
