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
		View invoiceB = new View(program, "AR0031");
		View invoice = new View(program, "AR0032");
		View invoiceD = new View(program, "AR0033");
		invoice.compose(invoiceB, invoiceD);
		int count = 0;
		invoice.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoice.goNext()) {
			//returns customer id, batch number,
			invoiceList.add(invoice.get("CNTBTCH")+ " " + invoice.get("CNTITEM") +
					" " + invoice.get("IDCUST") +" " + invoice.get("IDINVC") + "\n");
			count++;
		}
		System.out.println(count);
		invoice.dispose();
		return invoiceList;
	}

	public String[] getKeysInvoice(Program program, String batch)
	{
		View invoiceB = new View(program, "AR0031");
		View invoice = new View(program, "AR0032");
		View invoiceD = new View(program, "AR0033");
		View invoicePS = new View(program, "AR0034");
		View invoiceOF = new View(program, "AR0402");
		View invoiceDOF = new View(program, "AR0401");
		invoiceB.compose(invoice);
		invoice.compose(invoiceB, invoiceD, invoicePS, invoiceOF, null);
		invoiceD.compose(invoice, invoiceB, invoiceDOF);
		invoivePS.compose(invoice);
		invoiceOF.compose(invoice)
		int count = 0;
		//invoices.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoice.goNext()){ 
			if(invoice.get("CNTBTCH").equals(batch)){
				return(invoice.getDefiningKeyValues());
			}
		}
		System.out.println(count);
		invoice.dispose();
		invoiceB.dispose();
		invoiceD.dispose();
		invoicePS.dispose();
		invoiceOF.dispose();
		invoiceDOF.dispose();
		return null;
	}

	public static IViewFields getRecordsInvoice(Program program/*, String batch*/)
	{
		IViewFields def = null;
		View invoiceB = new View(program, "AR0031");
		View invoice = new View(program, "AR0032");
		View invoiceD = new View(program, "AR0033");
		invoice.compose(invoiceB, invoiceD);
		int count = 0;
		invoices.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoice.goNext()) { 
			if(invoices.get("CNTBTCH").equals(batch)){
				return(invoice.getViewFields());
			}
		}
		System.out.println(count);
		return def;
	}

	public static void insertInvoices(Program program, String id, String value)
	{
		View invoiceB = new View(program, "AR0031");
		View invoice = new View(program, "AR0032");
		View invoiceD = new View(program, "AR0033");
		View invoicePS = new View(program, "AR0034");
		View invoiceOF = new View(program, "AR0402");
		View invoiceDOF = new View(program, "AR0401");
		invoiceB.compose(invoice);
		invoice.compose(invoiceB, invoiceD, invoicePS, invoiceOF, null);
		invoiceD.compose(invoice, invoiceB, invoiceDOF);
		invoivePS.compose(invoice);
		invoiceOF.compose(invoice);
		int count = 0;
		invoice.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoice.goNext()) {
			if(invoice.get("IDCUST").equals(id)){
				invoice.set("IDINVC", value);
				System.out.println(invoice.get("CNTBTCH") + " " + invoice.get("IDINVC") + " " + 
				invoice.get("DATEINVC") + " " + invoice.get("DATEASOF") + " " +
				invoice.get("IDCUST") + " " + invoice.get("NAMECUST") + " " + 
				invoice.get("TEXTDESC") + " " + invoice.get("AMTINVCTOT"));
			}
		}
		invoice.dispose();
		invoiceB.dispose();
		invoiceD.dispose();
		invoicePS.dispose();
		invoiceOF.dispose();
		invoiceDOF.dispose();
	}
	
	public static ArrayList<String> searchInvoices(Program program, int filter, String id)
	{
		ArrayList<String> invoiceList = new ArrayList<String>();
		View invoiceB = new View(program, "AR0031");
		View invoice = new View(program, "AR0032");
		View invoiceD = new View(program, "AR0033");
		invoice.compose(invoiceB, invoiceD);
		invoice.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		switch(filter){
			case 0://Filter by Batch Number
				while (invoice.goNext()) {
					if(invoice.get("CNTBTCH").equals(id)){
						invoiceList.add(search(program, id, "CNTBTCH"));
					}
				}
				break;
			case 1://Filter by Invoice Number
				while (invoice.goNext()) {
					if(invoice.get("IDINVC").equals(id)){
						invoiceList.add(search(program, id, "IDINVC"));
					}
				}
				break;
			case 2://Filter by Invoice Date
				while (invoice.goNext()) {
					if(invoice.get("DATEINVC").equals(id)){
						invoiceList.add(search(program, id, "DATEINVC"));
					}
				}
				break;
			case 3://Filter by Apply Date
				while (invoice.goNext()) {
					if(invoice.get("DATEASOF").equals(id)){
						invoiceList.add(search(program, id, "DATEASOF"));
					}
				}
				break;
			case 4://Filter by Customer ID
				while (invoice.goNext()) {
					if(invoice.get("IDCUST").equals(id)){
						invoiceList.add(search(program, id, "IDCUST"));
					}
				}
				break;
		}
		invoice.dispose();
		return invoiceList;	
	}
	
	public static boolean search(Program program, String id, String field){
		View invoice = new View(program, "AR0032");
		invoice.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (invoice.goNext()) {
			if(invoice.get(field).equals(id)){
				System.out.println(invoice.get("CNTBTCH") + " " + invoice.get("IDINVC") + " " + 
				invoice.get("DATEINVC") + " " + invoice.get("DATEASOF") + " " +
				invoice.get("IDCUST") + " " + invoice.get("NAMECUST") + " " + 
				invoice.get("TEXTDESC") + " " + invoice.get("AMTINVCTOT"));
			}
		}
		return true;
	}

	public void processInvoice(Program program, String id){
		View invoiceB = new View(program, "AR0031");
		View invoice = new View(program, "AR0032");
		View invoiceD = new View(program, "AR0033");
		View invoicePS = new View(program, "AR0034");
		View invoiceOF = new View(program, "AR0402");
		View invoiceDOF = new View(program, "AR0401");
		invoiceB.compose(invoice);
		invoice.compose(invoiceB, invoiceD, invoicePS, invoiceOF, null);
		invoiceD.compose(invoice, invoiceB, invoiceDOF);
		invoivePS.compose(invoice);
		invoiceOF.compose(invoice);

		while (invoice.goNext()){ 
			if(invoice.get("IDCUST").equals(id)){
				
			}

		invoice.process();
		
		invoice.dispose();
		invoiceB.dispose();
		invoiceD.dispose();
		invoicePS.dispose();
		invoiceOF.dispose();
		invoiceDOF.dispose();
	}

}

