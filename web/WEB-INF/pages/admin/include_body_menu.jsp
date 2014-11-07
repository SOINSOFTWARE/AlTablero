<aside class="left-side sidebar-offcanvas">
    <section class="sidebar">
        <div class="user-panel">
            <div class="pull-left image">
                <img src="<c:url value="/res/img/${avatar}.png" />" class="img-circle" alt="Imagen de usuario" />
            </div>
            <div class="pull-left info"><p>Hola, ${userFirstName}</p></div>
        </div>

        <ul class="sidebar-menu">
            <li class="active">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>Tablero general</span>
                </a>
            </li>
            <c:if test="${canViewGradeMenu}">
                <li class="treeview">                            
                    <a href="#">
                        <i class="fa fa-edit"></i> <span>Cursos</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <c:if test="${accessList.contains('CURVE')}">
                            <li><a href="<c:url value="/admin/cursos" />"><i class="fa fa-angle-double-right"></i> Ver</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('CURCE')}">
                            <li><a href="pages/forms/general.html"><i class="fa fa-angle-double-right"></i> Crear o editar</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('CURMA')}">
                            <li><a href="pages/forms/editors.html"><i class="fa fa-angle-double-right"></i> Asociar materias</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('CURES')}">
                            <li><a href="pages/forms/editors.html"><i class="fa fa-angle-double-right"></i> Asociar estudiantes</a></li>
                        </c:if>
                    </ul>
                </li>
            </c:if>
            <c:if test="${canViewSubjectMenu}">
                <li class="treeview">
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
            <c:if test="${canViewTeacherMenu}">
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-laptop"></i> <span>Profesores</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <c:if test="${accessList.contains('PROVE')}">
                            <li><a href="pages/charts/morris.html"><i class="fa fa-angle-double-right"></i> Ver</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('PROCE')}">
                            <li><a href="<c:url value="/admin/profesor" />"><i class="fa fa-angle-double-right"></i> Crear o editar</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('PROMA')}">
                            <li><a href="pages/charts/flot.html"><i class="fa fa-angle-double-right"></i> Asociar a materia</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('PROCC')}">
                            <li><a href="pages/charts/morris.html"><i class="fa fa-angle-double-right"></i> Crear o editar notas</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('PROCA')}">
                            <li><a href="pages/charts/morris.html"><i class="fa fa-angle-double-right"></i> Tomar notas</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('PRODE')}">
                            <li><a href="pages/charts/inline.html"><i class="fa fa-angle-double-right"></i> Despedir</a></li>
                        </c:if>
                    </ul>
                </li>
            </c:if>
            <c:if test="${canViewStudentMenu}">
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-male"></i> <span>Estudiantes</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <c:if test="${accessList.contains('ESTVE')}">
                            <li><a href="pages/UI/general.html"><i class="fa fa-angle-double-right"></i> Ver</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('ESTCE')}">
                            <li><a href="pages/UI/general.html"><i class="fa fa-angle-double-right"></i> Crear o editar</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('ESTCU')}">
                            <li><a href="pages/UI/icons.html"><i class="fa fa-angle-double-right"></i> Asociar a curso</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('ESTCA')}">
                            <li><a href="pages/UI/icons.html"><i class="fa fa-angle-double-right"></i> Ver notas</a></li>
                        </c:if>
                        <c:if test="${accessList.contains('ESTEX')}">
                            <li><a href="pages/UI/buttons.html"><i class="fa fa-angle-double-right"></i> Expulsar</a></li>
                        </c:if>
                    </ul>
                </li>
            </c:if>
        </ul>
    </section>
</aside>