import java.util.*;

public class MyList<T>
{
  private int size = 0;
  private static final int DEFAULT_COPY = 10;
  private Object elements[];

  public MyList()
  {
    elements = new Object[DEFAULT_COPY];
  }

  public void add(T e)
  {
    if(size == elemts.length)
      ensureCapa();
    elements[size++] = e;

  }

  private void ensureCapa()
  {
    int newSize = elements.length * 2;
    elements = Arrays.copyOf(elements, newSize);

  }

  public T get(int i)
  {
    if(i>=size || i<0)
    {
      throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i);
    }
    return (T) elements[i];
    
  }
}
