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

/**
 * Created by Tamara on 7/29/2015.
 */
public class CommentCustomAdapter extends BaseAdapter {

    ArrayList<Object> commentList;

    public Activity context;
    public LayoutInflater inflater;

    public CommentCustomAdapter(Activity context,ArrayList<Object> itemList) {
        super();

        this.context = context;
        this.commentList = itemList;

        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return commentList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static class ViewHolder
    {
        ImageView ownerImage;
        TextView commentContent;
        TextView ownerName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.comment, null);

            holder.ownerImage = (ImageView) convertView.findViewById(R.id.owner_image);
            holder.ownerName = (TextView) convertView.findViewById(R.id.comment_owner);
            holder.commentContent = (TextView) convertView.findViewById(R.id.comment_content);

            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();

        CommentBean bean = (CommentBean) commentList.get(position);

//        holder.ownerImage.setImageResource(bean.getOwnerImage());
        holder.ownerName.setText(bean.getOwnerName());
        holder.commentContent.setText(bean.getCommentContent());

        return convertView;
    }

}
