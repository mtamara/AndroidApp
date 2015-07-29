package rs.codecentric.praksatwitterapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tamara on 7/28/2015.
 */
public class TweetCustomAdapter extends BaseAdapter {

    ArrayList<Object> tweetList;

    public Activity context;
    public LayoutInflater inflater;

    public TweetCustomAdapter(Activity context,ArrayList<Object> itemList) {
        super();

        this.context = context;
        this.tweetList = itemList;

        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return tweetList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return tweetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static class ViewHolder
    {
        ImageView userImage;
        TextView tweetContent;
        TextView userData;
        TextView tweetDate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.tweet, null);

            holder.userImage = (ImageView) convertView.findViewById(R.id.userImage);
            holder.userData = (TextView) convertView.findViewById(R.id.userData);
            holder.tweetContent = (TextView) convertView.findViewById(R.id.tweetContent);
            holder.tweetDate = (TextView) convertView.findViewById(R.id.tweetDate);

            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();

        TweetBean bean = (TweetBean) tweetList.get(position);

//        holder.userImage.setImageResource(bean.getUserImage());
        holder.userData.setText(bean.getUserData());
        holder.tweetContent.setText(bean.getTweetContent());
        holder.tweetDate.setText(bean.getTweetDate());

        return convertView;
    }

}
