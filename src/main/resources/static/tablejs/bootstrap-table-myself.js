$(function () {
    $('#reportTable').bootstrapTable({
        url: '',
        //你的路由(如url:http://locahost:getyouurl.do)，返回数据为jsonarray ，也可以先用ajax获取到数据，然后再使用 data:yourdata(这个是你的json数组)，使用data：yourdata，url就可以不用写了。
        //下面字段官方文档有说明，我举几个注释。
        method: 'get',
            cache: false,
        height: 500,
        striped: false,//表格渐变
        pagination: true,//分页开启
        showRefresh:true,//展示搜索框
        pageSize: 5,//数据个数
        pageNumber:1,
        pageList: [5, 10, 15, 20],  sidePagination:'client',//页面数据个数选择
        search: true,
        showColumns: true,
        showRefresh: true,
        showExport: false,
        exportTypes: ['csv','txt','xml'],
        search: true,
        clickToSelect: true,
        onClickRow: function (row) {
        /*这个是点击哪个行，就能获取那个行数据
        row.后面对应你的表格fieId数据.*/
        id=row.id;
        text=row.text;
        murl=row.murl;
        vurl=row.vurl;
        time=row.time;
        vtime=row.vtime;
        playback=row.playback;
        classifyd=row.classify;
        vid=row.videoid;



    },
    columns:
        [
            {field:"checked",checkbox:true},
            {field:"id",title:"ID",align:"center",valign:"middle",sortable:"true" ,width:100},
            {field:"text",title:"标题",align:"center",valign:"middle",sortable:"true",width:200},
            {field:"time",title:"日期",align:"center",valign:"middle",sortable:"true",width:200},
            {field:"vtime",title:"时长",align:"center",valign:"middle",sortable:"true",width:200},
            {field:"playback",title:"播放量",align:"center",valign:"middle",sortable:"true",width:200},
            {field:"videoid",title:"视频id",align:"center",valign:"middle",sortable:"true",width:200},
            {field:"classify",title:"分类",align:"center",valign:"middle",sortable:"true",width:200},
            { field: 'Desc',title: '操作',formatter : operateFormatter(),width:250 },

            //    operateFormatter()是添加button的方法

        ],
            data:datas,//这个是上面红字提到的data,使用ajax 给datas[]添加你要的数据
});
    var s;


    function operateFormatter(value, row, index) {

        return [

            '<button  style="width: 60px;height: 30px;" onclick="a(this);" type="button" class="btn btn-primary btn-xs">修改</button>&nbsp;', '<button  style="width: 60px;height: 30px;" onclick="b(this);" type="button" class="btn btn-primary btn-xs">获取时长</button>&nbsp;', '<button  style="width: 60px;height: 30px;" onclick="c(this);" type="button" class="btn btn-primary btn-xs">删除</button>' ]
            .join('');
    }
});