package models;



import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qqq58 on 2019/12/7  19:23 .
 */
public class ImportExcel {
    InputStream file;

    public ImportExcel(String path) throws IOException {
        this.file = new FileInputStream(path);;

    }

    public List<Student> importE() throws IOException, InvalidFormatException {
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        List<Student> list = new ArrayList<>();
        for(int j = 0; j <= sheet.getLastRowNum(); j++){
                try {
                    Row row = sheet.getRow(j);
                    if (row != null) {
                        //System.out.println(row.getCell(0).getNumericCellValue());
                        int id = (int) row.getCell(0).getNumericCellValue();
                        String fullName = row.getCell(1).getStringCellValue();
                        String[] arr = fullName.split(" ");
                        String firstName = arr[0];
                        String LastName;
                        if (arr.length == 1) {
                            LastName = "";
                        } else {
                            LastName = arr[1];
                        }
                        Name name = new Name(firstName, LastName);
                        String email = row.getCell(2).getStringCellValue();
                        //System.out.println(row.getCell(3).getStringCellValue());
                        String buID = row.getCell(3).getStringCellValue();
                        Student student = new Student(id, name, email, buID);
                        list.add(student);
                    }
                }
                catch(Exception e){
                    continue;
                }




        }
        workbook.close();
        System.out.println(list.size() + "list size !!!!!!!");
        return list;
    }



}
