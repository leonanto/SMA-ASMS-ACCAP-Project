package asms_accpac_intergrator;

import com.sage.accpac.sm.Program;
import com.sage.accpac.sm.View;

public class Customers {

	//Customers Table 
	public static boolean getCustomers(Program program, String field)
	{ 
		View customer = new View(program, "AR0024");
		int count = 0;
		customer.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (customer.goNext()) {
			System.out.println(customer.get(field).toString() + "\n");
			count++;
		}
		System.out.println(count);
		return true;
	}
}
