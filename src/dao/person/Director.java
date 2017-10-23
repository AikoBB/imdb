package dao.person;

public class Director extends Person {

	private String agent;

	public static class Builder extends PersonBuilder<Director> {
		private String agent;
		
		public Builder(){}
		
		public Builder agent(String agent){
			this.agent = agent;
			return this;
		}
		
		public Director build(){
			return new Director(this);
		}
	}
	
	private Director(Builder builder){
		super(builder.id, builder.name, builder.surname, builder.country);
		this.agent = builder.agent;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
	/**
	 * Returns surname and name, agent of the object
	 */
	public String toString(){
		return super.toString() + " " + this.agent;
	}
	
}
