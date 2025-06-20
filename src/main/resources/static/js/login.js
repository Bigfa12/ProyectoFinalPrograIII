const formulario = document.getElementById("formulario-iniciarSesion");

formulario.addEventListener("submit", function (e) {
  e.preventDefault();

  const username = document.getElementById("input-username");
  const password = document.getElementById("input-password");

  const fields = {
    username:
    {
      input: username,
      regex: /^[a-zA-Z0-9_]{4,16}$/,
      msg: "Debe tener entre 4 y 16 caracteres, solo letras, números o _",
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

  const basicAuth = btoa(`${username.value}:${password.value}`);
  localStorage.setItem('authHeader', basicAuth);

  fetch("http://localhost:8080/auth/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": "Basic " + basicAuth
    },
    body: JSON.stringify(datos),
  })
  .then(response => {
    if (response.ok) {
      alert("Inicio de sesión exitoso");
      window.location.href = 'index.html';
    } else {
      alert("Usuario o contraseña incorrectos");
    }
  });
}


});