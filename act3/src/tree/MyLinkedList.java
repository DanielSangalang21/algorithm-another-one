package tree;

import java.util.NoSuchElementException;

public class MyLinkedList<T>{
    private Node<T> head,tail;
    private int size = 0;
    public MyLinkedList() {
        head = tail = null;
    }


    public int getSize() {
        return size;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (tail == null)
        {
            head = tail = newNode;
            head.setPrevious(null);
        }
        else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail=newNode;
        }
        tail.setNext(null);
        size++;
    }

    public T getElement(T data) {
        T element = null;
        Node<T> current = head;
        while (current != null)
        {
            if (current.getData().equals(data))
                element =  current.getData();
            current = current.getNext();
        }
        if (element == null) {
            try {
                throw new NoSuchElementException("No element exists");
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
        return  element;
    }


    public boolean delete(T data) {
        Node<T> current = head;
        if(head.getData().equals(data)){
            head = head.getNext();
            head.setPrevious(null);
            size--;
            return true;
        }
        while (current != null) {
            if (current.getData().equals(data)) {
                current = current.getPrevious();
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }




    public boolean search(T data) {
        Node<T> node = head;
        while (node != null) {
            if (node.getData().equals(data)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    public void clear() {
        size = 0;
    }

    public boolean isEmpty() {
        if (head == null){
            return true;
        } else {
            return false;
        }
    }

    public T getFirst() {
        return head.getData();
    }

	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> Comparable<T> getLast() {
		// return (Comparable<T>) tail.getData();
		try {
			return (Comparable<T>) tail.getData();
		} catch (NullPointerException ex) {
			return null;
		}
	}

    public T removeLast() {
        tail = tail.getPrevious();
        return (tail != null) ? tail.getData() : null;
        //return tail.getData();
    }

    public T removeFirst() {
    	 head = head.getNext();
    	try {
			return head.getData();
		} catch (NullPointerException ex) {
			return null;
		}
    }

    public String toString() {
        StringBuilder result = new StringBuilder("");
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.getData());
            current = current.getNext();
            if (current != null)
                result.append("\n");
        }
        return result.toString();
    }


}