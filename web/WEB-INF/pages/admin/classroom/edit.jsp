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
                    <h1>Salones<small><c:choose><c:when test="${empty classroom}">Crear</c:when><c:otherwise>Editar</c:otherwise></c:choose></small></h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value="/admin/general" />"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><a href="<c:url value="/admin/cursos" />"><i class="fa fa-edit"></i> Salones</a></li>
                        <li class="active"><c:choose><c:when test="${empty classroom}">Crear</c:when><c:otherwise>Editar</c:otherwise></c:choose></li>
                    </ol>
                </section>
                <section class="content">
                    <c:if test="${not empty classroom && classroom.id > 0}">
                        <c:set var="updateMode" value="true" />
                    </c:if>
                    <c:choose>
                        <c:when test="${not empty classroom.yearBO.id && 
                            (classroom.yearBO.id != currentYear.id || not classroom.enabled)}">
                           <c:set var="disabled" value='readonly="readonly"' />
                           <c:set var="disabledForSelect" value='disabled="true"' />
                        </c:when>
                    </c:choose>
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab">Informaci&oacute;n</a></li>
                            <c:if test="${updateMode}">
                                <li><a href="#tab_2" data-toggle="tab">Estudiantes</a></li>
                            </c:if>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab_1">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="box box-solid">
                                            <div class="box-body" >
                                                <c:if test="${empty disabled}">
                                                    <%@include file="../include_save_link.jsp" %>
                                                </c:if>
                                                <c:if test="${not empty updateMode && classroom.enabled}">
                                                    <a href="#" id="deactivate-link" class="btn btn-app" onclick="showDeactivateDialog();">
                                                        <i class="fa fa-minus-circle"></i> Eliminar
                                                    </a>
                                                </c:if>
                                                <%@include file="../include_return_link.jsp" %>
                                                <%@include file="../include_div_messages.jsp" %>
                                                <c:if test="${invalidCode}">
                                                    <div class="alert alert-danger alert-dismissable">
                                                        <i class="fa fa-ban"></i>
                                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                                        <b>Error!</b> El c&oacute;digo del sal&oacute;n ${classroom.code} ya est&aacute; siendo usado.
                                                    </div>
                                                </c:if>
                                                <c:if test="${deactivated}">
                                                    <div class="alert alert-success alert-dismissable">
                                                        <i class="fa fa-check"></i>
                                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                                        <b>Eliminar!</b> El sal&oacute;n ha sido eliminado.
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <form id="frmDeactivate" name="frmDeactivate" method="POST"
                                      action="<c:url value='/admin/cursos/edicion/desactivar?${_csrf.parameterName}=${_csrf.token}' />">
                                    <input id="classroomId" name="classroomId" type="hidden" value="${classroom.id}" />
                                </form>
                                <form id="frmSave" name="frmSave" method="POST"
                                      action="<c:url value='/admin/cursos/edicion/guardar?${_csrf.parameterName}=${_csrf.token}' />">
                                    <div class="row">
                                        <div class="col-xs-4">
                                            <div class="box box-solid box-info">
                                                <div class="box-header">
                                                    <h3 class="box-title">General</h3>
                                                </div>
                                                <div class="box-body">
                                                    <input id="classroomId" name="classroomId" type="hidden" value="${classroom.id}" />
                                                    <div id="divYear" class="form-group">
                                                        <label for="yearName">A&ntilde;o</label>
                                                        <input id="yearName" name="yearName" type="text" maxlength="4" class="form-control" placeholder="A&ntilde;o" 
                                                               value="<c:choose><c:when test="${not empty updateMode}">${classroom.yearBO.name}</c:when><c:otherwise>${currentYear.name}</c:otherwise></c:choose>"
                                                               autocomplete="off" disabled=true" />
                                                        <input type="hidden" id="year" name="year" value="<c:choose><c:when test="${not empty updateMode}">${classroom.yearBO.id}</c:when><c:otherwise>${currentYear.id}</c:otherwise></c:choose>" />
                                                    </div>
                                                    <div id="divGrade" class="form-group">
                                                        <label id="lblGrade" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                        <label for="grade">Curso</label>
                                                        <select id="grade" name="grade" class="form-control" ${disabledForSelect}>
                                                               <option value="0">Seleccione uno...</option>
                                                               <c:forEach items="${grades}" var="grade">
                                                                   <option value="${grade.id}" <c:if test="${classroom.gradeBO.id == grade.id || classroom.idGrade == grade.id}">selected</c:if>>
                                                                       ${grade.name}
                                                                   </option>
                                                               </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div id="divTime" class="form-group">
                                                        <label id="lblTime" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                        <label for="time">Jornada</label>
                                                        <select id="time" name="time" class="form-control" ${disabledForSelect}>
                                                            <option value="0">Seleccione uno...</option>
                                                            <c:forEach items="${times}" var="time">
                                                                <option value="${time.id}" <c:if test="${classroom.timeBO.id == time.id || classroom.idTime == time.id}">selected</c:if>>
                                                                    ${time.name}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div id="divDirector" class="form-group">
                                                        <label id="lblDirector" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                        <label for="director">Director</label>
                                                        <select id="director" name="director" class="form-control" ${disabledForSelect}>
                                                            <option value="0">Seleccione uno...</option>
                                                            <c:forEach items="${teachers}" var="teacher">
                                                                <option value="${teacher.id}" <c:if test="${classroom.userBO.id == teacher.id || classroom.idUser == teacher.id}">selected</c:if>>
                                                                    ${teacher.lastName} ${teacher.name}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div id="divCode" class="form-group">
                                                        <label id="lblCode" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                        <label for="code">C&oacute;digo del sal&oacute;n</label>
                                                        <input id="code" name="code" type="text" maxlength="5" class="form-control" placeholder="C&oacute;digo" value="${classroom.code}" autocomplete="off" ${disabled} />
                                                    </div>
                                                    <div id="divName" class="form-group">
                                                        <label id="lblName" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                        <label for="name">Nombre del sal&oacute;n</label>
                                                        <input id="name" name="name" type="text" maxlength="50" class="form-control" placeholder="Sal&oacute;n" value="${classroom.name}" autocomplete="off" ${disabled} />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div><!-- tab pane 1  -->
                            <c:if test="${updateMode}">
                                <div class="tab-pane" id="tab_2">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <div class="box-body table-responsive">
                                                <table id="tblClass" class="table table-bordered table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 50%">Apellidos</th>
                                                            <th style="width: 50%">Nombre</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${students}" var="student">
                                                            <tr>
                                                                <td style="vertical-align: middle">
                                                                    ${student.lastName}
                                                                </td>
                                                                <td style="vertical-align: middle">
                                                                    ${student.name}
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div><!-- Tab Content -->
                    </div>
                </section>
            </aside>
        </div>                   
        <div id="save-dialog" title="Guardar" style="display: none">
            <c:choose>
                <c:when test="${empty updateMode}">
                    <p>Un nuevo sal&oacute;n ser&aacute; creado, ¿Deseas continuar con la acci&oacute;n?</p>
                </c:when>
                <c:otherwise>
                    <p>El sal&oacute;n ser&aacute; actualizado, ¿Deseas continuar con la acci&oacute;n?</p>
                </c:otherwise>
            </c:choose>
        </div>                                                
        <div id="deactivate-dialog" title="Eliminar" style="display: none">
            <p><span class="ui-icon ui-icon-alert" style="float:left; margin:2px 7px 20px 0;"></span>El sal&oacute;n ser&aacute; eliminado, ¿Desea continuar con la acci&oacute;n?</p>
        </div>
        <%@include file="../include_required_dialog.jsp" %>
        <%@include file="../include_body_jscript.jsp" %>
        <%@include file="../include_datatable_jscript.jsp" %>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#refClassRoom').trigger("click");
                $('#tblClass').dataTable(); 
            } );
            
            $('#tblClass').dataTable({
                "bPaginate": true,
                "bLengthChange": false,
                "bFilter": false,
                "bSort": true,
                "bInfo": true,
                "bAutoWidth": false
            });

            $( "#save-link" ).click(function( event ) {
                if($.trim($("#code").val()) !== '' && $.trim($("#name").val()) !== '' && $("#year").val() !== '0' 
                        && $("#grade").val() !== '0' && $("#time").val() !== '0' && $("#director").val() !== '0') {
                    <c:choose>
                        <c:when test="${empty updateMode}">
                            showSaveDialog();
                        </c:when>
                        <c:otherwise>
                            if(isDataChanged()) {
                               showSaveDialog();
                            }
                        </c:otherwise>
                    </c:choose>
                } else {
                    showRequiredFieldsDialog();
                }
                updateClassAndStyle();
                event.preventDefault();
            });
            
            $( "#cancel-link" ).click(function( event ) {
                showLoadingImage();
                window.location.href = "/AlTablero/admin/cursos";
                event.preventDefault();
            });
            
            function isDataChanged() {
                return $("#code").val() !== '${classroom.code}' 
                        || $("#name").val() !== '${classroom.name}'
                        || $("#year").val() !== '${classroom.yearBO.id}'
                        || $("#grade").val() !== '${classroom.gradeBO.id}'
                        || $("#time").val() !== '${classroom.timeBO.id}'
                        || $("#director").val() !== '${classroom.userBO.id}';
            }
            
            function updateClassAndStyle() {
                updateClassAndStyleForYearField();
                updateClassAndStyleForGradeField();
                updateClassAndStyleForTimeField();
                updateClassAndStyleForDirectorField();
                updateClassAndStyleForCodeField();
                updateClassAndStyleForNameField();
            }
            
            function updateClassAndStyleForYearField() {
                if ($("#year").val() === '0') {
                    changeClassNameAndStyle($("#divYear"), null, "form-group has-error", null);
                } else {
                    changeClassNameAndStyle($("#divYear"), null, "form-group", null);
                }
            }
            
            function updateClassAndStyleForGradeField() {
                if ($("#grade").val() === '0') {
                    changeClassNameAndStyle($("#divGrade"), $("#lblGrade"), "form-group has-error", "block");
                } else {
                    changeClassNameAndStyle($("#divGrade"), $("#lblGrade"), "form-group", "none");
                }
            }
            
            function updateClassAndStyleForTimeField() {
                if ($("#time").val() === '0') {
                    changeClassNameAndStyle($("#divTime"), $("#lblTime"), "form-group has-error", "block");
                } else {
                    changeClassNameAndStyle($("#divTime"), $("#lblTime"), "form-group", "none");
                }
            }
            
            function updateClassAndStyleForDirectorField() {
                if ($("#director").val() === '0') {
                    changeClassNameAndStyle($("#divDirector"), $("#lblDirector"), "form-group has-error", "block");
                } else {
                    changeClassNameAndStyle($("#divDirector"), $("#lblDirector"), "form-group", "none");
                }
            }
            
            function updateClassAndStyleForCodeField() {
                if($.trim($("#code").val()) === '') {
                    changeClassNameAndStyle($("#divCode"), $("#lblCode"), "form-group has-error", "block");
                } else {
                    changeClassNameAndStyle($("#divCode"), $("#lblCode"), "form-group", "none");
                }
            }
            
            function updateClassAndStyleForNameField() {
                if ($("#name").val() === '') {
                    changeClassNameAndStyle($("#divName"), $("#lblName"), "form-group has-error", "block");
                } else {
                    changeClassNameAndStyle($("#divName"), $("#lblName"), "form-group", "none");
                }
            }
        </script>
    </body>
</html>
