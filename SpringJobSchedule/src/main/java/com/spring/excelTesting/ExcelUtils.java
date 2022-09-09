package com.spring.excelTesting;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelUtils {
    public List<EntityFromExcel> readFile(MultipartFile file) throws IOException {
        int getDataStartAt = 3;
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        List<EntityFromExcel> entityFromExcels = new ArrayList<>();


        int totalRow = sheet.getPhysicalNumberOfRows();
        int totalColumn = 0;
        if (rowIterator.hasNext()) {
            Row headerRow = rowIterator.next();
            totalColumn = headerRow.getPhysicalNumberOfCells();
        }

        for (int i = getDataStartAt; i <= totalRow; i++) {
            List<String> properties = new ArrayList<>();
            for (int j = 0; j <= totalColumn; j++) {
                int size = properties.size();
                if (properties.size() == totalColumn) {
                    EntityFromExcel entityFromExcel = new EntityFromExcel(properties.get(0), properties.get(1), properties.get(2), properties.get(3));
                    entityFromExcels.add(entityFromExcel);
                }
                if (size != 4) {
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        break;
                    }
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        break;
                    }
                    if (cell.getCellType().equals(CellType.NUMERIC)) {
                        properties.add(String.valueOf(cell.getNumericCellValue()));
                    }
                    if (cell.getCellType().equals(CellType.STRING)) {
                        properties.add(cell.getStringCellValue());
                    }
                }
            }
        }

//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            Iterator<Cell> cellIterator = row.cellIterator();
//
//            while (cellIterator.hasNext()) {
//                if (properties.size() == (totalColumn - 1)) {
//                    EntityFromExcel entityFromExcel = new EntityFromExcel(properties.get(0), properties.get(1), properties.get(2),properties.get(3));
//                    entityFromExcels.add(entityFromExcel);
//                }
//                Cell cell = cellIterator.next();
//                properties.add(cell.getStringCellValue());
//            }
//        }

        return entityFromExcels;
    }
}
