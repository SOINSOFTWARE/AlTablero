<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Al Tablero | Tablero general</title>
        <%@include file="../include_header.jsp" %>
        
    </head>
    <body class="skin-blue">
        <%@include file="../include_body_header.jsp" %>
                            
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <%@include file="../include_body_menu.jsp" %>
            <aside class="right-side">
                <section class="content-header">
                    <h1>Cursos<small>Editar</small></h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value="/admin/general" />"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><i class="fa fa-edit"></i> Cursos</li>
                        <li class="active">Editar</li>
                    </ol>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-tools" style="height: 50px">
                                <a href="javascript:{}" class="btn btn-social-icon btn-success" 
                                    onclick="document.getElementById('frmClassRoomSave').submit();"
                                    style="margin: 8px; margin-left: 15px">
                                    <i class="fa fa-save" title="Guardar"></i>
                                </a>
                                <a href="javascript:{}" class="btn btn-social-icon btn-google-plus" 
                                    onclick="document.getElementById('frmClassRoom').submit();"
                                    style="margin: 8px">
                                    <i class="fa fa-minus-circle" title="Eliminar"></i>
                                </a>
                            </div>
                            <div class="box box-primary" style="height: 230px">
                                <div class="box-header">
                                    <h3 class="box-title"></h3>
                                </div>
                                <div class="box-body">
                                    <form id="frmClassRoomSave" name="frmClassRoomSave" method="POST"
                                          action="<c:url value='/admin/cursos/edicion/guardar?${_csrf.parameterName}=${_csrf.token}' />">
                                        <input id="classroomId" name="classroomId" type="hidden" value="${classroom.id}" />
                                        <table style="width: 100%">
                                            <tr>
                                                <th style="width: 20%"></th>
                                                <th style="width: 20%">A&ntilde;o:</th>
                                                <th style="width: 20%">Curso:</th>
                                                <th style="width: 20%">Director:</th>
                                                <th style="width: 20%"></th>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>
                                                    <select id="year" name="year" class="form-control">
                                                        <option>Seleccione uno...</option>
                                                        <c:forEach items="${years}" var="year">
                                                            <option <c:if test="${classroom.yearBO.id == year.id}">selected</c:if>>
                                                                ${year.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td>
                                                    <select id="grade" name="grade" class="form-control">
                                                        <option>Seleccione uno...</option>
                                                        <c:forEach items="${grades}" var="grade">
                                                            <option <c:if test="${classroom.gradeBO.id == grade.id}">selected</c:if>>
                                                                ${grade.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td>
                                                    <select id="director" name="director" class="form-control">
                                                        <option>Seleccione uno...</option>
                                                        <c:forEach items="${teachers}" var="teacher">
                                                            <option <c:if test="${classroom.userBO.id == teacher.id}">selected</c:if>>
                                                                ${teacher.name} ${teacher.lastName}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <th style="padding-top: 20px; text-align: right; padding-right: 15px">C&oacute;digo:</th>
                                                <td colspan="2" style="padding-top: 20px">
                                                    <input name="code" type="text" maxlength="5" class="form-control" 
                                                           placeholder="C&oacute;digo..." value="${classroom.code}"/>
                                                </td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <th style="padding-top: 20px; text-align: right; padding-right: 15px">Salon:</th>
                                                <td colspan="2" style="padding-top: 20px">
                                                    <input name="name" type="text" maxlength="50" class="form-control" 
                                                           placeholder="Salon..." value="${classroom.name}"/>
                                                </td>
                                                <td></td>
                                            </tr>
                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </aside>
        </div>
                        
        <%@include file="../include_body_jscript.jsp" %>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#refClassRoom').trigger("click");
            } );
        </script>
    </body>
</html>
