public class Joonistaja {
    Simulaator simulaator;
    int x_max, y_max;
    int x_suurus, y_suurus;
    double x_aspekt, y_aspekt;
    char[][] maatriks;


    public Joonistaja(Simulaator simulaator, int x, int y) {
        this.simulaator = simulaator;

        this.x_max = simulaator.väärtusePiirkonnaSuurus();
        this.y_max = simulaator.suurimVäärtus();

        this.x_suurus = x;
        this.y_suurus = y;

        this.x_aspekt = (double) x_suurus / x_max;
        this.y_aspekt = (double) y_suurus /y_max;

        this.maatriks = new char[x_suurus][y_suurus];
    }

    public Joonistaja(Simulaator simulaator) {
        this(simulaator, simulaator.väärtusePiirkonnaSuurus(), 24);
    }

    public Joonistaja(Simulaator simulaator, int y) {
        this(simulaator, simulaator.väärtusePiirkonnaSuurus(), y);
    }


    /**
     * Arvutab joonistatava graafiku, read milles pole väärtusi on n.
     */
    private void genereeriMaatriks(){
        if(simulaator.getViimaneSimulatsioon().isEmpty()){
            throw new RuntimeException("Simulatsiooni pole joosksutatud");
        }
        int min = simulaator.miinimumTäringuteVäärtus();
        for (int i = 0; i < x_suurus; i++) {
            int väärtus = simulaator.getViimaneSimulatsioon().get(i);
            int kõrgus = (int)(väärtus * y_aspekt);

            for (int j = 0; j < kõrgus; j++) {
                maatriks[i][j] = '█';
            }

            for (int j = kõrgus; j < y_suurus; j++) {
                if(j == 0 && väärtus == 0){
                    maatriks[i][j] = 'n';
                }else if(j == 0){
                    maatriks[i][j] = '▌';
                }else{
                    maatriks[i][j] = ' ';
//                    maatriks[i][j] = '□';
                }
            }
        }
    }

    /**
     * Joonistab graafiku.
     */
    public void joonista(){
        System.out.println();
        genereeriMaatriks();
        for (int i = 0; i < maatriks.length; i++) {
//            System.out.println(i);
            if(maatriks[i][0] == 'n'){
                continue;
            }
            System.out.printf("%2d|", i + simulaator.miinimumTäringuteVäärtus());
            for (int j = 0; j < maatriks[0].length; j++) {
                System.out.print(maatriks[i][j]);
            }
            System.out.println();
        }
        System.out.println();

    }

    @Override
    public String toString() {
        return "Joonistaja{" +
                "x_pikkus=" + x_suurus +
                ", y_pikkus=" + y_suurus +
                '}';
    }


}
