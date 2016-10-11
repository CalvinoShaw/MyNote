$(document).ready(function(){
    
    $("#register-part").hide();
    
    $("#login-part > form > div:eq(2) button:eq(1)").click(function(){
        $("#login-part").hide();
        $("#register-part").fadeIn(300);
        images = [
            "img/back-img/back1.jpg",
            "img/back-img/back2.jpg",
            "img/back-img/back3.jpg",
            "img/back-img/back4.jpg"
        ];
    });

    $("#register-part > form > div:eq(3) button:eq(1)").click(function(){
         $("#login-part").show(200);
         $("#register-part").slideUp(300);
         images = [
             "img/back-img/back6.jpg",
             "img/back-img/back7.jpg",
         ];
    });


    // 登录模块 - 用户名
    $("input:eq(0)").focus(function(){
        if($(this).val() == ""){
            $(".help-block:eq(0)").html("请输入用户名!");
        }
    }).keyup(function(){
        
        
        // name_tip.innerHTML='';
        // count.style.visibility="visible";
        // length_now=getLength(this.value);
        // count.innerHTML=length_now+"个字符";
        // if(length_now==0){
        // count.style.visibility="hidden";
        // name_tip.innerHTML='请输入6-25个字符，支持中文';
    }).blur(function(){
        $(".help-block:eq(0)").html("");
    });


    //登录模块 - 密码
    $("input:eq(1)").focus(function(){
        if($(this).val() == ""){
            $(".help-block:eq(1)").html("请输入密码!");
        }
    }).keyup(function(){

    }).blur(function(){
        $(".help-block:eq(1)").html("");
    });

    // 注册模块 - 用户名
    // $("input:eq(2)").keyup(checkName(this,$(".help-block:eq(2)")));
    //密码
    // $("input:eq(1)").keyup();
    //确认密码
    // $("input:eq(1)").keyup();


})

function AjaxINjQuery(username){
    var xhr = null;
    //IE5 IE6 是以 ActiveXObject 的方式引入 XMLHttpRequest 的
    if(window.ActiveXObject){
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }else if(window.XMLHttpRequest){
        xhr = new XMLHttpRequest();
    }
    xhr.open("GET", "login_do.do?flag=ajax?username="+username.value, true);
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4){
            if(xhr.status == 200){
                var username_tip = document.getElementById("username_tip");
                username_tip.innerHTML = xhr.responseText;
            }
        }
    };
    xhr.send(null);
}


// var length_now = 0;

// name_input.onfocus=function(){
//     name_tip.style.color="red";
//     name_tip.innerHTML='请输入6-25个字符，支持中文';
// }
// name_input.onkeyup=function(){
//     name_tip.innerHTML='';
//     count.style.visibility="visible";
//     length_now=getLength(this.value);
//     count.innerHTML=length_now+"个字符";
//     if(length_now==0){
//         count.style.visibility="hidden";
//         name_tip.innerHTML='请输入6-25个字符，支持中文';
//     }
// }

// name_input.onblur=function(){
//     //含有非法字符
//     var re = /[^\w\4e00-\u9fa5]/g;
//     if(re.test(this.value)){
//         name_tip.style.color="red";
//         name_tip.innerHTML='x 含有非法字符';
//     }else if(this.value==""){
//         name_tip.style.color="red";
//         name_tip.innerHTML='x 不能为空';
//     }else if(length_now>25){
//         name_tip.style.color="red";
//         name_tip.innerHTML='x 长度大于25个字符';
//     }else if(length_now<6){
//         name_tip.style.color="red";
//         name_tip.innerHTML='x 长度少于6个字符';
//     }else{
//         name_tip.style.color="green";
//         name_tip.innerHTML='√ 该用户名可用';
//     }
//     //不能为空
//     //长度超过25个字符
//     //长度少于6个字符
// }
// //密码框
// var em1=document.getElementById("em1");
// var em2=document.getElementById("em2");
// var em3=document.getElementById("em3");

// function finalstring(target,sample){//
//     var same_count = 0;
//     for (var i = 0; i < target.length; i++) {
//         if (target.charAt(i)==sample) {
//             same_count++;
//         }
//     }
//     return same_count;
// }

// pass_input.onfocus=function(){
//     pass_tip.style.color="red";
//     pass_tip.innerHTML='请输入6-16个字符，请使用数字加字母或符号的密码组合，不能单独使用数字、字母或符号';
    
// }
// pass_input.onkeyup=function(){
//     //中：大于5个字符
//     //强：大于10个字符
//     if(this.value.length>0){
//         em1.className="active";
//     }else{
//         em1.className="";
//     }
//     if(this.value.length>5){
//         em2.className="active";
//         pass_confirm.removeAttribute("disabled");
//         pass_confirm_tip.innerHTML='*请在输入一次';
//     }else{
//         em2.className="";
//         pass_confirm.setAttribute("disabled",0);
//         pass_confirm_tip.innerHTML='*必填';
//     }
//     if(this.value.length>10){
//         em3.className="active";
//     }else if(this.value.length<=10){
//         em3.className="";
//     }
// }
// pass_input.onblur=function(){
//     //不能为空
//     //不能全部使用相同的字符
//     var same_count = finalstring(this.value,this.value[0]);
//     //长度应该为6-16个字符
//     //不能全部为数字
//     var re_num = /[^\d]/g;
//     //不能全为字母
//     var re_word = /[^a-zA-Z]/g;
//     //OK
//     if(this.value==""){
//         pass_tip.style.color="red";
//         pass_tip.innerHTML='不能为空！';
//     }else if(same_count==this.value.length){
//         pass_tip.style.color="red";
//         pass_tip.innerHTML='不能全部使用相同的字符';
//     }else if(this.value.length<6 || this.value.length>16){
//         pass_tip.style.color="red";
//         pass_tip.innerHTML='长度应该为6-16个字符';
//     }else if(!re_num.test(this.value)){
//         pass_tip.style.color="red";
//         pass_tip.innerHTML='不能全部为数字';
//     }else if(!re_word.test(this.value)){
//         pass_tip.style.color="red";
//         pass_tip.innerHTML='不能全部为字母';
//     }else{
//         pass_tip.style.color="green";
//         pass_tip.innerHTML='该密码可用';
//     }

// }
// //密码校对框
// pass_confirm.onblur=function(){
//     if(this.value!=pass_input.value){
//         pass_confirm_tip.style.color="red";
//         pass_confirm_tip.innerHTML='两次输入的密码不一致';
//     }else{
//         pass_confirm_tip.style.color="green";
//         pass_confirm_tip.innerHTML='OK';
//     }
// }



// function doLogin(){
// 	var login_btn = document.getElementById("login_btn");
	
// }



