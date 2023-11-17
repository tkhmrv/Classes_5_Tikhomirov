// https://goo.su/z8Iurtc - геометрия задачи

import kotlin.math.pow
import kotlin.math.sqrt

// Класс, представляющий точку на плоскости
data class Point(val x: Double, val y: Double)

// Класс, представляющий треугольник
private class Triangle(val vertex1: Point, val vertex2: Point, val vertex3: Point) {
    // Функция для вычисления центра описанной окружности
    fun circleCenter(): Point {
        // Разности координат вершин треугольника (знаменатели уравнения)
        val a = vertex2.x - vertex1.x
        val b = vertex2.y - vertex1.y
        val c = vertex3.x - vertex1.x
        val d = vertex3.y - vertex1.y

        // Выражение для вычисления промежуточных значений
        val e = a * (vertex1.x + vertex2.x) + b * (vertex1.y + vertex2.y)
        val f = c * (vertex1.x + vertex3.x) + d * (vertex1.y + vertex3.y)

        // Знаменатель в выражении для центра описанной окружности
        val g = 2.0 * (a * (vertex3.y - vertex2.y) - b * (vertex3.x - vertex2.x))

        // Вычисление координат центра описанной окружности
        val centerX = (d * e - b * f) / g
        val centerY = (a * f - c * e) / g

        return Point(centerX, centerY)
    }

    // Функция для вычисления радиуса описанной окружности
    fun circleRadius(center: Point): Double {
        return sqrt((center.x - vertex1.x).pow(2) + (center.y - vertex1.y).pow(2))
    }
}

fun main() {
    // Ввод координат вершин треугольника с клавиатуры
    println("Введите координаты вершин треугольника поочерёдно:")

    val vertex1 = readPoint("Введите координаты вершины 1 (x y): ")
    val vertex2 = readPoint("Введите координаты вершины 2 (x y): ")
    val vertex3 = readPoint("Введите координаты вершины 3 (x y): ")

    // Создаем объект треугольника
    val triangle = Triangle(vertex1, vertex2, vertex3)

    // Вычисляем центр описанной окружности и ее радиус
    val circleCenter = triangle.circleCenter()
    val circleRadius = triangle.circleRadius(circleCenter)

    // Выводим результаты
    println("Центр описанной окружности: (${circleCenter.x.format(5)}, ${circleCenter.y.format(5)})")
    println("Радиус описанной окружности: ${circleRadius.format(5)}")
}

// Функция для чтения координат точки с клавиатуры
fun readPoint(prompt: String): Point {
    print(prompt)
    val input = readlnOrNull() ?: throw IllegalArgumentException("Ошибка ввода")
    val (x, y) = input.split(" ").map { it.toDouble() }
    return Point(x, y)
}

// Функция форматирования результата
fun Double.format(digits: Int) = "%.${digits}f".format(this)

