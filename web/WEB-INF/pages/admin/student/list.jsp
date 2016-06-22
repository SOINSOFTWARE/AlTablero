<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Al Tablero | Estudiantes</title>
        <%@include file="../include_header.jsp" %>
        <link href="<c:url value="/res/css/jquery-ui/jquery-ui.min.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="skin-blue">
        <%@include file="../include_body_header.jsp" %>                            
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <%@include file="../include_body_menu.jsp" %>
            <aside class="right-side">
                <section class="content-header">
                    <h1>Estudiantes<small>Ver</small></h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value="/admin/general" />"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><i class="fa fa-male"></i> Estudiantes</li>
                        <li class="active">Ver</li>
                    </ol>
                </section>
                <section class="content">
                    <c:if test="${showSearch eq false}">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="box box-tools">
                                    <div class="box-body">
                                        <form id="frmSearch" name="frmSearch" method="POST"
                                              action="<c:url value='/admin/estudiantes' />">
                                            <input id="${_csrf.parameterName}" name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
                                            <table style="width: 100%">
                                                <tr>
                                                    <td style="width: 20%;"></td>
                                                    <td style="width: 40%; vertical-align: bottom">
                                                        <div class="row">
                                                            <div class="col-xs-6">
                                                                <%@include file="../include_div_grade_all.jsp" %>
                                                            </div>
                                                            <div class="col-xs-6">
                                                                <%@include file="../include_div_classroom_select_one.jsp" %>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td style="width: 20%; vertical-align: bottom;">
                                                        <div class="form-group">
                                                            <a href="#" id="search-link" class="btn btn-social-icon btn-dropbox">
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
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-primary">
                                <div class="box-body table-responsive">
                                    <form id="frmEdit" name="frmEdit" method="POST"
                                          action="<c:url value='/admin/estudiantes/edicion' />">
                                        <input id="${_csrf.parameterName}" name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
                                        <input id="classroomId" name="classroomId" type="hidden" value="${param.classroom}" />
                                        <input id="userId" name="userId" type="hidden" />
                                    </form>
                                    <table id="tblStudent" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th style="width: 25%">N&uacute;mero de documento</th>
                                                <th style="width: 25%">Apellido(s)</th>
                                                <th style="width: 25%">Nombre(s)</th>
                                                <th style="width: 25%"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${students}" var="student">
                                                <tr>
                                                    <td style="vertical-align: middle;">${student.documentNumber}</td>
                                                    <td style="vertical-align: middle;">${student.lastName}</td>
                                                    <td style="vertical-align: middle;">${student.name}</td>
                                                    <td style="vertical-align: top;" align="center">
                                                        <a href="#" id="edit-link${student.id}" name="edit-link${student.id}" class="btn btn-social-icon btn-dropbox">
                                                            <c:choose>
                                                                <c:when test="${accessList.contains('ESTCE')}">
                                                                    <i class="fa fa-edit"></i>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <i class="fa fa-search"></i>
                                                                </c:otherwise>    
                                                            </c:choose>
                                                        </a>
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
                $("#refStudent").trigger("click");
                $("#tblStudent").dataTable();
                <c:if test="${not empty param.classroom}">
                    filterClassRoom();
                    $("#classroom").val("${param.classroom}");
                </c:if>
                <c:if test="${not empty param.classroomId}">
                    filterClassRoom();
                    $("#classroom").val("${param.classroomId}");
                </c:if>
            } );
            
            $('#tblStudent').dataTable({
                "bPaginate": true,
                "bLengthChange": false,
                "bFilter": false,
                "bSort": true,
                "bInfo": true,
                "bAutoWidth": false
            });
            
            $( "#search-link" ).click(function( event ) {
                if($("#classroom").val() !== '0') {
                    showLoadingImage();
                    $("#frmSearch").submit();
                }
                event.preventDefault();
            });
            
            $("#grade").on("change", function(){
                filterClassRoom();
            });
            
            <c:forEach items="${students}" var="student">
                $( "#tblStudent" ).on(
                    "click",
                    "#edit-link${student.id}",
                    function( event ) {
                        showLoadingImage();
                        $("#userId").val(${student.id});
                        $("#frmEdit").submit();
                        event.preventDefault();
                });
            </c:forEach>
            
            function filterClassRoom() {
                $('#classroom option:gt(0)').remove();
                if ($("#grade").val() === '0') {
                    <c:forEach items="${classrooms}" var="classroom">
                        addClassRoom("${classroom.id}", "${classroom.name}");
                    </c:forEach>
                } else if ($("#grade").val() === '1') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 1}">
                            addClassRoom("${classroom.id}", "${classroom.name}");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '2') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 2}">
                           addClassRoom("${classroom.id}", "${classroom.name}");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '3') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 3}">
                            addClassRoom("${classroom.id}", "${classroom.name}");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '4') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 4}">
                            addClassRoom("${classroom.id}", "${classroom.name}");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '5') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 5}">
                            addClassRoom("${classroom.id}", "${classroom.name}");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '6') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 6}">
                            addClassRoom("${classroom.id}", "${classroom.name}");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '7') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 7}">
                            addClassRoom("${classroom.id}", "${classroom.name}");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '8') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 8}">
                            addClassRoom("${classroom.id}", "${classroom.name}");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '9') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 9}">
                            addClassRoom("${classroom.id}", "${classroom.name}");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '10') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 10}">
                            addClassRoom("${classroom.id}", "${classroom.name}");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '11') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 11}">
                            addClassRoom("${classroom.id}", "${classroom.name}");
                        </c:if>
                    </c:forEach>
                }
            }
            
            function addClassRoom(id, name) {
                $('#classroom').append($("<option></option>").attr("value", id).text(name));
            }
        </script>
    </body>
</html>
