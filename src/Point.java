public class Point
{
    private double x;
    private double y;

    public Point(double x_, double y_)
    {
        x = x_;
        y = y_;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x_)
    {
        x = x_;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y_)
    {
        y = y_;
    }

    public String toString()
    {
        return "(" + String.format("%3.2f", x) + " , " + String.format("%3.2f", y) + ")";
    }

    public int calcDistance()
    {
        return 0;
    }

}
