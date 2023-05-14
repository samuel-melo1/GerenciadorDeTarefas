document.addEventListener('DOMContentLoaded', function() {
  var path = window.location.pathname;

  // Verifica se o caminho corresponde à página desejada
  if (path.includes("tarefas")) {
      if (!getCookie("alertShown")) {
          alert('Usuário logado com sucesso!');
          setCookie("alertShown", "true", 1);
      }
  }

  // Adiciona o evento de clique ao input "sair"
  var sairInput = document.getElementById('sair');
  if (sairInput) {
      sairInput.addEventListener('click', function() {
          // Remove o cookie "alertShown"
          removeCookie("alertShown");
      });
  }
  document.getElementById('cadastro-tarefa').addEventListener('submit', function(event) {
      event.preventDefault(); 
  });
});

function setCookie(name, value, days) {
  var expires = "";
  if (days) {
      var date = new Date();
      date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
      expires = "; expires=" + date.toUTCString();
  }
  document.cookie = name + "=" + (value || "") + expires + "; path=/";
}
function getCookie(name) {
  var nameEQ = name + "=";
  var ca = document.cookie.split(';');
  for (var i = 0; i < ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) == ' ') c = c.substring(1, c.length);
      if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
  }
  return null;
}
function removeCookie(name) {
  document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}