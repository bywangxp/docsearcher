$(function($){
  $("#upload").fadeOut(0);
  
  var flag_user = 0;
  var clientWidth = document.documentElement.clientWidth;
  if(clientWidth>768){
    $(".buttonImg").click(function(){
      if(flag_user === 0){
        $("#userCenter").animate({ right: "0px" }, 500);
        $(".buttonImg").rotate(90);
        flag_user = 1;
      }else{
        // $("#userCenter").css("right",-300);
        $("#userCenter").animate({ right: "-300px" }, 500);
        $(".buttonImg").rotate(0);
        flag_user = 0;
      }
    });
  }else{
    $(".buttonImg").click(function(){
      if(flag_user === 0){
        $("#userCenter").animate({ right: 0 }, 500);
        $(".buttonImg").rotate(90);
        flag_user = 1;
      }else{
        $("#userCenter").animate({ right: -clientWidth }, 500);
        $(".buttonImg").rotate(0);
        flag_user = 0;
      }
    });
  }
});