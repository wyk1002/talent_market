layui.use('form', function(){
    var form = layui.form;
    var $=layui.$;

    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
function getdata(id, name,phone,jobPurpose) {
    var form=layui.form;
    var $=layui.$;
    document.getElementById("id").value=id;
    document.getElementById("name").value=name;
    document.getElementById("phone").value=phone;
    $('#job_purpose').val(jobPurpose);
    form.render('select');
}
function updateJob_seekerButton() {
    var index = parent.layer.getFrameIndex(window.name);
    var $ = layui.jquery;
    var id = document.getElementById("id").value;
    var name=document.getElementById("name").value;
    var phone=document.getElementById("phone").value;
    var job_purpose=document.getElementById("job_purpose").value;
    $.ajax({
        url: 'updateJob_seeker',
        type: "post",
        data: {
            id: id,
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