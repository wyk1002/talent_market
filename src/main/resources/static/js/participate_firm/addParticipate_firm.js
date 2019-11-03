layui.use('form', function(){
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
    });
});
function addParticipate_firmButton()
{
    var index=parent.layer.getFrameIndex(window.name);
    var $=layui.jquery;
    var firm_id=document.getElementById("firm_id").value;
    var recruit_fair_id=document.getElementById("recruit_fair_id").value;
    $.ajax({
        url: 'addParticipate_firm',
        type: "post",
        data: {
            firm_id:firm_id,
            recruit_fair_id:recruit_fair_id
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