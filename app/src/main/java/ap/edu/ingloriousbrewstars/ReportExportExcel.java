package ap.edu.ingloriousbrewstars;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Created by Sander Peeters on 12/16/2015.
 */
public class ReportExportExcel extends Activity {
    SharedPreferences SP;
    boolean appConfirm;
    final Context context = this;
    Button excelButton;
    EditText emailaddressEditText;
    String emailaddress;
    File wbfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_export_excel);

        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        appConfirm = SP.getBoolean("applicationConfirmation", true);

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setUseTemporaryFileDuringWrite(true);
        wbfile = new File(getFilesDir() + File.separator + "try.xls");

        WritableWorkbook wb = null;

        try{
            wb = Workbook.createWorkbook(wbfile,wbSettings);
            WritableSheet sheet = wb.createSheet("IBS Report", 0);
            createCell(sheet, 0, 0, "Storage Places");
            createCell(sheet, 0, 1, "Categories");
            wb.write();
            wb.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }

        if(wbfile == null){
            Log.d("----FILE----", "NULL");
        }else{
            Log.d("----FILE----", "Not Null");
        }

        Log.d("File", getFilesDir() + File.separator + "try.xls");

        emailaddressEditText = (EditText) findViewById(R.id.email);

        excelButton = (Button) findViewById(R.id.excelButton);
        excelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailaddress = emailaddressEditText.getText().toString();

                Mail m = new Mail("Inglorious.Brew.Stars.App@gmail.com", "IngloriousBrewStarsApp"); //email, wachtwoord
                m.setReceiver(emailaddress);

                try {
                    m.addAttachment(wbfile.getAbsolutePath());

                    if(m.send()) {
                        Toast.makeText(ReportExportExcel.this, "Email was sent successfully.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ReportExportExcel.this, "Email was not sent.", Toast.LENGTH_LONG).show();
                    }
                } catch(Exception e) {
                    //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                    Log.e("MailApp", "Could not send email", e);
                }
            }
        });

        //Ophalen van de applicatiekleuren
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        int appBackColor = SP.getInt("applicationBackColor", 0);
        int appBackColorR = SP.getInt("applicationBackColorR", 0);
        int appBackColorG = SP.getInt("applicationBackColorG", 0);
        int appBackColorB = SP.getInt("applicationBackColorB", 0);
        int appColor = SP.getInt("applicationColor", 0);
        int appColorR = SP.getInt("applicationColorR", 0);
        int appColorG = SP.getInt("applicationColorG", 0);
        int appColorB = SP.getInt("applicationColorB", 0);
        boolean appStandardColor = SP.getBoolean("colorStandard", true);
        if (appStandardColor) {
            getWindow().getDecorView().setBackgroundColor(Color.rgb(238, 233, 233));
            toolbar.setBackgroundDrawable(new ColorDrawable(Color.rgb(59, 89, 152)));
        }
        else {
            if (appBackColor != 0) {
                getWindow().getDecorView().setBackgroundColor(Color.rgb(appBackColorR, appBackColorG, appBackColorB));
            }

            if (appColor != 0) {
                toolbar.setBackgroundDrawable(new ColorDrawable(Color.rgb(appColorR, appColorG, appColorB)));
            }
        }
    }

    public void createCell(WritableSheet sheet, int column, int row, String content) throws WriteException {
        Label newCell = new Label(row, column, content);
        sheet.addCell(newCell);
    }
}