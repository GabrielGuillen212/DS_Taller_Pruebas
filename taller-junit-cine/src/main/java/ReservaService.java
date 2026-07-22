import java.util.*;
public class ReservaService {
    private static final int MAX_ASIENTOS_POR_COMPRA = 6;
    private SalaCine sala;

    public ReservaService(SalaCine sala) {
        this.sala = sala;
    }

    public double reservarAsientos(List<String> codigosAsientos) {
        if (codigosAsientos == null || codigosAsientos.isEmpty()) {
            throw new IllegalArgumentException("Debe indicar al menos un asiento");
        }
        if (codigosAsientos.size() > MAX_ASIENTOS_POR_COMPRA) {
            throw new IllegalArgumentException(
                "No se pueden reservar más de " + MAX_ASIENTOS_POR_COMPRA + " asientos por compra"
            );
        }

        double subtotal = 0.0;
        List<Asiento> reservados = new ArrayList<>();

        for (String codigo : codigosAsientos) {
            Asiento asiento = sala.buscarAsiento(codigo);
            asiento.ocupar();
            reservados.add(asiento);
            subtotal += asiento.calcularPrecioBase();
        }

        return aplicarDescuento(subtotal, reservados.size());
    }

    private double aplicarDescuento(double subtotal, int cantidadAsientos) {
        double descuento = 0.0;
        if (cantidadAsientos >= 4) {
            descuento = 0.15; // 15% para grupos grandes
        } else if (cantidadAsientos >= 2) {
            descuento = 0.05; // 5% para pares/tríos
        }
        return subtotal - (subtotal * descuento);
    }
}
