public class TestClass
{
    public static void main(String args[])
    {
        MyPolygons testList = new MyPolygons();
        for (int i = 0; i < 10; i++)
        {
            testList.append(i);
        }

        testList.step();
        testList.step();
        testList.step();
        testList.step();
        testList.insert("There's a 3 above me");

        System.out.println("Data added!");

        for (int i = 0; i < 12; i++)
        {
            System.out.println(testList.take());
        }
        System.out.println("Output complete!");

        for (int i = 0; i < 10; i++)
        {
            testList.prepend(i);
        }

        for (int i=0;i<27;i++)
        {
            testList.step();
        }
        testList.insert("Not sure where I am but hopefully I didn't cause any errors");
        for (int i=0;i<38;i++)
        {
            testList.step();
        }
        testList.insert(":)");

        System.out.println("Data added!");

        for (int i = 0; i < 13; i++)
        {
            System.out.println(testList.take());
        }
        System.out.println("Output complete!");

    }
}
