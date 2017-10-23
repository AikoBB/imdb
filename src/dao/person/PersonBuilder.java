package dao.person;


public abstract class PersonBuilder<T> {
	protected int id;
	protected String name;
	protected String surname;
	protected String country;

	public PersonBuilder() {}
	
	@SuppressWarnings("rawtypes")
	public PersonBuilder id(int id){
		this.id = id;
		return this;
	}

	@SuppressWarnings("rawtypes")
	public PersonBuilder name(String name){
		this.name = name;
		return this;
	}

	@SuppressWarnings("rawtypes")
	public PersonBuilder surname(String surname){
		this.surname = surname;
		return this;
	}

	@SuppressWarnings("rawtypes")
	public PersonBuilder country(String country){
		this.country = country;
		return this;
	}
	
	public abstract T build();

}
