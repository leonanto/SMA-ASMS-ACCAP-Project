package asms_accpac_intergrator;

import com.sage.accpac.sm.Program;
import com.sage.accpac.sm.View;

public class Invoices {

	//Invoices Table
	public static boolean getInvoices(Program program){
		View invoices = new View(program, "AR0032");
		int count = 0;
		invoices.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoices.goNext()) {
			System.out.println(invoices.get("IDTRX").toString() + "\n");
			count++;
		}
		System.out.println(count);
		return true;
	}
}
