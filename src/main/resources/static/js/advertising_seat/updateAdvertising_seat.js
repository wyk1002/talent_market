layui.use('form', function(){
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
function getdata(id, advertisingSeatNum,firmId,remainTime,price,state) {
    layui.use('form', function () {
        var form = layui.form;
        var $ = layui.$;

        document.getElementById("id").value=id;
        document.getElementById("advertising_seat_num").value=advertisingSeatNum;
        document.getElementById("remain_time").value=remainTime;
        document.getElementById("price").value=price;
        $('#state').val(state);
        $("#firm_id").empty();
        if (state == "已出租") {
            for (var i = 0; i < firms.length; i++) {
                $("#firm_id").append(new Option(firms[i].name,firms[i].id))
            }
        }
        $('#firm_id').val(firmId);
        form.render('select');
    })
}
function updateAdvertising_seat() {
    var index = parent.layer.getFrameIndex(window.name);
    var $ = layui.jquery;
    var id = document.getElementById("id").value;
    var advertising_seat_num = document.getElementById("advertising_seat_num").value;
    var firm_id = document.getElementById("firm_id").value;
    var remain_time = document.getElementById("remain_time").value;
    var price = document.getElementById("price").value;
    var state = document.getElementById("state").value;
    $.ajax({
        url: 'updateAdvertising_seat',
        type: "post",
        data: {
            id: id,
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