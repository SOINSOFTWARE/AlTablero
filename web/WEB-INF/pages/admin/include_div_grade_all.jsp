<div id="divGrade" class="form-group">
    <label id="lblGrade" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
    <label for="grade">Curso</label>
    <select id="grade" name="grade" class="form-control">
        <option value="0">Todos</option>
        <c:forEach items="${grades}" var="grade">
            <option value="${grade.id}" <c:if test="${param.grade == grade.id || param.gradeId == grade.id}">selected</c:if>>
                ${grade.name}
            </option>
            <c:if test="${param.grade == grade.id || param.gradeId == grade.id}">
                <c:set var="gradeId" value="${grade.id}" />
            </c:if>
        </c:forEach>
    </select>
</div>