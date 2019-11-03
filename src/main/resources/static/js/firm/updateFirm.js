layui.use('form', function(){
    var form = layui.form;
    var $=layui.$;

    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
function getdata(id, name,principal_name,principal_phone) {
    document.getElementById("id").value=id;
    document.getElementById("name").value=name;
    document.getElementById("principal_name").value=principal_name;
    document.getElementById("principal_phone").value=principal_phone;
}
function updateFirmButton() {
    var index = parent.layer.getFrameIndex(window.name);
    var $ = layui.jquery;
    var id = document.getElementById("id").value;
    var name=document.getElementById("name").value;
    var principal_name=document.getElementById("principal_name").value;
    var principal_phone=document.getElementById("principal_phone").value;
    $.ajax({
        url: 'updateFirm',
        type: "post",
        data: {
            id: id,
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