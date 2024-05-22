import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SoutezniParFrame extends JFrame{
    private JTable tabulka;
    private JTextField txtField;
    private JCheckBox checkUkonceno;
    private JPanel panelMain;
    private List<SoutezniPar> seznamParu = new ArrayList<>();
    private File selectedFile;

    public SoutezniParFrame(){
        setContentPane(panelMain);
        setTitle("Taneční soutěž Jarošová");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initMenu();

        seznamParu.add(new SoutezniPar("Jana Nováková a Šimon Vystrčil", 1, new BigDecimal("200"), LocalDate.of(2021, 5, 1), false));
        seznamParu.add(new SoutezniPar("Petr Novák a Tereza Kostková", 3, new BigDecimal("200"), LocalDate.of(2021, 5, 1), false));
        seznamParu.add(new SoutezniPar("Mgjagag Uhgaiugha a Nhfauigha IJoijhg", 6, new BigDecimal("200"), LocalDate.of(2021, 5, 1), false));

        renderTable();
        display();
    }
    public void initMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Soutěž");
        JMenuItem menuItem = new JMenuItem("Načti...");
        menuItem.addActionListener(e -> {
            chooseFile();
        });
        JMenuItem menuItem2 = new JMenuItem("Ukonči soutěž");
        menuItem2.addActionListener(e -> {
            ukonci();
        });
        JMenuItem menuItem3 = new JMenuItem("Zobraz statistiky");
        menuItem3.addActionListener(e -> {
            ukazStatistiky();
        });
        menu.add(menuItem);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }
    public void chooseFile(){
        JFileChooser fileChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            selectedFile = fileChooser.getSelectedFile();
            readFile(selectedFile);
            renderTable();
            display();
        }
    }
    public void readFile(File selectedFile){
        try(Scanner sc = new Scanner(new BufferedReader(new FileReader(selectedFile)))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] parts = line.split(":");
                int startovniCislo = Integer.parseInt(parts[0]);
                String jmeno = parts[1];
                BigDecimal startovne = new BigDecimal(parts[2]);
                LocalDate datumPrihlaseni = LocalDate.parse(parts[3]);
                boolean jeDivokaKarta = parts[4].equals("x");
                SoutezniPar soutezniPar = new SoutezniPar(jmeno, startovniCislo, startovne, datumPrihlaseni, jeDivokaKarta);
                seznamParu.add(soutezniPar);
        }
    } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Soubor nebyl nalezen", "Chyba", JOptionPane.ERROR_MESSAGE);
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Byl zadán špatný formát čísla", "Chyba", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void ukonci(){
        checkUkonceno.setSelected(true);
    }
    public void ukazStatistiky(){
        Soutez soutez = new Soutez("Jarošová", checkUkonceno.isSelected(), null, seznamParu);
        JOptionPane.showMessageDialog(this, "Startovné celkem: " + soutez.startovneCelkem() + "\nPočet divokých karet: " + soutez.pocetDivokychKaret());
    }

    public void display(){
        txtField.setText(String.valueOf(seznamParu.size()));
        checkUkonceno.setSelected(false);
    }
    public void renderTable(){
        tabulka.setModel(new TabulkaModel());
    }
    public class TabulkaModel extends AbstractTableModel{
        @Override
        public int getRowCount() {
            return seznamParu.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            SoutezniPar soutezniPar = seznamParu.get(rowIndex);
            switch (columnIndex){
                case 0:
                    return soutezniPar.getStartovniCislo();
                case 1:
                     return soutezniPar.getJmeno();
                case 2:
                    return soutezniPar.getStartovne();
                case 3:
                    return soutezniPar.getDatumPrihlaseni();
                case 4:
                    return soutezniPar.isJeDivokaKarta() ? "ano" : "ne";
            }
            return null;
        }
    }
    public static void main(String[] args) {
        SoutezniParFrame frame = new SoutezniParFrame();
        frame.setVisible(true);
    }
}
