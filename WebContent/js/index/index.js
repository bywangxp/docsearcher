$(function($){
  $("#list").fadeOut(0);
});
// 显示遮罩层
function showOverlay() {
  $("#overlay").height(document.body.scrollHeight);
  $("#overlay").width(document.body.scrollWidth);
  // 速度、透明度
  $("#overlay").fadeTo(200, 0.8);
  // 解决窗口缩小时放大后不全屏遮罩
  $(window).resize(function(){
    $("#overlay").height(document.body.scrollHeight);
    $("#overlay").width(document.body.scrollWidth);
  });
  $("#list").fadeIn(200);
}
// 隐藏覆盖层
function hideOverlay() {
  $("#overlay").fadeOut(200);
  $("#list").fadeOut(200);
  $("#upload").fadeOut(200);
}
function showOverlayUpload() {
  $("#overlay").height(document.body.scrollHeight);
  $("#overlay").width(document.body.scrollWidth);
  // 速度、透明度
  $("#overlay").fadeTo(200, 0.8);
  // 解决窗口缩小时放大后不全屏遮罩
  $(window).resize(function(){
    $("#overlay").height(document.body.scrollHeight);
    $("#overlay").width(document.body.scrollWidth);
  });
  $("#upload").fadeIn(200);
}
//引入ngSanitize服务，添加ng-bind-html对文本中html标签解析
var app = angular.module('ds', ['ngSanitize']);
app.controller('fun', function($scope,$http) {
  $scope.search= "";
  $scope.person={name:'bywangxp',level:'LV1'};
  $scope.searchKind=[{id:'1',name:'全文检索'},{id:'2',name:'标题'},{id:'3',name:'作者'}];
  $scope.user={id:'1',logo:'images/user_logo/user_logo.png',coin:'339',name:'bywangxp',inf:'孙府藏俊龙，剑出威四方，峰尖笑沧桑，情满散天下',myDoc:'33',allDoc:'133',fav:'13',download:'33'};

  $scope.rank_top5=[{
                pic:'images/index/pic1.png',
                name:'下载排行',
                data:[{id:'1',rank:'1',name:'PPT的秘密',pic:'images/index/default_ppt.png'},{id:'1',rank:'2',name:'PPT的秘密',pic:'images/index/default_ppt.png'},{id:'1',rank:'3',name:'PPT的秘密',pic:'images/index/default_ppt.png'},{id:'1',rank:'4',name:'PPT的秘密',pic:'images/index/default_ppt.png'},{id:'1',rank:'5',name:'PPT的秘密',pic:'images/index/default_ppt.png'}]
              },
              {
                pic:'images/index/pic2.png',
                name:'检索热词',
                data:[{id:'1',rank:'1',name:'软件工程',pic:'images/index/default_word.png'},{id:'1',rank:'2',name:'软件工程',pic:'images/index/default_word.png'},{id:'1',rank:'3',name:'软件工程',pic:'images/index/default_word.png'},{id:'1',rank:'4',name:'PPT的秘密',pic:'images/index/default_word.png'},{id:'1',rank:'5',name:'PPT的秘密',pic:'images/index/default_word.png'}]
              },
              {
                pic:'images/index/pic3.png',
                name:'明星用户',
                data:[{id:'1',rank:'1',name:'bywangxp',pic:'images/user_logo/user_logo.png'},{id:'1',rank:'2',name:'孙剑峰',pic:'images/user_logo/user_logo.png'},{id:'1',rank:'3',name:'孙剑峰',pic:'images/user_logo/user_logo.png'},{id:'1',rank:'4',name:'孙剑峰',pic:'images/user_logo/user_logo.png'},{id:'1',rank:'5',name:'孙剑峰',pic:'images/user_logo/user_logo.png'}]
              }];


  $scope.inf=[{name:'软件介绍',inf:'这里是对DocSearcher的介绍；这里是对DocSearcher的介绍；这里是对DocSearcher的介绍；这里是对DocSearcher的介绍；这里是对DocSearcher的介绍；这里是对DocSearcher的介绍；这里是对DocSearcher的介绍；这里是对DocSearcher的介绍；'},
              {name:'使用说明',inf:'这里是对DocSearcher的使用说明；这里是对DocSearcher的使用说明；这里是对DocSearcher的使用说明；这里是对DocSearcher的使用说明；这里是对DocSearcher的使用说明；这里是对DocSearcher的使用说明；这里是对DocSearcher的使用说明；这里是对DocSearcher的使用说明；'},
              {name:'技术支持',inf:'这里是对404团队的介绍；这里是对404团队的介绍；这里是对404团队的介绍；这里是对404团队的介绍；这里是对404团队的介绍；这里是对404团队的介绍；这里是对404团队的介绍；这里是对404团队的介绍；这里是对404团队的介绍；这里是对404团队的介绍；'}];
  $scope.submitByEnter = function(){
    if(event.keyCode == 13)
    {
      // checkLogin();
      window.open("result.html?search="+$scope.search);
    }
  }
  //获取数据
  // $http.get("data/data.json").success(function (response) {$scope.slide = response.slide;});

});
function OpenFileDialog() 
{ 
    document.getElementById("fileToUpload").click(); 
} 
function fileSelected() {
  var file = document.getElementById('fileToUpload').files[0];
  if (file) {
    var fileSize = 0;
    if (file.size > 1024 * 1024)
      fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
    else
      fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
    document.getElementById('fileName').innerHTML = 'Name: ' + file.name;
    document.getElementById('fileSize').innerHTML = 'Size: ' + fileSize;
    document.getElementById('fileType').innerHTML = 'Type: ' + file.type;
    uploadFile();
  }
}
function uploadFile() {
  var fd = new FormData();
  fd.append("fileToUpload", document.getElementById('fileToUpload').files[0]);
  var xhr = new XMLHttpRequest();
  xhr.upload.addEventListener("progress", uploadProgress, false);
  xhr.addEventListener("load", uploadComplete, false);
  xhr.addEventListener("error", uploadFailed, false);
  xhr.addEventListener("abort", uploadCanceled, false);
  xhr.open("POST", "user/upload");//修改成自己的接口
  xhr.send(fd);
}
function uploadProgress(evt) {
  if (evt.lengthComputable) {
    var percentComplete = Math.round(evt.loaded * 100 / evt.total);
    document.getElementById('progressNumber').innerHTML = percentComplete.toString() + '%';
  }
  else {
    document.getElementById('progressNumber').innerHTML = '进度未知';
  }
}
function uploadComplete(evt) {
  /* 服务器端返回响应时候触发event事件*/
  alert(evt.target.responseText);
}
function uploadFailed(evt) {
  alert("上传失败");
}
function uploadCanceled(evt) {
  alert("用户取消");
}