package lt.skarb.backend.controller;

import lombok.RequiredArgsConstructor;
import lt.skarb.backend.model.entity.Wonder;
import lt.skarb.backend.repository.WonderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/wonder")
@RequiredArgsConstructor
public class WonderController {
    private final WonderRepository wonderRepository;

    @GetMapping("")
    public Mono<Wonder> getRandom() {
        return wonderRepository.findAll().singleOrEmpty();
    }
}
