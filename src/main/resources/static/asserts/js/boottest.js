var curPath = window.document.location.href;
var pathName = window.document.location.pathname;
var pos = curPath.indexOf(pathName);
var localhostPath = curPath.substring(0, pos);
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var params = {
    "section": "10"
}
var json = JSON.stringify(params);
$(document).ready(function(){

    $("#table_id").bootstrapTable({
        //请求地址,此处数据为本地加载
        url: localhostPath + projectName + "/loanuser/selectAllPage",
        //请求方式
        method: "POST",
        //请求内容类型
        contentType: "application/x-www-form-urlencoded",
        //数据类型
        dataType: "json",
        //是否显示行间隔色
        striped: true,
        //是否启用排序
        sortable: true,
        //排序方式
        sortOrder: "asc",
        //是否使用缓存
        cache: false,
        //每行的唯一标识
        uniqueId: "id",
        //指定工具栏
        toolbar: "#toolbar",
        //显示刷新按钮
        showRefresh: true,
        //切换显示样式
        showToggle: true,
        //默认显示详细视图
        cardView: false,
        showHeader : true,
        //是否显示搜索
        search: true,
        //是否显示分页
        pagination: true,
        //是否启用点击选中行
        clickToSelect: false,
        //最少要显示的列数
        minimumCountColumns: 2,
        //显示隐藏列
        showColumns: true,
        //cell没有值时显示
        undefinedText: '-',
        //分页方式：client客户端分页，server服务端分页
        sidePagination: "client",
        //每页的记录行数
        pageSize: 10,
        //初始化加载第1页，默认第1页
        pageNumber: 1,
        //可供选择的每页的行数
        pageList: "[10, 20, 50, 80, 100]",
        paginationFirstText: "首页",
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        paginationLastText: "末页",
        //按钮样式
        buttonsClass: 'btn',
        //分页器class
        iconSize: 'pager',
        //查询条件
        queryParams: queryParams,
        //表头
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
            }],
        onLoadSuccess: function (res) {//可不写
            //加载成功时
            console.log(res);
        }, onLoadError: function (statusCode) {
            return "加载失败了"
        }, formatLoadingMessage: function () {
            //正在加载
            return "拼命加载中...";
        }, formatNoMatches: function () {
            //没有匹配的结果
            return '无符合条件的记录';
        }
    });

    function genderDel(value, row, index) {
        /**
         * 替换delete数据为文字
         * @param {string} value 选值
         * @param {object} row 行数据
         * @param {number} index 行索引
         * @return {string} 返回状态或"-"
         */
        if (value == null || value == undefined) {
            return "-";
        } else if (value == 1) {
            return "已删除";
        } else if (value == 0) {
            return "正常";
        }
    }
//return还可以return字符串拼接
    function genderOpt() {
        var htm =  "<button class='btn btn-default btn -sm'><a href='javascript:void(0)' title='编辑'><i class='fa fa-edit'></i> 编辑</a></button>"+" &nbsp;&nbsp;"+"<button class='btn btn-default btn-sm'><a href='javascript:void(0)' title='删除'><i class='fa fa-remove'></i> 删除</a></button>";
        return htm;
    }
//操作事件建议卸载内部,防止第一次点击操作不生效
    window.operateEvents = {
        /**
         * 注册操作按钮事件
         */
        'click .trbtn-edit': function (e, value, row, index) {
            editData(row);
        },
        'click .trbtn-remove': function (e, value, row, index) {
            delData(row.custNo);
        }
    };

    function queryParams(params) {
        /**
         * 查询条件与分页数据
         * @return {object} 返回参数对象
         */
        //排序方式
        params.order = "modify_time desc";
        //第几页：指定跳转
        params.nowPage = this.pageNumber;
        //name
        params.custName = $("#search-name").val();
        //工具栏 参数
        console.log("查询条件");
        console.log(params);
        return params;
    }

    function refresh() {
        /**
         * 刷新表格数据
         */
        $table.bootstrapTable('refresh');
        //$table.bootstrapTable('refresh'.{url:""});//刷新时调用接口防止表格无限销毁重铸时出现英文
    }

//事件部分
    $("#btn-query").on("click", function () {
        /** * 查询 */
        refresh();
    })
    $("#btn-add").on("click", function () {
        console.log("新增页面");
    });

    function editData(row) {//row为表格内一行的数据传值
        console.log("修改页面")
    }

    function delData(strData) {
        /**
         * 删除 单行or多行
         * @param {string} strData 单行选中 数据
         * @param {object} strData 多行中行 数组
         */
        //多条数据转换
        if (typeof strData == "object") {
            strData = strData.join();
        }

        //确认操作
        if(confirm('确定要删除用户编号为' + strData + '数据?')) {
            /**
             * callback
             * @param {boolean} result：true>= OK, false>= Cancel
             */
            if (!result) {
                console.log("Cancel");
                return;
            }
            console.log("OK");
            console.log("删除数据");
            //组织数据-转换
            var sendData = {param:strData};
            console.log(sendData);
            $.ajax({
                url: 'user/deleteTest',
                method: 'POST',
                contentType: "application/x-www-form-urlencoded",
                data: sendData,
                //阻止深度序列化，向后台传送数组
                traditional: true,
                async : false,//这里同步，请按实际需求设置
                //成功
                success: function (msg) {
                    console.log()
                },
                //请求错误
                error: function (err) {
                    console.log(err)
                }
            })
        }
    }

    $("#btn-del").on("click", function () {
        /**
         * 多行删除
         */
        var checkDatas = $table.bootstrapTable('getSelections');//获取选中项
        console.log(checkDatas);
        if (checkDatas.length < 1) {
            alert("请先选择一条或多条数据");
        } else {
            var arr = [];
            for (var i = 0; i < checkDatas.length; i++) {
                arr.push(checkDatas[i].custNo);
            }
            console.log(arr);
            delData(arr);
        }
    });
});