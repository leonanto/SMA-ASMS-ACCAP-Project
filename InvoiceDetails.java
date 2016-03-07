package asms_accpac_intergrator;

import com.sage.accpac.sm.Program;
import com.sage.accpac.sm.View;

public class InvoiceDetails {
	
	//Invoice Details Table
	public static String getInvoiceDetails(Program program){
		View invoiceD = new View(program, "AR0033");
		int count = 0;
		invoiceD.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoiceD.goNext()) {
			count++;
			return(invoiceD.getDefiningKeyValues() + "\n");
		}
		System.out.println(count);
		return null;
	}
}
