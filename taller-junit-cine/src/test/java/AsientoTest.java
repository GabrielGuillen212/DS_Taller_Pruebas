import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class AsientoTest {
    Asiento asiento;

    @BeforeEach
    void setUp(){
        asiento=new Asiento("A1","VIP");
    }

    @Test
    @DisplayName("Constructor crea asiento válido")
    void constructorValido(){
        assertFalse(asiento.isOcupado());
        assertEquals("A1",asiento.getCodigo());
    }

    // TODO: Traducir los demás casos de su tabla a métodos @Test.

    @Test
    @DisplayName("calculando precio base ESTANDAR")
    void CalPrecioESTANDAR(){
        asiento = new Asiento("A3", "ESTANDAR");
       assertEquals(5.0, asiento.calcularPrecioBase());
    }
    @Test
    @DisplayName("Calculando precio base VIP")
    void CalPrecioVIP(){
        asiento = new Asiento("A2", "VIP");
       assertEquals(8.5, asiento.calcularPrecioBase());
    }
    @Test
    @DisplayName("Calculando precio base 4D")
     void CalPrecio4D(){
        asiento = new Asiento("A4", "4D");
       assertEquals(12.0, asiento.calcularPrecioBase());
    }
    @Test
    @DisplayName("Calculando precio base DEFAULT")
     void CalPrecioDefault(){
        asiento = new Asiento("A4", "OTRO VALOR");
       assertEquals(0.0, asiento.calcularPrecioBase());
    }
    @Test
    @DisplayName("ERROR DE TIPO NULL")
     void CalPrecioNull(){
        asiento = new Asiento("A4", null);
       assertThrows(NullPointerException.class, () -> {
        asiento.calcularPrecioBase();
    });

    }
}