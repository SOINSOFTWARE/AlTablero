<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Al Tablero | Cursos | Ver</title>
        <%@include file="../include_header.jsp" %>
        
    </head>
    <body class="skin-blue">
        <%@include file="../include_body_header.jsp" %>
                            
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <%@include file="../include_body_menu.jsp" %>
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
                            <div class="box box-primary">
                                <div class="box-body">
                                    <form id="frmClassRoom" name="frmClassRoom" method="POST"
                                          action="<c:url value='/admin/cursos?${_csrf.parameterName}=${_csrf.token}' />">
                                       <table style="width: 100%">
                                            <tr>
                                                <th style="width: 20%;"></th>
                                                <th style="width: 20%;">A&ntilde;o</th>
                                                <th style="width: 20%;">Curso</th>
                                                <th style="width: 20%;"></th>
                                                <th style="width: 20%;"></th>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td align="right">
                                                    <div class="form-group">
                                                        <select id="year" name="year" class="form-control">
                                                            <c:forEach items="${years}" var="year">
                                                                <option <c:if test="${param.year == year.name}">selected</c:if>>
                                                                    ${year.name}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-group">
                                                        <select id="grade" name="grade" class="form-control">
                                                            <option>Todos</option>
                                                            <c:forEach items="${grades}" var="grade">
                                                                <option <c:if test="${param.grade == grade.name}">selected</c:if>>
                                                                    ${grade.name}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </td>
                                                <td style="vertical-align: top;">
                                                    <a href="javascript:{}" class="btn btn-social-icon btn-dropbox" 
                                                        onclick="document.getElementById('frmClassRoom').submit();">
                                                        <i class="fa fa-search"></i>
                                                    </a>
                                                </td>
                                                <td></td>
                                            </tr>
                                        </table> 
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-header">
                                </div><!-- /.box-header -->
                                <div class="box-body table-responsive">
                                    <table id="tblClassRoom" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th style="width: 20%">Curso</th>
                                                <th style="width: 20%">C&oacute;digo</th>
                                                <th style="width: 20%">Salon</th>
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
                                                              action="<c:url value='/admin/cursos/edicion?${_csrf.parameterName}=${_csrf.token}' />">
                                                            <input id="classroomId" type="hidden" value="${classroom.id}" />
                                                            <a href="javascript:{}" class="btn btn-social-icon btn-dropbox" 
                                                                onclick="document.getElementById('frmEdit${classroom.id}').submit();">
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
        <script type="text/javascript">
            $(document).ready(function() {
                $('#refClassRoom').trigger("click");
                $('#tblClassRoom').dataTable();
            } );
        </script>
    </body>
</html>
