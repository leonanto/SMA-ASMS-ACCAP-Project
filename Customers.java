package asms_accpac_intergrator;

import com.sage.accpac.sm.Program;
import com.sage.accpac.sm.View;

public class Customers {

	//Customers Table 
	public static boolean getCustomers(Program program)
	{ 
		View customer = new View(program, "AR0024");
		int count = 0;
		customer.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (customer.goNext()) {
			System.out.println(customer.get("IDCUST") + "\n");
			count++;
		}
		System.out.println(count);
		return true;
	}
	
	public static boolean searchCustomers(Program program, String id)
	{
		View customer = new View(program, "AR0024");
		customer.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (customer.goNext()) {
			if(customer.get("IDCUST").equals(id)){
				System.out.println(customer.get("IDCUST") + " " + customer.get("NAMECUST") + "\n");
			}
		}
		return true;	
	}

	public static void insertCustomer(Program program, String id, String name)
	{
		View customer = new View(program, "AR0024");
	}
}
k