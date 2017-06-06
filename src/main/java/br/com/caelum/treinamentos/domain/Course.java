package br.com.caelum.treinamentos.domain;

import br.com.caelum.treinamentos.domain.projections.CourseProjection;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Duration;

/**
 * Created by nando on 06/06/17.
 */
@Entity
public class Course implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String code;

    @NotBlank
    private String description;

    @NotNull
    private Duration duration;

    public Course(String code, String description, Duration duration) {
        this.code = code;
        this.description = description;
        this.duration = duration;
    }

    /**
     * @deprecated only by frameworks
     */
    private Course(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return code.equals(course.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    public void merge(Course other) {
        code = other.code;
        description = other.description;
        duration = other.duration;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                '}';
    }

    public CourseProjection getProjection() {
        return new DefaultProjection(this);
    }


    private class DefaultProjection implements CourseProjection {

        private final String code;
        private final String description;
        private final Duration duration;

        public DefaultProjection(Course course) {

            this.code = course.code;
            this.description = course.description;
            this.duration = course.duration;
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public String getDescription() {
            return this.description;
        }

        @Override
        public Duration getDuration() {
            return this.duration;
        }
    }
}
