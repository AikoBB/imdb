package dao.person;


public class Actress extends Person {
	
	public static class Builder extends PersonBuilder<Actress> {
		
		public Builder(){}
		
		public Actress build(){
			return new Actress(this);
		}
	}
	
	private Actress(Builder builder){
		super(builder.id, builder.name, builder.surname, builder.country);
	}
	
}
