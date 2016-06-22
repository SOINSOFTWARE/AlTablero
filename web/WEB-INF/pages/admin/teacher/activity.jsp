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
                    <h1>Profesores<small>Actividades</small></h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value="/admin/general" />"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><a href="<c:url value="/admin/profesores" />"><i class="fa fa-edit"></i> Profesores</a></li>
                        <li class="active">Actividades</li>
                    </ol>
                </section>
                <c:choose>
                    <c:when test="${not empty activities}"><c:set var="end" value="${20 - activities.size()}" /></c:when>
                    <c:otherwise><c:set var="end" value="20" /></c:otherwise>
                </c:choose>
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-primary">
                                <div class="box-body" >
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <div class="box box-solid">
                                                <div class="box-body">
                                                    <form id="frmSearch" name="frmSearch" method="POST"
                                                        action="<c:url value='/admin/profesores/actividades' />">
                                                        <input id="${_csrf.parameterName}" name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
                                                      <table style="width: 100%">
                                                          <tr>
                                                              <td style="width: 15%;"></td>
                                                              <td style="width: 55%; vertical-align: bottom">
                                                                  <div class="row">
                                                                      <div class="col-xs-4">
                                                                          <%@include file="../include_div_classroom_all.jsp" %>
                                                                      </div>
                                                                      <div class="col-xs-4">
                                                                          <%@include file="../include_div_class_select_one.jsp" %>
                                                                      </div>
                                                                      <div class="col-xs-4">
                                                                          <%@include file="../include_div_period.jsp" %>
                                                                      </div>
                                                                  </div>
                                                              </td>
                                                              <td style="width: 10%; vertical-align: bottom;">
                                                                  <div class="form-group">
                                                                      <a href="#" id="search-link" class="btn btn-social-icon btn-dropbox">
                                                                          <i class="fa fa-search"></i>
                                                                      </a>
                                                                  </div>
                                                              </td>
                                                              <td style="width: 10%;"></td>
                                                          </tr>
                                                      </table>
                                                  </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <div class="box box-solid">
                                                <div class="box-body" >
                                                    <c:if test="${accessList.contains('PROCC') && not empty classBO}">
                                                        <%@include file="../include_save_link.jsp" %>
                                                    </c:if>
                                                    <%@include file="../include_div_messages.jsp" %>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <div class="box box-solid">
                                                <div class="box-header">
                                                    <h3 class="box-title">${classroom.name} - ${classBO.name} - ${period.name}</h3>
                                                </div>
                                                <div class="box-body table-responsive">
                                                    <form id="frmSave" name="frmSave" method="POST"
                                                          action="<c:url value='/admin/profesores/actividades/guardar' />">
                                                        <input id="${_csrf.parameterName}" name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
                                                        <input id="classroomId" name="classroomId" value="${classroom.id}" type="hidden" />
                                                        <input id="classId" name="classId" value="${classBO.id}" type="hidden" />
                                                        <input id="periodId" name="periodId" value="${period.id}" type="hidden" />
                                                        <input id="objectStr" name="objectStr" type="hidden" />
                                                        <table id="tblActivity" class="table table-bordered table-striped">
                                                            <thead>
                                                                <tr>
                                                                    <th style="width: 5%"></th>
                                                                    <th style="width: 25%">Actividad</th>
                                                                    <th style="width: 60%">Descripci&oacute;n del logro</th>
                                                                    <th style="width: 10%">Porcentaje</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach items="${activities}" var="activity">
                                                                    <tr>
                                                                        <td style="vertical-align: middle; text-align: center">
                                                                            <input id="activate${activity.id}" name="activate${activity.id}" type="checkbox" checked="checked" />
                                                                        </td>
                                                                        <td style="vertical-align: middle; text-align: center">
                                                                            <input id="activity${activity.id}" name="activity${activity.id}" value="${activity.id}" type="hidden" />
                                                                            <div id="divName${activity.id}">
                                                                                <label id="lblName${activity.id}" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                                                <input id="name${activity.id}" name="name${activity.id}" value="${activity.name}" style="width: 100%" type="text" />
                                                                            </div>
                                                                        </td>
                                                                        <td style="vertical-align: middle; text-align: center">
                                                                            <div id="divDescription${activity.id}">
                                                                                <label id="lblDescription${activity.id}" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                                                <input id="description${activity.id}" name="description${activity.id}" value="${activity.description}" style="width: 100%" type="text" />
                                                                            </div>
                                                                        </td>
                                                                        <td style="vertical-align: middle; text-align: center">
                                                                            <div id="divValue${activity.id}">
                                                                                <label id="lblValue${activity.id}" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                                                <input id="value${activity.id}" name="value${activity.id}" value="${activity.value}" style="width: 100%" type="text" data-inputmask='"mask": "999"' data-mask />
                                                                            </div>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                                <c:forEach var="count" begin="1" end="${end}">
                                                                    <tr>
                                                                        <td style="vertical-align: middle; text-align: center">
                                                                            <input id="newActivate${count}" name="newActivate${count}" type="checkbox" 
                                                                                   <c:if test="${empty classBO}">disabled="disabled"</c:if> />
                                                                        </td>
                                                                        <td style="vertical-align: middle; text-align: center">
                                                                            <div id="divNewName${count}">
                                                                                <label id="lblNewName${count}" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                                                <input id="newName${count}" name="newName${count}" style="width: 100%" type="text" disabled="disabled" />
                                                                        </div>
                                                                        </td>
                                                                        <td style="vertical-align: middle; text-align: center">
                                                                            <div id="divNewDescription${count}">
                                                                                <label id="lblNewDescription${count}" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                                                <input id="newDescription${count}" name="newDescription${count}" style="width: 100%" type="text" disabled="disabled" />
                                                                            </div>
                                                                        </td>
                                                                        <td style="vertical-align: middle; text-align: center">
                                                                            <div id="divNewValue${count}">
                                                                                <label id="lblNewValue${count}" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                                                <input id="newValue${count}" name="newValue${count}" style="width: 100%" type="text" data-inputmask='"mask": "999"' data-mask disabled="disabled" />
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
                                </div>
                            </div>
                        </div>
                    </div>                    
                </section>
            </aside>
        </div>
        <div id="save-dialog" title="Guardar" style="display: none">
            <p>Las actividades seleccionadas ser&aacute;n guardadas, ¿Deseas continuar con la acci&oacute;n?</p>
        </div>
        <div id="value-zero-dialog" title="Error" style="display: none">
            <p>El porcentaje de participaci&oacute;n de las actividades debe ser mayor a 0&percnt;.</p>
        </div>
        <div id="value-dialog" title="Error" style="display: none">
            <p>La sumatoria del porcentaje de participaci&oacute;n de todas las actividades en el periodo debe ser igual al 100&percnt;.</p>
        </div>
        <%@include file="../include_required_dialog.jsp" %>
        <%@include file="../include_body_jscript.jsp" %>
        <%@include file="../include_inputmask_jscript.jsp" %>
        <%@include file="../include_datatable_jscript.jsp" %>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#refTeacher').trigger("click");
                $('#tblActivity').dataTable();
            } );
            $(function () {
                $("[data-mask]").inputmask();
            });
            $('#tblActivity').dataTable({
                "bPaginate": false,
                "bLengthChange": false,
                "bFilter": false,
                "bSort": false,
                "bInfo": true,
                "bAutoWidth": false
            });
            $( "#search-link" ).click(function( event ) {
                if($("#classBO").val() !== '0') {
                    showLoadingImage();
                    $("#frmSearch").submit();
                }
                event.preventDefault();
            });
            <c:forEach var="count" begin="1" end="${end}">
                $("#newActivate${count}").on("ifChecked", function(event) {
                    enableNewInputs(${count}, false);
                });
                
                $("#newActivate${count}").on("ifUnchecked", function(event) {
                    enableNewInputs(${count}, true);
                });
            </c:forEach>
            
            function enableNewInputs(id, disabled) {
                $("#newName" + id).prop("disabled", disabled);
                $("#newDescription" + id).prop("disabled", disabled);
                $("#newValue" + id).prop("disabled", disabled);
            }
            
            <c:forEach items="${activities}" var="activity">
                $("#activate${activity.id}").on("ifChecked", function(event) {
                    enableInputs(${activity.id}, false);
                });
                
                $("#activate${activity.id}").on("ifUnchecked", function(event) {
                    enableInputs(${activity.id}, true);
                });
            </c:forEach>
            
            function enableInputs(id, disabled) {
                $("#name" + id).prop("disabled", disabled);
                $("#description" + id).prop("disabled", disabled);
                $("#value" + id).prop("disabled", disabled);
            }
            
            $("#classroom").on("change", function(){
                filterClasses();
            });
            
            function filterClasses() {
                $('#classBO option:gt(0)').remove();
                if ($("#classroom").val() === '0') {
                    <c:forEach items="${classes}" var="classBO">
                        $('#classBO').append($("<option></option>").attr("value", '${classBO.id}').text('${classBO.name}'));
                    </c:forEach>
                } else {
                    <c:forEach items="${classes}" var="classBO">
                        if('${classBO.classRoom.id}' === $("#classroom").val()) {
                            $('#classBO').append($("<option></option>").attr("value", '${classBO.id}').text('${classBO.name}'));
                        }
                    </c:forEach>
                }
            }
            
            $( "#save-link" ).click(function( event ) {
                if (validateRequiredFields()) {
                    if (isDataChanged() && validateValueColumn()) {
                        buildObject();
                        showSaveDialog();
                    }
                } else {
                    showRequiredFieldsDialog();
                }
                updateClassAndStyle();
                event.preventDefault();
            });
            
            function validateRequiredFields() {
                var valid = true;
                <c:forEach items="${activities}" var="activity">
                    if ($("#activate${activity.id}").is(":checked") && 
                            ($.trim($("#name${activity.id}").val()) === ''
                            || $.trim($("#description${activity.id}").val()) === ''
                            || $.trim($("#value${activity.id}").val()) === '')) {
                        valid = false;
                    }
                </c:forEach>
                <c:forEach var="count" begin="1" end="${end}">
                    if ($("#newActivate${count}").is(":checked") && 
                            ($.trim($("#newName${count}").val()) === ''
                            || $.trim($("#newDescription${count}").val()) === ''
                            || $.trim($("#newValue${count}").val()) === '')) {
                        valid = false;
                    }
                </c:forEach>
                return valid;
            }
            
            function isDataChanged() {
                var dataChanged = false;
                <c:choose>
                    <c:when test="${empty activities}">
                        <c:forEach var="count" begin="1" end="${end}">
                            if ($("#newActivate${count}").is(":checked")) {
                                dataChanged = true;
                            }
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${activities}" var="activity">
                            if ($("#activate${activity.id}").is(":checked") && 
                                    ($("#name${activity.id}").val() !== '${activity.name}'
                                    || $("#description${activity.id}").val() !== '${activity.description}'
                                    || parseInt($("#value${activity.id}").val()) !== parseInt('${activity.value}'))) {
                                dataChanged = true;
                            } else if (!$("#activate${activity.id}").is(":checked")) {
                                dataChanged = true;
                            }
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                return dataChanged;
            }
            
            function updateClassAndStyle() {
                <c:forEach items="${activities}" var="activity">
                    if ($("#activate${activity.id}").is(":checked")) {
                        validateUsingTrim($("#name${activity.id}").val(), $("#divName${activity.id}"), $("#lblName${activity.id}"));
                        validateUsingTrim($("#description${activity.id}").val(), $("#divDescription${activity.id}"), $("#lblDescription${activity.id}"));
                        validateUsingTrim($("#value${activity.id}").val(), $("#divValue${activity.id}"), $("#lblValue${activity.id}"));
                    }
                </c:forEach>
                <c:forEach var="count" begin="1" end="${end}">
                    if ($("#newActivate${count}").is(":checked")) {
                        validateUsingTrim($("#newName${count}").val(), $("#divNewName${count}"), $("#lblNewName${count}"));
                        validateUsingTrim($("#newDescription${count}").val(), $("#divNewDescription${count}"), $("#lblNewDescription${count}"));
                        validateUsingTrim($("#newValue${count}").val(), $("#divNewValue${count}"), $("#lblNewValue${count}"));
                    }
                </c:forEach>
            }
            
            function validateUsingTrim(val, div, label) {
                var className = "has-error";
                var display = "block";
                if ($.trim(val) !== '') {
                    className = "";
                    display = "none";
                }
                changeClassNameAndStyle(div, label, className, display);
            }
            
            function validateValueColumn() {
                var sumPercentage = 0;
                var valid = true;
                <c:forEach items="${activities}" var="activity">
                    if ($("#activate${activity.id}").is(":checked")) {
                        if ($.trim($("#value${activity.id}").val()) === '' || parseInt($("#value${activity.id}").val()) === 0) {
                            valid = false;
                            changeClassNameAndStyle($("#divValue${activity.id}"), $("#lblValue${activity.id}"), "has-error", "block");
                        } else {
                            var percentage = parseInt($("#value${activity.id}").val());
                            sumPercentage += percentage;
                        }
                    }
                </c:forEach>
                <c:forEach var="count" begin="1" end="${end}">
                    if ($("#newActivate${count}").is(":checked")) {
                        if ($.trim($("#newValue${count}").val()) === '' || parseInt($("#newValue${count}").val()) === 0) {
                            valid = false;
                            changeClassNameAndStyle($("#divNewValue${count}"), $("#lblNewValue${count}"), "has-error", "block");
                        } else {
                            var percentage = parseInt($("#newValue${count}").val());
                            sumPercentage += percentage;
                        }
                    }
                </c:forEach>
                if (!valid) {
                    showValueZeroDialog();
                } else {
                    if (sumPercentage !== 100) {
                        showValueDialog();
                        valid = false;
                    }
                }
                return valid;
            }
            
            function showValueZeroDialog() {
                $("#value-zero-dialog").dialog({
                    autoOpen: false,
                    width: 400,
                    modal: true,
                    resizable: false,
                    buttons: [{
                        text: "Cancelar",
                        click: function() {
                            $(this).dialog("close");
                        }
                    }]
                });
                $("#value-zero-dialog").dialog("open");
            }
            
            function showValueDialog() {
                $("#value-dialog").dialog({
                    autoOpen: false,
                    width: 400,
                    modal: true,
                    resizable: false,
                    buttons: [{
                        text: "Cancelar",
                        click: function() {
                            $(this).dialog("close");
                        }
                    }]
                });
                $("#value-dialog").dialog("open");
            }
            
            function buildObject() {
                var objectStr = "{";
                <c:forEach items="${activities}" var="activity">
                    if ($("#activate${activity.id}").is(":checked")) {
                        objectStr += buildObjectAsString(${activity.id}, true);
                    } else {
                        objectStr += buildObjectAsString(${activity.id}, false);
                    }
                </c:forEach>
                <c:forEach var="count" begin="1" end="${end}">
                    if ($("#newActivate${count}").is(":checked")) {
                        objectStr += buildNewObjectAsString(${count}, true);
                    }
                </c:forEach>
                objectStr += "}";
                $("#objectStr").val(objectStr);
            }
            
            function buildObjectAsString(id, enabled) {
                var objectStr = "[";
                objectStr += "idNoteDefinition=" + $("#activity" + id).val() + ";";
                objectStr += "name=" + $("#name" + id).val() + ";";
                objectStr += "description=" + $("#description" + id).val() + ";";
                objectStr += "value=" + $("#value" + id).val() + ";";
                objectStr += "enabled=" + enabled;
                objectStr += "]";
                return objectStr;
            }
            
            function buildNewObjectAsString(id, enabled) {
                var objectStr = "[";
                objectStr += "name=" + $("#newName" + id).val() + ";";
                objectStr += "description=" + $("#newDescription" + id).val() + ";";
                objectStr += "value=" + $("#newValue" + id).val() + ";";
                objectStr += "enabled=" + enabled;
                objectStr += "]";
                return objectStr;
            }
        </script>
    </body>
</html>