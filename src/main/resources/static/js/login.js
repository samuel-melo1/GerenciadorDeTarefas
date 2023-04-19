$(document).ready(function(){
    $('#form-login').submit(function(e){
        e.preventDefault();
        var usuario = {
            senha: $("#senha").val(),
            email: $("#email").val()
        }
        $.ajax({
            url: 'http://localhost:8080/usuarios/login',
            type: 'POST',
            data: JSON.stringify(usuario),
            contentType: "application/json",
            success: function(response){
                alert(' sucesso!');
                window.location.href = "index.html";
            },
            error: function(xhr, status, error) {
                console.log(xhr.responseText);
                console.log(status);
                console.log(error);
                alert("email ou senha incorretos")
            }
        });
    });
});