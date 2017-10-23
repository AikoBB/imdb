package commands;

import service.Service;

public abstract class Command {

		protected Service service;
		protected WriteFile wf;
	    protected String [] command;
	    
		public Command(Service service, WriteFile wf, String [] command) {
			this.service = service;
			this.wf = wf;
			this.command = command;
		}
		
		public void execute(){
			wf.writeCommandTitle(String.join(" ", command));
		}

	}