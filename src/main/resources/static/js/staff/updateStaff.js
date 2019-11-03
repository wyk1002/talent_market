layui.use('form', function(){
    var form = layui.form;
    var $=layui.$;

    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
function getdata(id, name,phone,position) {
    document.getElementById("id").value=id;
    document.getElementById("name").value=name;
    document.getElementById("phone").value=phone;
    document.getElementById("position").value=position;
}
function updateStaffButton() {
    var index = parent.layer.getFrameIndex(window.name);
    var $ = layui.jquery;
    var myid = document.getElementById("id").value;
    var myname = document.getElementById("name").value;
    var myphone = document.getElementById("phone").value;
    var myposition = document.getElementById("position").value;
    $.ajax({
        url: 'updateStaff',
        type: "post",
        data: {
            id: myid,
            name: myname,
            phone: myphone,
            position: myposition
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