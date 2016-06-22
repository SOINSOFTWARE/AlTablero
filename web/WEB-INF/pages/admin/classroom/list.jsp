<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Al Tablero | Salones</title>
        <%@include file="../include_header.jsp" %>        
    </head>
    <body class="skin-blue">
        <%@include file="../include_body_header.jsp" %>                            
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <%@include file="../include_body_menu.jsp" %>
            <aside class="right-side">
                <section class="content-header">
                    <h1>Salones<small>Ver</small></h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value="/admin/general" />"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><i class="fa fa-edit"></i> Salones</li>
                        <li class="active">Ver</li>
                    </ol>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-tools">
                                <div class="box-body">
                                    <form id="frmSearch" name="frmSearch" method="POST"
                                          action="<c:url value='/admin/cursos' />">
                                        <input id="${_csrf.parameterName}" name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
                                       <table style="width: 100%">
                                            <tr>
                                                <td style="width: 20%;"></td>
                                                <td style="width: 40%;">
                                                    <div class="col-xs-5">
                                                        <div class="form-group">
                                                            <label for="year">A&ntilde;o</label>
                                                            <select id="year" name="year" class="form-control">
                                                                <c:forEach items="${years}" var="year">
                                                                    <option value="${year.name}" <c:if test="${param.year == year.name}">selected</c:if>>
                                                                        ${year.name}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-7">
                                                        <%@include file="../include_div_grade_all.jsp" %>
                                                    </div>
                                                </td>
                                                <td style="width: 20%; vertical-align: bottom;">
                                                    <div class="form-group">
                                                        <a href="#" id="search-link" class="btn btn-social-icon btn-dropbox" onclick="onSearchClick();">
                                                            <i class="fa fa-search"></i>
                                                        </a>
                                                    </div>                                                    
                                                </td>
                                                <td style="width: 20%;"></td>
                                            </tr>
                                        </table> 
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12">
                            <div class="box box-primary">
                                <div class="box-header">
                                </div>
                                <div class="box-body table-responsive">
                                    <table id="tblClassRoom" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th style="width: 20%">Curso</th>
                                                <th style="width: 20%">C&oacute;digo</th>
                                                <th style="width: 20%">Sal&oacute;n</th>
                                                <th style="width: 30%">Director</th>
                                                <th style="width: 10%"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${classrooms}" var="classroom">
                                                <tr>
                                                    <td style="vertical-align: middle;">${classroom.gradeBO.name}</td>
                                                    <td style="vertical-align: middle;">${classroom.code}</td>
                                                    <td style="vertical-align: middle;">${classroom.name}</td>
                                                    <td style="vertical-align: middle;">${classroom.userBO.name} ${classroom.userBO.lastName}</td>
                                                    <td style="vertical-align: top;" align="center">
                                                        <form id="frmEdit${classroom.id}" name="frmEdit${classroom.id}" method="POST"
                                                              action="<c:url value='/admin/cursos/edicion' />">
                                                            <input id="${_csrf.parameterName}" name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
                                                            <input id="classroomId" name="classroomId" type="hidden" value="${classroom.id}" />
                                                            <a href="#" id="edit-link${classroom.id}" class="btn btn-social-icon btn-dropbox">
                                                                <i class="fa fa-edit"></i>
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
                $('#refClassRoom').trigger("click");
                $('#tblClassRoom').dataTable();
            } );
            
            <c:forEach items="${classrooms}" var="classroom">
                $( "#tblClassRoom" ).on(
                    "click",
                    "#edit-link${classroom.id}",
                    function( event ) {
                        showLoadingImage();
                        $("#frmEdit${classroom.id}").submit();
                        event.preventDefault();
                });
            </c:forEach>
        </script>
    </body>
</html>
