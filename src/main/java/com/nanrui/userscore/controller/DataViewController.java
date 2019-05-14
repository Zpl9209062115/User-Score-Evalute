package com.nanrui.userscore.controller;

import com.csvreader.CsvReader;
import com.nanrui.userscore.entities.DashboardPage_DataPackageBean;
import com.nanrui.userscore.entities.LoanUser_GiveMark;
import com.nanrui.userscore.entities.RuleBean;
import com.nanrui.userscore.service.DataViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.io.*;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DataViewController
 * @Description TODO
 * @Author ZPL
 * @Date 2019/5/5 11:44
 * @Version 1.0
 *
 * 数据展示层，包括源数据(规则生成的依赖数据)、用户导入数据即评分数据等相关数据的图形展示的控制层
 **/
@Controller
public class DataViewController {

    @Autowired
    DataViewService dataViewService;

    @ResponseBody
    @PostMapping(value = "/dataView/dashboard")
    public List<DashboardPage_DataPackageBean> sourceDataView_dashBoard(@RequestBody Map<String,Object> params){
        String section = (String) params.get("section");
        List<DashboardPage_DataPackageBean> jpaJavaBeanList = dataViewService.labelView_dashBoard(section);
        return jpaJavaBeanList;
    }

    @ResponseBody
    @PostMapping(value = "/dataView/getDataView")
    public LoanUser_GiveMark sourceDataView(@RequestBody Map<String,Object> params){
        String loanUser_id_giveMark = (String) params.get("loanUser_id_giveMark");
        LoanUser_GiveMark loanUser_giveMark = dataViewService.labelView(loanUser_id_giveMark);
        return loanUser_giveMark;
    }

    @RequestMapping(value = "/dataView/upload", method = RequestMethod.POST)
    @ResponseBody
    private Boolean importExcel(@RequestParam(value = "excelFile", required = false) MultipartFile file) {
        CsvReader reader = null;
        boolean result = false;
        try{
            if (!file.isEmpty()){
                reader = new CsvReader(file.getInputStream(), Charset.forName("GBK"));
                result = dataViewService.batchGiveMark(reader);
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
            return result;
        }
    }

    @ResponseBody
    @PostMapping(value = "/dataView/modelOptimize")
    public Boolean ruleModelView(@RequestBody Map<String,Object> params){
        return true;
    }

}
