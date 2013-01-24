<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Content-Style-Type" content="text/css" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="struts2, jquery, jquery-ui, plugin, deom" />
	<meta http-equiv="description" content="A Demo for the Struts2 jQuery Plugin" />
	<title>Struts2 JQuery Demo</title>
	
	<% String webPath = request.getContextPath(); %>
	<script type="text/javascript">
        var webpath = "<%=webPath%>";
        var $jQuery = $;
    </script>
	<script type="text/javascript" src="<%=webPath%>/js/grid.js"></script>
	<sj:head jqueryui="true"/>
</head>

<body>
	<sj:div id="main" href="jsp/grid.jsp">
		<img id="indicator" src="images/indicator.gif" alt="Loading..." />
	</sj:div>
</body>
</html>