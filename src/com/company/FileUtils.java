package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * <h1>Save and load</h1>
 * Codes for saving and loading files using generics
 */

public class FileUtils {

    /**
     *
     * @param o dynamic object
     * @param filename filename defined in controller upon method call
     * @param option amount of options and option defined in controller
     */
    public static void saveObject(Object o, String filename, StandardOpenOption... option) {
        Path path = Paths.get(filename);
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path, option))) {
            out.writeObject(o);
        } catch (Exception e) {
            System.out.println("Save failed, try again");
        }
    }

    /**
     *
     * @param filename filename defined in controller upon method call
     * @return
     */
    public static Object loadObject(String filename) {
        Path path = Paths.get(filename);
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
            return in.readObject();
        } catch (Exception e) {
            System.out.println("Load failed");
        }
        return null;
    }

}
