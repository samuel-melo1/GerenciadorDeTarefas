$(document).ready(function(){
    $('#form-cadastro').submit(function(e){
        e.preventDefault();
        var formData = new FormData(this);
        $.ajax({
            url: 'http://localhost:8000/usuarios',
            type: 'POST',
            data: formData,
            success: function(data){
                alert('Usuário cadastrado com sucesso!');
            },
            error: function(xhr, status, error){
                console.log("Erro ao cadastrar usuario " + error);
            }
        });
    });
});