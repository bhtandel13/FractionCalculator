package fracCalc;
import java.util.Scanner;
/*
 * APCS Project 1: FracCalc
 * Name:Bhumi Tandel
 * Period: 2
 * Checkpoint: All
 * Date: 10/4/17
 *
 */
public class FracCalc {

	//creates a scanner that reads user input and prints result 
    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
    	Scanner in = new Scanner(System.in);
    	String problem = in.nextLine();
    	in.close();
    	System.out.printf("Result: %s" , produceAnswer(problem));
    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    
    /*
     * produceAnswer separates the input into the first and second values, 
     * converts these values into fractions if they are not already, 
     * does operations with these fractions to produce a proper or improper fraction as an answer,
     * reduces and simplifies the answer into a proper fraction, whole number, or mixed number 
     */
    public static String produceAnswer(String input)
    { 
        // TODO: Implement this function to produce the solution to the input
		int space = input.indexOf(' ');
        String value1 = input.substring(0, space);
		String value2 = input.substring(space + 3, input.length());
		String operator = input.substring(space, space + 3);
		if (value1.contains("_"))
		{
			String newValue1 = convertMixedNumber(value1);
			if (value2.contains("_"))
			{
				String newValue2 = convertMixedNumber(value2);
				input = newValue1 + operator + newValue2;
			}
			else if (value2.contains("/"))
			{
				String newValue2 = value2;
				input = newValue1 + operator + newValue2;
			}
			else 
			{
				String newValue2 = convertWholeNumber(value2);
				input = newValue1 + operator + newValue2;
			}	
		}
		else if (value1.contains("/"))
		{
			String newValue1 = value1;
			if (value2.contains("_"))
			{
				String newValue2 = convertMixedNumber(value2);
				input = newValue1 + operator + newValue2;
			}
			else if (value2.contains("/"))
			{
				String newValue2 = value2;
				input = newValue1 + operator + newValue2;
			}
			else 
			{
				String newValue2 = convertWholeNumber(value2);
				input = newValue1 + operator + newValue2;
			}	
		}
		else 
		{
			String newValue1 = convertWholeNumber(value1);
			if (value2.contains("_"))
			{
				String newValue2 = convertMixedNumber(value2);
				input = newValue1 + operator + newValue2;
			}
			else if (value2.contains("/"))
			{
				String newValue2 = value2;
				input = newValue1 + operator + newValue2;
			}
			else 
			{
				String newValue2 = convertWholeNumber(value2);
				input = newValue1 + operator + newValue2;
			}	
		}
		if (input.contains(" / "))
		{
		 int slash = input.indexOf('/');
		 int slash2 = input.indexOf('/', slash + 1);
		 int slash3 = input.indexOf('/', slash2 + 1);
		 space = input.indexOf(' ');
		 int num1 = Integer.parseInt(input.substring(0, slash));
		 int denom1 = Integer.parseInt(input.substring(slash + 1, space));
		 int num2 = Integer.parseInt(input.substring(space + 3, slash3));
		 int denom2 = Integer.parseInt(input.substring(slash3 + 1, input.length()));
		 if (num2 < 0)
		 {
			 num2 = num2 * -1;
			 denom2 = denom2 * -1;
			 int numerator = num1 * denom2;
			 int denominator = denom1 * num2;
			 String Answer = numerator + "/" + denominator;
			 return reduceAnswer(Answer);
		 }
		 else
		 {
		 int numerator = num1 * denom2;
		 int denominator = denom1 * num2;
		 String Answer = numerator + "/" + denominator;
		 return reduceAnswer(Answer);
		 }
		}
		else {
		 int slash = input.indexOf('/');
	     int slash2 = input.indexOf('/', slash + 1);
	     space = input.indexOf(' ');
		 int num1 = Integer.parseInt(input.substring(0, slash));
	     int denom1 = Integer.parseInt(input.substring(slash + 1, space));
	     int num2 = Integer.parseInt(input.substring(space + 3, slash2));
	     int denom2 = Integer.parseInt(input.substring(slash2 + 1, input.length()));
	     if (input.contains(" + "))
        {
    	    int numerator1 = num1 * denom2;
    	    int numerator2 = num2 * denom1;
    	    int finalNumerator = numerator1 + numerator2;
    	    int denominator = denom2 * denom1;
    	    String Answer = finalNumerator + "/" + denominator;
    	    return reduceAnswer(Answer);
       		
        }
        else if (input.contains(" - "))
        {
        	int numerator1 = num1 * denom2;
        	int numerator2 = num2 * denom1;
        	int finalNumerator = numerator1 - numerator2;
        	int denominator = denom2 * denom1;
        	String Answer = finalNumerator + "/" + denominator;
        	return reduceAnswer(Answer);
        }
        else if (input.contains(" * "))
        {
        	int numerator = num1 * num2;
        	int denominator = denom1 * denom2;
        	String Answer = numerator + "/" + denominator;
        	return reduceAnswer(Answer);
        }
       else
       {
    	String error = "Error: Invalid user input";
    	return error; 
       }
	}
 }    
    
    // converts mixed numbers entered by the user into improper fractions 
    public static String convertMixedNumber(String originalFraction)
    {
		int slash = originalFraction.indexOf('/');
    	int underscore = originalFraction.indexOf('_');
		if (originalFraction.contains("-"))
		{
			int wholeNumber = Integer.parseInt(originalFraction.substring(1, underscore));
			int num = Integer.parseInt(originalFraction.substring(underscore + 1, slash));
			int denom = Integer.parseInt(originalFraction.substring(slash + 1, originalFraction.length()));
			int num1 = (denom * wholeNumber + num) * -1;
			String improperFraction = num1 + "/" + denom;
			return improperFraction;
		}
		else
		{
		int wholeNumber = Integer.parseInt(originalFraction.substring(0, underscore));
		int num = Integer.parseInt(originalFraction.substring(underscore + 1, slash));
		int denom = Integer.parseInt(originalFraction.substring(slash + 1, originalFraction.length()));
		int num1 = (denom * wholeNumber) + num;
		String improperFraction = num1 + "/" + denom;
		return improperFraction;
		}
    }
    
    //turns whole numbers entered by users into fractions by placing them over 1
    public static String convertWholeNumber(String originalNumber)
    {
    	String finalFraction = originalNumber + "/1";
    	return finalFraction;
    }
  
    //checks for special cases such as dividing by 0 & when the numerator equals 0
    //reduces fractions by converting them into mixed numbers & then simplifying the remaining fraction
    public static String reduceAnswer(String Answer)
    {
    	int slash = Answer.indexOf('/');
    	int num = Integer.parseInt(Answer.substring(0, slash));
    	int denom = Integer.parseInt(Answer.substring(slash + 1, Answer.length()));
    	if(denom == 0)
    	{
    		String error = "Error: cannot divide by 0";
    		return error;
    	}
    	else if(num == 0)
    	{
    		return Integer.toString(0);
    	}
    	else if (denom == 1)
    	{
    		return Integer.toString(num);	
    	
    	}
    	else
    	{
    		int wholeNumber = num / denom; 
    		int newNum = num % denom;
    		String mixedAnswer = wholeNumber + "_" + newNum + "/" + denom;
    		int underscore = mixedAnswer.indexOf('_');
    		slash = mixedAnswer.indexOf('/');
    		wholeNumber = Integer.parseInt(mixedAnswer.substring(0, underscore));
    		num = Integer.parseInt(mixedAnswer.substring(underscore + 1, slash));
        	denom = Integer.parseInt(mixedAnswer.substring(slash + 1, mixedAnswer.length()));
    		if (mixedAnswer.contains("_-") && (wholeNumber < 0))
    		{
    			newNum = Integer.parseInt(mixedAnswer.substring(underscore + 2, slash));
    			mixedAnswer = wholeNumber + "_" + newNum + "/" + denom;
    		}
        	if(wholeNumber == 0)
    		{
    			String finalAnswer = reduceFraction(mixedAnswer);
    			return finalAnswer; 
    		}
    		else if (num == 0)
    		{
    			return Integer.toString(wholeNumber);
    		}
    		else
    		{
    		String finalAnswer = wholeNumber + "_" + reduceFraction(mixedAnswer);
        	return finalAnswer;
    		}
    	}
    }
    
  //finds greatest common factor(GCF) of numerator and denominator 
    public static int findGCF(int num, int denom)
    {
    	if (denom == 0)
    	{
    		return num;
    	}
    	return findGCF(denom, num % denom);
    }
    
    //simplifies fraction part of whole numbers by dividing both parts by the GCF
    public static String reduceFraction(String mixedAnswer)
    {
    	int underscore = mixedAnswer.indexOf('_');
    	int slash = mixedAnswer.indexOf('/');
    	int num = Integer.parseInt(mixedAnswer.substring(underscore + 1, slash));
    	int denom = Integer.parseInt(mixedAnswer.substring(slash + 1, mixedAnswer.length()));
    	int newNum = num / findGCF(num, denom);
    	int newDenom = denom / findGCF(num, denom);
    	String reducedFraction = newNum + "/" + newDenom;
    	return reducedFraction; 
    }
    
    // TODO: Fill in the space below with any helper methods that you think you will need   
}
