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
public class MessageFragment extends Fragment {
    public Bundle bundle;
    public EditText messageEt2;

    public MessageFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);
        messageEt2 = rootView.findViewById(R.id.messageEt2);
        bundle = new Bundle();
        setMessage();
        return rootView;
    }
    protected Bundle setMessage() {
        String message;
        bundle = getArguments();
        if (bundle != null) {
            message = bundle.getString("message").toString();
            messageEt2.setText(message);
            //Toast.makeText(getContext(), "The email address " + message + " has been added.", Toast.LENGTH_SHORT).show();
            //messageEt.setText(message);
        }
        return bundle;
    }
}
