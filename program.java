import java.util.*;
import java.io.*;
public class program {
	//Here we arrange the price and goodies to respective postions in form of both price and goodies arrays.
	private static void goodies_arrange(int[] price, String[] goodies) {
		int n=price.length;
		for(int i=0;i<n-1;i++)
		{
			int min_idx=i;
			for(int j=i+1;j<n;j++)
			  if(price[j]<price[min_idx])
				  min_idx=j;

			int temp=price[min_idx];
			price[min_idx]=price[i];
			price[i]=temp;
			
			String temp1=goodies[min_idx];
			goodies[min_idx]=goodies[i];
			goodies[i]=temp1;	
		}
	}
//Finding the index position value  based on the price array and inputted number of employees for the resources to be distributed on.
	private static int find(int[] price,int n) {
		int b=Integer.MAX_VALUE;
		int pos=-1;
		for(int i=0;i<price.length-n+1;i++)
		{
			int n1=price[i+n-1]-price[i];
			if(b>n1)
			{
				pos=i;
				b=n1;
			}
		}
		return pos;
	}
	public static void main(String[] args) throws Exception {
		//having input stream variable sc
		Scanner sc=new Scanner(System.in);
		// path1 for input file(ip) path2 for output file(op) respective to the user system location
		String path1="C:\\programs\\highpeaksw_2021\\input.txt";
		String path2="C:\\programs\\highpeaksw_2021\\output_file.txt";
    //creation of file reader & writer with buffer reader and writer streams.
		 FileReader ip=new FileReader(path1);
		 BufferedReader br=new BufferedReader(ip);
		 FileWriter op=new FileWriter(path2,true);
		 BufferedWriter bw=new BufferedWriter(op);
	//	initialisation of different arrays
		int price[]=new int [10];
		String goodies[]=new String[10];
		String st=br.readLine();
		//distribution of values to arrays price and goodies with limiter (":")
		for(int i=0;i<10;i++)
		{  
			if((st=br.readLine())!=null){
			StringTokenizer stn=new StringTokenizer(st,":");
			String s1=stn.nextToken();
			int p1=Integer.parseInt(stn.nextToken());
			goodies[i]=s1;
			price[i]=p1;
			}
		}
		goodies_arrange(price,goodies);
		//After the arrangement entering the number of employees from the terminal input to the program code.
		System.out.println("Enter the number of Employees");
		int n=sc.nextInt();
		int val=find(price,n);
		//Print of scripted information onto the output file through different streams
		bw.newLine();
		bw.write("Number of Employees "+Integer.toString(n));
		bw.newLine();
		bw.write("Here the goodies that are selected for distribution are :");
		bw.newLine();
		for(int j=val;j<val+n;j++)
		{
			bw.write(goodies[j]+":");
			String str1=Integer.toString(price[j]);
			bw.write(str1);
			bw.newLine();	
		}
		//function to get difference between chosen goodie with highest price and the lowest price
		int d=price[val+n-1]-price[val];
		bw.write("And the difference between the chosen goodie with highest price and the lowest price is "+Integer.toString(d));
		//After writing of the output , with value of computed function we close all the leaking resource streams.
		bw.close();
		br.close();
		sc.close();
	}
}