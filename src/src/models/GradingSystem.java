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
    
    public GradingSystem(int id, Professor ownerProfessor) {
        super(ownerProfessor);
        this.id = id;
        this.activeCourses = null;
        this.courseTemplates = null;
    }

    public ArrayList<Course> getActiveCourses() {
        return activeCourses;
    }

    public ArrayList<CourseTemplate> getCourseTemplates() {
        return courseTemplates;
    }

    // Creates a new course NOT based in any previous one
    public Course addNewCourse(String name, String semester, String year, ArrayList<Student> students){
        var defaultSection = LoggedData.getCourseSectionList().get(0);
        CourseSection courseSection = new CourseSection(defaultSection.getId(), defaultSection.getName(), students);
        //CourseSection courseSection = new CourseSection(0,"First section", students);
        ArrayList<CourseSection> courseSections = new ArrayList<CourseSection>();
        courseSections.add(courseSection);
        CourseTemplate newCourseTemplate = new CourseTemplate(0, name, semester, year);
        Course newCourse = new Course(0, name, semester, year, courseSections, newCourseTemplate);

        courseTemplates.add(newCourseTemplate);
        activeCourses.add(newCourse);
        
        return newCourse;
    }

    // Creates a new course based on the template courseTemplate
    public Course addNewCourse(String name, String semester, String year, ArrayList<Student> students, CourseTemplate courseTemplate){
        //CourseSection courseSection = new CourseSection(0,"First section", students);
        var defaultSection = LoggedData.getCourseSectionList().get(0);
        CourseSection courseSection = new CourseSection(defaultSection.getId(), defaultSection.getName(), students);
        ArrayList<CourseSection> courseSections = new ArrayList<CourseSection>();
        courseSections.add(courseSection);
        CourseTemplate newCourseTemplate = new CourseTemplate(0, name, semester, year, courseTemplate);
        Course newCourse = new Course(0, name, semester, year, courseSections, newCourseTemplate);

        courseTemplates.add(newCourseTemplate);
        activeCourses.add(newCourse);
        
        return newCourse;
    }




    public void deleteCourse(Course courseToDelete){
        for (Course course:activeCourses){
            if (course.getId() == courseToDelete.getId()){
                activeCourses.remove(course);
                return;
            }
        }
    }

}
