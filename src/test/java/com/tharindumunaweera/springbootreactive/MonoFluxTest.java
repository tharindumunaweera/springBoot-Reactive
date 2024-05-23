package com.tharindumunaweera.springbootreactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono() {
        Mono<?> monoString = Mono.just("Tharindumunaweera")
                .then(Mono.error(new RuntimeException("Exception occured")))
                .log();
        monoString.subscribe(System.out::println);// subscriber should subscribe from publisher as a first step
    }
}
