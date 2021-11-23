$(document).ready(function() {

    // FUNCIONES GUARDADOS PRODUCTOS
    $("#grabarProductos").click(function(){
        if(validadorProductos()){
           guardarProducto();
        }

    });

    $("#grabarEditProducto").click(function(){
       if(validadorProductosEdit()){
          guardarEditarProducto();
       }

    });


    // FUNCIONES DE MODALES CERRADAS

    $("#modalNuevoProducto").on('hidden.bs.modal', function () {
      limpiarDatosCargaProducto();
    });

    // FUNCIONES KEYUP
    $("#nombre").on('keyup', function(){
        $("#nombre").css("border-color","");
    });
    $("#precio").on('keyup', function(){
        $("#precio").css("border-color","");
    });
    $("#precioEdit").on('keyup', function(){
        $("#precioEdit").css("border-color","");
    });
    $("#nombreEdit").on('keyup', function(){
        $("#nombreEdit").css("border-color","");
    });


})