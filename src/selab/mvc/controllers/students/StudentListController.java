package selab.mvc.controllers.students;

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

public class StudentListController extends Controller {

    private DataSet<Student> students;
    private DataSet<Enrollment> enrollments;
    public StudentListController(DataContext dataContext) {
        super(dataContext);
        students = dataContext.getStudents();
        enrollments = dataContext.getEnrollments();
    }

    @Override
    public View exec(String method, InputStream body) throws IOException {
        JSONObject result = new JSONObject();
        for(Student student : students.getAll()){
            ArrayList<String> courses = new ArrayList<String>();
            for (Enrollment enrollment : enrollments.getAll()) {
                if (enrollment.getStudent().getPrimaryKey().equals(student.getPrimaryKey())) {
                    courses.add(enrollment.getCourse().getTitle());
                }
            }
            student.setCourses(courses);
        }

        result.put("students", new JSONArray(students.getAll()));
        return new JsonView(result);
    }
}
