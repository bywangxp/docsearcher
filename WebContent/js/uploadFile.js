$(function(){
	// 初始化插件
	$("#uploadFile").zyUpload({
		width            :   "100%",                 // 宽度
		height           :   "100%",                 // 高度
		itemWidth        :   "100px",                // 文件项的宽度
		itemHeight       :   "80px",                 // 文件项的高度
		url              :   "addUploadFiles.do",    // 上传文件的路径
		multiple         :   true,                   // 是否可以多个文件上传
		dragDrop         :   true,                   // 是否可以拖动上传文件
		del              :   true,                   // 是否可以删除文件
		finishDel        :   true,  				 // 是否在上传文件完成后删除预览
		/* 外部获得的回调接口 */
		onSelect: function(files, allFiles){         // 选择文件的回调方法
			console.info("当前选择了以下文件：");
			console.info(files);
			console.info("之前没上传的文件：");
			console.info(allFiles);
		},
		onDelete: function(file, surplusFiles){      // 删除一个文件的回调方法
			console.info("当前删除了此文件：");
			console.info(file);
			console.info("当前剩余的文件：");
			console.info(surplusFiles);
		},
		onSuccess: function(file){                   // 文件上传成功的回调方法
			console.info("此文件上传成功：");
			console.info(file);
		},
		onFailure: function(file){                   // 文件上传失败的回调方法
			console.info("此文件上传失败：");
			console.info(file);
		},
		onComplete: function(responseInfo){          // 上传完成的回调方法
			console.info("文件上传完成。");
			console.info(responseInfo);
		}
	});
	// 判断是否已经成功上传过文件
	$("#file-submit").click(function(e){
		if($("#fileIds").find("input").length<1){
			alert("还未上传任何有效文件！");
			e.preventDefault();
		}
	});
});

