//Suppose you wanted to concatenate a list of strings together (where all strings are of size x)
//Example implementation

String joinStrings(String[] words)
{
  Strnig sentence = "";
  for (int i = 0; i< words.length; i++)
  {
    sentence = sentence + words[i];
  }
  return sentence;
}
//Big O time is O(xn^2) as on each iteration we need to make a copy of the previous string character by character

String joinWords2(String[] words)
{
  StringBuilder sentence = new StringBuilder();
  for(String w:words)
  {
    sentene.append(w);
  }
  return sentence.toString();
}
