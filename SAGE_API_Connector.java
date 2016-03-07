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
import com.sage.accpac.sm.View.RecordGenerateMode;

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
		//System.out.print(enterARInvoices());
		//Invoices.getRecordsInvoice(program);
		Invoices.searchInvoices(program, 4,"00319-L");
		Invoices.insertInvoices(program, "01619-R", "A12345");
		Invoices.getKeysInvoice(program, "345");
	}
	
	public static String enterARInvoices()

    {
		Session session;
		session = new Session(new ProgramSet(), new SharedDataSet(), "ADMIN",
		     "admin", "SANLTD", new Date());
		
		Program program;
		program = new Program(session, "XZ", "XZ0001", "01A");	

        int iEntry;
        int iDetail;
        int numEntries = 20;
        int numDetails = 5;
        String sBatchNum;

        View ARINVOICE1batch = new View(program, "AR0031");
        View ARINVOICE1header = new View(program, "AR0032");
        View ARINVOICE1detail1 = new View(program, "AR0033");
        View ARINVOICE1detail2 = new View(program, "AR0034");
        View ARINVOICE1detail3 = new View(program, "AR0402");
        View ARINVOICE1detail4 = new View(program, "AR0401");
        View ARCUSTOMER1header = new View(program, "AR0024");

        ARINVOICE1batch.compose ( ARINVOICE1header );
        ARINVOICE1header.compose (ARINVOICE1batch, ARINVOICE1detail1, ARINVOICE1detail2, ARINVOICE1detail3, null);
        ARINVOICE1detail1.compose (ARINVOICE1header, ARINVOICE1batch, ARINVOICE1detail4);
        ARINVOICE1detail2.compose (ARINVOICE1header);
        ARINVOICE1detail3.compose (ARINVOICE1header);
        ARINVOICE1detail4.compose (ARINVOICE1detail1);

        // Create the batch

        ARINVOICE1batch.recordGenerate(RecordGenerateMode.Insert);
        ARINVOICE1batch.set("PROCESSCMD","1");      // Process Command

        ARINVOICE1batch.process();
        ARINVOICE1batch.read(false);

        sBatchNum = ARINVOICE1batch.get("CNTBTCH").toString();

        // Loop through creating the entries

        for ( iEntry = 0; iEntry < numEntries; iEntry++ )
        {
            try
            {
                ARINVOICE1detail1.cancel();
                ARINVOICE1detail2.cancel();
                ARINVOICE1header.recordGenerate(RecordGenerateMode.DelayKey);
                ARINVOICE1detail1.recordClear();
                ARINVOICE1detail2.recordClear();

                ARINVOICE1header.set("PROCESSCMD","4");

                ARINVOICE1header.process();

                if ( false == ARCUSTOMER1header.goNext() )
                {
                    ARCUSTOMER1header.goTop();
                }

                ARINVOICE1header.set("IDCUST", "1200");

                for ( iDetail = 0; iDetail < numDetails; iDetail++ )
                {
                    ARINVOICE1detail1.recordClear();
                    ARINVOICE1detail1.recordGenerate (RecordGenerateMode.NoInsert);
                    ARINVOICE1detail1.process();

                    ARINVOICE1detail1.set("IDITEM", "CA-78" );                     // Item Number

                    ARINVOICE1detail1.insert();

                }

                ARINVOICE1header.insert();
            }
            catch( Exception e )
            {
                int count = program.getErrors().getCount();
                if ( 0 == count )
                {
                    e.printStackTrace();                   
                }
                for ( int i = 0; i < count; i++ )
                {
                    System.out.println(program.getErrors().get(i).getMessage());
                }
            }
        }
        ARINVOICE1batch.dispose();
        ARINVOICE1header.dispose();
        ARINVOICE1detail1.dispose();
        ARINVOICE1detail2.dispose();
        ARINVOICE1detail3.dispose();
        ARINVOICE1detail4.dispose();
        ARCUSTOMER1header.dispose();

        return( sBatchNum );
    }
	
}

