import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Simulaator {
    private ArrayList<Täring> suvalised = new ArrayList<>();
    private ArrayList<Integer> viimaneSimulatsioon = new ArrayList<>();
    private char operatsioon;


    public Simulaator(ArrayList<Täring> suvalised, char operatsioon) {
        this.suvalised = suvalised;
        if(operatsioon != '+' && operatsioon != '*'){
            throw new RuntimeException("Vale sisend");
        }
        this.operatsioon = operatsioon;
    }

    public int maksimumTäringuVäärtus(){
        if(operatsioon == '+'){
            int out = 0;
            for(Täring i : suvalised){
                out += i.getMax();
            }
            return out;

        }else if(operatsioon == '*'){
            int out = 1;
            for(Täring i : suvalised){
                out *= i.getMax();
            }
        return out;
        }
    return 0;
    }

    public int miinimumTäringuVäärtus(){
        if(operatsioon == '+'){
            int out = 0;
            for(Täring i : suvalised){
                out += i.getMin();
            }
            return out;

        }else if(operatsioon == '*'){
            int out = 1;
            for(Täring i : suvalised){
                out *= i.getMin();
            }
        return out;
        }
        return 0;
    }

    public int suurimVäärtus(){
        return Collections.max(this.viimaneSimulatsioon);
    }

    public int väärtusePiirkonnaSuurus(){
//        System.out.println(maksimumTäringuVäärtus());
//        System.out.println(miinimumTäringuVäärtus());
        return maksimumTäringuVäärtus() - miinimumTäringuVäärtus() + 1;
    }

    public int liidaIteratsioon(){
        int out = 0;
        for(Täring i : suvalised){
            out += i.viska();
        }
        return out;
    }

    public int korrutaIteratsioon(){
        int out = 1;
        for(Täring i : suvalised){
            out *= i.viska();
        }
        return out;
    }

    public Integer[] simuleeri(int n){
        Integer[] välja = new Integer[väärtusePiirkonnaSuurus()];
        Arrays.fill(välja, 0);

        if(operatsioon == '*'){
            for (int i = 0; i < n; i++) {
                välja[korrutaIteratsioon() - miinimumTäringuVäärtus()] += 1;
            }
        }
        if(operatsioon == '+'){
            for (int i = 0; i < n; i++) {
                välja[liidaIteratsioon() - miinimumTäringuVäärtus()] += 1;
            }
        }
        viimaneSimulatsioon = new ArrayList<>(Arrays.asList(välja));
        return välja;
    }


    public ArrayList<Integer> getViimaneSimulatsioon() {
        if(viimaneSimulatsioon.isEmpty()){
            throw new RuntimeException("Simulatsiooni pole joosksutatud");
        }
        return viimaneSimulatsioon;
    }

    public char getOperatsioon() {
        return operatsioon;
    }


}
