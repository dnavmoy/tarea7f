/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package daw;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 *
 * @author daniel
 */
public class Tarea7fDanielNavarro {

    public static void main(String[] args) {
        List<Personas> lista = Fichero.extraerDatosPersonaPorLinea(Fichero.leerFichero("personas.csv"));

        Set<String> setGenero = conjuntoPersonas(lista);

        for (String p : setGenero) {
            System.out.println(p);
        }

        Fichero.escribirString(setGenero);

        Map<String, Integer> mapa = contadorGeneros(lista);

        mapa.forEach((k, v) -> System.out.println(k + "," + v));
        Fichero.escribirString(mapa);

        //Obtener una lista de personas nacidas en 1985, ordenadas por su email.
        List<Personas> lista1985 = new ArrayList<>();
        for (Personas p : lista) {
            
            if (p.fecha().isAfter(LocalDate.of(1985, 01, 01)) && p.fecha().isBefore(LocalDate.of(1985, 12, 31))) {
                lista1985.add(p);
            }

        }
        lista1985.sort((p1, p2) -> p1.email().compareTo(p2.email()));

        System.out.println("lista 1985 sin stream");
        lista1985.forEach(System.out::println);

        //con Stream
        List<Personas> lista1985Stream = new ArrayList<>();
        lista1985Stream = lista.stream()
                .filter(p -> p.fecha().isAfter(LocalDate.of(1985, 01, 01)) && p.fecha().isBefore(LocalDate.of(1985, 12, 31)))
                .sorted((p1, p2) -> p1.email().compareTo(p2.email()))
                .toList();

        System.out.println("\nlista 1985 con stream");
        lista1985Stream.forEach(System.out::println);

        //Obtener un set de correos electrónicos, ordenados alfabéticamente, de 
        //aquellas personas cuyo género es "non-binary" y estén jubiladas.
        TreeSet<String> setEmail = new TreeSet<>();

        for (Personas p : lista) {
            if (p.genero().equalsIgnoreCase("Non-binary")&&p.jubilado()) {
                setEmail.add(p.email());
            }

        }
        System.out.println("\nmostrar lista de emails ordenados");
        setEmail.forEach(System.out::println);

        //Con Stream
        TreeSet<String> setEmailStream = new TreeSet<>();
        lista.stream()
                .filter(p -> p.genero().equalsIgnoreCase("Non-binary"))
                .filter(p-> p.jubilado())
                .forEach(p -> setEmailStream.add(p.email()));

        System.out.println("\nmostrar lista de emails ordenados con STream");
        setEmail.forEach(System.out::println);

        //Obtener el número de ciudades diferentes.
        Map<String, Integer> mapCiudades = new HashMap<>();
        for (int i = 0; i < lista.size(); i++) {
            if (mapCiudades.containsKey(lista.get(i).ciudad())) {
                mapCiudades.put(lista.get(i).ciudad(), mapCiudades.get(lista.get(i).ciudad()) + 1);
            } else {
                mapCiudades.put(lista.get(i).ciudad(), +1);
            }
        }
        mapCiudades.forEach((k, v) -> System.out.println("ciudad : " + k + " cantidad: " + v));
        System.out.println("\n\ntotal de ciudades: " + mapCiudades.size());

        //Stream
        System.out.println(" ciudades: "
                + lista.stream()
                        //.sorted((p1, p2) -> p1.ciudad().compareToIgnoreCase(p2.ciudad()))
                        .map(p -> p.ciudad())
                        .distinct()
                        .count()
        );

        //Comprobar si alguna persona se llama "Zondra".
        boolean zondra = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).nombre().equalsIgnoreCase("Zondra")) {
                zondra = true;                
            }

        }
        System.out.println("exsite alguna persona con el nombre de zondra?: " + zondra);

        //Stream
        System.out.println(" zondra Stream:?"
                + lista.stream()
                        .anyMatch(p -> p.nombre().equalsIgnoreCase("zondra"))
        );

        //Comprobar si ninguna persona vive en "Estepona".
        boolean estepona = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).ciudad().equalsIgnoreCase("Estepona")) {
                estepona = true;
            }

        }
        System.out.println("existe alguein en estepona?:" + estepona);

        //Stream
        System.out.println("Estepona Stream: "
                + lista.stream()
                        .anyMatch(p -> p.ciudad().equalsIgnoreCase("Estepona"))
        );

        //Saber cuantas personas tienen en su apellido la terminación "cía".
        int contadorCia = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).apellido().endsWith("cia")) {
                contadorCia++;
            }
        }
        System.out.println("personas con cia: " + contadorCia);

        //Stream
        System.out.println(" personas con cia Stream: "
                + lista.stream()
                        .filter(p -> p.apellido().endsWith("cia"))
                        .count()
        );

        //Obtener una lista con todas las edades de cada persona.
        Map<String, Integer> listaEdades = new HashMap<>();
        long edad;
        for (int i = 0; i < lista.size(); i++) {
            edad = ChronoUnit.YEARS.between(lista.get(i).fecha(), LocalDate.now());
            listaEdades.put(lista.get(i).nombre() +" "+ lista.get(i).apellido(), (int)edad);

        }

        listaEdades.forEach((k, v) -> System.out.println("nombre: " + k + "edad: " + v));
        
        
        //Stream: 
        
        Map<String,Integer> listaEdadesStream= new HashMap<>();
        lista.stream()
                .map(v -> listaEdadesStream.put(v.nombre()+" "+ v.apellido(),
                        (int)ChronoUnit.YEARS.between(v.fecha(), LocalDate.now())
                        
                ));
                
        listaEdadesStream.forEach((k, v) -> System.out.println("nombre: " + k + "edad: " + v));
        
        
        //Obtener la suma de todas las edades de las personas de la lista.
        int contadorEdad=0;
        
        Iterator<Integer> it = listaEdades.values().iterator();
        while(it.hasNext()){
            contadorEdad+=it.next();
        }
        System.out.println("total edad :" + contadorEdad);
       
                                
    
        //Stream
        contadorEdad=listaEdades.values().stream()
                 .mapToInt(Integer::intValue).sum();
                 
        System.out.println("suma edades stream : " +contadorEdad);
                
                
        //Obtener la media de edad de las personas de la lista.


        
        System.out.println("media de edad: " + lista.size()/contadorEdad);
        
        //¿¿con stream?
        
        
        
        //Obtener en un String todos los nombres de las personas concatenados.
        
        String nombresConcatenados="";
        
        for (int i =0; i<lista.size(); i++){
            nombresConcatenados=nombresConcatenados.concat(lista.get(i).nombre()+" "+ lista.get(i).apellido()+"\n ");
            
        }
        System.out.println(nombresConcatenados);
        
        
        //stream
        
        nombresConcatenados="";
        
        nombresConcatenados=lista.stream()
                .map(Personas::nombre).collect(Collectors.joining(" "));
                
                
             System.out.println(nombresConcatenados);   

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
