package models;

import java.util.ArrayList;

public class GradingSystem extends ProfessorsTool{
    private int id;
    private ArrayList<Course> activeCourses;
    private ArrayList<CourseTemplate> courseTemplates;

    public GradingSystem(int id, Professor ownerProfessor, ArrayList<Course> activeCourses, ArrayList<CourseTemplate> courseTemplates) {
        super(ownerProfessor);
        this.id = id;
        this.activeCourses = activeCourses;
        this.courseTemplates = courseTemplates;
    }

    public ArrayList<Course> getActiveCourses() {
        return activeCourses;
    }

    public ArrayList<CourseTemplate> getCourseTemplates() {
        return courseTemplates;
    }

    // Creates a new course NOT based in any previous one
    public void addNewCourse(String name, String semester, String year, ArrayList<Student> students){
        CourseSection courseSection = new CourseSection(0,"First section", students);
        ArrayList<CourseSection> courseSections = new ArrayList<CourseSection>();
        courseSections.add(courseSection);
        CourseTemplate newCourseTemplate = new CourseTemplate(0, name, semester, year);
        Course newCourse = new Course(0, name, semester, year, courseSections, newCourseTemplate);

        courseTemplates.add(newCourseTemplate);
        activeCourses.add(newCourse);
    }

    // Creates a new course based on the template courseTemplate
    public void addNewCourse(String name, String semester, String year, ArrayList<Student> students, CourseTemplate courseTemplate){
        CourseSection courseSection = new CourseSection(0,"First section", students);
        ArrayList<CourseSection> courseSections = new ArrayList<CourseSection>();
        courseSections.add(courseSection);
        CourseTemplate newCourseTemplate = new CourseTemplate(0, name, semester, year, courseTemplate);
        Course newCourse = new Course(0, name, semester, year, courseSections, newCourseTemplate);

        courseTemplates.add(newCourseTemplate);
        activeCourses.add(newCourse);
    }
    
}
