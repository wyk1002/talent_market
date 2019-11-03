layui.use('form', function(){
    var form = layui.form;
    var $=layui.$;

    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
function getdata(id, object_id,object_category,remain_money) {
    layui.use('form',function () {
        var form=layui.form;
        var $=layui.$;
        document.getElementById("id").value=id;
        $('#object_category').val(object_category);
        $("#object_id").empty();
        if (object_category == "企业") {
            for (var i = 0; i < firms.length; i++) {
                $("#object_id").append(new Option(firms[i].name,firms[i].id))
            }
        }
        else if(object_category == "个人"){
            for (var j = 0; j < seekers.length; j++) {
                $("#object_id").append(new Option(seekers[j].name,seekers[j].id))
            }
        }
        $('#object_id').val(object_id);
        form.render('select');
        document.getElementById("remain_money").value=remain_money;
    });
}
function updateRecruit_cardButton() {
    var index = parent.layer.getFrameIndex(window.name);
    var $ = layui.jquery;
    var id=document.getElementById("id").value;
    var object_id=document.getElementById("object_id").value;
    var object_category=document.getElementById("object_category").value;
    var remain_money=document.getElementById("remain_money").value;
    $.ajax({
        url: 'updateRecruit_card',
        type: "post",
        data: {
            id: id,
            object_id:object_id,
            object_category:object_category,
            remain_money:remain_money
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