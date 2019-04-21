package selab.mvc.controllers.courses;

import org.json.JSONArray;
import org.json.JSONObject;
import selab.mvc.controllers.Controller;
import selab.mvc.models.DataContext;
import selab.mvc.models.DataSet;
import selab.mvc.models.entities.Course;
import selab.mvc.models.entities.Enrollment;
import selab.mvc.models.entities.Student;
import selab.mvc.views.JsonView;
import selab.mvc.views.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CourseListController extends Controller {

    private DataSet<Course> courses;
    private DataSet<Enrollment> enrollments;
    public CourseListController(DataContext dataContext) {
        super(dataContext);
        courses = dataContext.getCourses();
        enrollments = dataContext.getEnrollments();
    }

    @Override
    public View exec(String method, InputStream body) throws IOException {
        JSONObject result = new JSONObject();
        for(Course course : courses.getAll()){
            ArrayList<String> students = new ArrayList<String>();
            for (Enrollment enrollment : enrollments.getAll()) {
                if (enrollment.getCourse().getPrimaryKey().equals(course.getPrimaryKey())) {
                    students.add(enrollment.getStudent().getName());
                }
            }
            course.setStudents(students);
        }

        result.put("courses", new JSONArray(courses.getAll()));
        return new JsonView(result);
    }
}
