import java.util.HashMap;
import java.util.Map;

public class ValemiLugeja {
    private final String sisendvalem;
    private int pos;
    private final Map<String, Täring> muutujad;
    Integer min, max;

    public ValemiLugeja(String valem, Map<String, Täring> muutujad) {
        this.sisendvalem = valem;
        this.pos = 0;
        this.muutujad = muutujad;
    }

    public ValemiLugeja(String valem) {
        this.sisendvalem = valem;
        this.pos = 0;
        Map<String, Täring> muutujadList = new HashMap<>();
        muutujadList.put("x", new Täring());
        muutujadList.put("y", new Täring(0, 1));

        this.muutujad = muutujadList;
    }

    /**
     * Simuleerib ühe korra valemit suvaliste väärtustega
     * @return tulemus
     */
    public int simuleeri() {
        pos = 0;
        return (int)loeLiitmineLahutamine(null);
    }

    /**
     * Miinimum väärtus mida valem saab omada
     * @return min väärtus
     */
    public int getMin() {
        if(min == null) {
            pos = 0;
            min = (int) loeLiitmineLahutamine(true);
        }
        return min;
    }

    /**
     * Maksimum väärtus mida valem saab omada
     * @return max väärtus
     */
    public int getMax() {
        if(max == null) {
            pos = 0;
            max = (int) loeLiitmineLahutamine(false);
        }
        return max;
    }

    /**
     * Itereerib läbi valemi kutsudes ennem välja loeKorrutamise meetodi ja itereerides hetkest positsiooni.
     * @param kasutaMin true - otsime miinimumväärtust, false - otsime maksimumväärtust, null - tavaline simulatsioon
     * @return tagastab valemi väärtuse
     */
    private double loeLiitmineLahutamine(Boolean kasutaMin) {
        double tulemus = loeKorrutamine(kasutaMin);
        while (pos < sisendvalem.length() && (sisendvalem.charAt(pos) == '+' || sisendvalem.charAt(pos) == '-')) {
            char op = sisendvalem.charAt(pos++);
            double parem = loeKorrutamine(kasutaMin);
            tulemus = (op == '+') ? tulemus + parem : tulemus - parem;
        }
        return tulemus;
    }

    /**
     * Itereerib läbi valemi kutsudes ennem välja loePeamised meetodi ja itereerides hetkest positsiooni.
     * @param kasutaMin true - otsime miinimumväärtust, false - otsime maksimumväärtust, null - tavaline simulatsioon
     * @return tagastab valemi väärtuse
     */
    private double loeKorrutamine(Boolean kasutaMin) {
        double tulemus = loePeamised(kasutaMin);
        while (pos < sisendvalem.length() && (sisendvalem.charAt(pos) == '*')) {
            char op = sisendvalem.charAt(pos++);
            double parem = loePeamised(kasutaMin);
            tulemus *=  parem;
        }
        return tulemus;
    }

    /**
     * Itereerib läbi valemi asendades väärtused.
     * @param kasutaMin true - otsime miinimumväärtust, false - otsime maksimumväärtust, null - tavaline simulatsioon
     * @return tagastab valemi väärtuse
     */
    private double loePeamised(Boolean kasutaMin) {
        char ch = sisendvalem.charAt(pos);

        if (ch == '(') {
            pos++;
            double result = loeLiitmineLahutamine(kasutaMin);
            pos++;
            return result;
        }

        if (ch == '-') {
            pos++;
            return -loePeamised(kasutaMin);
        }

        if (Character.isDigit(ch) || ch == '.') {
            int start = pos;
            while (pos < sisendvalem.length() && (Character.isDigit(sisendvalem.charAt(pos)) || sisendvalem.charAt(pos) == '.')) {
                pos++;
            }
            return Double.parseDouble(sisendvalem.substring(start, pos));
        }

        if (Character.isLetter(ch)) {
            int start = pos;
            while (pos < sisendvalem.length() && Character.isLetterOrDigit(sisendvalem.charAt(pos))) {
                pos++;
            }
            String varName = sisendvalem.substring(start, pos);
            if (!muutujad.containsKey(varName)) {
                throw new RuntimeException("Tundmatu muutuja: " + varName);
            }

            Täring t = muutujad.get(varName);
            if (kasutaMin == null) return t.viska();
            if (kasutaMin) return t.getMin();
            else return t.getMax();
        }

        throw new RuntimeException("Tundmatu sümbol: " + ch);
    }

    @Override
    public String toString() {
        return "ValemiLugeja{" +
                "sisendvalem='" + sisendvalem + '\'' +
                '}';
    }
}
