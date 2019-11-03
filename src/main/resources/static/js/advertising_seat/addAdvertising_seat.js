layui.use('form', function(){
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
function addAdvertising_seat()
{
    var index=parent.layer.getFrameIndex(window.name);
    var $=layui.jquery;
    var advertising_seat_num = document.getElementById("advertising_seat_num").value;
    var firm_id = document.getElementById("firm_id").value;
    var remain_time = document.getElementById("remain_time").value;
    var price = document.getElementById("price").value;
    var state = document.getElementById("state").value;
    // alert("code:"+mycode+"  areaid:"+myareaId+"  firmid:"+myfirmId+"  state:"+mystate+"  price:"+myprice);
    $.ajax({
        url: 'addAdvertising_seat',
        type: "post",
        data: {
            advertising_seat_num: advertising_seat_num,
            firm_id:firm_id,
            remain_time:remain_time,
            price:price,
            state:state
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