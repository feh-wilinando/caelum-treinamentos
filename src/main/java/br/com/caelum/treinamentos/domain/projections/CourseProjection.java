package br.com.caelum.treinamentos.domain.projections;

import br.com.caelum.treinamentos.domain.Course;

import java.time.Duration;

/**
 * Created by nando on 06/06/17.
 */
public interface CourseProjection {


    String getCode();
    String getDescription();
    Duration getDuration();
}
