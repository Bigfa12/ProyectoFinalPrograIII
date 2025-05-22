const formulario=document.getElementById("formulario-registro");

formulario.addEventListener("submit", function(e)
{
    e.preventDefault();

    const nombre=document.getElementById("input-nombre");
    const dni=document.getElementById("input-dni");
    const email=document.getElementById("input-email");
    const password=document.getElementById("input-contrasenia");

    const datos={
        nombreUsuario:nombre.value,
        dni:dni.value,
        email:email.value,
        contrasena:password.value
    };

    fetch('http://localhost:8080/register',
        {
            method:'POST', //El tipo de solicitud
            headers:
            {
                'Content-Type': 'application/json' //para indicar que vamos a enviar json
            },
            body: JSON.stringify(datos) //para convertir el objeto datos en json
        })
})