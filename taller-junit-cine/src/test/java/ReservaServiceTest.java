import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.ArrayList;

class ReservaServiceTest {
    SalaCine sala;
    ReservaService reservaService;

    @BeforeEach
    void setUp() {
        // Inicializamos la sala y el servicio antes de cada prueba
        sala = new SalaCine("Sala 1", 20);
        reservaService = new ReservaService(sala);

        // Agregamos 10 asientos ESTANDAR (precio base 5.0) para tener datos con qué probar
        for (int i = 1; i <= 10; i++) {
            sala.agregarAsiento(new Asiento("A" + i, "ESTANDAR"));
        }
    }

    @Test
    @DisplayName("CP-13: reservarAsientos sin descuento por ser 1 solo asiento")
    void reservarAsientosUnAsiento() {
        // 1 asiento a 5.0 = 5.0 total
        double total = reservaService.reservarAsientos(Arrays.asList("A1"));
        
        assertEquals(5.0, total, "El precio por 1 asiento ESTANDAR debe ser 5.0");
        assertTrue(sala.buscarAsiento("A1").isOcupado(), "El asiento A1 debe quedar marcado como ocupado");
    }

    @Test
    @DisplayName("CP-14: reservarAsientos aplica 15% de descuento a 4 asientos")
    void reservarAsientosCuatroAsientos() {
        // 4 asientos a 5.0 = 20.0. Descuento 15% (3.0) = 17.0
        double total = reservaService.reservarAsientos(Arrays.asList("A1", "A2", "A3", "A4"));
        
        assertEquals(17.0, total, "Debe aplicar un 15% de descuento al subtotal de 20.0");
    }

    @Test
    @DisplayName("CP-15: reservarAsientos al límite máximo permitido (6 asientos)")
    void reservarAsientosSeisAsientos() {
        // 6 asientos a 5.0 = 30.0. Descuento 15% (4.5) = 25.5
        double total = reservaService.reservarAsientos(Arrays.asList("A1", "A2", "A3", "A4", "A5", "A6"));
        
        assertEquals(25.5, total, "Debe procesar 6 asientos correctamente con su 15% de descuento");
    }

    @Test
    @DisplayName("CP-16: reservarAsientos lanza excepción si excede el límite de 6 por compra")
    void reservarAsientosExcedeLimite() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.reservarAsientos(Arrays.asList("A1", "A2", "A3", "A4", "A5", "A6", "A7"));
        });
        
        assertTrue(exception.getMessage().contains("No se pueden reservar más de 6"), 
            "Debe lanzar error indicando que se superó el límite");
    }

    @Test
    @DisplayName("CP-17: reservarAsientos lanza excepción con lista nula o vacía")
    void reservarAsientosListaVaciaONula() {
        // Probamos el caso nulo
        Exception exceptionNulo = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.reservarAsientos(null);
        });
        assertTrue(exceptionNulo.getMessage().contains("Debe indicar al menos un asiento"));

        // Probamos el caso de lista vacía
        Exception exceptionVacia = assertThrows(IllegalArgumentException.class, () -> {
            reservaService.reservarAsientos(new ArrayList<>());
        });
        assertTrue(exceptionVacia.getMessage().contains("Debe indicar al menos un asiento"));
    }
}