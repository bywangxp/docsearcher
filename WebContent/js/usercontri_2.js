// 参考jquery分页插件，主要参考动态加载逻辑编写
(function($){
	// 设置默认参数
	var defaults={
		leng:5,
		avtiveClass:'page-active',
		firstPage:'首页',
		lastPage:'尾页',
		prv:'<<',
		next:'>>',
	};
	$.fn.extend({
		page:function(options){
			var opts=$.extend(defaults,options);
			return this.each(function(){
				var obj=$(this);//将当前执行权限给,,
				var str1='';
				var str='';
				var l=opts.leng;

				if(l>1&&l<6){
					//将选中框默认为第一页
					str1='<li><a href="javascript:" class="'+opts.avtiveClass+'">1</a></li>';
					for(i=2;i<l+1;i++){
						// 动态生成多个li,存放除1以外的
						str+='<li><a href="javascript:">' + i + '</a></li>';
					}

				}else if(l>5){
					str1='<li><a href="javascript:" class="'+opts.avtiveClass+'">1</a></li>';
					// 只显示5个li
					for(i=2;i<6;i++){
						str+='<li><a href="javascript:">' + i + '</a></li>';
					}
				}else{
					//若l值为其他，不做处理
					str+='<li><a href="javascript:">' + i + '</a></li>';
				}

				//动态创建div
				obj.html('<div class="first">'+opts.firstPage+'</div><div class="prv">'+opts.prv+'</div><ul class="clearfix">'+str1+str+'</ul><div class="next">'+opts.next+'</div><div class="last">'+opts.lastPage+'</div><div class="total">共'+opts.leng+'页</div>');

				obj.on('click','.next',function(){
					var pageshow=parseInt($('.'+opts.avtiveClass).html());//取得高亮显示的页码值
					getTableInfo(pageshow);//加载下一页数据

					if(pageshow==l){}
						// 最后半部分显示
						else if(pageshow>l-3&&pageshow<l){
							$('.'+opts.avtiveClass).removeClass(opts.avtiveClass).parent().next().find('a').addClass(opts.avtiveClass);		
						}
						//最前半部分显示
						else if(pageshow>0&&pageshow<4){
							$('.'+opts.avtiveClass).removeClass(opts.avtiveClass).parent().next().find('a').addClass(opts.avtiveClass);
						}
						//其他部分
						else{
						$('.'+opts.avtiveClass).removeClass(opts.avtiveClass).parent().next().find('a').addClass(opts.avtiveClass);
						fpageShow();}
				});

				obj.on('click', '.prv', function () {
          			var pageshow = parseInt($('.' + opts.activeClass).html());
          			getTableInfo(pageshow+'-2');
          			if (pageshow == 1) {
         			 			}else if(pageshow > l-3&&pageshow < l+1){
          			  $('.' + opts.activeClass).removeClass(opts.activeClass).parent().prev().find('a').addClass(opts.activeClass);
          			        //this.fpageBranch(pageshow-1);
          			}else if(pageshow > 1&&pageshow < 4){
          			  $('.' + opts.activeClass).removeClass(opts.activeClass).parent().prev().find('a').addClass(opts.activeClass);
          			        //this.fpageBranch(pageshow-1);
          			}else {
          			  $('.' + opts.activeClass).removeClass(opts.activeClass).parent().prev().find('a').addClass(opts.activeClass);
          			          //this.fpageBranch(pageshow-1);
          			  fpageShow();
          			}
        		});

        		obj.on('click', '.first', function(){
          			var pageshow = 1;
          			$('.' + opts.activeClass).removeClass(opts.activeClass).parent().prev().find('a').addClass(opts.activeClass);
          			fpagePrv(0);
          			getTableInfo('0');
        		});

        		 obj.on('click', '.last', function(){
          				var pageshow = l;
          				getTableInfo(l+'-1');
          				if(l>5){
            			$('.' + opts.activeClass).removeClass(opts.activeClass).parent().prev().find('a').addClass(opts.activeClass);
            			fpageNext(4);
          				}else{
            			$('.' + opts.activeClass).removeClass(opts.activeClass).parent().prev().find('a').addClass(opts.activeClass);
            			fpageNext(l-1);
          				}
        		});

        		obj.on('click', 'li', function(){
          			var $this = $(this);
          			var pageshow = $this.find('a').html();
          			getTableInfo(pageshow+'-1');
          			if(l>5){
            			if(pageshow > l-3&&pageshow < l+1){
              			$('.' + opts.activeClass).removeClass(opts.activeClass);
              			$this.find('a').addClass(opts.activeClass);
              			fpageNext(8-(l-pageshow));//使高亮页码在中间位置
            			}else if(pageshow > 0&&pageshow < 3){
              			$('.' + opts.activeClass).removeClass(opts.activeClass);
              			$this.find('a').addClass(opts.activeClass);
              			fpagePrv(pageshow-1);//页码值从1开始，而参数从0计
            			}else{
              			$('.' + opts.activeClass).removeClass(opts.activeClass);
              			$this.find('a').addClass(opts.activeClass);
              			fpageShow();
            			}
          			}else{
            			$('.' + opts.activeClass).removeClass(opts.activeClass);
            			$this.find('a').addClass(opts.activeClass);
          			}
        		});

        		function fpageShow(){
          			var pageshow = parseInt($('.' + opts.activeClass).html());
          			var pageStart = pageshow - 2;//控制显示多少个li
          			var pageEnd = pageshow + 3;
          			var str1 = '';
          			for(i=0;i<5;i++){
            		// 动态生成看的见的li
            			str1 += '<li><a href="javascript:" class="">' + (pageStart+i) + '</a></li>'
          			}
          			obj.find('ul').html(str1);
          			// 最中间的显示activeClass;
          			obj.find('ul li').eq(2).find('a').addClass(opts.activeClass);
          			//******此处添加从后台获取数据的方法
        		}

        		function fpagePrv(prv){
          var str1 = '';
          if(l>4){
            for(i=0;i<5;i++){
              str1 += '<li><a href="javascript:" class="">' + (i+1) + '</a></li>'
            }
          }else{
            for(i=0;i<l;i++){
              str1 += '<li><a href="javascript:" class="">' + (i+1) + '</a></li>'
            }
          }
          	obj.find('ul').html(str1);
          	obj.find('ul li').eq(prv).find('a').addClass(opts.activeClass);     		
                }
        		function fpageNext(next){
          var str1 = '';
          if(l>4){
            for(i=l-4;i<l+1;i++){
              str1 += '<li><a href="javascript:" class="">' + i + '</a></li>'
            }
            obj.find('ul').html(str1);
            obj.find('ul li').eq(next).find('a').addClass(opts.activeClass);
          }else{
            for(i=0;i<l;i++){
              str1 += '<li><a href="javascript:" class="">' + (i+1) + '</a></li>'
            }
          	obj.find('ul').html(str1);
          	obj.find('ul li').eq(next).find('a').addClass(opts.activeClass);
          }
        		}

      		 	function getTableInfo(page){
					$.ajax({
					url:'/DocSearcher/user/contribution',
					type:'POST',
					data:'p='+page,
					dataType:'json',
					success:function(json){
					var oRow=Json.parse(json.tablerow);
					var rowData='';
				
					for(var i=0;i<oRow.length;i++){
						rowData='<li class="row clearfix">'+'<div class="n1">'+oRow[i].logo+'</div><div class="n2">'+oRow[i].docname+'</div><div class="n3">'+oRow[i].uptime+'</div><div class="n4">'+oRow[i].download+'</div><div class="n5">'+oRow[i].credit+'</div><div class="n6">'+oRow[i].score+'</div>'+'</li>'+rowData;
						
					}
					$('#tRow').html(rowData);
					},
					error:function(XMLHttpRequest,textStatus,errorThrown){
						console.log(XMLHttpRequest.status);
	 					console.log(XMLHttpRequest.readyState);
	 					console.log(textStatus);
			      	}
			  		});
				}
		});
	 }
	});
})(jQuery);

