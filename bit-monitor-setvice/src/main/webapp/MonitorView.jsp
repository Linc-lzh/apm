<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>监控中心页</title>
</head>

<body>
	<table class="table" >
		<tr>
			<td>服务名称</td>
			<td>方法名称</td>
			<td>执行次数</td>
			<td>失败次数</td>
			<td>最快时间</td>
			<td>最慢时间</td>
			<td>平均时间</td>
		</tr>
	
	<c:forEach items="${statis}" var="stati">  
	<tr>
		<td>
	        <c:out value="${stati.serviceName}"></c:out>  
	    </td>
	    <td>
	        <c:out value="${stati.serviceMethod}"></c:out>  
	    </td>
	    <td>
	        <c:out value="${stati.count}"></c:out>  
	    </td>
	    <td>
	        <c:out value="${stati.faills}"></c:out>  
	    </td>
	    <td>
	        <c:out value="${stati.fastTime}"></c:out>  
	    </td>
	    <td>
	        <c:out value="${stati.slowTime}"></c:out>  
	    </td>
	    <td>
	        <c:out value="${stati.avgTime}"></c:out>  
	    </td>
	    </tr>
    </c:forEach> 
	</table>
</body>
</html>