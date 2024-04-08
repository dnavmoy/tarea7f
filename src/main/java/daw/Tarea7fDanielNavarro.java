/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package daw;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author daniel
 */
public class Tarea7fDanielNavarro {

    public static void main(String[] args) {
       List<Personas> lista = Fichero.extraerDatosPersonaPorLinea(Fichero.leerFichero("personas.csv"));

        //for (Personas p : lista){
        //    System.out.println(p);
        //}
        Set<String> setGenero = conjuntoPersonas(lista);

        for (String p : setGenero) {
            System.out.println(p);
        }

        Fichero.escribirString(setGenero);

        Map<String, Integer> mapa = contadorGeneros(lista);

        mapa.forEach((k, v) -> System.out.println(k + "," + v));
        Fichero.escribirString(mapa);
        
        
       //Obtener una lista de personas nacidas en 1985, ordenadas por su email.
       List<Personas> lista1985= new ArrayList<>();
       for(Personas p : lista){
           
           if(p.fecha().isAfter(LocalDate.of(1985, 01, 01))&& p.fecha().isBefore(LocalDate.of(1985, 12, 31))){
               lista1985.add(p);
           }
           
           
       }
       lista1985.sort((p1,p2) -> p1.email().compareTo(p2.email()));
       
       System.out.println("lista 1985 sin stream");
       lista1985.forEach(System.out::println);
       
       //con Stream
       List<Personas> lista1985Stream= new ArrayList<>();
       lista1985Stream=lista.stream()
               .filter(p -> p.fecha().isAfter(LocalDate.of(1985, 01, 01))&& p.fecha().isBefore(LocalDate.of(1985, 12, 31)))
               .sorted((p1,p2) -> p1.email().compareTo(p2.email()))
               .toList();
               
               
        System.out.println("\nlista 1985 con stream");
      lista1985Stream.forEach(System.out::println);
       
        
      //Obtener un set de correos electrónicos, ordenados alfabéticamente, de 
      //aquellas personas cuyo género es "non-binary" y estén jubiladas.
      TreeSet<String> setEmail = new TreeSet<>();
      
      for(Personas p : lista){
          if(p.genero().equals(new Personas(0, "", "", "", "Non-binary", LocalDate.of(1980, 01, 01), true, ""))
                  ){
              setEmail.add(p.email());
          }
          
          
      }
        System.out.println("\nmostrar lista de emails ordenados");
      setEmail.forEach(System.out::println);
      
      
      
        
    }
    
    
    
    

    public static Set<String> conjuntoPersonas(List<Personas> listasPersonas) {

        Set<String> setGenero = new HashSet();

        for (int i = 0; i < listasPersonas.size(); i++) {
            setGenero.add(listasPersonas.get(i).genero());
        }

        return setGenero;
    }

    public static Map<String, Integer> contadorGeneros(List<Personas> lista) {
        Map<String, Integer> mapa = new TreeMap<>();

        for (int i = 0; i < lista.size() - 1; i++) {
            if (mapa.containsKey(lista.get(i).genero())) {
                mapa.replace(lista.get(i).genero(), mapa.get(lista.get(i).genero()) + 1);

            } else {
                mapa.put(lista.get(i).genero(), 1);
            }

        }

        return mapa;

    }
    }

