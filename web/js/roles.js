/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//El validate se aplica a un form. Se hace la relación mediante en "name"
//Se agrega function para que se ejecute cuando este cargado el formulario(DOM)
$(function(){
      
    $('#frmRole').validate({
        rules:{
//            Nombre del componente
            rolename:{
                //Reglas
                minlength:3,
                maxlength:20,
                required: true
            }
        },
//        Mensajes de los errores en base a su componente
        messages:{
            rolename:{
                minlength: "Debe tener más de 3 caracteres",
                maxlength: "Debe tener menos de 20 caracteres",
                required: "El nombre del rol es obligatorio"
            }
        },
//        Cuando se encuentra un error
        highlight: function(element){
            $(element).closest('.form-group').addClass('has-error');
        },
//        CUando ya no se tiene error
        unhighlight: function(element){
            $(element).closest('.form-group').removeClass('has-error');
        },
//        Como aparecera el error
        errorElement: 'span',
//        Clase a usar para el error
        errorClass: 'help-block',
//        Donde aparecera
        errorPlacement: function(error, element){
            error.insertAfter(element.parent());
        },
//        Comportamiento al precionar el boton
        submitHandler: function(form){
            //console.log("Al 1,000,000 viejon!!!");
            newRole();
            return false;
        }
    });
    
})  ;

function newRole() {
    //Parametro: Objeto de Javascript
    $.ajax({
        url: 'CrearRol', //Petición al servlet, este paramétro es una URL relativa para este ejemplo
        type: 'post', //Método que hará la peticion
        data: $('#frmRole').serialize() //Propiedad encarga de enviar datos, 
                //en esta situación se asocia el formulario encargado del registro
                /*data:{ rolename:$('#rolename').value } Otra forma de enviar parámetros*/
    }).done(//Si todo ocurre de forma correcta
            function (data) {//Respuesta de la peticion (JSon)                
                //Usando Growl
                $.growl.notice({
                    title: 'Exitoso',
                    message: data.message
                });//Titulo, Contenido
                $("#rolename").val('');//Limpiando el input
            })
            .fail(function (data) {//Si existió algun fallo //Data:Respuesta de la peticion (JSon)                
                $.growl.error({
                    title: 'Error!!',
                    message: data.message
                });
            })
}