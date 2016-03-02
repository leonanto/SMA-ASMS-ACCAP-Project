package asms_accpac_intergrator;

import java.util.ArrayList;
import java.util.List;

import com.sage.accpac.sm.Program;
import com.sage.accpac.sm.View;
import com.sage.accpac.sm.view.IViewFields;

public class Invoices {

	//Invoices Table
	public static List<String> getInvoices(Program program){
		List<String> invoiceList = new ArrayList<String>();
		View invoices = new View(program, "AR0032");
		int count = 0;
		//invoices.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoices.goNext()) {
			//returns customer id, batch number,
			count++;
			invoiceList.add(invoices.get("CNTBTCH")+ " " + invoices.get("CNTITEM") +
					" " + invoices.get("IDCUST") +" " + invoices.get("IDINVC") + "\n");
		}
		System.out.println(count);
		return invoiceList;
	}

	public String[] getKeysInvoice(Program program, String batch)
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
		return null;
	}

	public static IViewFields getRecordsInvoice(Program program/*, String batch*/)
	{
		IViewFields def = null;
		View invoices = new View(program, "AR0032");
		int count = 0;
		//invoices.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoices.goNext()) { 
			//if(invoices.get("CNTBTCH").equals(batch)){
				return(invoices.getViewFields());
			//}
		}
		System.out.println(count);
		return def;
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

