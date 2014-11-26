<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Al Tablero | Profesores | Editar</title>
    <%@include file="../include_header.jsp" %>

  </head>
  <body class="skin-blue">
    <%@include file="../include_body_header.jsp" %>

    <div class="wrapper row-offcanvas row-offcanvas-left">
      <%@include file="../include_body_menu.jsp" %>
      <aside class="right-side">
        <section class="content-header">
          <h1>Al Tablero<small>Profesores</small></h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
            <li class="active">Información Profesores</li>
          </ol>
        </section>
        <section class="content">
          <div class="row">
            <div class="col-xs-12">
              <div class="box box-tools" style="height: 50px">
                <a href="javascript:{}" class="btn btn-social-icon btn-success" 
                   onclick="saveClassRoom()"
                   style="margin: 8px; margin-left: 15px">
                  <i class="fa fa-save" title="Guardar"></i>
                </a>
                <c:if test="${not empty classroom || classroom.id > 0}">
                  <a href="javascript:{}" class="btn btn-social-icon btn-google-plus" 
                     onclick="deleteClassRoom()"
                     style="margin: 8px">
                    <i class="fa fa-minus-circle" title="Eliminar"></i>
                  </a>
                </c:if>
              </div>
              <div class="box box-primary" style="height: 430px">
                <div class="box-header">
                  <h3 class="box-title"></h3>
                </div>
                <div class="box-body" style="alignment-adjust: central" >


                  <form action="<c:url value='/admin/profesor/save?${_csrf.parameterName}=${_csrf.token}' />" method='POST'>

                    <table style="width: 100%" >
                      <tr>
                        <td style="width: 20%"></td>
                        <td style="width: 20%; text-align: right; padding-right: 10px" ><label for="selDocType">Tipo de Documento:</label></td>
                        <td style="width: 60%">
                          <select name="selDocType" class="form-control" style="width: 30%">
                            <option value="Cedula">C&eacute;dula</option>
                            <option value="TI">Tarjeta de Identidad</option>                        
                          </select>
                        </td>
                      </tr>
                      <tr>
                        <td></td>
                        <td style="text-align: right; padding-right: 10px"><label for="txtDocNumber">Núm. de Documento:</label></td>
                        <td><input name="txtDocNumber" type="text" class="form-control" style="width: 30%"></input></td>
                      </tr>
                      <tr>
                        <td></td>
                        <td style=" text-align: right; padding-right: 10px"><label for="txtName">Nombres:</label></td>
                        <td><input name="txtName" type="text" class="form-control" style="width: 30%"></input></td>
                      </tr>
                      <tr>
                        <td></td>
                        <td style="text-align: right; padding-right: 10px"> <label for="txtLastName">Apellidos:</label></td>
                        <td><input name="txtLastName" type="text" class="form-control" style="width: 30%"></input></td>
                      </tr>
                      <tr>
                        <td></td>
                        <td style="text-align: right; padding-right: 10px"> <label for="txtBornDate">Fecha Nacimiento:</label></td>
                        <td>
                          <div class="input-group">
                            <div class="input-group-addon">
                              <i class="fa fa-calendar">  </i>
                            </div>
                            <input type="text" class="form-control" style="width: 25.6%" data-inputmask="'alias': 'dd/mm/yyyy'" data-mask/>
                          </div><!-- /.input group -->
                        </td>
                      </tr>
                      <tr>
                        <td></td>
                        <td style="text-align: right; padding-right: 10px"><label for="txtAddress">Direcci&oacute;n:</label></td>
                        <td><input name="txtAddress" type="text" class="form-control" style="width: 30%"></input></td>
                      </tr>
                      <tr>
                        <td></td>
                        <td style="text-align: right; padding-right: 10px"><label for="txtPhone1">Tel&eacute;fono 1:</label></label></td>
                        <td><input name="txtPhone1" type="text" class="form-control" style="width: 30%"></input></td>
                      </tr>
                      <tr>
                        <td></td>
                        <td style="text-align: right; padding-right: 10px"><label for="txtPhone2">Telf&eacute;ono 2:</label></td>
                        <td><input name="txtPhone2" type="text" class="form-control" style="width: 30%"></input></td>
                      </tr>
                      <tr >
                        <td></td>
                        <td style=" text-align: right; padding-right: 10px"><label for="selGender">G&eacute;nero:</label></td>
                        <td>
                          <select name="selGender" class="form-control" style="width: 30%">
                            <option value="F">Femenino</option>
                            <option value="M">Masculino</option>                        
                          </select>
                        </td>
                      </tr>

                      </td>
                    </table>
                    <input name="submit" type="submit" class="btn btn-success" value="Guardar" />

                  </form>
                </div>
              </div>
            </div>
          </div>
        </section>
        <section class="content"></section>
      </aside>
    </div>

    <%@include file="../include_body_jscript.jsp" %>

    <!-- jQuery 2.0.2 -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>

    <!-- InputMask -->
    <script src="<c:url value="/res/javascript/plugins/input-mask/jquery.inputmask.js" />" type="text/javascript"></script>
    <script src="<c:url value="/res/javascript/plugins/input-mask/jquery.inputmask.date.extensions.js" />" type="text/javascript"></script>
    <script src="<c:url value="/res/javascript/plugins/input-mask/jquery.inputmask.extensions.js" />" type="text/javascript"></script>




    <script type="text/javascript">
                       $(function () {
                         //Datemask dd/mm/yyyy
                         $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
                         //Money Euro
                         $("[data-mask]").inputmask();


                       });
    </script>
  </body>
</html>
