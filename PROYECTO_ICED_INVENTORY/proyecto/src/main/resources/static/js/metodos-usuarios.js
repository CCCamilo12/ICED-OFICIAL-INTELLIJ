//Listar datos de Usuarios
function ListarUsuario() {
    $(document).ready(function() {
        $('body').load('load', function(){
            let tablaBody = document.querySelector('#tabla2-body');
            tablaBody.innerHTML = '';
            $.ajax({
                url: "http://localhost:8080/ListarUsuarios",
                type: "GET",
                dataType: "JSON",
                success: function(respuesta) {
                    console.log(respuesta);
                    for (let i = 0; i < respuesta.length; i++) {
                        tablaBody.innerHTML += '<tr>' +
                            '<td>' + respuesta[i].usu_Documento + '</td>' +
                            '<td>' + respuesta[i].usu_Nombre + '</td>' +
                            '<td>' + respuesta[i].usu_Apellido + '</td>' +
                            '<td>' + respuesta[i].usu_Tipo + '</td>' +
                            '<td>' + respuesta[i].usu_Celular + '</td>' +
                            '<td>' + respuesta[i].usu_Correo + '</td>' +
                            '<td>' + respuesta[i].usu_Ficha + '</td>' +
                            '<td>'+
                            '<div class="btn-container">' +
                            '<button class="btnEliminar" onclick="eliminarUsuario(' + respuesta[i].usu_Documento + ')">Eliminar</button>'+
                            '<button class="btnActualizar" onclick="actualizarUsuario(' + respuesta[i].usu_Documento + ')">Actualizar</button>' +
                            '</div>' +
                            '</td>' +
                            '</tr>';
                    }
                }
            });
        });
    });
}
//nueva funcion para eliminar usuario
function eliminarUsuario(usu_Documento) {
    // Mostrar un SweetAlert2 de confirmación antes de realizar la eliminación
    Swal.fire({
        title: '¿Estás seguro?',
        text: 'Esta acción no se puede deshacer',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Aceptar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            // Si el usuario confirma, realizar la solicitud de eliminación
            $.ajax({
                url: "http://localhost:8080/EliminarUsuario/" + usu_Documento,
                type: "DELETE",
                success: function(respuesta) {
                    Swal.fire({
                        title: '¡Eliminado!',
                        text: 'El Usuario ha sido eliminado.',
                        icon: 'success'
                    });
                    ListarUsuario();
                    obtenerCantidadUsuarios();
                    //aqui otro listar
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    if (jqXHR.status === 404) {
                        Swal.fire('Error', 'No se encontró el Usuario en la base de datos', 'error');
                    } else {
                        Swal.fire('Error', 'Ha ocurrido un error en la solicitud: ' + errorThrown, 'error');
                    }
                }
            });
        }
    });
}

ListarUsuario();

//Funcion para agregar
$('#AgregarUsuario').on('click', function () {
    let datosUsario = {
        usu_Documento: parseInt($('#usu_Documento').val()),
        usu_Nombre: $('#usu_Nombre').val(),
        usu_Apellido: $('#usu_Apellido').val(),
        usu_Tipo: $('#usu_Tipo').val(),
        usu_Celular: $('#usu_Celular').val(),
        usu_Correo: $('#usu_Correo').val(),
        usu_Ficha: $('#usu_Ficha').val(),
    };
    let datosenvio = JSON.stringify(datosUsario);
    console.log(datosUsario);
    console.log(datosenvio);

    $.ajax({
        url: "http://localhost:8080/InsertarUsuario",
        type: "POST",
        data: datosenvio,
        contentType: "application/JSON",
        datatype: JSON,
        success: function (respuesta) {
            // Mensaje de éxito
            Swal.fire({
                icon: 'success',
                title: 'Usuario Agregado',
                text: 'El usuario se agregó con éxito.',
            });

            ListarUsuario();
            obtenerCantidadUsuarios();
        },
        error: function (xhr, status, error) {
            // Mensaje de error
            Swal.fire({
                icon: 'error',
                title: 'Error al Agregar Usuario',
                text: 'Hubo un error al intentar agregar el usuario. Por favor, intenta nuevamente más tarde.',
            });
        }
    });
});


//Funcion para buscar
$('#BuscarUsuario').on('click',function(){
    let tablaUsuario = document.querySelector('#tabla2-body');
    tablaUsuario.innerHTML = '';
    let dato = $("#id_usuarioo").val();
        $.ajax({
            url: "http://localhost:8080/BuscarUsuario/" + dato,
            type: "GET",
            dataType: "json",
            success: function(respuesta) {
                if (respuesta.hasOwnProperty('usu_Documento')){
                    tablaUsuario.innerHTML += '<tr>' +
                        '<td>' + respuesta.usu_Documento + '</td>' +
                        '<td>' + respuesta.usu_Nombre + '</td>' +
                        '<td>' + respuesta.usu_Apellido + '</td>' +
                        '<td>' + respuesta.usu_Tipo + '</td>' +
                        '<td>' + respuesta.usu_Celular + '</td>' +
                        '<td>' + respuesta.usu_Correo + '</td>' +
                        '<td>' + respuesta.usu_Ficha + '</td>' +
                        '</tr>';
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: 'No se encontró el Usuario en la base de datos',
                            confirmButtonText: 'Aceptar'
                        }).then(() => {
                            ListarUsuario(); // Volver a listar los equipos existentes
                        });
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    if (jqXHR.status === 404) {
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: 'No se encontró el Usuario en la base de datos',
                            confirmButtonText: 'Aceptar'
                        }).then(() => {
                            ListarUsuario(); // Volver a listar los equipos existentes
                        });
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: 'Ha ocurrido un error en la solicitud: ' + errorThrown,
                            confirmButtonText: 'Aceptar'
                        });
                    }
                }
    });
});


//funcion para Atrapar y aactulizar datos
function actualizarUsuario(usu_Documento) {
    $.ajax({
        url: "http://localhost:8080/BuscarUsuario/" + usu_Documento,
        type: "GET",
        success: function(respuesta) {
            // Populate the form fields with the fetched user data
            $('#usu_Documento_id_actualizar').val(respuesta.usu_Documento);
            $('#usu_Nombre_id_actualizar').val(respuesta.usu_Nombre);
            $('#usu_Apellido_id_actualizar').val(respuesta.usu_Apellido);
            $('#usu_Tipo_id_actualizar').val(respuesta.usu_Tipo);
            $('#usu_Celular_id_actualizar').val(respuesta.usu_Celular);
            $('#usu_Correo_id_actualizar').val(respuesta.usu_Correo);
            $('#usu_Ficha_id_actualizar').val(respuesta.usu_Ficha);
        },
        error: function(error) {
            console.log(error);
        }
    });

    $('#ActualizarUsuario').on('click', function(){
        // Get values from the form fields
        let usu_Documento = $('#usu_Documento_id_actualizar').val();
        let usu_Nombre = $('#usu_Nombre_id_actualizar').val();
        let usu_Apellido = $('#usu_Apellido_id_actualizar').val();
        let usu_Tipo = $('#usu_Tipo_id_actualizar').val();
        let usu_Celular = $('#usu_Celular_id_actualizar').val();
        let usu_Correo = $('#usu_Correo_id_actualizar').val();
        let usu_Ficha = $('#usu_Ficha_id_actualizar').val();

        // Create an object with the updated user data
        let datosActualizados = {
            usu_Documento: usu_Documento,
            usu_Nombre: usu_Nombre,
            usu_Apellido: usu_Apellido,
            usu_Tipo: usu_Tipo,
            usu_Celular: usu_Celular,
            usu_Correo: usu_Correo,
            usu_Ficha: usu_Ficha
        };

        let datosenvio = JSON.stringify(datosActualizados);

        $.ajax({
            url: "http://localhost:8080/ActualizarUsuario/",
            type: "POST",
            data: datosenvio,
            contentType: "application/JSON",
            success: function(respuesta){
                Swal.fire({
                    icon: 'success',
                    title: '¡Éxito!',
                    text: 'El usuario se actualizó con éxito.',
                    confirmButtonText: 'Aceptar'
                }).then(() => {
                    listarUsuarios(); // Call the user listing function
                });
            }
        });
    });
}



//funciones adicionales

function obtenerCantidadUsuarios() {
    $.ajax({
        url: "http://localhost:8080/contarUsuarios",
        type: "GET",
        dataType: "text",
        success: function(respuesta) {
            $('.cantidad').text(respuesta); // Actualiza el contenido del contador1 con la cantidad obtenida
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Error al obtener la cantidad de usuarios:", errorThrown);
        }
    });
}

$(document).ready(function() {
    obtenerCantidadUsuarios();
});

