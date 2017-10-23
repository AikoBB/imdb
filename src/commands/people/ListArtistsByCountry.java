package commands.people;

import java.util.ArrayList;
import java.util.HashMap;

import commands.Command;
import commands.WriteFile;
import dao.person.Person;
import enums.PersonType;
import service.PersonService;

public class ListArtistsByCountry extends Command{

	public ListArtistsByCountry(PersonService personService, WriteFile wf,String command []) {
		super(personService, wf, command);
	}

	@Override
	public void execute() {
		super.execute();
		String country = command[3];
		HashMap<PersonType, ArrayList<Person>>  result = ((PersonService)service).getArtistsByCountry(country);
		
		wf.writeResult("Directors:");
		wf.writeResults(result.get(PersonType.DIRECTOR));
		
		wf.writeResult("Writers:");
		wf.writeResults(result.get(PersonType.WRITER));
		
		wf.writeResult("Actors:");
		wf.writeResults(result.get(PersonType.ACTOR));
		
		wf.writeResult("Actresses:");
		wf.writeResults(result.get(PersonType.ACTRESS));
	}


}
