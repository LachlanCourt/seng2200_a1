//Allows scanning of a directory to get all available txt files to read as input

//Allows use of Scanner class to read txt files
import java.util.*;
//Allows opening of files for reading
import java.io.*;

public class PA1
{
    private MyPolygons myPolygonsList = new MyPolygons();
    private MyPolygons sortedPolygons = new MyPolygons();
    static String filename;

    public static void main(String args[])
    {
        try
        {
            filename = args[0];
            PA1 assignment = new PA1();
            assignment.run();
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("No file specified at program load. Terminating...");
        }

    }

    public void run()
    {

        String inputText = readFromTextFile();
        if (inputText == null)
        {
            System.out.println("Error loading text file. Check file name and try again. Terminating...");
            return;
        }

        System.out.println("Text file loaded successfully. Generating Polygons...");
        while (inputText.compareTo("") != 0)
        {
            inputText = createPolygon(inputText);
        }

        System.out.println("Unsorted list:");
        myPolygonsList.currentToHead();
        int listSize = myPolygonsList.getSize();
        for (int i = 0; i < listSize; i++)
        {
            Polygon temp = myPolygonsList.removeFromHead();
            System.out.println(temp.toString());
            myPolygonsList.append(temp);
            sortedPolygons.insertInOrder(temp);
        }
        System.out.println("Sorted List:");


        sortedPolygons.currentToHead();
        listSize = sortedPolygons.getSize();
        for (int i = 0; i < listSize; i++)
        {
            Polygon temp = sortedPolygons.removeFromHead();
            System.out.println(temp.toString());
            sortedPolygons.append(temp);
        }
        System.out.println("Program complete!");

    }

    private String createPolygon(String text)
    {
        String polygonString = "";
        do
        {
            if (text.length() != 0)
            {
                polygonString += text.charAt(0);
                text = text.substring(1);
            }
            else
            {
                break;
            }
        }
        while (!text.toLowerCase().startsWith("p"));
        myPolygonsList.append(new Polygon(polygonString));

        return text;
    }

    private String readFromTextFile()
    {
        //Declare a Scanner to read the text file
        Scanner inputStream;
        try
        {
            //Try opening the Scanner with the filename selected
            inputStream = new Scanner(new File(filename));
        }
        catch (FileNotFoundException e)
        {
            //If there is an error opening the Scanner, return which will initiallise with an empty collection
            return null;
        }

        //Each line of text will be stored here as it is read so that it can be interpretted
        String textLine = "";

        //Loop while there is still information to read from the text file
        while (inputStream.hasNext())
        {
            //Read the text file line by line
            textLine += inputStream.nextLine();
        }
        //After the text file has been read, close the Scanner and show a success message to the user
        inputStream.close();
        return textLine;
    }
}
