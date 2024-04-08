/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author daniel
 */
public class Fichero {

    public static List<String> leerFichero(String nombreFichero) {
        //Creamos una lista para almacenar la que nos devolvera ReadAllLines
        List<String> lista = new ArrayList<>();
        //Importante usar un try para controlar una posible excepcion
        try {
            lista = Files.readAllLines(Paths.get(nombreFichero));
        } catch (IOException ex) {
            System.out.println("Error accediendo a " + nombreFichero);
        }
        lista.remove(0);//Eliminamos la primera porque no tiene el formato
        return lista;
    }

        public static List<Personas> extraerDatosPersonaPorLinea(List<String> lista) {
        //Creouna lista que devolvere
        List<Personas> listaDevolver = new ArrayList();

        for (int i = 0; i < lista.size(); i++) {
            //Separamos por , para obtener los datos de cada vehiculo
            String[] array = lista.get(i).split(",");//Corta por cada coma
            //Creamos un objeto de vehiculo y metemos los datos en cada campo
            boolean tem;
            if (array[6] == "true") {
                tem = true;
            } else {
                tem = false;
            }
            //LocalDate.of(array[5]., Month.MARCH, i)

            Personas temporal = new Personas(Integer.parseInt(array[0]), array[1], 
            array[2], array[3], array[4], LocalDate.parse(array[5],
            DateTimeFormatter.ofPattern("dd-MM-uuuu")), 
                    tem, array[7]);
            listaDevolver.add(temporal);

        }

        return listaDevolver;
    }

    public static void escribirString(Set<String> lista) {

        String[] array = lista.toArray(new String[lista.size() - 1]);

        for (int i = 0; i < array.length; i++) {

            try {
                Files.write(Paths.get("generos.txt"), (array[i] + "\n").getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException ex) {
                System.out.println("Error creando el fichero");
            }

        }

    }
    
     public static void escribirString(Map<String,Integer> lista) {

     List<Integer> listaValores = new ArrayList<>(lista.values());
     List<String> listaKeys= new ArrayList(lista.keySet());
     
       
      for (int i = 0; i < listaKeys.size(); i++) {

            try {
                Files.write(Paths.get("contadorGeneros.txt"), (listaKeys.get(i) + "," + listaValores.get(i)+"\n" ).getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException ex) {
                System.out.println("Error creando el fichero");
            }

        }
       
     

    }

}
