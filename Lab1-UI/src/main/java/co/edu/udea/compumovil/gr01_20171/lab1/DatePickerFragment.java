package co.edu.udea.compumovil.gr01_20171.lab1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by davida.marin on 15/02/17.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener
{
    String strMonth;
    EditText et;
    private View view;

    public DatePickerFragment(EditText et) {

        this.et = et;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //this.et = (EditText) getView().findViewById(R.id.et_fecha_nacimiento);
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();

        SimpleDateFormat month_date = new SimpleDateFormat("MMM");

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        strMonth = month_date.format(c.getTime());
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        et.setText(String.valueOf(day)+" - "+
               strMonth +" - "+
                String.valueOf(year));
        Toast.makeText(view.getContext(),"holaa",Toast.LENGTH_SHORT).show();
    }
}
