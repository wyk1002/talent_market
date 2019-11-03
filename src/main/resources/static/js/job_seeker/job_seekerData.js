layui.use(['laypage', 'layer', 'table', 'element'], function () {
    var laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , element = layui.element
        , $ = layui.jquery;
    //第一个实例
    var tableIns = table.render({
        elem: '#demo'//前台页面table的名字
        , height: 420
        , url: 'getAllJob_seeker' //数据接口
        , page: true //开启分页
        , toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , totalRow: true //开启合计行
        , id: 'Job_seekerTable'
        , method: "get"
        , cols: [[ //表头
            {type: 'checkbox', fixed: 'left'},
            {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left'},
            {field: 'name', title: '姓名', width: 150},
            {field: 'phone', title: '联系电话', width: 150},
            {field: 'jobPurpose', title: '求职意向', width: 150},
            {fixed: 'right', width: 200, align: 'center', toolbar: '#barDemo'}//barDemo对应前台页面table下的script语句
        ]]
        , parseData: function (res) { //进入页面就到后台拿数据，res 即为原始返回的数据
            //这句在前台的console输出数据  console.log(res);
            console.log(res);
            return {
                "code": 0, //解析接口状态
                "msg": "", //解析提示文本
                "count": res.length, //解析数据长度
                "data": res //解析数据列表
            };
        }
    });
    //监听表头栏事件：新增、批量删除等
    table.on('toolbar(test)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            , data = checkStatus.data;//获取选中的数据
        switch (obj.event) {
            case 'add':
                parent.layer.open({
                    type: 2,
                    shadeClose: true,
                    title: '新增求职人员',
                    area: ['30%', '250px'],
                    content: ['gotoAddJob_seeker', 'no'],//跳到后台
                    success:function(layer,index)
                    {

                    },
                    end: function () {
                        tableIns.reload();
                    }

                });
                break;
            case 'update':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else if (data.length > 1) {
                    layer.msg('只能同时编辑一个');
                } else {
                    parent.layer.open({
                        type: 2,
                        shadeClose: true,
                        title: '编辑求职人员',
                        area: ['30%', '250px'],
                        content: ['gotoUpdateJob_seeker', 'no'],//跳到后台
                        success: function (layer, index) {
                            // 获取子页面的iframe
                            var iframe = parent.window['layui-layer-iframe' + index];
                            iframe.getdata(data[0].id, data[0].name,data[0].phone,data[0].jobPurpose);
                        },
                        end: function () {
                            tableIns.reload();
                        }
                    });
                }
                break;
            case 'delete':
                if (data.length === 0) {
                    layer.msg('请至少选择一行');
                } else {
                    var datas = "";
                    for (var i = 0; i < data.length; i++) {
                        datas += data[i].id + ",";
                    }
                    $.ajax({
                        url: 'delJob_seekers',
                        type: "post",
                        data: {
                            datas: datas
                        },
                        success: function (msg) {
                            if (msg.code === 1) {
                                layer.msg("删除成功!", {icon: 6});
                                tableIns.reload();
                            } else {
                                layer.msg("删除失败!未知错误", {icon: 5});
                            }
                        }
                    });
                }
                break;
        }
    });
    $("#search").click(function () {//查询事件，在table中写的方法调用 tableIns.reload();
        var searchid = document.getElementById("searchid").value;
        table.reload('Job_seekerTable', {
            url: 'searchJob_seeker',
            page: {
                curr: 1
            }
            , where: {
                searchid: searchid
            }
        });

    });

    //监听单元格工具事件
    table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            , layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'del') {
            layer.confirm('确定删除？', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);//关闭当前弹窗，即：“确定删除”
                //向服务端发送删除指令
                $.ajax({
                    url: 'delJob_seeker',
                    type: "post",
                    data: {
                        id: data.id
                    },
                    success: function (msg) {
                        if (msg.code === 1) {
                            console.log(msg);
                            //关闭弹窗
                            layer.close(index);
                            layer.msg("删除成功!", {icon: 6});
                        } else {
                            layer.msg("删除失败!未知错误", {icon: 5});
                        }
                    }
                });
            });
        } else if (layEvent === 'edit') {
            parent.layer.open({
                type: 2,
                shadeClose: true,
                title: '编辑求职人员',
                area: ['30%', '250px'],
                content: ['gotoUpdateJob_seeker', 'no'],//跳到后台
                success: function (layer, index) {
                    // 获取子页面的iframe
                    var iframe = parent.window['layui-layer-iframe' + index];
                    iframe.getdata(data.id, data.name,data.phone,data.jobPurpose);
                },
                end: function () {
                    tableIns.reload();
                }
            });

        }
    });
});

