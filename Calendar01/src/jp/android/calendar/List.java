package jp.android.calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class List extends Activity {

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

		TextView viewText = (TextView) findViewById(R.id.textView1);
		
		// インテントを取得
		Intent intent = getIntent();
		
		// インテントに保存されたデータを取得
		String month = intent.getStringExtra("MONTH");
		String day = intent.getStringExtra("DAY");
		viewText.setText(month+"月"+day+"日の誕生日の有名人");
	}
}
