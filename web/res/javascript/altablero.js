function showLoadingImage() {
    $("<div class='overlay'></div><div class='loading-img'></div>").appendTo(".box-primary");
}

function onSearchClick() {
    showLoadingImage();
    $("#frmSearch").submit();
}

function showSaveDialog() {
    $("#save-dialog").dialog({
        autoOpen: false,
        width: 400,
        modal: true,
        resizable: false,
        buttons: [{
            text: "Guardar",
            click: function() {
                $(this).dialog("close");
                showLoadingImage();
                $("#frmSave").submit();
            }
        },
        {
            text: "Cancelar",
            click: function() {
                $(this).dialog("close");
            }
        }]
    });
    $("#save-dialog").dialog("open");
}

function showDeactivateDialog() {
    $( "#deactivate-dialog" ).dialog({
        autoOpen: false,
        width: 400,
        modal: true,
        resizable: false,
        buttons: [{
            text: "Eliminar",
            click: function() {
                $( this ).dialog("close");
                showLoadingImage();
                document.getElementById("frmDeactivate").submit();
            }
        },
        {
            text: "Cancelar",
            click: function() {
                $( this ).dialog("close");
            }
        }]
    });
    $("#deactivate-dialog").dialog("open");
}

function showRequiredFieldsDialog() {
    $("#required-dialog").dialog({
        autoOpen: false,
        width: 400,
        modal: true,
        resizable: false,
        buttons: [{
            text: "Volver",
            click: function() {
                $(this).dialog("close");
            }
        }]
    });
    $("#required-dialog").dialog("open");
}

function changeClassNameAndStyle(divElement, labelElement, className, style) {
    if (divElement !== null) {
        divElement.attr("class", className);
    }
    if (labelElement !== null) {
        labelElement.css("display", style);
    }
}