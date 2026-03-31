import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Simulaator {
    private ArrayList<Integer> viimaneSimulatsioon = new ArrayList<>(); // viimane jooksutatud simulatsioon
    private String valem; // viimane jooksutatud simulatsioon
    private ValemiLugeja lugeja;

    public Simulaator(String sisendVõrrand) {
        this.valem = sisendVõrrand;
        lugeja = new ValemiLugeja(sisendVõrrand);
    }

    /**
     * Tagastab maksimum väärtuse, mida see võõrand võimaldab.
     * @return max väärtus
     */
    public int maksimumTäringuteVäärtus(){
        return (int)lugeja.getMax();
    }

    /**
     * Tagastab minimum väärtuse, mida see võõrand võimaldab.
     * @return min väärtus
     */
    public int miinimumTäringuteVäärtus(){
       return (int)lugeja.getMin();
    }

    /**
     * Tagastab maksimum väärtuse viimases simulatsioonis.
     * @return suurim väärtus.
     */
    public int suurimVäärtus(){
        if(viimaneSimulatsioon.isEmpty()){
            throw new RuntimeException("Simulatsiooni pole joosksutatud");
        }
        return Collections.max(this.viimaneSimulatsioon);
    }

    /**
     * Tagastab mis on võimaliku väärtuste piirkonna vahemiku suurus.
     * @return vahemiku suurus
     */
    public int väärtusePiirkonnaSuurus(){
        return maksimumTäringuteVäärtus() - miinimumTäringuteVäärtus() + 1;
    }

    /**
     * Jooksutab n simulatsiooni ja lisab need viimaneSimulatsioon muutujasse.
     * @param n mitu korda jooksutada
     * @return tulemused
     */
    public Integer[] simuleeri(int n){
        Integer[] välja = new Integer[väärtusePiirkonnaSuurus()];
        Arrays.fill(välja, 0);

        for (int i = 0; i < n; i++) {
            välja[lugeja.simuleeri() - miinimumTäringuteVäärtus()] += 1;
        }

        viimaneSimulatsioon = new ArrayList<>(Arrays.asList(välja));
        return välja;
    }

    /**
     * Arvutab kindlad väärtused hetkese simulatsiooni kohta
     * @return double[], kus i=0 on keskväärtus, i=1 dispersioon, i=2 standardhälve
     */
    private double[] arvutaVäärtused(){
        if(viimaneSimulatsioon.isEmpty()){
            throw new RuntimeException("Simulatsiooni pole joosksutatud");
        }
        double EX=0,DX=0,standardhälve;
        ArrayList<Integer> list = this.viimaneSimulatsioon;
        double[] tõenäosused = new double[list.size()];
        int[] väärtused = new int[list.size()];
        int katseteSumma = 0;

        for(Integer n:list){ //arvutab kõigi katsete summa
            katseteSumma+=n;
        }

        for(int i=0;i<list.size();i++){
            tõenäosused[i]= (double) list.get(i)/katseteSumma; //arvutab iga väärtuse esinemise tõenäosuse
            väärtused[i]=this.miinimumTäringuteVäärtus()+i+1; //leiab iga vastava numbrilise väärtuse
            EX+=tõenäosused[i]*väärtused[i]; //arvutab keskväärtuse
        }

        for(int i=0;i<list.size();i++){
            DX+=(väärtused[i]-EX)*(väärtused[i]-EX)*tõenäosused[i]; //arvutab dispersiooni
        }

        standardhälve=Math.sqrt(DX); //arvutab standardhälbe

        double[] out = {EX, DX, standardhälve};

        return out;
    }


    public ArrayList<Integer> getViimaneSimulatsioon() {
        if(viimaneSimulatsioon.isEmpty()){
            throw new RuntimeException("Simulatsiooni pole joosksutatud");
        }
        return viimaneSimulatsioon;
    }

    public String getValem() {
        return valem;
    }

    public double getKeskväärtus(){
        return (double) Math.round(arvutaVäärtused()[0] * 100) /100;
    }

    public double getDispersioon(){
        return (double) Math.round(arvutaVäärtused()[1] * 100) /100;
    }

    public double getStandardhälve(){
        return (double) Math.round(arvutaVäärtused()[2] * 100) /100;
    }


}
