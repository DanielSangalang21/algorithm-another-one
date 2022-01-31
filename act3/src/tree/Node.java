package tree;

public class Node<E> {
    public E data;
    public Node<E> next;
    public Node<E> previous;

    public Node(E data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public E getData(){
        return this.data;
    }

    public Node<E> getNext() {
        return this.next;
    }

    public void setNext(Node<E> node) {
        this.next = node;
    }

    public Node<E> getPrevious() {
        return this.previous;
    }

    public void setPrevious(Node<E> node) {
        this.previous = node;
    }
}
