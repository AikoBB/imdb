package enums;

public enum PersonType {
	ACTOR("Actor:"),
	ACTRESS("Actress:"),
	WRITER("Writer:"),
	DIRECTOR("Director:"),
	USER("User:");
	
	public String value;
	
	private PersonType(String value){
		this.value = value;
	}
	
	public String value(){
		return value;
	}

}
