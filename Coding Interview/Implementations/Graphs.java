import java.util.*;
import java.util.ArrayList;

public class Graph
{
  public ArrayList<Node> Nodes;
  public ArrayList<>
  //Constructor for vertices
  Graph(String[] names)
  {
    for(int i; i < names.length; i++)
    {
      Nodes.add(names[i]);
    }
  }
  //Constructor for Edges
  Graph(String[][] edges)
  {
    for(String[] e : edges)

  }
  public addNode(String name)
  {
    this.Node.add(Node(name));
  }

  public addNodes(String[] names)
  {
    for(String s: names)
      Nodes.add(Node(s));
  }
}

public class Node
{
  public String name;
  public ArrayList<Node> children;

  Node(String name)
  {
    this.name = name;
    this.children = new ArrayList<Node>();
  }

  public addChild(Node<T> child)
  {
    this.children.add(child);
  }
}

public class Edge
{
  public Node parent;
  public Node child;
  public boolean dir;

  Edge(String parent, String child, boolean dir)
  {
      this.parent = parent;
      this.child = child;
      this.dir = dir;
  }
}

public static main(String[] args)
{
  int[] nodes = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
  int[][] vertices = {{"1","2"}, {"3","4"}, {"1","3"}, {"8","5"}, {"7","9"}, {"6","5"}, {"4","2"}};
  Graph demoGraph = new Graph(nodes.length);

  for(int i = 0; i< nodes.length; i++)
  {

  }
}
