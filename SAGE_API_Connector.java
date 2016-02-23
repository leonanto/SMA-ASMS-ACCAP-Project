package asms_accpac_intergrator;
import java.util.Date;
import com.sage.accpac.sm.Program;
import com.sage.accpac.sm.ProgramSet;
import com.sage.accpac.sm.Session;
import com.sage.accpac.sm.SharedDataSet;
import com.sage.accpac.sm.View;

public class SAGE_API_Connector {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Session session;
		session = new Session(new ProgramSet(), new SharedDataSet(), "ADMIN",
		     "admin", "SANLTD", new Date());
		
		Program program;
		program = new Program(session, "XZ", "XZ0001", "01A");	
		//System.out.print(getCustomers(program, "IDCUST"));
		System.out.print(getInvoices(program));
	}
	
	//Customers Table 
	public static boolean getCustomers(Program program1, String field)
	{ 
		View customer = new View(program1, "AR0024");
		customer.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (customer.goNext()) {
			System.out.println(customer.get(field).toString() + "\n");
		}
		return true;
	}
	
	//Invoices Table
	public static boolean getInvoices(Program program){
		View invoices = new View(program, "AR0032");
		invoices.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoices.goNext()) {
			System.out.println(invoices.get("IDTRX").toString() + "\n");
		}	
		return true;
	}
	
	//Invoice Details Table
	public static boolean getInvoiceDetails(Program program){
		View invoiceD = new View(program, "AR0033");
		invoiceD.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoiceD.goNext()) {
			System.out.println(invoiceD.get("IDTRX").toString() + "\n");
		}	
		return true;
	}
	
	//Invoice Batches Table
	public static boolean getInvoiceBatch(Program program){
		View invoiceB = new View(program, "AR0031");
		invoiceB.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoiceB.goNext()) {
			System.out.println(invoiceB.get("IDTRX").toString() + "\n");
		}	
		return true;
	}
}

