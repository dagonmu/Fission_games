$(document).ready(function () {
            $('#tabla').DataTable({

                language: {
                    processing: "Tratamiento en curso...",
                    search:"",
                    searchPlaceholder: "Buscar",
                    lengthMenu: "_MENU_",
                    info: "Mostrando _START_ - _END_",
                    infoEmpty: "0 preguntas encontradas",
                    infoPostFix: "",
                    loadingRecords: "Cargando...",
                    zeroRecords: "No se encontraron datos con tu busqueda",
                    emptyTable: "No hay datos disponibles en la tabla.",
                    paginate: {
                        first: "Primero",
                        previous: "Anterior",
                        next: "Siguiente",
                        last: "Ultimo"
                    }
                },
                scrollY: 400,
                lengthMenu: [ [10, 25, -1], ["Filas: 10", "Filas: 25", "Filas: Todas"] ],
            });
        });