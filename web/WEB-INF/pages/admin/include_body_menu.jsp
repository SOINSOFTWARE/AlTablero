<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="left-side sidebar-offcanvas">
    <section class="sidebar">
        <div class="user-panel">
            <div class="pull-left image">
                <c:choose>
                    <c:when test="${isAvatar}"><c:set var="imgSrc" value="/res/img/${avatar}.png"></c:set></c:when>
                    <c:otherwise><c:set var="imgSrc" value="${avatar}"></c:set></c:otherwise>
                </c:choose>
                <img src="<c:url value='${imgSrc}' />" class="img-circle" alt="Imagen de usuario" />
            </div>
            <div class="pull-left info"><p>Hola ${userFirstName}</p></div>
        </div>

        <ul class="sidebar-menu">
            <li id="liGeneral">
                <a href="<c:url value="/admin/general" />">
                    <i class="fa fa-dashboard"></i> <span>Tablero general</span>
                </a>
            </li>
            <c:if test="${canViewTeacherMenu}">
                <li class="treeview">
                    <a id="refTeacher" href="#">
                        <i class="fa fa-laptop"></i> <span>Profesores</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <c:if test="${accessList.contains('PROVE')}">
                            <li><a href="<c:url value="/admin/profesores" />"><i class="fa fa-angle-double-right"></i> Ver</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('PROCE')}">
                            <li><a href="<c:url value="/admin/profesores/edicion" />"><i class="fa fa-angle-double-right"></i> Crear</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('PROCC')}">
                            <li><a href="<c:url value="/admin/profesores/actividades" />"><i class="fa fa-angle-double-right"></i> Crear actividades</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('PROCA')}">
                            <li><a href="<c:url value="/admin/profesores/calificar" />"><i class="fa fa-angle-double-right"></i> Calificar</a></li>
                        </c:if>
                    </ul>
                </li>
            </c:if>
            <c:if test="${canViewGradeMenu}">
                <li class="treeview">                            
                    <a id="refClassRoom" href="#">
                        <i class="fa fa-edit"></i> <span>Salones</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <c:if test="${accessList.contains('CURVE')}">
                            <li><a href="<c:url value="/admin/cursos" />"><i class="fa fa-angle-double-right"></i> Ver</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('CURCE')}">
                            <li><a href="<c:url value="/admin/cursos/edicion" />"><i class="fa fa-angle-double-right"></i> Crear</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('CURMA')}">
                            <li><a href="<c:url value="/admin/cursos/clases" />"><i class="fa fa-angle-double-right"></i> Crear clases</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('CURES')}">
                            <li><a href="<c:url value="/admin/cursos/asociar" />"><i class="fa fa-angle-double-right"></i> Asociar estudiantes</a></li>
                        </c:if>
                    </ul>
                </li>
            </c:if>
            <c:if test="${canViewSubjectMenu}">
                <li id="liSubject" class="treeview">
                    <a href="#">
                        <i class="fa fa-book"></i> <span>Materias</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <c:if test="${accessList.contains('MATVE')}">
                            <li><a href="pages/tables/simple.html"><i class="fa fa-angle-double-right"></i> Ver</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('MATCE')}">
                            <li><a href="pages/tables/data.html"><i class="fa fa-angle-double-right"></i> Crear o editar</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('MATCU')}">
                            <li><a href="pages/tables/data.html"><i class="fa fa-angle-double-right"></i> Asociar a curso</a></li>
                        </c:if>
                    </ul>
                </li>
            </c:if>
            <c:if test="${canViewStudentMenu}">
                <li id="liStudent" class="treeview">
                    <a id="refStudent" href="#">
                        <i class="fa fa-male"></i> <span>Estudiantes</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <c:if test="${accessList.contains('ESTVE')}">
                            <li><a href="<c:url value="/admin/estudiantes" />"><i class="fa fa-angle-double-right"></i> Ver</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('ESTCE')}">
                            <li><a href="<c:url value="/admin/estudiantes/edicion" />"><i class="fa fa-angle-double-right"></i> Crear</a></li>
                        </c:if>
                    </ul>
                </li>
            </c:if>
        </ul>
    </section>
</aside>