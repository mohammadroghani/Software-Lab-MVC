package selab.mvc.models.entities;

import selab.mvc.models.Model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Student implements Model {
    private String name;
    private String studentNo;
    private ArrayList<String> courses;
    private float average;

    @Override
    public String getPrimaryKey() {
        return this.studentNo;
    }

    public void setName(String value) { this.name = value; }
    public String getName() { return this.name; }

    public void setStudentNo(String value) {
        if (!validateStudentNo(value))
            throw new IllegalArgumentException("The format is not correct");

        this.studentNo = value;
    }
    public String getStudentNo() { return this.studentNo; }

    public float getAverage() {
        return this.average;
    }

    public void setAverage(float average){
        this.average = average;
    }

    public String getCourses() {
        if(courses.size() == 0) {
            return "-";
        }
        String result = "";
        for(String course : courses){
            result = result + course + ", ";
        }
        return result.substring(0, result.length() - 2);
    }

    public void setCourses(ArrayList<String> courses){
        this.courses = courses;
    }

    /**
     *
     * @param studentNo Student number to be checeked
     * @return true, if the format of the student number is correct
     */
    private boolean validateStudentNo(String studentNo) {
        Pattern pattern = Pattern.compile("^[8-9]\\d{7}$");
        return pattern.matcher(studentNo).find();
    }
}
