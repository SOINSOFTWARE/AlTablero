<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Al Tablero | Profesores</title>
        <%@include file="../include_header.jsp" %>
        <link href="<c:url value="/res/css/jquery-ui/jquery-ui.min.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="skin-blue">
        <%@include file="../include_body_header.jsp" %>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <%@include file="../include_body_menu.jsp" %>
            <aside class="right-side">
                <section class="content-header">
                    <h1>Profesores<small>Ver</small></h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value="/admin/general" />"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><i class="fa fa-edit"></i> Profesores</li>
                        <li class="active">Ver</li>
                    </ol>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-primary">
                                <div class="box-body table-responsive">
                                    <table id="tblTeacher" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th style="width: 20%">No. Cedula</th>
                                                <th style="width: 20%">Apellidos</th>
                                                <th style="width: 20%">Nombre</th>
                                                <th style="width: 20%">Celular</th>
                                                <th style="width: 20%"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${teachers}" var="teacher">
                                                <tr>
                                                    <td style="vertical-align: middle;">${teacher.documentNumber}</td>
                                                    <td style="vertical-align: middle;">${teacher.lastName}</td>
                                                    <td style="vertical-align: middle;">${teacher.name}</td>
                                                    <td style="vertical-align: middle;">${teacher.phone1}</td>
                                                    <td style="vertical-align: top;" align="center">
                                                        <form id="frmEdit${teacher.id}" name="frmEdit${teacher.id}" method="POST"
                                                              action="<c:url value='/admin/profesores/edicion' />">
                                                            <input id="${_csrf.parameterName}" name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
                                                            <input id="userId" name="userId" type="hidden" value="${teacher.id}" />
                                                            <a href="#" id="edit-link${teacher.id}" class="btn btn-social-icon btn-dropbox">
                                                                <c:choose>
                                                                    <c:when test="${accessList.contains('PROCE')}">
                                                                        <i class="fa fa-edit"></i>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <i class="fa fa-search"></i>
                                                                    </c:otherwise>    
                                                                </c:choose>
                                                            </a>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </aside>
        </div>        
        <%@include file="../include_body_jscript.jsp" %>
        <script src="<c:url value="/res/javascript/plugins/datatables/jquery.dataTables.js" />" type="text/javascript"></script>
        <script src="<c:url value="/res/javascript/plugins/datatables/dataTables.bootstrap.js" />" type="text/javascript"></script>
        <script src="<c:url value="/res/javascript/altablero.js" />" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#refTeacher").trigger("click");
                $("#tblTeacher").dataTable();
            } );
            
            $('#tblTeacher').dataTable({
                "bPaginate": true,
                "bLengthChange": false,
                "bFilter": true,
                "bSort": true,
                "bInfo": true,
                "bAutoWidth": false
            });
            
            <c:forEach items="${teachers}" var="teacher">
                $( "#tblTeacher" ).on(
                    "click",
                    "#edit-link${teacher.id}",
                    function( event ) {
                        showLoadingImage();
                        $("#frmEdit${teacher.id}").submit();
                        event.preventDefault();
                });
            </c:forEach>
        </script>
    </body>
</html>
