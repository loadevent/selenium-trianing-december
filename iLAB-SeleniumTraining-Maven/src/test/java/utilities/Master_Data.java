package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.util.Random;


public class Master_Data {

    static String filePath = "src/test/resources/HRM New Employee Data.xlsx";
    public static Employee getDataRow(){
        File myFile = new File(filePath);
        FileInputStream inputStream = null;
        Employee objEmp = new Employee();
        try{
            inputStream = new FileInputStream(myFile);
            //create a workbook
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            //create a spread
            XSSFSheet sheet = wb.getSheetAt(0);

            Random rnd = new Random();
            int rowNum = (rnd.nextInt((sheet.getLastRowNum() - 1) -1 ) + 1);
            System.out.println(rowNum);

            XSSFRow row = sheet.getRow(rowNum);


            for (Cell myCell : row){
                switch (myCell.getCellType()){
                    case STRING:
                        objEmp.setFirstname(row.getCell(0).getStringCellValue())
                                .setMiddlename(row.getCell(1).getStringCellValue())
                                .setLastname(row.getCell(2).getStringCellValue())
                                .setUsername(row.getCell(3).getStringCellValue())
                                .setPassword(row.getCell(4).getStringCellValue());
                        break;
                }
            }


        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }

        return objEmp;
    }
}
