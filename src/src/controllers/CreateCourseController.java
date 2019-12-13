package controllers;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

/*import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/

//import views.CourseListView;
import MySql.DBManager;
import models.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import views.CreateCourseView;
import views.MainPanelView;

public class CreateCourseController {

	private CreateCourseView createCourse;
	private JPanel parentPanel;
	private ArrayList<Student> importedStudents;

	public CreateCourseController()
	{
		createCourse = new CreateCourseView();

		parentPanel = MainPanelView.getParentPanel();
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(createCourse, BorderLayout.CENTER);

		var tempList = LoggedData.getGradingSystem().getCourseTemplates();
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addAll(tempList);
		createCourse.setTemplateList(model);

		initController();
		importedStudents = new ArrayList<Student>();
	}

	private void initController()
	{


		createCourse.getCreateButton().addActionListener(l -> Create());
		createCourse.getImportButton().addActionListener(l -> {
			try {
				ImportStudent();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
		});
		createCourse.getCancelButton().addActionListener(l -> Cancel());
	}

	private void Cancel()
	{
		CourseListController cLC = new CourseListController();
	}

	private void Create()
	{
		int templateId = 0;
	    if (importedStudents == null) {
			importedStudents = new ArrayList<Student>();
		}else
		{
			for (var s : importedStudents) {
				int id = LoggedData.getDbManager().addStudent(s);
				s.setId(id);
			}
		}


		if (createCourse.getTemplateList().getSelectedObjects().length > 0)
		{
			var template = (CourseTemplate) createCourse.getTemplateList().getSelectedObjects()[0];
			templateId = template.getId();
			LoggedData.setSelectedCourse(LoggedData.getGradingSystem().addNewCourse(createCourse.getNameText().getText(),
					createCourse.getSemesterText().getText(), createCourse.getYearText().getText(), importedStudents, template));
		}
		else
		{
			LoggedData.setSelectedCourse(LoggedData.getGradingSystem().addNewCourse(createCourse.getNameText().getText(),
					createCourse.getSemesterText().getText(), createCourse.getYearText().getText(), importedStudents));
			System.out.println("Template id " + LoggedData.getSelectedCourse().getName() +" template id "+LoggedData.getSelectedCourse().getCourseTemplate().getId());
		}

        //to do
		// Add to database
		//DBManager dbManager = new DBManager();
		try {
			if (templateId != 0) {
				LoggedData.getSelectedCourse().getCourseTemplate().setId(0);
			}
			templateId = LoggedData.getDbManager().addTemplateCourse(LoggedData.getSelectedCourse().getCourseTemplate());
			LoggedData.getSelectedCourse().getCourseTemplate().setId(templateId);
			System.out.println("template id"+ templateId);
			int courseId = LoggedData.getDbManager().addCourse(LoggedData.getSelectedCourse(), templateId, LoggedData.getProf().getId());
			LoggedData.getSelectedCourse().setId(courseId);

			if (importedStudents.size() > 0)
			{
				for (var s : importedStudents) {
					int id = LoggedData.getDbManager().addEnrollment(s.getId(), false, 1, courseId);
					s.setId(id);
				}
			}


		}catch (Exception ex)
		{
			ex.printStackTrace();
		}

		ClassHomePageController cHPC = new ClassHomePageController(LoggedData.getSelectedCourse().toString());
	}

	private void ImportStudent() throws IOException, InvalidFormatException {
		chooseFile();
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


	/*private void read(File file) throws IOException
	{
		//File excelFile = new File("contacts.xlsx");
	    FileInputStream fis = new FileInputStream(file);

	    // we create an XSSF Workbook object for our XLSX Excel File
	    XSSFWorkbook workbook = new XSSFWorkbook(fis);
	    // we get first sheet
	    XSSFSheet sheet = workbook.getSheetAt(0);

	    // we iterate on rows
	    Iterator<Row> rowIt = sheet.iterator();

	    while(rowIt.hasNext()) {
	      Row row = rowIt.next();

	      // iterate on cells for the current row
	      Iterator<Cell> cellIterator = row.cellIterator();

	      while (cellIterator.hasNext()) {
	        Cell cell = cellIterator.next();
	        System.out.print(cell.toString() + ";");
	      }

	      System.out.println();
	    }

	    workbook.close();
	    fis.close();
	  }*/
	

}
