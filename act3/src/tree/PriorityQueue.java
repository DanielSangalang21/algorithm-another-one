package tree;
public class PriorityQueue<T extends Comparable<T>> {
    private MyLinkedList list;
    public PriorityQueue() {
        list = new MyLinkedList<>();
    }
    public void setList(MyLinkedList<T> list) {
        this.list = list;
    }
    public void clear() {
        list.clear();
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
    public T firsElement() {
        return (T) list.getFirst();
    }
    public T dequeue() {
        return (T) list.removeFirst();
    }
    public void enqueue(T element) {
        MyLinkedList<T> temp = new MyLinkedList<>();
        while (!list.isEmpty() && list.getLast() != null && (list.getLast().compareTo(element) > 0)) {
        	if(list.removeLast() != null) {
        		temp.add((T) list.removeLast());
        	}
        }
        list.add(element);

        while (!temp.isEmpty()) {
            list.add(temp.removeLast());
        }
    }
    public MyLinkedList<T> getList() {
        return list;
    }
    public String toString() {
        return list.toString();
    }
}