<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Al Tablero | Salones</title>
        <%@include file="../include_header.jsp" %>
        <link href="<c:url value="/res/css/jquery-ui/jquery-ui.min.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="skin-blue">
        <%@include file="../include_body_header.jsp" %>                            
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <%@include file="../include_body_menu.jsp" %>
            <aside class="right-side">
                <section class="content-header">
                    <h1>Salones<small>Asociar estudiantes</small></h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value="/admin/general" />"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><a href="<c:url value="/admin/cursos" />"><i class="fa fa-edit"></i> Salones</a></li>
                        <li class="active">Asociar</li>
                    </ol>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-tools">
                                <div class="box-body">
                                    <form id="frmSearch" name="frmSearch" method="POST"
                                          action="<c:url value='/admin/cursos/asociar?${_csrf.parameterName}=${_csrf.token}' />">
                                        <table style="width: 100%">
                                            <tr>
                                                <th style="width: 20%;"></th>
                                                <th style="width: 20%;">A&ntilde;o</th>
                                                <th style="width: 20%;">Curso</th>                                                
                                                <th style="width: 20%;">Sal&oacute;n</th>
                                                <th style="width: 20%;"></th>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>
                                                    <div class="form-group">
                                                        <input id="year" name="year" value="${year}" type="text"
                                                               maxlength="4" class="form-control" readonly="readonly" />
                                                    </div>
                                                </td>
                                                <td align="right">
                                                    <%@include file="../include_div_grade_all.jsp" %>
                                                </td>
                                                <td>
                                                    <%@include file="../include_div_classroom_all.jsp" %>
                                                </td>
                                                <td style="vertical-align: top;">
                                                    <a href="#" id="search-link" class="btn btn-social-icon btn-dropbox" onclick="onSearchClick();">
                                                        <i class="fa fa-search"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12">
                            <div class="box box-primary">
                                <br/>
                                <div class="box-header">
                                    <a href="#" id="save-link" class="btn btn-app">
                                        <i class="fa fa-save"></i> Guardar
                                    </a>
                                </div>
                                <div class="box-body table-responsive">
                                    <%@include file="../include_div_messages.jsp" %>
                                    <h3 class="box-title">Estudiantes no asignados a salones</h3>
                                    <form id="frmSave" name="frmSave" method="POST"
                                          action="<c:url value='/admin/cursos/asociar/guardar?${_csrf.parameterName}=${_csrf.token}' />">
                                        <input id="gradeId" name="gradeId" type="hidden" />
                                        <input id="classroomId" name="classroomId" type="hidden" />
                                        <input id="objectStr" name="objectStr" type="hidden" />
                                        <table id="tblClass" class="table table-bordered table-striped">
                                            <thead>
                                                <tr>
                                                    <th style="width: 8%; text-align: center">Matricular</th>
                                                    <th style="width: 16%">Apellidos</th>
                                                    <th style="width: 16%">Nombre</th>
                                                    <th style="width: 15%">&Uacute;ltimo curso</th>
                                                    <th style="width: 15%">&Uacute;ltimo sal&oacute;n</th>
                                                    <th style="width: 15%">Curso</th>
                                                    <th style="width: 15%">Sal&oacute;n</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${students}" var="student">
                                                    <tr>
                                                        <td style="vertical-align: middle; text-align: center">
                                                            <input id="activate${student.id}" name="activate${student.id}" type="checkbox" />
                                                            <input id="user${student.id}" name="user${student.id}" value="${student.id}" type="hidden" />
                                                        </td>
                                                        <td style="vertical-align: middle">
                                                            ${student.lastName}
                                                        </td>
                                                        <td style="vertical-align: middle">
                                                            ${student.name}
                                                        </td>
                                                        <td style="vertical-align: middle">
                                                            ${student.lastClassRoom.gradeBO.name}
                                                        </td>
                                                        <td style="vertical-align: middle">
                                                            ${student.lastClassRoom.name}
                                                        </td>
                                                        <td style="vertical-align: middle">
                                                            <div class="form-group">
                                                                <select id="grade${student.id}" name="grade${student.id}" class="form-control" disabled="disabled">
                                                                    <option value="0">Todos</option>
                                                                    <c:forEach items="${grades}" var="grade">
                                                                        <option value="${grade.id}">
                                                                            ${grade.name}
                                                                        </option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </td>
                                                        <td style="vertical-align: middle">
                                                            <div id="divClassRoom${student.id}" name="divClassRoom${student.id}" class="form-group">
                                                                <label id="lblClassRoom${student.id}" style="display: none" class="control-label" 
                                                                       for="inputError"><i class="fa fa-times-circle-o">
                                                                    </i> Campo requerido
                                                                </label>
                                                                <select id="classroom${student.id}" name="classroom${student.id}" class="form-control" disabled="disabled">
                                                                    <option value="0">Seleccione uno...</option>
                                                                    <c:forEach items="${currentClassrooms}" var="classroom">
                                                                        <option value="${classroom.id}">
                                                                            ${classroom.name}
                                                                        </option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </aside>
        </div>                                        
        <div id="save-dialog" title="Guardar" style="display: none">
            <p>Los estudiantes seleccionadas ser&aacute;n asociados a los salones asignados, ¿Deseas continuar con la acci&oacute;n?</p>
        </div>        
        <%@include file="../include_required_dialog.jsp" %>
        <%@include file="../include_body_jscript.jsp" %>
        <script src="<c:url value="/res/javascript/plugins/datatables/jquery.dataTables.js" />" type="text/javascript"></script>
        <script src="<c:url value="/res/javascript/plugins/datatables/dataTables.bootstrap.js" />" type="text/javascript"></script>
        <script src="<c:url value="/res/javascript/jquery-ui.min.js" />" type="text/javascript"></script>
        <script src="<c:url value="/res/javascript/altablero.js" />" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#refClassRoom').trigger("click");
                $('#tblClass').dataTable();                
                <c:if test="${not empty param.classroom}">
                    filterClassRoom();
                    $("#classroom").val("${param.classroom}");
                </c:if>
                <c:if test="${not empty param.classroomId}">
                    filterClassRoom();
                    $("#classroom").val("${param.classroomId}");
                </c:if>
            });
            
            $('#tblClass').dataTable({
                "bPaginate": true,
                "bLengthChange": false,
                "bFilter": false,
                "bSort": true,
                "bInfo": true,
                "bAutoWidth": false
            });
            
            $( "#search-link" ).click(function( event ) {
                showLoadingImage();
                $("#frmSearch").submit();
                event.preventDefault();
            });
            
            $("#grade").on("change", function(){
                filterClassRoom();
            });
            
            <c:forEach items="${students}" var="student">
                $("#activate${student.id}").on("ifChecked", function(event) {
                    disableInputs(${student.id}, false);
                });
                
                $("#activate${student.id}").on("ifUnchecked", function(event) {
                    disableInputs(${student.id}, true);
                    hideFieldsRequired(${student.id});
                    $("#grade${student.id}").val(0);
                    $("#classroom${student.id}").val(0);
                });
                
                $("#grade${student.id}").on("change", function(){
                    filterCurrentClassRoom(${student.id});
                });
            </c:forEach>
            
            $( "#save-link" ).click(function( event ) {
                var canSave = false;
                var showRequired = false;
                var objectStr = "{";
                <c:forEach items="${students}" var="student">
                    if ($("#activate${student.id}").is(":checked")) {
                        if (isValidData(${student.id})) {
                            canSave = true;
                            hideFieldsRequired(${student.id});
                            objectStr += buildObjectAsString(${student.id});
                        } else {
                            showRequired = true;
                            canSave = false;
                            showFieldsRequired(${student.id});
                        }
                    }
                </c:forEach>
                if (canSave) {
                    objectStr += "}";
                    $("#objectStr").val(objectStr);
                    $("#gradeId").val($("#grade").val());
                    $("#classroomId").val($("#classroom").val());
                    showSaveDialog();
                } else if (showRequired) {
                    objectStr = "{";
                    showRequiredFieldsDialog();
                }
                event.preventDefault();
            });
            
            function filterCurrentClassRoom(id) {
                $("#classroom" + id).empty();
                addClassRoom("0", "Seleccione uno...", id);
                if ($("#grade" + id).val() === "0") {
                    <c:forEach items="${currentClassrooms}" var="classroom">
                        addClassRoom("${classroom.id}", "${classroom.name}", id);
                    </c:forEach>
                } else if ($("#grade" + id).val() === '1') {
                    <c:forEach items="${currentClassrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 1}">
                            addClassRoom("${classroom.id}", "${classroom.name}", id);
                        </c:if>
                    </c:forEach>
                } else if ($("#grade" + id).val() === '2') {
                    <c:forEach items="${currentClassrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 2}">
                           addClassRoom("${classroom.id}", "${classroom.name}", id);
                        </c:if>
                    </c:forEach>
                } else if ($("#grade" + id).val() === '3') {
                    <c:forEach items="${currentClassrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 3}">
                            addClassRoom("${classroom.id}", "${classroom.name}", id);
                        </c:if>
                    </c:forEach>
                } else if ($("#grade" + id).val() === '4') {
                    <c:forEach items="${currentClassrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 4}">
                            addClassRoom("${classroom.id}", "${classroom.name}", id);
                        </c:if>
                    </c:forEach>
                } else if ($("#grade" + id).val() === '5') {
                    <c:forEach items="${currentClassrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 5}">
                            addClassRoom("${classroom.id}", "${classroom.name}", id);
                        </c:if>
                    </c:forEach>
                } else if ($("#grade" + id).val() === '6') {
                    <c:forEach items="${currentClassrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 6}">
                            addClassRoom("${classroom.id}", "${classroom.name}", id);
                        </c:if>
                    </c:forEach>
                } else if ($("#grade" + id).val() === '7') {
                    <c:forEach items="${currentClassrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 7}">
                            addClassRoom("${classroom.id}", "${classroom.name}", id);
                        </c:if>
                    </c:forEach>
                } else if ($("#grade" + id).val() === '8') {
                    <c:forEach items="${currentClassrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 8}">
                            addClassRoom("${classroom.id}", "${classroom.name}", id);
                        </c:if>
                    </c:forEach>
                } else if ($("#grade" + id).val() === '9') {
                    <c:forEach items="${currentClassrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 9}">
                            addClassRoom("${classroom.id}", "${classroom.name}", id);
                        </c:if>
                    </c:forEach>
                } else if ($("#grade" + id).val() === '10') {
                    <c:forEach items="${currentClassrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 10}">
                            addClassRoom("${classroom.id}", "${classroom.name}", id);
                        </c:if>
                    </c:forEach>
                } else if ($("#grade" + id).val() === '11') {
                    <c:forEach items="${currentClassrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 11}">
                            addClassRoom("${classroom.id}", "${classroom.name}", id);
                        </c:if>
                    </c:forEach>
                }
            }
            
            function filterClassRoom() {
                $("#classroom").empty();
                addClassRoom("0", "Todos", "");
                if ($("#grade").val() === "0") {
                    <c:forEach items="${classrooms}" var="classroom">
                        addClassRoom("${classroom.id}", "${classroom.name}", "");
                    </c:forEach>
                } else if ($("#grade").val() === '1') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 1}">
                            addClassRoom("${classroom.id}", "${classroom.name}", "");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '2') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 2}">
                           addClassRoom("${classroom.id}", "${classroom.name}", "");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '3') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 3}">
                            addClassRoom("${classroom.id}", "${classroom.name}", "");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '4') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 4}">
                            addClassRoom("${classroom.id}", "${classroom.name}", "");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '5') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 5}">
                            addClassRoom("${classroom.id}", "${classroom.name}", "");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '6') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 6}">
                            addClassRoom("${classroom.id}", "${classroom.name}", "");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '7') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 7}">
                            addClassRoom("${classroom.id}", "${classroom.name}", "");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '8') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 8}">
                            addClassRoom("${classroom.id}", "${classroom.name}", "");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '9') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 9}">
                            addClassRoom("${classroom.id}", "${classroom.name}", "");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '10') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 10}">
                            addClassRoom("${classroom.id}", "${classroom.name}", "");
                        </c:if>
                    </c:forEach>
                } else if ($("#grade").val() === '11') {
                    <c:forEach items="${classrooms}" var="classroom">
                        <c:if test="${classroom.gradeBO.id eq 11}">
                            addClassRoom("${classroom.id}", "${classroom.name}", "");
                        </c:if>
                    </c:forEach>
                }
            }
            
            function addClassRoom(id, name, idInput) {
                $("#classroom" + idInput).append($("<option></option>").attr("value", id).text(name));
            }
            
            function disableInputs(id, disabled) {
                $("#grade" + id).prop("disabled", disabled);
                $("#classroom" + id).prop("disabled", disabled);
            }
            
            function showFieldsRequired(id) {
                $("#divClassRoom" + id).attr("class", "form-group has-error");
                $("#lblClassRoom" + id).css("display","block");
            }
            
            function hideFieldsRequired(id) {
                $("#divClassRoom" + id).attr("class", "form-group");
                $("#lblClassRoom" + id).css("display","none");
            }
            
            function isValidData(id) {
                return $("#classroom" + id).val() !== '0';
            }
            
            function buildObjectAsString(id) {
                var objectStr = "[";
                objectStr += "idStudent=" + $("#user" + id).val() + ";";
                objectStr += "idClassRoom=" + $("#classroom" + id).val();
                objectStr += "]";
                return objectStr;
            }
        </script>
    </body>
</html>
