static void main(String[] args) {
    Täring t1 = new Täring();
    Täring t2 = new Täring();
    ArrayList<Täring> täringud = new ArrayList<>();
    täringud.add(t1);
    täringud.add(t2);

    Simulaator sim = new Simulaator(täringud, '+');
    System.out.println(Arrays.toString(sim.simuleeri(500000)));

    Joonistaja joonistaja = new Joonistaja(sim);
//    System.out.println(joonistaja);
    joonistaja.joonista();

}