import java.util.*;


//binary search Tree

public class Tree
{
  class Node
  {
    int value;
    Node left;
    Node right;

    Node(int value)
    {
      this.value = value;
      right = null;
      left = null;
    }
  }

  Node root;

  private Node addRecursive(Node current, int value)
  {
    if(current == null)
      return new Node(value);

    if(value < current.value)
      current.left = addRecursive(current.left, value);
    else if(value > current.value)
      current.right = addRecursive(current.right, value);
    else
      return current;

    return current;
  }

  //wrapper function
  public void add(int value)
  {
    root = addRecursive(root, value);
  }

  private boolean containsNodeRecursive(Node root, int value)
  {
    if(root == null)
      return false;

    if(root.value == value)
      return true;

    if(value < root.value)
      return containsNodeRecursive(root.left, value);

    else if(value > root.value)
      return containsNodeRecursive(root.right, value);

    else
      return false;
  }

  //wrapper function
  public boolean containsNode(int value)
  {
    return containsNodeRecursive(root, value);
  }

  private Node deleteRecursive(Node root, int value)
  {
    if(root == null)
      return null;

    if(root.value == value)
    {
      //Case 1, No leafs
      if((root.left == null) && (root.right == null))
        return null;

      //Case 2, right no left
      if(root.left == null)
        return root.right;

      //Case 3, left, no right
      if(root.right == null)
        return root.left;

      //Case 4, right and left
      
      int smallestValue = findSmallestValue(current.right);
      current.value = smallestValue;
      current.value = deleteRecursive(current.right, smallestValue);
      return current;

    }

    if(value < root.value)
    {
      root.left = deleteRecursive(root.left, value);
      return root;
    }

    else if(value > root.value)
    {
      root.right = deleteRecursive(root.right, value);
      return root;
    }
  }

  private int findSmallestValue(Node root)
  {
    return root.left == null ? root.value : findSmallestValue(root.left);
  }

}
