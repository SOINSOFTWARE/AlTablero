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
                    <h1>Profesores<small>Boletines</small></h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value="/admin/general" />"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><i class="fa fa-edit"></i> Profesores</li>
                        <li class="active">Boletines</li>
                    </ol>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-primary">
                                <div class="box-body">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <div class="box box-solid">
                                                <div class="box-body" >
                                                    <c:if test="${groupDirector eq true}">
                                                        <a href="#" id="generate-link" class="btn btn-app">
                                                            <i class="fa fa-download"></i> Generar
                                                        </a>
                                                    </c:if>
                                                    <div class="alert alert-info alert-dismissable">
                                                        <i class="fa fa-warning"></i>
                                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                                        <b>Advertencia!</b> Recuerde verificar si todas las actividades para los estudiantes han sido calificadas para el periodo a ser generado.
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <form id="frmSave" name="frmSave" method="POST"  enctype="multipart/form-data"
                                          action="<c:url value='/admin/profesores/boletines/generar?${_csrf.parameterName}=${_csrf.token}' />" >
                                        <div class="row">
                                            <div class="col-xs-4">
                                                <div class="box box-solid box-info">
                                                    <div class="box-header">
                                                        <h3 class="box-title">Director de grupo</h3>
                                                    </div>
                                                    <div class="box-body">
                                                        <div class="row">
                                                            <div class="form-group col-xs-12">
                                                                <input id="classroomId" name="classroomId" type="hidden" value="${classroom.id}" />
                                                                <input id="groupSubjectName" name="groupSubjectName" type="text" maxlength="50" 
                                                                       class="form-control" placeholder="-" value="${classroom.name}" readonly="readonly"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-4">
                                                <div class="box box-solid box-info">
                                                    <div class="box-body">
                                                        <%@include file="../include_div_period.jsp" %>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </aside>
        </div>
        <div id="generate-dialog" title="Boletines" style="display: none">
            <p>Los boletines ser&aacute;n generados para el periodo seleccionado, ¿Deseas continuar con la acci&oacute;n?</p>
        </div>
        <%@include file="../include_body_jscript.jsp" %>
        <script src="<c:url value="/res/javascript/altablero.js" />" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#refTeacher").trigger("click");
            } );
            
            $( "#generate-link" ).click(function( event ) {
                showGenerateDialog();
                event.preventDefault();
            });
            
            function showGenerateDialog() {
                $("#generate-dialog").dialog({
                    autoOpen: false,
                    width: 400,
                    modal: true,
                    resizable: false,
                    buttons: [{
                        text: "Generar",
                        click: function() {
                            $(this).dialog("close");
                            $("#frmSave").submit();
                        }
                    },
                    {
                        text: "Cancelar",
                        click: function() {
                            $(this).dialog("close");
                        }
                    }]
                });
                $("#generate-dialog").dialog("open");
            }
        </script>
    </body>
</html>
