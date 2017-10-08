package handlers;
import static org.junit.Assert.*;
import org.junit.Test;

import server.logic.model.User;
import server.logic.tables.UserTable;


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

				assertEquals("Check if password is incorrect.",handler.processInput("sdcmslkd",InputHandler.LIBRARIAN).getState(), InputHandler.LIBRARIAN );


				assertEquals("Display Librarian Terminal",handler.processInput("admin",InputHandler.LIBRARIAN).getOutput(),"What can I do for you?"
						
						+ " Menu:"
						+ "Create User"
						+ "Create Title"
						+ "Create Item"
						+ "Delete User"
						+ "Delete Title"
						+ "Delete Item." );
				
				
				assertEquals("Prompting librarian for username and password.",handler.processInput("create user",InputHandler.LIBRARIANLOGIN).getOutput(),"Please Input User Info: username,password:");
				assertEquals("Prompting librarian for Title.",handler.processInput("create title",InputHandler.LIBRARIANLOGIN).getOutput(),"Please Input Title Info:'ISBN,title'");
				assertEquals("Prompting librarian for Item.",handler.processInput("create item",InputHandler.LIBRARIANLOGIN).getOutput(),"Please Input Item Info:'ISBN':");
				
				assertEquals("Prompting librarian for username and password to delete",handler.processInput("delete user",InputHandler.LIBRARIANLOGIN).getOutput(),"TO DELETE -> Please Input User Info: username,password:");
				assertEquals("Prompting librarian for Title to delete",handler.processInput("delete title",InputHandler.LIBRARIANLOGIN).getOutput(),"TO DELETE -> Please Input Title Info:'ISBN,title'");
				assertEquals("Prompting librarian for Item to delete",handler.processInput("delete item",InputHandler.LIBRARIANLOGIN).getOutput(),"TO DELETE -> Please Input Item Info:'ISBN':");		
	}
	@Test
	public void UserTests() {

			User testUser = new User (0,"jim@carleton.ca","jim");
			assertEquals("get UserId", testUser.getUserid(),0);
			assertEquals("get username", testUser.getUsername(),"jim@carleton.ca");
			assertEquals("get password", testUser.getPassword(),"jim");
			
			assertTrue( "Check if UserTable Initialized correctly", UserTable.getInstance().getUserTable().get(0).sameUser(testUser));
			
			UserTable.getInstance();

			
			assertEquals("Add User to User Table.",handler.processInput("sun@carleton.ca,sun",InputHandler.CREATEUSER).getOutput(),"Success!");		
			assertEquals("Check if User has correct password or username ",handler.processInput("sun^carleton.ca,sun,hey",InputHandler.CREATEUSER).getOutput(),"Your input should in this format:'username,password'");	
			assertEquals("Check if User already exists",handler.processInput("jim@carleton.ca,jim",InputHandler.CREATEUSER).getOutput(),"The User Already Exists!");	

			assertEquals("Delete User from User Table.",handler.processInput("jim@carleton.ca",InputHandler.DELETEUSER).getOutput(),"Success!");		
			assertEquals("Delete User with incorrect username.",handler.processInput("jim^carleton.ca",InputHandler.DELETEUSER).getOutput(),"Your input should in this format:'useremail'");		

			
			
		}
	
	
	

}
