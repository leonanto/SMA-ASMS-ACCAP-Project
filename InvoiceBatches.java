package asms_accpac_intergrator;

import com.sage.accpac.sm.Program;
import com.sage.accpac.sm.View;

public class InvoiceBatches {
	
	//Invoice Batches Table
	public static String getInvoiceBatch(Program program){
		View invoiceB = new View(program, "AR0031");
		int count = 0;
		invoiceB.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoiceB.goNext()) {
			count++;
			return(invoiceB.getDefiningKeyValues() + "\n");
		}
		System.out.println(count);
		return null;
	}

}
