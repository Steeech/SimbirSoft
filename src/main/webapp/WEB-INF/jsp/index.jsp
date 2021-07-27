<%--
  Created by IntelliJ IDEA.
  User: An2D2
  Date: 09.09.2020
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@page language="java" trimDirectiveWhitespaces="true"%>
<html lang="en">
<head>
    <!-- Подключаем bootstrap CSS,
        Spring boot автоматически замапит ресурс благодаря зависимости webjars в pom.xml -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link href="${jstlCss}" rel="stylesheet" />

	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />

    <c:url value="/css/main.css" var="jstlCss" />
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>
<body>

<div class="container">
    <h1 class="cover-heading row  justify-content-center">SimbirSoft Backend Test Task</h1>
    <div class="card">

        <div class="row  justify-content-center align-items-center align-middle ">
            <div class="col">
                <form action="" method="POST" name="Submit" enctype="multipart/form-data">
                    <div class="form-group">
                        <div id="url">Enter URL = <input type="text" class="form-control" name="url" /></div>
                        <br>
                        <p>
                            <input type="submit" class="btn btn-primary" name="submit"  value="Submit"/>
                        </p>
                        <h3>Result: </h3>
                        <pre>${statistic}</pre>

                        <p>
                            <input type="submit" class="btn btn-primary" name="save" value="Save HTML"/>
                            <input type="submit" class="btn btn-primary" name="saveStatistic" value="Save Statistic"/>
                        </p>

                        <c:if test="${!error.isEmpty() }">
                            <div class="alert alert-danger" role="alert">
                                    ${error}
                            </div>
                        </c:if>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>