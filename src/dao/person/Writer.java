package dao.person;

public class Writer extends Person {
	private String style;

	public static class Builder extends PersonBuilder<Writer> {
		private String style;
		
		public Builder(){}
		
		public Builder style(String style){
			this.style = style;
			return this;
		}
		
		public Writer build(){
			return new Writer(this);
		}
	}
	
	private Writer(Builder builder){
		super(builder.id, builder.name, builder.surname, builder.country);
		this.style = builder.style;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	public String toString(){
		return super.toString() + " " + this.style;
	}
}
