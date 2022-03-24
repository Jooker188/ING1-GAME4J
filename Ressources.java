import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ressources {
    public static void Save(Serializable data, String fileName) throws Exception{
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))){
            oos.writeObject(data);
        }
    }
    public static Object Load(String fileName) throws Exception{
        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))){
            return ois.readObject();
        }
    }
}
