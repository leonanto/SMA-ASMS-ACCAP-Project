package asms_accpac_intergrator;
import java.util.Date;
import asms_accpac_intergrator.Customers;
import asms_accpac_intergrator.Invoices;
import asms_accpac_intergrator.InvoiceDetails;
import asms_accpac_intergrator.InvoiceBatches;
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
		//System.out.print(Customers.searchCustomers(program, "00319-L"));
		System.out.print(Invoices.getInvoices(program));
		//System.out.print(InvoiceDetails.getInvoiceDetails(program));
		//Invoices.getRecordsInvoice(program);
		Invoices.searchInvoices(program, 4,"00319-L");
	}
	
}

