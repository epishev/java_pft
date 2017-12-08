package ru.stqa.pft.sandbox;


public class MyFirstProgram {
  public static void main(String[] args) {
    hello("world");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

         /* Реализация через функцию
   Point p1 = new Point(7,3);
   Point p2 = new Point(6,7);
   System.out.println("Растояние между двумя координатами = "+ distance(p1,p2));
    */

    Point p = new Point(8, 8);
    System.out.println("Растояние между двумя координатами = " + p.distance(4, 4));
  }

  public static void hello(String somebody) {
    System.out.println("Hello" + somebody);
  }

       /* Функция
  public static double distance(Point p1, Point p2) {
   return = Math.sqrt(Math.pow((p1.x-p2.x),2)+Math.pow((p1.y-p2.y),2));
  }
   */


}
