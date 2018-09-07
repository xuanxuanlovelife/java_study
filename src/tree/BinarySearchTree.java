package tree;

/**
 * 二叉查找树查找，增加，删除
 * @param <T>
 */


class BinaryNode<T> implements Comparable<T>{
    public T data;
    BinaryNode<T> left, right;
    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(T o) {
        return data.toString().compareTo(o.toString());
    }
}


public class BinarySearchTree<T> {

    public BinaryNode<T> rootTree;

    public boolean contains(BinaryNode t){
        return contains(t,rootTree);
    }


    public boolean contains(BinaryNode t,BinaryNode<T> node){
        if(node == null){
            return false;
        }

        int result = t.compareTo(node.data);
        if(result < 0){
            return contains(t,node.left);
        }else if(result > 0){
            return contains(t,node.right);
        }else {
            return true;
        }
    }

    public T findMin(){
        if(rootTree == null){
            return null;
        }
        return findMin(rootTree).data;
    }

    public BinaryNode<T> findMin(BinaryNode<T> node){
        if(node == null) return null;
        while(node.left != null){
            node = node.left;
        }
        return node;
    }

    public T findMax(){
        if(rootTree == null){
            return null;
        }
        return findMin(rootTree).data;
    }

    public BinaryNode<T> findMax(BinaryNode<T> node){
        if(node == null) return null;
        while(node.right != null){
            node = node.right;
        }
        return node;
    }

    public void insert(BinaryNode<T> t){
        rootTree = insert(t,rootTree);
    }

    public BinaryNode<T> insert(BinaryNode<T> t,BinaryNode<T> node){
        if(node == null){
            return new BinaryNode<T>(t.data,null,null);
        }
        int result = t.compareTo(node.data);
        if(result < 0){
            node.left = insert(t,node.left);
        }else if(result > 0){
            node.right = insert(t,node.right);
        }
        return node;
    }

    public void remove(BinaryNode<T> t){
        rootTree = remove(t,rootTree);
    }

    public BinaryNode<T> remove(BinaryNode<T> t,BinaryNode<T> node){
        if(node == null) return node;

        int result = t.compareTo(node.data);
        if(result > 0){
            node.right = remove(t,node.right);
        }else if(result < 0){
            node.left = remove(t,node.left);
        }else if(node.left != null && node.right != null){
            node.data = findMin(node.right).data;
            node.right = remove(new BinaryNode<T>(node.data,null,null),node.right);
        }else{
            node = node.left != null ? node.left : node.right;
        }
        return node;

    }



}
