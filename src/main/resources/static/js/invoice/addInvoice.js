layui.use('form', function(){
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
function addInvoiceButton()
{
    var index=parent.layer.getFrameIndex(window.name);
    var $=layui.jquery;
    var object_id=document.getElementById("object_id").value;
    var object_category=document.getElementById("object_category").value;
    var bargain_money=document.getElementById("bargain_money").value;
    var bargain_explain=document.getElementById("bargain_explain").value;
    $.ajax({
        url: 'addInvoice',
        type: "post",
        data: {
            object_id:object_id,
            object_category:object_category,
            bargain_money:bargain_money,
            bargain_explain:bargain_explain
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