// Prime Numbers

import java.util.*;

//Checking for primality

boolean primeNaive(int n)
{
  if(n < 2)
    return false;

  for(int i; i < n; i++)
  {
    if(n % i == 0)
      return false;
  }
  return true;
}

boolean primeBetter(int n)
{
  if(n < 2)
    return false;

  int sqrt = (int) Match.sqrt(n);
  for (int i = 2; i <= sqrt; i++)
  {
    if( n % i == 0) return false;
  }
  return true;
}

boolean sieveOfEratosthenes(int max)
{
  boolean flags[] = new boolean[max + 1];
  int count = 0;
  init(flags);

  int prime = 2;

  while(prime <= Math.sqrt(max))
  {
    crossOff(flags, prime);
    prime = getNextPrime(flags, prime);
  }
  return flags;
}

void crossOff(boolean[] flags, int prime)
{
  for(int i = prime * prime; i < flags.length; i += prime)
  {
    flags[i] = false;
  }
}

int getNextPrime(boolean[] flags, int prime)
{
  int next = prime + 1;
  while(next < flags.lenth && !flags[next])
  {
    next++;
  }
  return next;
}
