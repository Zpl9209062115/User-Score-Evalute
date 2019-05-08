package com.nanrui.userscore.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.nanrui.userscore.dao.LoanUserGiveMarkDao;
import com.nanrui.userscore.dao.RuleDao;
import com.nanrui.userscore.dao.SourceDataDao;
import com.nanrui.userscore.entities.LoanUser_GiveMark;
import com.nanrui.userscore.entities.SourceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @ClassName DataViewService
 * @Description TODO
 * @Author ZPL
 * @Date 2019/5/5 11:47
 * @Version 1.0
 **/
@Service
public class DataViewService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    SourceDataDao sourceDataDao;

    @Autowired
    LoanUserGiveMarkDao loanUserGiveMarkDao;

    @Autowired
    RuleDao ruleDao;

    public LoanUser_GiveMark labelView(String label) {
        LoanUser_GiveMark oneBean = loanUserGiveMarkDao.findOne(label);
        return oneBean;
    }

    /**
     * 查看在现有规则下，每个指标的用户有多少个
     *
     * @return
     */
    public List<SourceData> labelView_dashBoard() {
        StringBuilder sqljoint = new StringBuilder(300);
        sqljoint.append("SELECT age,credit_amount,credit_history,creditability,duration_month,employment_since,housing,identifying,installment_income,installment_plans,other_debtors_or_guarantors,personal_status_and_sex,property,purpose,savings_account_and_bonds,status_sxisting_checking_account FROM source_data where 1=1");
        Query query = entityManager.createQuery(sqljoint.toString());
        List<SourceData> resultList = query.getResultList();
        return resultList;
    }
}
