var $_GET = (function(){
    var url = window.document.location.href.toString();
    var u = url.split("?");
    if(typeof(u[1]) == "string"){
        u = u[1].split("&");
        var get = {};
        for(var i in u){
            var j = u[i].split("=");
            get[j[0]] = j[1];
        }
        return get;
    } else {
        return {};
    }
})();
// jQuery(document).ready(function () {
// 	$(".rating-kv").rating();
// });

//引入ngSanitize服务，添加ng-bind-html对文本中html标签解析
var app = angular.module('ds', ['ngSanitize']);
app.controller('fun', function($scope,$http) {
  $scope.person={name:'CS逍遥剑仙',level:'LV1'};
  $scope.user={id:'1',logo:'images/user_logo/user_logo.png',coin:'339',name:'CS逍遥剑仙',inf:'孙府藏俊龙，剑出威四方，峰尖笑沧桑，情满散天下',myDoc:'33',allDoc:'133',fav:'13',download:'33'};
  $scope.pages=[{num:'01',pic:'images/slide_img/1478614897989_PPT_Chapter05_1.png'},{num:'02',pic:'images/slide_img/1478614897989_PPT_Chapter05_7.png'},{num:'03',pic:'images/slide_img/1478614897989_PPT_Chapter05_9.png'},{num:'04',pic:'images/slide_img/1478614897989_PPT_Chapter05_24.png'},{num:'05',pic:'images/slide_img/1478614897989_PPT_Chapter05_4.png'},{num:'06',pic:'images/slide_img/1478614897989_PPT_Chapter05_10.png'},{num:'07',pic:'images/slide_img/1478614897989_PPT_Chapter05_2.png'},{num:'08',pic:'images/slide_img/1478614897989_PPT_Chapter05_30.png'}];
  $scope.slide={id:'11',pic:'images/testpage.png',name:'软件工程课件',coin:'2',grade:'9.8',pageNow:'13',pageAll:'8',author:'孙剑峰',logo:'images/user_logo/user_logo.png',date:'2016-10-10',inf:'Long long ago, a team named <span>404</span> not found was established. 很久很久以前一个名为“<span>404</span>”的团队成立了。',downloadUrl:'123',downloadNum:'10',addFavNum:'22',ifaddFav:'images/result/star1.png'};          
  $scope.h5slide={data:
  	""
  };
  $scope.index = $_GET['slide'];

  //temp data
  $scope.index = 5;

  $scope.show=[];
  $scope.slide.pageNow = $scope.index;
  $scope.show[$scope.index] = true;
	
  $http({
    method: "POST", 
    headers: {
      'contentType': 'application/json'
    },
    url: "./user/get_all_slides_img",
    data:{
    }
  }).
  success(function(data) {
     $scope.pages = data.pages;
  }).
  error(function(data, status) {
    //$scope.data = data || "Request failed";
    //$scope.status = status;
  });

  $http({
    method: "POST", 
    headers: {
      'contentType': 'application/json'
    },
    url: "./user/get_h5",
    data:{
    }
  }).
  success(function(data) {
     $scope.h5slide = data.h5slide;
  }).
  error(function(data, status) {
    //$scope.data = data || "Request failed";
    //$scope.status = status;
  });
  
  $scope.change = function(index){
    for(var i=1;i<=$scope.pages.length;i++)
    {
      $scope.show[i] = false;
    }
    $scope.show[index+1] = true;
  };
});