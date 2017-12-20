package pope.com.finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static android.widget.Toast.LENGTH_LONG;


/**
 * A simple {@link Fragment} subclass.
 */
public class NamesFragment extends Fragment implements View.OnClickListener{

    public PeopleAdapter adapter;
    public ArrayList<People> naughtyOrNiceList;
    public HashMap<String, String> resultsList;
    public HashMap<People, People> namesPicked;
    public List<People> namesTaken;
    public String name;
    public String email;
    public String result;
    public TextView listItemTv;
    public TextView subItemTv;
    public ListView list;
    public Button addBtn;
    public Button resetBtn;
    public Button pickBtn;
    public Bundle bundle;
    public Bundle messageBundle;
    public EditText nameEt;
    public EditText emailEt;
    public NamesFragment() {
        // Required empty public constructor
    }
    public NamesFragment newInstance(){
        NamesFragment namesFragment = new NamesFragment();
        return namesFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_names, container, false);

        //Set widgets
        addBtn = rootView.findViewById(R.id.addBtn);
        nameEt = rootView.findViewById(R.id.nameEt);
        resetBtn = rootView.findViewById(R.id.resetBtn);
        pickBtn = rootView.findViewById(R.id.pickBtn);
        emailEt = rootView.findViewById(R.id.emailEt);
        list = rootView.findViewById(R.id.list);
        resetBtn = rootView.findViewById(R.id.resetBtn);
        subItemTv = rootView.findViewById(R.id.subItemTv);
        listItemTv = rootView.findViewById(R.id.listItemTv);

        //Initialize lists
        naughtyOrNiceList = new ArrayList<>();
        resultsList = new HashMap<>();
        namesPicked = new HashMap<>();
        namesTaken = new ArrayList<>();

        //Set bundle for passing data
        bundle = new Bundle();

        //Set listeners
        addBtn.setOnClickListener(this);
        pickBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        //On click, person's email and generated message is added to email screen
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Switches layouts to show data from bundle
                setMailTo(position);
                setMessage(position);
            }
        });
        return rootView;
    }

    public void addNames(){
        //Get name  and email_item from edit textbox
        name = nameEt.getText().toString();
        email = emailEt.getText().toString();

        //Add people to Secret Santa list
        People people = new People(name, email);
        naughtyOrNiceList.add(people);

        adapter = new PeopleAdapter(getContext(), naughtyOrNiceList);
        list.setAdapter(adapter);
        //Reset edit textboxes
        nameEt.setText("");
        emailEt.setText("");

        //Notify the user that people have been added
        Toast.makeText(getContext(), "You have added " + name + " to the naughty or nice list", Toast.LENGTH_LONG).show();
    }
    public void resetNames() {
        //Remove names from Array list and reset list view
        if(naughtyOrNiceList.size() != 0) {
            naughtyOrNiceList.clear();
            MailToFragment mailToFragment = new MailToFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mailToLayout, mailToFragment).commit();
            MessageFragment messageFragment = new MessageFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.messageLayout, messageFragment).commit();
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "Error: No names to reset. Please enter names before resetting.", Toast.LENGTH_LONG).show();
        }
    }
    public HashMap<People, People> shuffle(HashMap<People, People> namesPicked) {
        Random random = new Random();
        int nameCnt = naughtyOrNiceList.size();

        //Goes through list and picks a random partner
        for(int i=0; i< nameCnt; i++){
            int randNum = random.nextInt(nameCnt);
            String name = naughtyOrNiceList.get(i).name;
            String name2 = naughtyOrNiceList.get(randNum).name;
            People player = new People(name);
            People niceList = new People(name2);
            boolean picked = namesPicked.keySet().contains(niceList);
                if ( namesPicked.size() == 0 && ( picked == true || name.equals(name2))){
                    i--;
                }   else if ( namesPicked.size() > 0 && ( picked == true || name.equals(name2))){
                    namesPicked.remove(player);
                    i--;
                }   else {
                    namesPicked.put(player, niceList);
                }
            randNum++;
            }
        return namesPicked;
    }
    public HashMap<String, String> pickNames(){
        if (naughtyOrNiceList.size()!= 0) {
            shuffle(namesPicked);
            int i = 0;
            String secretSantaMsg = "Please make sure not to let anyone know who you have and keep gift purchases at the agreed upon price limit. Pray that you don't end up on the naughty list!!";
            for (Map.Entry<People, People> entry : namesPicked.entrySet()) {
                String key = entry.getKey().name;
                String value = entry.getValue().name;
                People people = new People(key);
                result = String.format("%s, give your gift to -%s. %s", key, value, secretSantaMsg);
                resultsList.put(people.name, result);
                i++;
            }
            Toast.makeText(getContext(), "Done! Click name on list to fill email boxes", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Error: No names to pick.", Toast.LENGTH_LONG).show();
        }
        return resultsList;
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addBtn:
                addNames();
                break;
            case R.id.pickBtn:
                pickNames();
                break;
            case R.id.resetBtn:
                resetNames();
                break;
            default:
                Toast.makeText(getContext(),"", LENGTH_LONG).show();
        }
    }
    public Bundle setMailTo(int position){
        //Loads genearated data into mailToFragment
        if (naughtyOrNiceList.size() != 0) {
            email = naughtyOrNiceList.get(position).email;
            bundle = new Bundle();
            bundle.putString("enter_email", email);
            MailToFragment mailToFragment = new MailToFragment();
            mailToFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mailToLayout, mailToFragment).commit();

        } else {
            Toast.makeText(getContext(), "Please hit Pick button before clicking in the list", Toast.LENGTH_LONG).show();
        }
        return bundle;
    }
    public Bundle setMessage(int position) {
        //Loads generated data into messageFragment
        String message;
        if (resultsList.size() != 0) {
            message = resultsList.get(naughtyOrNiceList.get(position).name);
                    messageBundle = new Bundle();
                    messageBundle.putString("message", message);
                    MessageFragment messageFragment = new MessageFragment();
                    messageFragment.setArguments(messageBundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.messageLayout, messageFragment).commit();
            } else {
            Toast.makeText(getContext(), "Please hit Pick button before clicking in the list", Toast.LENGTH_LONG).show();
        }
        return messageBundle;
    }
}
