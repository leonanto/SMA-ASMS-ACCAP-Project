package asms_accpac_intergrator;

import com.sage.accpac.sm.Program;
import com.sage.accpac.sm.View;

public class Invoices {

	//Invoices Table
	public static String getInvoices(Program program){
		View invoices = new View(program, "AR0032");
		int count = 0;
		//invoices.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoices.goNext()) {
			//returns customer id, batch number, 
			return(System.out.println(invoices.get("CNTBTCH")+ " " + invoices.get("CNTITEM") +
					" " + invoices.get("IDCUST") +" " + invoices.get("IDINVC") + "\n"));
			count++;
		}
		System.out.println(count);
		return ("");
	}

	public String getKeysInvoice(Program program, String batch)
	{
		View invoices = new View(program, "AR0032");
		int count = 0;
		//invoices.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoices.goNext()) { 
			if(invoices.get("CNTBTCH").equals(batch)){
				return(invoices.getDefiningKeyValues());
			}
		}
		System.out.println(count);
		return ("");
	}

	public View getFRecordsInvoice(Program program, String batch)
	{
		View invoices = new View(program, "AR0032");
		int count = 0;
		//invoices.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoices.goNext()) { 
			if(invoices.get("CNTBTCH").equals(batch)){
				return(invoices.getVeiwFields());
			}
		}
		System.out.println(count);
		return ("");
	}

	public static void insertInvoices(Program program, String value[])
	{
		View invoices = new View(program, "AR0024");
		invoices.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoices.goNext()) {
			if(invoices.get("IDCUST").equals(id)){
				invoices.set(, value[0]);
				invoices.set(, value[1]);
				invoices.set(, value[2]);
			}
		}
	}

}

