package org.opencv.samples.facedetect;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ChosePic extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.chose_photo);

		Button backbtn = (Button) findViewById(R.id.backbtn);
		backbtn.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ChosePic.this, HomeActivity.class);
				startActivity(intent);
				ChosePic.this.finish();
			}

		});

		Button pickBtn = (Button) findViewById(R.id.chosePic);
		pickBtn.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				/* 开启Pictures画面Type设定为image */
				intent.setType("image/*");
				/* 使用Intent.ACTION_GET_CONTENT这个Action */
				intent.setAction(intent.ACTION_GET_CONTENT);
				/* 取得相片后返回本画面 */
				startActivityForResult(intent, 1);
			}

		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (resultCode == RESULT_OK) {
			Uri uri = data.getData();
			Log.e("uri", uri.toString());
			ContentResolver cResolver = this.getContentResolver();
			try {
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 2;
				Bitmap bitmap = BitmapFactory.decodeStream(
						cResolver.openInputStream(uri), null, options);
				ImageView imageView = (ImageView) findViewById(R.id.imgPic);
				/* 将Bitmap设定到ImageView */
				imageView.setImageBitmap(bitmap);
				Toast.makeText(getApplicationContext(), "图片已载入",
						Toast.LENGTH_SHORT).show();
			} catch (FileNotFoundException e) {
				Log.e("Exception", e.getMessage(), e);

			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

//	public void caculateSampleSize() {
//		
//	}
//	
//	public void decodeBitmap(){
//		
//	}
}