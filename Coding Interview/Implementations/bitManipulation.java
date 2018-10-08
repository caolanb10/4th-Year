/*
By hand examples:
------------------------------------------
0110+0010 = 1000
6+2 = 8
------------------------------------------
0011 + 0010 = 0101
------------------------------------------
0110 - 0011 = 0011
6 - 3 = 3
------------------------------------------
1000 - 0110

------------------------------------------
0011 * 0101
------------------------------------------
0011 * 0011
------------------------------------------
1101 >> 2
------------------------------------------
1101 ^ 0101
------------------------------------------
0110 + 0110
------------------------------------------
0100 * 0011
------------------------------------------
1101 ^ (~1101)
------------------------------------------
1011 & (~0 << 2)
------------------------------------------
know all by hand ..

///////////////////
Symbols////////////
//////////////////
^ - XOR
~ - NOT
& - AND
| - OR

///////////////////
Bit tricks////////
//////////////////
x ^ 0s = x
x ^ 1s = ~x
x^x = 0
------------------------------------------
x & 0s = 0
x & 1s = x
x & x = x
------------------------------------------
x | 0s = x
x | 1s = 1s
x | x = x
------------------------------------------
Two's compliment and negative numbers

See book page 113

------------------------------------------
Arithmetic shift Versus Logical shift

Logical shift will shift the bits to the right and will
fill in the empty bit at the right as 0

example:

10001010 >>>
01000101
------------------------------------------
Arithmetic Shift will fill in the new bit as the sign bit

exmaple:

100111010 >>
110011101
------------------------------------------
*/


import java.util.*;

boolean getBit(int num, int i)
{
  return((num & (1<<i)) != 0);
}

int setBit(int num, int i)
{
  return (num | (1<<i));
}

int clearBit(int num, int i)
{
  int bitMask = ~(1 << i);
  return num & bitMask;
}

//Clears bits from Most Significant bit through to the bit at i
int clearBitsMSBthroughI(int num, int i)
{
  int bitMask = (1 << i) - 1;
  return num & bitMask;

}

int clearBitsLSBthroughI(int num, int i)
{
  int bitMask = (-1 << (i + 1));
  return num & bitMask;
}

int updateBit(int num, int i, boolean is1)
{
  int value = bits1 ? 1:0;
  int mask = ~(1 << i);
  return (num & mask) | (value << i);
}
