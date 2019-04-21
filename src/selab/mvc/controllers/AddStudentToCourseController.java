package selab.mvc.controllers;

import org.json.JSONObject;
import selab.mvc.models.DataContext;
import selab.mvc.models.entities.Course;
import selab.mvc.models.entities.Enrollment;
import selab.mvc.models.entities.Student;
import selab.mvc.views.JsonView;
import selab.mvc.views.View;

import java.io.IOException;
import java.io.InputStream;

public class AddStudentToCourseController extends Controller {

    public AddStudentToCourseController(DataContext dataContext) {
        super(dataContext);
    }

    @Override
    public View exec(String method, InputStream body) throws IOException {
        if (!method.equals("POST"))
            throw new IOException("Method not supported");

        JSONObject input = readJson(body);
        String studentNumber = input.getString("studentNo");
        String courseNumber = input.getString("courseNo");
        String point = input.getString("points");

        Student student = this.dataContext.getStudents().get(studentNumber);
        Course course = this.dataContext.getCourses().get(courseNumber);
        Enrollment enrollment = new Enrollment(course, student, point);
        this.dataContext.getEnrollments().add(enrollment);


        JSONObject resultJson = new JSONObject();
        resultJson.put("success", "true");
        return new JsonView(resultJson);

    }
}
