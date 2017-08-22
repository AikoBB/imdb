package com.aigerimbb.imdb.controller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.aigerimbb.imdb.dao.films.Documentary;
import com.aigerimbb.imdb.dao.films.FeatureFilm;
import com.aigerimbb.imdb.dao.films.ShortFilm;
import com.aigerimbb.imdb.dao.films.TVSeries;
import com.aigerimbb.imdb.dao.person.Actor;
import com.aigerimbb.imdb.dao.person.Actress;
import com.aigerimbb.imdb.dao.person.Director;
import com.aigerimbb.imdb.dao.person.User;
import com.aigerimbb.imdb.dao.person.Writer;


public class Main {

	
	public static void main(String[] args) {
		
		ArrayList<Actor> actor = new ArrayList<Actor>();//we used ArrayList because there wasn't any information about 
		ArrayList<Actress> actress = new ArrayList<Actress>();//amount of films and artists'
		ArrayList<Director> director = new ArrayList<Director>();
		ArrayList<User> user = new ArrayList<User>();
		ArrayList<Writer> writer = new ArrayList<Writer>();
		ArrayList<Documentary> doc = new ArrayList<Documentary>();
		ArrayList<FeatureFilm> fFilm = new ArrayList<FeatureFilm>();
		ArrayList<ShortFilm> sFilm = new ArrayList<ShortFilm>();
		ArrayList<TVSeries> tvs = new ArrayList<TVSeries>();
		ArrayList<String> filmIds = new ArrayList<String>();
		
		Methods meth = new Methods();//in this class we have created methods which are execute all commands 
		Scanner input = null;
		input = meth.openFile(args[0]);
		meth.peopleRead(input, actor, actress, director, user, writer);
		
		Scanner input1 = null;
		input1 = meth.openFile(args[1]);
		meth.filmRead(input1, doc, fFilm , sFilm , tvs , filmIds);//this line calls meth's method for reading film's data
		
		Scanner input2 = null;
		
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileOutputStream(args[3]),true);
		}
		catch (FileNotFoundException e) {
			System.out.println("Error opening file");
			System.exit(1);
		}
		input2 = meth.openFile(args[2]);
		meth.commands(input2, output, actor, actress, director, user, writer, doc, fFilm, sFilm, tvs, filmIds);// this line calls meth's method for
		//executing commands
		
	}

}
