package br.com.caelum.treinamentos.domain.viewModel;

import br.com.caelum.treinamentos.domain.Course;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Duration;

/**
 * Created by nando on 06/06/17.
 */
public class CourseViewModel {
    @Min(20)
    @NotNull
    private Long duration;

    @NotBlank
    private String code;

    @NotBlank
    private String description;

    public Long getDuration() {
        return duration;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Course toCourse() {
        return new Course(code, description, Duration.ofHours(duration));
    }

}
