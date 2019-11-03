layui.use('form', function(){
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
function getdata(id, name) {
    document.getElementById("id").value=id;
    document.getElementById("name").value=name;
}
function updatebutton() {
    var index = parent.layer.getFrameIndex(window.name);
    var $ = layui.jquery;
    var myid = document.getElementById("id").value;
    var myname = document.getElementById("name").value;
    $.ajax({
        url: 'updateRecruit_area',
        type: "post",
        data: {
            id: myid,
            name: myname
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