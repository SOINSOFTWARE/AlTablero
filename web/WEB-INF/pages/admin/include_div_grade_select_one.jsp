<div class="form-group">
    <select id="grade" name="grade" class="form-control">
        <option value="0">Seleccione uno...</option>
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