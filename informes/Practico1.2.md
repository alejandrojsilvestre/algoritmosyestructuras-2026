# Informe de requerimiento — Buscar un elemento en un vector desordenado

## Objetivo
Implementar un programa Java de consola **independiente** (no integrado al menú de juegos existente) que:
1. Pida al usuario la **cantidad de elementos** y un **rango de valores**.
2. Genere un vector de números enteros **no negativos** de forma **aleatoria** dentro de ese rango.
3. Muestre el **vector completo** en el orden en que fue generado (sin ordenar).
4. Explique al usuario que las posiciones se cuentan desde 0 (con un ejemplo).
5. Pida el **elemento a buscar**.
6. Muestre **todas las posiciones** (índices base 0) donde aparece el elemento, o un mensaje si no se encuentra.
7. Termine el programa tras **una sola búsqueda**.

## Entradas
El programa recibe por consola, en este orden:

| Campo | Reglas |
|---|---|
| **Cantidad de elementos** | Entero `>= 0`. `0` **termina el programa** (con despedida). Negativos y texto → error y se vuelve a pedir. **Sin límite máximo.** |
| **Límite inicial del rango** | Entero `>= 0`. Texto o negativo → error y se vuelve a pedir. |
| **Límite final del rango** | Entero `>= 0` y **estrictamente mayor** que el inicial. Texto, negativo, igual o menor al inicial → error y se vuelve a pedir. |
| **Elemento a buscar** | Entero **`>= 0`**. Texto o negativo → error y se vuelve a pedir. |

- Los valores del vector se generan aleatoriamente en el intervalo **inclusivo** `[inicial, final]`.
- El vector **no se ordena** en ningún momento.

## Salidas
- El **vector completo generado**, en una sola línea (ej.: `Vector generado: [12, 10, 17, 10, 19]`).
- Una **explicación del índice base 0** antes de pedir el elemento a buscar:
  `Las posiciones se cuentan desde 0. Ejemplo: en el vector [10, 20, 30], el valor 20 está en la posición 1.`
- El resultado de la búsqueda:
  - Si aparece **una vez**: `El elemento 7 se encuentra en la posición: 2`
  - Si aparece **varias veces**: `El elemento 7 se encuentra en las posiciones: 1, 3, 5` (separadas por coma y espacio).
  - Si **no aparece**: `El elemento 7 no se encuentra en el vector`
- **Mensajes de error** claros en español ante cada entrada inválida.
- Al ingresar `0` en la cantidad: despedida (`¡Hasta luego!`) y fin del programa.

## Restricciones y reglas
- Código en el **paquete `ejercicios`**, en `src/ejercicios/`.
- La clase ejecutable se llamará **`EjercicioBusquedaVector`** (contiene el `main`).
- **No** se integra al menú de `juegos`; es un programa con su propio `main`.
- **No** se requieren pruebas automatizadas (JUnit).
- Todo el texto debe estar en **español**.
- Los extremos del rango están **incluidos**; los rangos con inicial `==` final **no están permitidos**.
- La búsqueda es sobre el vector **desordenado** (no se ordena antes de buscar).
- El programa ejecuta **una sola búsqueda** y luego termina.

## Casos límite / casos de borde
- **Cantidad = 0** → mostrar despedida y terminar (no pedir rango, no generar vector ni pedir elemento).
- **Cantidad negativa** → error y volver a pedir la cantidad.
- **Cantidad = 1** → vector de un solo elemento.
- **Texto** en cualquier campo numérico → error y volver a pedir ese mismo campo.
- **Rango con inicial negativo o final negativo** → error y volver a pedir.
- **Rango con inicial == final** → error y volver a pedir (no permitido).
- **Rango con inicial > final** → error y volver a pedir.
- **Elemento a buscar negativo** → error y volver a pedir.
- **Elemento a buscar que no está en el vector** → mensaje de "no se encuentra".
- **Elemento repetido** → se muestran **todas** las posiciones donde aparece.
- **Elemento en la primera posición (índice 0)** → se muestra la posición 0.
- **Elemento en la última posición** → se muestra el último índice.
- **Cantidades muy grandes** → el programa debe soportarlas (sin límite máximo impuesto).

## Ejemplos

**Ejemplo 1 — caso normal con una aparición**
```
Ingrese la cantidad de elementos (0 para salir): 5
Ingrese el límite inicial del rango: 10
Ingrese el límite final del rango: 20
Vector generado: [12, 10, 17, 10, 19]
Las posiciones se cuentan desde 0. Ejemplo: en el vector [10, 20, 30], el valor 20 está en la posición 1.
Ingrese el elemento a buscar: 17
El elemento 17 se encuentra en la posición: 2
```
El programa finaliza.

**Ejemplo 2 — elemento repetido**
```
Ingrese la cantidad de elementos (0 para salir): 5
Ingrese el límite inicial del rango: 10
Ingrese el límite final del rango: 20
Vector generado: [12, 10, 17, 10, 19]
Las posiciones se cuentan desde 0. Ejemplo: en el vector [10, 20, 30], el valor 20 está en la posición 1.
Ingrese el elemento a buscar: 10
El elemento 10 se encuentra en las posiciones: 1, 3
```
El programa finaliza.

**Ejemplo 3 — elemento no encontrado**
```
Ingrese la cantidad de elementos (0 para salir): 3
Ingrese el límite inicial del rango: 1
Ingrese el límite final del rango: 5
Vector generado: [2, 4, 1]
Las posiciones se cuentan desde 0. Ejemplo: en el vector [10, 20, 30], el valor 20 está en la posición 1.
Ingrese el elemento a buscar: 9
El elemento 9 no se encuentra en el vector
```
El programa finaliza.

**Ejemplo 4 — terminar**
```
Ingrese la cantidad de elementos (0 para salir): 0
¡Hasta luego!
```
El programa finaliza.

## Criterios de aceptación
1. El programa es un `main` independiente en `src/ejercicios/EjercicioBusquedaVector.java` (paquete `ejercicios`).
2. Al ejecutarlo, pide cantidad y rango, genera el vector aleatorio dentro del rango inclusivo y lo muestra completo.
3. Antes de pedir el elemento, muestra la explicación de que las posiciones se cuentan desde 0 con el ejemplo indicado.
4. El elemento a buscar se valida como entero `>= 0`; texto y negativos muestran error y **vuelven a pedir** el dato.
5. El resultado muestra **todas** las posiciones (base 0) donde aparece el elemento, en el formato especificado.
6. Si el elemento no está, muestra `El elemento X no se encuentra en el vector`.
7. Las entradas inválidas (texto, negativos, rango con inicial ≥ final) muestran un error y **vuelven a pedir** el dato.
8. Ingresar `0` en la cantidad muestra la despedida y **termina** el programa.
9. El programa ejecuta **una sola búsqueda** y luego termina (no hay bucle de repetición).
10. El vector **no se ordena** en ningún momento.
11. No hay pruebas automatizadas ni integración con el menú de `juegos`.
