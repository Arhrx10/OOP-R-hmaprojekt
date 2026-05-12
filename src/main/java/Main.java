static void main(String[] args) {
    System.out.println("Tegemist on statistilise simulaatoriga.");
    System.out.println("Tuleb sisestada valem kus x on täring, mis võib olla suvaline arv 1-6");
    System.out.println("Näide \"(x+x)*2+7\", kusjuures x+x on erinev 2*x -st, kuna iga sümbol x-ist simuleerib eraldi täringu väärtust, 2*x aga ainult ühe.");
    System.out.println("Samuti ei ole võimalik jagamine.");



    Simulaator sim = new Simulaator("2*x+2*x");

    System.out.println(sim.getValem());
    sim.simuleeri(5000000);
    Joonistaja joonistaja = new Joonistaja(sim, 24);
    joonistaja.joonista();
    System.out.println("Keskväärtus: " + sim.getKeskväärtus());
    System.out.println("Dispersioon: " + sim.getDispersioon());
    System.out.println("Standardhälve: " + sim.getStandardhälve());




}