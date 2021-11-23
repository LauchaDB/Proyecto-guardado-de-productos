var idProductoEdit=0;

function validadorProductos(){
    var isValid = true;
    if($("#nombre").val() == ""){
        isValid = false;
        $("#nombre").css("border","1px solid red");
    }
    if($("#precio").val() == ""){
            isValid = false;
            $("#precio").css("border","1px solid red");
    }
    return isValid;

}



function validadorProductosEdit(){
    var isValid = true;
    if($("#nombreEdit").val() == ""){
        isValid = false;
        $("#nombreEdit").css("border","1px solid red");
    }
    if($("#precioEdit").val() == ""){
            isValid = false;
            $("#precioEdit").css("border","1px solid red");
    }
    return isValid;

}

function limpiarDatosCargaProducto(){
    $("#nombre").val("");
    $("#nombre").css("border","");
    $("#precio").val("");
    $("#precio").css("border","");
}

function guardarProducto(){
    var nombreProduct = $("#nombre").val();
    var precioProduct = $("#precio").val();

    var productosObject = {
        nombre: nombreProduct,
        precio: precioProduct
    };

    $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/save",
            data: JSON.stringify(productosObject),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (json) {
                 console.log("entro aaaaaaaaaaaaaaaaaaaaaqui");
                 console.log(json);
                 mensajito("PRODUCTO NUEVO","Producto creado correctamente.","success");
                 location.reload();
            },
            error: function (json) {
                console.log("ERROR");
                mensajito("ERROR","Ups, algo paso.","error");
            }
        });

   // location.reload();
}

function editProduct(idProduct){
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/listById",
        data: JSON.stringify(({ idProducto : idProduct})),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (json) {
            if(json.idProducto != null){
                idProductoEdit = json.idProducto;
            }
            if(json.precio != null){
                $("#precioEdit").val(json.precio);
            }
            if(json.nombre != null){
                $("#nombreEdit").val(json.nombre);
            }
            $("#modalEditProducto").modal("show");
        },
        error: function (e) {
            mensajito("ERROR","Ups, algo paso.","error");
        }
    });
}


function guardarEditarProducto(){
    var nombreProduct = $("#nombreEdit").val();
    var precioProduct = $("#precioEdit").val();

    var productosObject = {
        idProducto: idProductoEdit,
        nombre: nombreProduct,
        precio: precioProduct
    };

    $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/editById",
            data: JSON.stringify(productosObject),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (json) {
                 console.log("entro aaaaaaaaaaaaaaaaaaaaaqui");
                 console.log(json);
                 mensajito("PRODUCTO EDITADO","Producto editado correctamente.","success");
                 location.reload();
            },
            error: function (json) {
                console.log("ERROR");
                mensajito("ERROR","Ups, algo paso.","error");
            }
        });

}

function eliminarProduct(idProduct){
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/deleteById",
        data: JSON.stringify(({ idProducto : idProduct})),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            location.reload();
            mensajito("ELIMINADO OK","Producto eliminado correctamente.","success");
        },
        error: function (e) {
            console.log("ERROR");
            console.log(e);
            mensajito("ERROR","Ups, algo paso.","error");
        }
    });
}

function mensajito(titulo,mensaje,tipo){
    Swal.fire({
        icon: tipo,
        title: titulo,
        text: mensaje,
        type: tipo
    })
}
