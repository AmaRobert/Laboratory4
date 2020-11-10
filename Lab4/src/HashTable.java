import com.sun.tools.javac.util.Pair;

public class HashTable {
    private Node[] elements;
    private Integer primeNumber;

    public HashTable(Integer primeNumber) {
        this.elements = new Node[primeNumber];
        this.primeNumber = primeNumber;
    }

    public Pair<Integer, Integer> add(String x){
        Integer hValue = this.hash(x);

        // first we check if the position is empty
        if(this.elements[hValue] == null){
            Node node = new Node(x, 0);
            this.elements[hValue] = node;
            return new Pair<>(hValue, 0);
        }
        // if  we parse the nodes until the end
        Node current = this.elements[hValue];

        while(current.next != null){
            current = current.next;
        }
        // and place it here
        Node node = new Node(x, current.index + 1);
        current.next = node;
        return new Pair<>(hValue, node.index);
    }

    public Pair<Integer, Integer> search(String x){
        Integer hValue = this.hash(x);
        Node current = this.elements[hValue];
        if(current != null){
            while(current != null){
                if(current.identifier.equals(x)){
                    return new Pair<>(hValue, current.index);
                }
                current = current.next;
            }
        }
        return new Pair<>(-1, -1);
    }

    public Integer hash(String x){
        Integer sum = 0;
        for(int i=0; i<x.length(); i++){
            sum += x.charAt(i);
        }
        return sum % this.primeNumber;
    }

    public Integer size() { return primeNumber; }
    public Node[] getElements() {return elements; }

}
