import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class SalaCineTest {
    SalaCine sala;

    @BeforeEach
    void setUp() {
        // Inicializamos una sala con capacidad de 50 antes de cada test
        sala = new SalaCine("Sala IMAX", 50);
    }

    @Test
    @DisplayName("CP-08: agregarAsiento añade un asiento correctamente a la lista")
    void agregarAsientoValido() {
        // Simulamos que la sala ya tiene 10 asientos agregados
        for (int i = 1; i <= 10; i++) {
            sala.agregarAsiento(new Asiento("A" + i, "ESTANDAR"));
        }
        
        // Agregamos el nuevo asiento C3
        Asiento nuevoAsiento = new Asiento("C3", "VIP");
        sala.agregarAsiento(nuevoAsiento);
        
        // Verificamos que el asiento C3 ahora existe en la sala
        Asiento asientoBuscado = sala.buscarAsiento("C3");
        assertEquals("C3", asientoBuscado.getCodigo(), "El código del asiento agregado debe ser C3");
        assertEquals(11, sala.contarDisponibles(), "Debería haber 11 asientos disponibles en total");
    }

    @Test
    @DisplayName("CP-09: agregarAsiento lanza excepción cuando la sala está llena (Límite)")
    void agregarAsientoLanzaExcepcionSalaLlena() {
        // Creamos una sala específica con capacidad máxima de 5 para este caso límite
        SalaCine salaPequeña = new SalaCine("Sala VIP", 5);
        
        // Llenamos la sala con sus 5 asientos
        salaPequeña.agregarAsiento(new Asiento("A1", "ESTANDAR"));
        salaPequeña.agregarAsiento(new Asiento("A2", "ESTANDAR"));
        salaPequeña.agregarAsiento(new Asiento("A3", "ESTANDAR"));
        salaPequeña.agregarAsiento(new Asiento("A4", "ESTANDAR"));
        salaPequeña.agregarAsiento(new Asiento("A5", "ESTANDAR"));

        // Intentamos agregar el asiento C4 que excede la capacidad
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            salaPequeña.agregarAsiento(new Asiento("C4", "VIP"));
        });
        
        assertTrue(exception.getMessage().contains("capacidad máxima"), 
            "El mensaje de error debe indicar que se alcanzó la capacidad máxima");
    }

    @Test
    @DisplayName("CP-10: agregarAsiento lanza excepción si ya existe un asiento con ese código")
    void agregarAsientoLanzaExcepcionDuplicado() {
        // Agregamos el asiento original A1
        sala.agregarAsiento(new Asiento("A1", "ESTANDAR"));
        
        // Intentamos agregar otro asiento con el mismo código A1
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sala.agregarAsiento(new Asiento("A1", "VIP"));
        });
        
        assertTrue(exception.getMessage().contains("Ya existe un asiento con el código A1"), 
            "El mensaje de error debe advertir sobre el código duplicado");
    }

    @Test
    @DisplayName("CP-11: buscarAsiento retorna el objeto correcto si el código existe")
    void buscarAsientoExistente() {
        Asiento asientoB5 = new Asiento("B5", "4D");
        sala.agregarAsiento(asientoB5);
        
        Asiento resultado = sala.buscarAsiento("B5");
        
        assertEquals(asientoB5, resultado, "El objeto retornado debe ser el mismo que se agregó");
        assertEquals("B5", resultado.getCodigo(), "El código del objeto encontrado debe ser B5");
    }

    @Test
    @DisplayName("CP-12: buscarAsiento lanza excepción si el código no existe")
    void buscarAsientoNoExistente() {
        // Intentamos buscar Z9 en una sala vacía
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            sala.buscarAsiento("Z9");
        });
        
        assertTrue(exception.getMessage().contains("Asiento no encontrado: Z9"), 
            "El mensaje de error debe indicar que el asiento Z9 no fue encontrado");
    }
}