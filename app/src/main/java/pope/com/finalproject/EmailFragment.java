package pope.com.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmailFragment extends Fragment implements View.OnClickListener{
    public EditText mailToEt;
    public EditText ccEt;
    public EditText subjectEt;
    public EditText messageEt;
    public Button sendBtn;
    public EmailFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_email, container, false);

        //Set widgets
        mailToEt = rootView.findViewById(R.id.mailToEt);
        ccEt = rootView.findViewById(R.id.ccEt);
        subjectEt = rootView.findViewById(R.id.subjectEt);
        messageEt = rootView.findViewById(R.id.messageEt);
        sendBtn = rootView.findViewById(R.id.sendBtn);

        //Set listener
        sendBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.sendBtn:
                if (mailToEt != null) {
                    sendEmail();
                } else {
                    Toast.makeText(getContext(), "Please make sure to enter an email_item address", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                Toast.makeText(getContext(),"", Toast.LENGTH_LONG).show();
        }
    }
    private void sendEmail() {
        //Sends email if bundle not null
        String mailTo;
        String message;
        Bundle bundle = getArguments();
        if (bundle != null){
            message = bundle.getString("message");
            mailTo = bundle.getString("enter_email");
            String subject = subjectEt.getText().toString();
            String cc = ccEt.getText().toString();

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            if ( subject != null || message != null || mailTo != null ) {

                emailIntent.putExtra(Intent.EXTRA_CC, cc);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, mailTo);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, message);
                Toast.makeText(getContext(), "Success! Email has been sent to " + mailTo, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Please ensure that you have put correct values into the email, subject, and message boxes.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getContext(), "Bundle is null", Toast.LENGTH_LONG).show();
        }
    }
}
