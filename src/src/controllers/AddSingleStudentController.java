package controllers;

import models.*;
import views.AddingSingleStudnetView;
import views.ClassHomePage;
import views.MainPanelView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by qqq58 on 2019/12/8  21:10 .
 */
public class AddSingleStudentController {
    private AddingSingleStudnetView addingSingleStudnetView;
    private JPanel parentPanel;
    private Course data;
    AddSingleStudentController() {
        addingSingleStudnetView = new AddingSingleStudnetView();
        parentPanel = MainPanelView.getParentPanel();
        parentPanel.removeAll();
        parentPanel.revalidate();
        parentPanel.repaint();
        parentPanel.add(addingSingleStudnetView, BorderLayout.CENTER);
        initController();
        bindData();

    }

    private void bindData() {
        data = LoggedData.getSelectedCourse();

        JComboBox j = addingSingleStudnetView.getSectionCombo();

        for (var c: LoggedData.getCourseSectionList())
        {
        //for (CourseSection cs : LoggedData.getSelectedCourse().getCourseSections()) {
            j.addItem(c);
        }
    }

    private void initController()
    {
        addingSingleStudnetView.getHomeButton().addActionListener(l -> backHome());
        addingSingleStudnetView.getBackButton().addActionListener(l -> back());
        addingSingleStudnetView.getAddButton().addActionListener(l -> create());

    }

    private void backHome()
    {
        CourseListController cLc = new CourseListController();
    }
    private void back()
    {
        CourseStudentController cHP = new CourseStudentController();
    }
    private void create() {
        String name = addingSingleStudnetView.getNameText().getText();
        String email = addingSingleStudnetView.getEmailText().getText();
        String buid = addingSingleStudnetView.getBUIDText().getText();



        String[] arr = name.split(" ");
        String firstName = arr[0];
        String LastName;
        if (arr.length == 1) {
            LastName = "";
        } else {
            LastName = arr[1];
        }

        Name name1 = new Name(firstName, LastName);
        Student student = new Student(name1, email, buid);
        var section = (CourseSection)addingSingleStudnetView.getSectionCombo().getSelectedItem();
        System.out.println(section.getName());

        int studentId = LoggedData.getDbManager().addStudent(student);
        student.setId(studentId);

        LoggedData.getDbManager().addEnrollment(studentId, false, section.getId(),LoggedData.getSelectedCourse().getId());

        if(LoggedData.getSelectedCourse().getCourseSections() == null ||LoggedData.getSelectedCourse().getCourseSections().size() == 0)
        {
            section.addStudent(student);
            LoggedData.getSelectedCourse().addSection(section);
        }else
        {
            for (var s : LoggedData.getSelectedCourse().getCourseSections() )
            {
                if (s.getId() == section.getId())
                {
                    s.addStudent(student);
                    break;
                }
            }
        }
        //LoggedData.getSelectedCourse().
        //LoggedData.getSelectedCourse().getCourseSections().get(section).addStudent(student);
        back();
        //System.out.println(student.getName()+" "+student.getBuID()+" "+student.getEmail()+" ");

    }

}
