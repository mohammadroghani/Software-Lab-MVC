package selab.mvc.models.entities;

import selab.mvc.models.Model;

/**
 * Created by mohammad on 4/21/2019 AD.
 */
public class Enrollment implements Model{
    private Course course;
    private Student student;
    private float point;

    public Enrollment(Course course, Student student, String point) {
        this.course = course;
        this.student = student;
        this.point = Float.parseFloat(point);
    }

    public float getEnrollmentPoint(){
        return this.point;
    }

    public Student getStudent(){
        return student;
    }

    public Course getCourse(){
        return course;
    }

    @Override
    public String getPrimaryKey() {
        return student.getPrimaryKey() + course.getPrimaryKey();
    }
}
