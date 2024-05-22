import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Soutez {
    private String nazev;
    private boolean ukoncena;
    private SoutezniPar vitez;
    private List<SoutezniPar> seznamParu = new ArrayList<>();

    public Soutez(String nazev, boolean ukoncena, SoutezniPar vitez, List<SoutezniPar> seznamParu) {
        this.nazev = nazev;
        this.ukoncena = equals("ne");
        this.vitez = null;
        this.seznamParu = seznamParu;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public boolean isUkoncena() {
        return ukoncena;
    }

    public void setUkoncena(boolean ukoncena) {
        this.ukoncena = ukoncena;
    }

    public SoutezniPar getVitez() {
        return vitez;
    }

    public void setVitez(SoutezniPar vitez) {
        this.vitez = vitez;
    }

    public List<SoutezniPar> getSeznamParu() {
        return seznamParu;
    }

    public void setSeznamParu(List<SoutezniPar> seznamParu) {
        this.seznamParu = seznamParu;
    }

    public BigDecimal startovneCelkem() {
        BigDecimal startovneCelkem = BigDecimal.ZERO;
        for (SoutezniPar soutezniPar : seznamParu) {
            startovneCelkem = startovneCelkem.add(soutezniPar.getStartovne());
        }
        return startovneCelkem;
    }
    public int pocetDivokychKaret() {
        int pocetDivokychKaret = 0;
        for (SoutezniPar soutezniPar : seznamParu) {
            if (soutezniPar.isJeDivokaKarta()) {
                pocetDivokychKaret++;
            }
        }
        return pocetDivokychKaret;
    }
}
