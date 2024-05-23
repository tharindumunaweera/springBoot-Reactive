package com.tharindumunaweera.springbootreactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono() {
        Mono<String> monoString = Mono.just("Tharindumunaweera");
        monoString.subscribe(System.out::println);
    }
}
