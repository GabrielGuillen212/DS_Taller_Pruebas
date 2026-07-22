import java.util.*;
public class SalaCine {
    private String nombre;
    private List<Asiento> asientos;
    private int capacidadMaxima;

    public SalaCine(String nombre, int capacidadMaxima) {
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.asientos = new ArrayList<>();
    }

    public void agregarAsiento(Asiento asiento) {
        if (asientos.size() >= capacidadMaxima) {
            throw new IllegalStateException("La sala ha alcanzado su capacidad máxima");
        }
        for (Asiento a : asientos) {
            if (a.getCodigo().equals(asiento.getCodigo())) {
                throw new IllegalArgumentException("Ya existe un asiento con el código " + asiento.getCodigo());
            }
        }
        asientos.add(asiento);
    }

    public Asiento buscarAsiento(String codigo) {
        for (Asiento a : asientos) {
            if (a.getCodigo().equals(codigo)) {
                return a;
            }
        }
        throw new NoSuchElementException("Asiento no encontrado: " + codigo);
    }

    public int contarDisponibles() {
        int contador = 0;
        for (Asiento a : asientos) {
            if (!a.isOcupado()) {
                contador++;
            }
        }
        return contador;
    }

    public double calcularIngresoTotal() {
        double total = 0.0;
        for (Asiento a : asientos) {
            if (a.isOcupado()) {
                total += a.calcularPrecioBase();
            }
        }
        return total;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
}