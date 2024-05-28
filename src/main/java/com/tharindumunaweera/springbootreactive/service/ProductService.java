package com.tharindumunaweera.springbootreactive.service;

import com.tharindumunaweera.springbootreactive.dto.ProductDto;
import com.tharindumunaweera.springbootreactive.repository.ProductRepository;
import com.tharindumunaweera.springbootreactive.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.font.NumericShaper;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Flux<ProductDto> getProducts() {
        return repository.findAll().map(AppUtils:: entityToDto);
    }

    public Mono<ProductDto> getProduct(String id) {
        return repository.findById(id).map(AppUtils:: entityToDto);
    }

    public Flux<ProductDto> getProductInRange(double min, double max) {
        return  repository.findByPriceBetween(Range.closed(max, max));
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono.map(AppUtils::dtoToEntity).flatMap(repository:: insert).map(AppUtils:: entityToDto);
    }
}
