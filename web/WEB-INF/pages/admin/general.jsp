<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Al Tablero | Tablero general</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport' />
        <link href="<c:url value="/res/css/bootstrap.min.css" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/res/css/font-awesome.min.css" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/res/css/ionicons.min.css" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/res/css/AdminLTE.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="skin-blue">
        <header class="header">
            <a class="logo">Al Tablero</a>
            <nav class="navbar navbar-static-top" role="navigation">
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-user"></i>
                                <span>${username}<i class="caret"></i></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="user-header bg-light-blue">
                                    <img src="<c:url value="/res/img/${avatar}.png" />" class="img-circle" alt="User Image" />
                                    <p> ${usernameRole} </p>
                                </li>
                                <li class="user-footer">
                                    <div class="pull-left"><a href="<c:url value="/admin/lockscreen" />" class="btn btn-default btn-flat">Bloquear</a></div>
                                    <div class="pull-right"><a href="<c:url value="/login?logout" />" class="btn btn-default btn-flat">Salir</a></div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
                            
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <aside class="left-side sidebar-offcanvas">
                <section class="sidebar">
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="<c:url value="/res/img/${avatar}.png" />" class="img-circle" alt="Imagen de usuario" />
                        </div>
                        <div class="pull-left info"><p>Hola, ${username}</p></div>
                    </div>
                    
                    <ul class="sidebar-menu">
                        <li class="active">
                            <a href="#">
                                <i class="fa fa-dashboard"></i> <span>Tablero general</span>
                            </a>
                        </li>
                        <c:if test="${canViewGradeMenu}">
                            <li class="treeview">                            
                                <a href="#">
                                    <i class="fa fa-edit"></i> <span>Cursos</span>
                                    <i class="fa fa-angle-left pull-right"></i>
                                </a>
                                <ul class="treeview-menu">
                                    <c:if test="${accessList.contains('CURVE')}">
                                        <li><a href="pages/forms/advanced.html"><i class="fa fa-angle-double-right"></i> Ver</a></li>
                                    </c:if>
                                    <c:if test="${accessList.contains('CURCE')}">
                                        <li><a href="pages/forms/general.html"><i class="fa fa-angle-double-right"></i> Crear o editar</a></li>
                                    </c:if>
                                    <c:if test="${accessList.contains('CURMA')}">
                                        <li><a href="pages/forms/editors.html"><i class="fa fa-angle-double-right"></i> Asociar materias</a></li>
                                    </c:if>
                                    <c:if test="${accessList.contains('CURES')}">
                                        <li><a href="pages/forms/editors.html"><i class="fa fa-angle-double-right"></i> Asociar estudiantes</a></li>
                                    </c:if>
                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${canViewSubjectMenu}">
                            <li class="treeview">
                                <a href="#">
                                    <i class="fa fa-book"></i> <span>Materias</span>
                                    <i class="fa fa-angle-left pull-right"></i>
                                </a>
                                <ul class="treeview-menu">
                                    <c:if test="${accessList.contains('MATVE')}">
                                        <li><a href="pages/tables/simple.html"><i class="fa fa-angle-double-right"></i> Ver</a></li>
                                    </c:if>
                                    <c:if test="${accessList.contains('MATCE')}">
                                        <li><a href="pages/tables/data.html"><i class="fa fa-angle-double-right"></i> Crear o editar</a></li>
                                    </c:if>
                                    <c:if test="${accessList.contains('MATCU')}">
                                        <li><a href="pages/tables/data.html"><i class="fa fa-angle-double-right"></i> Asociar a curso</a></li>
                                    </c:if>
                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${canViewTeacherMenu}">
                            <li class="treeview">
                                <a href="#">
                                    <i class="fa fa-laptop"></i> <span>Profesores</span>
                                    <i class="fa fa-angle-left pull-right"></i>
                                </a>
                                <ul class="treeview-menu">
                                    <c:if test="${accessList.contains('PROVE')}">
                                        <li><a href="pages/charts/morris.html"><i class="fa fa-angle-double-right"></i> Ver</a></li>
                                    </c:if>
                                    <c:if test="${accessList.contains('PROCE')}">
                                        <li><a href="pages/charts/morris.html"><i class="fa fa-angle-double-right"></i> Crear o editar</a></li>
                                    </c:if>
                                    <c:if test="${accessList.contains('PROMA')}">
                                        <li><a href="pages/charts/flot.html"><i class="fa fa-angle-double-right"></i> Asociar a materia</a></li>
                                    </c:if>
                                    <c:if test="${accessList.contains('PROCC')}">
                                        <li><a href="pages/charts/morris.html"><i class="fa fa-angle-double-right"></i> Crear o editar notas</a></li>
                                    </c:if>
                                    <c:if test="${accessList.contains('PROCA')}">
                                        <li><a href="pages/charts/morris.html"><i class="fa fa-angle-double-right"></i> Tomar notas</a></li>
                                    </c:if>
                                    <c:if test="${accessList.contains('PRODE')}">
                                        <li><a href="pages/charts/inline.html"><i class="fa fa-angle-double-right"></i> Despedir</a></li>
                                    </c:if>
                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${canViewStudentMenu}">
                            <li class="treeview">
                                <a href="#">
                                    <i class="fa fa-male"></i> <span>Estudiantes</span>
                                    <i class="fa fa-angle-left pull-right"></i>
                                </a>
                                <ul class="treeview-menu">
                                    <c:if test="${accessList.contains('ESTVE')}">
                                        <li><a href="pages/UI/general.html"><i class="fa fa-angle-double-right"></i> Ver</a></li>
                                    </c:if>
                                    <c:if test="${accessList.contains('ESTCE')}">
                                        <li><a href="pages/UI/general.html"><i class="fa fa-angle-double-right"></i> Crear o editar</a></li>
                                    </c:if>
                                    <c:if test="${accessList.contains('ESTCU')}">
                                        <li><a href="pages/UI/icons.html"><i class="fa fa-angle-double-right"></i> Asociar a curso</a></li>
                                    </c:if>
                                    <c:if test="${accessList.contains('ESTCA')}">
                                        <li><a href="pages/UI/icons.html"><i class="fa fa-angle-double-right"></i> Ver notas</a></li>
                                    </c:if>
                                    <c:if test="${accessList.contains('ESTEX')}">
                                        <li><a href="pages/UI/buttons.html"><i class="fa fa-angle-double-right"></i> Expulsar</a></li>
                                    </c:if>
                                </ul>
                            </li>
                        </c:if>
                    </ul>
                </section>
            </aside>
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
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src="<c:url value="/res/javascript/bootstrap.min.js" />" type="text/javascript"></script>
        <script src="<c:url value="/res/javascript/AdminLTE/app.js" />" type="text/javascript"></script>
    </body>
</html>
