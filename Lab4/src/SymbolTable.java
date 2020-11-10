import com.sun.tools.javac.util.Pair;

public class SymbolTable {
    private HashTable hashTable;
    public SymbolTable(Integer primeNumber) { hashTable = new HashTable(primeNumber);}
    public Pair<Integer, Integer> add(String x) { return hashTable.add(x);}
    public Pair<Integer, Integer> search(String x) { return hashTable.search(x);}
    public Integer size() { return hashTable.size();}
    public Node[] getElements() {return hashTable.getElements(); }

}
