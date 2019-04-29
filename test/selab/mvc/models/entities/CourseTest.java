package selab.mvc.models.entities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mohammad on 4/29/2019 AD.
 */
public class CourseTest {
    @Test
    public void setAverage() throws Exception {
        Course course = new Course();
        course.setAverage((float)4.5);
        assertEquals(course.getAverage(), 4.5, 0.00001);
    }

}