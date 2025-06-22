
window.addEventListener("DOMContentLoaded", () => {
    const params = new URLSearchParams(window.location.search);
    const plan = params.get("plan");
    const precio = params.get("precio");

    if (plan && precio) {
        document.getElementById("planResumen").textContent = plan;
        document.getElementById("precioPlan").textContent = '$' + precio;
    }
})

const formulario = document.getElementById("formPago");

formulario.addEventListener("submit", function (e) {
    e.preventDefault();

    const nombreCompleto = document.getElementById("input-nombreCompleto");
    const nroTarjeta = document.getElementById("input-nroTarjeta");
    const mm = document.getElementById("input-mm");
    const yy = document.getElementById("input-yy");
    const cvv = document.getElementById("input-cvv");
    const dni = document.getElementById("input-dni");
    const plan = document.getElementById("planResumen").textContent.toLowerCase();


    const fields = {
        nombreCompleto:
        {
            input: nombreCompleto,
            regex: /^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+(?: [A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)+$/,
            msg: 'Cada palabra inicia con mayúscula, separadas por espacios',
        },
        nroTarjeta:
        {
            input: nroTarjeta,
            regex: /^\d{16}$/,
            msg: "Debe tener 16 digitos ",
        },
        mm:
        {
            input: mm,
            regex: /^(0[1-9]|1[0-2])$/,
            msg: "Debe ser de 01 a 12",
        },
        yy:
        {
            input: yy,
            regex: /^(202[5-9]|20[3-9][0-9])$/,
            msg: "Debe ser entre el año 2025 y 2099",
        },
        cvv:
        {
            input: cvv,
            regex: /^\d{3,4}$/,
            msg: "Debe tener 3 o 4 digitos",
        },
        dni:
        {
            input: dni,
            regex: /^\d{7,8}$/,
            msg: "Debe tener 7 u 8 digitos"
        }
    }

    let allValid = true;

    for (const key in fields) {
        const { input, regex, msg } = fields[key];
        const errorSpan = document.getElementById(`error-${key}`);

        if (!regex.test(input.value)) {
            errorSpan.textContent = msg;
            allValid = false;
        } else {
            errorSpan.textContent = "";
        }
    }

    const authHeader = localStorage.getItem("authHeader");

    if (allValid && authHeader) {

        const datos = {
            nroTarjeta: nroTarjeta.value,
            nombreTitular: nombreCompleto.value,
            fechaVencimiento: `${mm.value}/${yy.value}`,
            dni: dni.value,
            plan : plan
        };

        fetch("http://localhost:8080/clients/crearCliente", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Basic " + authHeader
            },
            credentials: "include",
            body: JSON.stringify(datos)

        }).then(response => {
            if (response.ok) {
                alert("Pago exitoso");
                window.location.href = 'index.html'
            } else {
                alert("Todavia no debe pagar");
            }
        }
        )
    }
})

