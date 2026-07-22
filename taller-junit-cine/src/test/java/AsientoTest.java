import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class AsientoTest {
Asiento asiento;

@BeforeEach
void setUp() {
// Se ejecuta antes de cada test para garantizar la independencia y repetibilidad
asiento = new Asiento("A1", "VIP");
}

@Test
@DisplayName("CP-01: Constructor crea un asiento válido correctamente")
void constructorValido() {
assertFalse(asiento.isOcupado(), "El asiento no debería estar ocupado al crearse");
assertEquals("A1", asiento.getCodigo(), "El código del asiento debe ser A1");
assertEquals("VIP", asiento.getTipo(), "El tipo de asiento debe ser VIP");
}

@Test
@DisplayName("CP-02: Constructor lanza excepción si el código está vacío")
void constructorLanzaExcepcionCodigoVacio() {
Exception exception = assertThrows(IllegalArgumentException.class, () -> {
new Asiento(" ", "ESTANDAR");
});
assertTrue(exception.getMessage().contains("no puede estar vacío"));
}

@Test
@DisplayName("CP-03: Constructor lanza excepción si el tipo es inválido")
void constructorLanzaExcepcionTipoInvalido() {
Exception exception = assertThrows(IllegalArgumentException.class, () -> {
new Asiento("B2", "PREMIUM");
});
assertTrue(exception.getMessage().contains("Tipo de asiento inválido"));
}

@Test
@DisplayName("CP-04: calcularPrecioBase retorna 5.0 para asiento ESTANDAR")
void calcularPrecioBaseEstandar() {
Asiento asientoEstandar = new Asiento("C1", "ESTANDAR");
assertEquals(5.0, asientoEstandar.calcularPrecioBase());
}

@Test
@DisplayName("CP-05: calcularPrecioBase retorna 12.0 para asiento 4D (Límite superior)")
void calcularPrecioBase4D() {
Asiento asiento4D = new Asiento("D1", "4D");
assertEquals(12.0, asiento4D.calcularPrecioBase());
}

@Test
@DisplayName("CP-06: ocupar() cambia el estado del asiento a ocupado")
void ocuparCambiaEstado() {
asiento.ocupar();
assertTrue(asiento.isOcupado(), "El asiento debería estar ocupado después de llamar a ocupar()");
}

@Test
@DisplayName("CP-07: ocupar() lanza excepción si el asiento ya está ocupado")
void ocuparLanzaExcepcionSiYaOcupado() {
asiento.ocupar(); // Se ocupa por primera vez

Exception exception = assertThrows(IllegalStateException.class, () -> {
asiento.ocupar(); // Intento de ocuparlo de nuevo
});
assertTrue(exception.getMessage().contains("ya está ocupado"));
}
}