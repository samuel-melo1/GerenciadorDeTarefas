$(document).ready(function(){
    $('#cadastro_tarefa').submit(function(e){
        e.preventDefault();
        var tarefas = {
            titulo: $("#titulo").val(),
            descricao: $("#descricao").val(),
            data_inicio: $("#data_inicio").val(),
            prazo: $("#prazo").val(),
            prioridade:$("#prioridade").val(),
            status:$("#status").val()
        }

        var localDateDataInicio = moment(data_inicio, 'YYYY-MM-DD').toObject();
        var localDatePrazo = moment(prazo, 'YYYY-MM-DD').toObject();
        var dataInicioObj = new Date(localDateDataInicio.years, localDateDataInicio.months, localDateDataInicio.date);
        var prazoObj = new Date(localDatePrazo.years, localDatePrazo.months, localDatePrazo.date);
        
        $.ajax({
            url: 'http://localhost:8080/tarefas',
            type: 'POST',
            data: JSON.stringify(tarefas),
            contentType: "application/json",
            success: function(response){
                alert('Tarefa cadastrada com sucesso!');
            },
            error: function(xhr, status, error) {
                console.log(xhr.responseText);
                console.log(status);
                console.log(error);
                alert("tente novamente - erro")
            }
        })
    });
});
