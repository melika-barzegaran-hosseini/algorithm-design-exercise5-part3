package problem3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author melika barzegaran hosseini
 */
public class Problem3
{
    private String path;
    private boolean detailedPrinting;

    public Problem3(String path)
    {
        this.path = path;
        detailedPrinting = false;
    }

    public void enableDetailedPrinting()
    {
        detailedPrinting = true;
    }

    public void disableDetailedPrinting()
    {
        detailedPrinting = false;
    }

    private Float[][] read(String path)
    {
        if(path == null || path.isEmpty())
        {
            System.err.println("error: the path provided is null or empty.");
            System.exit(1);
        }

        BufferedReader reader = null;
        Float[][] input = null;
        try
        {
            reader = new BufferedReader(new FileReader(path));

            String line;
            Integer n;
            if((line = reader.readLine()) != null && !line.isEmpty())
            {
                try
                {
                    n = Integer.parseInt(line);
                    if(n <= 0)
                    {
                        throw new NumberFormatException();
                    }
                }
                catch (NumberFormatException e)
                {
                    throw new Exception("error: the first line of the file '" + path + "' is not structured properly" +
                            ".\n it must represent the number of boxes.\n it must be a positive integer number with " +
                            "the max value of '" + Integer.MAX_VALUE + "'.");
                }
            }
            else
            {
                throw new Exception("error: the first line of the file '" + path + "' is null or empty.\n it must " +
                        "represent the number of boxes.");
            }

            if(detailedPrinting)
            {
                System.out.println("the number of boxes = " + n);
            }

            input = new Float[2][n];

            for(int i = 0; i < n; i++)
            {
                if((line = reader.readLine()) != null && !line.isEmpty())
                {
                    String[] tokens = line.trim().split("\\s+");
                    if(tokens.length == 2)
                    {
                        try
                        {
                            input[0][i] = Float.parseFloat(tokens[0]);
                            input[1][i] = Float.parseFloat(tokens[1]);

                            if(input[0][i] <= 0.0f || input[1][i] <= 0.0f)
                            {
                                throw new NumberFormatException();
                            }
                        }
                        catch (NumberFormatException e)
                        {
                            throw new Exception("error: the '" + (i + 2) + "'th line of the file '" + path + "' is " +
                                    "not structured properly.\n it must represent the width and height of a box .\n " +
                                    "the width and height must be positive float numbers with the min value of '" +
                                    Float.MIN_VALUE + "' and the max value of '" + Float.MAX_VALUE + "'.");
                        }
                    }
                    else
                    {
                        throw new Exception("error: the '" + (i + 2) + "'th line of the file '" + path + "' is not " +
                                "structured properly.\n it must represent the width and height of a box.");
                    }
                }
                else
                {
                    throw new Exception("error: the '" + (i + 2) + "'th line of the file '" + path + "' is null or " +
                            "empty.\n it must represent the width and height of a box.");
                }
            }

            if(detailedPrinting)
            {
                StringBuilder log = new StringBuilder("the width and heights of the boxes = ");
                for(int i = 0; i < input[0].length; i++)
                {
                    log.append("(").append(input[0][i]).append(",").append(input[1][i]).append("), ");
                }
                log.setLength(log.length() - 2);
                System.out.println(log);
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("error: the file '" + path + "' doesn't exist or is a directory.");
            System.exit(1);
        }
        catch (IOException e)
        {
            System.err.println("error: an error occurred while reading from the file '" + path + "'.");
            System.exit(1);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        finally
        {
            try
            {
                if(reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException e)
            {
                System.err.println("error: an error occurred while closing the file '" + path + "'.");
                System.exit(1);
            }
        }

        return input;
    }

    private Integer solve(Float[][] input)
    {
        return null;
    }

    public void solve()
    {
        Float[][] input = read(path);
        Integer output = solve(input);
        if(detailedPrinting)
        {
            System.out.println("the output = " + output);
        }
        else
        {
            System.out.print(output);
        }
    }

    public static void main(String args[])
    {
        if(args.length == 1)
        {
            Problem3 problem = new Problem3(args[0]);
            problem.enableDetailedPrinting();
            problem.solve();
        }
        else
        {
            System.err.println("error: the input is invalid. it should be the path of the input file.");
        }
    }
}