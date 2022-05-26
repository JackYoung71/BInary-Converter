package Binary;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//This class controls everything: input, conversion, etc.
public class Binary {

	public static String conversionTable = ""
			 + "  1        1      1      1      1     1    1    1"
			+ "\n" + "128     64    32    16     8     4    2    1";

	private int num;
	private boolean binary;
	private String input;
	private String converted;
	private String sum = "";
	private String sumNoZeros = "";
	
	public Binary() {
		//JOptionPane.showMessageDialog(null, "Welcome to the Binary & Integer Converter. \n Rico break my code no balls.");
		Buttons();
		while(true)
			Buttons();
	}

	
	//Buttons shows 4 different buttons to the user, each with different functions.
	public void Buttons() {
		
		String[] options = 
			{ "Close the converter", "Convert numbers & binary", "Convert text & binary", "Learn how to convert"};
		
		int opt = JOptionPane.showOptionDialog(null, "Welcome to the Binary & Integer Converter.", "Rico try to break my code no balls, if you can get an error, I owe you one isopod.",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
		
		if(opt == 1) {
			convert();
		}else if(opt == 2) {
			convertText();
		}else if(opt == 3 ) {
			teach();
		}else if(opt == 0 ) {
			close();
		}
		
	}
	
	//This closes the program.
	public void close() {
		JOptionPane.showMessageDialog(null, "Hope you had a fun time converting stuff");
		System.exit(0);
	}
	
	//This shows a message that tells the user how to use the program.
	public void teach() {
		
		String[] options = 
			{"Convert Something"};
		
		int opt = JOptionPane.showOptionDialog(null, 
				"It is very simple to convert from 8-bit binary to an integer and an integer to 8-bit binary."
				+ "\n" + "First of all, 8-bit binary consists of 8 digits, all either 1s or 0s."
				+ "\n" + "To convert from binary to integer, you use a table like this: "
				+ "\n" + conversionTable
				+ "\n" + "For every 1 in the binary code, you see where that 1 corresponds to in the table, and add up all those numbers."
				+ "\n" + "The resulting number is your integer."
				+ "\n" + "Here is an example: If your binary code is 01101010, we can look at the table above to see what numbers are counted."
				+ "\n" + "The 0s represent numbers not used."
				+ "\n" + "0 + 64 + 32 + 0 + 8 + 0 + 2 + 0"
				+ "\n" + "Adding those numbers up gives us 106!"
				
				
				
				
				, "Click the button below when you are done.",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		
		
			convert();
	}
	
	//This converts the inputted text into binary and the inputted binary into text.
	public void convertText() {
		inputText();
		validText();
		
		sum = "";
		//binary = true means it is a binary
		
		
		if(!binary) { //converting to binary
			
			
			converted = (convertStringToBinary(input));
			
			//System.out.println(converted);
			
			
			String s;
			
			JOptionPane.showMessageDialog(null, 
					 input + " converted to binary is: " + converted
					);

		}else if(binary) { //converting to num
			//add 0s before
			int l = input.length();
			for(int i = 0; i < 8 - l; i++) {
				input = 0 + input;
			}
			
			converted = toText(input) + "";
			
			JOptionPane.showMessageDialog(null, input + " converted to text is: " + converted);
			
			
		}
		
	}
	
	
	//This converts given text to binary.
	public static String convertStringToBinary(String in) {

        StringBuilder result = new StringBuilder();
        char[] chars = in.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
                            .replaceAll(" ", "0")                         // zero pads
            );
        }
        return result.toString();

    }

	//This converts the binary given into "pretty binary," or an easier way of seeing binary.
    public static String prettyBinary(String binary, int blockSize, String separator) {

        List<String> result = new ArrayList<String>();
        int index = 0;
        while (index < binary.length()) {
            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }

        return result.stream().collect(Collectors.joining(separator));
    }
    
    //This gets an inputted text from the user and makes the instance variable equal to that.
	public void inputText() {
		input = JOptionPane.showInputDialog("Give me a character that you want to convert to binary (only 1 character). \n Or you can type in a binary number to convert to a character, which has to between 8 and 1 digits. \n The binary number has to be greater than 32 (and also not 127 for some reason) to be able to show the conversion cause idk binary weird.");
		input = input.replace(" ", "");

		//see if it is valid
		//no numbers (other than 0/1) not nothing, between 1 and 8 characters
		while(input.equalsIgnoreCase("") || input.length() > 8 || input.length() < 1) {
			input = JOptionPane.showInputDialog("That input is not valid. It has to be between 1 and 8 characters long.");
			//get rid of spaces
			input = input.replace(" ", "");
		}
	}
	
	//This sees if the inputted text is either binary or a text
	public void validText() {
		
		if(onlyBinary(input)) { 
			//either
			String[] options = 
				{"I inputted Binary", "I inputted a text"};
			int opt = JOptionPane.showOptionDialog(null, "Since what you inputted could be either a text or binary code, select which one you inputed.", "Choose an option below",
	                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			
			if(opt == 0)
				binary = true;
			if(opt == 1) 
				binary = false;
		
			
		}else if (!onlyBinary(input)) {
			binary = false;
			//valid string
		}
		
		//if the binary inputted is greater than 32
		
		
		
		if(!binary && (input.length() > 1 || input.length() < 1)) {
			
			JOptionPane.showMessageDialog(null, "Your text was too long, remember have it only 1 character long please.");
			inputText();
			validText();
		}
		if(!binary) {
			//good job
		}else if(binary && (((Integer.parseInt(input, 2)) <= 32)) || ((Integer.parseInt(input, 2)) == 127)) {
			String[] options = 
					
				{"Keep what I inputted", "Convert another binary"};
			int opt = JOptionPane.showOptionDialog(null, "The binary you inputted is going to give you a character that you basically can't see cause binary wierd.\nDo you want to try another binary or use the one you already inputted?", "Choose an option below",
	                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			
			if(opt == 1) {
				inputText();
				validText();
			}
		}
		//System.out.println(binary);
	}
	
	
	
	
	
	
	
	
	//This method gets a number from the user that will be converted to binary or a binary that is converted to a number.
	public void input() {

		input = JOptionPane.showInputDialog("Give me an integer you want to convert to binary. \n It has to be between 0 and 255. \n Or you can type in a binary number, which has to between 8 and 1 digits.");
		input = input.replace(" ", "");
		try {
			num = Integer.parseInt(input);
		}
		catch(Exception e){
		}
		
		//see if it is valid
		//only numbers, not nothing, between 1 and 8 characters
		while(!onlyDigits(input) || input.equalsIgnoreCase("") || input.length() > 8 || input.length() < 1) {
			input = JOptionPane.showInputDialog("That number is not valid. It has to be between 0 and 255. \n Or the binary number has to between 8 and 1 digits.");
			//get rid of spaces
			input = input.replace(" ", "");
			try {
				num = Integer.parseInt(input);
			}
			catch(Exception e){
			}
		}
	}
	
	//This gets a new number if the number the user inputted cannot be converted.
	public void inputNumber() {

		input = JOptionPane.showInputDialog("We have detected that you input a number. However, this number is not valid, so please put in a valid number between 0 and 255.");
		input = input.replace(" ", "");
		try {
			num = Integer.parseInt(input);
		}
		catch(Exception e){
		}
		
		//see if it is valid
		//only numbers, not nothing, between 1 and 8 characters
		while(!onlyDigits(input) || input.equalsIgnoreCase("") || input.length() > 8 || input.length() < 1) {
			input = JOptionPane.showInputDialog("That number is not valid. It has to be between 0 and 255. \n Or the binary number has to between 8 and 1 digits.");
			//get rid of spaces
			input = input.replace(" ", "");
			try {
				num = Integer.parseInt(input);
			}
			catch(Exception e){
			}
		}
		num = Integer.parseInt(input);
		valid();
	}
	
	//This sees if the inputted text is a valid binary number or valid number.
	private void valid() {
		if(input.length() > 3 && onlyBinary(input)) { 
			binary = true;
			//valid binary
		}else if (!onlyBinary(input) && input.length() <= 3 && input.length() > 0 && (num < 256 && num >= 0)) {
			
			binary = false;
			//valid number
		}else if (!onlyBinary(input) && (num >= 256 || num < 0)) {
			
			binary = false;
			//number too big, put in a new one
			inputNumber();
		}else if (input.length() <= 3 && input.length() > 0 && onlyBinary(input)) {
			String[] options = 
				{"I inputted Binary", "I inputted a Number"};
			int opt = JOptionPane.showOptionDialog(null, "Since what you inputted could be either a number or binary code, select which one you inputed.", "Choose an option below",
	                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			
			if(opt == 0)
				binary = true;
			if(opt == 1) 
				binary = false;
		
			
		}
		
		
		//System.out.println(binary);
	}
	
	
	//This converts the binary text into a number or vise versa.
	private void convert() {
		input();
		valid();
		
		
		sum = "";
		//binary = true means it is a binary
		
		//System.out.println(binary);
		
		if(!binary) { //converting to binary
			
			converted = (toBinary(num));
			
			
			
			
			sumNoZeros = sum;
			//System.out.println(input);
			if(input.equals("0")) {
				sumNoZeros = "0 = 0";
				
			}else {

				//System.out.println(sumNoZeros);
				
				String s = sumNoZeros.substring(0, (sumNoZeros.length()-(input.length())));
				//System.out.println(s);
				int index = s.indexOf("0");
				
				
				while(index != -1) {
					if(index == 0) {
						s = s.substring(index+4, s.length());
					}else {
						s = s.substring(0, index-2) + s.substring(index+2, s.length());
					}
					index = s.indexOf("0");
					//System.out.println(s + " " + index);
					
				}
				//System.out.println("stop");
				sumNoZeros = s + input;
			}
			
			//System.out.println(converted);
			if(input.length() == 1) {
				sum = sum.substring(0, sum.length()-4);
			}else if(input.length() == 2) {
				sum = sum.substring(0, sum.length()-5);
			}else if(input.length() == 3) {
				sum = sum.substring(0, sum.length()-6);
			}
			
			
			if(num >= 33) {
			String s;
			String[] options = 
				{"Convert to text", "Convert something else"};
			
			
			
			
			
			int opt = JOptionPane.showOptionDialog(null, 
					 "We can convert " + num + " to 8-bit binary using this conversion table: \n"
								+ conversionTable + "\n"
								+ "The numbers in the table that add up to " + num + " are shown below\nThe zeros represent numbers in the table that are not used\n"
								+ sum + "\n"
								+ "When we remove the zeros, we get: \n" + sumNoZeros
								+ "\nThis gives us our final binary code as: " + converted
								+
					"\nWith your new binary, would you like to convert it to text?"
					, "Choose an option below",
	                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			
			if(opt == 0) {
				s = toText(converted) + "";
				
				JOptionPane.showMessageDialog(null, converted + " converted to text is: " + s);
				 
			}
			}else {
				String[] options = 
					{"Convert something else"};
				
				int opt = JOptionPane.showOptionDialog(null, 
						"We can convert " + num + " to 8-bit binary using this conversion table: \n"
								+ conversionTable + "\n"
								+ "The numbers in the table that add up to " + num + " are shown below\nThe zeros represent numbers in the table that are not used\n"
								+ sum + "\n"
								+ "When we remove the zeros, we get: \n" + sumNoZeros
								+ "\nThis gives us our final binary code as: " + converted
						, "Choose an option below",
		                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

				
			}
			
			
			
		}else if(binary) { //converting to num
			//add 0s before
			
			
			
			int l = input.length();
			for(int i = 0; i < 8 - l; i++) {
				input = 0 + input;
			}
			converted = toNumber(input) + "";
			
			
			sumNoZeros = sum;
			//System.out.println(input);
			if(converted.equals("0")) {
				sumNoZeros = "0 = 0";
				
			}else {

				if(sum.substring(0, 2).equals(" +")) {
					sum = sum.substring(3, sum.length());
					sumNoZeros = sum;
				}
				
				//System.out.println(sumNoZeros);
				
				String s = sumNoZeros.substring(0, (sumNoZeros.length()-(converted.length())));
				//System.out.println(s);
				int index = s.indexOf("0");
				
				
				while(index != -1) {
					if(index == 0) {
						s = s.substring(index+4, s.length());
					}else {
						s = s.substring(0, index-2) + s.substring(index+2, s.length());
					}
					index = s.indexOf("0");
					//System.out.println(s + " " + index);
					
				}
				//System.out.println("stop");
				sumNoZeros = s + converted;
			}
			
			//System.out.println(converted);
			if(converted.length() == 1) {
				sum = sum.substring(0, sum.length()-4);
			}else if(converted.length() == 2) {
				sum = sum.substring(0, sum.length()-5);
			}else if(converted.length() == 3) {
				sum = sum.substring(0, sum.length()-6);
			}
			
			JOptionPane.showMessageDialog(null, "We can convert " + input + " to an integer using this conversion table: \n"
					+ conversionTable + "\n"
					+ "The numbers in the table that add up to " + converted + " are shown below\nThe zeros represent numbers in the table that are not used\n"
					+ sum + "\n"
					+ "When we remove the zeros, we get: \n" + sumNoZeros
					+ "\nThis gives us our final number as: " + converted
					);
			
			
		}
		
		
	}
	
	
	//This takes given binary numbers and returns it converted to text.
	public String toText(String input) {
		//String input = "";
		int charCode = Integer.parseInt(input, 2);
		
		@SuppressWarnings("deprecation")
		String str = new Character((char)charCode).toString();
		
		
		//System.out.println(str);
		//JOptionPane.showMessageDialog(null, "The binary " + input + " converted to text is: " + str);
		

		return str;
		
	}
	
	//This returns a binary number converted from a number.
	public String toBinary(int decimal){
		int d = decimal;
	     int binary[] = new int[999];    
	     String ret = "";
	     int index = 1;  
	     int n = 7;
	     String butt = "12345678";
	     //System.out.println(decimal);
	     
	     
	     while(butt.length() > 0){   //for the entire number, until 0  
	    	
	    	if((decimal - (Math.pow(2, n))) >= 0) {
	    		//System.out.println("1");
	    		sum += ((int)Math.pow(2, n)) + " + ";
	    		binary[index-1] = 1;
	    		decimal -= (Math.pow(2, n));
	    		//System.out.println(decimal + " 1");
	    		
	    	}else {
	    		//System.out.print(0 + " ");
	    		//System.out.println("0");
	    		sum += "0 + ";
	    		binary[index-1] = 0;
	    		//System.out.println(decimal + " 2");
	    	}
	       index++;
	       
	       butt = butt.substring(0, n);
	       n--;
	       //System.out.println(butt);
	      
	     } 

	     
	     
	     for(int i = index; i < 8; i++) {
	    	 sum += "p + ";
	    	//binary[i] = 0;
	     }
	     
	     //System.out.println(index);
	     for(int i = 0; i < index-1; i++){    
	       //System.out.print(binary[i]);    
	    	 ret += binary[i] + "";
	    	 
	    	 //System.out.println(ret);
	     }    
	     //System.out.println();//new line 
	     
	     sum = sum.substring(0, sum.length()-2);
	     
	     
		 sum += "= " + d;
		 
		 String temp = ret + "";
		 //System.out.println(ret);
		 for(int i = 0; i < 8-temp.length(); i++) {
			 ret = "0" + ret;
		 }
		 //System.out.println(ret);
	     return ret;
	     
	}  

	//This returns a number converted from binary.
	public String toNumber(String binary){  
		//System.out.println("hi");
	    int decimal = 0;  
	    int n = 0;  
	   
	    int binaryInt = (Integer.parseInt(binary));
	    //System.out.println(binaryInt);
	    boolean onlyZero = (binaryInt == 0);
	    
	    if(!onlyZero) {
	    while(true){  
	      if(binaryInt == 0){  
	        break;  
	      }else {  
	    	 
	          int temp =  binaryInt%10; 
	          decimal += temp*Math.pow(2, n);  
	          sum = " + " + (int)(temp*Math.pow(2, n)) + sum + "";  
	          binaryInt =  binaryInt/10;  
	          n++;  
	          
	          //System.out.println(temp + " " + decimal + " " +  betterBinary + " " + n);
	        
	       }
	    }  
	    
	    String s = "";
	    int i  = 0;
	    //System.out.println(binary);
	   
	    while (binary.substring(i, i+1).equals("0")) {
		   s+= "0 + ";
		   i++;
	    }
	   
	   try{
		   s = s.substring(0, s.length()-3);
	   }catch(Exception e) {
		   //System.out.println("NOOOO");
		   }
		
	   //System.out.println(s);
	   s += sum;
	   sum = s;
	 
	    
	    sum = sum.substring(0, sum.length());
	    
	    
	    sum += " = " + decimal;
	    
	   
	    //sum = sum.substring(3, sum.length());
		   
	    //System.out.println(sum);
	    return decimal + "";  
	    }else {
	    	sum = "0 + 0 + 0 + 0 + 0 + 0 + 0 + 0 = 0";
	    	return "0";
	    }
	    
	}  
	
	
	//This takes a given string and returns true if the given string has only numbers.
	public static boolean onlyDigits(String str)
    {
        // Traverse the string from
        // start to end
        for (int i = 0; i < str.length(); i++) {
  
            // Check if character is
            // digit from 0-9
            // then return true
            // else false
            if (str.charAt(i) >= '0'
                && str.charAt(i) <= '9') {
                //return true;
            }
            else {
                return false;
            }
        }
        return true;
    }
	
	//This takes a given string and returns true if the given string has only 1s and 0s.
	public static boolean onlyBinary(String str)
    {
        // Traverse the string from
        // start to end
        for (int i = 0; i < str.length(); i++) {
  
            // Check if character is
            // digit from 0-9
            // then return true
            // else false
            if (str.charAt(i) == '0'
                || str.charAt(i) == '1') {
                //return true;
            }
            else {
                return false;
            }
        }
        return true;
    }
	
	
	

	
}
