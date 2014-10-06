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
          <h1>Al Tablero<small>Tablero</small></h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
            <li class="active">Crear</li>
          </ol>
          </br>
          <form>
            <label for="selDocType">Tipo de Documento:</label>
            <select name="selDocType">
              <option>Cédula</option>
              <option>Tarjeta de Identidad</option>                        
            </select>
            <label for="txtDocNumber">Núm. de Documento:</label>
            <input id="txtDocNumber" type="text"></input>
            <label for="txtName">Nombres:</label>
            <input id="txtName" type="text"></input>
            <label for="txtName">Apellidos:</label>
            <input id="txtLastName" type="text"></input>
            <label for="txtBornDate">Fecha de Nacimiento:</label>
            <input id="txtBornDate" type="text"></input>
            <label for="txtAddress">Dirección:</label>
            <input id="txtAddress" type="text"></input>
            <label for="txtPhone1">Teléfono 1:</label>
            <input id="txtPhone1" type="text"></input>
            <label for="txtPhone2">Teléfono 2:</label>
            <input id="txtPhone2" type="text"></input>



          </form>

        </section>
        <section class="content"></section>
      </aside>
    </div>

    <%@include file="include_body_jscript.jsp" %>
  </body>
</html>
