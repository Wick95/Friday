package com.surge.Friday.tools.excel;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class test {
    public static void main(String[] args) throws Exception {
        File file2022 = new File("/Users/liubojin/Desktop/数据示例及对应关系.xlsx");
        HashMap<String, List<List<String>>> excelList2022 = ReadExcelFragment.readBigExcelAllBySheet(file2022);

        System.out.println(132);
    }
}
