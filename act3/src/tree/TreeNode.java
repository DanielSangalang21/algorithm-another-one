package tree;

import java.util.LinkedList;

public class TreeNode<T> extends LinkedList {
    public TreeNode<T> leftNode;
    public T data;
    private char c;
    public TreeNode<T> rightNode;
    public TreeNode(T nodeData) {
        data = nodeData;
        leftNode = null;
        rightNode = null;
    }
    public void setLeft(TreeNode<T> left) {
        leftNode = left;
    }
    public void setRight(TreeNode<T> right) {
        rightNode = right;
    }
    public void setData(T d) {
        data = d;
    }
    public TreeNode<T> getLeftNode() {
        return leftNode;
    }
    public TreeNode<T> getRightNode() {
        return rightNode;
    }
    public T getData() {
        return data;
    }
    public String toString() {
        return data.toString();
    }
    public void insert(T insertValue) {
        if (insertValue.toString().compareTo(data.toString()) < 0) {  if (leftNode == null)
            leftNode = new TreeNode<T>(insertValue);  else
            leftNode.insert(insertValue);
        } // end if
        else if (insertValue.toString().compareTo(data.toString()) > 0) {  if (rightNode == null)
            rightNode = new TreeNode<T>(insertValue);  else
            rightNode.insert(insertValue);
        }// end else if
    } // end insert
    public void setCode(){

    }

} // end TreeNode<T> class
