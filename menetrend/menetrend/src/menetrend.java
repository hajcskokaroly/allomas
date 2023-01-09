import java.io.*;
import java.util.*;
    public class menetrend {
        public static void main(String[] args) throws IOException {
            System.out.println("Az 1. feladat");
            System.out.println("A fájl beolvasása...");
            BufferedReader beolvas = new BufferedReader(new FileReader("vonat.txt"));
            int[] von = new int[1000];
            int[] all = new int[1000];
            int[] ora = new int[1000];
            int[] perc = new int[1000];
            String[] ie = new String[1000];
            int db = 0;
            String sor;
            String[] ds;
            while ((sor = beolvas.readLine()) != null) {
                ds = sor.split("\t");
                db++;
                von[db - 1] = Integer.parseInt(ds[0]);
                all[db - 1] = Integer.parseInt(ds[1]);
                ora[db - 1] = Integer.parseInt(ds[2]);
                perc[db - 1] = Integer.parseInt(ds[3]);
                ie[db - 1] = ds[4];
            }
            beolvas.close();
            System.out.println("\nBeolvasás sikeres!");
            System.out.println("\nA 2. feladat ");
            int vondb = von[db - 1];
            int vegall = all[db - 1];
            System.out.println("Az állomások száma: " + (vegall + 1));
            System.out.println("A vonatok száma: " + vondb);
            System.out.println("\nA 3. feladat ");
            int akterk = -1;
            int aktvar = -1;
            int maxvar = -1;
            int maxvon = -1;
            int maxall = -1;
            int i, j, k;
            for (i = 1; i <= vondb; i++)
                for (j = 1; j <= vegall; j++)
                    for (k = 1; k <= db; k++) {
                        if (von[k - 1] == i && all[k - 1] == j && ie[k - 1].equals("E"))
                            akterk = 60 * ora[k - 1] + perc[k - 1];

                        if (von[k - 1] == i && all[k - 1] == j && ie[k - 1].equals("I")) {
                            aktvar = 60 * ora[k - 1] + perc[k - 1] - akterk;
                            if (aktvar > maxvar) {
                                maxvon = i;
                                maxall = j;
                                maxvar = aktvar;
                            }
                        }
                    }


            System.out.println("A(z) " + maxvon + ". vonat a(z) " + maxall + ". állomáson " + maxvar + " percet állt.");
            System.out.println("\nA 4. feladat ");
            Scanner sc1 = new Scanner(System.in);
            System.out.print("Adja meg egy vonat azonosítóját! ");
            int aktvon = sc1.nextInt();
            Scanner sc2 = new Scanner(System.in);
            System.out.print("Adjon meg egy időpontot (óra:perc)! ");
            String aktido = sc2.nextLine();
            int aktora = Integer.parseInt(aktido.split(":")[0]);
            int aktperc = Integer.parseInt(aktido.split(":")[1]);
            sc1.close();
            sc2.close();
            System.out.println("\nAz 5. feladat ");
            int menetido = 60 * 2 + 22;
            int aktind = 0;
            akterk = 0;
            int aktmenetido;
            for (i = 1; i <= db; i++) {
                if (von[i - 1] == aktvon && all[i - 1] == 0)
                    aktind = 60 * ora[i - 1] + perc[i - 1];
                if (von[i - 1] == aktvon && all[i - 1] == vegall)
                    akterk = 60 * ora[i - 1] + perc[i - 1];

            }
            aktmenetido = akterk - aktind;
            if (menetido > 0) {
                if (aktmenetido < menetido)
                    System.out.println("A(z) " + aktvon + ". vonat útja " + (menetido - aktmenetido) + " perccel rövidebb volt az előírtnál.");
                else if (aktmenetido > menetido)
                    System.out.println("A(z) " + aktvon + ". vonat útja " + (aktmenetido - menetido) + " perccel hosszabb volt az előírtnál.");
                else
                    System.out.println("A(z) " + aktvon + ". vonat útja pontosan az előírt ideig tartott.");
            } else
                System.out.println("Nem volt ilyen vonat.");
            System.out.println("\nA 6. feladat ");
            String cim = "halad" + aktvon + ".txt";
            PrintWriter kiir = new PrintWriter(new FileWriter(cim));
            for (i = 1; i <= db; i++)
                if (von[i - 1] == aktvon && ie[i - 1].equals("E"))
                    kiir.println(all[i - 1] + ". állomás: " + ora[i - 1] + ":" + perc[i - 1]);
            System.out.println("A halad" + aktvon + ".txt fájl kiírása befejeződött.");
            kiir.close();



            System.out.println("\nA 7. feladat ");
            int aktidoertek = 60 * aktora + aktperc;
            akterk = -1;
            aktind = -1;
            for (i = 1; i <= vondb; i++) {
                for (j = 1; j <= vegall; j++) {
                    for (k = 1; k <= db; k++) {
                        if (von[k - 1] == i && all[k - 1] == j - 1 && ie[k - 1].equals("I"))
                            aktind = 60 * ora[k - 1] + perc[k - 1];
                        if (von[k - 1] == i && all[k - 1] == j && ie[k - 1].equals("E")) {
                            akterk = 60 * ora[k - 1] + perc[k - 1];
                            if (aktind < aktidoertek && akterk > aktidoertek)
                                System.out.println("A(z) " + i + ". vonat a(z) " + (j - 1) + ". és a(z) " + (j) + ". állomások között járt.");
                        }
                        if (von[k - 1] == i && all[k - 1] == j && ie[k - 1].equals("I")) {
                            aktind = 60 * ora[k - 1] + perc[k - 1];
                            if (akterk <= aktidoertek && aktind >= aktidoertek)
                                System.out.println("A(z) " + i + ". vonat a(z) " + j + ". állomáson állt.");
                        }
                    }
                }
            }
        }
    }