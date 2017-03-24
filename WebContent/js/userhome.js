$(function(){


// 上传头像
 	$('#pic').click(function(){
	// $('#upload').click();

	$('#upload').change(function(){
		var file=this.files[0];
		var reader=new FileReader();//读取本地类的对象
		var url;
		reader.onload=function(){
			 url=reader.result;
			$('#pic').attr('src',url);
		};
		reader.readAsDataURL(file);
		$.ajax({
			url:"/DocSearcher/user/home_img",
			type:"POST",
			data:'url='+url,
			success:function(){
				$('#tip').html('修改成功！').css('display','block');
				var timer=null;
				timer=setTimeout(function(){
					$('#tip').css('display','none');
				},500);
			},
			error:function(XMLHttpRequest,textStatus,errorThrown){
				console.log(XMLHttpRequest.status);
	 				console.log(XMLHttpRequest.readyState);
	 				console.log(textStatus);
			      }
		});
	});
		fd.append('file',blob);//此处是什么意思
		
	});
// 666666666666666

// 获取左侧用户栏信息/中栏信息
	var myChart1=echarts.init(document.getElementById('chart1'))
	var option1=
		{
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: { // 坐标轴指示器，坐标轴触发有效
			            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend: {
			        data: ['本月上传文档数', '本月收藏文档数', '本月文档被评论数', '平均分数'],
			        align: 'right',
			        right: 10
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: [{
			        type: 'category',
			        data: ['1月', '2月', '3月', '4月', '5月','6月','7月','8月','9月','10月','11月','12月']
			    }],
			    yAxis: [{
			        type: 'value',
			        name: '分数',
			        axisLabel: {
			            formatter: '{value}'
			        }
			    }],
			    series: [{
			        name: '本月上传文档数',
			        type: 'bar',
			        data: [20, 12, 31, 34, 31,34]
			    }, {
			        name: '本月收藏文档数',
			        type: 'bar',
			        data: [10, 20, 5, 9, 3,6]
			    }, {
			        name: '本月文档被评论数',
			        type: 'bar',
			        data: [1, 1, 2, 3, 1,4]
			    }, {
			        name: '平均分数',
			        type: 'line',
			        data: [60, 80, 50, 10,80,90]
			    }]
        };		
	myChart1.setOption(option1);


// 每次返回的数据
	$.ajax({
		url:'/DocSearcher/user/home_middle',
		type:'POST',
		data:'',
		datatype:'json',
		success:function(lText){
			var $name=$('.left').find('p.name');
			var $credit=$name.next().children('span:first-child');
			var $lvl=$name.next().children('span:last-child');
			var $asset=$('.asset-info').find('p.asset');
			var $doc=$asset.eq(0);
			var $rate=$asset.eq(1);
			var $collect=$asset.eq(2);
			

			$('#pic').src(lText.userimage)
			$name.text(lText.nickName);
			$credit.text(lText.credit);
			$lvl.text(lText.level);
			$asset.text(lText.collectnum);
			$doc.text(lText.docnum);
			$rate.text(lText.ratenum);

			var seriValue0=option1.series;
			var seriValue1=lText.series;
			seriValue[0].data=seriValue1[0].data;
			seriValue[1].data=seriValue1[1].data;
			seriValue[2].data=seriValue1[2].data;
			seriValue[3].data=seriValue1[3].data;

			var iname=lText.nickname;
	 		var iaddr=lText.location;
	 		var iphone=lText.phone;
	 		var iemail=lText.email;

			var sign=lText.description;
		},
		error:function(XMLHttpRequest,textStatus,errorThrown){
	 				console.log(XMLHttpRequest.status);
	 				console.log(XMLHttpRequest.readyState);
	 				console.log(textStatus);
	 				

	 			}
	});

// 6666666666666666

// 图表栏
	

// 点击“编辑”按钮触发事件

	$('.info-head span').click(dataEdit);
// 点击“提交”：隐藏该按钮，且表单恢复到只读状态
	$('#suBtn').click(Submit);
	
	function dataEdit(){
	// 表单变为可编辑状态
	 $('.form-group input').removeAttr('readonly');
	 // $('.form-group input').css("cursor","")
	// 显示提交按钮
	 $('#suBtn').css('visibility','visible');
	}

	function Submit(){	
		// 提交数据
		var iname=$('#nickName').val();
		var iaddr=$('#address').val();
		var iphone=$('#phonenum').val();
		var iemail=$('#email').val();

		var data = 'addre='+iname+'&address='+iaddr+'&iphone='+iphone+'&email='+iemail;
		// console.log(data);
		$.ajax({
			url:'/DocSearcher/user/home_right',
			type:"POST",
			data:data,
			dataType:'json',
	 		success:function(rText){	
	 				alert('传输成功');
	 				console.log(rText);//结果告诉我
	 				// 定义返回类型为json格式，返回一个json对象，不用解析
	 				// var iJson=Json.parse(responseText);
	 				iname=rText.nickname;
	 				iaddr=rText.location;
	 				iphone=rText.phone;
	 				iemail=rText.email;
	 			},
	 		error:function(XMLHttpRequest,textStatus,errorThrown){
	 				console.log(XMLHttpRequest.status);
	 				console.log(XMLHttpRequest.readyState);
	 				console.log(textStatus);
	 			}
		});
		$('#infoform input').each(function(){
			$(this).removeAttr('disabled');
		});
		// 隐藏按钮
		$(this).attr('visibility','hidden');
	}

	//签名框提交
	$('#sign').blur(function(){
		var tdata=$(this).val();
		alert(tdata);

		$.ajax({
			url:'/DocSearcher/user/home_sign',
			type:"POST",
			data:tdata,
			// dataType:'json',
 			success:function(){
 				alert('ok');
 			},
 			error:function(XMLHttpRequest,textStatus,errorThrown){
	 				console.log(XMLHttpRequest.status);
	 				console.log(XMLHttpRequest.readyState);
	 				console.log(textStatus);
 			}
		});
	});

});
