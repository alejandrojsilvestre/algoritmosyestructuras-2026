# Informe de requerimiento — Encontrar el mínimo de un vector

## Objetivo
Implementar un programa Java de consola **independiente** (no integrado al menú de juegos existente) que:
1. Pida al usuario la **cantidad de elementos** y un **rango de valores**.
2. Genere un vector de números enteros **no negativos** de forma **aleatoria** dentro de ese rango.
3. Muestre el **vector completo** (en el orden en que fue generado, **sin ordenar**).
4. Muestre el **elemento mínimo** del vector.
5. Al terminar, **vuelva a pedir todo** desde el principio, en bucle.

## Entradas
El programa recibe por consola, en este orden:

| Campo | Reglas |
|---|---|
| **Cantidad de elementos** | Entero. Debe ser `>= 0`. `0` **termina el programa**. Negativos y texto → mensaje de error y se vuelve a pedir. **Sin límite máximo.** |
| **Límite inicial del rango** | Entero `>= 0`. Texto o negativo → mensaje de error y se vuelve a pedir. |
| **Límite final del rango** | Entero `>= 0` y **estrictamente mayor** que el inicial. Texto, negativo, igual o menor al inicial → mensaje de error y se vuelve a pedir. |

- Los valores del vector se generan aleatoriamente en el intervalo **inclusivo** `[inicial, final]`.

## Salidas
- El **vector completo generado**, en una sola línea (por ejemplo: `Vector generado: [3, 5, 1, 8]`).
- El **valor mínimo** del vector (por ejemplo: `El mínimo es: 1`).
- **Mensajes de error** claros en español ante cada entrada inválida.
- Al ingresar `0` en la cantidad: un **mensaje de despedida** (`¡Hasta luego!`) y fin del programa.

## Restricciones y reglas
- Código en el **paquete `ejercicios`**, dentro de una carpeta nueva `src/ejercicios/`.
- La clase ejecutable se llamará **`EjercicioMinimoVector`** (contiene el `main`).
- **No** se integra al menú de `juegos`; es un programa con su propio `main`.
- **No** se requieren pruebas automatizadas (JUnit).
- Todo el texto debe estar en **español**.
- El vector **no se ordena**: se muestra tal como fue generado.
- Los extremos del rango están **incluidos**; los rangos con inicial `==` final **no están permitidos**.

## Casos límite / casos de borde
- **Cantidad = 0** → mostrar despedida y terminar (no pedir rango ni generar vector).
- **Cantidad negativa** → error y volver a pedir la cantidad.
- **Cantidad = 1** → vector de un solo elemento; el mínimo es ese mismo elemento.
- **Texto** en cualquier campo numérico → error y volver a pedir ese mismo campo.
- **Rango con inicial negativo o final negativo** → error y volver a pedir.
- **Rango con inicial == final** → error y volver a pedir (no permitido).
- **Rango con inicial > final** → error y volver a pedir.
- **Elementos repetidos** → el mínimo se determina normalmente (los repetidos no cambian el resultado).
- **Cantidades muy grandes** → el programa debe soportarlas (sin límite máximo impuesto).

## Ejemplos

**Ejemplo 1 — caso normal**
```
Ingrese la cantidad de elementos (0 para salir): 5
Ingrese el límite inicial del rango: 10
Ingrese el límite final del rango: 20
Vector generado: [12, 10, 17, 10, 19]
El mínimo es: 10
```
Luego vuelve a pedir la cantidad.

**Ejemplo 2 — terminar**
```
Ingrese la cantidad de elementos (0 para salir): 0
¡Hasta luego!
```
El programa finaliza.

**Ejemplo 3 — rango inválido (extremos iguales)**
```
Ingrese la cantidad de elementos (0 para salir): 3
Ingrese el límite inicial del rango: 7
Ingrese el límite final del rango: 7
Entrada inválida: el límite final debe ser mayor que el inicial.
```
Vuelve a pedir el rango.

## Criterios de aceptación
1. El programa es un `main` independiente en `src/ejercicios/EjercicioMinimoVector.java` (paquete `ejercicios`).
2. Al ejecutarlo, pide cantidad y rango, genera el vector aleatorio dentro del rango inclusivo y lo muestra completo.
3. El mínimo mostrado es correcto para **cualquier** vector generado (incluidos vectores con repeticiones y de un solo elemento).
4. Las entradas inválidas (texto, negativos, rango con inicial ≥ final) muestran un error y **vuelven a pedir** el dato.
5. Ingresar `0` en la cantidad muestra la despedida y **termina** el programa.
6. Al finalizar una iteración, el programa **vuelve a pedir todo** desde la cantidad.
7. El vector **no se ordena** en ningún momento.
8. No hay pruebas automatizadas ni integración con el menú de `juegos`.
