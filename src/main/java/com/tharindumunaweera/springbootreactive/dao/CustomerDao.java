package com.tharindumunaweera.springbootreactive.dao;

import com.tharindumunaweera.springbootreactive.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleepExecution(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerDao:: sleepExecution)
                .peek(i -> System.out.println("Processing count : "+ i))
                .mapToObj(i -> new Customer(i, "customer" + 1)).collect(Collectors.toList());
    }


    public Flux<Customer> getCustomersStream() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Processing stream flow: "+ i))
                .map(i -> new Customer(i, "customer" + 1));
    }
}
