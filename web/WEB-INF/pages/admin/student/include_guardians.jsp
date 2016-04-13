<div class="row">
    <div class="col-xs-12">
        <div class="box box-primary">
            <div class="box-header"></div>
            <div class="box-body">
                <div class="row">
                    <div class="col-xs-4">
                        <div class="box box-solid box-info">
                            <div class="box-header">
                                <h3 class="box-title">Acudiente #1</h3>
                            </div>
                            <div class="box-body">
                                <input id="guardian1Id" name="guardian1Id" type="hidden" <c:if test="${not empty user.guardian1}">value="${user.guardian1.id}"</c:if> />
                                <div class="form-group">
                                    <label for="guardian1DocType">Tipo de documento</label>
                                    <select id="guardian1DocType" name="guardian1DocType" class="form-control" ${disabledForSelect}>
                                        <option value="Cedula">C&eacute;dula</option>
                                        <option value="TI">Tarjeta de Identidad</option>                                    
                                    </select>
                                </div>
                                <div id="divGuardian1DocumentNumber" class="form-group">
                                    <label id="lblGuardian1DocumentNumber" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                    <label for="guardian1DocumentNumber">N&uacute;mero de documento</label>
                                    <input id="guardian1DocumentNumber" name="guardian1DocumentNumber" type="text"
                                           maxlength="12" required="true" autocomplete="off"
                                           class="form-control" placeholder="N&uacute;mero de Documento"
                                           <c:if test="${not empty user.guardian1}">value="${user.guardian1.documentNumber}"</c:if>
                                           onkeydown="return (event.ctrlKey || event.altKey 
                                                            || (47 < event.keyCode && event.keyCode < 58 && event.shiftKey === false) 
                                                            || (95 < event.keyCode && event.keyCode < 106)
                                                            || (event.keyCode === 8) || (event.keyCode === 9) 
                                                            || (event.keyCode > 34 && event.keyCode < 40) 
                                                            || (event.keyCode === 46))"
                                           ${disabled} />
                                </div>
                                <div id="divGuardian1Name" class="form-group">
                                    <label id="lblGuardian1Name" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                    <label for="guardian1Name">Nombres</label>
                                    <input id="guardian1Name" name="guardian1Name" type="text" maxlength="30"
                                           required="true" autocomplete="off"
                                           class="form-control" placeholder="Nombres"
                                           <c:if test="${not empty user.guardian1}">value="${user.guardian1.name}"</c:if>
                                           ${disabled}/>
                                </div>
                                <div id="divGuardian1LastName" class="form-group">
                                    <label id="lblGuardian1LastName" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                    <label for="guardian1LastName">Apellidos</label>
                                    <input id="guardian1LastName" name="guardian1LastName" type="text" maxlength="30"
                                           required="true" autocomplete="off"
                                           class="form-control" placeholder="Apellidos"
                                           <c:if test="${not empty user.guardian1}">value="${user.guardian1.lastName}"</c:if>
                                           ${disabled}/>
                                </div>
                                <div id="divGuardian1Gender" class="form-group">
                                    <label id="lblGuardian1Gender" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                    <label for="guardian1Gender">G&eacute;nero</label>
                                    <select id="guardian1Gender" name="guardian1Gender" class="form-control" ${disabledForSelect}>
                                        <option value="N">Seleccione uno...</option>
                                        <option value="F" 
                                            <c:if test="${not empty user.guardian1 && user.guardian1.gender == 'F'}">selected</c:if>>
                                            Femenino
                                        </option>
                                        <option value="M"
                                            <c:if test="${not empty user.guardian1 && user.guardian1.gender == 'M'}">selected</c:if>>
                                            Masculino
                                        </option>                        
                                    </select>
                                </div>
                                <div id="divGuardian1Address" class="form-group">
                                    <label id="lblGuardian1Address" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                    <label for="guardian1Address">Direcci&oacute;n</label>
                                    <input id="guardian1Address" name="guardian1Address" type="text" maxlength="60"
                                           required="true" autocomplete="off"
                                           class="form-control" placeholder="Direcci&oacute;n"
                                           <c:if test="${not empty user.guardian1}">value="${user.guardian1.address}"</c:if>
                                           ${disabled}/>
                                </div>
                                <div id="divGuardian1Phone1" class="form-group">
                                    <label id="lblGuardian1Phone1" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                    <label for="guardian1Phone1">Celular</label>
                                    <div class="input-group">
                                        <div class="input-group-addon">
                                            <i class="fa fa-phone"></i>
                                        </div>
                                        <input id="guardian1Phone1" name="guardian1Phone1" type="text" min="3000000000"
                                               class="form-control" <c:if test="${not empty user.guardian1}">value="${user.guardian1.phone1}"</c:if>
                                               data-inputmask='"mask": "(999) 999-9999"' data-mask
                                               ${disabled}/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="guardian1Phone2">Tel&eacute;fono</label>
                                    <div class="input-group">
                                        <div class="input-group-addon">
                                            <i class="fa fa-phone"></i>
                                        </div>
                                        <input id="guardian1Phone2" name="guardian1Phone2" type="text" 
                                               class="form-control" <c:if test="${not empty user.guardian1}">value="${user.guardian1.phone2}"</c:if>
                                               data-inputmask='"mask": "999-9999"' data-mask
                                               ${disabled}/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="box box-solid box-info">
                            <div class="box-header">
                                <h3 class="box-title">Acudiente #2</h3>
                            </div>
                            <div class="box-body">
                                <input id="guardian2Id" name="guardian2Id" type="hidden" <c:if test="${not empty user.guardian2}">value="${user.guardian2.id}"</c:if> />
                                <div class="form-group">
                                    <label for="guardian2DocType">Tipo de documento</label>
                                    <select id="guardian2DocType" name="guardian2DocType" class="form-control" ${disabledForSelect}>
                                        <option value="Cedula">C&eacute;dula</option>
                                        <option value="TI">Tarjeta de Identidad</option>                                    
                                    </select>
                                </div>
                                <div id="divGuardian2DocumentNumber" class="form-group">
                                    <label id="lblGuardian2DocumentNumber" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                    <label for="guardian2DocumentNumber">N&uacute;mero de documento</label>
                                    <input id="guardian2DocumentNumber" name="guardian2DocumentNumber" type="text"
                                           maxlength="12" required="true" autocomplete="off"
                                           class="form-control" placeholder="N&uacute;mero de Documento"
                                           <c:if test="${not empty user.guardian2}">value="${user.guardian2.documentNumber}"</c:if>
                                           onkeydown="return (event.ctrlKey || event.altKey 
                                                            || (47 < event.keyCode && event.keyCode < 58 && event.shiftKey === false) 
                                                            || (95 < event.keyCode && event.keyCode < 106)
                                                            || (event.keyCode === 8) || (event.keyCode === 9) 
                                                            || (event.keyCode > 34 && event.keyCode < 40) 
                                                            || (event.keyCode === 46))"
                                           ${disabled} />
                                </div>
                                <div id="divGuardian2Name" class="form-group">
                                    <label id="lblGuardian2Name" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                    <label for="guardian2Name">Nombres</label>
                                    <input id="guardian2Name" name="guardian2Name" type="text" maxlength="30"
                                           required="true" autocomplete="off"
                                           class="form-control" placeholder="Nombres"
                                           <c:if test="${not empty user.guardian2}">value="${user.guardian2.name}"</c:if>
                                           ${disabled}/>
                                </div>
                                <div id="divGuardian2LastName" class="form-group">
                                    <label id="lblGuardian2LastName" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                    <label for="guardian2LastName">Apellidos</label>
                                    <input id="guardian2LastName" name="guardian2LastName" type="text" maxlength="30"
                                           required="true" autocomplete="off"
                                           class="form-control" placeholder="Apellidos"
                                           <c:if test="${not empty user.guardian2}">value="${user.guardian2.lastName}"</c:if>
                                           ${disabled}/>
                                </div>
                                <div id="divGuardian2Gender" class="form-group">
                                    <label id="lblGuardian2Gender" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                    <label for="guardian2Gender">G&eacute;nero</label>
                                    <select id="guardian2Gender" name="guardian2Gender" class="form-control" ${disabledForSelect}>
                                        <option value="N">Seleccione uno...</option>
                                        <option value="F" 
                                            <c:if test="${not empty user.guardian2 && user.guardian2.gender == 'F'}">selected</c:if>>
                                            Femenino
                                        </option>
                                        <option value="M"
                                            <c:if test="${not empty user.guardian2 && user.guardian2.gender == 'M'}">selected</c:if>>
                                            Masculino
                                        </option>                        
                                    </select>
                                </div>
                                <div id="divGuardian2Address" class="form-group">
                                    <label id="lblGuardian2Address" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                    <label for="guardian2Address">Direcci&oacute;n</label>
                                    <input id="guardian2Address" name="guardian2Address" type="text" maxlength="60"
                                           required="true" autocomplete="off"
                                           class="form-control" placeholder="Direcci&oacute;n"
                                           <c:if test="${not empty user.guardian2}">value="${user.guardian2.address}"</c:if>
                                           ${disabled}/>
                                </div>
                                <div id="divGuardian2Phone1" class="form-group">
                                    <label id="lblGuardian2Phone1" style="display: none" class="control-label" for="inputError"><i class="fa fa-times-circle-o"></i> Campo requerido</label>
                                    <label for="guardian2Phone1">Celular</label>
                                    <div class="input-group">
                                        <div class="input-group-addon">
                                            <i class="fa fa-phone"></i>
                                        </div>
                                        <input id="guardian2Phone1" name="guardian2Phone1" type="text" min="3000000000"
                                               class="form-control" <c:if test="${not empty user.guardian2}">value="${user.guardian2.phone1}"</c:if>
                                               data-inputmask='"mask": "(999) 999-9999"' data-mask
                                               ${disabled}/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="guardian2Phone2">Tel&eacute;fono</label>
                                    <div class="input-group">
                                        <div class="input-group-addon">
                                            <i class="fa fa-phone"></i>
                                        </div>
                                        <input id="guardian2Phone2" name="guardian2Phone2" type="text" 
                                               class="form-control" <c:if test="${not empty user.guardian2}">value="${user.guardian2.phone2}"</c:if>
                                               data-inputmask='"mask": "999-9999"' data-mask
                                               ${disabled}/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>