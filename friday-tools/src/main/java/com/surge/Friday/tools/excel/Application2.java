package com.surge.Friday.tools.excel;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.surge.Friday.tools.excel.ReadExcelFragment.writeBigExcel;

public class Application2 {

    private static List<String> title = new ArrayList<>();

    private static final int flowDesPosition = 10;
    private static final int cmdCodePosition = 20;
    private static final int cmdValuePosition = 43;

    public static void main(String[] args) throws Exception {
        String titleCountry = "印度";

        List<List<String>> excelList2011 = readExcelDataWithoutTitle("/Users/liubojin/Desktop/数据计算处理0619/" + titleCountry + "/2011.xlsx");
        List<List<String>> excelList2011zhong = readExcelDataWithoutTitle("/Users/liubojin/Desktop/数据计算处理0619/" + titleCountry + "/" + titleCountry + "2011.xlsx");

        List<List<String>> excelList2022 = readExcelDataWithoutTitle("/Users/liubojin/Desktop/数据计算处理0619/" + titleCountry + "/2022.xlsx");
        List<List<String>> excelList2022zhong = readExcelDataWithoutTitle("/Users/liubojin/Desktop/数据计算处理0619/" + titleCountry + "/" + titleCountry + "2022.xlsx");

        HashMap<String, List<List<String>>> hsMappingData = ReadExcelFragment.readBigExcelAllBySheet(new File("/Users/liubojin/Desktop/数据计算处理0619/" + titleCountry + "/HS2022toHS2007Conversion.xlsx"));
        List<List<String>> hsMappingDataList = hsMappingData.get("HS2022-HS2007");
        Map<String, List<String>> hs6ToHs3 = hsMappingDataList.stream().collect(Collectors.toMap(ele -> ele.get(0), ele -> ele, (k1, k2) -> k2));

//        List<List<String>> hs2022To1992List = hsMappingData.get("HS2022-HS1992");
//        Map<String, List<String>> hs2022To1992 = hs2022To1992List.stream().collect(Collectors.toMap(ele -> ele.get(0), ele -> ele, (k1, k2) -> k2));
//
//        List<List<String>> hs2011To1992List = hsMappingData.get("HS2011-HS1992");
//        Map<String, List<String>> hs2011To1992 = hs2011To1992List.stream().collect(Collectors.toMap(ele -> ele.get(0), ele -> ele, (k1, k2) -> k2));
//
//        List<List<String>> hs2001To1992List = hsMappingData.get("HS2001-HS1992");
//        Map<String, List<String>> hs2001To1992 = hs2001To1992List.stream().collect(Collectors.toMap(ele -> ele.get(0), ele -> ele, (k1, k2) -> k2));


        List<List<String>> opList = excelList2011zhong;


        HashMap<String, BigDecimal> all2022MapImport = new HashMap<>();
        HashMap<String, BigDecimal> all2022MapExport = new HashMap<>();
        processData(excelList2022, all2022MapImport, all2022MapExport, hs6ToHs3);

        HashMap<String, BigDecimal> zhong2022MapImport = new HashMap<>();
        HashMap<String, BigDecimal> zhong2022MapExport = new HashMap<>();
        processData(excelList2022zhong, zhong2022MapImport, zhong2022MapExport, hs6ToHs3);

        HashMap<String, BigDecimal> all2011MapImport = new HashMap<>();
        HashMap<String, BigDecimal> all2011MapExport = new HashMap<>();
        processData(excelList2011, all2011MapImport, all2011MapExport, hs6ToHs3);

        HashMap<String, BigDecimal> zhong2011MapImport = new HashMap<>();
        HashMap<String, BigDecimal> zhong2011MapExport = new HashMap<>();
        processData(opList, zhong2011MapImport, zhong2011MapExport, hs6ToHs3);

        // 计算2011年中国所占全球比例
        HashMap<String, BigDecimal> zhong2011RateMapImport = calculateRate(zhong2011MapImport, all2011MapImport);
        HashMap<String, BigDecimal> zhong2011RateMapExport = calculateRate(zhong2011MapExport, all2011MapExport);

        // 计算2022年中国所占全球比例
        HashMap<String, BigDecimal> zhong2022RateMapImport = calculateRate(zhong2022MapImport, all2022MapImport);
        HashMap<String, BigDecimal> zhong2022RateMapExport = calculateRate(zhong2022MapExport, all2022MapExport);

        // 处理映射关系
        HashMap<String, List<List<String>>> mappingData = ReadExcelFragment.readBigExcelAllBySheet(new File("/Users/liubojin/Desktop/数据计算处理0619/" + titleCountry + "/数据示例及对应关系.xlsx"));
        Map<String, List<String>> hs6ToBec4Map = processMappingData(mappingData, "hs6对应bec4");
        Map<String, List<String>> hs3ToBec4Map = processMappingData(mappingData, "hs3对应bec4");
        Map<String, List<String>> hs6ToIsic3Map = processMappingData(mappingData, "hs6对应isic3");
        Map<String, List<String>> hs3ToIsic3Map = processMappingData(mappingData, "hs3对应isic3");
        Map<String, List<String>> hs0ToBec4Map = processMappingData(mappingData, "h0对应bec4");
        Map<String, List<String>> hs0ToIsic3Map = processMappingData(mappingData, "h0对应isic3");


        Map<String, List<String>> middleMap = processMappingData(mappingData, "对应中间品");
        Map<String, List<String>> middle2Map = processMappingData(mappingData, "对应中间品2");
        Map<String, List<String>> categoryMap = processMappingData(mappingData, "对应大类");

        String badStringSplit = "?,";
        String tempStringSplit = "%";

        //2022数据处理
        for (List<String> row : opList) {

            //去除乱码
            if (row.get(30).contains(badStringSplit)) {
                String badString = row.get(30);
                badString = badString.replaceAll("\\" + badStringSplit, tempStringSplit);
                badString = badString.replaceAll("\"", "");
                String QtyUnitAbbr = badString.split(tempStringSplit)[0];
                String Qty = badString.split(tempStringSplit)[1];

                row.add(30, QtyUnitAbbr);
                row.set(31, Qty);
            }

            String hsVersion = row.get(cmdCodePosition - 3);
            String cmdCode = row.get(cmdCodePosition);
            String flowDesc = row.get(flowDesPosition);

            String bec4 = "";
            String isic3 = "";
            if (Objects.equals(hsVersion, "H6")) {
                bec4 = hs6ToBec4Map.get(cmdCode).get(1);
                isic3 = hs6ToIsic3Map.get(cmdCode).get(1);
            } else if (Objects.equals(hsVersion, "H3")) {
                bec4 = hs3ToBec4Map.get(cmdCode) == null ? "" : hs3ToBec4Map.get(cmdCode).get(1);
                isic3 = hs3ToIsic3Map.get(cmdCode) == null ? "" : hs3ToIsic3Map.get(cmdCode).get(1);
            } else if (Objects.equals(hsVersion, "H0")) {
                bec4 = hs0ToBec4Map.get(cmdCode) == null ? "" : hs0ToBec4Map.get(cmdCode).get(1);
                isic3 = hs0ToIsic3Map.get(cmdCode) == null ? "" : hs0ToIsic3Map.get(cmdCode).get(1);
            }

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

            if (Objects.equals(flowDesc, "Import")) {

                BigDecimal zhong2022Rate = zhong2022RateMapImport.get(cmdCode) == null ? BigDecimal.ZERO : zhong2022RateMapImport.get(cmdCode);
                BigDecimal zhong2011Rate = zhong2011RateMapImport.get(cmdCode) == null ? BigDecimal.ZERO : zhong2011RateMapImport.get(cmdCode);
                BigDecimal different = zhong2011Rate.subtract(zhong2022Rate);

                BigDecimal all2011 = all2011MapImport.get(cmdCode);
                BigDecimal zhong2022Value = zhong2022MapImport.get(cmdCode);
                BigDecimal all2022 = all2022MapImport.get(cmdCode);

                row.add(zhong2011Rate.toString());
                row.add(different.toString());


                row.add(all2011 == null ? "" : all2011.toString());
                row.add(zhong2022Value == null ? "" : zhong2022Value.toString());
                row.add(all2022 == null ? "" : all2022.toString());

            } else if (Objects.equals(flowDesc, "Export")) {

                BigDecimal zhong2022Rate = zhong2022RateMapExport.get(cmdCode) == null ? BigDecimal.ZERO : zhong2022RateMapExport.get(cmdCode);
                BigDecimal zhong2011Rate = zhong2011RateMapExport.get(cmdCode) == null ? BigDecimal.ZERO : zhong2011RateMapExport.get(cmdCode);
                BigDecimal different = zhong2011Rate.subtract(zhong2022Rate);

                BigDecimal all2011 = all2011MapExport.get(cmdCode);
                BigDecimal zhong2022Value = zhong2022MapExport.get(cmdCode);
                BigDecimal all2022 = all2022MapExport.get(cmdCode);

                row.add(zhong2011Rate.toString());
                row.add(different.toString());


                row.add(all2011 == null ? "" : all2011.toString());
                row.add(zhong2022Value == null ? "" : zhong2022Value.toString());
                row.add(all2022 == null ? "" : all2022.toString());

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
        title.add("bec4");
        title.add("中间品");
        title.add("3级中间品");
        title.add("5级中间品");
        title.add("isic3");
        title.add("isic大类");
        title.add("hs6");

        title.add("2011年" + titleCountry + "PrimaryValue占全球比例");
        title.add("变化趋势（对比2022）");
        title.add("2011年全球PrimaryValue");
        title.add("2022年" + titleCountry + "PrimaryValue");
        title.add("2022年全球PrimaryValue");


        opList.add(0, title);
        fileContent.put("1992年" + titleCountry + "数据计算结果", opList);

        writeBigExcel("/Users/liubojin/Desktop/2011年" + titleCountry + "数据计算结果.xlsx", fileContent);
    }

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    private static List<List<String>> readExcelDataWithoutTitle(String filePath) throws Exception {
        File file = new File(filePath);
        List<List<String>> excelData = ReadExcelFragment.readBigExcelAll(file);
        title = excelData.get(0);
        excelData.remove(0);
        return excelData;
    }

    private static void processData(List<List<String>> excelData, HashMap<String, BigDecimal> importData, HashMap<String, BigDecimal> exportData, Map<String, List<String>> hsMappingMap) {
        for (List<String> row : excelData) {
            String hsCode = row.get(cmdCodePosition);
            BigDecimal primaryValue = new BigDecimal(row.get(cmdValuePosition));

            if (hsMappingMap != null) {
                hsCode = hsMappingMap.get(row.get(cmdCodePosition)) == null ? "" : hsMappingMap.get(row.get(cmdCodePosition)).get(1);
            }

            if (row.get(flowDesPosition).equals("Import")) {
                if (importData.containsKey(hsCode)) {
                    primaryValue = primaryValue.add(importData.get(hsCode));
                }
                importData.put(hsCode, primaryValue);
            } else if (row.get(flowDesPosition).equals("Export")) {
                if (exportData.containsKey(hsCode)) {
                    primaryValue = primaryValue.add(exportData.get(hsCode));
                }
                exportData.put(hsCode, primaryValue);
            }
        }
    }

    private static HashMap<String, BigDecimal> calculateRate(HashMap<String, BigDecimal> targetData, HashMap<String, BigDecimal> globalData) {
        HashMap<String, BigDecimal> rateMap = new HashMap<>();
        for (Map.Entry<String, BigDecimal> entry : targetData.entrySet()) {
            if (globalData.get(entry.getKey()).equals(BigDecimal.ZERO)) {
                rateMap.put(entry.getKey(), BigDecimal.ZERO);
            } else {
                rateMap.put(entry.getKey(), entry.getValue().divide(globalData.get(entry.getKey()), 6, RoundingMode.HALF_UP));
            }
        }
        return rateMap;
    }

    private static Map<String, List<String>> processMappingData(HashMap<String, List<List<String>>> mappingData, String sheetName) {
        List<List<String>> mappingSheetData = mappingData.get(sheetName);
        return mappingSheetData.stream().collect(Collectors.toMap(ele -> ele.get(0), ele -> ele, (k1, k2) -> k2));
    }
}

