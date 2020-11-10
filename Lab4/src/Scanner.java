import com.sun.tools.javac.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scanner {
        private static final String PATH_TOKEN = "source/token.in";

        private List<String> errorList;
        private List<String> tokenList;
        private SymbolTable symbolTable;
        private List<PifElement> pif;
        private String program;

    public Scanner(String program) {
        this.program = program;
        this.symbolTable = new SymbolTable(23);
        this.errorList = new ArrayList<>();
        this.tokenList = new ArrayList<>();
        this.pif = new ArrayList<>();
        this.tokenList = tokens();
    }

    private List<String> tokens(){
        List<String> tokens = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(Scanner.PATH_TOKEN));
            String line = "";
            while(true){
                line = reader.readLine();
                if(line == null || line.equals(""))
                    break;
                tokens.add(line.toString());
            }
            reader.close();

        }catch (FileNotFoundException e){

        } catch (IOException e){
            e.printStackTrace();
        }
        return tokens;
    }
    private Boolean isConstant(String token) {
        return token.matches("\\-?[1-9]+[0-9]*|0")
                || token.matches("\"[a-zA-Z0-9 _]+\"")
                || token.equals("true")
                || token.equals("false");
    }

    private Boolean isIdentifier(String token){
        return token.matches("(^[a-zA-Z][a-zA-Z0-9]*)");
    }


    public void scan(){
        try{
            Integer currentLine = 1;
            List<String> tokens = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(this.program));
            String line = "";
            while(true){
                line = reader.readLine();
                if(line == null || line.equals(""))
                    break;
                tokens = Arrays.asList(line.split(" "));
                tokens = tokenSplit(tokens);
                tokens = stringCase(tokens);

                for( String token : tokens){
                    if(this.tokenList.contains(token)){
                        pif.add(new PifElement(token, 0, -1));
                    }else{
                        if(isConstant(token) || isIdentifier(token)) {
                            Pair<Integer, Integer> position = symbolTable.search(token);
                            String aux = "CONST";
                            if(isIdentifier(token))
                                aux = "IDENT";
                            if(position.fst == -1)
                                position = symbolTable.add(token);
                            pif.add(new PifElement(aux, position.fst, position.snd));
                            } else{
                                errorList.add("Lexical error at the token: " + token + " at the line" + currentLine);
                            }
                        }
                    }

                System.out.println(tokens);
                currentLine++;
            }
            reader.close();
            showSymbolTable();
            showPif();
            if(errorList.size()==0) {
                errorList.add("Lexically correct");
                System.out.println("Lexically correct");
            }
            else {
                errorList.add("Lexically incorrect");
                System.out.println("Lexically incorrect");
            }
            showErrors();
            //showProgram(programContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> stringCase(List<String> tokens){
        if(!tokens.toString().contains("\""))
            return tokens;
        List<String> result = new ArrayList<>();
        for(int i=0; i<tokens.size(); i++){
            if(tokens.get(i).contains("\"")  && tokens.get(i).length()>1 && tokens.get(i).substring(1).contains("\"")){
                result.add(tokens.get(i));
            }
            else{
                if(tokens.get(i).contains("\"")){
                    String str = tokens.get(i);
                    for(int j=i+1; j<tokens.size(); j++) {
                        str += " "+tokens.get(j);
                        if (tokens.get(j).contains("\"")) {
                            i = j;
                            break;
                        }
                    }
                    result.add(str);
                }
                else{
                    result.add(tokens.get(i));
                }
            }
        }
        return result;
    }

    private List<String> tokenSplit(List<String> programTokens){
        for(String token: this.tokenList){
            List<String> newTokens = new ArrayList<>();
            for(String line : programTokens) {
                if (line.contains(token)) {
                    List<String> resultLine = new ArrayList<>();
                    if (line.equals(token)) {
                        resultLine.add(line);
                    }else{
                        int firstPos = line.indexOf(token);
                        while (firstPos != -1) {
                            if (firstPos > 0)
                                if(!line.substring(0,firstPos).equals(" "))
                                    resultLine.add(line.substring(0, firstPos));
                            resultLine.add(token);
                            line = line.substring(token.length() + firstPos);
                            firstPos = line.indexOf(token);
                        }
                        if(line.length()>0){
                            resultLine.add(line);
                        }
                    }
                    newTokens.addAll(resultLine);
                } else {
                    newTokens.add(line);
                }
            }
            programTokens = newTokens;
        }
        return programTokens;
    }

    public void showProgram(List<String> programContent){
        for(String line : programContent){
            System.out.println(line);
        }
    }

    public void showSymbolTable(){
        try {
            FileWriter fileWriter = new FileWriter("output/st.out");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(int index=0; index< symbolTable.size(); index++){
                String str = index + ") ";
                Node node = symbolTable.getElements()[index];
                while(node != null){
                    str += node.identifier + " " + node.index + " ";
                    node = node.next;
                }
                printWriter.println(str);
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showPif(){
        try {
            FileWriter fileWriter = new FileWriter("output/pif.out");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(int index=0; index< pif.size(); index++){
                printWriter.println(index + ") " + pif.get(index).getTokenName() + " " + pif.get(index).getHashPosition() + " " + pif.get(index).getListPosition());
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showErrors(){
        try {
            FileWriter fileWriter = new FileWriter("output/lexicalErrors.out");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(int index=0; index< errorList.size(); index++){
                printWriter.println(index + ") " + errorList.get(index));
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
