package noobstangvb.hs.ics4u.tpj.storageArea.Lite;

import java.io.File;
import java.io.IOException;

public class FilePathTest {
	 
    public static void main(String[] args) throws IOException {
//        accessFileUsingAbsolutePath();
        accessFileUsingRelativePath();
    }
    
    private static void accessFileUsingAbsolutePath()
    {
        System.out.println("Access file using absolute path: ");
        String absolutePath = "D:\\sample-documents\\pdf-sample.pdf";
        File file = new File(absolutePath);
        printPaths(file);
    }
    
    private static void accessFileUsingRelativePath()
    {
        System.out.println("Access file relatively in different ways: ");
        // This goes back to the root drive of the current directory.
        System.out.println("Access file relative to the root drive of the current directory: ");
        File fileRelative = new File("/sample-documents/pdf-sample.pdf");
        printPaths(fileRelative);
 
        // This goes up 2 levels from the current directory
        System.out.println("Access file relative to the current directory: ");
        File fileRelative2 = new File("../../sample-documents/pdf-sample.pdf");
        printPaths(fileRelative2);
 
        System.out.println("Access file relative to the current directory: ");
        File fileRelative3 = new File("../.././sample-documents/pdf-sample.pdf");
        printPaths(fileRelative3);
    }
    
    private static void printPaths(File file)
    {
        try
        {
            System.out.println("File Path = " + file.getPath());
            System.out.println("Absolute Path = " + file.getAbsolutePath());
            System.out.println("Canonical Path = " + file.getCanonicalPath());
            System.out.println("\n");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
