package br.com.caelum.treinamentos.clients;

import br.com.caelum.treinamentos.domain.Course;
import br.com.caelum.treinamentos.repositories.CourseRepository;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by nando on 06/06/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class CoursesClientsTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CourseRepository repository;


    Course fj11 = new Course("FJ-11", "Object Orientation", Duration.ofHours(40));
    Course fj21 = new Course("FJ-21", "Java Web", Duration.ofHours(40));
    Course fj22 = new Course("FJ-22", "Spring, Test, Git & Maven", Duration.ofHours(20));

    @Before
    public void setup(){

        repository.save(fj11);
        repository.save(fj21);
        repository.save(fj22);
    }

    @Test
    public void shouldDisplayingFJ_11WhenInputSearchIsEqualTo11() throws Exception {

        mvc.perform(get("/courses/FJ-11"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is("FJ-11")))
                .andExpect(jsonPath("$.description", is("Object Orientation")))
                .andExpect(jsonPath("$.duration.seconds", is(144000)));

    }


    @Test
    public void shouldDisplayAllCourseWhenRequestInRootOfCoursesURI() throws Exception {
        mvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].code", is("FJ-11")))
                .andExpect(jsonPath("$[1].code", is("FJ-21")))
                .andExpect(jsonPath("$[2].code", is("FJ-22")));
    }

    @Test
    public void shouldSaveACourseWhenReceivePostRequestInRootOfCoursesURI() throws Exception {
        String fj25 = new JSONObject()
                .put("code", "FJ-25")
                .put("description", "Hibernate and JPA & EJB Lite")
                .put("duration", 40).toString();

        mvc.perform(
                post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(fj25))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/courses/FJ-25")))
                .andExpect(jsonPath("$.code", is("FJ-25")))
                .andExpect(jsonPath("$.description", is("Hibernate and JPA & EJB Lite")))
                .andExpect(jsonPath("$.duration.seconds", is(144000)));
    }

    @Test
    public void shouldUpdateACourseWhenReceivePutRequestInCourseCodeURIWithCourseJson() throws Exception {

        String newFJ11 = new JSONObject()
                .put("code", "FJ-11")
                .put("description", "Object Orientation with JavaFX")
                .put("duration", 40).toString();

        mvc.perform(
                put("/courses/FJ-11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newFJ11))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is("FJ-11")))
                .andExpect(jsonPath("$.description", is("Object Orientation with JavaFX")))
                .andExpect(jsonPath("$.duration.seconds", is(144000)));
    }

    @Test
    public void shouldDeleteACourseWhenReceiveDeleteRequestInCourseCodeURI() throws Exception {
        mvc.perform(delete("/courses/FJ-11"))
                .andExpect(status().isOk());

        mvc.perform(get("/courses/FJ-11"))
                .andExpect(status().isNotFound());
    }
}
