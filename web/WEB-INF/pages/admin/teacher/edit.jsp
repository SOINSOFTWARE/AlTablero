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
                    <h1>Profesores<small><c:choose><c:when test="${empty user}">Crear</c:when><c:otherwise>Editar</c:otherwise></c:choose></small></h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value="/admin/general" />"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><a href="<c:url value="/admin/profesores" />"><i class="fa fa-edit"></i> Profesores</a></li>
                        <li class="active"><c:choose><c:when test="${empty user}">Crear</c:when><c:otherwise>Editar</c:otherwise></c:choose></li>
                    </ol>
                </section>
                <section class="content">
                    <c:if test="${not empty user && user.id > 0}">
                        <c:set var="updateMode" value="true" />
                    </c:if>
                    <c:if test="${deactivated || not accessList.contains('PROCE')}">
                        <c:set var="disabled" value='readonly="readonly"' />
                        <c:set var="disabledForSelect" value='disabled="true"' />
                    </c:if>
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab">Informaci&oacute;n</a></li>
                            <c:if test="${updateMode && not deactivated}">
                                <li><a href="#tab_2" data-toggle="tab">Clases</a></li>
                            </c:if>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab_1">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="box box-primary">
                                            <div class="box-body" >
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div class="box box-solid">
                                                            <div class="box-body" >
                                                                <c:if test="${not deactivated && accessList.contains('PROCE')}">
                                                                    <%@include file="../include_save_link.jsp" %>
                                                                </c:if>
                                                                <c:if test="${accessList.contains('PRODE') && updateMode && not deactivated}">
                                                                    <a href="#" id="deactivate-link" class="btn btn-app" onclick="validateDeactivateAction();">
                                                                        <i class="fa fa-minus-circle"></i> Despedir
                                                                    </a>
                                                                </c:if>
                                                                <%@include file="../include_return_link.jsp" %>
                                                                <%@include file="../include_div_messages.jsp" %>
                                                                <c:if test="${invalidCode}">
                                                                    <div class="alert alert-danger alert-dismissable">
                                                                        <i class="fa fa-ban"></i>
                                                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                                                        <b>Error!</b> El n&uacute;mero de documento ${user.documentNumber} ya est&aacute; siendo usado por un usuario activo.
                                                                    </div>
                                                                </c:if>
                                                                <c:if test="${deactivated}">
                                                                    <div class="alert alert-success alert-dismissable">
                                                                        <i class="fa fa-check"></i>
                                                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                                                        <b>Despedir!</b> El profesor ha sido despedido.
                                                                    </div>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <form id="frmDeactivate" name="frmDeactivate" method="POST"
                                                      action="<c:url value='/admin/profesores/edicion/desactivar' />">
                                                    <input id="${_csrf.parameterName}" name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
                                                    <input id="userId" name="userId" type="hidden" value="${user.id}" />
                                                </form>
                                                <form id="frmSave" name="frmSave" method="POST"  enctype="multipart/form-data"
                                                    action="<c:url value='/admin/profesores/edicion/guardar' />" >
                                                    <input id="${_csrf.parameterName}" name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
                                                    <div class="row">
                                                        <div class="col-xs-3">
                                                            <div class="box box-solid box-info">
                                                                <div class="box-header">
                                                                    <h3 class="box-title">Foto</h3>
                                                                </div>
                                                                <div class="box-body">
                                                                    <div id="image-holder">
                                                                        <c:choose>
                                                                            <c:when test="${not empty user && not empty user.photo && user.photo != ''}">
                                                                                <c:set var="userImage" value="${user.photo}" />
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <c:set var="userImage" value="/res/img/avatar5.png" />
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                        <img id="image" src="<c:url value='${userImage}'/>"
                                                                            class="img-circle" alt="Imagen de usuario" style="height: 200px">
                                                                    </div>
                                                                    <input type="file" id="fileUpload" name="fileUpload" ${disabledForSelect} />
                                                                    <p class="help-block">Use esta opci&oacute;n para cambiar la foto.</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-4">
                                                            <div class="box box-solid box-info">
                                                                <div class="box-header">
                                                                    <h3 class="box-title">General</h3>
                                                                </div>
                                                                <div class="box-body">
                                                                    <input id="userId" name="userId" type="hidden" value="${user.id}" />
                                                                    <div class="form-group">
                                                                        <label for="documentType">Tipo de documento</label>
                                                                        <select class="form-control" disabled="disabled">
                                                                            <option value="Cedula">C&eacute;dula</option>
                                                                            <option value="TI">Tarjeta de Identidad</option>
                                                                        </select>
                                                                        <input id="documentType" name="documentType" type="hidden" value="Cedula">
                                                                    </div>
                                                                    <div id="divDocumentNumber" class="form-group">
                                                                        <label id="lblDocumentNumber" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                                        <label for="documentNumber">N&uacute;mero de documento</label>
                                                                        <input id="documentNumber" name="documentNumber" type="text"
                                                                               maxlength="12" required="true" autocomplete="off"
                                                                               class="form-control" placeholder="N&uacute;mero de Documento"
                                                                               <c:if test="${not empty user}">value="${user.documentNumber}"</c:if>
                                                                               onkeydown="return (event.ctrlKey || event.altKey 
                                                                                                || (47 < event.keyCode && event.keyCode < 58 && event.shiftKey === false) 
                                                                                                || (95 < event.keyCode && event.keyCode < 106)
                                                                                                || (event.keyCode === 8) || (event.keyCode === 9) 
                                                                                                || (event.keyCode > 34 && event.keyCode < 40) 
                                                                                                || (event.keyCode === 46))"
                                                                               ${disabled} />
                                                                    </div>
                                                                    <div id="divName" class="form-group">
                                                                        <label id="lblName" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                                        <label for="name">Nombres</label>
                                                                        <input id="name" name="name" type="text" maxlength="30"
                                                                               required="true" autocomplete="off"
                                                                               class="form-control" placeholder="Nombres"
                                                                               <c:if test="${not empty user}">value="${user.name}"</c:if>
                                                                               ${disabled}/>
                                                                    </div>
                                                                    <div id="divLastName" class="form-group">
                                                                        <label id="lblLastName" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                                        <label for="lastName">Apellidos</label>
                                                                        <input id="lastName" name="lastName" type="text" maxlength="30"
                                                                               required="true" autocomplete="off"
                                                                               class="form-control" placeholder="Apellidos"
                                                                               <c:if test="${not empty user}">value="${user.lastName}"</c:if>
                                                                               ${disabled}/>
                                                                    </div>
                                                                    <div id="divBornDate" class="form-group">
                                                                        <label id="lblBornDate" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                                        <label for="bornDate">Fecha de nacimiento</label>
                                                                        <div class="input-group">
                                                                            <div class="input-group-addon"><i class="fa fa-calendar">  </i></div>
                                                                            <input id="bornDate" name="bornDate" type="text"
                                                                                   class="form-control" placeholder="dd/mm/yyyy"
                                                                                   <c:if test="${not empty user}">value="${user.getBornDateInFormat()}"</c:if>
                                                                                   data-inputmask="'alias': 'dd/mm/yyyy'" data-mask
                                                                                   ${disabled}/>
                                                                        </div>
                                                                    </div>
                                                                    <div id="divGender" class="form-group">
                                                                        <label id="lblGender" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                                        <label for="gender">G&eacute;nero</label>
                                                                        <select id="gender" name="gender" class="form-control" ${disabledForSelect}>
                                                                            <option value="N">Seleccione uno...</option>
                                                                            <option value="F" 
                                                                                <c:if test="${not empty user && user.gender == 'F'}">selected</c:if>>
                                                                                Femenino
                                                                            </option>
                                                                            <option value="M"
                                                                                <c:if test="${not empty user && user.gender == 'M'}">selected</c:if>>
                                                                                Masculino
                                                                            </option>                        
                                                                        </select>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="coordinator">Coordinador</label>
                                                                        <div class="checkbox">
                                                                            <label>
                                                                                <input id="coordinator" name="coordinator" type="checkbox" ${disabledForSelect}
                                                                                    <c:if test="${not empty user && user.isCoordinator()}">checked="checked"</c:if>>
                                                                                    ¿&Eacute;l profesor es un coordinador?
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-5">
                                                            <div class="box box-solid box-info">
                                                                <div class="box-header">
                                                                    <h3 class="box-title">Contacto</h3>
                                                                </div>
                                                                <div class="box-body">
                                                                    <div id="divAddress" class="form-group">
                                                                        <label id="lblAddress" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                                        <label for="address">Direcci&oacute;n</label>
                                                                        <input id="address" name="address" type="text" maxlength="60"
                                                                               required="true" autocomplete="off"
                                                                               class="form-control" placeholder="Direcci&oacute;n"
                                                                               <c:if test="${not empty user}">value="${user.address}"</c:if>
                                                                               ${disabled}/>
                                                                    </div>
                                                                    <div id="divPhone1" class="form-group">
                                                                        <label id="lblPhone1" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                                                        <label for="phone1">Celular</label>
                                                                        <div class="input-group">
                                                                            <div class="input-group-addon">
                                                                                <i class="fa fa-phone"></i>
                                                                            </div>
                                                                            <input id="phone1" name="phone1" type="text" min="3000000000"
                                                                                   class="form-control" <c:if test="${not empty user}">value="${user.phone1}"</c:if>
                                                                                   data-inputmask='"mask": "(999) 999-9999"' data-mask
                                                                                   ${disabled}/>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="phone2">Tel&eacute;fono</label>
                                                                        <div class="input-group">
                                                                            <div class="input-group-addon">
                                                                                <i class="fa fa-phone"></i>
                                                                            </div>
                                                                            <input id="phone2" name="phone2" type="text" 
                                                                                   class="form-control" <c:if test="${not empty user}">value="${user.phone2}"</c:if>
                                                                                   data-inputmask='"mask": "999-9999"' data-mask
                                                                                   ${disabled}/>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>                                    
                                </div>
                            </div><!-- tab pane 1  -->
                            <c:if test="${updateMode}">
                                <div class="tab-pane" id="tab_2">
                                    <%@include file="include_class.jsp" %>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </section>
            </aside>
        </div>
        <div id="save-dialog" title="Guardar" style="display: none">
            <c:choose>
                <c:when test="${empty updateMode}">
                    <p>Un nuevo profesor ser&aacute; creado, ¿Deseas continuar con la acci&oacute;n?</p>
                </c:when>
                <c:otherwise>
                    <p>La informaci&oacute;n del profesor ser&aacute; actualizada, ¿Deseas continuar con la acci&oacute;n?</p>
                </c:otherwise>
            </c:choose>
        </div>
        <div id="not-able-to-deactivate-dialog" title="Advertencia" style="display: none">
            <p><span class="ui-icon ui-icon-alert" style="float:left; margin:2px 7px 20px 0;"></span>
                El profesor no puede ser depedido a&uacute;n porque: Es un director de grupo y/o tiene clases asignadas.
            </p>
            <p>
                Asigne las actividades a otro profesor antes de despedir.
            </p>
        </div>
        <div id="deactivate-dialog" title="Eliminar" style="display: none">
            <p><span class="ui-icon ui-icon-alert" style="float:left; margin:2px 7px 20px 0;"></span>El profesor ser&aacute; despedido, ¿Desea continuar con la acci&oacute;n?</p>
        </div>
        <%@include file="../include_required_dialog.jsp" %>
        <%@include file="../include_body_jscript.jsp" %>
        <%@include file="../include_inputmask_jscript.jsp" %>
        <%@include file="../include_datatable_jscript.jsp" %>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#refTeacher").trigger("click");
            } );
            
            $('#tblClasses').dataTable({
                "bPaginate": true,
                "bLengthChange": false,
                "bFilter": false,
                "bSort": true,
                "bInfo": true,
                "bAutoWidth": false
            });
            
            $(function () {
                $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
                $("[data-mask]").inputmask();
            });
            
            $("#fileUpload").on('change', function () {
                var countFiles = $(this)[0].files.length;
                if (countFiles === 1) {
                    if (typeof(FileReader) !== "undefined") {                    
                        var imgPath = $(this)[0].value;
                        var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
                        if (extn === "png" || extn === "jpg" || extn === "jpeg") {
                            var image_holder = $("#image-holder");
                            image_holder.empty();

                            var reader = new FileReader();
                            reader.onload = function (e) {
                                $("<img />", {
                                    "src": e.target.result,
                                    "class": "img-circle",
                                    "width": "200px"
                                }).appendTo(image_holder);

                            };
                            image_holder.show();
                            reader.readAsDataURL($(this)[0].files[0]);
                        } else {
                            alert("Seleccione solo imagenes con extensiones png, jpg o jpeg");
                        }
                    } else {
                        alert("Su navegador no soporta est&aacute; funcionalidad.");
                    }
                } else {
                    alert("Seleccione solo un archivo");
                }
            });
            
            $( "#cancel-link" ).click(function( event ) {
                showLoadingImage();
                window.location.href = "/AlTablero/admin/profesores";
                event.preventDefault();
            });
            
            $( "#save-link" ).click(function( event ) {
                if (validateRequiredFields()) {
                    if (isDataChanged()) {
                        showSaveDialog();
                    }
                } else {
                    showRequiredFieldsDialog();
                }
                updateClassAndStyle();
                event.preventDefault();
            });
            
            function validateRequiredFields() {
                var phone1 = $("#phone1").val();
                phone1 = phone1.replace('(','').replace(')','').replace('-','').replace(' ','');
                return ($.trim($("#documentNumber").val()) !== ''
                        && $.trim($("#name").val()) !== ''
                        && $.trim($("#lastName").val()) !== ''
                        && $.trim($("#bornDate").val()) !== ''
                        && $("#gender").val() !== 'N'
                        && $.trim($("#address").val()) !== ''
                        && $.trim($("#phone1").val()) !== ''
                        && Number(phone1) > Number("3000000000"));
            }
            
            function isDataChanged() {
                var phone1 = $("#phone1").val();
                var phone2 = $("#phone2").val();
                var countFiles = $("#fileUpload")[0].files.length;
                var isCoordinator = $("#coordinator").is(":checked") + "";
                phone1 = phone1.replace('(','').replace(')','').replace('-','').replace(' ','');
                phone2 = phone2.replace('(','').replace(')','').replace('-','').replace(' ','');
                return ($("#documentNumber").val() !== '${user.documentNumber}'
                        || $("#name").val() !== '${user.name}'
                        || $("#lastName").val() !== '${user.lastName}'
                        || $("#bornDate").val() !== '${user.getBornDateInFormat()}'
                        || $("#gender").val() !== '${user.gender}'
                        || $("#address").val() !== '${user.address}'
                        || (phone1 !== '${user.phone1}' && Number(phone1) > Number("3000000000"))
                        || phone2 !== '${user.phone2}'
                        || countFiles === 1
                        || isCoordinator !== '${user.isCoordinator()}');
            }
            
            function updateClassAndStyle() {
                validateUsingTrim($("#documentNumber").val(), $("#divDocumentNumber"), $("#lblDocumentNumber"));
                validateUsingTrim($("#name").val(), $("#divName"), $("#lblName"));
                validateUsingTrim($("#lastName").val(), $("#divLastName"), $("#lblLastName"));
                validateUsingTrim($("#bornDate").val(), $("#divBornDate"), $("#lblBornDate"));
                validateUsingTrim($("#address").val(), $("#divAddress"), $("#lblAddress"));
                validateUsingTrim($("#phone1").val(), $("#divPhone1"), $("#lblPhone1"));
                validateGender($("#divGender"), $("#lblGender"));
                var phone1 = $("#phone1").val();
                phone1 = phone1.replace('(','').replace(')','').replace('-','').replace(' ','');
                if (Number(phone1) < Number("3000000000")) {
                    validateUsingTrim("", $("#divPhone1"), $("#lblPhone1"));
                }
            }
            
            function validateUsingTrim(val, div, label) {
                var className = "form-group has-error";
                var display = "block";
                if ($.trim(val) !== '') {
                    className = "form-group";
                    display = "none";
                }
                changeClassNameAndStyle(div, label, className, display);
            }
            
            function validateGender(div, label) {
                var className = "form-group has-error";
                var display = "block";
                if ($("#gender").val() !== 'N') {
                    className = "form-group";
                    display = "none";
                }
                changeClassNameAndStyle(div, label, className, display);
            }
            
            function validateDeactivateAction() {
                var isDirector = ('${classroom}' === "") ? false : true;
                var hasClasses = ('${classes}' === "" || '${classes.isEmpty()}' === "true") ? false : true;
                if (isDirector || hasClasses) {
                    showNotAbleToDeactivateDialog()
                } else {
                    showDeactivateDialog();
                }
            }
            
            function showNotAbleToDeactivateDialog() {
                $( "#not-able-to-deactivate-dialog" ).dialog({
                    autoOpen: false,
                    width: 500,
                    modal: true,
                    resizable: false,
                    buttons: [
                    {
                        text: "Ok",
                        click: function() {
                            $( this ).dialog("close");
                        }
                    }]
                });
                $("#not-able-to-deactivate-dialog").dialog("open");
            }
        </script>
    </body>
</html>