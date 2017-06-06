package br.com.caelum.treinamentos.controllers;

import br.com.caelum.treinamentos.domain.Course;
import br.com.caelum.treinamentos.domain.projections.CourseProjection;
import br.com.caelum.treinamentos.domain.viewModel.CourseViewModel;
import br.com.caelum.treinamentos.repositories.CourseProjectionRepository;
import br.com.caelum.treinamentos.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Created by nando on 06/06/17.
 */
@RestController
@RequestMapping("courses")
public class CourseController {

    @Autowired
    private CourseProjectionRepository projectionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value = "{code}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByCode(@PathVariable("code") String code){
        Optional<CourseProjection> possibleProjection = projectionRepository.findByCode(code);

        return possibleProjection.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());


    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseProjection> findAll(){
        return projectionRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseProjection> save(@RequestBody CourseViewModel courseViewModel){
        Course course = courseViewModel.toCourse();

        courseRepository.save(course);

        CourseProjection courseProjection = course.getProjection();

        return ResponseEntity.created(URI.create("/courses/" + courseProjection.getCode() )).body(courseProjection);
    }

    @PutMapping(value = "{code}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("code") String code, @RequestBody CourseViewModel viewModel){
        Optional<Course> possibleCourse = courseRepository.findByCode(code);

        if (possibleCourse.isPresent()) {

            Course existingCourse = possibleCourse.get();

            Course course = viewModel.toCourse();

            existingCourse.merge(course);

            courseRepository.save(existingCourse);

            return ResponseEntity.ok(existingCourse.getProjection());
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{code}")
    public ResponseEntity<?> delete(@PathVariable("code") String code){
        courseRepository.deleteByCode(code);

        return ResponseEntity.ok().build();
    }
}
