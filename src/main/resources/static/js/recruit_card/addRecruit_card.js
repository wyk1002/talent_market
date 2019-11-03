layui.use('form', function(){
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
function addRecruit_cardButton()
{
    var index=parent.layer.getFrameIndex(window.name);
    var $=layui.jquery;
    var object_id=document.getElementById("object_id").value;
    var object_category=document.getElementById("object_category").value;
    var remain_money=document.getElementById("remain_money").value;
    $.ajax({
        url: 'addRecruit_card',
        type: "post",
        data: {
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