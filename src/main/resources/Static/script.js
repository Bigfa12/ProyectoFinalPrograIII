const formulario=document.getElementById("formulario-registro");

formulario.addEventListener("submit", function(e)
{

    const username=document.getElementById("input-username");
    const nombre=document.getElementById("input-nombre");
    const apellido=document.getElementById("input-apellido");
    const dni=document.getElementById("input-dni");
    const domicilio=document.getElementById("input-domicilio");
    const email=document.getElementById("input-email");
    const password=document.getElementById("input-contrasenia");
    const repPassword=document.getElementById("input-repContrasenia");

    const datos={
        username:username.value,
        nombre:nombre.value,
        apellido:apellido.value,
        dni:dni.value,
        domicilio:domicilio.value,
        email:email.value,
        contrasena:password.value,
    };

    if(password.value==repPassword.value)
    {
        fetch('http://localhost:8080/auth/register',
        {
            method:'POST', //El tipo de solicitud
            headers:
            {
                'Content-Type': 'application/json' //para indicar que vamos a enviar json
            },
            body: JSON.stringify(datos) //para convertir el objeto datos en json
        })
    }else{
        alert("Error, las contraseñas no son iguales");
    }
    
   
})