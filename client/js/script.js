$(document).ready(function(){
    $('#form-cadastro').submit(function(e){
        e.preventDefault();
        var usuario = {
            nome: $("#nome").val(),
            senha: $("#senha").val(),
            email: $("#email").val()
        }
        $.ajax({
            url: 'http://localhost:8080/usuarios',
            type: 'POST',
            data: JSON.stringify(usuario),
            contentType: "application/json",
            success: function(response){
                alert('Usuário cadastrado com sucesso!');
                window.location.href = "login.html";
            },
            error: function(xhr, status, error) {
                console.log(xhr.responseText);
                console.log(status);
                console.log(error);
                alert("tente novamente - erro")
            }
        });
    });
});