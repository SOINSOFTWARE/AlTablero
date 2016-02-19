<c:if test="${hasServerErrors}">
    <div class="alert alert-danger alert-dismissable">
        <i class="fa fa-ban"></i>
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <b>Error!</b> Por favor contacte a SOIN Software.
    </div>
</c:if>
<c:if test="${saved}">
    <div class="alert alert-success alert-dismissable">
        <i class="fa fa-check"></i>
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <b>Guardar!</b> Los datos han sido salvados exitosamente.
    </div>
</c:if>
