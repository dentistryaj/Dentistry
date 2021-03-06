<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP List Users Records</title>
</head>
<body>
    <sql:setDataSource
        var="myDS"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/dentistry"
        user="root" password="root"
    />
     
    <sql:query var="listTopics"   dataSource="${myDS}">
        SELECT * FROM topic;
    </sql:query>
     
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of topics</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Link</th>
                <th>Desc</th>
                <th>ParentId</th>
                <th>Image Link</th>
            </tr>
            <c:forEach var="topic" items="${listTopics.rows}">
                <tr>
                    <td><c:out value="${topic.id}" /></td>
                    <td><c:out value="${topic.topic_name}" /></td>
                    <td><c:out value="${topic.topic_link}" /></td>
                    <td><c:out value="${topic.topic_description}" /></td>
                    <td><c:out value="${topic.parent_id}" /></td>
                    <td><a href="${topic.topic_link}">View image</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>