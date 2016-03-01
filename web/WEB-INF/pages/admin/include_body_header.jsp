<header class="header">
    <a class="logo">Al Tablero</a>
    <nav class="navbar navbar-static-top" role="navigation">
        <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>
        <div class="navbar-right">
            <ul class="nav navbar-nav">
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="glyphicon glyphicon-user"></i>
                        <span>${username}<i class="caret"></i></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="user-header bg-light-blue">
                            <c:choose>
                                <c:when test="${isAvatar}"><c:set var="imgSrc" value="/res/img/${avatar}.png"></c:set></c:when>
                                <c:otherwise><c:set var="imgSrc" value="${avatar}"></c:set></c:otherwise>
                            </c:choose>
                            <img src="<c:url value='${imgSrc}' />" class="img-circle" alt="User Image" />
                            <p> ${username} </p>
                        </li>
                        <li class="user-footer">
                            <div class="pull-right"><a href="<c:url value="/login?logout" />" class="btn btn-default btn-flat">Salir</a></div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>