package burbujas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lista {
    private Nodo primero;
    private Nodo ultimo;
    private int nElementos = 0;
    private int ordenElementos = 0;

    public Lista() {
        this.primero = null;
        this.ultimo = null;
    }

    public Lista insertarAlInicio(Trabajador obj) {
        Nodo nodo = new Nodo(obj);
        nodo.setEnlace(this.primero);
        this.primero = nodo;
        nElementos++;
        return this;
    }

    public void insertarEntre(Trabajador anterior, Trabajador obj) {
        Nodo nuevo = new Nodo(obj);
        Nodo nodo = buscarNodoAnterior(anterior);
        nElementos++;
        nuevo.setEnlace(nodo.getEnlace());
        nodo.setEnlace(nuevo);
    }

    private Nodo buscarNodoAnterior(Trabajador anterior) {
        Nodo target = this.primero;

        while (target.getEnlace() != null) {
            if (anterior.equals(target.getEnlace().getTrabajador())) {
                return target;
            }
            target = target.getEnlace();
        }
        return target;
    }

    public boolean esVacia() {
        return (this.primero == null & this.ultimo == null);
    }

    public void imprimirHistorial() {
        Nodo copiaLista = primero;
        if (primero == null) {
            System.out.println("No hay registros.");
        } else {
            while (copiaLista != null) {
                copiaLista.getTrabajador().mostrarDatos();
                copiaLista = copiaLista.getEnlace();
            }
        }
    }

    public void imprimirOrdenID(Lista i) {
        i.metodoBurbuja();
        i.imprimirHistorial();
    }

    public void imprimirOrdenCreado(Lista i) {
        i.metodoBurbujaOrdenCreacion();
        i.imprimirHistorial();
    }

    public int contador() {
        return nElementos;
    }

    public void metodoBurbuja() {
        Trabajador aux;
        for (int i = 0; i < nElementos; i++) {
            for (int j = 0; j < nElementos - 1; j++) {
                if (obtener(j).getTrabajador().getId() > obtener(j).getEnlace().getTrabajador().getId()) {
                    aux = obtener(j).getTrabajador();
                    obtener(j).setTrabajador(obtener(j + 1).getTrabajador());
                    obtener(j + 1).setTrabajador(aux);
                }
            }

        }
    }

    public void metodoBurbujaOrdenCreacion() {
        Trabajador aux;
        for (int i = 0; i < nElementos; i++) {
            for (int j = 0; j < nElementos - 1; j++) {
                if (obtener(j).getTrabajador().getOrden() < obtener(j).getEnlace().getTrabajador().getOrden()) {
                    aux = obtener(j).getTrabajador();
                    obtener(j).setTrabajador(obtener(j + 1).getTrabajador());
                    obtener(j + 1).setTrabajador(aux);
                }
            }

        }
    }

    public Nodo obtener(int i) {
        if (primero == null) {
            return null;
        } else {
            Nodo puntero = primero;
            int contador = 0;
            while (contador < i) {
                puntero = puntero.getEnlace();
                contador++;
            }
            return puntero;
        }
    }

    public Nodo obtenerPorID(int i) {
        if (primero == null) {
            return null;
        } else {
            Nodo puntero = primero;
            while (puntero.getTrabajador().getId() != i) {
                puntero = puntero.getEnlace();
                if (puntero == null) {
                    return null;
                }
            }
            return puntero;
        }
    }

    public void eliminar(int a) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String respuesta = "";
        while(!respuesta.equals("si") && !respuesta.equals("SI") && !respuesta.equals("Si") && !respuesta.equals("sI") && !respuesta.equals("no") && !respuesta.equals("NO") && !respuesta.equals("No") && !respuesta.equals("nO")){
            System.out.println("\n¿Seguro que desea eliminar el registro? Si/No");
            respuesta = lector.readLine().toString();
            if (!respuesta.equals("si") && !respuesta.equals("SI") && !respuesta.equals("Si") && !respuesta.equals("sI") && !respuesta.equals("no") && !respuesta.equals("NO") && !respuesta.equals("No") && !respuesta.equals("nO")) {
                System.out.println("Respuesta no válida...");
            }
        }
        if(respuesta.equals("si") || respuesta.equals("SI") || respuesta.equals("Si") || respuesta.equals("sI")){    
            if (obtenerPorID(a).equals(primero)) {
                primero = primero.getEnlace();
                nElementos--;
            } else {
                Nodo temp, anterior;
                anterior = primero;
                temp = primero.getEnlace();
                while (temp != null && temp.getTrabajador().getId() != a) {
                    anterior = anterior.getEnlace();
                    temp = temp.getEnlace();
                }
                if (temp != null) {
                    anterior.setEnlace(temp.getEnlace());
                    if (temp == ultimo) {
                        ultimo = anterior;
                    }
                }
                nElementos--;
            }
            System.out.println("Se ha eliminado exitosamente el trabajador con ID: " + a);
        }
    }

    public Trabajador pedirDatos(boolean modifID) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String nombre;
        String apellidoP;
        String apellidoM;
        int edad = 0;
        String genero;
        int id = 0;
        int orden = ordenElementos;
        double sueldo = 0;
        String puesto;
        boolean esNum;
        esNum = false;
        if (modifID == false) {
            id = (int) (Math.random() * 9000 + 1000);
            while (obtenerPorID(id) != null) {
                id = (int) (Math.random() * 1000 + 1);
            }
            System.out.println("La ID es: " + id);
            ordenElementos++;
        }

        System.out.println("\nNombre: ");
        nombre = lector.readLine();

        System.out.println("\nApellido Paterno: ");
        apellidoP = lector.readLine();

        System.out.println("\nApellido Materno: ");
        apellidoM = lector.readLine();

        esNum = false;
        while (esNum == false) {
            try {
                System.out.println("\nEdad: ");
                edad = Integer.parseInt(lector.readLine());
                esNum = true;
            } catch (NumberFormatException e) {
                System.out.println("\nEntrada no válida...");
                esNum = false;
            }
        }

        System.out.println("\nGénero: ");
        genero = lector.readLine();

        System.out.println("\nPuesto: ");
        puesto = lector.readLine();

        esNum = false;
        while (esNum == false) {
            try {
                System.out.println("\nSueldo: ");
                sueldo = Double.parseDouble(lector.readLine());
                esNum = true;
            } catch (NumberFormatException e) {
                System.out.println("\nEntrada no válida...");
                esNum = false;
            }
        }

        Trabajador aux = new Trabajador(nombre, apellidoP, apellidoM, edad, genero, id, orden, sueldo, puesto);
        return aux;
    }

    public void modificar(Lista j, int i) throws IOException {
        boolean x = true;
        int y = obtenerPorID(i).getTrabajador().getOrden();
        Trabajador reemplazo = pedirDatos(x);
        reemplazo.setId(i);
        reemplazo.setOrden(y);
        obtenerPorID(i).setTrabajador(reemplazo);
        System.out.println("\nSe ha modificado la entrada correctamente.");
    }

    public static void menu() throws NumberFormatException, IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Lista lista = new Lista();
        int op = 1;
        int id = 1;
        boolean x;
        boolean salir;
        boolean esNum = false;

        // Se regresa al menú siempre y cuando op no sea 0
        while (op > 0) {
            salir = false;
            while (salir == false) {
                try {// try para verificar que el valor de op sea válido.
                    System.out.println("\n\t MENÚ REGISTRO EMPLEADOS\n"); // Se muestran las opciones del menú al
                                                                          // usuario
                    System.out.println("1. Agregar registro.");
                    System.out.println("2. Eliminar registro.");
                    System.out.println("3. Modificar registro.");
                    System.out.println("4. Consultar historial de registro.");
                    System.out.println("5. Consultar registro por ID.");
                    System.out.println("0. Salir.");

                    System.out.println("\nOpción:");
                    op = Integer.parseInt(lector.readLine()); // Se pide el número de la acción
                    salir = true;
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada no válida..."); // Se imprime este mensaje indicando el error
                    op = 6;
                }

                switch (op) {
                case 1:
                    if (lista.contador() <= 8999) {
                        System.out.println("\n\t AGREGAR TRABAJADOR\n");
                        x = false;
                        lista.insertarAlInicio(lista.pedirDatos(x));
                    } else {
                        System.out.println("Capacidad llena...");
                    }
                    break;

                case 2:
                    System.out.println("\n\t ELIMINAR TRABAJADOR\n");
                    if (lista.contador() == 0) {
                        System.out.println("No hay registros.");
                    } else {
                        while (esNum == false) {
                            try {
                                System.out.println("\nID del trabajador a eliminar:");
                                id = Integer.parseInt(lector.readLine());
                                esNum = true;
                            } catch (NumberFormatException e) {
                                System.out.println("\nEntrada no válida...");
                            }
                        }
                        if (lista.obtenerPorID(id) != null) {
                            lista.eliminar(id);
                        } else {
                            if (lista.contador() == 0) {
                                System.out.println("No hay registros.");
                            } else {
                                System.out.println("\nNo se encontró la ID");
                            }
                        }
                    }
                    esNum = false;
                    break;

                case 3:
                    System.out.println("\n\t MODIFICAR TRABAJADOR\n");
                    if (lista.contador() == 0) {
                        System.out.println("No hay registros.");
                    } else {
                        while (esNum == false) {
                            try {
                                System.out.println("\nID del trabajador a modificar:");
                                id = Integer.parseInt(lector.readLine());
                                esNum = true;
                            } catch (NumberFormatException e) {
                                System.out.println("\nEntrada no válida...");
                            }
                        }
                        if (lista.obtenerPorID(id) != null) {
                            lista.modificar(lista, id);
                        } else {
                            System.out.println("\nNo se encontró la ID");
                        }
                    }
                    esNum = false;
                    break;

                case 4:
                    System.out.println("\n\t HISTORIAL DE REGISTRO\n");
                    lista.imprimirOrdenCreado(lista);
                    break;

                case 5:
                    System.out.println("\n\t CONSULTA ORDENADA POR ID\n");
                    lista.imprimirOrdenID(lista);
                    break;

                default:
                    salir = true;
                    break;
                }
            }
        }
    }
}
