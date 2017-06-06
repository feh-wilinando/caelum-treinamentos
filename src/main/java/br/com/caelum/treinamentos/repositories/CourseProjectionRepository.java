package br.com.caelum.treinamentos.repositories;

import br.com.caelum.treinamentos.domain.Course;
import br.com.caelum.treinamentos.domain.projections.CourseProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by nando on 06/06/17.
 */
public interface CourseProjectionRepository extends Repository<Course, Long> {

    Optional<CourseProjection> findByCode(@Param("code") String code);

    @Query("select c.code as code, c.description as description, c.duration as duration from Course c")
    List<CourseProjection> findAll();
}
