package selab.mvc.controllers.courses;

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
import java.util.HashMap;
import java.util.Map;

public class RemoveCourseController extends Controller {

    private DataSet<Course> courses;
    private DataSet<Enrollment> enrollments;
    public RemoveCourseController(DataContext dataContext) {
        super(dataContext);
        courses = dataContext.getCourses();
        enrollments = dataContext.getEnrollments();
    }

    @Override
    public View exec(String method, InputStream body) throws IOException {
        if (!method.equals("POST"))
            throw new IOException("Method not supported");

        JSONObject input = readJson(body);
        String courseNumber = input.getString("courseNo");

        Course course = this.dataContext.getCourses().get(courseNumber);
        ArrayList<Enrollment> deletedEnrollment = new ArrayList<Enrollment>();
        for(Enrollment enrollment : enrollments.getAll()){
            if(enrollment.getCourse().getPrimaryKey().equals(course.getPrimaryKey()))
                deletedEnrollment.add(enrollment);
        }
        for(Enrollment enrollment : deletedEnrollment)
            this.dataContext.getEnrollments().delete(enrollment);
        this.dataContext.getCourses().delete(course);

        JSONObject resultJson = new JSONObject();
        resultJson.put("success", "true");
        return new JsonView(resultJson);
    }

    @Override
    protected View getExceptionView(Exception exception) {
        Map<String, String> result = new HashMap<>();
        result.put("message", exception.getMessage());
        return new JsonView(new JSONObject(result));
    }
}
