public class Node {
    String identifier;
    Integer index;
    Node next;

    public Node(String identifier, Integer index) {
        this.identifier = identifier;
        this.index = index;
        this.next = null;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node nextNode) {
        this.next = nextNode;
    }
}
