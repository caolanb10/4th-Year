import java.util.*;

public class MyQueue<T>
{
  private static class MyNode<T>
  {
    private T data;
    private MyNode<T> next;
    public MyNode(T data)
    {
      this.data = data;
    }
  }

  private MyNode<T> first;
  private MyNode<T> last;

  public void add(T item)
  {
    MyNode<T> node = new MyNode<T>(item);
    if(last != null)
      last.next = node;

    last = t;
    
    if(first == null)
      first = last;
  }

  public T remove()
  {
    if(first == null) throw new NoSuchElementException();
    T data = first.data;
    first = first.next;
    if(first == null)
      last = null;
    return data;
  }

  public T peek()
  {
    if(first == null) throw new NoSuchElementException();
      return first.data;
  }

  public boolean isEmpty()
  {
    return first == null;
  }
}
