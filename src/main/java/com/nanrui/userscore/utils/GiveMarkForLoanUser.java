package com.nanrui.userscore.utils;

import com.nanrui.userscore.entities.LoanUser;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GiveMarkForLoanUser
 * @Description TODO
 * @Author ZPL
 * @Date 2019/4/25 18:11
 * @Version 1.0
 **/
public class GiveMarkForLoanUser {

    /**
     * 贷款人年龄
     */
    String age;

    /**
     * 性别及婚姻状态
     */
    String personalStatusAndSex;

    /**
     * 社保缴纳时间
     */
    String durationMonth;

    /**
     * 分期付款率占可支配收入的百分比
     */
    String installmentIncome;

    /**
     * 从业经历
     */
    String employmentSince;

    /**
     * 住房情况
     */
    String housing;

    /**
     * 其他分期付款计划
     */
    String installmentPlans;

    /**
     * 储蓄存款及债券
     */
    String savingsAccountAndBonds;

    /**
     * 信用历史
     */
    String creditHistory;

    /**
     * 欠款金额
     */
    String creditAmount;

    /**
     * 现有支票帐户的状况
     */
    String statusSxistingCheckingAccount;

    /**
     * 用途
     */
    String purpose;

    /**
     * 其他债务人或保证人
     */
    String otherDebtorsOrGuarantors;

    public List<String> loanUserDispose(LoanUser loanUser){
        age = loanUser.getAge();
        personalStatusAndSex = loanUser.getPersonalStatusAndSex();
        durationMonth = loanUser.getDurationMonth();
        installmentIncome = loanUser.getInstallmentIncome();
        employmentSince = loanUser.getEmploymentSince();
        housing = loanUser.getHousing();
        installmentPlans = loanUser.getInstallmentPlans();
        savingsAccountAndBonds = loanUser.getSavingsAccountAndBonds();
        creditHistory = loanUser.getCreditHistory();
        creditAmount = loanUser.getCreditAmount();
        statusSxistingCheckingAccount = loanUser.getStatusSxistingCheckingAccount();
        purpose = loanUser.getPurpose();
        otherDebtorsOrGuarantors = loanUser.getOtherDebtorsOrGuarantors();
        return null;
    }

    public String sectionDispose(String label,String section){
        IntervalUtil a = new IntervalUtil();
        if ("age".equals(label)){
            boolean inTheInterval = a.isInTheInterval("0.436589", "");
        }else if ("personalStatusAndSex".equals(label)){

        }else if ("durationMonth".equals(label)){

        }else if ("installmentIncome".equals(label)){

        }else if ("employmentSince".equals(label)){

        }else if ("housing".equals(label)){

        }else if ("installmentPlans".equals(label)){

        }else if ("savingsAccountAndBonds".equals(label)){

        }else if ("creditHistory".equals(label)){

        }else if ("creditAmount".equals(label)){

        }else if ("statusSxistingCheckingAccount".equals(label)){

        }else if ("purpose".equals(label)){

        }else if ("otherDebtorsOrGuarantors".equals(label)){

        }
        return null;
    }

}
