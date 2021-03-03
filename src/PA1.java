//Allows scanning of a directory to get all available txt files to read as input

import java.nio.file.*;
//Allows use of Scanner class to read txt files
import java.util.*;
//Allows opening of files for reading
import java.io.*;

public class PA1
{
    MyPolygons myPolygonsList = new MyPolygons();

    public static void main(String args[])
    {
        PA1 Assignment = new PA1();
        Assignment.run();
    }

    public void run()
    {
        String inputText = readFromTextFile();
        if (inputText == null)
        {
            System.out.println("Program terminated");
            return;
        }
        System.out.println("Text file loaded successfully. Generating Polygons...");
        while (inputText.compareTo("") != 0)
        {
            inputText = createPolygon(inputText);
        }


        while (true)
        {
            myPolygonsList.reset();
            Polygon temp = (Polygon)myPolygonsList.take();
            if (temp == null)
            {
                break;
            }
            System.out.println(temp.toString());
            System.out.println(temp.getLeastDistance());


        }
        System.out.println("Polygons generated! Sorting...");


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
        //Get the current path of the source directory
        Path dir = Paths.get(System.getProperty("user.dir"));
        //Start the menu with a prompting message
        String menuOutput = "What file do you wish to load from?\n";
        //The menu item the user picks will be stored here
        String userInput;
        //Keep track of all text file names in the source directory
        String[] files = new String[5];
        //Logical size of the array
        int numberOfFiles = 0;
        //As each file is added to the menu it will be temporarily stored here
        String tempFilename;

        Scanner console = new Scanner(System.in);

        //Start by finding all text files in the source directory by opening a DirectoryStream
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir))
        {
            //Loop through each file found in the DirectoryStream
            for (Path entry : stream)
            {
                //If the file name is a text file, it will end in the extension .txt
                if (String.valueOf(entry.getFileName()).endsWith(".txt"))
                {
                    //Save the file name and add it to the files array
                    tempFilename = String.valueOf(entry.getFileName());
                    files[numberOfFiles] = tempFilename;
                    /*
                    Add it to the menu by taking the substring of the first part of the name in order to remove the
                    .txt from the end
                     */
                    menuOutput += (numberOfFiles + 1) + ". " + tempFilename.substring(0, tempFilename.length() - 4) + "\n";
                    //Increase the logical size of the array
                    numberOfFiles++;
                    /*
                    If the logical size of the array is the same as the physical size of the array, increase the
                    physical size using the resizeStringArray method
                     */
                    if (numberOfFiles == files.length)
                    {
                        files = resizeStringArray(files);
                    }
                }
            }
        }
        catch (IOException e)
        {
            //If there is an error loading any of the files, just return which will initialise with an empty collection
            return null;
        }

        //If there are no text files in the source directory, just return which will initialise with an empty collection
        if (numberOfFiles == 0)
        {
            return null;
        }
        //Add an option to initialise with an empty collection
        numberOfFiles++;
        menuOutput += numberOfFiles + ". No file - Exit programme";

        //Present the menu to the user and ask them to select an option
        do
        {
            System.out.println(menuOutput);
            userInput = console.next();
        }
        //Loop until the user enters a valid integer that is an option on the menu
        while (!validMenuEntry(userInput, 1, numberOfFiles));

        //If the user selects no file load, simply return which will simply initialise with an empty collection
        if (Integer.parseInt(userInput) == numberOfFiles)
        {
            return null;
        }

        console.close();

        /*
        Save the filename by comparing the user input to the files array, and save this name in the global
        collectionName variable
         */
        String filename = files[Integer.parseInt(userInput) - 1];

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

    public String[] resizeStringArray(String[] oldArray)
    {
        /*
        This method takes a String array and adds 5 new spots in it
         */

        //Keep track of the length of the old array
        int length = oldArray.length;
        //Declare a new array with 5 extra spaces
        String[] newArray = new String[length + 5];
        //Loop through the old array, and add each item in the array into the new array
        for (int i = 0; i < length; i++)
        {
            newArray[i] = oldArray[i];
        }
        //Return the new array
        return newArray;
    }

    public boolean validMenuEntry(String userInput, int lowOption, int highOption)
    {
        /*
         This method takes three arguments. A user input, and then a low option and a high option. Because the menus
         in this programme are displayed numerically and the user is expected to type in a number, the low option is
         the smallest number available on the menu (usually 1), and the high option is the largest number on the menu,
         or the last option that a user could pick. This method first checks that the value the user entered is an
         integer using the isInteger method. After that it checks that it is within the acceptable range, that is
         between the low option and high option. Returns true if it is a valid entry and false if it is invalid.
         */

        if (isInteger(userInput) && (Integer.parseInt(userInput) >= lowOption) && (Integer.parseInt(userInput) <= highOption))
        {
            return true;
        }
        else
        {
            /*
            Either the user has entered something that is not an integer, or they have entered a number that does not
            exist on the menu (less than 1 or more than the number of items in the menu)
             */
            System.out.println("Please enter a valid menu item number");
            return false;
        }
    }

    public boolean isInteger(String temp)
    {
        /*
         This method takes a string that has been entered by the user, and attempts to parse it into an integer
         variable. If a NumberFormatException is thrown, the variable is not an integer and therefore the function
         returns false. If the parse is successful it returns true
         */

        try
        {
            // Declare a new integer and attempt to parse the argument "temp" into it
            Integer.parseInt(temp);
            // If the above line doesn't throw any errors, return true
            return true;
        }
        catch (NumberFormatException e)
        {
            // If a number format exception is thrown, return false. The argument "temp" is not an integer
            return false;
        }
    }
}
