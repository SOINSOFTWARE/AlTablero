<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="bg-black">
    <head>
        <title>Al Tablero | Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport' />
        <link href="<c:url value="/res/css/bootstrap.min.css" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/res/css/font-awesome.min.css" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/res/css/AdminLTE.css" />" rel="stylesheet" type="text/css" />
    </head>    
    <body class="bg-black" onload="document.loginForm.username.focus();">
        <div class="form-box" id="login-box">
            <div class="header">Iniciar sesi&oacute;n</div>
            <form name="loginForm" action="<c:url value='/j_spring_security_check' />" method='POST'>
                <div class="body bg-gray">
                    <div class="form-group">
                        <input type='text' name='username' class="form-control" required="true" placeholder="N&uacute;mero de documento" autocomplete="off" ></input>
                    </div>
                    <div class="form-group">
                        <input type='password' name='password' class="form-control" required="true" placeholder="Contrase&ntilde;a" autocomplete="off" ></input>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div class="footer">  
                    <input name="submit" type="submit" class="btn bg-olive btn-block" value="Iniciar sesi&oacute;n" />
                </div>
            </form>
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>     
        </div>
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src="<c:url value="/res/javascript/bootstrap.min.js" />" type="text/javascript"></script>
    </body>
</html>