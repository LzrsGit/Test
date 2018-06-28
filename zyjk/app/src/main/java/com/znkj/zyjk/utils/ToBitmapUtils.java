package com.znkj.zyjk.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;

import java.io.InputStream;


public class ToBitmapUtils {
    /**
     * 计算位图的采样比例大小
     * @param options
     * @param imageView 控件(根据控件的大小进行压缩)
     * @return
     */
    private static int calculatInSampleSize(BitmapFactory.Options options, ImageView imageView) {
        //获取位图的原宽高
        final int w = options.outWidth;
        final int h = options.outHeight;

        if (imageView!=null){
            //获取控件的宽高
            final int reqWidth = imageView.getWidth();
            final int reqHeight = imageView.getHeight();

            //默认为一(就是不压缩)
            int inSampleSize = 1;
            //如果原图的宽高比需要的图片宽高大
            if (w > reqWidth || h > reqHeight) {
                if (w > h) {
                    inSampleSize = Math.round((float) h / (float) reqHeight);
                } else {
                    inSampleSize = Math.round((float) w / (float) reqWidth);
                }
            }

            System.out.println("压缩比为:" + inSampleSize);

            return inSampleSize;

        }else {
            return 1;
        }
    }

    /**
     * 将Uri转换成Bitmap
     * @param context
     * @param uri
     * @param options
     * @return
     */
    public static Bitmap decodeBitmap(Context context, Uri uri, BitmapFactory.Options options) {
        Bitmap bitmap = null;

        if (uri != null) {
            ContentResolver cr = context.getContentResolver();
            InputStream inputStream = null;
            try {
                /**
                 * 将图片的Uri地址转换成一个输入流
                 */
                inputStream = cr.openInputStream(uri);

                /**
                 * 将输入流转换成Bitmap
                 */
                bitmap = BitmapFactory.decodeStream(inputStream, null, options);

                assert inputStream != null;
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    /**
     * 对图片进行重新采样
     * @param context
     * @param uri 图片的Uri地址
     * @param imageView
     * @return
     */
    public static Bitmap compressBitmap(Context context, Uri uri, ImageView imageView) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        decodeBitmap(context, uri, options);
        options = new BitmapFactory.Options();
        options.inSampleSize = calculatInSampleSize(options, imageView);
        Bitmap bitmap = null;
        try {
            bitmap = decodeBitmap(context, uri, options);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

}
