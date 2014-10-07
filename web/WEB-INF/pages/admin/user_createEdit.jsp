<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Al Tablero | Tablero general</title>
    <%@include file="include_header.jsp" %>

  </head>
  <body class="skin-blue">
    <%@include file="include_body_header.jsp" %>

    <div class="wrapper row-offcanvas row-offcanvas-left">
      <%@include file="include_body_menu.jsp" %>
      <aside class="right-side">
        <section class="content-header">
          <h1>Al Tablero<small>Profesores</small></h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
            <li class="active">Información Profesores</li>
          </ol>
          </br>
          <form>
            <table >
              <tr >
                <td><label for="selDocType">Tipo de Documento</label></td>
                <td>
                  <select name="selDocType">
                    <option>Cédula</option>
                    <option>Tarjeta de Identidad</option>                        
                  </select>
                </td>
              </tr>
              <tr>
                <td><label for="txtDocNumber">Núm. de Documento</label></td>
                <td><input id="txtDocNumber" type="text"></input></td>
              </tr>
              <tr>
                <td><label for="txtName">Nombres</label></td>
                <td><input id="txtName" type="text"></input></td>
              </tr>
              <tr>
                <td> <label for="txtLastName">Apellidos</label></td>
                <td><input id="txtLastName" type="text"></input></td>
              </tr>
              <tr>
                <td> <label for="txtBornDate">Fecha de Nacimiento</label></td>
                <td> <input id="txtBornDate" type="text"></input></td>
              </tr>
              <tr>
                <td><label for="txtAddress">Dirección</label></td>
                <td><input id="txtAddress" type="text"></input></td>
              </tr>
              <tr>
                <td><label for="txtPhone1">Teléfono 1</label></label></td>
                <td><input id="txtPhone1" type="text"></input></td>
              </tr>
              <tr>
                <td><label for="txtPhone2">Teléfono 2</label></td>
                <td><input id="txtPhone2" type="text"></input></td>
              </tr>
              <tr >
                <td><label for="selGender">Género</label></td>
                <td>
                  <select name="selGender">
                    <option>F</option>
                    <option>M</option>                        
                  </select>
                </td>
              </tr>
            </table>

          </form>

        </section>
        <section class="content"></section>
      </aside>
    </div>

    <%@include file="include_body_jscript.jsp" %>
  </body>
</html>
