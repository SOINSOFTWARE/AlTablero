<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Al Tablero | Tablero general</title>
        <%@include file="include_header.jsp" %>
        
    </head>
    <body class="skin-blue">
        <%@include file="include_body_header.jsp" %>
                            
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <%@include file="include_body_menu.jsp" %>
            <aside class="right-side">
                <section class="content-header">
                    <h1>Al Tablero<small>Tablero General</small></h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li class="active">Tablero general</li>
                    </ol>
                </section>
                <section class="content"></section>
            </aside>
        </div>
        
            <%@include file="include_body_jscript.jsp" %>
    </body>
</html>
