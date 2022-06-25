package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        System.out.println("Hello World without war");

        // метод в классе Point
        Point a1 = new Point(0, 3);
        Point a2 = new Point(2, 0);

        a2.distance(a1);

        System.out.println(a2.distance(a1));

        // функция в main
        System.out.println(distance(a1, a2));
    }
    //функция в main
    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));

    }

}