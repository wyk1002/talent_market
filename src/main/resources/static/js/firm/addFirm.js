layui.use('form', function(){
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
function addFirmButton()
{
    var index=parent.layer.getFrameIndex(window.name);
    var $=layui.jquery;
    var name=document.getElementById("name").value;
    var principal_name=document.getElementById("principal_name").value;
    var principal_phone=document.getElementById("principal_phone").value;
    $.ajax({
        url: 'addFirm',
        type: "post",
        data: {
            name:name,
            principal_name:principal_name,
            principal_phone:principal_phone
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