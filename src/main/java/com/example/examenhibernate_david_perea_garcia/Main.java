package com.example.examenhibernate_david_perea_garcia;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static  Session session;

    public static void main(String[] args) {

        /**
         * Codigo para deshabilitar los warnings de hibernate
         */
        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        globalLogger.setLevel(java.util.logging.Level.OFF);

        try {
            Conexiones.setUp();
            session = Conexiones.abrirSesion();




            Conexiones.cerrarSesion();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }




    /**
     * Se pide como parámetro los datos de la persona que se va a insertar, esos datos se meten en el constructor
     * del objeto tipo PlayerEntidad. Una vez que se crea el objeto con los datos introducidos, se crea una
     * transacción y se guarda la persona.
     * @param nick
     * @param password
     * @param email
     *//*
    private static void guardarPersona(String nick, String password, String email ) {


        PlayerEntity persona = new PlayerEntity(nick, password, email);
      Conexiones.abrirTransaccion();

        int id = (int) session.save(persona);
      Conexiones.commitTransaccion();
        System.out.println(id);

    }
*/
    /**
     * Método que requiere un int como parámetro que será el id de la persona que se desea eliminar. Se crea un
     * objeto de tipo PlaterEntidad y se recoge la persona con el id que es pasado por parámetro, luego se borra
     * a esa persona.
     * @param id
     *//*
    private static void eliminarPersona(int id){

        Conexiones.abrirTransaccion();
        // Recoge una persona con el id
        PlayerEntity persona = session.get(PlayerEntity.class, id);
        // Se borra esa persona
        session.delete(persona);
        Conexiones.commitTransaccion();

    }
*/

    /**
     * Este método requiere un int como parámetro, que será el id de una persona que se desea modificar. Al igual
     * que en el método eliminar, se obtiene una persona con este id, y una vez que se hacen las modificaciones,
     *  se actualiza dicha persona.
     * @param id
     * @param nickUpdate
     * @param passwordUpdate
     * @param emailUpdate
     *//*
    private static void actualizarPersona(int id, String nickUpdate, String passwordUpdate, String emailUpdate){

        Conexiones.abrirTransaccion();
        // Recoge una persona con el id
        PlayerEntity persona = session.get(PlayerEntity.class, id);
        // Se cambia el parámetro que se quiera seteando su atributo
        persona.setNick(nickUpdate);
        persona.setPassword(passwordUpdate);
        persona.setEmail(emailUpdate);
        // Se actualiza esa persona
        session.saveOrUpdate(persona);
        Conexiones.commitTransaccion();

    }
*/
    /**
     * Con este método obtenemos todos los datos de todas las personas. Primero se crea un objeto tipo CriteriaBuilder
     * donde se hará uso de la session. Luego se crean una serie de objetos para crear la conexión con la tabla
     * correspondiente y para coger todos los datos. Estos datos se recogen en una lista de tipo PlayerEntidad,
     * que se recorrerá con un for each para mostrar los datos.
     *//*
    public static void mostrarDatosPersona() {


        Conexiones.abrirTransaccion();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<PlayerEntity> cq = cb.createQuery(PlayerEntity.class);
        Root<PlayerEntity> rootEntry = cq.from(PlayerEntity.class);
        CriteriaQuery<PlayerEntity> all = cq.select(rootEntry);

        TypedQuery<PlayerEntity> allQuery = session.createQuery(all);

        for (PlayerEntity jugador:   allQuery.getResultList()
        ) {
            System.out.println(jugador.getNick() + " " +  jugador.getPassword()  +  " " +  jugador.getEmail());
        }
        Conexiones.commitTransaccion();

    }
*/



    /**
     * Método para listar de forma genérica, se cambia el tipo de List y la sentencia HQL o bien
     * el NamedQuery
     */
    public static void listar() {

        /* Ejemplo NamedQuery*/
      //  List<tProfesoresHQL> lista = listarNamedQueries("listaProfesores");
        List<String> lista = listarNamedQueries("listaProfesores");

        /* Ejemplo select nombre en NamedQuery
            List <tAlumnosHQL> lista = listarNamedQueries("listaNombreAlumnos");
        */

        /* Ejemplo de consulta
        List <tAlumnosHQL> lista = Conexiones.session.createQuery("from tAlumnosHQL WHERE ").getResultList();
         */

        /* Listado usando un parámetro, primero se le pasa el nombre de la NamedQuery, luego el nombre
        del parámetro puesto en la clase, en este caso apellido,y por último será el valora buscar

        List <tAlumnosHQL> lista = listarConParametro("listaAlumnosPorApellido","apellido","Muletas");
          */
        /* Ejemplo de namedQuert que devuelve un unique valor
        List <tAlumnosHQL> lista = listarNamedQueries("numeroAlumnado");
           System.out.println( resultado("numeroAlumnado"));
        */

        for(int i =0; i< lista.size();i++){

           // System.out.println( lista.get(i));
        }
    }


    /**
     * Método que se necestia para listar, recibe como parámetro los NamedQuery creados
     * en las clases
     * @param namedQuery
     * @return
     */
    public static List listarNamedQueries(String namedQuery) {
        TypedQuery lista = session.getNamedQuery(namedQuery);
        return lista.getResultList();
    }


    /**
     * Método que recibe el nombre de la Namedquery, del parámetro que hayamos puesto en la clase,
     * y del valora buscar
     * @param namedQuery
     * @param param
     * @param valor
     * @return
     */
    public static List listarConParametro(String namedQuery,String param, String valor) {
        TypedQuery lista = session.getNamedQuery(namedQuery).setParameter(param,valor);
        return lista.getResultList();
    }

    /**
     * Método que devuelve el valor de las consultas de agregación, pueden ser:
     * avg(...)
     * sum(...)
     * min(...)
     * max(...)
     * count(*), count(...), count(distinct ...), count(all...)
     * @param namedQuery
     * @return
     */
    public static long resultado(String namedQuery) {

        Query lista = session.getNamedQuery(namedQuery);

        // Para el uso del uniqueResult se importa import org.hibernate.query.Query;
        return (long)lista.uniqueResult();
    }


    /**
     * Esto va en la clase
     *
     * @NamedQueries({
     *         @NamedQuery(name="listaAlumnos", query="from tAlumnosHQL "),
     *         @NamedQuery(name="listaNombreAlumnos", query="select a.nombre from tAlumnosHQL a"),
     *         @NamedQuery(name="listaAlumnosPorApellido", query="select a from tAlumnosHQL a where a.apellido1 LIKE :apellido"),
     *         @NamedQuery(name = "numeroAlumnado", query = "select count(a) from tAlumnosHQL a")
     */


    /**
     * Método para posible lectura
     */

/*
    public static void leerFicheros(){

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader("C:\\Users\\david\\IdeaProjects\\ExamenHibernate_David_Perea_Garcia\\src\\VQ_Naves.txt"));
            Scanner sc = new Scanner(br);
            String lineasInsertar="";
            while (sc.hasNext()){
                lineasInsertar=sc.nextLine();


                Query query =  session.createNativeQuery(lineasInsertar);

                query.executeUpdate();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);


        }finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

*/


}
