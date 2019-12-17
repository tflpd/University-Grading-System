package controllers;

import models.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import views.AddingSingleStudnetView;
import views.ClassHomePage;
import views.MainPanelView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qqq58 on 2019/12/8  21:10 .
 */
public class AddSingleStudentController {
    private AddingSingleStudnetView addingSingleStudnetView;
    private JPanel parentPanel;
    private Course data;
    private ArrayList<Student> importedStudents;

    AddSingleStudentController() {
        addingSingleStudnetView = new AddingSingleStudnetView();
        parentPanel = MainPanelView.getParentPanel();
        parentPanel.removeAll();
        parentPanel.revalidate();
        parentPanel.repaint();
        parentPanel.add(addingSingleStudnetView, BorderLayout.CENTER);
        initController();
        bindData();
        importedStudents = new ArrayList<Student>();
    }

    private void bindData() {
        data = LoggedData.getSelectedCourse();

        JComboBox j = addingSingleStudnetView.getSectionCombo();

        for (var c : LoggedData.getCourseSectionList()) {
            //for (CourseSection cs : LoggedData.getSelectedCourse().getCourseSections()) {
            j.addItem(c);
        }
    }

    private void initController() {
        addingSingleStudnetView.getHomeButton().addActionListener(l -> backHome());
        addingSingleStudnetView.getBackButton().addActionListener(l -> back());
        addingSingleStudnetView.getAddButton().addActionListener(l -> create());
        addingSingleStudnetView.getImportStudent().addActionListener(l -> importS());
    }

    private void backHome() {
        CourseListController cLc = new CourseListController();
    }

    private void back() {
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
        var section = (CourseSection) addingSingleStudnetView.getSectionCombo().getSelectedItem();
        System.out.println(section.getName());

        int studentId = LoggedData.getDbManager().addStudent(student);
        student.setId(studentId);

        LoggedData.getDbManager().addEnrollment(studentId, false, section.getId(), LoggedData.getSelectedCourse().getId());
        LoggedData.getSelectedCourse().addNewGrade(student);


        if (LoggedData.getSelectedCourse().getCourseSections() == null || LoggedData.getSelectedCourse().getCourseSections().size() == 0) {
            section.addStudent(student);
            LoggedData.getSelectedCourse().addSection(section);
        } else {
            boolean isSectionExist = false;
            for (var s : LoggedData.getSelectedCourse().getCourseSections()) {
                if (s.getId() == section.getId()) {
                    s.addStudent(student);
                    isSectionExist = true;
                    break;
                }
            }
            if (!isSectionExist) {
                section.addStudent(student);
                LoggedData.getSelectedCourse().addSection(section);
            }
        }
        //LoggedData.getSelectedCourse().
        //LoggedData.getSelectedCourse().getCourseSections().get(section).addStudent(student);
        back();
        //System.out.println(student.getName()+" "+student.getBuID()+" "+student.getEmail()+" ");

    }


    private void importS () {
        try {
            chooseFile();
        } catch (Exception ex) {

        }


        if (importedStudents == null) {
            importedStudents = new ArrayList<Student>();
        } else {
            for (var s : importedStudents) {
                int id = LoggedData.getDbManager().addStudent(s);
                s.setId(id);
                var section = (CourseSection) addingSingleStudnetView.getSectionCombo().getSelectedItem();
                int enrollmentId = LoggedData.getDbManager().addEnrollment(s.getId(), false, section.getId(), LoggedData.getSelectedCourse().getId());

                boolean isSectionExist = false;
                for (var sC : LoggedData.getSelectedCourse().getCourseSections()) {
                    if (sC.getId() == section.getId()) {
                        sC.addStudent(s);
                        isSectionExist = true;
                        break;
                    }
                }
                if (!isSectionExist) {
                    section.addStudent(s);
                    LoggedData.getSelectedCourse().addSection(section);
                }

            }
        }


        back();
    }


    private void chooseFile() throws IOException, InvalidFormatException {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            var keep = selectedFile.getName();
			/*try {
				read(selectedFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

            ImportExcel importExcel = new ImportExcel(selectedFile.getPath());
            List<Student> list = importExcel.importE();
            importedStudents = new ArrayList<>(list);

        }
    }
}
