  document.addEventListener("DOMContentLoaded", function () {
  const basicAuth=localStorage.getItem('authHeader');

  fetch("http://localhost:8080/usuario/miPerfil", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      "Authorization": "Basic " + basicAuth
    }
  })
  .then(response => response.json())
  .then(data => {
    const perfil=document.getElementById("miPerfil");

    perfil.innerHTML+=`
        <div class="item-perfil"><span class="label">Nombre: </span>${data.usuario.nombre}</div>
        <div class="item-perfil"><span class="label">Apellido: </span>${data.usuario.apellido}</div>
        <div class="item-perfil"><span class="label">Email: </span>${data.usuario.email}</div>
        <div class="item-perfil"><span class="label">Nombre de usuario: </span>${data.username}</div>
        <div class="item-perfil"><span class="label">DNI: </span>${data.usuario.dni}</div>
    `
  });
});