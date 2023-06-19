package com.surge.Friday.tools.excel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadExcel {

    public static void main(String[] args) {
        ReadExcel obj = new ReadExcel();
        File file = new File("/Users/liubojin/Downloads/TradeData_6_16_2023_22_56_3.xls");
        List excelList = obj.readExcel(file);
        System.out.println("123");

    }

    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
    public List readExcel(File file) {
        List<List<String>> outerList = new ArrayList<>();
        try {
            // 创建输入流，读取Excel
            InputStream is = Files.newInputStream(Paths.get(file.getAbsolutePath()));

            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);

            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();

            //Sheet页维度
            for (int index = 0; index < sheet_size; index++) {
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List<String> innerList = new ArrayList<>();

                    String cmdCode = sheet.getCell(20, i).getContents();
                    String cmdDesc = sheet.getCell(21, i).getContents();
                    String primaryValue = sheet.getCell(43, i).getContents();

                    innerList.add(cmdCode);
                    innerList.add(cmdDesc);
                    innerList.add(primaryValue);

                    outerList.add(i, innerList);
                }
            }
        } catch (BiffException | IOException e) {
            e.printStackTrace();
        }

        return outerList;
    }
}