package com.nanrui.userscore.controller;

import com.nanrui.userscore.entities.LoanUser;
import com.nanrui.userscore.entities.RuleBean;
import com.nanrui.userscore.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @ClassName empController
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/15 14:10
 * @Version 1.0
 **/
@Controller
public class EmpController {

    @Autowired
    EmpService empService;

    /**
     * 查询所有用户，返回列表页面
     * @return
     */
    /*@GetMapping("/emps/getAllEmp")
    public String getEmpMessage(Model model){
        List<RuleBean> allLoanUser = empService.getAllLoanUser();
        *//**
         * 放到请求域中
         *//*
        model.addAttribute("emps",allLoanUser);
        return "emp/list";
    }*/

    /**
     * 来到用户预测页面
     * @return
     */
    @GetMapping("/empRatings")
    public String toPredictionPage(){
        /**
         * 返回到添加页面
         */
        return "emp/add";
    }

    /**
     * 查询规则，根据用户信息，对用户进行评分并获取用户评分
     * @return
     */
    @PostMapping("/emps/getPredictionMessage")
    public String EmpPrediction(LoanUser user){
        System.out.println("预测用户信息" + user);
        empService.JudgeScore(user);
        return "emp/score";
    }

}
