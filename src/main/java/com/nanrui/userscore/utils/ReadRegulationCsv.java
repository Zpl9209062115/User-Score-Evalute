package com.nanrui.userscore.utils;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.nanrui.userscore.entities.RuleBean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @ClassName ReadRegulationExcel
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/16 13:55
 * @Version 1.0
 **/
public class ReadRegulationCsv {

    public static char separator = ',';

    String variable = "";
    String bin = "";
    String points = "";

    /**
     * 1、保存从文件中读取的数据中包含冒号的数据。
     * 2、根据对现有规则的分析，包含冒号的数据是
     *          female : divorced/separated/married
     * 保存到colonMap中进行处理，处理成
     *          female : divorced
     *          female :separated
     *          female :married
     * 3、存入数据库
     */
    Map<String,String> colonMap = new HashMap<>();

    /**
     * 1、保存从文件中读取的数据中包含特殊字符的数据。
     * 2、根据对现有规则的分析，包含特殊字符('%,%' , '/')的数据是
     *          500 <= ... < 1000 DM%,%... >= 1000 DM%,%unknown/ no savings account
     * 保存到specialCharMap中进行处理，处理成
     *          500 <= ... < 1000
     *          ... >= 1000
     *          unknown
     *          no savings account
     * 3、存入数据库
     */
    Map<String,String> colonNotMap = new HashMap<>();

    Map<String,Map<String,String>> mapCsv = new HashMap<>();



    Pattern p = Pattern.compile(".*\\d+.*");

    public static void main(String[] args) throws Exception {
        // 测试导出
        String filePath = "D:/aa/3.csv";
        /*List<String[]> dataList = new ArrayList<String[]>();
        //添加标题
        dataList.add(new String[]{"学号", "姓名", "分数"});
        for (int i = 0; i < 10; i++) {
            dataList.add(new String[]{"2010000" + i, "张三" + i, "8" + i});
        }
        createCSV(dataList, filePath);*/

        // 读取CSV文件
        ReadRegulationCsv csv = new ReadRegulationCsv();

        //csv.readCSV(filePath);

    }

    /**
     * 读取CSV文件
     * @param filePath:全路径名
     */
    public Map<String,Map<String,String>> readCSV(String filePath) throws Exception {
        CsvReader reader = null;
        String key = "";
        String value = "";
        Map<String, String> mapRule = new HashMap<>();
        try {
            //如果生产文件乱码，windows下用gbk，linux用UTF-8
            reader = new CsvReader(filePath, separator, Charset.forName("GBK"));

            // 读取表头
            reader.readHeaders();
            //String[] headArray = reader.getHeaders();//获取标题
            // 逐条读取记录，直至读完
            while (reader.readRecord()) {

                variable = reader.get("variable").trim();
                bin = reader.get("bin").trim();
                points = reader.get("points").trim();
                boolean symbolBooleanResult = bin.contains("<") || bin.contains("<=") || bin.contains(">") || bin.contains(">=");
                boolean numBooleanResult = p.matcher(bin).matches();

                key = variable+"@"+bin.trim();
                value = points.trim();

                if (null != bin){
                    if (bin.contains(":")){
                        colonMap.put(key,value);
                    } else {
                        colonNotMap.put(key,value);
                    }
                }
                // 读一整行
                //System.out.println(reader.getRawRecord());
            }

            if (0 != colonMap.size()){
                mapCsv.put("colonMap",colonMap);
            }
            if (0 != colonNotMap.size()){
                mapCsv.put("colonNotMap",colonNotMap);
            }
            System.out.println(mapCsv.size());
            return mapCsv;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }
        return mapCsv;
    }

    /**
     * 生成CSV文件
     * @param dataList:数据集
     * @param filePath:全路径名
     */
    public static boolean createCSV(List<String[]> dataList, String filePath) throws Exception {
        boolean isSuccess = false;
        CsvWriter writer = null;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath, true);
            //如果生产文件乱码，windows下用gbk，linux用UTF-8
            writer = new CsvWriter(out, separator, Charset.forName("GBK"));
            for (String[] strs : dataList) {
                writer.writeRecord(strs);
            }
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                writer.close();
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSuccess;
    }

}
