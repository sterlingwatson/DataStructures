/**
 * BinaraySearchTree is a class that implements methods to allow the inherited Binary Tree to be used as a binary search tree
 * @author	Sterling Watson
 * @version	version 1.0 03/10/2021
 */

public class BinarySearchTree<T> extends BinaryTree{

    BinaryNode focusNode = root;

    

    public BinarySearchTree(){
        super();
    }

    public BinarySearchTree(T[] seq){
        super(seq);
    }

    public BinarySearchTree(T[] seq, int nullSymbol){
       super(seq, nullSymbol);
    }

    /**
     * A method for inserting an Integer value into a binary seach tree
     * @param value Intereger value to be added
     */
    
    public void insert(Integer value){
        
        BinaryNode newNode = new BinaryNode(value);

        if(root == null){
            root = newNode;
    
        }

        while(contains(value) == false){

    
        if(value < (Integer) focusNode.getData()){
   
            if(focusNode.getLeftNode() == null){
                focusNode.setLeftNode(newNode);
            }
            if(focusNode.getLeftNode() != null){
                focusNode = focusNode.getLeftNode();
                insert(value);
            }
        }

        else if(value > (Integer) focusNode.getData()){

            if(focusNode.getRightNode() == null){
                focusNode.setRightNode(newNode);
            }
            if(focusNode.getRightNode() != null){
                focusNode = focusNode.getRightNode();
                insert(value);
            }
        }
    }
    
}

    public boolean contains(Integer value){

        while (focusNode.getData() != value){

            if (value < (Integer) focusNode.getData()){

                focusNode = focusNode.getLeftNode();
            }else {

                focusNode = focusNode.getRightNode();
            }

        if(focusNode == null)
            return false;
}

return true;
    
}

public void remove(Integer value){

    //BinaryNode focusNode = root;

    if(contains(value)){

        if(value < (Integer) focusNode.getData()){                  //value is in left subtree
            focusNode = focusNode.getLeftNode();
            remove( (Integer) focusNode.getData());
        }
        if(value > (Integer) focusNode.getData()){                  //value is in right sub tree
            focusNode = focusNode.getRightNode();
            remove( (Integer) focusNode.getData());
        }
        if(value == (Integer) focusNode.getData()){

            if(focusNode.isLeaf()){
                focusNode = null;
            }
            if(focusNode.getLeftNode() != null && focusNode.getRightNode() == null){
                focusNode = focusNode.getLeftNode();
                focusNode.setLeftNode(null);
            }
            if(focusNode.getRightNode() != null && focusNode.getLeftNode() == null){
                BinaryNode temp = focusNode.getRightNode();
                focusNode.setData(temp.getData());
                focusNode.setRightNode(null);
                temp = null;
            }
            if(focusNode.getRightNode() != null && focusNode.getLeftNode() != null){
                BinaryNode temp = findRightSubtreeMin(focusNode);
                focusNode.setData(temp.getData());
                if(focusNode.getData() == focusNode.getRightNode().getData()){
                    focusNode.setRightNode(null);
                }else{}
                temp = null;
            }
        }
    else
        System.out.println("Doesn't contain that value");
    
}
}

public BinaryNode findRightSubtreeMin(BinaryNode currentNode){

    BinaryNode minNode = new BinaryNode(currentNode.getRightNode().getData());

    minNode = leftmostNode(minNode);
    return minNode;
}

public BinaryNode leftmostNode(BinaryNode newRoot){


    while(newRoot.getLeftNode() != null){
        newRoot = newRoot.getLeftNode();
        if(newRoot.getLeftNode() == null){
            return newRoot;
        }
    }
    return newRoot;
}


}