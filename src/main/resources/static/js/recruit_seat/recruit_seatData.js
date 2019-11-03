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
        , url: 'getAllRecruit_seat' //数据接口
        , page: true //开启分页
        , toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , totalRow: true //开启合计行
        , id: 'recruit_seatTable'
        , method: "get"
        , cols: [[ //表头
            {type: 'checkbox', fixed: 'left'},
            {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left'},
            {field: 'recruitSeatNum', title: '编号', width: 80},
            {field: 'recruitAreaId', title: '所属招聘区域ID', width: 150},
            {field: 'firmId', title: '所属招聘企业ID', width: 150},
            {field: 'state', title: '状态', width: 80},
            {field: 'price', title: '价格', width: 80},
            {fixed: 'right', width: 165, align: 'center', toolbar: '#barDemo'}//barDemo对应前台页面table下的script语句
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
   function onload() {
        // console.log(location.search);
        if (location.search!="")
        {
            // console.log(location.search.substring(8,location.search.length));
            table.reload('recruit_seatTable', {
                url: 'searchSeat',
                page: {
                    curr: 1
                }
                , where: {
                    areaid: location.search.substring(8,location.search.length)
                }
            });
        }
    }
    onload();
    //监听表头栏事件：新增、批量删除等
    table.on('toolbar(test)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            , data = checkStatus.data;//获取选中的数据
        switch (obj.event) {
            case 'add':
                parent.layer.open({
                    type: 2,
                    shadeClose: true,
                    title: '新增招聘位',
                    area: ['30%', '360px'],
                    content: ['gotoAddRecruit_seat', 'no'],//跳到后台
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
                        title: '编辑招聘位',
                        area: ['30%', '360px'],
                        content: ['gotoUpdateRecruit_seat', 'no'],//跳到后台
                        success: function (layer, index) {
                            // 获取子页面的iframe
                            var iframe = parent.window['layui-layer-iframe' + index];
                            // session.getAttribute()
                            iframe.getdata(data[0].id, data[0].recruitSeatNum,data[0].recruitAreaId,data[0].firmId,data[0].state,data[0].price);
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
                        url: 'delRecruit_seats',
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
        table.reload('recruit_seatTable', {
            url: 'searchRecruit_seat',
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
        if (layEvent === 'detail') {
            layer.msg('查看操作');
        } else if (layEvent === 'del') {
            layer.confirm('确定删除？', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);//关闭当前弹窗，即：“确定删除”
                //向服务端发送删除指令
                $.ajax({
                    url: 'delRecruit_seat',
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
                        }else {
                            layer.msg("删除失败!未知错误", {icon: 5});
                        }
                    }
                });
            });
        } else if (layEvent === 'edit') {
            parent.layer.open({
                type: 2,
                shadeClose: true,
                title: '编辑招聘位',
                area: ['30%', '360px'],
                content: ['gotoUpdateRecruit_seat', 'no'],//跳到后台
                success: function (layer, index) {
                    // 获取子页面的iframe
                    var iframe = parent.window['layui-layer-iframe' + index];
                    iframe.getdata(data.id, data.recruitSeatNum,data.recruitAreaId,data.firmId,data.state,data.price);
                },
                end: function () {
                    tableIns.reload();
                }
            });
        }
    });
});

