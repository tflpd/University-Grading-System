package controllers;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

/*import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/

//import views.CourseListView;
import views.CreateCourseView;
import views.MainPanelView;



public class CreateCourseController {

	private CreateCourseView createCourse;
	private JPanel parentPanel;

	public CreateCourseController()
	{
		createCourse = new CreateCourseView();

		parentPanel = MainPanelView.getParentPanel();
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(createCourse, BorderLayout.CENTER);

		initController();
	}

	private void initController()
	{
		createCourse.getCreateButton().addActionListener(l -> Create());
		createCourse.getImportButton().addActionListener(l -> ImportStudent());
	}

	private void Create()
	{
	
		
		ClassHomePageController cHPC = new ClassHomePageController(null);
	}

	private void ImportStudent()
	{
		chooseFile();
	}

	private void chooseFile() {
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
			System.out.println(keep);
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
