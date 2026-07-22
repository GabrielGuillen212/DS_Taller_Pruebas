# Taller: Automatización de Casos de Prueba con JUnit 5

**Modalidad:** mismos grupos de la clase anterior  
**Duración:** 1h30

## Contexto
En la clase anterior elaboraron una tabla de casos de prueba para el sistema de reservas de cine. En este taller usarán esa tabla como insumo para implementar pruebas unitarias automatizadas con JUnit 5.

## Actividades
1. Tome la tabla de casos de prueba elaborada previamente.
2. Cree una prueba `@Test` por cada fila de la tabla usando nombres descriptivos.
3. Utilice `@BeforeEach`, `@DisplayName`, `assertEquals`, `assertThrows`, `assertTrue`, `assertFalse` cuando corresponda.
4. Agrupe las pruebas en una clase por cada clase del sistema.
5. Ejecute las pruebas y corrija errores hasta que todas pasen.

## Se evaluará
- Cobertura de los casos de la tabla.
- Nombres descriptivos.
- Uso de al menos 4 tipos de aserciones.
- Pruebas que compilen y pasen (Corregir el código fuente de ser necesario).
- Independencia y repetibilidad.

## Entrega
- Url del último commit en Github
- Archivos:
    - `readme.md` con la tabla de los casos de prueba
    - `AsientoTest.java`
    - `SalaCineTest.java`
    - `ReservaServiceTest.java`
