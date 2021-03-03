public class Polygon implements ComparePoly
{
    private Point[] points;
    private int pointsSize;

    private double area;
    private double leastDistance;

    public Polygon(String params)
    {
        interpretString(params);
        calcArea();
        calcLeastDistance();
    }

    private void interpretString(String params)
    {
        // Remove P and the following space
        params = params.substring(1);
        if (params.charAt(0) == ' ')
        {
            params = params.substring(1);
        }
        // Get number of points
        String temp = "";
        int paramsLength = params.length();
        for (int i = 0; i < paramsLength; i++)
        {
            if (params.charAt(0) != ' ')
            {
                temp += params.charAt(0);
                params = params.substring(1);
            }
        }
        int numberPoints = Integer.valueOf(temp);
        // Create an extra space at the end to store a duplicate of the first point for area calculation
        points = new Point[numberPoints + 1];
        pointsSize = 0;
        // Remove leading space and then calculate the points
        params = params.substring(1);
        params += " ";

        double a = 0;
        double b = 0;
        temp = "";
        boolean createPoint = false;
        Point newPoint;

        paramsLength = params.length();
        for (int i = 0; i < paramsLength; i++)
        {
            if (params.charAt(0) == ' ')
            {
                while ((params.length() != 0) && (params.charAt(0) == ' '))
                {
                    params = params.substring(1);
                }

                if (createPoint)
                {
                    b = Double.valueOf(temp);
                    newPoint = new Point(a, b);
                    points[pointsSize] = newPoint;
                    pointsSize++;
                    a = 0;
                    b = 0;
                    temp = "";
                    createPoint = false;
                }
                else
                {
                    a = Double.valueOf(temp);
                    temp = "";
                    createPoint = true;
                }
                // If params is empty, there's no more numbers to create
                if (params.length() == 0)
                {
                    break;
                }
            }
            else
            {
                temp += params.charAt(0);
                params = params.substring(1);
            }
        }

        // Add last point into the array as a duplicate of the first item
        newPoint = new Point(points[0].getX(), points[0].getY());
        points[pointsSize] = newPoint;
        pointsSize++;
    }

    private void calcArea()
    {
        area = 0;
        for (int i = 0; i < pointsSize - 1; i++)
        {
            area += (points[i].getX() + points[i + 1].getX()) * (points[i + 1].getY() - points[i].getY());
        }
        if (area < 0)
        {
            area *= -1;
        }
        area /= 2;


    }

    private void calcLeastDistance()
    {
        leastDistance = points[0].calcDistance();
        for (int i = 0; i < pointsSize - 1; i++)
        {
            if (points[i].calcDistance() < leastDistance)
            {
                leastDistance = points[i].calcDistance();
            }
        }
    }

    public String toString()
    {
        String stringPoly = "[";

        // The last point in the array is the same as the first point, so do not print it out
        for (int i = 0; i < pointsSize - 1; i++)
        {
            stringPoly += " " + points[i].toString();
        }

        stringPoly += " ]:    ";

        // AREA
        stringPoly += String.format("%5.2f", area);

        return stringPoly;
    }

    public double getLeastDistance()
    {
        return leastDistance;
    }

    public double getArea()
    {
        return area;
    }

    public boolean ComesBefore(Object o)
    {
        Polygon other = (Polygon)o;
        double differenceMargin;
        double areaTest;
        if (area - other.getArea() < 0)
        {
            differenceMargin = 0.05 * area;
        }
        else
        {
            differenceMargin = 0.05 * other.getArea();
        }
        areaTest = area - other.getArea();
        if (areaTest < 0)
        {
            areaTest *= -1;
        }
        // Use the calculated distance from the origin
        if (areaTest < differenceMargin)
        {
            if (leastDistance < other.getLeastDistance())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        if (area < other.getArea())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
