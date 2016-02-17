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
		program = new Program(session, "XZ", "XZ0001", "10A");	
	
		View customer = new View(program, "AR0024");
		customer.filterSelect("", true, 1, View.FilterOrigin.FromStart);
		while (customer.goNext()) {
			System.out.println(customer.getField("NAMECUST") + "\n");
		}
	}
}

