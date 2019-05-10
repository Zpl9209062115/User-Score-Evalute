package com.nanrui.userscore.utils;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.nanrui.userscore.entities.RuleBean;
import com.nanrui.userscore.entities.SourceData;

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

    boolean flag = false;
    GenerativeRuleUtils generativeRuleUtils = new GenerativeRuleUtils();
    String strNum_sourceDataDispose = "";

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

                key = variable+"@"+bin.trim();
                value = points.trim();


                if (null != bin){
                    if (bin.contains(":")){
                        colonMap.put(key,value);
                    } else {
                        colonNotMap.put(key,value);
                    }
                }
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

    public List<SourceData> readSourceData(String filePathSourceData) throws Exception {
        CsvReader reader = null;
        List<SourceData> sourceDataList = new ArrayList<>();
        try {
            //如果生产文件乱码，windows下用gbk，linux用UTF-8
            reader = new CsvReader(filePathSourceData, separator, Charset.forName("GBK"));

            // 读取表头
            reader.readHeaders();
            //String[] headArray = reader.getHeaders();//获取标题
            // 逐条读取记录，直至读完
            int columnCount = reader.getColumnCount();
            System.out.println(columnCount);
            long currentRecord = reader.getCurrentRecord();
            System.out.println(currentRecord);
            while (reader.readRecord()) {
                SourceData sourceData = readSourceData_javaBeanDispose(reader);
                sourceDataList.add(sourceData);
            }
            return sourceDataList;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public SourceData readSourceData_javaBeanDispose(CsvReader reader) throws IOException {
        SourceData sourceDataBean = new SourceData();

        String statusSxistingCheckingAccount = stringBooleanNumDispose(reader.get("status.of.existing.checking.account").trim());
        sourceDataBean.setStatus_sxisting_checking_account(statusSxistingCheckingAccount);

        String durationMonth = stringBooleanNumDispose(reader.get("duration.in.month").trim());
        sourceDataBean.setDuration_month(durationMonth);

        String creditHistory = stringBooleanNumDispose(reader.get("credit.history").trim());
        sourceDataBean.setCredit_history(creditHistory);

        String purpose = stringBooleanNumDispose(reader.get("purpose").trim());
        sourceDataBean.setPurpose(purpose);

        String creditAmount = stringBooleanNumDispose(reader.get("credit.amount"));
        sourceDataBean.setCredit_amount(creditAmount);

        String savingsAccountAndBonds = stringBooleanNumDispose(reader.get("savings.account.and.bonds"));
        sourceDataBean.setSavings_account_and_bonds(savingsAccountAndBonds);

        String employmentSince = stringBooleanNumDispose(reader.get("present.employment.since"));
        sourceDataBean.setEmployment_since(employmentSince);

        String installmentIncome = stringBooleanNumDispose(reader.get("installment.rate.in.percentage.of.disposable.income"));
        sourceDataBean.setInstallment_income(installmentIncome);

        String personalStatusAndSex = stringBooleanNumDispose(reader.get("personal.status.and.sex"));
        sourceDataBean.setPersonal_status_and_sex(personalStatusAndSex);

        String otherDebtorsOrGuarantors = stringBooleanNumDispose(reader.get("other.debtors.or.guarantors"));
        sourceDataBean.setOther_debtors_or_guarantors(otherDebtorsOrGuarantors);

        String property = stringBooleanNumDispose(reader.get("property"));
        sourceDataBean.setProperty(property);

        String age = stringBooleanNumDispose(reader.get("age.in.years"));
        sourceDataBean.setAge(age);

        String installmentPlans = stringBooleanNumDispose(reader.get("other.installment.plans"));
        sourceDataBean.setInstallment_plans(installmentPlans);

        String housing = stringBooleanNumDispose(reader.get("housing"));
        sourceDataBean.setHousing(housing);

        String creditability = stringBooleanNumDispose(reader.get("creditability"));
        sourceDataBean.setCreditability(creditability);

        sourceDataBean.setDispose_sign("0");
        return sourceDataBean;
    }

    public String stringBooleanNumDispose(String string){
        Map<String,String> mapStr = new HashMap<>();
        boolean symbolBooleanResult = string.contains("<") || string.contains("<=") || string.contains(">") || string.contains(">=");
        boolean numBooleanResult = p.matcher(string).matches();
        if (symbolBooleanResult && numBooleanResult){
            strNum_sourceDataDispose = generativeRuleUtils.utils(string);
        } else {
            strNum_sourceDataDispose = string;
        }
        return strNum_sourceDataDispose;
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
