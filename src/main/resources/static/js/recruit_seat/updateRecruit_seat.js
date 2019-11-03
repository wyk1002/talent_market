layui.use('form', function(){
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
function getdata(id, mycode,myareaId,firmId,state,price) {
    layui.use('form', function(){
        var $=layui.$;
        var form=layui.form;
        document.getElementById("id").value=id;
        document.getElementById("mycode").value=mycode;
        document.getElementById("price").value=price;
        $('#myareaId').val(myareaId);
        $('#state').val(state);
        $("#firmId").empty();
        if (state == "已出租") {
            for (var i = 0; i < firms.length; i++) {
                $("#firmId").append(new Option(firms[i].name,firms[i].id))
            }
        }
        $('#firmId').val(firmId);
        form.render('select');
    });
}
function updateRecruit_seat() {
    var index = parent.layer.getFrameIndex(window.name);
    var $ = layui.jquery;
    var myid = document.getElementById("id").value;
    var mycode = document.getElementById("mycode").value;
    var myareaId = document.getElementById("myareaId").value;
    var myfirmId = document.getElementById("firmId").value;
    var mystate = document.getElementById("state").value;
    var myprice = document.getElementById("price").value;
    $.ajax({
        url: 'updateRecruit_seat',
        type: "post",
        data: {
            id: myid,
            code: mycode,
            areaid:myareaId,
            firmid:myfirmId,
            state:mystate,
            price:myprice
        },
        success: function (msg) {
            if (msg.code === 1) {
                console.log(msg);
                layer.msg("添加成功!", {icon: 6});
            } else {
                layer.msg("删除失败!未知错误", {icon: 5});
            }
        }
    });
    parent.layer.close(index);
}