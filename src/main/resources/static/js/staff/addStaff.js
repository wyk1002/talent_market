layui.use('form', function(){
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
function addStaffButton()
{
    var index=parent.layer.getFrameIndex(window.name);
    var $=layui.jquery;
    var myname=document.getElementById("name").value;
    var myphone=document.getElementById("phone").value;
    var myposition=document.getElementById("position").value;
    $.ajax({
        url: 'addStaff',
        type: "post",
        data: {
            name:myname,
            phone:myphone,
            position:myposition
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