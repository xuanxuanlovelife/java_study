package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node{
    public int val;
    public Node left;
    public Node right;
    public Node(int val){
        this.val = val;
    }
}
public class BinaryTree {

    public static void main(String[] args){
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        System.out.print(levelOrder(root));
    }

    public static ArrayList<Integer> preOrder(Node root){
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Stack<Node> stack = new Stack<>();
        while(root != null || !stack.empty()){
            while(root != null){
                result.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return result;
    }

    public static ArrayList<Integer> inOrder(Node root){
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Stack<Node> stack = new Stack<>();
        while(root != null || !stack.empty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }

    public static ArrayList<Integer> postOrder(Node root){
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(root);
        while(!s1.empty()){
            root = s1.pop();
            if(root.left != null) s1.push(root.left);
            if(root.right != null) s1.push(root.right);
            s2.push(root);
        }
        while(!s2.empty()){
            result.add(s2.pop().val);
        }
        return result;
    }

    public static ArrayList<Integer> levelOrder(Node root){
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            root = queue.poll();
            if(root.left != null) queue.add(root.left);
            if(root.right != null) queue.add(root.right);
            result.add(root.val);
        }
        return  result;
    }
}
