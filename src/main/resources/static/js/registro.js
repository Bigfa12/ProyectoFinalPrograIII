const formulario = document.getElementById("formulario-registro");

formulario.addEventListener("submit", function (e) {

    e.preventDefault();

    const username = document.getElementById("input-username");
    const nombre = document.getElementById("input-nombre");
    const apellido = document.getElementById("input-apellido");
    const dni = document.getElementById("input-dni");
    const domicilio = document.getElementById("input-domicilio");
    const email = document.getElementById("input-email");
    const password = document.getElementById("input-contrasenia");
    const repPassword = document.getElementById("input-repContrasenia");

    const fields = {
        username: {
            input: username,
            regex: /^[a-zA-Z0-9_]{4,16}$/,
            msg: "Debe tener entre 4 y 16 caracteres, solo letras, números o _",
        },
        nombre: {
            input: nombre,
            regex: /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]{2,}$/,
            msg: "Nombre inválido",
        },
        apellido: {
            input: apellido,
            regex: /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]{2,}$/,
            msg: "Apellido inválido",
        },
        dni: {
            input: dni,
            regex: /^\d{7,8}$/,
            msg: "DNI inválido (7-8 dígitos)",
        },
        domicilio: {
            input: domicilio,
            regex: /^.{5,}$/,
            msg: "Domicilio muy corto",
        },
        email: {
            input: email,
            regex: /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/,
            msg: "Email inválido",
        },
        contrasenia: {
            input: password,
            regex: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{6,}$/,
            msg: "Debe tener al menos 6 caracteres, mayúscula, minúscula, número y símbolo",
        }
    };

    let allValid = true;

    //el for recorre todos los campos de fields, la primera key es username, la segunda nombre y asi con todos
    for (const key in fields) {
        const { input, regex, msg } = fields[key];//esto agarra el input, el regex y el msg que esta recorriendo
        const errorSpan = document.getElementById(`error-${key}`)//busca el span

        if (!regex.test(input.value)) {
            errorSpan.textContent = msg;
            allValid = false;
        } else {
            errorSpan.textContent = "";//si es valido borra el error
        }
    }

    const errorRep = document.getElementById("error-repContrasenia");
    if (password.value !== repPassword.value) {
        errorRep.textContent="Las contraseñas no coinciden";
        allValid=false;
    }else{
        errorRep.textContent="";
    }

    if(allValid)
    {
        const datos = {
            username: username.value,
            nombre: nombre.value,
            apellido: apellido.value,
            dni: dni.value,
            domicilio: domicilio.value,
            email: email.value,
            contrasena: password.value,
        };


        fetch('http://localhost:8080/auth/register',
            {
                method: 'POST', //El tipo de solicitud
                headers:
                {
                    'Content-Type': 'application/json' //para indicar que vamos a enviar json
                },
                body: JSON.stringify(datos) //para convertir el objeto datos en json
            }).then(response => {
                if (response.ok) {
                    alert("Registro exitoso");
                    formulario.reset();
                } else {
                    alert("Hubo un error en el registro");
                }
            }
            )
    }
})