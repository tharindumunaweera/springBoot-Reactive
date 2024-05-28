package com.tharindumunaweera.springbootreactive.repository;

import com.tharindumunaweera.springbootreactive.entity.ProductEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {
}
