import java.util.*;

/*
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

Hash Table
 */
public class LT438_Find_All_Anagrams_in_a_String {
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s.length()<p.length()) return res;
        int m = s.length(), n = p.length();
        int[] ss = new int[26];
        int[] pp = new int[26];
        for(int i=0;i<n;i++){
            ss[s.charAt(i)-'a']++;
            pp[p.charAt(i)-'a']++;
        }
        
        for(int i=n;i<m;i++){
            if(compare(ss, pp)) res.add(i-n);
            ss[s.charAt(i)-'a']++;
            ss[s.charAt(i-n)-'a']--;
        }
        if(compare(ss, pp)) res.add(m-n);
        return res;
        
    }
    
    public boolean compare(int[] a, int[] b){
        for(int i=0;i<26;i++){
            if(a[i]!=b[i])
                return false;
        }
        return true;
    }
    
    public List<Integer> findAnagrams(String s, String p) {
	// sliding window. update count
	List<Integer> list = new ArrayList<>();
	if (s == null || s.length() == 0 || p == null || p.length() == 0)
	    return list;
	int[] hash = new int[256];
	for (int i = 0; i < p.length(); i++)
	    hash[p.charAt(i)]++;
	int left = 0, right = 0, count = p.length();
	while (right < s.length()) {
	    // move right everytime, if the character exists in p's hash, decrease the count
	    // current hash value >= 1 means the character is existing in p
	    if (hash[s.charAt(right++)]-- >= 1)
		count--;
	    // if (hash[s.charAt(right)] >= 1) {
	    // count--;
	    // }
	    // hash[s.charAt(right)]--;
	    // right++;

	    // when the count is down to 0, means we found the right anagram
	    // then add window's left to result list
	    if (count == 0)
		list.add(left);

	    // if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
	    // ++ to reset the hash because we kicked out the left
	    // only increase the count if the character is in p
	    // the count >= 0 indicate it was original in the hash, cuz it won't go below 0
	    if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0)
		count++;
	    // if (right - left == p.length() ) {
	    // if (hash[s.charAt(left)] >= 0) {
	    // count++;
	    // }
	    // hash[s.charAt(left)]++;
	    // left++;
	    // }
	}

	return list;
    }
}
