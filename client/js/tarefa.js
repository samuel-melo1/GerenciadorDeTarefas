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

        
        $.ajax({
            type: "POST",
            url: 'http://localhost:8080/tarefas',
            data: JSON.stringify(),
            contentType: "application/json",
            success: function (response) {
            },
            error: function (xhr) {
                console.error("Erro ao obter ID do usuário logado:", xhr.responseText);
            }
        });
    });
});

