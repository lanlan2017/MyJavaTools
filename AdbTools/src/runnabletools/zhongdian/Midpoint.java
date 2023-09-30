package runnabletools.zhongdian;

import tools.copy.SystemClipboard;

import java.util.Scanner;

/**
 * 求两个点的中点
 *
 * Midpoint
 */
public class Midpoint {
    public static void main(String[] args) {

        // String input = "bounds=\"[510,1735][570,1776]\"";
        System.out.print("坐标字符串:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        input = input.substring(input.indexOf("[") + 1, input.lastIndexOf("]"));
        System.out.println("input = " + input);

        String upperLeft = input.substring(0, input.indexOf("]["));
        String lowerRight = input.substring(input.indexOf("][") + 2);

        System.out.println("upperLeft = " + upperLeft);
        System.out.println("lowerRight = " + lowerRight);

        Point p1 = getPoint(upperLeft);
        System.out.println("point = " + p1);
        Point p2 = getPoint(lowerRight);
        System.out.println("point = " + p2);


        // String[] split = input.split("\\]\\[");

        // Point point1 = new Point(96, 1609);
        // Point point2 = new Point(984, 1734);

        // Point point1 = new Point(510, 1735);
        // Point point2 = new Point(570, 1776);
        // bounds="[96,1609][984,1734]
        // Point midpoint = calculateMidpoint(point1, point2);
        Point midpoint = calculateMidpoint(p1, p2);

        // System.out.println("The midpoint is at (" + midpoint.x + ", " + midpoint.y + ")");
        String x = "adb shell input tap " + midpoint.x + " " + midpoint.y;
        System.out.println(x);
        SystemClipboard.setSysClipboardText(x);
    }

    private static Point getPoint(String upperLeft) {
        String xUL = upperLeft.substring(0, +upperLeft.indexOf(","));
        String yUL = upperLeft.substring(upperLeft.indexOf(",") + 1);
        System.out.println("xUL = " + xUL);
        System.out.println("yUL = " + yUL);


        double xULD = Double.parseDouble(xUL);
        double yULD = Double.parseDouble(yUL);

        Point point = new Point(xULD, yULD);
        return point;
    }

    public static Point calculateMidpoint(Point point1, Point point2) {
        double midX = (point1.x + point2.x) / 2.0;
        double midY = (point1.y + point2.y) / 2.0;

        return new Point(midX, midY);
    }
}

class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}