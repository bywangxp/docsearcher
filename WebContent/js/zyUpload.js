(function($,undefined){
	$.fn.zyUpload = function(options,param){
		var otherArgs = Array.prototype.slice.call(arguments, 1);
		if (typeof options == 'string') {
			var fn = this[0][options];
			if($.isFunction(fn)){
				return fn.apply(this, otherArgs);
			}else{
				throw ("没有找到方法：" + options);
			}
		}

		return this.each(function(){
			var para = {};    // 保留参数
			var self = this;  // 保存组件对象
			
			var defaults = {
					width            : "700px",  					// 宽度
					height           : "400px",  					// 宽度
					itemWidth        : "140px",                     // 文件项的宽度
					itemHeight       : "120px",                     // 文件项的高度
					url              : "addUploadFiles.do",  	    // 上传文件的路径
					multiple         : true,  						// 是否可以多个文件上传
					dragDrop         : true,  						// 是否可以拖动上传文件
					del              : true,  						// 是否可以删除文件
					finishDel        : false,  						// 是否在上传文件完成后删除预览
					/* 提供给外部的接口方法 */
					onSelect         : function(selectFiles, files){}, // 选择文件的回调方法  selectFile:当前选中的文件  allFiles:还没上传的全部文件
					onDelete		 : function(file, files){},        // 删除一个文件的回调方法 file:当前删除的文件  files:删除之后的文件
					onSuccess		 : function(file){},               // 文件上传成功的回调方法
					onFailure		 : function(file){},               // 文件上传失败的回调方法
					onComplete		 : function(responseInfo){},       // 上传完成的回调方法
			};
			
			para = $.extend(defaults,options);
			
			this.init = function(){
				this.createHtml();  // 创建组件html
				this.createCorePlug();  // 调用核心js
			};
			
			/**
			 * 功能：创建上传所使用的html
			 * 参数: 无
			 * 返回: 无
			 */
			this.createHtml = function(){
				var multiple = "";  // 设置多选的参数
				para.multiple ? multiple = "multiple" : multiple = "";
				var html= '';
				
				if(para.dragDrop){
					// 创建带有拖动的html
					html += '<form id="uploadForm" action="'+para.url+'" method="post" enctype="multipart/form-data">';
					html += '	<div class="upload_box">';
					html += '		<div class="upload_main">';
					html += '			<div class="upload_choose">';
	            	html += '				<div class="convent_choice">';
	            	html += '					<div class="andArea">';
	            	html += '						<div class="filePicker btn btn-default" id="filePicker">点击选择文件</div>';
	            	html += '						<input id="fileImage" type="file" size="30" name="fileselect[]" '+multiple+' accept="application/pdf,application/msword,application/vnd.ms-powerpoint">';
	            	html += '					</div>';
	            	html += '				</div>';
					html += '				<span id="fileDragArea" class="upload_drag_area">或者将文件拖到此处</span>';
					html += '			</div>';
		            html += '			<div class="status_bar">';
		            html += '				<div id="status_info" class="info"></div><div style="clear:both;"></div>';
		            html += '			</div>';
					html += '		</div>';
					html += '	</div>';
					html += '</form>';
				}
				
	            $(self).append(html).css({"width":para.width,"height":para.height});
	            
	            // 初始化html之后绑定按钮的点击事件
	            this.addEvent();
			};
			
			/**
			 * 功能：显示统计信息和绑定继续上传和上传按钮的点击事件
			 * 参数: 无
			 * 返回: 无
			 */
			this.funSetStatusInfo = function(files){
				var size = 0;
				var num = files.length;
				$.each(files, function(k,v){
					// 计算得到文件总大小
					size += v.size;
				});
				
				// 转化为kb和MB格式。文件的名字、大小、类型都是可以现实出来。
				if (size > 1024 * 1024) {                    
					size = (Math.round(size * 100 / (1024 * 1024)) / 100).toString() + 'MB';                
				} else {                    
					size = (Math.round(size * 100 / 1024) / 100).toString() + 'KB';                
				}
			};
			
			/**
			 * 功能：过滤上传的文件格式等
			 * 参数: files 本次选择的文件
			 * 返回: 通过的文件
			 */
			this.funFilterEligibleFile = function(files){
				var arrFiles = [];  // 替换的文件数组
				var fileName;
				var fileType;
				for (var i = 0, file; file = files[i]; i++) {
					fileName =  file.name;
					fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
					if(fileType==".pdf"||fileType==".doc"||fileType==".docx"||fileType==".ppt"
						||fileType==".pptx"){
						if (file.size >= 1024000000) {
							alert('您这个"'+ file.name +'"文件大小过大');	
						} else {
							// 在这里需要判断当前所有文件中
							arrFiles.push(file);	
						}
					}else{
						alert("暂不支持该类型文件打印！");
					}
				}
				return arrFiles;
			};
			
			/**
			 * 功能：获取字符串字节数
			 */
			function cutString(str,len,suffix){
				if(!str) return "";
				if(len<= 0) return "";
				if(!suffix) suffix = "";
				var templen=0;
				for(var i=0;i<str.length;i++){
					if(str.charCodeAt(i)>255){
						templen+=2;
					}else{
						templen++;
					}
					if(templen == len){
						return str.substring(0,i+1)+suffix;
					}else if(templen >len){
						return str.substring(0,i)+suffix;
					}
				}
			        return str;
			}
			
			/**
			 * 功能： 处理参数和格式上的预览html
			 * 参数: files 本次选择的文件
			 * 返回: 预览的html
			 */
			this.funDisposePreviewHtml = function(file, e){
				var size = 0;
			    var fileName;
				size = file.size;
				
				// 转化为kb和MB格式。文件的名字、大小、类型都是可以现实出来。
				if (size > 1024 * 1024) {                    
					size = (Math.round(size * 100 / (1024 * 1024)) / 100).toString() + 'MB';                
				} else {                    
					size = (Math.round(size * 100 / 1024) / 100).toString() + 'KB';                
				} 
				var html = "";
				fileName = file.name;
				fileName = cutString(fileName,16,'...');
				html = '<div class="file_content">'
					  +'<span class="file_info"><span class="file_name" title="'+ file.name +'">' + fileName +'(' + size +')' + '</span></span>'
				       +"<span class='file_progress' title='file_progress_"+file.name+"'></span></div>";
				return html;
			};
			
			/**
			 * 功能：调用核心插件
			 * 参数: 无
			 * 返回: 无
			 */
			this.createCorePlug = function(){
				var params = {
					fileInput: $("#fileImage").get(0),
					uploadInput: $("#file-submit").get(0),
					dragDrop: $("#fileDragArea").get(0),
					url: $("#uploadForm").attr("action"),
					
					filterFile: function(files) {
						// 过滤合格的文件
						return self.funFilterEligibleFile(files);
					},
					onSelect: function(selectFiles, allFiles) {
						para.onSelect(selectFiles, allFiles);  // 回调方法
						self.funSetStatusInfo(ZYFILE.funReturnNeedFiles());  // 显示统计信息
						var html = '', i = 0;
						// 组织预览html
						var funDealtPreviewHtml = function() {
							file = selectFiles[i];
							if (file) {
								var reader = new FileReader()
								reader.onload = function(e) {
									// 处理下配置参数和格式的html
									html += self.funDisposePreviewHtml(file, e);
									i++;
									// 再接着调用此方法递归组成可以预览的html
									funDealtPreviewHtml();
								}
								reader.readAsDataURL(file);
							} else {
								// 走到这里说明文件html已经组织完毕，要把html添加到预览区
								funAppendPreviewHtml(html);
							}
						};
						
						// 添加预览html
						var funAppendPreviewHtml = function(html){
							// 添加到添加按钮前
							$("#status_info").append(html);
							// 绑定删除按钮
							funBindDelEvent();
							funBindHoverEvent();
						};
						
						// 绑定删除按钮事件
						var funBindDelEvent = function(){
							if($(".file_del").length>0){
								// 删除方法
								$(".file_del").click(function() {
									ZYFILE.funDeleteFile(parseInt($(this).attr("data-index")), true);
									return false;	
								});
							}
							
							if($(".file_edit").length>0){
								// 编辑方法
								$(".file_edit").click(function() {
									// 调用编辑操作
									return false;	
								});
							}
						};
						
						// 绑定显示操作栏事件
						var funBindHoverEvent = function(){
							$(".upload_append_list").hover(
								function (e) {
									$(this).find(".file_bar").addClass("file_hover");
								},function (e) {
									$(this).find(".file_bar").removeClass("file_hover");
								}
							);
						};
						
						funDealtPreviewHtml();
						ZYFILE.funUploadFiles();
					},
					onDelete: function(file, files) {
						// 移除效果
						$("#uploadList_" + file.index).fadeOut();
						// 重新设置统计栏信息
						self.funSetStatusInfo(files);
						console.info("剩下的文件");
						console.info(files);
					},
					onProgress: function(file, loaded, total) {
						var eleProgress = $("span[title='file_progress_"+file.name+"']"), percent = (loaded / total * 100).toFixed(3) + '%';
						eleProgress.css("width",percent);
						eleProgress.show();
					},
					onSuccess: function(file, response) {debugger
						//回写文件信息
						$("span[title='file_progress_"+file.name+"']").show();
						var obj = JSON.parse(response);
						
						if(obj.success){
							var fileArray = obj.result;
							for(var i=0;i<fileArray.length;i++){
								if(fileArray[0].filePages>0){
									$('span[title="'+file.name+'"]').attr("id",fileArray[0].id);
									$('span[title="'+file.name+'"]').after("<span>&nbsp;&nbsp;共"+fileArray[0].filePages+"页</sapn>");
									$('span[title="'+file.name+'"]').after("<span class='glyphicon glyphicon-ok' aria-hidden='true'></span>");
									$('span[title="'+file.name+'"]').after("<span class='del-file valid-file unvisible'><span class='glyphicon glyphicon-remove' aria-hidden='true'></span>&nbsp;&nbsp;删除</span>");
									$('#fileIds').append("<input type='text' name='fileId' class='hidden' value='"+fileArray[0].id+"'>");
								}else{
									$('span[title="'+file.name+'"]').after("<span class='del-file'><span class='glyphicon glyphicon-remove' aria-hidden='true'></span>&nbsp;&nbsp;上传错误，点击删除！</span>");
								}
							}
						}else{
							$('span[title="'+file.name+'"]').after("<span class='del-file'><span class='glyphicon glyphicon-remove' aria-hidden='true'></span>&nbsp;&nbsp;解析错误，点击删除！</span>");
						}
						
						// 删除上传中的文件
						$(".del-file").click(function(e){
							var id = $(this).prev().attr("id");
							var fileName = $(this).prev().attr("title");
							var self = $(this);
							if(id){
								// 解析正确并成功上传的文件
								confirm("确定删除？",function(){
									$.ajax({
										cache : true,
										type : "post",
										async : true,
										url : "removeFile.do",
										data : {fileId: id},
										dataType : 'json',
										error : function(data) {
										},
										success : function(data) {
											self.parent().parent().remove();
											for(var filesId = 0; filesId<ZYFILE.lastUploadFile.length; filesId++){
												if(fileName == ZYFILE.lastUploadFile[filesId].name){
													ZYFILE.lastUploadFile.splice(filesId,1);
													break;
												}
											}
										}
									});
								});
							}
							else{
								// 解析错误的文件
								self.parent().parent().remove();
								for(var filesId = 0; filesId<ZYFILE.lastUploadFile.length; filesId++){
									if(fileName == ZYFILE.lastUploadFile[filesId].name){
										ZYFILE.lastUploadFile.splice(filesId,1);
										break;
									}
								}
							}
						});
						
						// 悬停显示删除按钮
						$(".file_content").mouseover(function(e){
							$(this).find(".del-file.valid-file").removeClass("unvisible");
						});
						// 悬停隐藏删除按钮
						$(".file_content").mouseout(function(e){
							$(this).find(".del-file.valid-file").addClass("unvisible");
						});
						
						// 根据配置参数确定隐不隐藏上传成功的文件
						if(para.finishDel){
							// 移除效果
							$("#uploadList_" + file.index).fadeOut();
							// 重新设置统计栏信息
							self.funSetStatusInfo(ZYFILE.funReturnNeedFiles());
						}
					},
					onFailure: function(file) {
						$("span[title='file_progress_"+file.name+"']").show();
						$("#uploadInf").append("<span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"></span>");
					},
					onComplete: function(response){
						console.info(response);
					},
					onDragOver: function() {
						$(this).addClass("upload_drag_hover");
					},
					onDragLeave: function() {
						$(this).removeClass("upload_drag_hover");
					}

				};
				
				ZYFILE = $.extend(ZYFILE, params);
				ZYFILE.init();
			};
			
			/**
			 * 功能：绑定事件
			 * 参数: 无
			 * 返回: 无
			 */
			this.addEvent = function(){
				// 如果快捷添加文件按钮存在
				if($(".filePicker").length > 0){
					// 绑定选择事件
					$(".filePicker").bind("click", function(e){
		            	$("#fileImage").click();
		            });
				}
	            
				// 绑定继续添加点击事件
				$(".webuploader_pick").bind("click", function(e){
	            	$("#fileImage").click();
	            });
				
				// 绑定上传点击事件
				$(".upload_btn").bind("click", function(e){
					// 判断当前是否有文件需要上传
					if(ZYFILE.funReturnNeedFiles().length > 0){
						$("#fileSubmit").click();
					}else{
						alert("请先选中文件再点击上传");
					}
	            });
				
				// 如果快捷添加文件按钮存在
				if($("#rapidAddImg").length > 0){
					// 绑定添加点击事件
					$("#rapidAddImg").bind("click", function(e){
						$("#fileImage").click();
		            });
				}
			};

			// 初始化上传控制层插件
			this.init();
		});
	};
})(jQuery);