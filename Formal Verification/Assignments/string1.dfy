// Fiach Time - 1 hr
// Caolan Barry - 2 hr
method isPrefix(pre: string, str: string) returns (res:bool)
requires |pre| <= |str|
requires 0 < |pre|
requires 0 < |str|
{
    if (pre == str[0..|pre|])
    {
        return true;
    }
    return false;
}

method isSubstring(sub: string, str: string) returns (res:bool)
requires |sub| <= |str|
requires 0 < |sub|
requires 0 < |str|
{

    var t:= false;
    var i:= 0;
    var diff:= |str| - |sub|;
    while i <= diff
    invariant i>= 0;
    decreases diff - i
    {
        t:= isPrefix(sub, str[i..|sub| + i]);

        if(t)
        {
            return true;
        }
        else
        {
            i:= i + 1;
        }
    }
    return t;
}

method haveCommonKSubstring(k: nat, str1: string, str2: string) returns(b: bool)
requires 0 < |str1| 
requires 0 < |str2|
requires k <= |str1|
requires k <= |str2|
requires k > 0
{
    var q:= false;
    var i:= 0;
    var range:= |str1| - k;
    while i <= range
    invariant 0 <= i
    decreases range - i
    {
        q:= isSubstring(str1[i..i+k], str2);
        if(q)
        {
            return true;
        }
        else
        {
            i:= i + 1;
        }
    }
    return q;
}

method maxCommonSubstringLength(str1: string, str2: string) returns (len: nat)
{
    var subL:= 0;
    //empty strings have len 0 common substring
    if (|str1| == 0 || |str2| == 0)
    {
        return subL;
    }

    //Index range for shorter string 
    var i:= 0;
    if |str1| >= |str2|
    {
        i:= |str2|;
    }
    else
    {
        i:= |str1|;
    }

    var t := false;
    while i > 0
    invariant i>=0
    decreases i - 0
    {
        t:= haveCommonKSubstring(i, str1, str2);
        if(t)
        {
            subL:=i;
            return subL;
        }
        i := i - 1;
    }
}
