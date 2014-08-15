package com.buaa.ericsson.gridview.timeline;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;

public class GridViewTimeLineActivity extends Activity
{

	public static HashMap<String, ArrayList<ImageInfo>> staticImageInfo = new HashMap<String, ArrayList<ImageInfo>>();
	//图片映射信息
	private HashMap<String, ArrayList<ImageInfo>> imageInfoMap = new HashMap<String, ArrayList<ImageInfo>>();
	//日期
	private String[] timeMap = { "2012-12-11", "2012-12-10", "一周前", "一月前" };
	//view列表
	private ListView listView;
	private TextView tv_head;


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initData();													//数据初始化

		tv_head = (TextView) this.findViewById(R.id.head_timeline);
		listView = (ListView) this.findViewById(R.id.lv);
		listView.setAdapter(new ListViewAdapter(this, timeMap, imageInfoMap,this));

		listView.setOnScrollListener(new OnScrollListener()	
		{

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState)
			{
				// TODO Auto-generated method stub


			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
			{
				tv_head.setText(timeMap[firstVisibleItem]);
			}
		});

	}
	
	private void initData()
	{

		ArrayList<ImageInfo> list = new ArrayList<ImageInfo>();		//图片信息数组

		for (int i = 0; i < 5; i++)
		{
			ImageInfo ii = new ImageInfo();
			ii.setName("img_" + i);
			list.add(ii);
		}

		for (int i = 0; i < 4; i++)
		{
			imageInfoMap.put(timeMap[i], list);						//一个时间对应一组图片信息
		}
	}

}