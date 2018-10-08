import java.util.Arrays;

public class HashFunction
{
  String[] theArray;
  int arraySize;
  int itemsInArray;

  public static void main(String[] args)
  {
    HashFunction theFunc = new HashFunction(30);
    String[] elements = { "100", "510", "170", "214", "268", "398",
                "235", "802", "900", "723", "699", "1", "16", "999", "890",
                "725", "998", "978", "988", "990", "989", "984", "320", "321",
                "400", "415", "450", "50", "660", "624"
              }
    theFunc.hashFunction2(elements, theFunc.theArray);
    theFunc.findKey("660");
    theFunc.displayTheStack();

  }
  public void hashFunction1(String[] stringsForArray, String[] theArray)
  {
    for(int i =0; i< stringsForArray.length; i++)
    {
      String newElement = stringsForArray[i];
      theArray[Integer.parseInt(newElement)] = newElement;

    }
  }

  public void hashFunction2(Strin[] stringsForArray, String[] theArray)
  {
    for(int i = 0; i<stringsForArray.length; i++)
    {
      String newElement = stringsForArray[i];
      int arrayIndex = Integer.parseInt(newElement) % 29;
      System.out.println("Modulus Index= " + arrayIndex + "for value" + newElement);
      while(theArray[arrayIndex] != "-1")
      {
        ++arrayIndex;
        System.out.println("Collision Try " + arrayIndex + " Instead");
        arrayIndex %= arraySize;
      }
      theArray[arrayIndex] = newElement;
    }
  }

  public String findKey(String key)
  {
    int arrayIndexHash = Integer.parseInt(key) % 29;
    while(theArray[arrayIndexHash] != "-1")
    {
      if(theArray[arrayIndexHash] == key)
      {
        System.out.println(key + " was found at index " + arrayIndexHash);
        return theArray[arrayIndexHash];
      }
      ++arrayIndexHash;
      arrayIndexHash %= arraySize;
    }
    return null;
  }

  //Constructor
  HashFunction(int size)
  {
    arraySize = size;
    theArray = new String[size];
    Arrays.fill(theArray, "-1");
  }

/*
  public void displayTheStack()
  {
    int increment = 0;
    for (int i = 0; i<3; i++)
    {
      incement +=10;
      for(int n = 0; n< 71; n++)
    }
  }
  */
}
