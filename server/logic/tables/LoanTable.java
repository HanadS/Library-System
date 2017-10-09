package server.logic.tables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import server.logic.model.Loan;



public class LoanTable {
	List<Loan> loanList=new ArrayList<Loan>();
    private static class LoanListHolder {
        private static final LoanTable INSTANCE = new LoanTable();
    }
    private LoanTable(){
    	//set up the default list with some instances
    	
	    	Loan loan=new Loan(0,"9781442668584","1",new Date(),"0");
	    	loanList.add(loan);
	    	
    };
    public static final LoanTable getInstance() {
        return LoanListHolder.INSTANCE;
    }
	
    
	public List<Loan> getLoanTable() {
		return loanList;
	}
	public Object createloan(int i, String string, String string2, Date date) {
		String result="";
		boolean user=UserTable.getInstance().lookup(i);
		boolean isbn=TitleTable.getInstance().lookup(string);
		boolean copynumber=ItemTable.getInstance().lookup(string,string2);
		boolean oloan=LoanTable.getInstance().lookup(i,string,string2);
		boolean limit=LoanTable.getInstance().checkLimit(i);
		
		if(user==false){
			result="User Invalid";
		}else if(isbn==false){
			result="ISBN Invalid";
		}else if(copynumber==false){
			result="Copynumber Invalid";
		}else{
			if(oloan){
				if(limit){
				Loan loan=new Loan(i,string,string2,date,"0");
				loanList.add(loan);
				result="success";
				}else if(limit==false){
					result="The Maximun Number of Items is Reached";
				}
			}else{
				result="The Item is Not Available";
			}
		}
    	return result;
	}
	public boolean lookup(int j, String string, String string2) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			String ISBN=(loanList.get(i)).getIsbn();
			String copynumber=(loanList.get(i)).getCopynumber();
		
			if(ISBN.equalsIgnoreCase(string) && copynumber.equalsIgnoreCase(string2)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			result=false;
		}
		return result;
	}
	public boolean checkLimit(int j) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			int userid=(loanList.get(i)).getUserid();
			if(userid==j){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag>=3){
			result=false;
		}
		return result;
	}

	
	
}
