/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Efe
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
public class JsonFileWriter {
    public static void write(Course a) {
        String folderpath = System.getProperty("user.dir");
        String folderPath = folderpath + File.separator + "src" + File.separator + "jsons";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Course course = a;

            // Specify the directory where you want to save the new JSON file
            String outputDirectory = folderPath;

            // Create the output directory if it doesn't exist
            File outputDirFile = new File(outputDirectory);
            if (!outputDirFile.exists()) {
                outputDirFile.mkdirs(); // Creates the directory and its parent directories
            }

            // Construct the output file path
            String courseName = course.getCourseName()+".json";
            String outputFilePath = outputDirectory + File.separator + courseName;

            // Create a new File object for the output file
            File outputFile = new File(outputFilePath);

            // Write the Course object to the new JSON file
            objectMapper.writeValue(outputFile, course);

            System.out.println("JSON written to file successfully at: " + outputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


