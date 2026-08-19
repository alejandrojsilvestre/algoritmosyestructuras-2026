# Sistema de juegos de consola

Programa de consola en Java con un menú que permite elegir entre dos juegos y una opción educativa:

1. **El mayor y menor**
2. **Piedra, papel o tijera**
3. **Notación Big O**

---

## Explicación funcional

### ¿Qué hace la solución?

Al ejecutar el programa aparece un **menú principal** con las siguientes opciones:

```
=== Menú principal ===
1. El mayor y menor
2. Piedra, papel o tijera
3. Notación Big O
4. Salir
Elija una opción:
```

El usuario elige una opción y, al terminar (una partida completa o la indicación de la opción educativa), **vuelve automáticamente al menú**. Todo el texto está en español y no se lleva ningún puntaje acumulado entre partidas.

### Funcionalidades

#### Juego 1 — "El mayor y menor"

- El programa elige un **número secreto al azar entre 1 y 100** (ambos inclusive).
- El usuario tiene **5 intentos** para adivinarlo.
- Después de cada intento válido, el programa responde:
  - **"El número secreto es mayor."** → el secreto es más grande que el ingresado.
  - **"El número secreto es menor."** → el secreto es más chico que el ingresado.
- Si acierta: muestra un mensaje de **victoria** y termina la partida.
- Si agota los 5 intentos sin acertar: muestra un mensaje de **derrota** y revela el número secreto.
- Las entradas inválidas (texto o números fuera de 1–100) muestran un error y **no descuentan** un intento.

**Ejemplo:**

```
=== El mayor y menor ===
Adivine el número secreto entre 1 y 100.
Tiene 5 intentos.
Intento 1 de 5 - Ingrese un número (1-100): 50
El número secreto es mayor.
Intento 2 de 5 - Ingrese un número (1-100): 75
El número secreto es menor.
Intento 3 de 5 - Ingrese un número (1-100): 62
¡Correcto! Adivinaste el número secreto: 62.
```

#### Juego 2 — "Piedra, papel o tijera"

- Se juega contra la **máquina**.
- La partida dura **exactamente 5 rondas**.
- En cada ronda el usuario elige con un número:
  - `1` = piedra, `2` = papel, `3` = tijera.
- La máquina elige su opción **al azar**.
- Cada ronda muestra **la elección de la máquina** y el resultado de esa ronda.
- Reglas clásicas: piedra vence a tijera, papel vence a piedra, tijera vence a papel; elecciones iguales son **empate**.
- El **empate cuenta como una ronda más**.
- Al final gana quien acumuló **más victorias**; si hay igual cantidad, la partida termina en **empate**.
- Las entradas inválidas (texto o números distintos de 1, 2, 3) muestran un error y **no cuentan** como ronda.

**Ejemplo:**

```
=== Piedra, papel o tijera ===
Jugaremos 5 rondas. Gana quien acumule más victorias.
Ronda 1 de 5 - Elija: 1 = piedra, 2 = papel, 3 = tijera: 2
Ronda 1: elegiste papel y la máquina eligió tijera.
Perdiste esta ronda.
...
Resultado final: 3 victorias tuyas contra 2 de la máquina.
¡Ganaste la partida!
```

#### Opción 3 — "Notación Big O"

- No ejecuta un juego: al elegirla, el programa muestra un único mensaje que indica dónde está la explicación y vuelve al menú.
- El contenido completo (qué es la notación Big O y ejemplos en código Java de O(1), O(log n), O(n), O(n log n), O(n²) y O(2ⁿ)) está en [`algoritmos.md`](algoritmos.md).

**Ejemplo:**

```
=== Menú principal ===
1. El mayor y menor
2. Piedra, papel o tijera
3. Notación Big O
4. Salir
Elija una opción: 3
La explicación de la notación Big O está en el archivo algoritmos.md.
```

### Casos de uso

| Caso | Resultado esperado |
|---|---|
| Elegir opción `3` en el menú | Muestra el mensaje que orienta a la documentación y vuelve al menú. |
| Elegir opción `4` en el menú | El programa se despide y termina. |
| Ingresar una opción inválida en el menú | Muestra error y vuelve a pedir la opción. |
| Adivinar el número en el primer o quinto intento | Mensaje de victoria. |
| No adivinar en 5 intentos | Mensaje de derrota y se muestra el número secreto. |
| Ingresar `1` o `100` en el Juego 1 | Se aceptan como intentos válidos (límites del rango). |
| Ingresar texto o un número fuera de rango | Error y el intento no se descuenta. |
| Jugar 5 rondas de piedra/papel/tijera | Se muestra la elección de la máquina y el resultado por ronda, y luego el ganador final. |
| Empatar las 5 rondas | Resultado final: empate. |

---

## Explicación técnica

### Estructura del código

Todos los archivos están en el paquete `juegos`, dentro de `src/juegos/`.

| Clase / interfaz | Tipo | Responsabilidad |
|---|---|---|
| `Juego` | interfaz | Contrato de todo juego: `getNombre()` y `jugar()`. |
| `Consola` | interfaz | Abstracción de entrada/salida: `leerLinea()`, `escribir()`, `escribirLinea()`. |
| `Main` | clase | Punto de entrada: arma la consola, el azar, los juegos y la opción educativa, y arranca el menú. |
| `ExplicacionBigO` | clase | Opción del menú que indica dónde está la explicación de la notación Big O. |
| `Menu` | clase | Menú en bucle: muestra opciones, valida la elección y ejecuta el juego elegido. |
| `JuegoMayorMenor` | clase | Lógica del juego de adivinanza. |
| `JuegoPiedraPapelTijera` | clase | Lógica del juego contra la máquina. |
| `Eleccion` | enum | Opciones del piedra/papel/tijera y regla de quién vence a quién. |
| `Pista` | enum | Resultado de comparar un intento: `MAYOR`, `MENOR`, `ACIERTO`. |
| `ResultadoRonda` | enum | Resultado de una ronda: `GANA_USUARIO`, `GANA_MAQUINA`, `EMPATE`. |
| `ConsolaSistema` | clase | `Consola` real que usa `System.in` y `System.out`. |
| `ConsolaFalsa` | clase (pruebas) | `Consola` simulada con entradas predefinidas y salidas capturadas. |
| `RandomFijo` | clase (pruebas) | Generador aleatorio determinista (valores en secuencia). |
| `JuegoEspia` | clase (pruebas) | `Juego` de prueba que registra si fue ejecutado. |
| `JuegoMayorMenorTest` | clase (pruebas) | Pruebas del juego de adivinanza. |
| `JuegoPiedraPapelTijeraTest` | clase (pruebas) | Pruebas del piedra/papel/tijera. |
| `MenuTest` | clase (pruebas) | Pruebas del menú. |

### Decisiones de diseño

- **Principio de responsabilidad única (SRP):** cada clase tiene una única responsabilidad (leer/ escribir, presentar el menú, aplicar la regla de un juego, etc.).
- **Abierto/cerrado (OCP):** `Menu` depende de la lista `List<Juego>`. Agregar una opción nueva solo requiere crear una clase nueva y añadirla a la lista en `Main`, sin modificar `Menu`.
- **Inversión de dependencias (DIP):** los juegos y el menú dependen de la abstracción `Consola` y reciben el `Random` por constructor. Esto permite probarlos con `ConsolaFalsa` y `RandomFijo` sin teclado ni azar real.
- **Polimorfismo en `Eleccion`:** cada constante del enum define con qué opción gana (`venceA`), evitando cadenas de `if`/`switch` para discriminar tipos.

### Estructuras de datos y algoritmos

- **`java.util.Random`** con `nextInt(bound)` para el azar:
  - Número secreto: `nextInt(100) + 1` → valor en `[1, 100]`.
  - Elección de la máquina: `Eleccion.values()[nextInt(3)]`.
- **`List` y `List.of(...)`** para la colección de juegos del menú.
- **`Deque` (`ArrayDeque`)** en `ConsolaFalsa` para devolver las entradas de prueba en orden.
- **Algoritmo del Juego 1:** búsqueda guiada por pistas. Tras cada intento, `evaluarIntento` compara el número ingresado con el secreto y devuelve `MAYOR`, `MENOR` o `ACIERTO`.
- **Algoritmo del Juego 2:** comparación directa de `Eleccion` por reglas fijas y conteo de victorias en dos acumuladores.
- **Validación de entradas:** `Integer.parseInt` dentro de `try/catch`, más verificación de rango, en bucles `while` que repiten hasta recibir un valor válido.

### Manejo de errores y excepciones

- **`NumberFormatException`** (entrada no numérica): se captura y se muestra un mensaje de error; el intento o la ronda no se consumen.
- **Validación de rango** sin excepción: si el número está fuera del rango permitido, se informa el error y se vuelve a pedir.
- **`IllegalArgumentException`** en `Menu`: si la lista de juegos está vacía, el menú no se puede construir.
- **`Objects.requireNonNull`** en los constructores: evita que los juegos o el menú se usen con dependencias `null`.

### Pruebas implementadas

Se escribieron **26 pruebas** con **JUnit 5**:

- `JuegoMayorMenorTest` (8 pruebas): regla de comparación (`MAYOR`, `MENOR`, `ACIERTO`), victoria, derrota con número secreto, entradas inválidas que no descuentan intentos y límites del rango (1 y 100).
- `JuegoPiedraPapelTijeraTest` (10 pruebas): reglas de quién vence a quién, empate, exactamente 5 rondas, empate que cuenta como ronda, entrada inválida que no consume ronda, mostrar la elección de la máquina y los tres resultados finales.
- `MenuTest` (5 pruebas): mostrar opciones y salir, ejecutar solo el juego elegido, rechazar opción inválida, volver al menú tras una partida y mostrar la opción Notación Big O.
- `ExplicacionBigOTest` (3 pruebas): nombre de la opción, mensaje que orienta a la documentación y rechazo de una consola nula.

**Cómo ejecutar las pruebas:**

```bash
javac -cp 'lib/*' -d bin $(find src -name '*.java')
java -jar lib/junit-platform-console-standalone-1.10.2.jar --class-path bin --scan-class-path
```

También se pueden ejecutar desde VS Code con el panel **Testing**.

---

## Observaciones y decisiones de diseño

- **Sin Maven:** en este equipo Maven no está instalado, por lo que se quitó `pom.xml` y la librería JUnit 5 se incluyó autocontenida en `lib/junit-platform-console-standalone-1.10.2.jar`.
- **Código y pruebas en el mismo paquete `juegos`:** VS Code (sin herramienta de compilación) usa la carpeta `src` como raíz de fuentes. Mantener todo en `src/juegos/` evita errores de paquete y permite que el editor reconozca el proyecto sin configuración adicional.
- **`.vscode/settings.json`** declara `lib/**/*.jar` como librería referenciada para que el editor resuelva JUnit.
- **Entradas inválidas:** por decisión de diseño, no se descuentan intentos ni rondas; solo se informa el error y se repite la solicitud.
