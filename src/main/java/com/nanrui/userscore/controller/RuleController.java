package com.nanrui.userscore.controller;

import com.nanrui.userscore.entities.LoanUser;
import com.nanrui.userscore.entities.LoanUser_GiveMark;
import com.nanrui.userscore.service.RuleService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @PostMapping("/rule/usersPredict")
    public String getRulelabel(Model model){
        Map<String, List<String>> ruleLabelByAge = ruleService.findAll();

        model.addAttribute("ruleLabelByAge",ruleLabelByAge);
        return "emp/mark";
    }

    /**
     * 获取页面选项
     * @param model
     * @return
     * @throws IOException
     */
    @GetMapping("/rule/giveMarkLabel")
    public String giveMarkLabel(Model model) throws IOException {
        /*Map<String, List<String>> ruleLabelByAge = ruleService.findAll();
        model.addAttribute("ruleLabelByAge",ruleLabelByAge);*/

        Map<String, List<String>> labelByTag = ruleService.findLabelByTag();
        for (Map.Entry<String,List<String>> listEntry : labelByTag.entrySet()){
            model.addAttribute(listEntry.getKey(),listEntry.getValue());
        }

        return "emp/mark";
    }

    /**
     * 获取单个员工信息进行评分
     * @return
     */
    @PostMapping("/rule/giveMark")
    public String giveMark(LoanUser loanUser){
        try {
            ruleService.preserveLoanUser(loanUser);
            ruleService.giveMarkForLoanUser(loanUser);
            return "redirect:/userList";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    @PostMapping(value = "/rule/giveBatchMark")
    public String giveBatchMark(@RequestParam("giveBatchMark") MultipartFile file, Model model){
        System.out.println("================");
        if (file.isEmpty()){
            model.addAttribute("message", "The file is empty!");
            return "/uploadStatus";
        }
        try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get("D:/test_data/" + file.getOriginalFilename());
            Files.write(path, bytes);
            model.addAttribute("message", "succes");

        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/userList";
    }


}
