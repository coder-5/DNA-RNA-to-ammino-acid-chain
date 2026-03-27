import java.util.Scanner;

import static java.lang.IO.print;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        StringBuilder isDNAorRNA = new StringBuilder();
        StringBuilder RNA = new StringBuilder();
        StringBuilder input = new StringBuilder();
        boolean inputNotFinalized = true;

        while (String.valueOf(isDNAorRNA).equals("DNA") || String.valueOf(isDNAorRNA).equals("RNA")) {
            print("Enter DNA or RNA: ");
            if (sc.nextLine().equalsIgnoreCase("DNA")) {
                isDNAorRNA.append("DNA");
            } else if (sc.nextLine().equalsIgnoreCase("RNA")) {
                isDNAorRNA.append("RNA");
            } else {
                print("Invalid input, try again.");
            }
        }

        TakeInRNA(RNA, inputNotFinalized, input, sc, isDNAorRNA);
    }

    private static void TakeInRNA(StringBuilder RNA, boolean inputNotFinalized, StringBuilder input, Scanner sc, StringBuilder isDNAorRNA) {
        mainParsingLoop:
        while (RNA.isEmpty() || inputNotFinalized) {
            print("Enter DNA or RNA sequence: ");
            input.append(sc.nextLine());
            if (isDNAorRNA.toString().equals("DNA")) {
                input.delete(0, input.indexOf("TAC"));

                for (char c : input.toString().toCharArray()) {
                    switch (c) {
                        case 'T':
                            RNA.append("A");
                            break;
                        case 'A':
                            RNA.append("U");
                            break;
                        case 'G':
                            RNA.append("C");
                            break;
                        case 'C':
                            RNA.append("G");
                            break;
                        default:
                            System.out.println("Invalid input, try again.");
                            continue mainParsingLoop;
                    }
                }

            } else {
                for (char c : input.toString().toCharArray()) {
                    switch (c) {
                        case 'U':
                            RNA.append("U");
                            break;
                        case 'A':
                            RNA.append("A");
                            break;
                        case 'G':
                            RNA.append("G");
                            break;
                        case 'C':
                            RNA.append("C");
                            break;
                        default:
                            System.out.println("Invalid input, try again.");
                            continue mainParsingLoop;
                    }
                }
            }

            if (RNA.toString().contains("AUG")) {
                RNA.delete(0, RNA.indexOf("AUG"));
            } else {
                System.out.println("Invalid input, try again.");
                continue;
            }

            if (removeEndCodon(RNA)) continue;

            while (RNA.toString().contains("UAA") || RNA.toString().contains("UAG") || RNA.toString().contains("UGA")) {
                removeEndCodon(RNA);
            }


            inputNotFinalized = false;
        }
    }

    private static boolean removeEndCodon(StringBuilder RNA) {
        if (RNA.toString().contains("UAA")) {
            RNA.delete(RNA.lastIndexOf("UAA") + 1, RNA.length());
        } else if (RNA.toString().contains("UAG")) {
            RNA.delete(RNA.lastIndexOf("UAG") + 1, RNA.length());
        } else if (RNA.toString().contains("UGA")) {
            RNA.delete(RNA.lastIndexOf("UGA") + 1, RNA.length());
        } else {
            System.out.println("Invalid input, try again.");
            return true;
        }
        return false;
    }

}
