package rs.codecentric.praksatwitterapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

/**
 * Created by Tamara on 7/24/2015.
 */
public class TweetAdapter extends ArrayAdapter {

    private LayoutInflater inflater;

    public TweetAdapter(Activity activity, String[] items){
        super(activity, R.layout.tweet, items);
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return inflater.inflate(R.layout.tweet, parent, false);
    }

}
