package com.surge.Friday.tools.excel;

import com.alibaba.excel.util.StringUtils;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.surge.Friday.tools.excel.ReadExcelFragment.writeBigExcel;

public class Application {
    // 读取全球数据 读取
    public static void main(String[] args) throws Exception {
        int flowDesPosition = 10;
        int cmdCodePosition = 20;
        int cmdValuePosition = 43;

//                String cmdCode = row.getCell(cmdCodePosition).getStringCellValue();
//                String cmdDesc = row.getCell(21).getStringCellValue();
//                String primaryValue = row.getCell(cmdValuePosition).getStringCellValue();

//                innerList.add(cmdCode);
//                innerList.add(cmdDesc);
//                innerList.add(primaryValue);


        File file2022 = new File("/Users/liubojin/Desktop/2022.xlsx");
        List<List<String>> excelList2022 = ReadExcelFragment.readBigExcelAll(file2022);
        excelList2022.remove(0);

        File file2011 = new File("/Users/liubojin/Desktop/2011.xlsx");
        List<List<String>> excelList2011 = ReadExcelFragment.readBigExcelAll(file2011);
        excelList2011.remove(0);

        File zhong2011 = new File("/Users/liubojin/Desktop/2011z.xlsx");
        List<List<String>> zhong2011List = ReadExcelFragment.readBigExcelAll(zhong2011);
        zhong2011List.remove(0);

        File zhong2022 = new File("/Users/liubojin/Desktop/2022中国数据处理结果.xlsx");
        List<List<String>> zhong2022List = ReadExcelFragment.readBigExcelAll(zhong2022);
        List<String> title = zhong2022List.get(0);
        title.add("rateForAllCountry");
        title.add("rateForAllCountryDiffWith2011");
        zhong2022List.remove(0);

        File hsMapping = new File("/Users/liubojin/Desktop/HS2022toHS2007Conversion.xlsx");
        HashMap<String, List<List<String>>> hsMappingData = ReadExcelFragment.readBigExcelAllBySheet(hsMapping);
        List<List<String>> hsMappingDataList = hsMappingData.get("HS2022-HS2007 Conversions");
        Map<String, List<String>> hsMappingMap = hsMappingDataList.stream().collect(Collectors.toMap(ele -> ele.get(0), ele -> ele, (k1, k2) -> k2));


        HashMap<String, BigDecimal> all2011MapImport = new HashMap<>();
        HashMap<String, BigDecimal> all2011MapExport = new HashMap<>();
        for (List<String> row : excelList2011) {
            if (row.get(flowDesPosition).equals("Import")) {
                all2011MapImport.put(row.get(cmdCodePosition), new BigDecimal(row.get(cmdValuePosition)));
            } else if (row.get(flowDesPosition).equals("Export")) {
                all2011MapExport.put(row.get(cmdCodePosition), new BigDecimal(row.get(cmdValuePosition)));
            }
        }

        HashMap<String, BigDecimal> all2022MapImport = new HashMap<>();
        HashMap<String, BigDecimal> all2022MapExport = new HashMap<>();
        for (List<String> row : excelList2022) {
            String hs3Code = hsMappingMap.get(row.get(cmdCodePosition)) == null ? "" : hsMappingMap.get(row.get(cmdCodePosition)).get(1);

            if (row.get(flowDesPosition).equals("Import")) {
                all2022MapImport.put(hs3Code, new BigDecimal(row.get(cmdValuePosition)));
            } else if (row.get(flowDesPosition).equals("Export")) {
                all2022MapExport.put(hs3Code, new BigDecimal(row.get(cmdValuePosition)));
            }
        }


        HashMap<String, BigDecimal> zhong2011MapImport = new HashMap<>();
        HashMap<String, BigDecimal> zhong2011MapExport = new HashMap<>();
        for (List<String> row : zhong2011List) {
            if (row.get(flowDesPosition).equals("Import")) {
                zhong2011MapImport.put(row.get(cmdCodePosition), new BigDecimal(row.get(cmdValuePosition)));
            } else if (row.get(flowDesPosition).equals("Export")) {
                zhong2011MapExport.put(row.get(cmdCodePosition), new BigDecimal(row.get(cmdValuePosition)));
            }
        }


        HashMap<String, BigDecimal> zhong2022MapImport = new HashMap<>();
        HashMap<String, BigDecimal> zhong2022MapExport = new HashMap<>();


        for (List<String> row : zhong2022List) {
            String hs3Code = hsMappingMap.get(row.get(cmdCodePosition)) == null ? "" : hsMappingMap.get(row.get(cmdCodePosition)).get(1);

            if (row.get(flowDesPosition).equals("Import")) {
                zhong2022MapImport.put(hs3Code, new BigDecimal(row.get(cmdValuePosition)));
            } else if (row.get(flowDesPosition).equals("Export")) {
                zhong2022MapExport.put(hs3Code, new BigDecimal(row.get(cmdValuePosition)));
            }
        }

        //计算2011年中国所占全球比例
        HashMap<String, BigDecimal> zhong2011RateMapImport = new HashMap<>();
        HashMap<String, BigDecimal> zhong2011RateMapExport = new HashMap<>();
        for (Map.Entry<String, BigDecimal> entry : zhong2011MapImport.entrySet()) {

            if (all2011MapImport.get(entry.getKey()).equals(BigDecimal.ZERO)) {
                zhong2011RateMapImport.put(entry.getKey(), BigDecimal.ZERO);
            } else {
                zhong2011RateMapImport.put(entry.getKey(), entry.getValue().divide(all2011MapImport.get(entry.getKey()), 6, RoundingMode.HALF_UP));
            }
        }

        for (Map.Entry<String, BigDecimal> entry : zhong2011MapExport.entrySet()) {

            if (all2011MapExport.get(entry.getKey()).equals(BigDecimal.ZERO)) {
                zhong2011RateMapExport.put(entry.getKey(), BigDecimal.ZERO);
            } else {
                zhong2011RateMapExport.put(entry.getKey(), entry.getValue().divide(all2011MapExport.get(entry.getKey()), 6, RoundingMode.HALF_UP));
            }
        }


        //计算2011年中国所占全球比例
        HashMap<String, BigDecimal> zhong2022RateMapImport = new HashMap<>();
        HashMap<String, BigDecimal> zhong2022RateMapExport = new HashMap<>();
        for (Map.Entry<String, BigDecimal> entry : zhong2022MapImport.entrySet()) {

            if (all2022MapImport.get(entry.getKey()).equals(BigDecimal.ZERO)) {
                zhong2022RateMapImport.put(entry.getKey(), BigDecimal.ZERO);
            } else {
                zhong2022RateMapImport.put(entry.getKey(), entry.getValue().divide(all2022MapImport.get(entry.getKey()), 6, RoundingMode.HALF_UP));
            }
        }

        for (Map.Entry<String, BigDecimal> entry : zhong2022MapExport.entrySet()) {

            if (all2022MapExport.get(entry.getKey()).equals(BigDecimal.ZERO)) {
                zhong2022RateMapExport.put(entry.getKey(), BigDecimal.ZERO);
            } else {
                zhong2022RateMapExport.put(entry.getKey(), entry.getValue().divide(all2022MapExport.get(entry.getKey()), 6, RoundingMode.HALF_UP));
            }
        }

        File mappingFile = new File("/Users/liubojin/Desktop/数据示例及对应关系.xlsx");
        HashMap<String, List<List<String>>> mappingData = ReadExcelFragment.readBigExcelAllBySheet(mappingFile);

        //处理hs6对应bec4
        List<List<String>> hs6ToBec4 = mappingData.get("hs6对应bec4");
        Map<String, List<String>> hs6ToBec4Map = hs6ToBec4.stream().collect(Collectors.toMap(ele -> ele.get(0), ele -> ele, (k1, k2) -> k2));
        //处理hs3对应bec4
        List<List<String>> hs3ToBec4 = mappingData.get("hs3对应bec4");
        Map<String, List<String>> hs3ToBec4Map = hs3ToBec4.stream().collect(Collectors.toMap(ele -> ele.get(0), ele -> ele, (k1, k2) -> k2));

        //处理hs6对应isic3
        List<List<String>> hs6ToIsic3 = mappingData.get("HS6对应isic3");
        Map<String, List<String>> hs6ToIsic3Map = hs6ToIsic3.stream().collect(Collectors.toMap(ele -> ele.get(0), ele -> ele, (k1, k2) -> k2));
        //处理hs3对应isic3
        List<List<String>> hs3ToIsic3 = mappingData.get("hs3对应isic3");
        Map<String, List<String>> hs3ToIsic3Map = hs3ToIsic3.stream().collect(Collectors.toMap(ele -> ele.get(0), ele -> ele, (k1, k2) -> k2));

        //对应中间品
        List<List<String>> middle = mappingData.get("对应中间品");
        Map<String, List<String>> middleMap = middle.stream().collect(Collectors.toMap(ele -> ele.get(0), ele -> ele, (k1, k2) -> k2));
        //对应中间品2
        List<List<String>> middle2 = mappingData.get("对应中间品2");
        Map<String, List<String>> middle2Map = middle2.stream().collect(Collectors.toMap(ele -> ele.get(0), ele -> ele, (k1, k2) -> k2));

        //对应大类
        List<List<String>> category = mappingData.get("对应大类");
        Map<String, List<String>> categoryMap = category.stream().collect(Collectors.toMap(ele -> ele.get(0), ele -> ele, (k1, k2) -> k2));


        //2022数据处理
        for (List<String> row : zhong2011List) {
            String hsVersion = row.get(cmdCodePosition - 3);
            String cmdCode = row.get(cmdCodePosition);
            String flowDesc = row.get(flowDesPosition);


            String bec4 = "";
            String isic3 = "";
            if (Objects.equals(hsVersion, "H6")) {
                bec4 = hs6ToBec4Map.get(cmdCode).get(1);
                isic3 = hs6ToIsic3Map.get(cmdCode).get(1);
            } else if (Objects.equals(hsVersion, "H3")) {
                bec4 = hs3ToBec4Map.get(cmdCode) == null ? "" :  hs3ToBec4Map.get(cmdCode).get(1);
                isic3 = hs3ToIsic3Map.get(cmdCode) == null ? "" : hs3ToIsic3Map.get(cmdCode).get(1);
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

            //数字格式校验
            StringBuffer stringBuffer = new StringBuffer();
            if (StringUtils.isBlank(cmdCode) || !isNumber(cmdCode)) {
                stringBuffer.append("AR列出现非数字或者空白；");
            }
            String cmdCode1 = row.get(cmdCodePosition + 1);
            if (StringUtils.isBlank(cmdCode1) || cmdCode1.equals("TRUE") || cmdCode1.equals("FALSE")) {
                stringBuffer.append("AS列出现英文单词TRUE或者FALSE或者空白；");
            }
            row.add(stringBuffer.toString());

        }


        zhong2011List.add(0, title);

        HashMap<String, List<List<String>>> fileContent = new HashMap<>();
        fileContent.put("2011年中国数据计算结果", zhong2011List);

        writeBigExcel("/Users/liubojin/Desktop/2011年中国数据计算结果-1.xlsx", fileContent);

        System.out.println(123);

    }

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}

