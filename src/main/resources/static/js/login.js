const formulario = document.getElementById("formulario-iniciarSesion");

formulario.addEventListener("submit", function (e) {
  e.preventDefault();

  const username = document.getElementById("input-username");
  const password = document.getElementById("input-password");

  const fields = {
    username:
    {
      input: username,
      regex: /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/,
      msg: "Email inválido",
    },
    contrasenia: {
      input: password,
      regex: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{6,}$/,
      msg: "Debe tener al menos 6 caracteres, mayúscula, minúscula, número y símbolo"
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

  if (allValid) {
    const datos = {
      username: username.value,
      contrasenia: password.value,
    };

    fetch("http://localhost:8080/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(datos),
    })
    .then(response=>{
      if(response.ok)
      {
        localStorage.setItem('usuario', JSON.stringify({username:username.value}));//para guardar los datos del usuario en el navegador
        alert("Inicio de sesion exitoso");
        window.location.href='index.html';
      }else
      {
        alert("Usuario o contraseña incorrectos");
      }
    })

  }

});