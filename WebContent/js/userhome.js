$(function(){


// 上传头像
 	$('#pic').click(function(){
	$('#upload').click();

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
			url:"localhost",
			type:"POST",
			data:'url='+url,
			success:function(){}
		});
	});
		fd.append('file',blob);
		
	});
// 666666666666666

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
		var data = 'name='+iname+'&address='+iaddr+'&iphone='+iphone+'&email='+iemail;
		// console.log(data);
		$.ajax({
			url:'/user/test',
			type:"POST",
			data:data,
	 		success:function(){
	 				alert('传输成功');
	 			},
	 		error:function(){
	 				alert("sorry,submit has a bug!");
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
			url:'/user/test',
			type:"POST",
			data:tdata,
 			success:function(){
 				alert('ok');
 			},
 			error:function(){
 				alert("sorry,siganiture has a bug!");
 			}
		});
	});

});
