layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    })

    //更换验证码
    $('#verifyimage').click(function() {
        var timestamp = new Date().getTime();
        $(this).attr('src',$(this).attr('src') + '?' +timestamp);
    });

    //登录按钮
    form.on("submit(login)",function(data){
        $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
        var username=$('#userName').val();
        var password=$("#password").val();
        var code=$("#code").val();
        if(username!=""&&password!=""&&code.length==4){
            $.ajax({
                type:"POST",
                url:"/user/login",
                dataType:"JSON",
                data:{
                    "userName":username,
                    "password":password,
                    "code":code
                },
                success:function(data){
                   console.log(data)
                }
            })
        }else{
            alert("请检查您的输入！");
        }
    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
