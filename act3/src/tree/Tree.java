package tree;

public class Tree<T> implements Comparable<Tree<Element>> {
    private TreeNode<T> root;
    private Element sum;
    private Tree<Element> previous;
    private Tree<Element> next;
    private T element;
    public Tree() {
        root = null;
    }

    public Tree(Element sum, Tree<Element> previous, Tree<Element> next) {
        this.sum = sum;
        this.next = next;
        this.previous = previous;
    }

    public void insertNode(T insertValue) {
        if (root == null)
            root = new TreeNode<T>(insertValue);
        else
            root.insert(insertValue);
    }

    public boolean isLeaf(){
        if(root.getRightNode() == null && root.getLeftNode() == null){
            return true;
        } else {
            return false;
        }
    }

    public TreeNode<Element> getRoot() {
        return (TreeNode<Element>) root;
    }


    public T find(TreeNode<T> root, T element) {
        T result = null;
        if (this.root.getLeftNode() != null) result = find(root, element);
        if (this.root == element) return (T) this;
        if (result == null && this.root.getRightNode() != null)
            result = find(root,element);

        return result;
    }

    @Override
    public int compareTo(Tree<Element> o) {
        return 0;
    }
}

