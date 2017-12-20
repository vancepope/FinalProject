package pope.com.finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class MailToFragment extends Fragment {
    public Bundle bundle;
    public EditText mailToEt2;
    public MailToFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mail_to, container, false);
        mailToEt2 = rootView.findViewById(R.id.mailToEt2);
        bundle = new Bundle();
            setEmail();
        return rootView;
    }
    protected Bundle setEmail() {
        String mailTo;
        //String message;
        bundle = getArguments();
        if (bundle != null) {
            mailTo = bundle.getString("enter_email").toString();
            mailToEt2.setText(mailTo);
            Toast.makeText(getContext(), "The email address " + mailTo + " has been added.", Toast.LENGTH_SHORT).show();
            //messageEt.setText(message);
        }
        return bundle;
    }
}
