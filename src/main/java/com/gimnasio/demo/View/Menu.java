package com.gimnasio.demo.View;

import com.gimnasio.demo.Controller.ClienteController;
import com.gimnasio.demo.Controller.RecordController;
import com.gimnasio.demo.Controller.RutinaController;
import com.gimnasio.demo.Controller.UsuarioController;
import com.gimnasio.demo.Enums.Ejercicio;
import com.gimnasio.demo.Model.Record;
import com.gimnasio.demo.Model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private RutinaController rutinaController;

    @Autowired
    private RecordController recordController;

    @Autowired
    private ClienteController clienteController;


    public Menu() {
    }

    public void menu(){
        int opcion = 0;
        String username = " ";
        String password = " ";
        Scanner sc = new Scanner(System.in);

        System.out.println(" -----Inicio de Sesion----- ");
        System.out.print("Ingrese nombre de usuario: ");
        username = sc.nextLine();

        System.out.print("Ingrese contrasenia: ");
        password = sc.nextLine();

        /// Validar si existe el admin (username y pass)

        while(opcion<4){
            System.out.println("-----------TRIBU GYM-----------");
            System.out.println(" 1 - Listar todos los usuarios ");
            System.out.println(" 2 - Listar todas las rutinas y ejercicios ");
            System.out.println(" 3 - Listar todos los records por ejercicio ");
            System.out.println(" 4 - Agregar record ");
            System.out.println(" 5 - Salir ");

            System.out.print("Ingrese su opcion: ");
            opcion = sc.nextInt();

            switch (opcion){
                case 1:
                    if(usuarioController != null){
                        System.out.println(usuarioController.listarUsuarios());
                    }
                    break;
                case 2:
                    if(rutinaController != null){
                        System.out.println(rutinaController.listarRutinasYejercicios().toString());
                    }
                    break;
                case 3:
                    System.out.println(" 1 - Press Banca ");
                    System.out.println(" 2 - Sentadilla ");
                    System.out.println(" 3 - PesoMuerto ");

                    System.out.print("Ingrese el ejercicio para ver los records: ");
                    opcion = sc.nextInt();

                    if(opcion == 1 && recordController != null){
                        System.out.println(recordController.verRecordsPorEjercicio(Ejercicio.PressBanca));
                    }else if (opcion==2 && recordController != null){
                        System.out.println(recordController.verRecordsPorEjercicio(Ejercicio.Sentadilla));
                    }else if (opcion==3 && recordController != null){
                        System.out.println(recordController.verRecordsPorEjercicio(Ejercicio.PesoMuerto));
                    }else{
                        System.out.println("No existe dicho ejercicio");
                    }

                    break;
                case 4:
                    Record record = new Record();

                    System.out.println("--------AGREGUE SU RECORD--------");
                    System.out.println(" 1 - Press Banca ");
                    System.out.println(" 2 - Sentadilla ");
                    System.out.println(" 3 - PesoMuerto ");

                    System.out.print("Ingrese el ejercicio: ");
                    opcion = sc.nextInt();

                    System.out.print("Ingrese el peso que levanto: ");
                    int peso = sc.nextInt();

                    System.out.print("Ingrese el id_cliente: ");
                    Long id_cliente = sc.nextLong();

                    if(opcion == 1 && recordController != null){
                        record.setEjercicio(Ejercicio.PressBanca);
                    }else if (opcion==2 && recordController != null){
                        record.setEjercicio(Ejercicio.Sentadilla);
                    }else if (opcion==3 && recordController != null){
                        record.setEjercicio(Ejercicio.PesoMuerto);
                    }else{
                        System.out.println("No existe dicho ejercicio");
                    }

                    if(clienteController.buscarClientePorID(id_cliente).isPresent());{
                        record.setCliente(clienteController.buscarClientePorID(id_cliente).get());
                        record.setPeso(peso);
                        recordController.altaRecord(record);
                    }



                    break;
                case 5:
                    System.out.println(" ¡¡Gracias por visitar TRIBU GYM!! ");
                    break;
            }

        }
    }


}
