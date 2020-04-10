import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CombineTextFiles {

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(
                new FileOutputStream(
                        new File("D:\\combinedTextOutput.txt")),"utf-8"));
        String filePath = "D:\\inputFolderToCombine";
        List<String> fileList = new ArrayList<String>();
        fileList = getFiles(filePath, fileList);

        //List<String> extensions = getExtensions(fileList);
        // List text file extensions
        List<String> textExtensions = new ArrayList<>();
        Collections.addAll(textExtensions,"vue", "js", "html", "mina", "py");

        // Keep text files only and rule out binary
        var textFiles = getTextFiles(fileList, textExtensions);

        for (String file:textFiles) {
            BufferedReader reader = new BufferedReader( new InputStreamReader( new FileInputStream( new File(file)),"utf-8"));
            String s=null;
            while ((s=reader.readLine())!=null) {
                writer.append(s);
                writer.newLine();
            }
        }
        writer.close();
    }

//    private static List<String> getExtensions(List<String> fileList) {
//        List<String> extensions = new ArrayList<>();
//        String[] fileSplit = null;
//        for (String s : fileList) {
//            fileSplit = s.split("\\.");
//            var extension = fileSplit[fileSplit.length - 1];
//            if (!extensions.contains(extension))
//                extensions.add(extension);
//        }
//        return extensions;
//    }

    private static List<String> getTextFiles(List<String> fileList, List<String> textExtensions){
        List<String> textFiles = new ArrayList<>();
        String[] fileSplit = null;
        for (String s : fileList) {
            fileSplit = s.split("\\.");
            var extension = fileSplit[fileSplit.length - 1];
            if (textExtensions.contains(extension))
                textFiles.add(s);
        }
        return textFiles;
    }

    static List<String> getFiles(String filePath, List<String> fileList) {
        File root = new File( filePath );
        File[] files = root.listFiles();
        for ( File file : files )
        {
            if ( file.isDirectory() )
            {
                getFiles( file.getAbsolutePath(), fileList );
                //fileList.add( file.getAbsolutePath() );
            }else{
                fileList.add( file.getAbsolutePath() );
            }
        }
        return fileList;
    }
}
