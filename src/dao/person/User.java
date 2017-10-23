package dao.person;

import java.util.HashMap;
import java.util.Map;

public class User extends Person {
	
	public Map<Integer, Integer> ratings;
	
	public static class Builder extends PersonBuilder<User> {
		
		private Map<Integer, Integer>ratings;
		
		public Builder(){
			ratings =new HashMap<Integer, Integer>();
		}
		
		public User build(){
			return new User(this);
		}
	}
	
	private User(Builder builder){
		super(builder.id, builder.name, builder.surname, builder.country);
		ratings = builder.ratings;
	}

}
