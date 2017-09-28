package handlers;
import static org.junit.Assert.*;
import org.junit.Test;

public class UnitTests {

	
	
	InputHandler handler =new InputHandler();
	
	@Test
	public void determineRole() {
	
		assertEquals("Ask if librarian or user.",handler.processInput(" ",InputHandler.WAITING).getOutput(), "Are you a librarian or a User?" );
		
		assertEquals("Check if librarian.",handler.processInput("librarian",InputHandler.ROLEDETERMINED).getState(), InputHandler.LIBRARIAN  );
		
		assertEquals("Check if User.",handler.processInput("user",InputHandler.ROLEDETERMINED).getState(), InputHandler.USER  );
		
		assertEquals("Handle Incorrect Input.",handler.processInput("teacher",InputHandler.ROLEDETERMINED).getOutput(), "Role not recognized. Are you a librarian or a User?");
	}
	
	@Test
	public void librarianTests() {
		
				assertEquals("Prompt for password.",handler.processInput("librarian",InputHandler.ROLEDETERMINED).getOutput(), "Please Input The Password." );

				assertEquals("Check if password is correct.",handler.processInput("admin",InputHandler.LIBRARIAN).getState(), InputHandler.LIBRARIANLOGIN );


				
				
	}
	
	
	

}
