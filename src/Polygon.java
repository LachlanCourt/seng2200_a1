public class Polygon
{
    private Point[] points;
    private int pointsSize;

    private int area;
    private double distance;

    public Polygon(String params)
    {
        interpretString(params);
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
        points = new Point[numberPoints];
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
            //System.out.println(params);
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
                // If params ends in a space, there's no more numbers to create
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
    }

    public String toString()
    {
        String stringPoly = "[";

        for (int i = 0; i < pointsSize; i++)
        {
            stringPoly += " " + points[i].toString();
        }

        stringPoly += " ]:    ";

        // AREA
        stringPoly += "AREA";

        return stringPoly;
    }

}
