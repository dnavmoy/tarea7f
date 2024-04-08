/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package daw;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author daniel
 */
public class Tarea7cDanielNavarro {

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


