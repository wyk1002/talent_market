layui.use('form', function(){
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
function addJob_seekerButton()
{
    var index=parent.layer.getFrameIndex(window.name);
    var $=layui.jquery;
    var name=document.getElementById("name").value;
    var phone=document.getElementById("phone").value;
    var job_purpose=document.getElementById("job_purpose").value;
    $.ajax({
        url: 'addJob_seeker',
        type: "post",
        data: {
            name:name,
            phone:phone,
            job_purpose:job_purpose
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