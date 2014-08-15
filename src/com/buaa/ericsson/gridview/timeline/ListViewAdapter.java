package com.buaa.ericsson.gridview.timeline;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.Activity;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseAdapter
{

	private Context context;
	private HashMap<String, ArrayList<ImageInfo>> imageInfoMap;
	private String[] timeMap;
	private LayoutInflater layoutInflater;
	static boolean i = true;
	GridViewTimeLineActivity activity;

	public ListViewAdapter(Context context, String[] timeMap, HashMap<String, ArrayList<ImageInfo>> imageInfoMap, GridViewTimeLineActivity activity)
	{

		this.context = context;
		this.timeMap = timeMap;
		this.imageInfoMap = imageInfoMap;
		this.layoutInflater = LayoutInflater.from(context);
		this.activity = activity;
	}

	public int getCount()
	{
		// TODO Auto-generated method stub
		return imageInfoMap.size();
	}

	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{

		TextView tv;
		MyGridView gv;
		if (convertView == null)
		{
			convertView = layoutInflater.inflate(R.layout.listview_item, null);
		}

		tv = (TextView) convertView.findViewById(R.id.TextView01);
		gv = (MyGridView) convertView.findViewById(R.id.gridview1);
	
		int[] colors = { Color.RED, Color.GREEN, Color.BLUE};//RGB颜色
		tv.setTextColor(colors[position%3]);
		tv.setVisibility(View.VISIBLE);
		tv.setText(timeMap[position]);
		gv.setAdapter(new GridViewAdapter(context, position));

		gv.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				//System.out.println(position);
				//for (mCursor.moveToFirst(); position > 0; mCursor.moveToNext())
					//position--;
				if (i) {
//					((ImageView) view).setImageBitmap(BitmapFactory
//							.decodeFile(getFileName()));
					File file=new File(getFileName());
					Intent it =new Intent(Intent.ACTION_VIEW);
					Uri mUri = Uri.parse("file://"+file.getPath());
					it.setDataAndType(mUri, "image/*");
					i = false;
					activity.startActivity(it);
				} else {
//					((ImageView) view).setImageBitmap(BitmapFactory
//							.decodeStream(getFileName()));
					i = true;
				}
			}
		});

		return convertView;
	}
	/**
	 * 获取sd卡的路径
	 * @param null
	 * @return sd卡的路径
	 */
	public String getSDPath(){ 
	       File sdDir = null; 
	       boolean sdCardExist = Environment.getExternalStorageState()   
	                           .equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在 
	       if   (sdCardExist)   
	       {                               
	         sdDir = Environment.getExternalStorageDirectory();//获取跟目录 
	      }   
	       return sdDir.toString(); 
	       
	}
	/**
	 * 获取文件路径+名字
	 * @return
	 */
	public String getFileName()
	{
		final String name = "DCIM/Camera/IMG_20140813_224433.jpg";
		return getSDPath() +"/" + name;
	}

}
