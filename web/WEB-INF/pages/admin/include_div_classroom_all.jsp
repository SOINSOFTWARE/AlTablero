<div id="divClassroom" class="form-group">
    <label id="lblClassRoom" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
    <label for="classroom">Sal&oacute;n</label>
    <select id="classroom" name="classroom" class="form-control">
        <option value="0">Todos</option>
        <c:forEach items="${classrooms}" var="classroom">
            <option value="${classroom.id}" <c:if test="${param.classroom == classroom.id || param.classroomId == classroom.id}">selected</c:if>>
                ${classroom.name}
            </option>
            <c:if test="${param.classroom == classroom.id || param.classroomId == classroom.id}">
                <c:set var="classroomId" value="${classroom.id}" />
            </c:if>
        </c:forEach>
    </select>
</div>