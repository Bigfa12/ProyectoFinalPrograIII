document.addEventListener("DOMContentLoaded", () => {
    const navPlaceholder = document.getElementById("nav-placeholder");

    fetch("/src/main/resources/static/html/nav.html")
        .then(res => res.text())
        .then(navHtml => {
            navPlaceholder.innerHTML = navHtml;

            const navUsuario = document.getElementById("nav-usuario");
            const authHeader = localStorage.getItem('authHeader');


            if (authHeader) {
                navUsuario.innerHTML = `
            <li class="perfil-dropdown">
            <div class="avatar" id="perfil-btn"><img src="/src/main/resources/Static/images/imgPerfil.webp" alt="" class="imgPerfil"></div>
                <ul class="menu-perfil" id="menu-perfil">
                    <li><a href="/src/main/resources/Static/html/perfil.html">Perfil</a></li>
                    <li><a href="#" id="cerrar-sesion">Cerrar sesión</a></li>
                </ul>
            </li>
            `;

            document.getElementById("perfil-btn").addEventListener("click", () => 
            {
                document.getElementById("menu-perfil").classList.toggle("mostrar");//aca le decis que cuando se clickee el perfil haga lo que esta en el css de mostrar
            });

            document.getElementById("cerrar-sesion").addEventListener("click", ()=>{
                localStorage.removeItem("authHeader");
                location.reload();
            });
            }else
            {
                navUsuario.innerHTML = `
                <li><a href="/src/main/resources/Static/html/registrarse.html" target="_blank">Registrarse</a></li>
                <li><div class="linea"></div></li>
                <li><a href="/src/main/resources/Static/html/iniciarSesion.html" target="_blank">Iniciar sesión</a></li>
                `
            }
        })
})