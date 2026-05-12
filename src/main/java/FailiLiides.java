import java.io.*;

public class FailiLiides {

    /**
     * Abimeetod et serialiseerida simulaator faili
     * @param failinimi failinimi
     * @param simulaator simulaator mida serialiseerida
     */
    public static void salvestaFaili(String failinimi, Simulaator simulaator){
        try (
            FileOutputStream file = new FileOutputStream(failinimi);
            ObjectOutputStream out = new ObjectOutputStream(file);
        ){
            out.writeObject(simulaator);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Abimeetod et deserialiseerida simulaator failist
     * @param failinimi failinimi
     * @return simulaator
     */
    public static Simulaator loeFailist(String failinimi){
        Simulaator simulaator;
        try (
            FileInputStream file = new FileInputStream(failinimi);
            ObjectInputStream in = new ObjectInputStream(file);)
        {
            simulaator = (Simulaator) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return simulaator;
    }
}
