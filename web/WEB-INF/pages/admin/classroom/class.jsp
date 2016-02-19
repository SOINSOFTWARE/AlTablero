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
                    <h1>Salones<small>Crear clases</small></h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value="/admin/general" />"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><a href="<c:url value="/admin/cursos" />"><i class="fa fa-edit"></i> Salones</a></li>
                        <li class="active">Crear clases</li>
                    </ol>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-tools">
                                <div class="box-body">
                                    <form id="frmSearch" name="frmSearch" method="POST"
                                          action="<c:url value='/admin/cursos/clases?${_csrf.parameterName}=${_csrf.token}' />">
                                        <table style="width: 100%">
                                            <tr>
                                                <th style="width: 20%;"></th>
                                                <th style="width: 20%;">Curso</th>
                                                <th style="width: 20%;">Sal&oacute;n</th>
                                                <th style="width: 20%;"></th>
                                                <th style="width: 20%;"></th>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td align="right">
                                                    <%@include file="../include_div_grade_all.jsp" %>
                                                </td>
                                                <td>
                                                    <%@include file="../include_div_classroom_select_one.jsp" %>
                                                </td>
                                                <td style="vertical-align: top;">
                                                    <a href="#" id="search-link" class="btn btn-social-icon btn-dropbox">
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
                            <div class="box box-primary">
                                <br/>
                                <div class="box-header">
                                    <a href="#" id="save-link" class="btn btn-app">
                                        <i class="fa fa-save"></i> Guardar
                                    </a>
                                </div>
                                <div class="box-body table-responsive">
                                    <%@include file="../include_div_messages.jsp" %>
                                    <h3 class="box-title">${classroomName}</h3>
                                    <form id="frmSave" name="frmSave" method="POST"
                                          action="<c:url value='/admin/cursos/clases/guardar?${_csrf.parameterName}=${_csrf.token}' />">
                                        <input id="classroomId" name="classroomId" type="hidden" value="${classroomId}" />
                                        <input id="gradeId" name="gradeId" type="hidden" value="${gradeId}" />
                                        <input id="classJson" name="classJson" type="hidden" />
                                        <table id="tblClass" class="table table-bordered table-striped">
                                            <thead>
                                                <tr>
                                                    <th style="width: 7%; text-align: center">Activar</th>
                                                    <th style="width: 30%">Materia</th>
                                                    <th style="width: 31%">Nombre</th>
                                                    <th style="width: 32%">Profesor</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${classes}" var="classBO">
                                                    <tr>
                                                        <td style="vertical-align: middle; text-align: center">
                                                            <input id="activate${classBO.subject.id}" name="activate${classBO.subject.id}" type="checkbox"
                                                                   <c:if test="${classBO.enabled}">checked="checked"</c:if> />
                                                        </td>
                                                        <td style="vertical-align: middle">
                                                            ${classBO.subject.name}
                                                            <input id="class${classBO.subject.id}" name="class${classBO.subject.id}" value="${classBO.id}" type="hidden" />
                                                            <input id="subject${classBO.subject.id}" name="subject${classBO.subject.id}" value="${classBO.subject.id}" type="hidden" />
                                                        </td>
                                                        <td style="vertical-align: middle">
                                                            <div id="divName${classBO.subject.id}" class="form-group" style="width: 100%">
                                                                <label id="lblName${classBO.subject.id}" style="display: none" class="control-label" 
                                                                       for="inputError"><i class="fa fa-times-circle-o">
                                                                    </i> Campo requerido
                                                                </label>
                                                                <input id="name${classBO.subject.id}" name="name${classBO.subject.id}" type="text" maxlength="50" class="form-control"
                                                                       placeholder="Clase" value="${classBO.name}" autocomplete="off"
                                                                       <c:if test="${not classBO.enabled}">readonly="readonly"</c:if> />
                                                            </div>
                                                        </td>
                                                        <td style="vertical-align: middle">
                                                            <div id="divTeacher${classBO.subject.id}" class="form-group" style="width: 100%">
                                                                <label id="lblTeacher${classBO.subject.id}" style="display: none" class="control-label" 
                                                                       for="inputError"><i class="fa fa-times-circle-o">
                                                                    </i> Campo requerido
                                                                </label>
                                                                <select id="teacher${classBO.subject.id}" name="teacher${classBO.subject.id}" class="form-control" style="width: 100%"
                                                                        <c:if test="${not classBO.enabled}">disabled="disabled"</c:if>>
                                                                    <option value="0">Seleccione uno...</option>
                                                                    <c:forEach items="${teachers}" var="teacher">
                                                                        <option value="${teacher.id}" <c:if test="${classBO.teacher.id == teacher.id || classBO.idTeacher == teacher.id}">selected</c:if>>
                                                                            ${teacher.name} ${teacher.lastName}
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
            <p>Las clases seleccionadas ser&aacute;n guardadas, ¿Deseas continuar con la acci&oacute;n?</p>
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
                if($("#classroom").val() !== '0') {
                    showLoadingImage();
                    $("#frmSearch").submit();
                }
                event.preventDefault();
            });
            
            $("#grade").on("change", function(){
                filterClassRoom();
            });
            
            <c:forEach items="${classes}" var="classBO">
                $("#activate${classBO.subject.id}").on("ifChecked", function(event) {
                    disableInputs(${classBO.subject.id}, false);
                });
                
                $("#activate${classBO.subject.id}").on("ifUnchecked", function(event) {
                    disableInputs(${classBO.subject.id}, true);
                    hideFieldsRequired(${classBO.subject.id});
                    $("#name${classBO.subject.id}").val("${classBO.name}");
                    <c:choose>
                        <c:when test="${not empty classBO.teacher}">
                            $("#teacher${classBO.subject.id}").val(${classBO.teacher.id});
                        </c:when>
                        <c:otherwise>
                            $("#teacher${classBO.subject.id}").val(${classBO.idTeacher});
                        </c:otherwise>
                    </c:choose>                    
                });
            </c:forEach>
            
            $( "#save-link" ).click(function( event ) {
                var canSave = false;
                var showRequired = false;
                var classJson = "{";
                <c:forEach items="${classes}" var="classBO">
                    var idTeacher = 0;
                    <c:choose>
                        <c:when test="${not empty classBO.teacher}">
                            idTeacher = ${classBO.teacher.id};
                        </c:when>
                        <c:otherwise>
                            idTeacher = ${classBO.idTeacher};
                        </c:otherwise>
                    </c:choose>
                    if ($("#activate${classBO.subject.id}").is(":checked")) {
                        if (isValidData(${classBO.subject.id})) {
                            hideFieldsRequired(${classBO.subject.id});
                            if (isDataChanged(${classBO.subject.id}, "${classBO.name}", idTeacher.toString(), ${classBO.enabled})
                                    && !showRequired) {
                                canSave = true;
                                classJson += buildClassAsString(${classBO.subject.id}, true);
                            }
                        } else {
                            showRequired = true;
                            if ($.trim($("#name${classBO.subject.id}").val()) === '') {
                                showNameRequired(${classBO.subject.id});
                            }
                            if ($("#teacher${classBO.subject.id}").val() === '0') {
                                showTeacherRequired(${classBO.subject.id});
                            }
                            canSave = false;
                        }
                    } else if (${classBO.enabled}) {
                        canSave = true;
                        classJson += buildClassAsString(${classBO.subject.id}, false);
                    }
                </c:forEach>
                if (canSave) {
                    classJson += "}";
                    $("#classJson").val(classJson);
                    showSaveDialog();
                } else if (showRequired) {
                    classJson = "{";
                    showRequiredFieldsDialog();
                }
                event.preventDefault();
            });
            
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
            
            function disableInputs(id, disabled) {
                $("#name" + id).prop("readonly", disabled);
                $("#teacher" + id).prop("disabled", disabled);
            }
            
            function showNameRequired(id) {
                $("#divName" + id).attr("class", "form-group has-error");
                $("#lblName" + id).css("display","block");
            }
            
            function showTeacherRequired(id) {
                $("#divTeacher" + id).attr("class", "form-group has-error");
                $("#lblTeacher" + id).css("display","block");
            }
            
            function hideFieldsRequired(id) {
                $("#divName" + id).attr("class", "form-group");
                $("#divTeacher" + id).attr("class", "form-group");
                $("#lblName" + id).css("display","none");
                $("#lblTeacher" + id).css("display","none");
            }
            
            function isValidData(id) {
                return ($.trim($("#name" + id).val()) !== '' 
                        && $("#teacher" + id).val() !== '0');
            }
            
            function isDataChanged(id, name, idTeacher, enabled) {
                return (($("#name" + id).val() !== name) 
                        || ($("#teacher" + id).val() !== idTeacher)
                        || (!enabled));
            }
            
            function buildClassAsString(id, enabled) {
                var classJson = "[";
                classJson += "idClass=" + $("#class" + id).val() + ";";
                classJson += "idSubject=" + $("#subject" + id).val() + ";";
                classJson += "name=" + $("#name" + id).val() + ";";
                classJson += "idTeacher=" + $("#teacher" + id).val() + ";";
                classJson += "enabled=" + enabled;
                classJson += "]";
                return classJson;
            }
        </script>
    </body>
</html>
