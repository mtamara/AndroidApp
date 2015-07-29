package rs.codecentric.praksatwitterapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * Created by Tamara on 7/28/2015.
 */
public class FollowingCustomAdapter extends BaseAdapter {

    ArrayList<Object> followingList;

    public Activity context;
    public LayoutInflater inflater;

    public FollowingCustomAdapter(Activity context, ArrayList<Object> itemList) {
        super();

        this.context = context;
        this.followingList = itemList;

        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return followingList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return followingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static class ViewHolder
    {
        ImageView followingImage;
        TextView followingFirstName;
        TextView followingLastName;
        TextView followingId;
        ImageButton removeFollower;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.following_list, null);

            holder.followingImage = (ImageView) convertView.findViewById(R.id.followingImage);
            holder.followingFirstName = (TextView) convertView.findViewById(R.id.followingFirstName);
            holder.followingLastName = (TextView) convertView.findViewById(R.id.followingLastName);
            holder.followingId = (TextView) convertView.findViewById(R.id.followingId);
            holder.removeFollower = (ImageButton) convertView.findViewById(R.id.remove_follower);

            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();

        FollowingBean bean = (FollowingBean) followingList.get(position);

//        holder.followingImage.setImageResource(bean.getFollowingImage());
        holder.followingFirstName.setText(bean.getFollowingFirstName());
        holder.followingLastName.setText(bean.getFollowingLastName());
        holder.followingId.setText(bean.getFollowingId());
        holder.removeFollower.setContentDescription(bean.getFollowingId());

        return convertView;
    }
}
