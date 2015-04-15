package com.lyp.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity  implements View.OnClickListener {

	LinearLayout headView = null;
	LinearLayout settingView = null;
	LinearLayout xyrdView = null;
	LinearLayout shareView = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		headView = (LinearLayout)findViewById(R.id.user_info);
		settingView = (LinearLayout)findViewById(R.id.action_settings);
		xyrdView = (LinearLayout)findViewById(R.id.action_xqrd);
		shareView = (LinearLayout)findViewById(R.id.action_share);

		headView.setOnClickListener(this);
		settingView.setOnClickListener(this);
		xyrdView.setOnClickListener(this);
		shareView.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
        switch (v.getId()){
			case R.id.user_info:
				Toast.makeText(this,"用户信息",Toast.LENGTH_LONG).show();
				break;
			case R.id.action_settings:
				Toast.makeText(this,"通用设置",Toast.LENGTH_LONG).show();
				break;
			case R.id.action_xqrd:
				Toast.makeText(this,"校区认定",Toast.LENGTH_LONG).show();
				break;
			case R.id.action_share:
				Toast.makeText(this,"快速分享",Toast.LENGTH_LONG).show();
				break;

		}
	}
}
