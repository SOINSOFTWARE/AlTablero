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
                    <h1>Profesores<small>Calificar</small></h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value="/admin/general" />"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li><a href="<c:url value="/admin/profesores" />"><i class="fa fa-laptop"></i> Profesores</a></li>
                        <li class="active">Calificar</li>
                    </ol>
                </section>
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
                                                        action="<c:url value='/admin/profesores/calificar?${_csrf.parameterName}=${_csrf.token}' />">
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
                                                    <c:if test="${accessList.contains('PROCA') && not empty classBO}">
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
                                                          action="<c:url value='/admin/profesores/calificar/guardar?${_csrf.parameterName}=${_csrf.token}' />">
                                                        <input id="classroomId" name="classroomId" value="${classroom.id}" type="hidden" />
                                                        <input id="classId" name="classId" value="${classBO.id}" type="hidden" />
                                                        <input id="periodId" name="periodId" value="${period.id}" type="hidden" />
                                                        <input id="objectStr" name="objectStr" type="hidden" />
                                                        <table id="tblStudent" class="table table-bordered table-striped">
                                                            <thead>
                                                                <tr>
                                                                    <th>N&uacute;mero de documento</th>
                                                                    <th>Apellido(s)</th>
                                                                    <th>Nombre(s)</th>
                                                                    <c:forEach items="${activities}" var="activity">
                                                                        <th>${activity.name}</th>
                                                                    </c:forEach>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach items="${students}" var="student">
                                                                    <tr>
                                                                        <td style="vertical-align: middle;">${student.documentNumber}</td>
                                                                        <td style="vertical-align: middle;">${student.lastName}</td>
                                                                        <td style="vertical-align: middle;">${student.name}</td>
                                                                        <c:forEach items="${activities}" var="activity">
                                                                            <td>
                                                                                <input id="${activity.name}" name="${activity.name}" 
                                                                                       <c:forEach items="${values}" var="value">
                                                                                           <c:if test="${value.idNoteDefinition eq activity.id && value.idStudent eq student.id}">
                                                                                               value="${value.value}"
                                                                                           </c:if>
                                                                                       </c:forEach> type="text">
                                                                            </td>
                                                                        </c:forEach>
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
            <p>Las calificaciones ser&aacute;n guardadas, ¿Deseas continuar con la acci&oacute;n?</p>
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
            $('#tblStudent').dataTable({
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
        </script>
    </body>
</html>