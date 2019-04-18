package com.atguigu.userscore.utils;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String,String> map = csv.readCSV(filePath);
        for (Map.Entry<String,String> mapRule: map.entrySet()){
            System.out.println(mapRule.getKey() + " --> " +mapRule.getValue());

        }
        System.out.println(map.size());

    }

    /**
     * 读取CSV文件
     * @param filePath:全路径名
     */
    public Map<String,String> readCSV(String filePath) throws Exception {
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
                key = variable+"@"+bin;
                value = points;
                mapRule.put(key,value);
                // 读一整行
                //System.out.println(reader.getRawRecord());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }

        return mapRule;
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
