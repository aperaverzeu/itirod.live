package lt.skarb.backend.controller;

import lombok.RequiredArgsConstructor;
import lt.skarb.backend.model.dto.CourseNameDTO;
import lt.skarb.backend.model.entity.Course;
import lt.skarb.backend.service.CourseService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("")
    public Flux<Course> getAll() {
        return courseService.getCourses();
    }

    @GetMapping("/color")
    public Mono<String> getColorByTitle(@RequestBody CourseNameDTO dto) {
        return courseService.getCourseColorByTitle(dto.courseName());
    }
}
