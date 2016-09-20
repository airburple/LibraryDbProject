package cs5530;

import java.sql.*;
//import javax.servlet.http.*;

public class SplitAuthors{
	public SplitAuthors() {
	}
	public String splitAuthors(String authors) throws Exception{
		
		String results = "These are the authors: " ;

		String[] individuals = authors.split(",", -1);


		for (int i=0; i < individuals.length; i++){
				
			results += "<br>"+(i+1) ;
			results += individuals[i];
		}

		return results;
	}
}
