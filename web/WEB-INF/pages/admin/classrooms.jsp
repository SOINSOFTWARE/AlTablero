<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Al Tablero | Cursos | Ver</title>
        <%@include file="include_header.jsp" %>
        
    </head>
    <body class="skin-blue">
        <%@include file="include_body_header.jsp" %>
                            
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <%@include file="include_body_menu.jsp" %>
            <aside class="right-side">
                <section class="content-header">
                    <h1>Cursos<small>Ver</small></h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value="/admin/general" />"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><i class="fa fa-edit"></i> Cursos</li>
                        <li class="active">Ver</li>
                    </ol>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-header">
                                </div><!-- /.box-header -->
                                <div class="box-body table-responsive">
                                    <table id="tblClassRoom" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Curso</th>
                                                <th>C&oacute;digo</th>
                                                <th>Salon</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${classrooms}" var="classroom">
                                                <tr>
                                                    <th>${classroom.gradeBO.name}</th>
                                                    <th>${classroom.code}</th>
                                                    <th>${classroom.name}</th>
                                                </tr>
                                            </c:forEach>                                            
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Curso</th>
                                                <th>C&oacute;digo</th>
                                                <th>Salon</th>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </aside>
        </div>
        
            <%@include file="include_body_jscript.jsp" %>
            <script src="<c:url value="/res/javascript/plugins/datatables/jquery.dataTables.js" />" type="text/javascript"></script>
            <script src="<c:url value="/res/javascript/plugins/datatables/dataTables.bootstrap.js" />" type="text/javascript"></script>
            <script type="text/javascript">
                $(function() {
                    $("#tblClassRoom").dataTable();
                });
            </script>
    </body>
</html>
