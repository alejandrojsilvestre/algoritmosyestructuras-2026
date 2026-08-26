# Ejercicio — Calcular factorial con una función recursiva

Programa de consola **independiente** en Java (no integrado al menú de juegos) que
pide un número entero `n`, calcula su **factorial** mediante una **función recursiva**
usando `BigInteger` y muestra el resultado, repitiendo la operación en bucle hasta que
el usuario ingresa `-1` para salir.

- Paquete: `ejercicios`.
- Clase ejecutable: `EjercicioFactorial` (contiene el `main`).
- Pruebas automatizadas: `EjercicioFactorialTest` (JUnit 5).

---

## Explicación funcional

### ¿Qué hace la solución?

El programa pide al usuario un número entero `n` y calcula su **factorial**
(`n! = n × (n-1) × … × 1`), mostrando el resultado por pantalla.

Después de cada cálculo, **vuelve a pedir** otro número, en bucle. Para terminar, el
usuario ingresa `-1`, ante lo cual el programa muestra una despedida y finaliza.

Está pensado para practicar la **recursión**: el factorial se calcula con una función
que se llama a sí misma, en lugar de usar un bucle.

### ¿Para qué sirve?

- Practicar la implementación de **algoritmos recursivos** (caso base + paso recursivo).
- Mostrar el manejo de **números muy grandes**: como el factorial crece muy rápido, se
  usa `BigInteger` para que el resultado sea correcto incluso para entradas grandes
  (por ejemplo, `20!`, `50!` o `100!`), sin desbordamientos.
- Reforzar la **validación de entradas** por consola (texto y números negativos).

### Funcionalidades

| Funcionalidad | Detalle |
|---|---|
| Entrada del número | Se pide un entero; `-1` despide y finaliza. |
| Validación de texto | Una entrada no numérica muestra error y se vuelve a pedir. |
| Validación de negativos | Un negativo distinto de `-1` muestra error y se vuelve a pedir. |
| Cálculo del factorial | Recursivo, con `BigInteger` (sin desbordamiento). |
| Visualización | Muestra `El factorial de N es: R`. |
| Repetición | Tras mostrar el resultado, vuelve a pedir otro número. |

### Casos de uso

| Caso | Resultado esperado |
|---|---|
| Ingresar `-1` | Muestra `¡Hasta luego!` y termina (no calcula). |
| Ingresar `0` | `El factorial de 0 es: 1`. |
| Ingresar `1` | `El factorial de 1 es: 1`. |
| Ingresar `5` | `El factorial de 5 es: 120`. |
| Ingresar un negativo distinto de `-1` | Error y vuelve a pedir el número. |
| Ingresar texto | Error y vuelve a pedir el número. |
| Ingresar valores grandes (`20`, `50`, `100`) | Resultado correcto y exacto, sin desbordamiento. |
| Varios cálculos seguidos | Tras cada resultado, se vuelve a pedir otro número. |

### Ejemplos de uso

**Ejemplo 1 — caso normal**

```
Ingrese un número entero (-1 para salir): 5
El factorial de 5 es: 120
Ingrese un número entero (-1 para salir):
```

**Ejemplo 2 — factorial de 0**

```
Ingrese un número entero (-1 para salir): 0
El factorial de 0 es: 1
Ingrese un número entero (-1 para salir):
```

**Ejemplo 3 — entrada negativa (distinta de -1)**

```
Ingrese un número entero (-1 para salir): -3
Entrada inválida: debe ingresar un número mayor o igual a 0 (o -1 para salir).
Ingrese un número entero (-1 para salir):
```

**Ejemplo 4 — texto**

```
Ingrese un número entero (-1 para salir): hola
Entrada inválida: debe ingresar un número entero.
Ingrese un número entero (-1 para salir):
```

**Ejemplo 5 — salir**

```
Ingrese un número entero (-1 para salir): -1
¡Hasta luego!
```

---

## Explicación técnica

### Estructura del código

Dos archivos en el paquete `ejercicios`:

- `src/ejercicios/EjercicioFactorial.java` — el programa.
- `src/ejercicios/EjercicioFactorialTest.java` — pruebas JUnit 5.

| Elemento | Tipo | Responsabilidad |
|---|---|---|
| `VALOR_DE_SALIDA` | constante `int` | Centinela que termina el programa (`-1`). |
| `entrada` | campo `Scanner` | Fuente de lectura de las líneas ingresadas. |
| `EjercicioFactorial(Scanner)` | constructor | Crea el programa con la entrada dada; rechaza `null`. |
| `main(String[])` | método estático | Punto de entrada: arma la consola y ejecuta el bucle principal. |
| `ejecutar()` | método | Bucle principal: pide un número, calcula y muestra, y repite. |
| `leerNumero()` | método privado | Lee y valida el número (`>= 0`, o `-1` para salir). |
| `leerEntero(String)` | método privado | Lee un entero, reintentando ante entradas no numéricas. |
| `factorial(int)` | método privado | Calcula el factorial de forma **recursiva** con `BigInteger`. |
| `mostrarFactorial(int)` | método privado | Muestra el resultado con el formato requerido. |

### Estructuras de datos y algoritmos

- **Recursión** (`factorial`): se define con un **caso base** (`n <= 1` devuelve `1`,
  cubriendo `0! = 1` y `1! = 1`) y un **paso recursivo** (`n! = n × (n-1)!`). La
  complejidad es **O(n)** en tiempo y **O(n)** en memoria de pila (una llamada por
  cada valor entre `n` y el caso base).
- **`BigInteger`**: tipo de la librería estándar (`java.math`) que representa enteros
  de precisión arbitraria. Evita el desbordamiento que tendría `int` (correcto solo
  hasta `12!`) o `long` (correcto solo hasta `20!`). En el paso recursivo se convierte
  `n` con `BigInteger.valueOf(n)` y se multiplica con `multiply(...)`.
- **Validación de entradas**: `Integer.parseInt` dentro de `try/catch`
  (`NumberFormatException`) y verificación de rango, en bucles `while` que repiten
  hasta recibir un valor válido.

### Manejo de errores y excepciones

- **`NumberFormatException`** (entrada no numérica): se captura, se muestra
  `Entrada inválida: debe ingresar un número entero.` y se vuelve a pedir el campo.
- **Validación de negativos sin excepción**: un negativo distinto de `-1` se rechaza
  con un mensaje claro y se repite la solicitud.
- **`IllegalArgumentException`** en `factorial`: guarda defensiva si se llamara con
  `n < 0`; en la práctica no ocurre porque `leerNumero()` ya filtra los negativos.
- **`Objects.requireNonNull`** en el constructor: evita instanciar el programa con un
  `Scanner` nulo (lanza `NullPointerException`).

### Pruebas o casos de uso implementados

`EjercicioFactorialTest` cubre 9 casos con JUnit 5, capturando la salida estándar
(`System.out`) para verificar el comportamiento completo:

- Factoriales correctos: `0!`, `1!`, `5!` y un valor grande (`20!`) sin desbordamiento.
- Repetición en bucle con varias entradas válidas.
- Entrada negativa distinta de `-1` y entrada no numérica: error y reintento.
- Salida con `-1`: despedida y fin sin calcular.
- Constructor con `null`: lanza `NullPointerException`.

**Cómo compilar y ejecutar:**

```bash
# Compilar (incluye las pruebas)
javac -cp 'lib/*' -d bin $(find src -name '*.java')

# Ejecutar el programa
java -cp bin ejercicios.EjercicioFactorial

# Ejecutar las pruebas
java -jar lib/junit-platform-console-standalone-1.10.2.jar \
  --class-path bin --select-class ejercicios.EjercicioFactorialTest
```

---

## Observaciones y decisiones de diseño

- **Programa independiente**: tiene su propio `main` y no depende del paquete `juegos`
  ni del menú existente; vive en `src/ejercicios/`.
- **Principio de responsabilidad única (SRP)**: cada método privado tiene una única
  responsabilidad (leer un dato, validar, calcular, mostrar).
- **DRY**: la lectura de un entero con reintento ante texto está centralizada en
  `leerEntero`; `leerNumero` solo agrega la validación de negativos.
- **Inyección de dependencias**: `Scanner` se recibe por constructor, lo que desacopla
  la lógica de la fuente real y permite probarla con entradas simuladas.
- **`System.out.flush()`** antes de leer: garantiza que el mensaje de solicitud se vea
  en pantalla antes de bloquearse esperando la entrada.
- **Centinela `-1`**: se usa `-1` (y no `0`) porque `0! = 1` es un cálculo válido.
- **`BigInteger` por requerimiento**: asegura exactitud en factoriales grandes, a
  diferencia de `int`/`long`.
- **Recursión por requerimiento**: el algoritmo es recursivo (no iterativo).

> **Observación para revisar (no corregida, por rol de documentación):** aunque
> `BigInteger` evita el desbordamiento aritmético, la recursión consume una entrada de
> pila por cada nivel. Para valores de `n` extremadamente grandes podría producirse un
> `StackOverflowError` antes de agotar la memoria de `BigInteger`. No se impuso un
> tope de entrada, así que conviene que el equipo evalúe si se documenta o limita ese
> extremo (por ejemplo, un máximo razonable de `n`).
