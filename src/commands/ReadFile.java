package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class ReadFile {
	
	public final void read(String file){
		Scanner input = openFile(file);
		if(input != null){
			readData(input);
		}
		input.close();
	}
	
	public Scanner openFile(String file){
		Scanner input;
		try{
			input=new Scanner(new File(file));
			return input;
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Error opening file!");
			return null;
		}
	}
	
	public void readData(Scanner input){
		while(input.hasNext()){
			String line=input.nextLine();
			String [] data=line.split("\\s+");
			processData(data);
		}
	}
	
	public abstract void processData(String [] data);

}
