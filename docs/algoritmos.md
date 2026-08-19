# Notación Big O

## ¿Qué es la notación Big O?

La notación Big O describe **cómo crece el tiempo de ejecución (o la memoria) de un
algoritmo** a medida que crece el tamaño de la entrada, al que llamamos `n`.

- Nos interesa el **orden de crecimiento**: qué pasa cuando `n` se hace muy grande.
- Se ignoran las **constantes** y los términos menores. Por ejemplo, un algoritmo que
  hace `3n + 5` pasos se clasifica como **O(n)**.
- Big O describe, en general, el **peor caso** (aunque también se analiza el mejor caso
  y el caso promedio).

## ¿Cómo se calcula la complejidad?

Para calcular la notación Big O se siguen tres pasos:

1. **Contar operaciones:** se escribe cuántas operaciones elementales se ejecutan en
   función de `n`.
2. **Quedarse con el término dominante:** el término que crece más rápido cuando `n`
   crece.
3. **Descartar constantes y términos menores:** se elimina todo lo que no cambia el
   orden de crecimiento.

**Ejemplo:** un algoritmo que hace `3n² + 5n + 100` pasos se clasifica como **O(n²)**:

- Se descarta el `100` (constante): no depende de `n`.
- Se descarta el `5n` (término de menor orden): crece mucho más lento que `n²`.
- Se descarta el `3` (constante multiplicativa): no cambia la forma de la curva.

**¿Por qué se pueden descartar?** Porque para entradas grandes el término dominante
hace que el resto sea insignificante:

| `n` | `3n²` | `5n` | `100` | `(5n + 100) / 3n²` |
|---:|---:|---:|---:|---:|
| 10 | 300 | 50 | 100 | 50 % |
| 100 | 30.000 | 500 | 100 | 2 % |
| 1.000 | 3.000.000 | 5.000 | 100 | 0,17 % |
| 10.000 | 300.000.000 | 50.000 | 100 | 0,017 % |

A medida que `n` crece, `5n + 100` se vuelve irrelevante frente a `n²`; por eso solo
se conserva el término de mayor orden.

| Notación | Nombre | Ejemplo típico |
|---|---|---|
| O(1) | Constante | Acceder a una posición de un arreglo |
| O(log n) | Logarítmica | Búsqueda binaria |
| O(n) | Lineal | Recorrer una lista |
| O(n log n) | Lineal logarítmica | Ordenamientos eficientes (mergesort) |
| O(n²) | Cuadrática | Bucles anidados |
| O(2ⁿ) | Exponencial | Recursión con dos ramas (Fibonacci) |

---

## O(1) — Constante

El algoritmo tarda **lo mismo** sin importar el tamaño de la entrada. Es la complejidad
más deseable.

```java
// Acceder a una posición de un arreglo no depende de n: es O(1).
int obtenerPrimerElemento(int[] numeros) {
    return numeros[0];
}
```

**Cálculo:** se ejecuta una única operación (`numeros[0]`), sin importar el tamaño de
`n`. No hay ningún término que dependa de `n`, por eso se escribe **O(1)**.

**Qué se descarta:** si el algoritmo hiciera 1, 5 o 1.000 pasos fijos, seguiría siendo
**O(1)**, porque cualquier cantidad constante se descarta.

**Uso típico:** leer o escribir un elemento por índice, consultar una tabla hash
(en el caso promedio) o sumar dos números.

---

## O(log n) — Logarítmica

El trabajo crece **muy despacio**: cada paso reduce el problema a la mitad.
Con `n = 1.000.000`, solo se necesitan unos 20 pasos.

```java
// Búsqueda binaria: en cada iteración se descarta la mitad del arreglo.
// Solo funciona sobre arreglos ordenados. Complejidad: O(log n).
int busquedaBinaria(int[] arreglo, int objetivo) {
    int inicio = 0;
    int fin = arreglo.length - 1;
    while (inicio <= fin) {
        int medio = inicio + (fin - inicio) / 2;
        if (arreglo[medio] == objetivo) {
            return medio;
        } else if (arreglo[medio] < objetivo) {
            inicio = medio + 1;
        } else {
            fin = medio - 1;
        }
    }
    return -1;
}
```

**Cálculo:** en cada iteración el rango se parte a la mitad, así que el bucle se
ejecuta `log₂(n)` veces. La base del logaritmo se descarta porque cambiar de base
solo agrega una constante: `log₂(n) = log₁₀(n) / log₁₀(2)`.

**Qué se descarta:** la base del logaritmo (una constante) y cualquier trabajo fijo
que se haga dentro del bucle.

**Uso típico:** búsqueda binaria, operaciones sobre árboles balanceados y algunas
operaciones sobre montículos.

---

## O(n) — Lineal

El tiempo crece **proporcional** a `n`: si `n` se duplica, el trabajo también se duplica.

```java
// Recorre todos los elementos una sola vez: O(n).
int sumarElementos(int[] numeros) {
    int suma = 0;
    for (int i = 0; i < numeros.length; i++) {
        suma += numeros[i];
    }
    return suma;
}
```

**Cálculo:** el bucle se ejecuta `n` veces y cada iteración hace un trabajo constante
(O(1)). Total: `n × O(1) = O(n)`.

**Qué se descarta:** las constantes. Si el bucle hiciera `2n + 3` pasos (dos
operaciones por vuelta más una operación final), quedaría **O(n)** porque el `2` y el
`+3` no cambian el crecimiento lineal.

**Uso típico:** recorrer una lista o arreglo para buscar un elemento, calcular un total
o copiar datos.

---

## O(n log n) — Lineal logarítmica

Combina `n` pasadas con un trabajo logarítmico en cada nivel. Es la complejidad de los
**ordenamientos eficientes**.

```java
// Mergesort: divide el arreglo en mitades (log n niveles) y en cada nivel
// recorre todos los elementos (n). Complejidad total: O(n log n).
void mergesort(int[] arreglo, int[] auxiliar, int inicio, int fin) {
    if (fin - inicio < 2) {
        return;
    }
    int medio = inicio + (fin - inicio) / 2;
    mergesort(arreglo, auxiliar, inicio, medio);
    mergesort(arreglo, auxiliar, medio, fin);
    mezclar(arreglo, auxiliar, inicio, medio, fin);
}

void mezclar(int[] arreglo, int[] auxiliar, int inicio, int medio, int fin) {
    int i = inicio;
    int j = medio;
    for (int k = inicio; k < fin; k++) {
        if (i < medio && (j >= fin || arreglo[i] <= arreglo[j])) {
            auxiliar[k] = arreglo[i++];
        } else {
            auxiliar[k] = arreglo[j++];
        }
    }
    System.arraycopy(auxiliar, inicio, arreglo, inicio, fin - inicio);
}
```

**Cálculo:** el arreglo se divide en mitades hasta llegar a piezas de 1 elemento, lo
que da `log₂(n)` niveles. En cada nivel, `mezclar` recorre los `n` elementos. Total:
`n × log₂(n) = O(n log n)`.

**Qué se descarta:** la base del logaritmo y las constantes de `mezclar`. El factor
`n` multiplica al logaritmo, por eso **no** se descarta.

**Uso típico:** mergesort, quicksort (en el caso promedio) y heapsort.

---

## O(n²) — Cuadrática

El tiempo crece con el **cuadrado** de `n`: con 1.000 elementos se hacen alrededor de
1.000.000 de pasos. Suele aparecer con **bucles anidados**.

```java
// Dos bucles anidados recorren n * n pares: O(n²).
void imprimirPares(int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            System.out.println(i + ", " + j);
        }
    }
}
```

**Cálculo:** el bucle externo corre `n` veces y, por cada una, el bucle interno corre
otras `n` veces. Total: `n × n = n²`, es decir **O(n²)**.

**Qué se descarta:** los términos de menor orden. Si además hubiera un barrido lineal
(`n² + n`), el `n` se descarta porque, para `n` grande, `n²` domina totalmente.

**Uso típico:** ordenamientos simples como burbuja, selección e inserción (en su versión
básica) y comparar todos los pares de una colección.

---

## O(2ⁿ) — Exponencial

El trabajo **se duplica** con cada elemento adicional: crece tan rápido que deja de ser
práctico para valores medianos de `n`.

```java
// Fibonacci recursivo: cada llamada genera dos llamadas más, como un árbol.
// Complejidad: O(2ⁿ).
int fibonacci(int n) {
    if (n <= 1) {
        return n;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
}
```

**Cálculo:** cada llamada genera dos llamadas más, así que el número de llamadas se
duplica en cada nivel: `2⁰ + 2¹ + … + 2ⁿ` ≈ `2ⁿ`, es decir **O(2ⁿ)**. El total queda
dominado por el último nivel.

**Qué se descarta:** los niveles inferiores (su suma es menor que el último nivel) y
cualquier constante.

**Uso típico:** soluciones de fuerza bruta que exploran todas las combinaciones
(por ejemplo, ciertos problemas de mochila o de subconjuntos).

---

## Resumen

- **O(1)** es ideal; **O(log n)** y **O(n)** son muy buenas.
- **O(n log n)** es aceptable y típica de buenos ordenamientos.
- **O(n²)** y **O(2ⁿ)** hay que evitarlas cuando `n` es grande.

Al comparar algoritmos, elegimos el de **menor orden de crecimiento** para entradas
grandes.
