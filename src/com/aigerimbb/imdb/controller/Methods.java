package com.aigerimbb.imdb.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.aigerimbb.imdb.dao.films.Documentary;
import com.aigerimbb.imdb.dao.films.FeatureFilm;
import com.aigerimbb.imdb.dao.films.ShortFilm;
import com.aigerimbb.imdb.dao.films.TVSeries;
import com.aigerimbb.imdb.dao.films.UserIdRate;
import com.aigerimbb.imdb.dao.person.Actor;
import com.aigerimbb.imdb.dao.person.Actress;
import com.aigerimbb.imdb.dao.person.Director;
import com.aigerimbb.imdb.dao.person.RatingsOfUser;
import com.aigerimbb.imdb.dao.person.User;
import com.aigerimbb.imdb.dao.person.Writer;
//in this class there was created methods for executing commands
//also for opening files from console
public class Methods {

	//this method for opening arguments
	public Scanner openFile(String args)
	{
		Scanner input;
		try{
			input=new Scanner(new File(args));
			return input;
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Error opening file!");
			System.exit(1);
			return null;
		}
	}
	
	//this method for reading people data
	public void peopleRead(Scanner input,ArrayList<Actor>actor,ArrayList<Actress>actress,ArrayList<Director>director,ArrayList<User>user,ArrayList<Writer>writer)
	{
		while(input.hasNext())
		{
			String line=input.nextLine();//reads line
			String [] separation=line.split("\\s+");//separate them
			if(separation[0].equalsIgnoreCase("actor:"))//if separated string's first index is equal to actor
			{
				actor.add(new Actor(separation[1],separation[2],separation[3],separation[4],separation[5]));//add actor with his data
			}
			else if(separation[0].equalsIgnoreCase("actress:"))//if separated string's first index is equal to actress
			{
				actress.add(new Actress(separation[1],separation[2],separation[3],separation[4]));//add actress with data
			}
			
			else if(separation[0].equalsIgnoreCase("director:"))//if separated string's first index is equal to director
			{
				director.add(new Director(separation[1],separation[2],separation[3],separation[4],separation[5]));//add director with his data
			}
			
			else if(separation[0].equalsIgnoreCase("writer:"))//if separated string's first index is equal to writer
			{
				writer.add(new Writer(separation[1],separation[2],separation[3],separation[4],separation[5]));//add writer with his data
			}
			
			else if(separation[0].equalsIgnoreCase("user:"))//if separated string's first index is  equal to user
			{
				user.add(new User(separation[1],separation[2],separation[3],separation[4]));//add user with his data
			}
		}
	}

	//this method for reading films' data
	public void filmRead(Scanner input,ArrayList<Documentary>doc,ArrayList<FeatureFilm> fFilm, ArrayList<ShortFilm>sFilm,ArrayList<TVSeries> tvs,ArrayList<String> filmIds)
	{
		while(input.hasNext())
		{
			String line=input.nextLine();//reads by line
			String [] separation=line.split("\\s+");//separate the taken line from txt file
			filmIds.add(separation[1]);
			 if(separation[0].equalsIgnoreCase("FeatureFilm:"))//if separated string's first index  equals
			{
				fFilm.add(new FeatureFilm(separation[1],separation[2],separation[3],separation[4],separation[5],separation[6],separation[7],separation[8],separation[9],separation[10],separation[11]));//add feature film
			}
			 
			 else  if(separation[0].equalsIgnoreCase("ShortFilm:"))//if separated string's first index  equals
				{
					sFilm.add(new ShortFilm(separation[1],separation[2],separation[3],separation[4],separation[5],separation[6],separation[7],separation[8],separation[9],separation[10]));//add short film
				}
			 else if(separation[0].equalsIgnoreCase("TVSeries:"))////if separated string's first index  equals
				{
					tvs.add(new TVSeries(separation[1],separation[2],separation[3],separation[4],separation[5],separation[6],separation[7],separation[8],separation[9],separation[10],separation[11],separation[12],separation[13]));//add tv series
				} 
			 
			 else if(separation[0].equalsIgnoreCase("Documentary:"))////if separated string's first index  equals
				{
					doc.add(new Documentary(separation[1],separation[2],separation[3],separation[4],separation[5],separation[6],separation[7],separation[8]));//add documentary
				} 
		}
	}
	
	//this method reads commands and execute this commands by calling methods
	public void commands(Scanner input,PrintWriter output,ArrayList<Actor>actor,ArrayList<Actress>actress,ArrayList<Director>director,ArrayList<User>user,ArrayList<Writer>writer,ArrayList<Documentary>doc,ArrayList<FeatureFilm> fFilm, ArrayList<ShortFilm>sFilm,ArrayList<TVSeries> tvs,ArrayList<String> filmIds)
	{
		while(input.hasNext())
		{
			String line=input.nextLine();
			String [] seperation=line.split("\\s+");
			if(seperation[0].equalsIgnoreCase("RATE"))////if separated string's first index  equals
			{
				commandRate( line,doc, fFilm, sFilm, tvs,user, output);//call metod
			}
			
			if(seperation[0].equalsIgnoreCase("ADD"))//if separated string's first index  equals
			{
				commandAddFeatureFilm(line,output,fFilm,filmIds,actor,actress,director,writer);//call method
			}
			
			else if(seperation[0].equalsIgnoreCase("VIEWFILM"))//if separated string's first index  equals
			{
				commandViewFilm(line, output,doc, fFilm, sFilm,tvs,actor,actress,director,writer);//call method
			}
			
			else if(seperation[0].equalsIgnoreCase("List")&&seperation[1].equalsIgnoreCase("user"))//if separated string's first index  equals
			{
				commandListUserRate (line ,  doc ,  fFilm ,  sFilm , tvs ,  user , output);//call method
			}
			
			else if(seperation[0].equalsIgnoreCase("Edit"))//if separated string's first index  equals
			{
				commandEditUserRate (line , doc , fFilm , sFilm , tvs ,  user ,output);//call method
			}
			
			else if(seperation[0].equalsIgnoreCase("Remove"))////if separated string's first index  equals
			{
				commandRemoveRate( output, line, user, doc ,  fFilm , sFilm , tvs);//call method
			}
			
			else if(seperation[0].equalsIgnoreCase("List")&&seperation[1].equalsIgnoreCase("film")&&seperation[2].equalsIgnoreCase("series"))
			{
				commandListTVSeries ( output , tvs);//call method
				
			}
			else if(seperation[0].equalsIgnoreCase("List")&&seperation[1].equalsIgnoreCase("films")&&seperation[2].equalsIgnoreCase("by")&&seperation[3].equalsIgnoreCase("rate"))
			{
				commandListFilmsByRate(line, doc ,fFilm , sFilm , tvs, output );
			}
			
			else if(seperation[0].equalsIgnoreCase("List")&&seperation[1].equalsIgnoreCase("featurefilms")&&seperation[2].equalsIgnoreCase("before"))
			{
				commandListFeatureBefore(line, fFilm, output);
			}
			
			else if(seperation[0].equalsIgnoreCase("List")&&seperation[1].equalsIgnoreCase("featurefilms")&&seperation[2].equalsIgnoreCase("after"))
			{
				commandListFeatureAfter(line, fFilm, output);
			}
			
			else if(seperation[0].equalsIgnoreCase("List")&&seperation[1].equalsIgnoreCase("films")&&seperation[2].equalsIgnoreCase("by")&&seperation[3].equalsIgnoreCase("country"))
			{
				commandListFilmByCountry ( line,doc, fFilm, sFilm, tvs, output ,  filmIds);
			}
			
			else if(seperation[0].equalsIgnoreCase("List")&&seperation[1].equalsIgnoreCase("artists"))
			{
				commandListArtistsFrom(line,output,actor,actress,director,user,writer);
			}
		}
	}

	//for executing command add feature film
	public void commandAddFeatureFilm(String line,PrintWriter output,ArrayList<FeatureFilm> f, ArrayList<String> filmIds,ArrayList<Actor> actor , ArrayList<Actress> actress , ArrayList<Director> director, ArrayList<Writer> writer)
	{
		boolean is=true;
		int bir = 0;//for control whether "if" functions is true or not
		int iki = 0;//if it is true so this counters are added by one
		int uc = 0;
		int dort = 0;
		
		String [] separation=line.split("\\s+"); 
		String [] dIds = separation[5].split(",");
		String [] pIds = separation[8].split(",");
		String [] wIds = separation[11].split(",");
		if(!(separation.length>=13))//if there is not writer's id or director's id program prints out command failed
		{
			output.println("Command Failed");
			output.println("Film ID:"+" "+separation[2]+"\n"+"Film title:"+" "+separation[3]);
		}
		else
		{
		   	for(int i=0;i<f.size();i++)
		   	{
		   		if(separation[2].equalsIgnoreCase(f.get(i).getId()))//if the feature's film id is equal to given id in the command program prints out command failed
		   		{
		   			output.println(line+"\n");
		   			output.println("Command Failed");
		   			output.println("Film ID:"+" "+separation[2]+"\n"+"Film title:"+" "+separation[3]+"\n");
		   			output.println("-----------------------------------------------------------------------------------------------------");
		   			is=false;//bollean is used for controlling if the feature's film id is equal to given id in the command or not
		   			break;
		   		}
		   	}
		   	if (is)//if boolean is is equal to true program must control if there is writer/director/actor/actress with the same id
		   	{
		   		for (int j = 0 ; j<director.size() ; j++)
		   		{
		   			for (int k = 0; k<dIds.length ; k++)
		   			{
		   				if (dIds[k].equalsIgnoreCase(director.get(j).getId()))
		   				{
		   					bir++;
		   					break;
		   				}
		   			}
		   		}
		   		for (int j = 0 ; j<writer.size() ; j++)
		   		{
		   			for (int k = 0; k<wIds.length ; k++)
		   			{
		   				if (writer.get(j).getId().equalsIgnoreCase(wIds[k]))
		   				{
		   					iki++;
		   					break;
		   				}
		   			}
		   		}
		   		for (int j = 0 ; j<actor.size() ; j++)
		   		{
		   			for (int k = 0; k<pIds.length ; k++)
		   			{
		   				if (actor.get(j).getId().equalsIgnoreCase(pIds[k]))
		   				{
		   					uc++;
		   					break;
		   				}
		   			}
		   		}	
		   		for (int j = 0 ; j<actress.size() ; j++)
		   		{
		   			for (int k = 0; k<pIds.length ; k++)
		   			{
		   				if (actress.get(j).getId().equalsIgnoreCase(pIds[k]))
		   				{
		   					dort++;
		   					break;
		   				}
		   			}
		   		}
		   		if(is && (bir==dIds.length) && (iki==wIds.length) && ((uc+dort)==pIds.length))
		   		{
		   			filmIds.add(separation[2]);
		   			f.add(new FeatureFilm(separation[2],separation[3],separation[4],separation[5],separation[6],separation[7],separation[8],separation[9],separation[10],separation[11],separation[12]));
		   			output.println(line+"\n");
		   			output.println("FeatureFilm added successfully");
		   			output.println("Film ID:"+" "+separation[2]+"\n"+"Film title:"+" "+separation[3]+"\n");
		   			output.println("-----------------------------------------------------------------------------------------------------");
		   		}
		   		else 
		   		{
		   			output.println(line+"\n");
		   			output.println("Command Failed");
		   			output.println("Film ID:"+" "+separation[2]+"\n"+"Film title:"+" "+separation[3]+"\n");
		   			output.println("-----------------------------------------------------------------------------------------------------");
		   		}
		   	}
		}
	}

	//for executing command Rate
	public void commandRate(String line,ArrayList<Documentary>doc,ArrayList<FeatureFilm> fFilm, ArrayList<ShortFilm>sFilm,ArrayList<TVSeries> tvs,ArrayList<User>user,PrintWriter output)
	{
		output.println(line+"\n");
		String [] separation=line.split("\\s+");
		int control=0;//control for whether function if true or not
		ter:for(int i=0;i<user.size();i++)
		{
			if(user.get(i).getId().equalsIgnoreCase(separation[1]))
			{
				for(int j=0;j<fFilm.size();j++)
				{
					if(separation[2].equalsIgnoreCase(fFilm.get(j).getId()))
					{
						if(Integer.parseInt(separation[3])>=1&&Integer.parseInt(separation[3])<=10)
						{
							int control1=0;
							for(int u=0;u<user.get(i).rates.size();u++)
								
							if(user.get(i).rates.get(u).getFilmId().equalsIgnoreCase(separation[2]))
							{
								output.println("This film was earlier rated"+"\n");
								output.println("-----------------------------------------------------------------------------------------------------");
								control1++; control++;
								break ter;
							}
							if(control1==0){
							user.get(i).rates.add(new RatingsOfUser(Integer.parseInt(separation[3]),separation[2] ));
							fFilm.get(j).uir.add(new UserIdRate(separation[1],Integer.parseInt(separation[3])));
							output.println("Film rated successfully"+"\n"+"Film type: "+"FeatureFilm\n"+"Film title: "+fFilm.get(j).getTitle()+"\n");
							output.println("-----------------------------------------------------------------------------------------------------");
							control++;
							break ter;
							}
						}
					}
				}
				if(control==0)
				for(int j=0;j<sFilm.size();j++)
				{
					if(separation[2].equalsIgnoreCase(sFilm.get(j).getId()))
					{
						if(Integer.parseInt(separation[3])>=1&&Integer.parseInt(separation[3])<=10)
						{
							int control1=0;
							for(int u=0;u<user.get(i).rates.size();u++)
								
							if(user.get(i).rates.get(u).getFilmId().equalsIgnoreCase(separation[2]))
							{
								output.println("This film was earlier rated"+"\n");
								output.println("-----------------------------------------------------------------------------------------------------");
								control1++; control++;
								break ter;
							}
							if(control1==0){
							user.get(i).rates.add(new RatingsOfUser(Integer.parseInt(separation[3]),separation[2] ));
							sFilm.get(j).uir.add(new UserIdRate(separation[1],Integer.parseInt(separation[3])));
							output.println("Film rated successfully"+"\n"+"Film type: "+"ShortFilm\n"+"Film title: "+sFilm.get(j).getTitle()+"\n");
							output.println("-----------------------------------------------------------------------------------------------------");
							control++;
							break ter;
							}
						}
					}
				}
				if(control==0)
				for(int j=0;j<doc.size();j++)
				{
					if(separation[2].equalsIgnoreCase(doc.get(j).getId()))
					{
						if(Integer.parseInt(separation[3])>=1&&Integer.parseInt(separation[3])<=10)
						{
							int control1=0;
							for(int u=0;u<user.get(i).rates.size();u++)
								
							if(user.get(i).rates.get(u).getFilmId().equalsIgnoreCase(separation[2]))
							{
								output.println("This film was earlier rated"+"\n");
								output.println("-----------------------------------------------------------------------------------------------------");
								control1++; control++;
								break ter;
							}
							if(control1==0){
							user.get(i).rates.add(new RatingsOfUser(Integer.parseInt(separation[3]),separation[2] ));
							doc.get(j).uir.add(new UserIdRate(separation[1],Integer.parseInt(separation[3])));
							output.println("Film rated successfully"+"\n"+"Film type: "+"Documentary\n"+"Film title: "+doc.get(j).getTitle()+"\n");
							output.println("-----------------------------------------------------------------------------------------------------");
							control++;
							break ter;
							}
						}
					}
				}
				if(control==0)
				for(int j=0;j<tvs.size();j++)
				{
					if(separation[2].equalsIgnoreCase(tvs.get(j).getId()))
					{
						if(Integer.parseInt(separation[3])>=1&&Integer.parseInt(separation[3])<=10)
						{
							int control1=0;
							for(int u=0;u<user.get(i).rates.size();u++)
							{
							if(user.get(i).rates.get(u).getFilmId().equalsIgnoreCase(separation[2]))
							{
								output.println("This film was earlier rated"+"\n");
								output.println("-----------------------------------------------------------------------------------------------------");
								control1++; control++;
								break ter;
							}}
							if(control1==0){
							user.get(i).rates.add(new RatingsOfUser(Integer.parseInt(separation[3]),separation[2] ));
							tvs.get(j).uir.add(new UserIdRate(separation[1],Integer.parseInt(separation[3])));
							output.println("Film rated successfully"+"\n"+"Film type: "+"TVSeries\n"+"Film title: "+tvs.get(j).getTitle()+"\n");
							output.println("-----------------------------------------------------------------------------------------------------");
							control++;
							break ter;
							}
						}
					}
				}
			}
		}
		if(control==0)
		{
			output.println("Command Failed\n"+"User ID: "+separation[1]+"\nFilm ID: "+separation[2]+"\n");
			output.println("-----------------------------------------------------------------------------------------------------");
		}
	}

	
	//for executing command view film
	public void commandViewFilm(String line,PrintWriter output,ArrayList<Documentary>doc,ArrayList<FeatureFilm> fFilm, ArrayList<ShortFilm>sFilm,ArrayList<TVSeries> tvs,ArrayList<Actor>actor,ArrayList<Actress>actress,ArrayList<Director>director,ArrayList<Writer>writer)
	{
		output.println(line+"\n");
		DecimalFormat fmt=new DecimalFormat("0.#");//this function used for writing decimals with comma
		String []separation=line.split("\\s+");
		int control=0;
		ter:for(int i=0;i<fFilm.size();i++)
		{
			if(separation[1].equalsIgnoreCase(fFilm.get(i).getId()))
				
			{
				String [] split=fFilm.get(i).getGenre().split(",");
				String tostr=new String();
				for(int y=0;y<split.length;y++)
				{
					tostr+=split[y];
					if(y!=split.length-1)
						tostr+=", ";
				}
				output.println(fFilm.get(i).getTitle()+" ("+ fFilm.get(i).getReleaseDate().substring(6, 10)+")"+"\n"+tostr);
				String wIds=fFilm.get(i).getWriters();
				String [] wId=wIds.split(",");
				for(int j=0;j<wId.length;j++)
				{ 
					if(j==0) output.print("Writers: ");
				   for(int u=0;u<writer.size();u++)
				   {
					   if(writer.get(u).getId().equalsIgnoreCase(wId[j]))
					   {
						   
						  output.print(writer.get(u).getName()+" "+writer.get(u).getSurname());   
						  if(u!=0&&j!=wId.length-1) output.print(", ");
					   }
				   }
				}
		
				String dIds=fFilm.get(i).getDirectorIds();
				String [] dId=dIds.split(",");
				for(int j=0;j<dId.length;j++)
				{ 
					if(j==0) output.print("\nDirectors: ");
				   for(int u=0;u<director.size();u++)
				   {
					   if(director.get(u).getId().equalsIgnoreCase(dId[j]))
					   {
						  
						  output.println(director.get(u).getName()+" "+director.get(u).getSurname()); 
						  if(u!=0&&j!=dId.length-1) output.print(", ");
					   }
				   }
				}
		
				String sIds=fFilm.get(i).getCastIds();
				String [] sId=sIds.split(",");
				for(int j=0;j<sId.length;j++)
				{ 
					if(j==0) output.print("Stars: ");
				   for(int u=0;u<actor.size()||u<actress.size();u++)
				   {
					 
					   {
						   
						   if(u<actor.size())
						   {
							   if(actor.get(u).getId().equalsIgnoreCase(sId[j])){
						  output.print(actor.get(u).getName()+" "+actor.get(u).getSurname());
						  if(u!=0&&j!=sId.length-1) output.print(", ");}
						   }
						   if(u<actress.size())
						   {
							   if(actress.get(u).getId().equalsIgnoreCase(sId[j])){
								  output.print(actress.get(u).getName()+" "+actress.get(u).getSurname());
								  if(u!=0&&j!=sId.length-1) output.print(", "); }
						   }
						   
					   }
				   }
				}
				double rate; int sum=0;
				for(int k=0;k<fFilm.get(i).uir.size();k++)
				{
					sum+=fFilm.get(i).uir.get(k).getRate();
					
				}
				if(fFilm.get(i).uir.size()==0)
	        	{output.println("\nAwaiting for votes");
	        	output.println("\n-----------------------------------------------------------------------------------------------------");
	        	control++;
		        break ter;
	        	}
				rate=sum/(double)fFilm.get(i).uir.size();
		        
		        
		        	{
		        	
		        	if(Math.ceil(rate)==0)
		        	{
		        		output.println("\nRatings: "+(int)rate+"/10 from "+fFilm.get(i).uir.size()+" users");
			        	output.println("\n-----------------------------------------------------------------------------------------------------");
		        	}
		        	else{
					output.println("\nRatings: "+fmt.format(rate)+"/10 from "+fFilm.get(i).uir.size()+" users");
		        	output.println("\n-----------------------------------------------------------------------------------------------------");
		        	}	}
		        control++;
		        break ter;
			}
		}
		
		if(control==0)
		ter:for(int i=0;i<sFilm.size();i++)
		{
			if(separation[1].equalsIgnoreCase(sFilm.get(i).getId()))
			{
				String [] split=sFilm.get(i).getGenre().split(",");
				String tostr=new String();
				for(int y=0;y<split.length;y++)
				{
					tostr+=split[y];
					if(y!=split.length-1)
						tostr+=", ";
				}
				output.println(sFilm.get(i).getTitle()+" ("+ sFilm.get(i).getReleaseDate().substring(6, 10)+")"+"\n"+tostr);
				String wIds=sFilm.get(i).getWriters();
				String [] wId=wIds.split(",");
				for(int j=0;j<wId.length;j++)
				{ 
					if(j==0) output.print("Writers: ");
				   for(int u=0;u<writer.size();u++)
				   {
					   if(writer.get(u).getId().equalsIgnoreCase(wId[i]))
					   {
						  output.print(writer.get(u).getName()+" "+writer.get(u).getSurname());  
						  if(u!=0&&j!=wId.length-1) output.print(", ");
					   }
				   }
				}
		
				String dIds=sFilm.get(i).getDirectorIds();
				String [] dId=dIds.split(",");
				for(int j=0;j<dId.length;j++)
				{ 
					if(j==0) output.print("\nDirectors: ");
				   for(int u=0;u<director.size();u++)
				   {
					   if(dId[i].equalsIgnoreCase(director.get(u).getId()))
					   {
						  output.print(director.get(u).getName()+" "+director.get(u).getSurname());  
						  if(u!=0&&j!=dId.length-1) output.print(", ");
					   }
				   }
				}
		
				String sIds=sFilm.get(i).getCastIds();
				String [] sId=sIds.split(",");
				for(int j=0;j<sId.length;j++)
				{ 
					if(j==0) output.print("\nStars: ");
				   for(int u=0;u<actor.size()||u<actress.size();u++)
				   {
	
					   if(u<actor.size())
					   {
						   if(sId[j].equalsIgnoreCase(actor.get(u).getId())){
					  output.print(actor.get(u).getName()+" "+actor.get(u).getSurname());
					  if(u!=0&&j!=sId.length-1) output.print(", ");}
					   }
					   if(u<actress.size())
					   {
						   if(sId[j].equalsIgnoreCase(actress.get(u).getId())){
							  output.print(actress.get(u).getName()+" "+actress.get(u).getSurname());
							  if(u!=0&&j!=sId.length-1) output.print(", "); }
					   }
				   }
				}
				
				double rate; int sum=0;
				for(int k=0;k<sFilm.get(i).uir.size();k++)
				{
					sum+=sFilm.get(i).uir.get(k).getRate();
					
				}
				if(sFilm.get(i).uir.size()==0)
	        	{output.println("\nAwaiting for votes");
	        	output.println("\n-----------------------------------------------------------------------------------------------------");
	        	control++;
		        break ter;
	        	}
				rate=sum/(double)fFilm.get(i).uir.size();
		        
		        
		        	{
		        	
		        	if(Math.ceil(rate)==0)
		        	{
		        		output.println("\nRatings: "+(int)rate+"/10 from "+sFilm.get(i).uir.size()+" users");
			        	output.println("\n-----------------------------------------------------------------------------------------------------");
		        	}
		        	else{
					output.println("\nRatings: "+fmt.format(rate)+"/10 from "+sFilm.get(i).uir.size()+" users");
		        	output.println("\n-----------------------------------------------------------------------------------------------------");
		        	}	}
		        control++;
		        break ter;
		        
			}
		}
		
		if(control==0)
		ter:for(int i=0;i<doc.size();i++)
		{
			if(separation[1].equalsIgnoreCase(doc.get(i).getId()))
			{
				output.println(doc.get(i).getTitle()+" ("+ doc.get(i).getReleaseDate().substring(6, 10)+")");
				
				
				String dIds=doc.get(i).getDirectorIds();
				String [] dId=dIds.split(",");
				for(int j=0;j<dId.length;j++)
				{ 
					if(j==0) output.print("\nDirectors: ");
				   for(int u=0;u<director.size();u++)
				   {
					   if(dId[j].equalsIgnoreCase(director.get(u).getId()))
					   {
						   
						  output.print(director.get(u).getName()+" "+director.get(u).getSurname()); 
						  if(u!=0&&j!=dId.length-1) output.print(", ");
					   }
				   }
				}
		
				String sIds=doc.get(i).getCastIds();
				String [] sId=sIds.split(",");
				for(int j=0;j<sId.length;j++)
				{ 
					if(j==0) output.print("\nStars: ");
				   for(int u=0;u<actor.size()||u<actress.size();u++)
				   {
					   if(u<actor.size())
					   {
						   if(sId[j].equalsIgnoreCase(actor.get(u).getId())){
					  output.print(actor.get(u).getName()+" "+actor.get(u).getSurname());
					  if(u!=0&&j!=sId.length-1) output.print(", ");}
					   }
					   if(u<actress.size())
					   {
						   if(sId[j].equalsIgnoreCase(actress.get(u).getId())){
							  output.print(actress.get(u).getName()+" "+actress.get(u).getSurname());
							  if(u!=0&&j!=sId.length-1) output.print(", "); }
					   }
				   }
				}
				
				double rate; int sum=0;
				for(int k=0;k<doc.get(i).uir.size();k++)
				{
					sum+=doc.get(i).uir.get(k).getRate();
					
				}
				if(doc.get(i).uir.size()==0)
	        	{output.println("\nAwaiting for votes");
	        	output.println("\n-----------------------------------------------------------------------------------------------------");
	        	control++;
		        break ter;
	        	}
				rate=sum/(double)doc.get(i).uir.size();
		        
		        
		        	{
		        	
		        	if(Math.ceil(rate)==0)
		        	{
		        		output.println("\nRatings: "+(int)rate+"/10 from "+doc.get(i).uir.size()+" users");
			        	output.println("\n-----------------------------------------------------------------------------------------------------");
		        	}
		        	else{
					output.println("\nRatings: "+fmt.format(rate)+"/10 from "+doc.get(i).uir.size()+" users");
		        	output.println("\n-----------------------------------------------------------------------------------------------------");
		        	}	}
		        control++;
		        break ter;
			}
		}
		if(control==0)
		ter:for(int i=0;i<tvs.size();i++)
		{
			if(separation[1].equalsIgnoreCase(tvs.get(i).getId()))
			{
				String [] split=tvs.get(i).getGenre().split(",");
				String tostr=new String();
				for(int y=0;y<split.length;y++)
				{
					tostr+=split[y];
					if(y!=split.length-1)
						tostr+=", ";
				}
				output.println(tvs.get(i).getTitle()+" ("+ tvs.get(i).getStartDate().substring(6, 10)+"-"+tvs.get(i).getEndDate().substring(6, 10)+")"+"\n"+tvs.get(i).getSeason()+" seasons, "+tvs.get(i).getEpisodes()+" episodes\n"+tostr);
				String wIds=tvs.get(i).getWriters();
				String [] wId=wIds.split(",");
				for(int j=0;j<wId.length;j++)
				{ 
					if(j==0) output.print("Writers: ");
				   for(int u=0;u<writer.size();u++)
				   {
					   if(wId[j].equalsIgnoreCase(writer.get(u).getId()))
					   {
						   
						  output.print(writer.get(u).getName()+" "+writer.get(u).getSurname()); 
						  if(u!=0&&j!=wId.length-1) output.print(", ");
					   }
				   }
				}
		
				String dIds=tvs.get(i).getDirectorIds();
				String [] dId=dIds.split(",");
				for(int j=0;j<dId.length;j++)
				{ 
					if(j==0) output.print("\nDirectors: ");
				   for(int u=0;u<director.size()-1;u++)
				   {
					   if(dId[j].equalsIgnoreCase(director.get(u).getId()))
					   {
						  output.print(director.get(u).getName()+" "+director.get(u).getSurname());   
						  if(u!=0&&j!=dId.length-1) output.print(", ");
					   }
				   }
				}
		
				String sIds=tvs.get(i).getCastIds();
				String [] sId=sIds.split(",");
				for(int j=0;j<sId.length;j++)
				{ 
					if(j==0) output.print("\nStars: ");
				   for(int u=0;u<actor.size()||u<actress.size();u++)
				   {
					   if(u<actor.size())
					   {
						   if(sId[j].equalsIgnoreCase(actor.get(u).getId())){
					  output.print(actor.get(u).getName()+" "+actor.get(u).getSurname());
					  if(u!=0&&j!=sId.length-1) output.print(", ");}
					   }
					   if(u<actress.size())
					   {
						   if(sId[j].equalsIgnoreCase(actress.get(u).getId())){
							  output.print(actress.get(u).getName()+" "+actress.get(u).getSurname());
							  if(u!=0&&j!=sId.length-1) output.print(", "); }
					   }
				   }
				}
				double rate=0; int sum=0;
				for(int k=0;k<tvs.get(i).uir.size();k++)
				{
					sum+=tvs.get(i).uir.get(k).getRate();
					
				}
				if(tvs.get(i).uir.size()==0)
	        	{output.println("\nAwaiting for votes");
	        	output.println("\n-----------------------------------------------------------------------------------------------------");
	        	control++;
		        break ter;
	        	}
				rate=sum/(double)tvs.get(i).uir.size();
		        	{
		        	
		        	if(Math.ceil(rate)==0)
		        	{
		        		output.println("\nRatings: "+(int)rate+"/10 from "+tvs.get(i).uir.size()+" users");
			        	output.println("\n-----------------------------------------------------------------------------------------------------");
		        	}
		        	else{
					output.println("\nRatings: "+fmt.format(rate)+"/10 from "+tvs.get(i).uir.size()+" users");
		        	output.println("\n-----------------------------------------------------------------------------------------------------");
		        	}	}
		        control++;
		        sum=0;rate=0;
		        break ter;
			}
		}
		
		if(control==0)
		{
			output.println("Command Failed\n"+"Film ID: "+separation[1]);
			output.println("\n-----------------------------------------------------------------------------------------------------");
		}
		
	}

	
	//for executing command list tv series
	public void commandListTVSeries (PrintWriter output , ArrayList<TVSeries> tvs) {
		
		output.println("LIST	FILM	SERIES");
		output.println();
		if (tvs.size()==0)
			output.println("No result");//if there isn't any series print out no result
		else
		{
			for (int i = 0 ; i<tvs.size() ; i++)
			{
				output.println(tvs.get(i).getTitle()+" ("+tvs.get(i).getStartDate().substring(6, 10)+"-"+tvs.get(i).getEndDate().substring(6, 10)+")");
				output.println(tvs.get(i).getSeason()+" seasons and "+tvs.get(i).getEpisodes()+" episodes");
				output.println();
			}
		}
		output.println("-----------------------------------------------------------------------------------------------------");
	}
//for listing users' rate which they gave before
	public void commandListUserRate (String line , ArrayList<Documentary> doc , ArrayList<FeatureFilm> fFilm , ArrayList<ShortFilm> sFilm , ArrayList<TVSeries> tvs , ArrayList<User> user ,PrintWriter output) {
			
		output.println(line);
		output.println();
		String [] seperation = line.split("\\s+");
		int control = 0;
		ter:for (int i = 0 ; i<user.size() ; i++)
		{
			if (user.get(i).getId().equalsIgnoreCase(seperation[2]))
			{
				if (user.get(i).rates.size()!=0)
				{
					int control1 = 0;
					for (int j = 0 ; j<user.get(i).rates.size() ; j++)
					{
						for (int k = 0 ; k<fFilm.size() ; k++)
						{
							if (user.get(i).rates.get(j).getFilmId().equalsIgnoreCase(fFilm.get(k).getId()))
							{
								output.println(fFilm.get(k).getTitle()+": "+user.get(i).rates.get(j).getRate());
								control++;
								control1++;
							}
						}
						for (int k = 0 ; k<sFilm.size() ; k++)
						{
							if (user.get(i).rates.get(j).getFilmId().equalsIgnoreCase(sFilm.get(k).getId()))
							{
								output.println(sFilm.get(k).getTitle()+": "+user.get(i).rates.get(j).getRate());
								control++;
								control1++;
							}
						}
						for (int k = 0 ; k<doc.size() ; k++)
						{
							if (user.get(i).rates.get(j).getFilmId().equalsIgnoreCase(doc.get(k).getId()))
							{
								output.println(doc.get(k).getTitle()+": "+user.get(i).rates.get(j).getRate());
								control++;
								control1++;
							}
						}
						for (int k = 0 ; k<tvs.size() ; k++)
						{
							if (user.get(i).rates.get(j).getFilmId().equalsIgnoreCase(tvs.get(k).getId()))
							{
								output.println(tvs.get(k).getTitle()+": "+user.get(i).rates.get(j).getRate());
								control++;
								control1++;
							}
						}
					}
					if (control1==0)
					{
						output.println("There is not any ratings so far");
						break ter;
					}
				}
			}
		}
		if (control==0)
			output.println("Command Failed\nUser ID: "+seperation[2]);
		output.println();
		output.println("-----------------------------------------------------------------------------------------------------");
	}
//for editing new rate, if user rated film before then printed out command failed
	public void commandEditUserRate (String line , ArrayList<Documentary> doc , ArrayList<FeatureFilm> fFilm , ArrayList<ShortFilm> sFilm , ArrayList<TVSeries> tvs , ArrayList<User> user ,PrintWriter output) {
	
			
		output.println(line);
		output.println();
		String [] seperation = line.split("\\s+");
		int control = 0;
		ter:for (int i = 0 ; i<user.size() ; i++)//controlling in feature film
		{
			if (user.get(i).getId().equalsIgnoreCase(seperation[2]))
			{
				for (int j = 0 ; j<fFilm.size() ; j++)
				{
					if (fFilm.get(j).getId().equalsIgnoreCase(seperation[3]))
					{
						for (int k = 0 ; k<fFilm.get(j).uir.size() ; k++)
						{
							if (user.get(i).getId().equalsIgnoreCase(fFilm.get(j).uir.get(k).getUserId()))
							{
								for (int n = 0 ; n<user.get(i).rates.size() ; n++)
								{
									if (user.get(i).rates.get(n).getFilmId().equalsIgnoreCase(fFilm.get(j).getId()))
									{
										user.get(i).rates.remove(n);
										user.get(i).rates.add(n,new RatingsOfUser(Integer.parseInt(seperation[4]),fFilm.get(j).getId()));
									}
								}
								fFilm.get(j).uir.remove(k);
								fFilm.get(j).uir.add(k, new UserIdRate(user.get(i).getId(),Integer.parseInt(seperation[4])));
								output.println("New ratings done successfully");
								output.println("Film title: "+fFilm.get(j).getTitle()+"\nYour rating: "+seperation[4]);
								control++;
								break ter;
							}
						}
					}
				}
				for (int j = 0 ; j<sFilm.size() ; j++)//controlling from short films
				{
					if (sFilm.get(j).getId().equalsIgnoreCase(seperation[3]))
					{
						for (int k = 0 ; k<sFilm.get(j).uir.size() ; k++)
						{
							if (user.get(i).getId().equalsIgnoreCase(sFilm.get(j).uir.get(k).getUserId()))
							{
								for (int n = 0 ; n<user.get(i).rates.size() ; n++)
								{
									if (user.get(i).rates.get(n).getFilmId().equalsIgnoreCase(sFilm.get(j).getId()))
									{
										user.get(i).rates.remove(n);
										user.get(i).rates.add(n,new RatingsOfUser(Integer.parseInt(seperation[4]),sFilm.get(j).getId()));
									}
								}
								sFilm.get(j).uir.remove(k);
								sFilm.get(j).uir.add(k, new UserIdRate(user.get(i).getId(),Integer.parseInt(seperation[4])));
								output.println("New ratings done successfully");
								output.println("Film title: "+sFilm.get(j).getTitle()+"\nYour rating: "+seperation[4]);
								control++;
								break ter;
							}
						}
					}
				}
				for (int j = 0 ; j<doc.size() ; j++)
				{
					if (doc.get(j).getId().equalsIgnoreCase(seperation[3]))
					{
						for (int k = 0 ; k<doc.get(j).uir.size() ; k++)
						{
							if (user.get(i).getId().equalsIgnoreCase(doc.get(j).uir.get(k).getUserId()))
							{
								for (int n = 0 ; n<user.get(i).rates.size() ; n++)
								{
									if (user.get(i).rates.get(n).getFilmId().equalsIgnoreCase(doc.get(j).getId()))
									{
										user.get(i).rates.remove(n);
										user.get(i).rates.add(n,new RatingsOfUser(Integer.parseInt(seperation[4]),doc.get(j).getId()));
									}
								}
								doc.get(j).uir.remove(k);
								doc.get(j).uir.add(k, new UserIdRate(user.get(i).getId(),Integer.parseInt(seperation[4])));
								output.println("New ratings done successfully");
								output.println("Film title: "+doc.get(j).getTitle()+"\nYour rating: "+seperation[4]);
								control++;
								break ter;
							}
						}
					}
				}
				for (int j = 0 ; j<tvs.size() ; j++)//controlling in tv series
				{
					if (tvs.get(j).getId().equalsIgnoreCase(seperation[3]))
					{
						for (int k = 0 ; k<tvs.get(j).uir.size() ; k++)
						{
							if (user.get(i).getId().equalsIgnoreCase(tvs.get(j).uir.get(k).getUserId()))
							{
								for (int n = 0 ; n<user.get(i).rates.size() ; n++)
								{
									if (user.get(i).rates.get(n).getFilmId().equalsIgnoreCase(tvs.get(j).getId()))
									{
										user.get(i).rates.remove(n);
										user.get(i).rates.add(n,new RatingsOfUser(Integer.parseInt(seperation[4]),tvs.get(j).getId()));
									}
								}
									tvs.get(j).uir.remove(k);
									tvs.get(j).uir.add(k, new UserIdRate(user.get(i).getId(),Integer.parseInt(seperation[4])));
									output.println("New ratings done successfully");
									output.println("Film title: "+tvs.get(j).getTitle()+"\nYour rating: "+seperation[4]);
									control++;
									break ter;
							}
						}
					}
				}
			}
		}
		if (control==0)
			output.println("Command Failed\nUser ID: "+seperation[2]+"\nFilm ID: "+seperation[3]);
		output.println();
		output.println("-----------------------------------------------------------------------------------------------------");
	}

	//for listing films by their rate
	public void commandListFilmsByRate(String line,ArrayList<Documentary> doc , ArrayList<FeatureFilm> fFilm , ArrayList<ShortFilm> sFilm , ArrayList<TVSeries> tvs,PrintWriter output )
	{
		output.println(line);
		double sum=0,rate=0;
		ArrayList<String> saving=new ArrayList<String>();
		ArrayList<String> saving1=new ArrayList<String>();
		ArrayList<String> saving2=new ArrayList<String>();
		ArrayList<String> saving3=new ArrayList<String>();
		String[]ter=new String[fFilm.size()];
		for(int i=0;i<fFilm.size();i++)
			ter[i]=new String();
		DecimalFormat fmt1=new DecimalFormat("0.#");//for writing decimals with comma
		for(int i=0;i<fFilm.size();i++)//for listing feature films
		{
			for(int j=0;j<fFilm.get(i).uir.size();j++)
			{
				sum+=fFilm.get(i).uir.get(j).getRate();
			}
			rate=sum/(double)fFilm.get(i).uir.size();
			if(fFilm.get(i).uir.size()==0)
			{
				saving.add(new String(fFilm.get(i).getTitle()+" ("+fFilm.get(i).getReleaseDate().substring(6, 10)+") Ratings: 0"+"/10 from "+fFilm.get(i).uir.size()+" users"));	
			    ter[i]=fFilm.get(i).getTitle()+" 0";	
			}
			else if(Math.ceil(rate)==rate)
			{
			   saving.add(new String(fFilm.get(i).getTitle()+" ("+fFilm.get(i).getReleaseDate().substring(6, 10)+") Ratings: "+(int)rate+"/10 from "+fFilm.get(i).uir.size()+" users"));	
			   ter[i]=fFilm.get(i).getTitle()+" "+(int)rate;
			}
			
			else
			{
				saving.add(new String(fFilm.get(i).getTitle()+" ("+fFilm.get(i).getReleaseDate().substring(6, 10)+") Ratings: "+fmt1.format(rate)+"/10 from "+fFilm.get(i).uir.size()+" users"));	
				ter[i]=fFilm.get(i).getTitle()+" "+rate;
			}
			sum=0; rate=0;
		}
		
		for(int j=1;j<ter.length;j++)
		{
			String temp=null;
			
			for(int w=j;w>=0;w--)
			{
				if(j!=ter.length-1)
				{
					String [] split=ter[w].split("\\s+");
					String [] split2=ter[w+1].split("\\s+");
					if(Double.parseDouble(split[1])<Double.parseDouble(split2[1]))
					{
						temp=ter[w];
						ter[w]=ter[w+1] ;
						ter[w+1]=temp;
					}
				}
			}
		}
		for(int i=0;i<ter.length;i++)
		{
			if(i==0) output.println("\nFeatureFilm:");
			String [] s1=ter[i].split("\\s+");
			for(int j=0;j<saving.size();j++)
			{
				String [] s2=saving.get(j).split("\\s+");
				if(s1[0].equalsIgnoreCase(s2[0]))
				{
					output.println(saving.get(j));
				}
			}
		}
		
		
		for(int i=0;i<sFilm.size();i++)//for listing short films
			ter[i]=new String();
		
		for(int i=0;i<sFilm.size();i++)
		{
			for(int j=0;j<sFilm.get(i).uir.size();j++)
			{
				sum+=sFilm.get(i).uir.get(j).getRate();
			}
			rate=sum/(double)sFilm.get(i).uir.size();
			if(sFilm.get(i).uir.size()==0)
			{
				saving1.add(new String(sFilm.get(i).getTitle()+" ("+sFilm.get(i).getReleaseDate().substring(6, 10)+") Ratings: 0"+"/10 from "+sFilm.get(i).uir.size()+" users"));	
			    ter[i]=sFilm.get(i).getTitle()+" 0";	
			}
			else if(Math.ceil(rate)==rate)
			{
			   saving1.add(new String(sFilm.get(i).getTitle()+" ("+sFilm.get(i).getReleaseDate().substring(6, 10)+") Ratings: "+(int)rate+"/10 from "+sFilm.get(i).uir.size()+" users"));	
			   ter[i]=sFilm.get(i).getTitle()+" "+(int)rate;
			}
			
			else
			{
				saving1.add(new String(sFilm.get(i).getTitle()+" ("+sFilm.get(i).getReleaseDate().substring(6, 10)+") Ratings: "+fmt1.format(rate)+"/10 from "+sFilm.get(i).uir.size()+" users"));	
				ter[i]=sFilm.get(i).getTitle()+" "+rate;
			}
			sum=0; rate=0;
		}
		
		for(int j=1;j<ter.length;j++)
		{
			String temp=null;
			
			for(int w=j;w>=0;w--)
			{
				if(j!=ter.length-1)
				{
					String [] split=ter[w].split("\\s+");
					String [] split2=ter[w+1].split("\\s+");
					if(Double.parseDouble(split[1])<Double.parseDouble(split2[1]))
					{
						temp=ter[w];
						ter[w]=ter[w+1] ;
						ter[w+1]=temp;
					}
				}
			}
		}
		for(int i=0;i<ter.length;i++)
		{
			if(i==0) output.println("\nShortFilm:");
			String [] s1=ter[i].split("\\s+");
			for(int j=0;j<saving1.size();j++)
			{
				String [] s2=saving1.get(j).split("\\s+");
				if(s1[0].equalsIgnoreCase(s2[0]))
				{
					output.println(saving1.get(j));
				}
			}
		}
		
		
		
		for(int i=0;i<doc.size();i++)//for listing documentary
			ter[i]=new String();
		
		for(int i=0;i<doc.size();i++)
		{
			for(int j=0;j<doc.get(i).uir.size();j++)
			{
				sum+=doc.get(i).uir.get(j).getRate();
			}
			rate=sum/(double)doc.get(i).uir.size();
			if(doc.get(i).uir.size()==0)
			{
				saving2.add(new String(doc.get(i).getTitle()+" ("+doc.get(i).getReleaseDate().substring(6, 10)+") Ratings: 0"+"/10 from "+doc.get(i).uir.size()+" users"));	
			    ter[i]=doc.get(i).getTitle()+" 0";	
			}
			else if(Math.ceil(rate)==rate)
			{
			   saving2.add(new String(doc.get(i).getTitle()+" ("+doc.get(i).getReleaseDate().substring(6, 10)+") Ratings: "+(int)rate+"/10 from "+doc.get(i).uir.size()+" users"));	
			   ter[i]=doc.get(i).getTitle()+" "+(int)rate;
			}
			
			else
			{
				saving2.add(new String(doc.get(i).getTitle()+" ("+doc.get(i).getReleaseDate().substring(6, 10)+") Ratings: "+fmt1.format(rate)+"/10 from "+doc.get(i).uir.size()+" users"));	
				ter[i]=doc.get(i).getTitle()+" "+rate;
			}
			sum=0; rate=0;
		}
		
		for(int j=1;j<ter.length;j++)
		{
			String temp=null;
			
			for(int w=j;w>=0;w--)
			{
				if(j!=ter.length-1)
				{
					String [] split=ter[w].split("\\s+");
					String [] split2=ter[w+1].split("\\s+");
					if(Double.parseDouble(split[1])<Double.parseDouble(split2[1]))
					{
						temp=ter[w];
						ter[w]=ter[w+1] ;
						ter[w+1]=temp;
					}
				}
			}
		}
		for(int i=0;i<ter.length;i++)
		{
			if(i==0) output.println("\nDocumentary:");
			String [] s1=ter[i].split("\\s+");
			for(int j=0;j<saving2.size();j++)
			{
				String [] s2=saving2.get(j).split("\\s+");
				if(s1[0].equalsIgnoreCase(s2[0]))
				{
					output.println(saving2.get(j));
				}
			}
		}
		
		for(int i=0;i<tvs.size();i++)//for listing tv series
			ter[i]=new String();
		
		for(int i=0;i<tvs.size();i++)
		{
			for(int j=0;j<tvs.get(i).uir.size();j++)
			{
				sum+=tvs.get(i).uir.get(j).getRate();
			}
			rate=sum/(double)tvs.get(i).uir.size();
			if(tvs.get(i).uir.size()==0)
			{
				saving3.add(new String(tvs.get(i).getTitle()+" ("+tvs.get(i).getStartDate().substring(6, 10)+"-"+tvs.get(i).getEndDate().substring(6, 10)+") Ratings: 0"+"/10 from "+tvs.get(i).uir.size()+" users"));	
			    ter[i]=tvs.get(i).getTitle()+" 0";	
			}
			else if(Math.ceil(rate)==rate)
			{
				saving3.add(new String(tvs.get(i).getTitle()+" ("+tvs.get(i).getStartDate().substring(6, 10)+"-"+tvs.get(i).getEndDate().substring(6, 10)+") Ratings: 0"+"/10 from "+tvs.get(i).uir.size()+" users"));
			   ter[i]=tvs.get(i).getTitle()+" "+(int)rate;
			}
			
			else
			{
				saving3.add(new String(tvs.get(i).getTitle()+" ("+tvs.get(i).getStartDate().substring(6, 10)+"-"+tvs.get(i).getEndDate().substring(6, 10)+") Ratings: "+fmt1.format(rate)+"/10 from "+tvs.get(i).uir.size()+" users"));
				ter[i]=tvs.get(i).getTitle()+" "+rate;
			}
			sum=0; rate=0;
		}
		
		for(int j=1;j<ter.length;j++)
		{
			String temp=null;
			
			for(int w=j;w>=0;w--)
			{
				if(j!=ter.length-1)
				{
					String [] split=ter[w].split("\\s+");
					String [] split2=ter[w+1].split("\\s+");
					if(Double.parseDouble(split[1])<Double.parseDouble(split2[1]))
					{
						temp=ter[w];
						ter[w]=ter[w+1] ;
						ter[w+1]=temp;
				
					}
				}
			}
		}
		for(int i=0;i<ter.length;i++)
		{
			if(i==0) output.println("\nTVSeries:");
			String [] s1=ter[i].split("\\s+");
			for(int j=0;j<saving3.size();j++)
			{
				String [] s2=saving3.get(j).split("\\s+");
				if(s1[0].equalsIgnoreCase(s2[0]))
				{
					output.println(saving3.get(j));
				}
			}
		}
		output.println("\n-----------------------------------------------------------------------------------------------------");
	}
	
	//for remove the given rate of users'

	public void commandRemoveRate(PrintWriter output,String line,ArrayList<User> user,ArrayList<Documentary> doc , ArrayList<FeatureFilm> fFilm , ArrayList<ShortFilm> sFilm , ArrayList<TVSeries> tvs)	{
	
		output.println(line);
		String [] separation= line.split("\\s+");
		int control=0,control1=0;
		for(int i=0;i<user.size();i++)
		{
			if(user.get(i).getId().equalsIgnoreCase(separation[2]))
			{
				control++;
				for(int j=0;j<user.get(i).rates.size();j++)//if rating in feature film removes it
					if(user.get(i).rates.get(j).getFilmId().equalsIgnoreCase(separation[3]))
					{
						control1++;
						output.print("\nYour film rating was removed successfully\n"+"Film title: ");
						int count=0;
						for(int k=0;k<fFilm.size();k++)
						{
							if(fFilm.get(k).getId().equalsIgnoreCase(separation[3]))
							{
								output.println(fFilm.get(k).getTitle());
								user.get(i).rates.remove(j);
								for(int w=0;w<fFilm.get(k).uir.size();w++)
								{
									if(fFilm.get(k).uir.get(w).getUserId().equalsIgnoreCase(user.get(i).getId()))
									{
										fFilm.get(k).uir.remove(w);
									}
								}
								count++;
								break;
							}
						}
						if(count==0)
							for(int k=0;k<sFilm.size();k++)//if rating in short film removes it
							{
								if(sFilm.get(k).getId().equalsIgnoreCase(separation[3]))
								{
									output.println(sFilm.get(k).getTitle());
									user.get(i).rates.remove(j);
									for(int w=0;w<sFilm.get(k).uir.size();w++)
									{
										if(sFilm.get(k).uir.get(w).getUserId().equalsIgnoreCase(user.get(i).getId()))
										{
											sFilm.get(k).uir.remove(w);
										}
									}
									count++;
									break;
								}
							}		
						if(count==0)
							for(int k=0;k<doc.size();k++)//if rating in documentary removes it
							{
								if(doc.get(k).getId().equalsIgnoreCase(separation[3]))
								{
									output.println(doc.get(k).getTitle());
									user.get(i).rates.remove(j);
									for(int w=0;w<doc.get(k).uir.size();w++)
									{
										if(doc.get(k).uir.get(w).getUserId().equalsIgnoreCase(user.get(i).getId()))
										{
											doc.get(k).uir.remove(w);
										}
									}
									count++;
									break;
								}
							}
						if(count==0)
							for(int k=0;k<tvs.size();k++)//if rating in tv series removes it
							{
								if(tvs.get(k).getId().equalsIgnoreCase(separation[3]))
								{
									output.println(tvs.get(k).getTitle());
									user.get(i).rates.remove(j);
									for(int w=0;w<tvs.get(k).uir.size();w++)
									{
										if(tvs.get(k).uir.get(w).getUserId().equalsIgnoreCase(user.get(i).getId()))
										{
											tvs.get(k).uir.remove(w);
										}
									}
									count++;
									break;
								}
							}
						
						output.println("\n-----------------------------------------------------------------------------------------------------");
					}
			}
		}
		if(control==0||control1==0)
		{
		output.println("\nCommand Failed\n"+"User ID: "+separation[2]+"\nFilm ID: "+separation[3]);
		output.println("\n-----------------------------------------------------------------------------------------------------");
		}
	}
	
	//for listing artists from the given country in command
	public void commandListArtistsFrom(String line,PrintWriter output,ArrayList<Actor>actor,ArrayList<Actress>actress,ArrayList<Director>director,ArrayList<User>user,ArrayList<Writer>writer)
	{
		output.println(line);
		String [] separation=line.split("\\s+");
		int control=0;
		for (int i=0;i<director.size();i++)//for listing directors
		{
			if(i==0) {output.println("\nDirectors:"); }
			if(director.get(i).getCountry().equalsIgnoreCase(separation[3]))
			{
				control++;
				
				output.println(director.get(i).getName()+" "+director.get(i).getSurname()+" "+director.get(i).getAgent());
				
			}
		}
		if(control==0)
		{
			output.println("No result");
			}
		control=0;
		for (int i=0;i<writer.size();i++)//for writing writers
		{
			if(i==0) {output.println("\nWriters:"); }
			if(writer.get(i).getCountry().equalsIgnoreCase(separation[3]))
			{control++;
				
				output.println(writer.get(i).getName()+" "+writer.get(i).getSurname()+" "+writer.get(i).getStyle());
				
			}
		}
		if(control==0)
		{
			output.println("No result");
			}
		control=0;
		for (int i=0;i<actor.size();i++)//for listing actors
		{
			if(i==0) {output.println("\nActors:"); }
			if(actor.get(i).getCountry().equalsIgnoreCase(separation[3]))
			{
				control++;
				
				output.println(actor.get(i).getName()+" "+actor.get(i).getSurname()+" "+actor.get(i).getHeight()+" cm");
				
			}
		}
		if(control==0)
		{
			output.println("No result");
			}
		control=0;
		for (int i=0;i<actress.size();i++)//for listing actresses
		{
			if(i==0) {output.println("\nActresses:"); }
			if(actress.get(i).getCountry().equalsIgnoreCase(separation[3]))
			{
				control++;
				
				output.println(actress.get(i).getName()+" "+actress.get(i).getSurname());
				
				
			}
			if(i==(actress.size()-1)&&control!=0)
			{
				output.println();
				output.println("-----------------------------------------------------------------------------------------------------");
			}
			
		}
		
		if(control==0)
		{
			output.println("No result");
			output.println("\n-----------------------------------------------------------------------------------------------------");
		}
	}
	
	//for listing films by country which given in the command
	public void commandListFilmByCountry (String line,ArrayList<Documentary>doc,ArrayList<FeatureFilm> fFilm, ArrayList<ShortFilm>sFilm,ArrayList<TVSeries> tvs,PrintWriter output , ArrayList<String> filmIds) {
		
		output.println(line);
		output.println();
		String [] seperation = line.split("\\s+");
		int control = 0;
		for (int i = 0 ; i<filmIds.size() ; i++)//listing feature films
		{
			for (int j = 0 ; j<fFilm.size() ; j++)
			{
				if (filmIds.get(i).equalsIgnoreCase(fFilm.get(j).getId())&&fFilm.get(j).getCountry().equalsIgnoreCase(seperation[4]))
				{
					output.println("Film title: "+fFilm.get(j).getTitle()+"\n"+fFilm.get(j).getLength()+" min\nLanguage: "+fFilm.get(j).getLanguage());
					output.println();
					control++;
					break;
				}
			}
			for (int j = 0 ; j<sFilm.size() ; j++)//for listing short films
			{
				if (filmIds.get(i).equalsIgnoreCase(sFilm.get(j).getId())&&sFilm.get(j).getCountry().equalsIgnoreCase(seperation[4]))
				{
					output.println("Film title: "+sFilm.get(j).getTitle()+"\n"+sFilm.get(j).getLength()+" min\nLanguage: "+sFilm.get(j).getLanguage());
					output.println();
					control++;
					break;
				}
			}
			for (int j = 0 ; j<doc.size() ; j++)//for listing documentary
			{
				if (filmIds.get(i).equalsIgnoreCase(doc.get(j).getId())&&doc.get(j).getCountry().equalsIgnoreCase(seperation[4]))
				{
					output.println("Film title: "+doc.get(j).getTitle()+"\n"+doc.get(j).getLength()+" min\nLanguage: "+doc.get(j).getLanguage());
					output.println();
					control++;
					break;
				}
			}
			for (int j = 0 ; j<tvs.size() ; j++)//for listing tv series
			{
				if (filmIds.get(i).equalsIgnoreCase(tvs.get(j).getId())&&tvs.get(j).getCountry().equalsIgnoreCase(seperation[4]))
				{
					output.println("Film title: "+tvs.get(j).getTitle()+"\n"+tvs.get(j).getLength()+" min\nLanguage: "+tvs.get(j).getLanguage());
					output.println();
					control++;
					break;
				}
			}
			
		}
		if (control==0)
		{
			output.println("No result");
			output.println();
		}
		output.println("-----------------------------------------------------------------------------------------------------");
	}
	
	//for listing feature film before the given year
	public void commandListFeatureBefore (String line , ArrayList<FeatureFilm> fFilm , PrintWriter output) {
		
		output.println(line);
		output.println();
		String [] seperation = line.split("\\s+");
		int control = 0;
		for (int i = 0; i<fFilm.size() ; i++)
		{
			String rDate = fFilm.get(i).getReleaseDate();
			String [] sep = rDate.split("\\.");
			if (sep[2].compareTo(seperation[3])<0)
			{
				output.println("Film title: "+fFilm.get(i).getTitle()+" ("+sep[2]+")\n"+fFilm.get(i).getLength()+" min\nLanguage: "+fFilm.get(i).getLanguage());
				output.println();
				control++;
			}
		}
		if (control==0)
		{
			output.println("No result");
			output.println();
		}
		output.println("-----------------------------------------------------------------------------------------------------");
	}
	//for listing feature film after the given year
	public void commandListFeatureAfter (String line , ArrayList<FeatureFilm> fFilm , PrintWriter output) {
		
		output.println(line);
		output.println();
		String [] seperation = line.split("\\s+");
		int control = 0;
		for (int i = 0; i<fFilm.size() ; i++)
		{
			String rDate = fFilm.get(i).getReleaseDate();
			String [] sep = rDate.split("\\.");
			if (sep[2].compareTo(seperation[3])>=0)
			{
				output.println("Film title: "+fFilm.get(i).getTitle()+" ("+sep[2]+")\n"+fFilm.get(i).getLength()+" min\nLanguage: "+fFilm.get(i).getLanguage());
				output.println();
				control++;
			}
		}
		if (control==0)
		{
			output.println("No result");
			output.println();
		}
		output.println("-----------------------------------------------------------------------------------------------------");
	}
}
