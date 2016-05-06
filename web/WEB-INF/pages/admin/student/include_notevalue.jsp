<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<div class="nav-tabs-custom">
    <ul class="nav nav-tabs">
        <c:set var="activeTab" value="true" />
        <c:forEach items="${periods}" var="period">
            <li <c:if test="${activeTab eq true}">class="active"</c:if> ><a href="#tab_period_${period.id}" data-toggle="tab">${period.name}</a></li>
            <c:set var="activeTab" value="false" />
        </c:forEach>
    </ul>
    <div class="tab-content">
        <c:set var="activeTab" value="true" />
        <c:forEach items="${periods}" var="period">
            <div class="tab-pane <c:if test="${activeTab eq true}">active</c:if>" id="tab_period_${period.id}">
                <c:forEach items="${classes}" var="classBO">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">${classBO.name}</h3>
                                </div>
                                <div class="box-body">
                                    <c:set var="countNoteDef" value="1" />
                                    <c:set var="mustCloseDiv" value="false" />
                                    <c:forEach items="${classBO.noteDefinitionSet}" var="noteDef">
                                        <c:if test="${noteDef.period.id eq period.id}">
                                            <c:if test="${countNoteDef mod 4 == 1}">
                                                <c:if test="${countNoteDef > 1}">
                                                    </div>
                                                </c:if>
                                                <div class="row">
                                                <c:set var="mustCloseDiv" value="true" />
                                            </c:if>
                                            <div class="col-md-3 col-sm-6 col-xs-6 text-center">
                                                <input type="text" data-readonly="true" disabled="disabled" class="knob" 
                                                       data-min="0" data-max="${maxEvaluation}" data-width="90" data-height="90" data-fgColor="#3c8dbc"
                                                       <c:choose>
                                                            <c:when test="${empty noteDef.noteValueSet}">
                                                                value="0"/>
                                                                <div class="knob-label">${noteDef.name} - Sin calificar</div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:forEach items="${noteDef.noteValueSet}" var="noteValue">
                                                                    value="${noteValue.value}" />
                                                                    <div class="knob-label">${noteDef.name}</div>
                                                                </c:forEach>
                                                            </c:otherwise>
                                                        </c:choose>
                                            </div>
                                            <c:set var="countNoteDef" value="${countNoteDef+1}" />
                                        </c:if>                                        
                                    </c:forEach>
                                    <c:if test="${mustCloseDiv eq true}"></div></c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <c:set var="activeTab" value="false" />
        </c:forEach>
    </div>    
</div>