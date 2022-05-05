package lt.skarb.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/")
public class MainController {
    @GetMapping
    public Mono<String> main() {
        return Mono.just("Hello!");
    }
}
