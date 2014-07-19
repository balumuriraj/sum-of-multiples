import java.util.ArrayList;
import java.util.Scanner;


public class SumMultiples {

	//This function does the sum of all the multiples of a given number and range.
	private int sumofmultiples(int multiple, int maxnumber){
		int n = (int) Math.ceil(maxnumber/multiple); //calculates range of given multiple
		int sum = multiple * (n*(n+1))/2; //sum of multiples
		System.out.println("Sum of Multiples of " + multiple + ": " + sum);
		return sum;
	}
	
	//Checks if the number is prime or not
	private boolean isPrime(int mul){
		//checks if the number is a multiple of 2
		if(mul%2 == 0 && mul != 2){
			return false;
		}
		
		//checks only the odd number
		for(int i=3; i*i <= mul; i=i+2){
			if(mul%i==0){
				return false;
			}
		}
		return true;
	}

	public static void main(String args[]){
		
		//variable declarations
		int sum = 0;
		int submultiple = 1;
		int num = 1;
		int answer = 0;

		SumMultiples s = new SumMultiples();
		Scanner reader = new Scanner(System.in);
		ArrayList<Integer> number = new ArrayList<Integer>();

		//User Input
		System.out.println("Enter the Range Max number:");
		int maxnumber = reader.nextInt();
		System.out.println("Enter the number of numbers you want (should be <=3) :");
		int count = reader.nextInt();
		while(count > 3){
			System.out.println("Enter the number of numbers you want (should be <=3) :");
			count = reader.nextInt();
		}

		while(num != count+1){
			System.out.println("Enter number: ");
			int temp = reader.nextInt();
			if(!s.isPrime(temp)){
				System.out.println("number should be a prime number!");
			} else if(number.contains(temp)){
				System.out.println("number already given!");
			}else{
				number.add(temp);
				num++;
			}
		}
		
		//summation of all the sums of multiples 
		for(int i : number){
			sum = sum + s.sumofmultiples(i, maxnumber-1);
			submultiple = submultiple * i; 
		}
		
		if(count == 1){ //if given multiple is one
			answer = sum;
			System.out.println("answer is: " + answer);
		} 
		else if(count == 2){ //if given multiples are two
			if(submultiple == 1){
				submultiple = 0;
			}
			System.out.println("Substracting sum of common multiples... ");
			answer = sum - s.sumofmultiples(submultiple, maxnumber-1); //Substracting the values which are counted twice
			System.out.println("answer is: " + answer);
		} 
		else { //if given multiples are three
			System.out.println("Substracting sum of common multiples... ");

			for(int i = 0; i<count; i++){
				for(int j=i; j<count; j++){
					if((j+1) < count){
						int subtemp =  number.get(i) * number.get(j+1);
						sum = sum - s.sumofmultiples(subtemp, maxnumber-1); //Substracting the values which are counted twice
					}
				}
			}
			
			if(submultiple == 1){
				submultiple = 0;
			}
			System.out.println("adding values which are completely removed from above substractions...");
			answer = sum + s.sumofmultiples(submultiple, maxnumber-1); //add values which are completely removed from above substractions
			System.out.println("answer is: " + answer);

		}
		reader.close();
	}

}
