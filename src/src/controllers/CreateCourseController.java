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


		var tempList = LoggedData.getGrading().getCourseTemplates();
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




	}

	private void Create()
	{
		//ArrayList<Student> studentList= new ArrayList<Student>();
	    if (importedStudents == null)
		{
			importedStudents = new ArrayList<Student>();
		}

	    //System.out.println("Selected template "+template);

		if (createCourse.getTemplateList().getSelectedObjects().length > 0)
		{
			var template = (CourseTemplate) createCourse.getTemplateList().getSelectedObjects()[0];
			LoggedData.setSelectedCourse(LoggedData.getGrading().addNewCourse(createCourse.getNameText().getText(),
					createCourse.getSemesterText().getText(), createCourse.getYearText().getText(), importedStudents, template));
		}
		else
		{
			LoggedData.setSelectedCourse(LoggedData.getGrading().addNewCourse(createCourse.getNameText().getText(),
					createCourse.getSemesterText().getText(), createCourse.getYearText().getText(), importedStudents));
		}

        //to do
		// Add to database
		//

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
//			for(Student student : importExcel.importE()){
//				System.out.println(student.getBuID());
//			}
			importedStudents = new ArrayList<>(list);
			//for(Student student : importedStudents){
			//	System.out.println(student.getBuID());
			//}


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
