import Automata.FiniteAutomata;
import com.sun.tools.javac.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    private static final String PATH_P1 = "source/p1.txt";
    private static final String PATH_P2 = "source/p2.txt";
    private static final String PATH_P3 = "source/p3.txt";
    private static final String PATH_ERROR = "source/error.txt";
    public static void main(String[] args) {
        //lab2();
        //lab3();
        lab4();
    }

    private static void lab4(){
        FiniteAutomata finiteAutomata = new FiniteAutomata();
        finiteAutomata.read();
        try {
            boolean aux = true;
            while (aux) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("1) Show states");
                System.out.println("2) Show alphabet");
                System.out.println("3) Show initial state");
                System.out.println("4) Show final state");
                System.out.println("5) Show transitions");
                System.out.println("6) Is DFA?");
                System.out.println("7) Test sequence");
                System.out.println("x) Exit");
                System.out.println("Enter the number of the program: \n");
                String number = reader.readLine();
                switch (number){
                    case "1":
                        //show states
                        finiteAutomata.showStates();
                        break;
                    case "2":
                        //show alphabet
                        finiteAutomata.showAlphabet();
                        break;
                    case "3":
                        //show initial State
                        finiteAutomata.showInitialState();
                        break;
                    case "4":
                        //show final State
                        finiteAutomata.showFinalState();
                        break;
                    case "5":
                        //show transitions
                        finiteAutomata.showTransitions();
                        break;
                    case "6":
                        // is DFA
                        System.out.println(finiteAutomata.isDFA() ? "Is DFA" : "Is not DFA");
                        break;
                    case "7":
                        try{
                            BufferedReader r =  new BufferedReader(new InputStreamReader(System.in));
                            System.out.println("Enter the sequence: ");
                            String sequence = r.readLine();
                            String message  = finiteAutomata.testSequence(sequence) ? "Sequence is accepted" : "Sequence is not accepted";
                            System.out.println(message);
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "x":
                        aux = false;
                        break;
                    default:
                        System.out.println("Wrong number entered!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void lab3(){
        try{
            boolean aux = true;
            while (aux) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter the number of the program:");
                String number = reader.readLine();
                String source = main.PATH_ERROR;
                switch (number) {
                    case "1":
                        source = main.PATH_P1;
                        break;
                    case "2":
                        source = main.PATH_P2;
                        break;
                    case "3":
                        source = main.PATH_P3;
                        break;
                    case "e":
                        source = main.PATH_ERROR;
                        break;
                    case "x":
                        aux = false;
                        break;
                    default:
                        System.out.println("Wrong number entered!");
                }
                Scanner scanner = new Scanner(source);
                scanner.scan();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void lab2(){
        SymbolTable symbolTable = new SymbolTable(23);
        Pair<Integer, Integer> a = symbolTable.search("a");
        System.out.println("HashValue: " + a.fst + " at position: " +  a.snd);
        a = symbolTable.add("a");
        System.out.println("HashValue: " + a.fst + " at position: " +  a.snd);

        Pair<Integer, Integer> b = symbolTable.add("b");
        System.out.println("HashValue: " + b.fst + " at position: " +  b.snd);

        Pair<Integer, Integer> a2 = symbolTable.add("a");
        System.out.println("HashValue: " + a2.fst + " at position: " +  a2.snd);

        Pair<Integer, Integer> a3 = symbolTable.add("a");
        System.out.println("HashValue: " + a3.fst + " at position: " +  a3.snd);
    }
}
