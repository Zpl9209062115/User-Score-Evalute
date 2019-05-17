var curPath = window.document.location.href;
var pathName = window.document.location.pathname;
var pos = curPath.indexOf(pathName);
var localhostPath = curPath.substring(0, pos);
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var params = {
    "section": "10"
}
var json = JSON.stringify(params);
$(document).ready(function () {
    debugger
    $('#mytab').bootstrapTable({
        url: localhostPath + projectName + "/loanuser/selectAllPage",
        method: 'post',                      //请求方式（*）
        queryParams: null,
        toolbar: "#toolbar",
        sidePagination: "client",
        pageNumber: 1,
        striped: true,
        search: "true",
        uniqueId: "loanUser_id",
        pageSize: "5",
        pagination: true,
        sortName: "sum_point",
        sortOrder: "desc",
        paginationPreText: "<i class='la la-chevron-circle-left'></i>",
        paginationNextText: "<i class='la la-chevron-circle-right'></i>",
        sortable: true,
        columns: [{
            field: 'loanUser_id',
            title: '用户id',
            align: 'center',
            },
            {
                field: 'status_sxisting_checking_account_giveMark',
                title: '现有支票<br/>帐户的状况',
                align: 'center',
            },
            {
                field: 'duration_month_giveMark',
                title: '社保缴纳<br/>时间',
                align: 'center',
            },
            {
                field: 'credit_history_giveMark',
                title: '信用历史',
                align: 'center',
            },
            {
                field: 'purpose_giveMark',
                title: '用途',
                align: 'center',
            },
            {
                field: 'credit_amount_giveMark',
                title: '欠款金额',
                align: 'center',
            },
            {
                field: 'savings_account_and_bonds_giveMark',
                title: '储蓄存款<br/>及债券',
                align: 'center',
            },
            {
                field: 'employment_since_giveMark',
                title: '从业经历',
                align: 'center',
            },
            {
                field: 'installment_income_giveMark',
                title: '分期付款率/可支配收入(%)',
                align: 'center',
            },
            {
                field: 'personal_status_and_sex_giveMark',
                title: '性别及婚姻状态',
                align: 'center',
            },
            {
                field: 'other_debtors_or_guarantors_giveMark',
                title: '其他债务人<br/>或保证人',
                align: 'center',
            },
            {
                field: 'property_giveMark',
                title: '财产',
                align: 'center',
            },
            {
                field: 'age_giveMark',
                title: '贷款人年龄',
                align: 'center',
            },
            {
                field: 'installment_plans_giveMark',
                title: '其他分期付款计划',
                align: 'center',
            },
            {
                field: 'housing_giveMark',
                title: '住房情况',
                align: 'center',
            },
            {
                field: 'sum_point',
                title: '基础分',
                visible: false,
            },
            {
                field: 'sum_point',
                title: '总分',
                align: 'center',
            },
            {
                field: 'creditability_giveMark',
                title: '可信性-违约标识',
                visible: false,
            },
            {
                field: 'identifying_giveMark',
                title: '原始数据是否经过处理的标识',
                visible: false,
            }
        ]
    });
})

function operation(value,row,index){
    var htm = "<button class='btn btn-default btn-xs' onclick='editUser(this)'><i class='la la-edit'></i></button> <button class='btn btn-danger btn-xs' onclick='delUser(this)'><i class='la la-remove'></i></button>";
    return htm;
}