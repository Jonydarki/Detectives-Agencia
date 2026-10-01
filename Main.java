import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Caso caso = null;
        boolean salir = false;

        System.out.println("Bienvenido a la Agencia De Detectives");

        caso = crearCaso(scanner);

        while (!salir) {

            mostrarMenu();

            try {

                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {

                    case 1:
                        caso = crearCaso(scanner);
                        break;

                    case 2:
                        registrarUbicacion(scanner, caso);
                        break;

                    case 3:
                        consultarUbicaciones(caso);
                        break;

                    case 4:
                        consultarUnaUbicacion(scanner, caso);
                        break;

                    case 5:
                        modificarUbicacion(scanner, caso);
                        break;

                    case 6:
                        descartarUbicacion(scanner, caso);
                        break;

                    case 7:
                        registrarPista(scanner, caso);
                        break;

                    case 8:
                        consultarPistas(caso);
                        break;

                    case 9:
                        buscarPista(scanner, caso);
                        break;

                    case 10:
                        modificarPista(scanner, caso);
                        break;

                    case 11:
                        eliminarPista(scanner, caso);
                        break;

                    case 12:
                        mostrarReporte(caso);
                        break;

                    case 13:
                        salir = true;
                        System.out.println("\nPrograma finalizado.");
                        break;

                    default:
                        System.out.println(
                            "Opción inválida. Seleccione una opción entre 1 y 13."
                        );
                }

            } catch (InputMismatchException e) {

                System.out.println(
                    "\nError: debe ingresar un valor numérico."
                );

                // Limpiar la entrada incorrecta
                scanner.nextLine();

            } catch (IllegalArgumentException e) {

                System.out.println(
                    "\nError: " + e.getMessage()
                );

            } finally {

                System.out.println("\n----------------------------------------");

            }
        }

        scanner.close();
    }

    public static Caso crearCaso(Scanner scanner) {
        System.out.println("\n------------------------------------------");
        System.out.println("           Nuevo Caso");
        System.out.println("\n------------------------------------------");
        System.out.print("Nombre del caso: ");
        String nombre = scanner.nextLine();

        System.out.print("Código de identificación: ");
        String codigo = scanner.nextLine();

        System.out.print("Nombre del detective responsable: ");
        String detective = scanner.nextLine();

        Caso nuevoCaso = new Caso(nombre, codigo, detective);

        System.out.println("\nCaso creado correctamente.");

        return nuevoCaso;
    }

    public static void mostrarMenu() {

        System.out.println("\n------------------------------------------");
        System.out.println("       Bienvenido al Menu Principal");
        System.out.println("------------------------------------------");
        System.out.println("1.  Nuevo caso");
        System.out.println("2.  Registrar ubicación");
        System.out.println("3.  Consultar ubicaciones");
        System.out.println("4.  Consultar una ubicación");
        System.out.println("5.  Modificar ubicación");
        System.out.println("6.  Eliminar ubicación");
        System.out.println("7.  Registrar pista");
        System.out.println("8.  Consultar pistas");
        System.out.println("9.  Buscar pista");
        System.out.println("10. Modificar pista");
        System.out.println("11. Eliminar pista");
        System.out.println("12. Mostrar reporte de investigación");
        System.out.println("13. Salir");
        System.out.println("========================================");
    }

    public static void registrarUbicacion(
            Scanner scanner,
            Caso caso) {

        System.out.println("\n-------------------------------------------");
        System.out.println("        Registrar ubicación");
        System.out.println("-------------------------------------------");

        System.out.print("Posición del arreglo (0-4): ");
        int posicion = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Dirección o descripción (Lugar): ");
        String direccion = scanner.nextLine();

        System.out.print("Nivel de riesgo (1-10): ");
        int riesgo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Estado actual (Pendiente, En investigacion, Descartada, Concluida): ");
        String estado = scanner.nextLine();

        Ubicacion ubicacion = new Ubicacion(
            codigo,
            nombre,
            direccion,
            riesgo,
            estado
        );

        caso.registrarUbicacion(posicion, ubicacion);

        System.out.println("\nUbicación registrada correctamente.");
    }

    public static void consultarUbicaciones(Caso caso) {

        System.out.println("\n-------------------------------------------");
        System.out.println("    Consultar ubicaciones Registradas");
        System.out.println("-------------------------------------------");

        boolean existen = false;

        Ubicacion[] ubicaciones = caso.getUbicaciones();

        for (int i = 0; i < ubicaciones.length; i++) {

            if (ubicaciones[i] != null) {

                existen = true;

                System.out.println("\nPosición: " + i);
                System.out.println(ubicaciones[i]);
            }
        }

        if (!existen) {
            System.out.println("No existen ubicaciones registradas.");
        }
    }

    public static void consultarUnaUbicacion(
            Scanner scanner,
            Caso caso) {

        System.out.println("\n-------------------------------------------");
        System.out.println("    Consultar una ubicación especifica");
        System.out.println("-------------------------------------------");

        System.out.print("Ingrese la posición (0-4): ");
        int posicion = scanner.nextInt();
        scanner.nextLine();

        Ubicacion ubicacion = caso.consultarUbicacion(posicion);

        if (ubicacion == null) {

            System.out.println(
                "La posición seleccionada está vacía."
            );

        } else {

            System.out.println("\nInformación de la ubicación:");
            System.out.println(ubicacion);
        }
    }

    public static void modificarUbicacion(
            Scanner scanner,
            Caso caso) {

        System.out.println("\n-------------------------------------------");
        System.out.println("         Modificar ubicación");
        System.out.println("-------------------------------------------");

        System.out.print("Ingrese la posición (0-4): ");
        int posicion = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nuevo nivel de riesgo (1-10): ");
        int riesgo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nuevo estado: ");
        String estado = scanner.nextLine();

        caso.modificarUbicacion(
            posicion,
            riesgo,
            estado
        );

        System.out.println("\nUbicación modificada correctamente.");
    }

    public static void descartarUbicacion(
            Scanner scanner,
            Caso caso) {

        System.out.println("\n-------------------------------------------");
        System.out.println("         Eliminar ubicación");
        System.out.println("-------------------------------------------");

        System.out.print("Ingrese la posición (0-4): ");
        int posicion = scanner.nextInt();
        scanner.nextLine();

        caso.descartarUbicacion(posicion);

        System.out.println(
            "\nUbicación descartada correctamente."
        );
        System.out.println(
            "La posición vuelve a estar disponible."
        );
    }

    public static void registrarPista(
            Scanner scanner,
            Caso caso) {

        System.out.println("\n-----------------------------------------");
        System.out.println("          Registrar una pista");
        System.out.println("-----------------------------------------");

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Tipo de evidencia: ");
        String tipo = scanner.nextLine();

        System.out.print("Nivel de importancia (1-10): ");
        int importancia = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nivel de confiabilidad (0-100): ");
        int confiabilidad = scanner.nextInt();
        scanner.nextLine();

        Pista pista = new Pista(
            codigo,
            descripcion,
            tipo,
            importancia,
            confiabilidad
        );

        caso.registrarPista(pista);

        System.out.println("\nPista registrada correctamente.");
    }

    public static void consultarPistas(Caso caso) {

        System.out.println("\n-----------------------------------------");
        System.out.println("       Consultar Pistas Registradas");
        System.out.println("-----------------------------------------");

        if (caso.getPistas().isEmpty()) {

            System.out.println("No existen pistas registradas.");
            return;
        }

        for (Pista pista : caso.getPistas()) {

            System.out.println("\n" + pista);
        }
    }

    public static void buscarPista(
            Scanner scanner,
            Caso caso) {

        System.out.println("\n-----------------------------------------");
        System.out.println("        Buscar Pista Especifica");
        System.out.println("-----------------------------------------");

        System.out.print("Código de la pista: ");
        String codigo = scanner.nextLine();

        Pista pista = caso.buscarPista(codigo);

        if (pista == null) {

            System.out.println(
                "No se encontró una pista con ese código."
            );

        } else {

            System.out.println("\nPista encontrada:");
            System.out.println(pista);
        }
    }

    public static void modificarPista(
            Scanner scanner,
            Caso caso) {

        System.out.println("\n-----------------------------------------");
        System.out.println("         Modificar Una Pista");
        System.out.println("-----------------------------------------");

        System.out.print("Código de la pista: ");
        String codigo = scanner.nextLine();

        System.out.print("Nueva descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Nuevo tipo de evidencia: ");
        String tipo = scanner.nextLine();

        System.out.print("Nuevo nivel de importancia (1-10): ");
        int importancia = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nuevo nivel de confiabilidad (0-100): ");
        int confiabilidad = scanner.nextInt();
        scanner.nextLine();

        caso.modificarPista(
            codigo,
            descripcion,
            tipo,
            importancia,
            confiabilidad
        );

        System.out.println("\nPista modificada correctamente.");
    }

    public static void eliminarPista(
            Scanner scanner,
            Caso caso) {

        System.out.println("\n-----------------------------------------");
        System.out.println("         Eliminar Una Pista");
        System.out.println("-----------------------------------------");

        System.out.print("Código de la pista: ");
        String codigo = scanner.nextLine();

        caso.eliminarPista(codigo);

        System.out.println("\nPista eliminada correctamente.");
    }

    public static void mostrarReporte(Caso caso) {

        System.out.println("\n-----------------------------------------");
        System.out.println("      Reporte De Investigacion");
        System.out.println("-----------------------------------------");

        System.out.println("\nDATOS DEL CASO");
        System.out.println("Nombre: " + caso.getNombre());
        System.out.println("Código: " + caso.getCodigo());
        System.out.println(
            "Detective responsable: "
            + caso.getDetectiveResponsable()
        );

        System.out.println("\nUBICACIONES");
        System.out.println(
            "Ubicaciones registradas: "
            + caso.cantidadUbicaciones()
        );

        System.out.println(
            "Espacios disponibles: "
            + caso.espaciosDisponibles()
        );

        Ubicacion mayorRiesgo = caso.ubicacionMayorRiesgo();

        if (mayorRiesgo != null) {

            System.out.println(
                "Ubicación con mayor nivel de riesgo:"
            );
            System.out.println(mayorRiesgo);

        } else {

            System.out.println(
                "No existen ubicaciones registradas."
            );
        }

        System.out.println("\nPISTAS");
        System.out.println(
            "Pistas registradas: "
            + caso.cantidadPistas()
        );

        if (caso.getPistas().isEmpty()) {

            System.out.println(
                "No existen pistas para realizar cálculos."
            );

        } else {

            Pista mayorImportancia =
                caso.pistaMayorImportancia();

            Pista mayorConfiabilidad =
                caso.pistaMayorConfiabilidad();

            System.out.println(
                "\nPista con mayor nivel de importancia:"
            );
            System.out.println(mayorImportancia);

            System.out.println(
                "\nPista con mayor nivel de confiabilidad:"
            );
            System.out.println(mayorConfiabilidad);

            System.out.println(
                "\nPromedio del nivel de importancia: "
                + caso.promedioImportancia()
            );
        }
    }
}