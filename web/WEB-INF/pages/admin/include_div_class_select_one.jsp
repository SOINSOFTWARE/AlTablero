<div id="divClass" class="form-group">
    <label id="lblClass" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
    <label for="classBO">Asignatura</label>
    <select id="classBO" name="classBO" class="form-control">
        <option value="0">Seleccione uno...</option>
        <c:forEach items="${classes}" var="classBO">
            <option value="${classBO.id}" <c:if test="${param.classBO == classBO.id || param.classId == classBO.id}">selected</c:if>>
                ${classBO.name}
            </option>
            <c:if test="${param.classBO == classBO.id || param.classId == classBO.id}">
                <c:set var="classId" value="${classBO.id}" />
            </c:if>
        </c:forEach>
    </select>
</div>