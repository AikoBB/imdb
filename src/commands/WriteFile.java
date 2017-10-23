package commands;

import java.util.ArrayList;

public class WriteFile {
	private static WriteFile wf = new WriteFile();

	private WriteFile() {}
	
	public static WriteFile getInstance(){
		return wf;
	}
	
	public void writeCommandTitle(String command){
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.println(command);
		System.out.println();
	}
	
	public void noResults(){
		System.out.println("No result");
	}
	
	public void writeResult(String result){
		System.out.println(result);
	}
	
	public void writeResultLn(String result){
		System.out.println(result);
		System.out.println();
	}
	
	public void writeResults(ArrayList obj){
		if(obj.size()>0){
			for (Object object : obj) {
				System.out.println(object);
			}
		}
		else
			noResults();
		System.out.println();
	}

}
