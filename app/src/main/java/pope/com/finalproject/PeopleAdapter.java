package pope.com.finalproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vance on 12/16/2017.
 */

public class PeopleAdapter extends ArrayAdapter<People> {

    public PeopleAdapter(@NonNull Context context, ArrayList<People> nameList) {
        super(context, 0,nameList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        People people = getItem(position);
            if (convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items,parent, false);
            }
            TextView listItemTv = convertView.findViewById(R.id.listItemTv);
            TextView subItemTv = convertView.findViewById(R.id.subItemTv);

            listItemTv.setText(people.name);
            subItemTv.setText(people.email);

            return convertView;
    }
}
