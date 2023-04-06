$(document).ready(function(){
    $('#cadastro_tarefa').submit(function(e){
        e.preventDefault();
        var tarefas = {
            titulo: $("#titulo").val(),
            descricao: $("#descricao").val(),
            data_inicio: $("#data_inicio").val(),
            prazo: $("#prazo").val(),
            prioridade: $("prioridade").val(),
        }
        var id;
        $.ajax({
            type: "GET",
            url: 'http://localhost:8080/usuarios/id',
            data: JSON.stringify(tarefas),
            contentType: "application/json",
            success: function (response) {
                id = response;
                console.log("ID do usuário logado:" + id);
            },
            error: function (xhr) {
                console.error("Erro ao obter ID do usuário logado:", xhr.responseText);
            }
        });
    });
});