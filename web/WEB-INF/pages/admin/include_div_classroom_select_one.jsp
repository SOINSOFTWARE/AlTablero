<div class="form-group">
    <select id="classroom" name="classroom" class="form-control">
        <option value="0">Seleccione uno...</option>
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