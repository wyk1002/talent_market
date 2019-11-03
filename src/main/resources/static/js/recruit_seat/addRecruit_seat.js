layui.use('form', function(){
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
function addRecruit_seat()
{
    var index=parent.layer.getFrameIndex(window.name);
    var $=layui.jquery;
    var mycode = document.getElementById("code").value;
    var myareaId = document.getElementById("areaId").value;
    var myfirmId = document.getElementById("firmId").value;
    var mystate = document.getElementById("state").value;
    var myprice = document.getElementById("price").value;
    // alert("code:"+mycode+"  areaid:"+myareaId+"  firmid:"+myfirmId+"  state:"+mystate+"  price:"+myprice);
    $.ajax({
        url: 'addRecruit_seat',
        type: "post",
        data: {
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