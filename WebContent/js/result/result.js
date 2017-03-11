var url = window.document.location.href.toString();
var $_GET = (function(){
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

$(function($){

  $("#slideShow").hide();
  $("#slideShowList").show();
  $("#result_pic1").click(function(){
    $("#slideShow").animate({height:"0%"});
    $("#slideShowList").animate({height:"100%"});
    $("#slideShow").hide();
    $("#slideShowList").show();
  });
  $("#result_pic2").click(function(){
    $("#slideShowList").animate({height:"0%"});
    $("#slideShow").animate({height:"100%"});
    $("#slideShowList").hide();
    $("#slideShow").show();
  });

  $("#list").fadeOut(0);
  $("#fun2").fadeOut(0);
  $("#back").fadeOut(0);
  $(function(){ 
    //获取距离浏览器顶部距离
    var navH = $("#menu").offset().top;
    var navH2 = $("#fun").offset().top;
    //滚动条事件
    $(window).scroll(function(){
      //获取滚动条的滑动距离
      var scroH = $(this).scrollTop();
      if(scroH >= navH-65){
        $("#menu").animate({ top: "65px"},0);
      }else if(scroH < navH-65){
        $("#menu").animate({ top: "194px"},0);
      }
      if(scroH >= navH2){
        $("#fun2").fadeIn(500);
      }else if(scroH < navH2){
        $("#fun2").fadeOut(500);
      }
      if(scroH >= 300){
        $("#back").fadeIn(500);
      }else if(scroH < 300){
        $("#back").fadeOut(500);
      }
      $("#back").click(function(){  
        $('body,html').animate({scrollTop:0},1000);  
        return true;  
      });
      
      // console.log(scroH==navH);
    })
  });

  // $.ajax({ 
  //   type: "post", 
  //   url: "./user/get_all_slides?search=charpter&kid=2&sort_id=3&sort=6&mine=9", 
  //   dataType: "json", 
  //   data:{
  //     'search':$scope.search,
  //     'kid':$scope.menu_id,
  //     'sort_id':$scope.kind_sort_id,
  //     'sort':0,
  //     'mine':0,
  //   },
  //   success: function (data) { 
  //     $scope.slide = data.slide;
  //   }, 
  //   error: function (XMLHttpRequest, textStatus, errorThrown) { 
  //     // alert(errorThrown); 
  //   } 
  // });

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
app.config(function($httpProvider) {
  $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
  $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
 
  // Override $http service's default transformRequest
  $httpProvider.defaults.transformRequest = [function(data) {
    /**
     * The workhorse; converts an object to x-www-form-urlencoded serialization.
     * @param {Object} obj
     * @return {String}
     */
    var param = function(obj) {
      var query = '';
      var name, value, fullSubName, subName, subValue, innerObj, i;
 
      for (name in obj) {
        value = obj[name];
 
        if (value instanceof Array) {
          for (i = 0; i < value.length; ++i) {
            subValue = value[i];
            fullSubName = name + '[' + i + ']';
            innerObj = {};
            innerObj[fullSubName] = subValue;
            query += param(innerObj) + '&';
          }
        } else if (value instanceof Object) {
          for (subName in value) {
            subValue = value[subName];
            fullSubName = name + '[' + subName + ']';
            innerObj = {};
            innerObj[fullSubName] = subValue;
            query += param(innerObj) + '&';
          }
        } else if (value !== undefined && value !== null) {
          query += encodeURIComponent(name) + '='
              + encodeURIComponent(value) + '&';
        }
      }
 
      return query.length ? query.substr(0, query.length - 1) : query;
    };
 
    return angular.isObject(data) && String(data) !== '[object File]'
        ? param(data)
        : data;
  }];
  //开关按钮
  $('.toggle').click(function(e){
    e.preventDefault();
    $(this).toggleClass('toggle-on');
  });
});
app.controller('fun', function($scope,$http) {
  $scope.search= decodeURIComponent($_GET['search']);
  $scope.sortKind=[
                // {id:'0',name:'文件名'},
                {id:'1',name:'时间'},
                // {id:'2',name:'大小'},
                {id:'3',name:'评分'},
                {id:'4',name:'下载量'},
                {id:'5',name:'收藏次数'}];
  $scope.downloadList=[];
  
  /**
    * test data start
    * 
    */
  $scope.person={name:'bywangxp',level:'LV66'};
  $scope.searchKind=[{id:'1',name:'全文检索'},{id:'2',name:'标题'},{id:'3',name:'作者'}];
  $scope.menu=[{id:'1',name:'全部分类',num:'133',active:true},{id:'2',name:'课件',num:'33',active:false},{id:'3',name:'演示',num:'33',active:false},{id:'4',name:'科技',num:'33',active:false},{id:'5',name:'文学',num:'33',active:false},{id:'6',name:'情感',num:'33',active:false},{id:'7',name:'教程',num:'33',active:false},{id:'8',name:'其他',num:'33',active:false}];
  $scope.slide=[];
  $scope.user={id:'1',logo:'images/user_logo/user_logo.png',coin:'339',name:'bywangxp',inf:'孙府藏俊龙，剑出威四方，峰尖笑沧桑，情满散天下',myDoc:'33',allDoc:'133',fav:'13',download:'33'};
  /**
    * test data end
    * 
    */
  $scope.menu_id = $scope.menu[0].id;
  $scope.kind_search = 0;
  $scope.kind_sort = 0;
  $scope.kind_search_id = $scope.searchKind[$scope.kind_search].id;
  //总排序方式
  $scope.kind_sort_id = $scope.sortKind[$scope.kind_sort].id;

  $scope.removeDownloadList = function(index,item) {
    $scope.chooseAllFlag = false;
    $scope.downloadList.splice(index,1);
    for(var i=0; i<$scope.slide.length; i++){
      if(item.id == $scope.slide[i].id){
        $scope.slide[i].choose = false;
        $scope.slide[i].ifChoose = 'images/result/choose1.png';
        break;
      }
    }
    $scope.listNum = $scope.downloadList.length;
  }
  $scope.download = function() { 
	  var page_id="page_id=";
	  for(var i=0;i<$scope.downloadList.length;i++){
		  page_id += $scope.downloadList[i].page_id+",";
	  }
	  page_id = page_id.slice(0,-1);
	  console.log(page_id);
	  $http({
	      method: "POST", 
	      url: "/"+location.pathname.split("/")[1]+"/slides/download",
	      headers: {
	        'contentType': 'application/json'
	      },
	      data:page_id
	    }).
	    success(function(data) {
	       $scope.slide = data.slide;
	       window.location.href=data.downloadPath;
	       
	    }).
	    error(function(data, status) {
	      //$scope.data = data || "Request failed";
	      //$scope.status = status;
	    });
  
  }
  
  $scope.choose = function(i,item) {  
    $scope.chooseAllFlag = false;
    if(item.choose == true){
      item.choose = false;
      item.ifChoose='images/result/choose1.png';
      for(var j = 0,vlen = $scope.downloadList.length; j <= vlen; j++){ 
        if($scope.downloadList[j].id == item.id){ 
          $scope.downloadList.splice(j,1);
          $scope.listNum = $scope.downloadList.length;
          break;
        }
      }
    }else{
      item.choose = true;
      item.ifChoose='images/result/choose2.png';
      $scope.downloadList.push(item);
    }
    $scope.listNum = $scope.downloadList.length;
    //item.index = i;
  };
  $scope.fav = function(item){
    var data = 'page_id='+item.page_id+'&doc_id='+item.doc_id;
    if(item.ifaddFav == "images/result/star1.png"){
      item.ifaddFav = "images/result/star2.png";
      data += "&fav=true";
    }else{
      item.ifaddFav = "images/result/star1.png";
      data += "&fav=false";
    }
    $http({
      method: "POST", 
      url: "/"+location.pathname.split("/")[1]+"/slides/addfav.action",
      headers: {
        'contentType': 'application/json'
      },
      data:data
    }).
    success(function(data) {
       $scope.slide = data.slide;
    }).
    error(function(data, status) {
      //$scope.data = data || "Request failed";
      //$scope.status = status;
    });
  };

  $scope.onlyMineSwitch = 0;
  $scope.onlyMine = function(item){
    if($scope.onlyMineSwitch == 0){
      $scope.onlyMineSwitch = 1;
    }else{
      $scope.onlyMineSwitch = 0;
    }

    //重新请求数据
    var data = 'search='+$scope.search+'&kid='+$scope.menu_id+'&sort_id='+$scope.kind_sort_id+'&sort=0&mine='+$scope.onlyMineSwitch;
    $http({
      method: "POST", 
      url: "./slides/get_all_slides",
      headers: {
        'contentType': 'application/json'
      },
      data:data
    }).
    success(function(data) {
       $scope.slide = data.slide;
    }).
    error(function(data, status) {
      //$scope.data = data || "Request failed";
      //$scope.status = status;
    });
  };














  $scope.changeSort = function(item,i){
    $scope.kind_sort = i;
    $scope.kind_sort_id = $scope.sortKind[$scope.kind_sort].id;
    $scope.sort_0();
  }
  $scope.changeSearch = function(item,i){
    $scope.kind_search = i;
    $scope.kind_search_id = $scope.searchKind[$scope.kind_search].id;
  }
  $scope.chooseM = false;
  $scope.chooseMy = function(){
    $scope.chooseM = !$scope.chooseM;
  }
  $scope.menuActive = function(item){
    $scope.menu_id = item.id;
    for(var i=0; i<$scope.menu.length; i++){
      $scope.menu[i].active = false;
    }
    item.active = true;
    //重新请求数据
    var data = 'search='+$scope.search+'&kid='+$scope.menu_id+'&sort_id='+$scope.kind_sort_id+'&sort=0&mine='+$scope.onlyMineSwitch;
    $http({
      method: "POST", 
      url: "./slides/get_all_slides",
      headers: {
        'contentType': 'application/json'
      },
      data:data
    }).
    success(function(data) {
       $scope.slide = data.slide;
    }).
    error(function(data, status) {
      //$scope.data = data || "Request failed";
      //$scope.status = status;
    });
  }
  $scope.chooseAllFlag = false;
  $scope.chooseAll = function(){
    if($scope.chooseAllFlag == false){
      $scope.downloadList = [];
      for(var i=0; i<$scope.slide.length; i++){
        $scope.slide[i].choose = true;
        $scope.slide[i].ifChoose = 'images/result/choose2.png';
        $scope.downloadList.push($scope.slide[i]);
      }
      $scope.listNum = $scope.downloadList.length;
      $scope.chooseAllFlag = true;
    }else{
      for(var i=0; i<$scope.slide.length; i++){
        $scope.slide[i].choose = false;
        $scope.slide[i].ifChoose = 'images/result/choose1.png';
      }
      $scope.downloadList = [];
      $scope.listNum = $scope.downloadList.length;
      $scope.chooseAllFlag = false;
    }
  }
  //排序
  $scope.sort0 = false;
  $scope.sort1 = false;
  $scope.sort2 = false;
  $scope.sort3 = false;
  $scope.sort4 = false;
  $scope.sort5 = false;
  $scope.sort6 = false;
  $scope.sort_0 = function(){
    if($scope.sort0 == true){
      $scope.sort0 = false;
      if($scope.kind_sort_id == 1){
        $scope.slide.sort(function(a,b){
          return Date.parse(b.date) - Date.parse(a.date);
        });
      }else if($scope.kind_sort_id == 3){
        $scope.slide.sort(function(a,b){
          return b.grade - a.grade;
        });
      }else if($scope.kind_sort_id ==4){
        $scope.slide.sort(function(a,b){
          return b.downloadNum - a.downloadNum;
        });
      }else if($scope.kind_sort_id ==5){
        $scope.slide.sort(function(a,b){
          return b.addFavNum - a.addFavNum;
        });
      }
    }else{
      $scope.sort0 = true;
      if($scope.kind_sort_id == 1){
        $scope.slide.sort(function(a,b){
          return Date.parse(a.date) - Date.parse(b.date);
        });
      }else if($scope.kind_sort_id == 3){
        $scope.slide.sort(function(a,b){
          return a.grade - b.grade;
        });
      }else if($scope.kind_sort_id ==4){
        $scope.slide.sort(function(a,b){
          return a.downloadNum - b.downloadNum;
        });
      }else if($scope.kind_sort_id ==5){
        $scope.slide.sort(function(a,b){
          return a.addFavNum - b.addFavNum;
        });
      }
    }
  }
  $scope.sort_1 = function(){
    if($scope.sort1 == true){
      $scope.sort1 = false;
      $scope.slide.sort(function(a,b){
        return b.name - a.name;
      });
    }else{
      $scope.sort1 = true;
      $scope.slide.sort(function(a,b){
        return a.name - b.name;
      });
    }
  }
  $scope.sort_2 = function(){
    if($scope.sort2 == true){
      $scope.sort2 = false;
      $scope.slide.sort(function(a,b){
        return b.author - a.author;
      });
    }else{
      $scope.sort2 = true;
      $scope.slide.sort(function(a,b){
        return a.author - b.author;
      });
    }
  }
  $scope.sort_3 = function(){
    if($scope.sort3 == true){
      $scope.sort3 = false;
      $scope.slide.sort(function(a,b){
        return b.downloadNum - a.downloadNum;
      });
    }else{
      $scope.sort3 = true;
      $scope.slide.sort(function(a,b){
        return a.downloadNum - b.downloadNum;
      });
    }
  }
  $scope.sort_4 = function(){
    if($scope.sort4 == true){
      $scope.sort4 = false;
      $scope.slide.sort(function(a,b){
        return b.addFavNum - a.addFavNum;
      });
    }else{
      $scope.sort4 = true;
      $scope.slide.sort(function(a,b){
        return a.addFavNum - b.addFavNum;
      });
    }
  }
  $scope.sort_5 = function(){
    if($scope.sort5 == true){
      $scope.sort5 = false;
      $scope.slide.sort(function(a,b){
        return b.grade - a.grade;
      });
    }else{
      $scope.sort5 = true;
      $scope.slide.sort(function(a,b){
        return a.grade - b.grade;
      });
    }
  }
  $scope.sort_6 = function(){
    if($scope.sort6 == true){
      $scope.sort6 = false;
      $scope.slide.sort(function(a,b){
        return Date.parse(b.date) - Date.parse(a.date);
      });
    }else{
      $scope.sort6 = true;
      $scope.slide.sort(function(a,b){
        return Date.parse(a.date) - Date.parse(b.date);
      });
    }
  }
  $scope.search_commit = function(){
    $http({
      method: "POST", 
      url: "./slides/get_all_slides",
      headers: {
        'contentType': 'application/json'
      },
      data:{
        'search':$scope.search,
        'kid':$scope.menu_id,
        'sort_id':$scope.kind_sort_id,
        'sort':0,
        'mine':0,
      }
    }).
    success(function(data) {
       $scope.slide = data.slide;
    }).
    error(function(data, status) {
      //$scope.data = data || "Request failed";
      //$scope.status = status;
    });
  }

  //获取数据
  // $http.get("/user/get_all_slides").success(function (response) {
  //   $scope.slide = response.slide;
  // });

  // $http({
  //   method: "POST", 
  //   url: "./user/get_all_slides",
  //   headers: {
  //     'Content-Type': 'application/x-www-form-urlencoded'
  //   },
  //   data:{
  //     'search':$scope.search,
  //     'kid':$scope.menu_id,
  //     'sort_id':$scope.kind_sort_id,
  //     'sort':0,
  //     'mine':0,
  //   }
  // }).
  $http({
    method: "POST", 
    url: "./slides/get_all_slides",
    headers: {
      'contentType': 'application/json'
    },
    data:{
      'search':$scope.search,
      'kid':$scope.menu_id,
      'sort_id':$scope.kind_sort_id,
      'sort':0,
      'mine':0,
    }
  }).
  success(function(data) {
     $scope.slide = data.slide;
  }).
  error(function(data, status) {
    //$scope.data = data || "Request failed";
    //$scope.status = status;
  });

  $http({
    method: "POST", 
    url: "./user/get_user",
    headers: {
      'contentType': 'application/json'
    },
    data:{
    }
  }).
  success(function(data) {
     //$scope.person = data.person;
  }).
  error(function(data, status) {
    //$scope.data = data || "Request failed";
    //$scope.status = status;
  });

  $http({
    method: "POST", 
    url: "./user/get_user_detail",
    headers: {
      'contentType': 'application/json'
    },
    data:{
    }
  }).
  success(function(data) {
     //$scope.user = data.user;

     //$scope.user
     //$scope.user.inf="孙府藏俊龙，剑出威四方，峰尖笑沧桑，情满散天下";
  }).
  error(function(data, status) {
    //$scope.data = data || "Request failed";
    //$scope.status = status;
  });

  $http({
    method: "POST", 
    url: "./global/get_all_kinds",
    headers: {
      'contentType': 'application/json'
    },
    data:{
    }
  }).
  success(function(data) {
     $scope.menu = data.kind;
  }).
  error(function(data, status) {
    //$scope.data = data || "Request failed";
    //$scope.status = status;
  });

  // $.ajax({ 
  //   type: "post", 
  //   url: "./user/get_all_slides", 
  //   dataType: "json", 
  //   data:{
  //     'search':$scope.search,
  //     'kid':$scope.menu_id,
  //     'sort_id':$scope.kind_sort_id,
  //     'sort':0,
  //     'mine':0,
  //   },
  //   success: function (data) { 
  //     $scope.slide = data.slide;
  //   }, 
  //   error: function (XMLHttpRequest, textStatus, errorThrown) { 
  //     // alert(errorThrown); 
  //   } 
  // });


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