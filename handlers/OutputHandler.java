package handlers;



import logic.handler.model.Output;



public class OutputHandler {
	
	public Output determineRole() {
		
		Output output=new Output("",0);

	
		output.setOutput("Are you a librarian or a User?");
        
		
		return output;
	}
	
	
	
	
}
