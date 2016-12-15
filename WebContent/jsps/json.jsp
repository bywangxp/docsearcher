<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="/DocSearcher/js/jquery-1.4.4.min.js"></script>  
<script type="text/javascript">  
     function test(){  
         var id=document.getElementById("id").value;          
         $.ajax({   
                type:"POST", //请求方式  
                url:"/DocSearcher/user/all", //请求路径  
                cache: false,     
                data:"id="+id,  //传参  
                dataType: 'json',   //返回值类型   
                success:function(msg){  
                	   $('#result').html(syntaxHighlight(msg));
                      
                   },  
                error:function(){  
                    alert("error");  
                }  
                });  
      } 
     function test2(){  
         var id=document.getElementById("kid").value;          
         $.ajax({   
                type:"POST", //请求方式  
                url:"/DocSearcher/user/get_all_kinds", //请求路径  
                cache: false,     
                data:"id="+id,  //传参  
                dataType: 'json',   //返回值类型   
                success:function(msg){  
                	   $('#result').html(syntaxHighlight(msg));
                      
                   },  
                error:function(){  
                    alert("error");  
                }  
                });  
      } 
     
     function test3(){  
         var id=document.getElementById("pageid").value;          
         $.ajax({   
                type:"POST", //请求方式  
                url:"/DocSearcher/user/get_all_slides_img", //请求路径  
                cache: false,     
                data:"id="+id,  //传参  
                dataType: 'json',   //返回值类型   
                success:function(msg){  
                	   $('#result').html(syntaxHighlight(msg));
                      
                   },  
                error:function(){  
                    alert("error");  
                }  
                });  
      } 
     function syntaxHighlight(json) {
 	    if (typeof json != 'string') {
 	        json = JSON.stringify(json, undefined, 2);
 	    }
 	    json = json.replace(/&/g, '&').replace(/</g, '<').replace(/>/g, '>');
 	    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
 	        var cls = 'number';
 	        if (/^"/.test(match)) {
 	            if (/:$/.test(match)) {
 	                cls = 'key';
 	            } else {
 	                cls = 'string';
 	            }
 	        } else if (/true|false/.test(match)) {
 	            cls = 'boolean';
 	        } else if (/null/.test(match)) {
 	            cls = 'null';
 	        }
 	        return '<span class="' + cls + '">' + match + '</span>';
 	    });
 	}
     function test4(){  
         var id=document.getElementById("pid").value;          
         $.ajax({   
                type:"POST", //请求方式  
                url:"/DocSearcher/user/get_h5", //请求路径  
                cache: false,     
                data:"id="+id,  //传参  
                dataType: 'json',   //返回值类型   
                success:function(msg){  
                	   $('#result').html(syntaxHighlight(msg));
                      
                   },  
                error:function(){  
                    alert("error");  
                }  
                });  
      } 
     function test5(){  
         var search=document.getElementById("search").value;      
         var kid=document.getElementById("kid").value;   
         var sort_id=document.getElementById("sort_id").value;   
         var sort=document.getElementById("sort").value;   
         var mine=document.getElementById("mine").value;   
         $.ajax({   
                type:"POST", //请求方式  
                url:"/DocSearcher/user/get_h5", //请求路径  
                cache: false,     
                data: {kid:search,kid:kid,sort_id=sort_id,sort=sort,mine=mine},  //传参  
                dataType: 'json',   //返回值类型   
                success:function(msg){  
                	alert("sort_id:"+sort_id);
                	   $('#result').html(syntaxHighlight(msg));
                      
                   },  
                error:function(){  
                	alert("sort_id:"+sort_id);
                    alert("error");  
                }  
                });  
      } 
    
</script>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
    pre {outline: 1px solid #ccc; padding: 5px; margin: 5px; }
    .string { color: green; }
    .number { color: darkorange; }
    .boolean { color: blue; }
    .null { color: magenta; }
    .key { color: red; }
</style>
<title>json测试</title>
</head>
<body>
欢迎,${user.userName}
<h1>欢迎进入json数据交互测试阶段</h1>
 <form action="" method="post" >  
请输入你要测试的文档id：  <input type="text" name="id" id="id" ></br>
                       <input type="button" name="b" value="测试" onclick=test()>   <br/>
                        </form>  
      <form action="" method="post" >                  
请输入你要测试的文档id：  
<input type="text" name="kid" id="kid" ></br>
 <input type="button" name="b" value="测试" onclick=test2()/> <br/>

    <pre id="result">

</pre>

<h>搜索结果页展示</h>
 <form action="" method="post" >  
请输入搜索内容：
<input type="text" name="search" id="search" ></br>
请输入分类id
<input type="text" name="pageid" id="kid" ></br>
请输入排序排序方式id，1文件名  2贡献者  3下载次数  4收藏次数 5 评分  6上传时间
<input type="text" name="pageid" id="sort_id" ></br>
情输入排序方式，0降序，1升序
<input type="text" name="pageid" id="sort" ></br>
是否只显示我的文库数据，0否，1是
<input type="text" name="pageid" id="mine" ></br>
<input type="button" name="b" value="测试" onclick=test5()>   <br/>
 </form>  
 

<br/> <a href="http://localhost:8080/DocSearcher/user/get_user_detail">2.测试用户信息</a>
<br/> <a href="http://localhost:8080/DocSearcher/user/get_user">3.测试基本信息信息</a>
<br/> 
4.获取分类标签
 <form action="" method="post" >  
 请输入分类id：
<input type="text" name="pageid" id="kid" ></br>
<input type="button" name="b" value="测试" onclick=test2()>   <br/>
</form>  

<hr/>

<h>接口测试2：ppt展示页</h>
1.获取指定PPT的所有页面的缩略图
                   

 <form action="" method="post" >  
  请输入你要测试的pageid： 
    <input type="text" name="pageid" id="pageid" ></br>
    <input type="button" name="b" value="测试" onclick=test4()> <br/>
</form>  

2.获取单页PPT的H5数据


  <form action="" method="post" >  
         请输入你要测试的pid(单页PPT的pageid)：
    <input type="text" name="pid" id="pid" ></br>
    <input type="button" name="b" value="测试" onclick=test4()/><br/>
  </form>  
</body>
</html>