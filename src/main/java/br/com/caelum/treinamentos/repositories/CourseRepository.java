package br.com.caelum.treinamentos.repositories;

import br.com.caelum.treinamentos.domain.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by nando on 06/06/17.
 */
public interface CourseRepository extends Repository<Course, Long> {

    Optional<Course> findByCode(@Param("code") String code);

    void save(Course course);

    @Modifying
    @Query("delete from Course c where c.code = ?1")
    void deleteByCode(String code);
}
