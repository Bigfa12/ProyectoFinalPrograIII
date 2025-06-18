document.addEventListener("DOMContentLoaded", function()
{
    fetch("http://localhost:8080/records/top10")
    .then(response=>response.json())
    .then(data=>{
        console.log("data recibida", data);
        mostrarTop("record-pressBanca", data.PressBanca);
        mostrarTop("record-sentadilla", data.Sentadilla);
        mostrarTop("record-pesoMuerto", data.PesoMuerto);
    })
    .catch(error=>
    {
        console.log("Error al cargar los datos de records: ", error);
    }
    );

    function mostrarTop(containerId, records)
    {
        const container=document.getElementById(containerId);
        container.innerHTML='';

        records.forEach(record => {
            const p=document.createElement("p");
            p.textContent=`${record.nombre} - ${record.apellido} - ${record.peso}KG`
            container.appendChild(p);
        });
    }
})

function redirigirPago(plan, precio)
{
    window.open(`pago.html?plan=${plan}&precio=${precio}`, '_blank')
}