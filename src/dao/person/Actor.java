package dao.person;

public class Actor extends Person {
	private String height;

	public static class Builder extends PersonBuilder<Actor> {
			
		private String height;
			
			public Builder(){}
			
			public Builder height(String height){
				this.height = height;
				return this;
			}
			
			public Actor build(){
				return new Actor(this);
			}
		}
	
	private Actor(Builder builder){
		super(builder.id, builder.name, builder.surname, builder.country);
		this.height = builder.height;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
	
	/**
	 * Returns surname and name, height of the object
	 */
	public String toString(){
		return super.toString() + " " + this.height + "cm";
	}
}
