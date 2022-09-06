# Randusy Encryption
A one way encryption algorithm implemented in Java.

# Purpose
To provide a simple unhackable encryption for passwords.

# Overview

The algorithm takes in a string as the password, converts it to ACSII integers,
Finds the prime factors of the numbers, Multiplies them in groups, Adds an offset to the results and
converts them back to ASCII characters.

# Description

Lets encrypt the password “randu”. 
We are going to create a list of letters from the word, and get [‘r’,’a’,’n’,’d’,’u’].

From this list, we are going to convert each character to its ASCII integer equivalent.

We end up getting the following list of integers [114,97,110,100,117]. 

From this list of integers we find the list of prime factors of each and every number in the list 
i.e 114 will produce [2, 3, 19], similarly 97 will produce [97], 110 will produce [2, 5, 11] and so on...

After getting the list of prime factors from all the numbers, the lists are concatenated and we will have the resulting list as 
[2, 3, 19, 97, 2, 5, 11, 2, 2, 5, 5, 3, 3, 13].

From this resulting list, we find the product of the numbers in groups of three and if
there is a remainder its computed as a group of two or if its one then its added
directly, so we end up with the following list [(2 * 3 * 19), (97 * 2 * 5), (11 * 2 * 2),
(5 * 5 * 3), (3 * 13)] which gives us [114,970,44,75,39]. 

Based on this list we can see that we have reverted back some of the numbers in the original list i.e 114 in
{[114,97,110,100,117]}. 

Due to this we are going to do a little bit of offset shift of the characters hence we will be adding an offset of being the length of the password (i.e 5) and an additional offset of the length of the list at that point (i.e real index position, not zero based). 

So the total offset will be (5 + 3) + 114 for the first number which will give us 122, (5 + 6) + 970 = 981, (5 + 9) + 44= 58, (5 + 12)+ 75=
92, (5 + 15) + 39= 59 and we end up with the following list [122,981,58,92,59]. 

At this point we are going to now get the encryption string itself by finding the equivalent ASCII character of the numbers in the list, therefore the first number will give us 122 = ‘w’ then 981 is out of range of the ASCII characters so we take the first two digits and get the ASCII equivalent i.e 98 = ‘b’ and we pass 1 the way it is. 

Note: The algorithm converts ASCII equivalent in the range between 33 and 126 inclusive and the rest of the characters are passed as is, 58 will give us ‘:’, 92 = ‘\’, 59 = ‘;’, so we and up with the following list [‘z’,’b1’,’:’,’\’,’;’]. 

At this point we have the list of the encryption characters therefore we concatenate the characters in the list and we
end up having the following string as our encryption “zb1:\;”

# Code Example

Clone the project and extract the RandusyEncryption.java file and include it in your project.

Create a driver class and in the main method add the following testing code.
<code><pre>
//example 1

RandusyEncryption e = new RandusyEncryption("myStrong@Password!@");
System.out.println("Encryption : "+e);
System.out.println("Verify :"+e.verifyPassword("myStrong@Password!@."));
     
//output
Encryption : 13211#720221(144,903B11204U719*172Q229KN
Verify :false

//example 2
RandusyEncryption e2 = new RandusyEncryption("password");
System.out.println("Encryption : "+e2);
System.out.println("Verify :"+e2.verifyPassword("password"));

//output
Encryption : 1913H;2279245140O
Verify :true

//example 3
RandusyEncryption e3 = new RandusyEncryption("randu");
System.out.println("Encryption : "+e3);
System.out.println("Verify :"+e3.verifyPassword("randu"));

//output
Encryption : zb1:\;
Verify :true
</pre></code>
