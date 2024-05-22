import java.math.BigDecimal;
import java.time.LocalDate;

public class SoutezniPar {
    private String jmeno;
    private int startovniCislo;
    private BigDecimal startovne;
    private LocalDate datumPrihlaseni;
    private boolean jeDivokaKarta;

    public SoutezniPar(String jmeno, int startovniCislo, BigDecimal startovne, LocalDate datumPrihlaseni, boolean jeDivokaKarta) {
        this.jmeno = jmeno;
        this.startovniCislo = startovniCislo;
        this.startovne = startovne;
        this.datumPrihlaseni = datumPrihlaseni;
        this.jeDivokaKarta = jeDivokaKarta;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public int getStartovniCislo() {
        return startovniCislo;
    }

    public void setStartovniCislo(int startovniCislo) {
        this.startovniCislo = startovniCislo;
    }

    public BigDecimal getStartovne() {
        return startovne;
    }

    public void setStartovne(BigDecimal startovne) {
        this.startovne = startovne;
    }

    public LocalDate getDatumPrihlaseni() {
        return datumPrihlaseni;
    }

    public void setDatumPrihlaseni(LocalDate datumPrihlaseni) {
        this.datumPrihlaseni = datumPrihlaseni;
    }

    public boolean isJeDivokaKarta() {
        return jeDivokaKarta;
    }

    public void setJeDivokaKarta(boolean jeDivokaKarta) {
        this.jeDivokaKarta = jeDivokaKarta;
    }
}
