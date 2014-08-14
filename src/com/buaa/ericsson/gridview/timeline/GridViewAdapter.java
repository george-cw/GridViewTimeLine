package com.buaa.ericsson.gridview.timeline;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridViewAdapter extends BaseAdapter
{

	private Context context;
	private LayoutInflater layoutInflater;
    String fileName;//以name存在目录中
	private int size;

	private Integer[] imgIds1 = { R.drawable.icon002, R.drawable.icon002, R.drawable.icon002};
	private Integer[] imgIds2 = { R.drawable.personal_circle, R.drawable.f, R.drawable.icon003, R.drawable.icon004};
	private Integer[] imgIds3 = { R.drawable.icon001, R.drawable.icon002, R.drawable.icon003, R.drawable.icon004,
			R.drawable.icon005, R.drawable.icon001, R.drawable.icon002, R.drawable.icon003 };

	/**
	 * 构造函数
	 * @param context
	 * @param size
	 */
	public GridViewAdapter(Context context, int size)
	{

		this.context = context;
		this.size = size;
		this.layoutInflater = LayoutInflater.from(context);
	}

	public int getCount()
	{
		// TODO Auto-generated method stub

		if (size == 0)
			return imgIds1.length;
		else if (size == 1)
			return imgIds2.length;
		else
			return imgIds3.length;

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

		ImageView iv;
		if (convertView == null)
		{	//加载一张图片，这里暂时是70dp x 70dp
			convertView = layoutInflater.inflate(R.layout.gv_item, null);
		}
		 fileName = getFileName();
		iv = (ImageView) convertView.findViewById(R.id.image);
		iv.setImageBitmap(getImageThumbnail(fileName,50,50));
//		if (size == 0)
//			iv.setImageResource(imgIds1[position]);
//		if (size == 1)
//			iv.setImageResource(imgIds2[position]);
//		else
//			iv.setImageResource(imgIds3[position]);

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

	/**
	 * 根据指定的图像路径和大小来获取缩略图
	 * 此方法有两点好处：
	 *     1. 使用较小的内存空间，第一次获取的bitmap实际上为null，只是为了读取宽度和高度，
	 *        第二次读取的bitmap是根据比例压缩过的图像，第三次读取的bitmap是所要的缩略图。
	 *     2. 缩略图对于原图像来讲没有拉伸，这里使用了2.2版本的新工具ThumbnailUtils，使
	 *        用这个工具生成的图像不会被拉伸。
	 * @param imagePath 图像的路径
	 * @param width 指定输出图像的宽度
	 * @param height 指定输出图像的高度
	 * @return 生成的缩略图
	 */
	private Bitmap getImageThumbnail(String imagePath, int width, int height) {
		Bitmap bitmap = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// 获取这个图片的宽和高，注意此处的bitmap为null
		bitmap = BitmapFactory.decodeFile(imagePath, options);
		options.inJustDecodeBounds = false; // 设为 false
		// 计算缩放比
		int h = options.outHeight;
		int w = options.outWidth;
		int beWidth = w / width;
		int beHeight = h / height;
		int be = 1;
		if (beWidth < beHeight) {
			be = beWidth;
		} else {
			be = beHeight;
		}
		if (be <= 0) {
			be = 1;
		}
		options.inSampleSize = be;
		// 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
		bitmap = BitmapFactory.decodeFile(imagePath, options);
		// 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
				ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}
}


