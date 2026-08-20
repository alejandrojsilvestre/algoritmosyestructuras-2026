# Ejercicio — Encontrar el mínimo de un vector

Programa de consola **independiente** en Java (no integrado al menú de juegos) que
genera un vector de números enteros **no negativos** de forma **aleatoria** dentro de un
rango indicado por el usuario y muestra su **elemento mínimo**.

- Paquete: `ejercicios`.
- Clase ejecutable: `EjercicioMinimoVector` (contiene el `main`).
- Sin pruebas automatizadas (JUnit).

---

## Explicación funcional

### ¿Qué hace la solución?

El programa pide al usuario tres datos, en este orden:

1. **Cantidad de elementos**: entero `>= 0`. Ingresar `0` **termina el programa**.
2. **Límite inicial del rango**: entero `>= 0`.
3. **Límite final del rango**: entero `>= 0` y **estrictamente mayor** que el inicial.

Con esos datos:

- Genera un vector con la cantidad indicada, llenando cada posición con un número
  aleatorio dentro del intervalo **inclusivo** `[inicial, final]`.
- Muestra el **vector completo** en el orden en que fue generado, **sin ordenarlo**.
- Muestra el **elemento mínimo** del vector.
- Al terminar, **vuelve a pedir todo** desde la cantidad, en bucle.

Está pensado para practicar el recorrido de un vector y el cálculo del mínimo sobre
elementos generados aleatoriamente, sin usar ordenamiento.

### Funcionalidades

| Funcionalidad | Detalle |
|---|---|
| Entrada de la cantidad | Se valida que sea un entero `>= 0`; `0` despide y finaliza. |
| Entrada del límite inicial | Se valida que sea un entero `>= 0`. |
| Entrada del límite final | Se valida que sea un entero `>= 0` y mayor que el inicial. |
| Generación del vector | Valores aleatorios en `[inicial, final]`, con ambos extremos incluidos. |
| Visualización | Muestra `Vector generado: [...]` en el orden original (sin ordenar). |
| Cálculo del mínimo | Muestra `El mínimo es: X` para cualquier vector (con o sin repetidos). |
| Repetición | Tras mostrar el mínimo, vuelve a pedir la cantidad. |

### Casos de uso

| Caso | Resultado esperado |
|---|---|
| Ingresar `0` como cantidad | Muestra `¡Hasta luego!` y termina (no pide rango ni genera vector). |
| Ingresar una cantidad negativa | Error y vuelve a pedir la cantidad. |
| Ingresar texto en cualquier campo numérico | Error y vuelve a pedir ese mismo campo. |
| Ingresar un límite inicial negativo | Error y vuelve a pedir el límite inicial. |
| Ingresar un límite final negativo | Error y vuelve a pedir el límite final. |
| Ingresar un límite final igual o menor al inicial | Error y vuelve a pedir el límite final. |
| Cantidad `1` | Vector de un elemento; el mínimo es ese mismo elemento. |
| Elementos repetidos | El mínimo se calcula normalmente (los repetidos no cambian el resultado). |
| Cantidades muy grandes | Sin límite máximo impuesto; se genera y recorre el vector completo. |

### Ejemplos de uso

**Ejemplo 1 — caso normal**

```
Ingrese la cantidad de elementos (0 para salir): 5
Ingrese el límite inicial del rango: 10
Ingrese el límite final del rango: 20
Vector generado: [12, 10, 17, 10, 19]
El mínimo es: 10
Ingrese la cantidad de elementos (0 para salir):
```

**Ejemplo 2 — terminar**

```
Ingrese la cantidad de elementos (0 para salir): 0
¡Hasta luego!
```

**Ejemplo 3 — rango inválido (extremos iguales)**

```
Ingrese la cantidad de elementos (0 para salir): 3
Ingrese el límite inicial del rango: 7
Ingrese el límite final del rango: 7
Entrada inválida: el límite final debe ser mayor que el inicial.
Ingrese el límite final del rango:
```

---

## Explicación técnica

### Estructura del código

Una única clase en el paquete `ejercicios`: `src/ejercicios/EjercicioMinimoVector.java`.

| Elemento | Tipo | Responsabilidad |
|---|---|---|
| `VALOR_DE_SALIDA` | constante `int` | Valor de la cantidad que termina el programa (`0`). |
| `entrada` | campo `Scanner` | Fuente de lectura de las líneas ingresadas. |
| `aleatorio` | campo `Random` | Generador de números aleatorios del vector. |
| `EjercicioMinimoVector(Scanner)` | constructor | Crea el programa con la entrada dada y un `Random` nuevo. |
| `EjercicioMinimoVector(Scanner, Random)` | constructor | Crea el programa con entrada y generador aleatorio propios. |
| `main(String[])` | método estático | Punto de entrada: arma la consola y ejecuta el bucle principal. |
| `ejecutar()` | método | Bucle principal: pide datos, genera, muestra y repite. |
| `leerCantidadElementos()` | método privado | Lee y valida la cantidad (`>= 0`). |
| `leerLimiteInicial()` | método privado | Lee y valida el límite inicial (`>= 0`). |
| `leerLimiteFinal(int)` | método privado | Lee y valida el límite final (`>= 0` y `> inicial`). |
| `leerEntero(String)` | método privado | Lee un entero, reintentando ante entradas no numéricas. |
| `generarVector(int, int, int)` | método privado | Genera el vector aleatorio en el rango inclusivo. |
| `mostrarVector(int[])` | método privado | Muestra el vector completo sin ordenar. |
| `mostrarMinimo(int[])` | método privado | Muestra el elemento mínimo. |
| `buscarMinimo(int[])` | método privado | Recorre el vector y devuelve el mínimo. |

### Estructuras de datos y algoritmos

- **Vector (`int[]`)**: estructura de almacenamiento de los valores generados. El tamaño
  se fija una vez conocida la cantidad y no se redimensiona.
- **Generación aleatoria**: `Random.nextInt(ancho) + limiteInicial`, donde
  `ancho = limiteFinal - limiteInicial + 1`, produce un valor en `[inicial, final]`.
  Como `limiteFinal > limiteInicial` ya fue validado, `ancho` siempre es `>= 2`, por lo
  que `nextInt` nunca recibe un límite no positivo.
- **Búsqueda del mínimo**: recorrido lineal O(n). Se inicia con `minimo = vector[0]` y
  se compara cada elemento restante, actualizando el mínimo cuando aparece uno menor.
- **Validación de entradas**: `Integer.parseInt` dentro de `try/catch`
  (`NumberFormatException`) y verificación de rango, en bucles `while` que repiten hasta
  recibir un valor válido.

### Manejo de errores y excepciones

- **`NumberFormatException`** (entrada no numérica): se captura, se muestra
  `Entrada inválida: debe ingresar un número entero.` y se vuelve a pedir el campo.
- **Validación de rango sin excepción**: valores negativos, límite final igual o menor
  al inicial se rechazan con un mensaje claro y se repite la solicitud.
- **`IllegalStateException`** en `buscarMinimo`: guarda defensiva si el vector está
  vacío; en la práctica no ocurre porque `ejecutar()` termina cuando la cantidad es `0`.
- **`Objects.requireNonNull`** en los constructores: evita instanciar el programa con
  `Scanner` o `Random` nulos.

### Pruebas o casos de uso implementados

Por el informe de requerimiento **no** se requieren pruebas automatizadas. La
verificación se realiza ejecutando el programa y probando los casos de borde descritos
en la sección *Casos de uso* (salida con `0`, negativos, texto, rango igual, cantidad
`1` y repetición del bucle).

**Cómo compilar y ejecutar:**

```bash
javac -cp 'lib/*' -d bin $(find src -name '*.java')
java -cp bin ejercicios.EjercicioMinimoVector
```

---

## Observaciones y decisiones de diseño

- **Programa independiente**: tiene su propio `main` y no depende del paquete `juegos`
  ni del menú existente; vive en `src/ejercicios/`.
- **Principio de responsabilidad única (SRP)**: cada método privado tiene una única
  responsabilidad (leer un dato, validar, generar, mostrar, buscar el mínimo).
- **DRY**: la lectura de un entero con reintento ante texto está centralizada en
  `leerEntero`; los métodos de lectura solo agregan sus validaciones de rango.
- **Inyección de dependencias**: `Scanner` y `Random` se reciben por constructor, lo que
  desacopla la lógica de las fuentes reales y facilita probarla en el futuro.
- **`System.out.flush()`** antes de leer: garantiza que el mensaje de solicitud se vea
  en pantalla antes de bloquearse esperando la entrada, incluso si la salida usa búfer.
- **El vector no se ordena**: se muestra tal como fue generado, y el mínimo se obtiene
  por recorrido lineal, respetando el objetivo del ejercicio.
