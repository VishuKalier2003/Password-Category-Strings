/* A password is considered strong if the below conditions are all met:
1)- It has at least 6 characters and at most 20 characters.
2)- It contains at least one lowercase letter, at least one uppercase letter, and at least one digit.
3)- It does not contain three repeating characters in a row (i.e., "Baaabb0" is weak, but "Baaba0" is strong).
Given a string password, return the minimum number of steps required to make password strong. if password is already 
strong, return 0.
In one step, you can:
1)- Insert one character to password,
2)- Delete one character from password, or
3)- Replace one character of password with another character.
* Eg 1 :  password = "a"                         steps = 5 
* Eg 2 :  paswword = "aA1"                       steps = 3 
* Eg 3 :  password = "abdrFg18"                  steps = 0 
* Eg 4 :  password = "aaacdefbbzzzAAAdrfghilk"   steps = 4 
*/
import java.util.*;
public class Password
{
      public int StrongPassowrdChecker(String password)
      {
            if(password.length() < 6)    // If password length less than 6...
                  return 6 - password.length();
            boolean digitCheck = false, lowerCase = false, upperCase = false;    //* Variable declaration -> O(1)
            int sequenceDigit = 0, sequenceLower = 0, sequenceUpper = 0, steps = 0;  //* Variable declaration -> O(1)
            for(int i = 0; i < password.length(); i++)     //! Checking for Rules -> O(N)
            {
                  if(((int)(password.charAt(i)) >= 48) && ((int)(password.charAt(i)) <= 57))
                        digitCheck = true;     // ASCII code for digit...
                  else if(((int)(password.charAt(i)) >= 65) && ((int)(password.charAt(i)) <= 90))
                        upperCase = true;      // ASCII code for uppercase...
                  else if(((int)(password.charAt(i)) >= 97) && ((int)(password.charAt(i)) <= 122))
                        lowerCase = true;      // ASCII code for lowercase...
            }
            for(int i = 0; i < password.length() - 2; i++)    //! Checking for Sequence -> O(N)
            {
                  if((password.charAt(i) == password.charAt(i + 1)) && (password.charAt(i) == 
                  password.charAt(i + 2)))
                  {     // Checking three consecutive indices...
                        if(((int)(password.charAt(i)) >= 48) && ((int)(password.charAt(i)) <= 57))
                              sequenceDigit++;     // If the sequence is digit...
                        else if(((int)(password.charAt(i)) >= 65) && ((int)(password.charAt(i)) <= 90))
                              sequenceUpper++;     // If the sequence is uppercase...
                        else if(((int)(password.charAt(i)) >= 48) && ((int)(password.charAt(i)) <= 57))
                              sequenceLower++;     // If the sequence is lowercase...
                  }
            }
            if((digitCheck == true) && (lowerCase == true) && (upperCase == true) && (sequenceDigit == 0)
            && (sequenceLower == 0) && (sequenceUpper == 0))
                  return 0;     // When all conditions are satisfied...
            if(password.length() <= 20)
            {     // When password length is between 6 and 20...
                  if(sequenceDigit > 0)   steps += sequenceDigit;
                  if(sequenceLower > 0)   steps += sequenceLower;
                  if(sequenceUpper > 0)   steps += sequenceUpper;
                  if(digitCheck == false) steps += 1;
                  if(lowerCase == false)  steps += 1;
                  if(upperCase == false)  steps += 1;
            }
            else      // When password length is more than 20...
            {
                  int left = (password.length() - 20) - (sequenceDigit + sequenceLower + sequenceUpper);
                  if(left < 0)    // If sequences are left after reducing password length to 20...
                  {
                        if(digitCheck == false)    // Checking for digit, lowercase and uppercase...
                        {steps += 1; left++; digitCheck = true;}
                        if(lowerCase == false)  
                        {steps += 1; left++; lowerCase = true;}
                        if(upperCase == false) 
                        {steps += 1; left++; upperCase = true;}
                  }
                  if(left < 0)    // If still one or more sequence is left...
                        steps -= left;
                  steps += steps + (password.length() - 20);    // Steps when string reduced to length of 20...
                  if(digitCheck == false)
                        steps++;    // Checking for digits, lowercase and uppercase...
                  if(lowerCase == false)
                        steps++;
                  if(upperCase == false)
                        steps++;
            }
            return steps;     // Returing the number of Steps...
      }
      public static void main(String args[])
      {
            Scanner sc = new Scanner(System.in);
            String s;
            System.out.print("Enter the String : ");
            s = sc.next();
            Password password = new Password();       // Object creation...
            System.out.println("Minimum Steps to make Password strong : "+password.
            StrongPassowrdChecker(s));       // Function calling...
            sc.close();
      }
}



//! Time Cmplexity -> O(N)
//* Space Complexity -> O(1)