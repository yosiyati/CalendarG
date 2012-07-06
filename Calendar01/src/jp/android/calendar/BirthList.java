package jp.android.calendar;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class BirthList extends Activity {

	// CalendarActivityから渡された日付を表示するTextView
	private TextView textview;
	// 戻るボタン
	private Button backButton;
	// 有名人の一覧を表示するListView
	private ListView listView;
	private List<String> dataList = new ArrayList<String>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

		// 日付を表示するTextViewのインスタンス生成
		textview = (TextView) findViewById(R.id.textView1);

		// 戻るボタンのインスタンス生成+onClickListenerの設定
		backButton = (Button) findViewById(R.id.back);
		backButton.setOnClickListener(new backButtonClickListener());

		// インテントを取得
		Intent intent = getIntent();

		// インテントに保存されたデータを取得
		String month = intent.getStringExtra("MONTH");
		String day = intent.getStringExtra("DAY");

		// 取得したmonthとdayを表示させる
		textview.setText(month + "月" + day + "日の誕生日の有名人");

		RegistDataList data = (RegistDataList) intent
				.getSerializableExtra("RegistDataList");

		List<RegistData> registdata = data.getRegistDataList();

		//showListに値を渡し連結さてdatalistに挿入
		for (RegistData rd : registdata) {
			dataList.add(showList(rd));
		}

		// ListViewのインスタンス生成
		listView = (ListView) findViewById(R.id.listview);

		// ArrayAdapterクラスのオブジェクトを作成
		ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this,
				R.layout.listview, dataList);

		// ArrayAdapterクラスのオブジェクトをListViewに設定
		listView.setAdapter(Adapter);
	}

	private String showList(RegistData rd) {
		
		//名前と仕事を取得
		String name = rd.getName();
		String work = rd.getJob();

		StringBuilder sb = new StringBuilder();

		//取得したデータを連結
		sb.append(name);
		sb.append("/");
		sb.append(work);

		return sb.toString();
	}

	class backButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			finish();
		}
	}
}
