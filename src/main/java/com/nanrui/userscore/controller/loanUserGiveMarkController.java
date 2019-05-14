package com.nanrui.userscore.controller;

import com.nanrui.userscore.entities.LoanUser_GiveMark;
import com.nanrui.userscore.service.LoanUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
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
public class loanUserGiveMarkController {

    @Autowired
    LoanUserService loanUserService;

    @GetMapping(value = "/giveMarks/userListSkip")
    public String userListSkip(){
        System.out.println("页面跳转");
        return "emp/list";
    }

    @ResponseBody
    @PostMapping(value = "/giveMarks/getLoanUser_giveMark")
    public List<LoanUser_GiveMark> giveUserListPage(@RequestParam Map<String,Object> params, Model model){
        List<LoanUser_GiveMark> loanUser_giveMarks = loanUserService.selectAllLoanUser_GiveMark();
        return loanUser_giveMarks;
    }

    @ResponseBody
    @PostMapping(value = "/giveMarks/getLoanUser_giveMark_one")
    public LoanUser_GiveMark giveUserListPageOne(@RequestBody Map<String,Object> params){
        String loanUser_id_giveMark = (String) params.get("loanUser_id_giveMark");
        System.out.println(loanUser_id_giveMark);
        LoanUser_GiveMark loanUser_giveMark = loanUserService.selectOneById(loanUser_id_giveMark);
        return loanUser_giveMark;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/loanuser/selectAllPage")
    public List<LoanUser_GiveMark> selectAll(@RequestBody Map<String,Object> params){

        //@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize
        int pageNum = (int) params.get("pageNum");
        int pageSize = (int) params.get("pageSize");

        Iterator<LoanUser_GiveMark> userIterator = loanUserService.selectAll(pageNum, pageSize);
        List<LoanUser_GiveMark> list = new ArrayList<>();
        while(userIterator.hasNext()){
            list.add(userIterator.next());
        }
        return list;
    }

}
