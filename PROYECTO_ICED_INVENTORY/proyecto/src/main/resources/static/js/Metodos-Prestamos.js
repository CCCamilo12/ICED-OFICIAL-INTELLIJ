(document).ready(function() {
    // listar
    function obtenerListaPrestamos() {
        $.ajax({
            url: "http://localhost:8080/ListarPrestamos",
            type: "GET",
            dataType: "json",
            success: function(respuesta) {
                console.log(respuesta);
                let tablaBody = $("#tabla1-body");
                tablaBody.empty(); // Limpiar el contenido anterior de la tabla

                respuesta.forEach(function(prestamo) {
                    tablaBody.append(`<tr>
                        <td>${prestamo.presId}</td>
                        <td>${prestamo.pres_Fec_Entrega}</td>
                        <td>${prestamo.pres_Hora_Entrega}</td>
                        <td>${prestamo.pres_Tiempo_Limite}</td>
                        <td>${prestamo.pres_Observaciones_Entrega}</td>
                        <td>${prestamo.equ_id_equipos.equ_id}</td>
                        <td>${prestamo.usu_Documento_usurios.usu_Documento}</td>
                    </tr>`);
                });
            },
            error: function() {
                console.log("Error al obtener la lista de préstamos");
            }
        });
    }

// Agregar un préstamo
$(document).ready(function() {
    // Manejador del botón AgregarPrestamo
    $('#AgregarPrestamo').on('click', function(event) {
        event.preventDefault(); // Evitar que se recargue la página al enviar el formulario

        // Obtener los valores del formulario
        let pres_Fec_Entrega = $("#pres_Fec_Entrega").val();
        let pres_Hora_Entrega = $("#pres_Hora_Entrega").val();
        let pres_Tiempo_Limite = $("#pres_Tiempo_Limite").val();
        let pres_Observaciones_Entrega = $("#pres_Observaciones_Entrega").val();
        let equ_id_equipos = $("#equ_id_equipos").val();
        let usu_Documento_usurios = $("#usu_Documento_usurios").val();

        if (pres_Fec_Entrega === '' || pres_Hora_Entrega === '' || pres_Tiempo_Limite === '' || pres_Observaciones_Entrega === '' || equ_id_equipos === '' || usu_Documento_usurios === '') {
            alert("Por favor completa todos los campos");
            return;
        }

        // Crear el objeto de datos del préstamo
        let nuevoPrestamo = {
            pres_Fec_Entrega: pres_Fec_Entrega,
            pres_Hora_Entrega: pres_Hora_Entrega,
            pres_Tiempo_Limite: pres_Tiempo_Limite,
            pres_Observaciones_Entrega: pres_Observaciones_Entrega,
            equipo: {
                equ_id_equipos: equ_id_equipos
            },
            usuario: {
                usu_Documento_usurios: usu_Documento_usurios
            }
        };

        let datosenvio = JSON.stringify(nuevoPrestamo);

        // Enviar la solicitud AJAX para agregar el préstamo
        $.ajax({
            url: `/insertarPrestamo/{Eq}/{Us}/${equ_id_equipos}/${usu_Documento_usurios}`,
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: datosenvio,
            success: function(respuesta) {
                console.log(respuesta);
                // Actualizar la lista de préstamos después de agregar uno nuevo
                obtenerListaPrestamos();
                // Restablecer los valores del formulario
                $("#insertForm")[0].reset();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Error al agregar el préstamo - Código de estado: " + jqXHR.status);
                obtenerListaPrestamos();
            }
        });
    });

    // Llamar a la función para obtener la lista de préstamos al cargar la página
    obtenerListaPrestamos();
});
});

// Función para obtener la lista de préstamos
function obtenerListaPrestamos() {
    $.ajax({
        url: "http://localhost:8080/ListarPrestamos",
        type: "GET",
        dataType: "json",
        success: function(respuesta) {
            console.log(respuesta);
            let tablaBody = $("#tabla1-body");
            tablaBody.empty(); // Limpiar el contenido anterior de la tabla

            respuesta.forEach(function(prestamo) {
                tablaBody.append(`<tr>
                    <td>${prestamo.presId}</td>
                    <td>${prestamo.fechaEntrega}</td>
                    <td>${prestamo.pres_Tiempo_Limite}</td>
                    <td>${prestamo.tiempoLimite}</td>
                    <td>${prestamo.observacionesEntrega}</td>
                    <td>${prestamo.equipo.equ_id}</td>
                    <td>${prestamo.usuario.usu_Documento}</td>
                </tr>`);
            });
        },
        error: function() {
            console.log("Error al obtener la lista de préstamos");
        }
    });
}

//////////////////////////////Buscar Prestamo//////////////////////////////////////////////////////////////////////////////////////
$('#BuscarPrestamo').on('click', function() {
    let tablaEquipos = $("#tabla1-body");
    tablaEquipos.empty(); // Limpiar el contenido anterior de la tabla
    let dato = $("#id_equipoo").val();
    $.ajax({
        url: "http://localhost:8080/BuscarPrestamo/" + dato,
        type: "GET",
        dataType: "json",
        success: function(respuesta) {
            if (respuesta && respuesta.presId) {
                let equipoInfo = respuesta.equipo ? respuesta.equipo : {}; // Verificar si existe el campo 'equipo'
                let usuarioInfo = respuesta.usuario ? respuesta.usuario : {}; // Verificar si existe el campo 'usuario'
                tablaEquipos.append(`<tr>
                    <td>${respuesta.presId}</td>
                    <td>${respuesta.pres_Fec_Entrega}</td>
                    <td>${respuesta.pres_Hora_Entrega}</td>
                    <td>${respuesta.pres_Tiempo_Limite}</td>
                    <td>${respuesta.pres_Observaciones_Entrega}</td>
                    <td>${respuesta.equ_id_equipos.equ_id|| ''}</td> // Acceder al subcampo 'equ_id' o mostrar cadena vacía
                    <td>${respuesta.usu_Documento_usurios.usu_Documento || ''}</td> // Acceder al subcampo 'usu_Documento' o mostrar cadena vacía
                </tr>`);
            } else {
                alert("No se encontró el préstamo en la base de datos");
            }
        },

        error: function(jqXHR, textStatus, errorThrown) {
            if (jqXHR.status === 404) {
                alert("No se encontró el préstamo en la base de datos");
            } else {
                alert("Ha ocurrido un error en la solicitud: " + errorThrown);
            }
        }
    });
});
///////////////////////// final Buscar Prestamo/////////////////////////////////////////////////////////////////////////////////////////////////

//Eliminar prestamo
$('#EliminarPrestamo').on('click', function() {
    let codigo = $("#id_prestamoo").val();

    if (codigo === '') {
        alert('Por favor, completa el campo');
        return; // Detener la ejecución si hay campos vacíos
    }

    $.ajax({
        url: "http://localhost:8080/EliminarPrestamo/" + codigo,
        type: "DELETE",
        success: function(respuesta) {
            alert(respuesta);
            obtenerListaPrestamos();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            if (jqXHR.status === 404) {
                alert("No se encontró el prestamo en la base de datos");
            } else {
                alert("Ha ocurrido un error en la solicitud: " + errorThrown);
            }
        }
    });
});



// ACTUALIZAR EQUIPO
$('#ActualizarPrestamo').on('click',function(){
    let presId = $('#presId').val();

    // Realizar una solicitud al servidor para obtener los datos actuales del equipo
    $.ajax({
        url: "http://localhost:8080/BuscarEquipo/" + presId,
        type: "GET",
        success: function(respuesta){
            // Aquí obtienes los datos actuales del equipo desde la respuesta del servidor

            // Obtener los nuevos valores del formulario
            let fechaEntrega = $('#fechaEntrega_actualizar').val();
            let fechaDevolucion = $('#fechaDevolucion_actualizar').val();
            let horaEntrega = $('#horaEntrega_actualizar').val();
            let tiempoLimite = $('#tiempoLimite_actualizar').val();
            let observacionesEntrega = $('#observacionesEntrega_actualizar').val();
            let observacionesRecibido = $('#observacionesRecibido_actualizar').val();


            // Combinar los datos actuales con los nuevos valores
            let datosActualizados = {
                presId: presId,
                equi_tipo: equi_tipo || respuesta.equi_tipo, // Usar el valor actual si no se proporciona uno nuevo
                equi_modelo: equi_modelo || respuesta.equi_modelo,
                equi_color: equi_color || respuesta.equi_color,
                equi_serial: equi_serial || respuesta.equi_serial,
                equi_estado: equi_estado || respuesta.equi_estado,
                equi_especialidad: equi_especialidad || respuesta.equi_especialidad
            };

            let datosenvio = JSON.stringify(datosActualizados);

            $.ajax({
                url: "http://localhost:8080/ActualizarEquipo/",
                type: "POST",
                data: datosenvio,
                contentType: "application/JSON",
                success: function(respuesta){
                    alert(respuesta);
                    listarequipo();
                }
            });
        },
        error: function(error){
            console.log(error);
        }
    });
});







//no borrar estas funciones  cargarEquipos(); , cargarUsuarios(); son para que aparezca la informacion  de los select en el formulario html

function cargarEquipos() {
    $.ajax({
        url: "http://localhost:8080/listarEquipos",
        type: "GET",
        dataType: "JSON",
        success: function(respuesta) {
            let selectEquipos = $("#equipoId");
            selectEquipos.empty();

            for (let i = 0; i < respuesta.length; i++) {
                let equipo = respuesta[i];
                let optionText = equipo.equ_id + " - " + equipo.equi_serial;
                let option = $("<option></option>").attr("value", equipo.equi_serial).text(optionText);
                selectEquipos.append(option);
            }
        }
    });
}


function cargarUsuarios() {
    $.ajax({
        url: "http://localhost:8080/ListarUsuarios",
        type: "GET",
        dataType: "JSON",
        success: function(respuesta) {
            let selectUsuarios = $("#usuarioDocumento");
            selectUsuarios.empty();

            for (let i = 0; i < respuesta.length; i++) {
                let usuario = respuesta[i];
                let optionText = usuario.usu_Documento + " - " + usuario.usu_Nombre + " " + usuario.usu_Apellido;
                let option = $("<option></option>").attr("value", usuario.usu_Documento).text(optionText);
                selectUsuarios.append(option);
            }
        }
    });
}


// carga los datos de los selec que hay en el formulario
$(document).ready(function() {
    cargarEquipos();
    cargarUsuarios();
});


