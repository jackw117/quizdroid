package edu.washington.jackw117.quizdroid3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jack on 2/12/2017.
 */

public class CustomAdapter extends BaseAdapter {
    private String [] result;
    private Context context;
    private int [] imageId;
    private String[] shortDesc;
    private static LayoutInflater inflater = null;

    public CustomAdapter(MainActivity mainActivity, String[] prgmNameList, int[] prgmImages, String[] shortDescList) {
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
        shortDesc = shortDescList;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View rowView;
        rowView = inflater.inflate(R.layout.program_list, null);
        TextView ttv=(TextView) rowView.findViewById(R.id.textView1);
        ImageView img=(ImageView) rowView.findViewById(R.id.imageView1);
        TextView sdtv=(TextView) rowView.findViewById(R.id.shortDesc);
        ttv.setText(result[position]);
        img.setImageResource(imageId[position]);
        sdtv.setText(shortDesc[position]);
        return rowView;
    }

}
