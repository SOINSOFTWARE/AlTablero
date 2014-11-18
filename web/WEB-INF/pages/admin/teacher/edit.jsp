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
          </br>
          <form action="<c:url value='/admin/profesor/save?${_csrf.parameterName}=${_csrf.token}' />" method='POST'>
            <table >
              <tr >
                <td><label for="selDocType">Tipo de Documento</label></td>
                <td>
                  <select name="selDocType" class="form-control">
                    <option value="Cedula">Cédula</option>
                    <option value="TI">Tarjeta de Identidad</option>                        
                  </select>
                </td>
              </tr>
              <tr>
                <td><label for="txtDocNumber">Núm. de Documento</label></td>
                <td><input name="txtDocNumber" type="text" class="form-control"></input></td>
              </tr>
              <tr>
                <td><label for="txtName">Nombres</label></td>
                <td><input name="txtName" type="text" class="form-control"></input></td>
              </tr>
              <tr>
                <td> <label for="txtLastName">Apellidos</label></td>
                <td><input name="txtLastName" type="text" class="form-control"></input></td>
              </tr>
              <tr>
                <td> <label for="txtBornDate">Fecha nacimiento</label></td>
                <td>
                  <div class="input-group">
                    <div class="input-group-addon">
                      <i class="fa fa-calendar">
                        
                      </i>
                    </div>
                    <input type="text" class="form-control" data-inputmask="'alias': 'dd/mm/yyyy'" data-mask/>
                  </div><!-- /.input group -->
                </td>
              </tr>
              <tr>
                <td><label for="txtAddress">Dirección</label></td>
                <td><input name="txtAddress" type="text" class="form-control"></input></td>
              </tr>
              <tr>
                <td><label for="txtPhone1">Teléfono 1</label></label></td>
                <td><input name="txtPhone1" type="text" class="form-control"></input></td>
              </tr>
              <tr>
                <td><label for="txtPhone2">Teléfono 2</label></td>
                <td><input name="txtPhone2" type="text" class="form-control"></input></td>
              </tr>
              <tr >
                <td><label for="selGender">Género</label></td>
                <td>
                  <select name="selGender" class="form-control">
                    <option value="F">F</option>
                    <option value="M">M</option>                        
                  </select>
                </td>
              </tr>
            </table>
            <input name="submit" type="submit" class="btn btn-success" value="Guardar" />

          </form>

        </section>
        <section class="content"></section>
      </aside>
    </div>

    <%@include file="../include_body_jscript.jsp" %>

    <!-- jQuery 2.0.2 -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>

    <!-- InputMask -->
    <script src="/res/javascript/plugins/input-mask/jquery.inputmask.js" type="text/javascript"></script>
    <script src="/res/javascript/plugins/input-mask/jquery.inputmask.date.extensions.js" type="text/javascript"></script>
    <script src="/res/javascript/plugins/input-mask/jquery.inputmask.extensions.js" type="text/javascript"></script>


    <script type="text/javascript">
      $(function () {
        //Datemask dd/mm/yyyy
        $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
        //Datemask2 mm/dd/yyyy
        $("#datemask2").inputmask("mm/dd/yyyy", {"placeholder": "mm/dd/yyyy"});
        //Money Euro
        $("[data-mask]").inputmask();

        //Date range picker
        $('#reservation').daterangepicker();
        //Date range picker with time picker
        $('#reservationtime').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A'});
        //Date range as a button
        $('#daterange-btn').daterangepicker(
                {
                  ranges: {
                    'Today': [moment(), moment()],
                    'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
                    'Last 7 Days': [moment().subtract('days', 6), moment()],
                    'Last 30 Days': [moment().subtract('days', 29), moment()],
                    'This Month': [moment().startOf('month'), moment().endOf('month')],
                    'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
                  },
                  startDate: moment().subtract('days', 29),
                  endDate: moment()
                },
        function (start, end) {
          $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
        }
        );

        //iCheck for checkbox and radio inputs
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
          checkboxClass: 'icheckbox_minimal',
          radioClass: 'iradio_minimal'
        });
        //Red color scheme for iCheck
        $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
          checkboxClass: 'icheckbox_minimal-red',
          radioClass: 'iradio_minimal-red'
        });
        //Flat red color scheme for iCheck
        $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
          checkboxClass: 'icheckbox_flat-red',
          radioClass: 'iradio_flat-red'
        });

        //Colorpicker
        $(".my-colorpicker1").colorpicker();
        //color picker with addon
        $(".my-colorpicker2").colorpicker();

        //Timepicker
        $(".timepicker").timepicker({
          showInputs: false
        });
      });
    </script>
  </body>
</html>
