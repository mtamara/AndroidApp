package rs.codecentric.praksatwitterapp;

import android.content.Context;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Tamara on 7/24/2015.
 */
public class OptionsActionProvider extends ActionProvider {

    Context mContext;
    public OptionsActionProvider(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View onCreateActionView() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.options ,null);
        return view;
    }
}
