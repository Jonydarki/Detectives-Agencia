import java.util.ArrayList;

public class Caso {
    private String nombre;
    private String codigo;
    private String detectiveResponsable;
    private Ubicacion[] ubicaciones;
    private ArrayList<Pista> pistas;

    // Constructor
    public Caso(String nombre, String codigo, String detectiveResponsable) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.detectiveResponsable = detectiveResponsable;
        this.ubicaciones = new Ubicacion[5];
        this.pistas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDetectiveResponsable() {
        return detectiveResponsable;
    }

    public Ubicacion[] getUbicaciones() {
        return ubicaciones;
    }

    public ArrayList<Pista> getPistas() {
        return pistas;
    }

    public void registrarUbicacion(int posicion, Ubicacion ubicacion) {

        validarPosicion(posicion);

        if (ubicaciones[posicion] != null) {
            throw new IllegalArgumentException(
                "La posición seleccionada ya contiene una ubicación."
            );
        }

        if (ubicacion == null) {
            throw new IllegalArgumentException(
                "No se puede registrar una ubicación nula."
            );
        }

        ubicaciones[posicion] = ubicacion;
    }

    public Ubicacion consultarUbicacion(int posicion) {

        validarPosicion(posicion);

        if (ubicaciones[posicion] == null) {
            return null;
        }

        return ubicaciones[posicion];
    }

    public void modificarUbicacion(
            int posicion,
            int nuevoNivelRiesgo,
            String nuevoEstado) {

        validarPosicion(posicion);

        if (ubicaciones[posicion] == null) {
            throw new IllegalArgumentException(
                "No existe una ubicación registrada en esa posición."
            );
        }

        ubicaciones[posicion].setNivelRiesgo(nuevoNivelRiesgo);
        ubicaciones[posicion].setEstado(nuevoEstado);
    }

    public void descartarUbicacion(int posicion) {

        validarPosicion(posicion);

        if (ubicaciones[posicion] == null) {
            throw new IllegalArgumentException(
                "No existe una ubicación registrada en esa posición."
            );
        }
        ubicaciones[posicion] = null;
    }

    public void registrarPista(Pista pista) {

        if (pista == null) {
            throw new IllegalArgumentException(
                "No se puede registrar una pista nula."
            );
        }

        if (buscarPista(pista.getCodigo()) != null) {
            throw new IllegalArgumentException(
                "Ya existe una pista con ese código."
            );
        }

        pistas.add(pista);
    }

    public Pista buscarPista(String codigo) {

        for (Pista pista : pistas) {

            if (pista.getCodigo().equals(codigo)) {
                return pista;
            }
        }

        return null;
    }

    public void modificarPista(
            String codigo,
            String nuevaDescripcion,
            String nuevoTipoEvidencia,
            int nuevoNivelImportancia,
            int nuevoNivelConfiabilidad) {

        Pista pista = buscarPista(codigo);

        if (pista == null) {
            throw new IllegalArgumentException(
                "No existe una pista con ese código."
            );
        }

        pista.setDescripcion(nuevaDescripcion);
        pista.setTipoEvidencia(nuevoTipoEvidencia);
        pista.setNivelImportancia(nuevoNivelImportancia);
        pista.setNivelConfiabilidad(nuevoNivelConfiabilidad);
    }

    public void eliminarPista(String codigo) {

        Pista pista = buscarPista(codigo);

        if (pista == null) {
            throw new IllegalArgumentException(
                "No existe una pista con ese código."
            );
        }

        pistas.remove(pista);
    }

    public int cantidadUbicaciones() {

        int cantidad = 0;

        for (Ubicacion ubicacion : ubicaciones) {

            if (ubicacion != null) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public int espaciosDisponibles() {

        return ubicaciones.length - cantidadUbicaciones();
    }

    // Obtener la ubicación con mayor nivel de riesgo
    public Ubicacion ubicacionMayorRiesgo() {

        Ubicacion mayor = null;

        for (Ubicacion ubicacion : ubicaciones) {

            if (ubicacion != null) {

                if (mayor == null ||
                    ubicacion.getNivelRiesgo() > mayor.getNivelRiesgo()) {

                    mayor = ubicacion;
                }
            }
        }

        return mayor;
    }

    // Cantidad de pistas registradas
    public int cantidadPistas() {

        return pistas.size();
    }

    public Pista pistaMayorImportancia() {

        if (pistas.isEmpty()) {
            return null;
        }

        Pista mayor = pistas.get(0);

        for (Pista pista : pistas) {

            if (pista.getNivelImportancia() >
                mayor.getNivelImportancia()) {

                mayor = pista;
            }
        }

        return mayor;
    }

    public Pista pistaMayorConfiabilidad() {

        if (pistas.isEmpty()) {
            return null;
        }

        Pista mayor = pistas.get(0);

        for (Pista pista : pistas) {

            if (pista.getNivelConfiabilidad() >
                mayor.getNivelConfiabilidad()) {

                mayor = pista;
            }
        }

        return mayor;
    }

    public double promedioImportancia() {

        if (pistas.isEmpty()) {
            return 0;
        }

        int suma = 0;

        for (Pista pista : pistas) {
            suma += pista.getNivelImportancia();
        }

        return (double) suma / pistas.size();
    }

    private void validarPosicion(int posicion) {

        if (posicion < 0 || posicion >= ubicaciones.length) {
            throw new IllegalArgumentException(
                "La posición debe estar entre 0 y 4."
            );
        }
    }
}