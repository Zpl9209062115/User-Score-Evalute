package com.nanrui.userscore.controller;

import com.nanrui.userscore.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RuleController
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/22 9:19
 * @Version 1.0
 **/
@Controller
public class RuleController {

    @Autowired
    RuleService ruleService;

    @GetMapping("/emps/getAllEmp")
    public String getRuleLable(Model model){
        List<String> ruleLabel = ruleService.findRuleLabel();
        model.addAttribute("emps",ruleLabel);
        return "emp/list";
    }

}
