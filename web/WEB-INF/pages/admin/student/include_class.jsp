<div class="row">
    <div class="col-xs-12">
        <div class="box box-solid box-info">
            <div class="box-header">
                <h3 class="box-title">Director de grupo</h3>
            </div>
            <div class="box-body">
                <div class="row">
                    <div class="form-group col-xs-4">
                        <input id="groupSubjectName" name="groupSubjectName" type="text" maxlength="50" 
                               class="form-control" placeholder="-" value="${classroom.name}" readonly="readonly"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-12">
        <div class="box box-solid">
            <div class="box-header">
                <h3 class="box-title">Clases</h3>
            </div>
            <div class="box-body table-responsive">
                <table id="tblClasses" class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th style="width: 25%">Curso</th>
                            <th style="width: 25%">Sal&oacute;n</th>
                            <th style="width: 25%">Materia</th>
                            <th style="width: 25%">Nombre</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${classes}" var="classBO">
                            <tr>
                                <td>${classBO.classRoom.gradeBO.name}</td>
                                <td>${classBO.classRoom.name}</td>
                                <td>${classBO.subject.name}</td>
                                <td>${classBO.name}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>