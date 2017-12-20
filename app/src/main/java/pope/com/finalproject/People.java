package pope.com.finalproject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vance on 12/16/2017.
 */

public class People extends MainActivity {
    public String name;
    public String email;

    public People(String name, String email){
        this.name = name;
        this.email = email;
    }
    public People(String name){
        this.name = name;
    }
}
