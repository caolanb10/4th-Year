//Fiach - 1hr
//Caolan - 2hr


predicate isPrefixPred(pre:string, str:string)
{
	(|pre| <= |str|) && pre == str[..|pre|]
}

predicate isNotPrefixPred(pre:string, str:string)
{
	
    (|pre| > |str|) || pre != str[..|pre|]
}

// Sanity check: Dafny should be able to automatically prove the following lemma
lemma PrefixNegationLemma(pre:string, str:string)
	ensures  isPrefixPred(pre,str) <==> !isNotPrefixPred(pre,str)
	ensures !isPrefixPred(pre,str) <==>  isNotPrefixPred(pre,str)
{}


predicate isSubstringPred(sub:string, str:string)
    requires |sub| <= |str|
{
    exists i, j :: (0 <= i <= (|str| - |sub|) && (j == (i + |sub|))) && sub == str[i..i+j]
}


predicate isNotSubstringPred(sub:string, str:string)
{
	//TODO: your FOL formula should start with a forall
    !(forall i, j :: !((0 <= i <= (|str| - |sub|) && (j == (i + |sub|))) ==> sub == str[i..i+j]))
}

// Sanity check: Dafny should be able to automatically prove the following lemma
lemma SubstringNegationLemma(sub:string, str:string)
	ensures  isSubstringPred(sub,str) <==> !isNotSubstringPred(sub,str)
	ensures !isSubstringPred(sub,str) <==>  isNotSubstringPred(sub,str)
{}


predicate haveCommonKSubstringPred(k:nat, str1:string, str2:string)
{
	exists i :: (0 <= i < (|str1|-k)) ==> isSubstringPred(str1[i..i+k], str2)
    //forall sub :: (|sub| == k) ==> (exists sub :: (|sub| == k) && (sub in str1) && (sub in str2))
}

predicate haveNotCommonKSubstringPred(k:nat, str1:string, str2:string)
{
	//TODO: your FOL formula should start with a forall
    forall x, y :: (|x| == k) && (|y| == k) && isSubstringPred(x, str1) && isSubstringPred(y, str2) ==> x != y
}

// Sanity check: Dafny should be able to automatically prove the following lemma
lemma commonKSubstringLemma(k:nat, str1:string, str2:string)
	ensures  haveCommonKSubstringPred(k,str1,str2) <==> !haveNotCommonKSubstringPred(k,str1,str2)
	ensures !haveCommonKSubstringPred(k,str1,str2) <==> haveNotCommonKSubstringPred(k,str1,str2)
{}

