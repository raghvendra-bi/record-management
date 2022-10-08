package com.bi.recordmanagement.helper;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.bi.recordmanagement.models.RecordColumn;
import com.bi.recordmanagement.models.RecordFile;
import com.bi.recordmanagement.models.RecordRow;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {


    //check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();
        System.out.print("111222222");
        System.out.print(contentType);
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
        		contentType.equals("application/vnd.ms-excel")) {
            return true;
        } else {
            return false;
        }

    }


    //convert excel to list of products

    public static RecordFile getRecordFileName(MultipartFile file) {
    	
    	RecordFile recordFile = new RecordFile();

        try {
        	InputStream is = file.getInputStream();
        	file.getOriginalFilename();
//            XSSFWorkbook workbook = new XSSFWorkbook(is);
//
//            XSSFSheet sheet = workbook.getSheet("data");
//            String name = sheet.getSheetName();
            recordFile.setName(file.getOriginalFilename());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordFile;

    }
    
    public static List<RecordRow> getRows(InputStream is) {
        List<RecordRow> list = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                while (cells.hasNext()) {
                	RecordRow p = new RecordRow();
                    Cell cell = cells.next();
                    DataFormatter dataFormatter = new DataFormatter();
                    String formattedCellStr = dataFormatter.formatCellValue(cell);
                    p.setRow(formattedCellStr);
                    list.add(p);
                }

                


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    
    
    public static List<RecordColumn> getColumns(InputStream is) {
        List<RecordColumn> list = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();
            

            while (iterator.hasNext()) {
                Row row = iterator.next();


                Iterator<Cell> cells = row.iterator();


                

                while (cells.hasNext()) {
                	RecordColumn p = new RecordColumn();
                    Cell cell = cells.next();
                    DataFormatter dataFormatter = new DataFormatter();
                    String formattedCellStr = dataFormatter.formatCellValue(cell);
                    p.setColumn(formattedCellStr);
                    list.add(p);
                }

                
                break;

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }


}
