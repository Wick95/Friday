package com.surge.Friday.tools.excel;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.surge.Friday.tools.excel.ReadExcelFragment.writeBigExcel;

public class Application4 {

    private static List<String> title = new ArrayList<>();

    private static final int yearPosition = 3;
    private static final int countryNamePosition = 8;
    private static final int flowDesPosition = 10;
    private static final int hsKindPosition = 17;
    private static final int cmdCodePosition = 20;
    private static final int cmdValuePosition = 43;

    public static void main(String[] args) throws Exception {

        String[] countrySet = new String[]{"China", "France", "Germany", "India", "Italy", "Japan", "Mexico", "Netherlands", "United Kingdom", "USA"};

        String targetYear = "1992";

        for (String country : countrySet) {
            tempVersion(country, targetYear);
        }
    }

    private static void tempVersion(String targetCountry, String targetYear) throws Exception {
        String[] countrySet = new String[]{"China", "France", "Germany", "India", "Italy", "Japan", "Mexico", "Netherlands", "United Kingdom", "USA"};

        String baseUrl = "/Users/liubojin/Downloads/数据处理0624/";
        String[] yearSet = new String[]{"2022-2011", "2010-1999", "1998-1992"};
        String[] transSet = new String[]{"Import", "Export"};

        String badStringSplit = "?,";
        String tempStringSplit = "%";


        List<List<String>> targetDataList = new ArrayList<>();


        //h0Code-年份，primaryVlue总和
        Map<String, BigDecimal> totalPrimaryValueMap = new HashMap<>();
        //h0Code-年份，某一个国家primaryVlue
        Map<String, BigDecimal> primaryValueMap = new HashMap<>();


        //读取HS数据
        HashMap<String, List<List<String>>> hsMappingData = ReadExcelFragment.readBigExcelAllBySheet(new File(baseUrl + "H0.xlsx"));
        //HOcode转换集合
        Map<String, Map<String, String>> h0CodeMap = new HashMap<>();
        for (Map.Entry<String, List<List<String>>> sheet : hsMappingData.entrySet()) {
            Map<String, String> toHs0 = sheet.getValue().stream().collect(Collectors.toMap(ele -> ele.get(0), ele -> ele.get(1), (k1, k2) -> k2));
            h0CodeMap.put(sheet.getKey(), toHs0);
        }
        // 处理映射关系
        HashMap<String, List<List<String>>> mappingData = ReadExcelFragment.readBigExcelAllBySheet(new File(baseUrl + "数据示例及对应关系.xlsx"));
        Map<String, List<String>> hs0ToBec4Map = processMappingData(mappingData, "h0对应bec4");
        Map<String, List<String>> hs0ToIsic3Map = processMappingData(mappingData, "h0对应isic3");
        Map<String, List<String>> middleMap = processMappingData(mappingData, "对应中间品");
        Map<String, List<String>> middle2Map = processMappingData(mappingData, "对应中间品2");
        Map<String, List<String>> categoryMap = processMappingData(mappingData, "对应大类");

        //excel数据集合 国家，全量数据
//        Map<String, List<List<String>>> excelDataMap = new HashMap<>();

        //加载数据
        for (String countryName : countrySet) {
            List<List<String>> countryData = new ArrayList<>();
            for (String year : yearSet) {
                for (String trans : transSet) {
                    List<List<String>> excelList = readExcelDataWithoutTitle(getFileUrl(baseUrl, countryName, year, trans));
                    countryData.addAll(excelList);
//                    if (excelDataMap.containsKey(country)) {
//                        List<List<String>> oldData = excelDataMap.get(country);
//                        oldData.addAll(excelList);
//                    } else {
//                        excelDataMap.put(country, excelList);
//
                }
            }
            for (List<String> row : countryData) {
                //1.去除乱码
                if (row.get(30).contains(badStringSplit)) {
                    String badString = row.get(30);
                    badString = badString.replaceAll("\\" + badStringSplit, tempStringSplit);
                    badString = badString.replaceAll("\"", "");
                    String QtyUnitAbbr = badString.split(tempStringSplit)[0];
                    String Qty = badString.split(tempStringSplit)[1];

                    row.add(30, QtyUnitAbbr);
                    row.set(31, Qty);
                }

                String year = row.get(yearPosition);
                String country = row.get(countryNamePosition);
                String flow = row.get(flowDesPosition);
                String hsKind = row.get(hsKindPosition);
                String hsCode = row.get(cmdCodePosition);
                BigDecimal primaryValue = new BigDecimal(row.get(cmdValuePosition));

                //2.转换H0code
                if (!hsKind.equals("H0")) {
                    hsCode = h0CodeMap.get(hsKind).get(hsCode) == null ? "" : h0CodeMap.get(hsKind).get(hsCode);
                }

                //转换H0Code
                String cashKey = year + country + flow + hsCode;
                String cashKeyWithout = year + flow + hsCode;


                //3.存储总和PrimaryVlue 格式H0Code-Export-年代
                if (totalPrimaryValueMap.containsKey(cashKeyWithout)) {
                    totalPrimaryValueMap.put(cashKeyWithout, totalPrimaryValueMap.get(cashKeyWithout).add(primaryValue));
                } else {
                    totalPrimaryValueMap.put(cashKeyWithout, primaryValue);
                }
                //4.如果是目标国家，存储单个国家PrimaryValue， 格式H0Code-年代
                if (country.equals(targetCountry)) {
                    if (primaryValueMap.containsKey(cashKey)) {
                        primaryValueMap.put(cashKey, primaryValueMap.get(cashKey).add(primaryValue));
                    } else {
                        primaryValueMap.put(cashKey, primaryValue);
                    }
                }

                //5.如果是目标年份，进行存储
                if (country.equals(targetCountry) && year.equals(targetYear)) {
                    targetDataList.add(row);
                }
            }
        }

        title.add("bec4");
        title.add("中间品");
        title.add("3级中间品");
        title.add("5级中间品");
        title.add("isic3");
        title.add("isic大类");
        title.add("hs6");
        //6.遍历年份，进行组装本国primaryValue和十国
        for (int i = 1992; i <= 2022; i++) {
            title.add(i + "年" + targetCountry + "PrimaryValue");
            title.add(i + "年PrimaryValue总和");
        }

        //6.对目标集合进行遍历
        for (List<String> row : targetDataList) {

            String year = row.get(yearPosition);
            String country = row.get(countryNamePosition);
            String flow = row.get(flowDesPosition);
            String hsKind = row.get(hsKindPosition);
            String hsCode = row.get(cmdCodePosition);
            BigDecimal primaryValue = new BigDecimal(row.get(cmdValuePosition));

            //6.组装基础字段
            String bec4 = hs0ToBec4Map.get(hsCode) == null ? "" : hs0ToBec4Map.get(hsCode).get(1);
            String isic3 = hs0ToIsic3Map.get(hsCode) == null ? "" : hs0ToIsic3Map.get(hsCode).get(1);
            String middleName = middleMap.get(bec4) == null ? "" : middleMap.get(bec4).get(1);
            String middle3Name = middle2Map.get(bec4) == null ? "" : middle2Map.get(bec4).get(2);
            String middle5Name = middle2Map.get(bec4) == null ? "" : middle2Map.get(bec4).get(1);
            String isic3Category = categoryMap.get(isic3) == null ? "" : categoryMap.get(isic3).get(1);

            row.add(bec4);
            row.add(middleName);
            row.add(middle3Name);
            row.add(middle5Name);
            row.add(isic3);
            row.add(isic3Category);
            row.add("");

            //6.遍历年份，进行组装本国primaryValue和十国
            for (int i = 1992; i <= 2022; i++) {
                String cashKey = i + country + flow + hsCode;
                String cashKeyWithout = i + flow + hsCode;

                row.add(primaryValueMap.get(cashKey) == null ? "" : primaryValueMap.get(cashKey).toString());
                row.add(totalPrimaryValueMap.get(cashKeyWithout) == null ? "" : totalPrimaryValueMap.get(cashKeyWithout).toString());

            }

            int[] positions = {19, 32, 36, 38, 40, 45, 46};

            for (int position : positions) {
                if (row.get(position).equals("1")) {
                    row.set(position, "TRUE");
                } else if (row.get(position).equals("0")) {
                    row.set(position, "FALSE");
                }
            }
        }
        HashMap<String, List<List<String>>> fileContent = new HashMap<>();

        targetDataList.add(0, title);
        fileContent.put(targetYear + "年" + targetCountry + "数据计算结果", targetDataList);

        writeBigExcel(baseUrl + "计算结果/" + targetYear + "年" + targetCountry + "数据计算结果.xlsx", fileContent);
    }

    public static String getFileUrl(String baseUrl, String country, String year, String export) {
        return baseUrl + country + "/" + country + year + export + ".xlsx";
    }

    private static List<List<String>> readExcelDataWithoutTitle(String filePath) throws Exception {
        File file = new File(filePath);
        List<List<String>> excelData = ReadExcelFragment.readBigExcelAll(file);
        title = excelData.get(0);
        excelData.remove(0);
        return excelData;
    }

    private static Map<String, List<String>> processMappingData(HashMap<String, List<List<String>>> mappingData, String sheetName) {
        List<List<String>> mappingSheetData = mappingData.get(sheetName);
        return mappingSheetData.stream().collect(Collectors.toMap(ele -> ele.get(0), ele -> ele, (k1, k2) -> k2));
    }
}

