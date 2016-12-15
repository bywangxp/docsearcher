<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作成功</title>
</head>
<body>
欢迎,${user.userName}

${uploadInfo}
<a href="${pageContext.request.contextPath}/jsps/upload.jsp">点击上传文件</a>
<a href="${pageContext.request.contextPath}/jsps/json.jsp">json测试程序</a>

<a href='http://localhost:8080/DocSearcher/login.jsp'>点击进入首页</a><br/>
</body>
</html>