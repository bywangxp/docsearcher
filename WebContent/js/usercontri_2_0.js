// 参考jquery分页插件，主要参考动态加载逻辑编写
// 设置默认参数
$(document).ready(function() {
var page = 0;

var opts = new Object();
    opts.leng = 10;
    opts.activeClass = 'activP';
    opts.firstPage = '首页';
    opts.lastPage = '尾页';
    opts.prv = '上一页';
    opts.next = '下一页';
var l=opts.leng;

  var totalP = null;
  var Id = [];

  // window.page=0;

  getTableInfo(0);
  alert(page);
  getPageStyle(opts);
  // getTableInfo(page);

  // 发送请求获取数据
  function getTableInfo(p) {
    alert(p);
    $.ajax({
      url: '/DocSearcher/user/contri_data',
      type: 'POST',
      data: 'page=' + p,
      dataType: 'json',
      success: function(json0) {
        var jsonObj=json0.tablerow;
        console.log(jsonObj);
        var oRow=[];
        for (var i = 0; i < jsonObj.length; i++) {
           oRow.push(jsonObj[i]);
         } 
        console.log(oRow);
        totalP = json0.totalpage; //总页数
        console.log(totalP);

        var rowData = '';

        for (var i = 0; i < oRow.length; i++) {
          rowData = '<li class="row clearfix">' + '<div class="n1">' + oRow[i].logo + '</div><div class="n2">' + oRow[i].docname + '</div><div class="n3">' + oRow[i].uptime + '</div><div class="n4">' + oRow[i].download + '</div><div class="n5">' + oRow[i].credit + '</div><div class="n6">' + oRow[i].score + '</div>' + '</li>' + rowData;
          Id.push(oRow[i].id);
        }
        $('#tRow').html(rowData);
      },
      error: function(XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status);
        console.log(XMLHttpRequest.readyState);
        console.log(textStatus);
      }
    });
  }


  // 定义操作对象


  // 获取分页栏样式并返回当前页码


   

  function getPageStyle(options) {
    var opts=options;
    var str1 = '';
    var str = '';
    // var l = opts.leng;
   

    if (l > 1 && l < 6) {
      //将选中框默认为第一页
      str1 = '<li><a href="javascript:" class="' + opts.activeClass + '">1</a></li>';
      for (i = 2; i < l + 1; i++) {
        // 动态生成多个li,存放除1以外的
        str += '<li><a href="javascript:">' + i + '</a></li>';
      }
    } else if (l > 5) {
      str1 = '<li><a href="javascript:" class="' + opts.activeClass + '">1</a></li>';
      // 只显示5个li
      for (i = 2; i < 6; i++) {
        str += '<li><a href="javascript:">' + i + '</a></li>';
      }
    } else {
      //若l值为其他，不做处理
      str += '<li><a href="javascript:">' + i + '</a></li>';
    }

    //动态创建div
    $('#page').html('<div class="first">' + opts.firstPage + '</div><div class="prv">' + opts.prv + '</div><ul class="pagingUl clearfix">' + str1 + str + '</ul><div class="next">' + opts.next + '</div><div class="last">' + opts.lastPage + '</div><div class="total">共' + opts.leng + '页</div>');
    //点击下一页按钮
}


    $('#page').on('click', '.next', function() {
      var pageshow = parseInt($('.' + opts.activeClass).html()); //取得高亮显示的页码值
      page = pageshow;
      
      // alert(pageshow);
      if (pageshow == l) {
        alert(l);
      }
      // 最后半部分显示
      else if (pageshow > l - 3 && pageshow < l) {
        $('.' + opts.activeClass).removeClass(opts.activeClass).parent().next().find('a').addClass(opts.activeClass);
      } else if (pageshow > 0 && pageshow < 4) {
        alert("how???")
        $('.' + opts.activeClass).removeClass(opts.activeClass).parent().next().find('a').addClass(opts.activeClass);
      } else {
        $('.' + opts.activeClass).removeClass(opts.activeClass).parent().next().find('a').addClass(opts.activeClass);
        fpageShow();
      }
      getTableInfo(page);
    });

    // 点击上一页按钮
    $('#page').on('click', '.prv', function() {
      var pageshow = parseInt($('.' + opts.activeClass).html());
      if (pageshow == 1) {
        page = 0;

      } else if (pageshow > l - 3 && pageshow < l + 1) {
        $('.' + opts.activeClass).removeClass(opts.activeClass).parent().prev().find('a').addClass(opts.activeClass);
        //this.fpageBranch(pageshow-1);
        page = pageshow - 2;
      } else if (pageshow > 1 && pageshow < 4) {
        $('.' + opts.activeClass).removeClass(opts.activeClass).parent().prev().find('a').addClass(opts.activeClass);
        //this.fpageBranch(pageshow-1);
        page = pageshow - 2;
      } else {
        $('.' + opts.activeClass).removeClass(opts.activeClass).parent().prev().find('a').addClass(opts.activeClass);
        //this.fpageBranch(pageshow-1);
        fpageShow();
        page = pageshow - 2;

      }
      getTableInfo(page);


    });
    // 点击首页
    $('#page').on('click', '.first', function() {
      var pageshow = 1;
      page=pageshow;
     
      // return pageshow;
      // alert(page);
      $('.' + opts.activeClass).removeClass(opts.activeClass).parent().prev().find('a').addClass(opts.activeClass);
      fpagePrv(0);
       getTableInfo(page);

    });
    // 点击尾页
    $('#page').on('click', '.last', function() {
      var pageshow = l;
      // alert(pageshow);
      page = l - 1;
      
      if (l > 5) {
        $('.' + opts.activeClass).removeClass(opts.activeClass).parent().prev().find('a').addClass(opts.activeClass);
        fpageNext(4);
      } else {
        $('.' + opts.activeClass).removeClass(opts.activeClass).parent().prev().find('a').addClass(opts.activeClass);
        fpageNext(l - 1);
      }
      getTableInfo(page);

    });
    // alert(page);
    // 点击页码
    $('#page').on('click', 'li', function() {
      var $this = $(this);
      var pageshow = $this.find('a').html();
      page = pageshow - 1;
      
      // alert(pageshow);
      if (l > 5) {
        if (pageshow > l - 3 && pageshow < l + 1) {
          $('.' + opts.activeClass).removeClass(opts.activeClass);
          $this.find('a').addClass(opts.activeClass);
          fpageNext(4 - (l - pageshow)); //使高亮页码在中间位置
        } else if (pageshow > 0 && pageshow < 3) {
          $('.' + opts.activeClass).removeClass(opts.activeClass);
          $this.find('a').addClass(opts.activeClass);
          fpagePrv(pageshow - 1); //页码值从1开始，而参数从0计
        } else {
          $('.' + opts.activeClass).removeClass(opts.activeClass);
          $this.find('a').addClass(opts.activeClass);
          fpageShow();
        }
      } else {
        $('.' + opts.activeClass).removeClass(opts.activeClass);
        $this.find('a').addClass(opts.activeClass);
      }
      getTableInfo(page);
    });

    function fpageShow() {
      var pageshow = parseInt($('.' + opts.activeClass).html());
      var pageStart = pageshow - 2; //控制显示多少个li
      var pageEnd = pageshow + 3;
      var str1 = '';
      // alert("fpageshow");
      for (i = 0; i < 5; i++) {
        // 动态生成看的见的li
        str1 += '<li><a href="javascript:" class="">' + (pageStart + i) + '</a></li>'
      }
      $('#page').find('ul').html(str1);
      // 最中间的显示activeClass;
      $('#page').find('ul li').eq(2).find('a').addClass(opts.activeClass);
      //******此处添加从后台获取数据的方法
    }

    function fpagePrv(prv) {
      var str1 = '';
      if (l > 4) {
        for (i = 0; i < 5; i++) {
          str1 += '<li><a href="javascript:" class="">' + (i + 1) + '</a></li>'
        }
      } else {
        for (i = 0; i < l; i++) {
          str1 += '<li><a href="javascript:" class="">' + (i + 1) + '</a></li>'
        }
      }
      $('#page').find('ul').html(str1);
      $('#page').find('ul li').eq(prv).find('a').addClass(opts.activeClass);
    }

    function fpageNext(next) {
      var str1 = '';
      if (l > 4) {
        for (i = l - 4; i < l + 1; i++) {
          str1 += '<li><a href="javascript:" class="">' + i + '</a></li>'
        }
        $('#page').find('ul').html(str1);
        $('#page').find('ul li').eq(next).find('a').addClass(opts.activeClass);
      } else {
        for (i = 0; i < l; i++) {
          str1 += '<li><a href="javascript:" class="">' + (i + 1) + '</a></li>'
        }
        $('#page').find('ul').html(str1);
        $('#page').find('ul li').eq(next).find('a').addClass(opts.activeClass);
      }
    }

});