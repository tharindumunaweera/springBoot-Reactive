package com.tharindumunaweera.springbootreactive.controller;

import com.tharindumunaweera.springbootreactive.service.StreamingService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stream")
public class StreamingController  {

    @Autowired
    private StreamingService service;

    @GetMapping(value = "/{title}", produces = "video/mp4")
    public Mono<Resource> getVideos(@PathVariable String title, @RequestHeader("Range") String range) {
        System.out.println("range in bytes() : " + range);
        return service.getVideo(title);
    }
}
