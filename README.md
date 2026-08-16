# algoritmosyestructuras-2026

Sistema de consola en Java con dos juegos seleccionables desde un menú:

1. **El mayor y menor**: adivinar un número secreto entre 1 y 100 en un máximo de 5 intentos.
2. **Piedra, papel o tijera**: 5 rondas contra la máquina; gana quien acumule más victorias.

## Requisitos

- Java 17 o superior.
- La librería de JUnit 5 ya viene incluida en `lib/`, por lo que no es necesario instalar Maven.

## Ejecutar el programa en VS Code

Abre `src/juegos/Main.java` y pulsa **Run** (▶). El menú se mostrará en la terminal integrada.

## Compilar y ejecutar (sin Maven)

```bash
javac -d bin $(find src -name '*.java')
java -cp bin juegos.Main
```

## Ejecutar las pruebas (JUnit 5)

**Opción A — desde VS Code:** abre cualquier clase de `src/juegos` cuyo nombre termine en `Test` y pulsa el icono ▶ de cada prueba (o usa el panel *Testing*).

**Opción B — desde la terminal (sin Maven):**

```bash
javac -cp 'lib/*' -d bin $(find src -name '*.java')
java -jar lib/junit-platform-console-standalone-1.10.2.jar --class-path bin --scan-class-path
```

## Estructura

- `src/juegos/`: código fuente del sistema y pruebas automatizadas (JUnit 5).
- `lib/`: librerías de JUnit 5 (autocontenidas).
- `.vscode/settings.json`: configuración de librerías para VS Code.
