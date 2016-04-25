<div id="divPeriod" class="form-group">
    <label id="lblPeriod" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
    <label for="period">Periodo</label>
    <select id="period" name="period" class="form-control">
        <c:forEach items="${periods}" var="period">
            <option value="${period.id}" <c:if test="${param.period == period.id || param.periodId == period.id}">selected</c:if>>
                ${period.name}
            </option>
            <c:if test="${param.period == period.id || param.periodId == period.id}">
                <c:set var="periodId" value="${period.id}" />
            </c:if>
        </c:forEach>
    </select>
</div>