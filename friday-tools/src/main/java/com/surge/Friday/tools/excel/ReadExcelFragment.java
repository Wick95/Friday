package com.surge.Friday.tools.excel;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadExcelFragment {


    public static void main(String[] args) throws Exception {

        File file = new File("/Users/liubojin/Downloads/TradeData_6_17_2023_9_30_52.xlsx");

        List<List<String>> excelList = ReadExcelFragment.readBigExcelAll(file);

    }

    public static List<List<String>> readBigExcelAll(File file) throws Exception {
        List<List<String>> resultList = new ArrayList<>();
        int startRow = 0;
        while (true) {
            List<List<String>> excelList = ReadExcelFragment.readBigExcel(fileToMultipartFile(file), 0, startRow);
            if (excelList.size() > 0) {
                startRow += excelList.size();
                resultList.addAll(excelList);
            } else {
                break;
            }
        }
        return resultList;
    }

    public static HashMap<String, List<List<String>>> readBigExcelAllBySheet(File file) throws Exception {
        HashMap<String, List<List<String>>> resultList = new HashMap<>();

        InputStream inputStream = fileToMultipartFile(file).getInputStream();

        try (Workbook wk = StreamingReader.builder()
                .rowCacheSize(1000000)  //缓存到内存中的行数，默认是10
                .bufferSize(102400000)  //读取资源时，缓存到内存的字节大小，默认是1024
                .open(inputStream)) { //打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件
            for (int i = 0; i < wk.getNumberOfSheets(); i++) {

                Sheet sheet = wk.getSheetAt(i);
                List<List<String>> rowList = new ArrayList<>();
                //遍历所有的行（）
                for (Row row : sheet) {
                    //从设定的行开始取值
                    List<String> innerList = new ArrayList<>();

                    short minColIx = row.getFirstCellNum();
                    short maxColIx = row.getLastCellNum();
                    for (short colIx = minColIx; colIx < maxColIx; colIx++) {
                        Cell cell = row.getCell(colIx);
                        if (cell == null) {
                            innerList.add("");
                        } else {
                            innerList.add(cell.getStringCellValue());
                        }
                    }
                    rowList.add(innerList);
                }
                resultList.put(sheet.getSheetName(), rowList);
            }
        }

        return resultList;
    }

    /**
     * 大批量数据读取 15W以上
     * 思路：采用分段缓存，防止出现OOM的情况
     * 格式限制：必须使用xlsx的格式，调用前需判断格式
     * file：前台页面传递的文件
     * <p>
     * rowname：为读取的每个列起的名字。如excel文件列为 姓名，身份证，性别 ；此处可传字符串"name,card,sex"；
     * <p>
     * stasheetNum：读取的工作簿  从0开始
     * <p>
     * starowNum： 开始读取的行  从0开始
     * <p>
     * stacolumn：开始读取的列 从0开始
     * <p>
     * 返回值：List<Map<String,Object>>  将读取的内容 封装为到一个list中，并以map形式存放；
     *
     * @return
     * @throws Exception
     */
    public static List<List<String>> readBigExcel(MultipartFile file, int stasheetNum, int starowNum) throws
            Exception {
        //定义返回值
        List<List<String>> resultList = new ArrayList<>();

        InputStream inputStream = file.getInputStream();
        try (Workbook wk = StreamingReader.builder()
                .rowCacheSize(1000000)  //缓存到内存中的行数，默认是10
                .bufferSize(102400000)  //读取资源时，缓存到内存的字节大小，默认是1024
                .open(inputStream)) { //打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件
            Sheet sheet = wk.getSheetAt(stasheetNum);

            //遍历所有的行（）
            for (Row row : sheet) {
                if (row.getRowNum() < starowNum) {
                    continue;
                }

                //从设定的行开始取值
                List<String> innerList = new ArrayList<>();

                short minColIx = row.getFirstCellNum();
                short maxColIx = row.getLastCellNum();
                for (short colIx = minColIx; colIx < maxColIx; colIx++) {
                    Cell cell = row.getCell(colIx);
                    if (cell == null) {
                        innerList.add("");
                    } else {
                        innerList.add(cell.getStringCellValue());
                    }
                }

                resultList.add(innerList);

            }
        }
        return resultList;
    }

    /**
     * file转MultipartFile
     *
     * @param file file
     * @return MultipartFile
     */
    public static MultipartFile fileToMultipartFile(File file) {
        MultipartFile result = null;
        if (null != file) {
            try (FileInputStream input = new FileInputStream(file)) {
                result = new MockMultipartFile(file.getName().concat("temp"), file.getName(), "text/plain", input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void writeBigExcel(String fileUrl, HashMap<String, List<List<String>>> fileContent) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        for (Map.Entry<String, List<List<String>>> sheetContent : fileContent.entrySet()) {
            XSSFSheet sheet = workbook.createSheet(sheetContent.getKey());

            List<List<String>> value = sheetContent.getValue();
            int maxCount = value.size();
            for (int i = 0; i < maxCount; i++) {
                List<String> rowContent = value.get(0);
                value.remove(0);
                XSSFRow row = sheet.createRow(i);
                for (int j = 0; j < rowContent.size(); j++) {
                    row.createCell(j).setCellValue(rowContent.get(j));
                }
            }
        }

        File file = new File(fileUrl);
        //将文件保存到指定的位置
        try {
            workbook.write(Files.newOutputStream(file.toPath()));
            System.out.println("写入成功");
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
