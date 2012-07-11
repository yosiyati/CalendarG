package jp.android.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CalendarActivity extends Activity implements OnClickListener {

	// 前月のボタン
	private Button prevMonth;
	// 次月のボタン
	private Button nextMonth;
	// 年月を表示するTextView
	private TextView dateText;
	// 1か月の日付のボタン
	private Button[] btn_table = new Button[43];
	// 現在注目している年月を保持する変数
	private GregorianCalendar mCalendar = new GregorianCalendar();

	private Calendar calendar = Calendar.getInstance();
	private int year = mCalendar.get(Calendar.YEAR); // 年をyearに設定
	private int month = calendar.get(Calendar.MONTH); // 月をmonthに設定
	private int day = calendar.get(Calendar.DAY_OF_MONTH);
	private int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

	private RegistDao registDao;
	private SQLiteDatabase db;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		DatabaseHelper dbHelper = new DatabaseHelper(this);
		db = dbHelper.getReadableDatabase();

		// Helperクラスからdbを取得したものをDaoのコンストラクタに設定
		registDao = new RegistDao(db);

		// 前月ボタンのインスタンス生成+onClickListenerを設定
		prevMonth = (Button) findViewById(R.id.prevMonth);
		prevMonth.setOnClickListener(this);

		// 次月ボタンのインスタンス生成+onClickListenerを設定
		nextMonth = (Button) findViewById(R.id.nextMonth);
		nextMonth.setOnClickListener(this);

		// 年月を表示するTextViewのインスタンス生成
		dateText = (TextView) findViewById(R.id.dateText);

		// 日付ボタンのインスタンス生成
		btn_table[1] = (Button) this.findViewById(R.id.btn_1);
		btn_table[2] = (Button) this.findViewById(R.id.btn_2);
		btn_table[3] = (Button) this.findViewById(R.id.btn_3);
		btn_table[4] = (Button) this.findViewById(R.id.btn_4);
		btn_table[5] = (Button) this.findViewById(R.id.btn_5);
		btn_table[6] = (Button) this.findViewById(R.id.btn_6);
		btn_table[7] = (Button) this.findViewById(R.id.btn_7);
		btn_table[8] = (Button) this.findViewById(R.id.btn_8);
		btn_table[9] = (Button) this.findViewById(R.id.btn_9);
		btn_table[10] = (Button) this.findViewById(R.id.btn_10);
		btn_table[11] = (Button) this.findViewById(R.id.btn_11);
		btn_table[12] = (Button) this.findViewById(R.id.btn_12);
		btn_table[13] = (Button) this.findViewById(R.id.btn_13);
		btn_table[14] = (Button) this.findViewById(R.id.btn_14);
		btn_table[15] = (Button) this.findViewById(R.id.btn_15);
		btn_table[16] = (Button) this.findViewById(R.id.btn_16);
		btn_table[17] = (Button) this.findViewById(R.id.btn_17);
		btn_table[18] = (Button) this.findViewById(R.id.btn_18);
		btn_table[19] = (Button) this.findViewById(R.id.btn_19);
		btn_table[20] = (Button) this.findViewById(R.id.btn_20);
		btn_table[21] = (Button) this.findViewById(R.id.btn_21);
		btn_table[22] = (Button) this.findViewById(R.id.btn_22);
		btn_table[23] = (Button) this.findViewById(R.id.btn_23);
		btn_table[24] = (Button) this.findViewById(R.id.btn_24);
		btn_table[25] = (Button) this.findViewById(R.id.btn_25);
		btn_table[26] = (Button) this.findViewById(R.id.btn_26);
		btn_table[27] = (Button) this.findViewById(R.id.btn_27);
		btn_table[28] = (Button) this.findViewById(R.id.btn_28);
		btn_table[29] = (Button) this.findViewById(R.id.btn_29);
		btn_table[30] = (Button) this.findViewById(R.id.btn_30);
		btn_table[31] = (Button) this.findViewById(R.id.btn_31);
		btn_table[32] = (Button) this.findViewById(R.id.btn_32);
		btn_table[33] = (Button) this.findViewById(R.id.btn_33);
		btn_table[34] = (Button) this.findViewById(R.id.btn_34);
		btn_table[35] = (Button) this.findViewById(R.id.btn_35);
		btn_table[36] = (Button) this.findViewById(R.id.btn_36);
		btn_table[37] = (Button) this.findViewById(R.id.btn_37);
		btn_table[38] = (Button) this.findViewById(R.id.btn_38);
		btn_table[39] = (Button) this.findViewById(R.id.btn_39);
		btn_table[40] = (Button) this.findViewById(R.id.btn_40);
		btn_table[41] = (Button) this.findViewById(R.id.btn_41);
		btn_table[42] = (Button) this.findViewById(R.id.btn_42);

		// 現在の年月を表示するTextViewにセット
		dateText.setText(String.valueOf(String.valueOf(year + "年" + (month + 1)
				+ "月")));

		mCalendar.set(year, month, 1);
		day = mCalendar.get(Calendar.DAY_OF_MONTH);
		dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);

		// 日1,月2,・・・土7
		mCalendar.add(Calendar.MONTH, 1);
		mCalendar.add(Calendar.DATE, -1);
		int lastDate = mCalendar.get(Calendar.DATE);

		// 日付ボタンのonClickLitenerの設定
		for (int i = 1; i <= 42; i++) {
			btn_table[i].setOnClickListener(this);
		}

		// 日付ボタンに日を設定
		for (int i = dayOfWeek; i <= dayOfWeek + lastDate - 1; i++) {
			btn_table[i].setText(String.valueOf(day));
			btn_table[i].setTextSize(15);
			day = day + 1;
		}
	}

	// ボタンがクリックされた時の処理
	@Override
	public void onClick(View v) {

		// 前月(prevMonth)ボタンを押下した時の処理
		if (v == prevMonth) {

			if (month == 0) {
				year = year - 1;
				month = 11;
			} else {
				month = month - 1;
			}

			// 次月(nextMonth)ボタンが押下された時の処理
		} else if (v == nextMonth) {

			if (month == 11) {
				year = year + 1;
				month = 0;
			} else {
				month = month + 1;
			}
		}

		// 前月・次月ボタンが押下された時レイアウトの日付を変更
		dateText.setText(String.valueOf(String.valueOf(year + "年" + (month + 1)
				+ "月")));
		calendar.set(year, month, 1);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		int lastDate = calendar.get(Calendar.DATE);
		calendar.set(year, month, 1);

		// 日1,月2,・・・土7
		for (int i = 1; i < dayOfWeek; i++) {
			btn_table[i].setText("");
		}

		// 前月・翌月のボタンが押下されたとき新たに日付ボタンに日を設定する処理
		for (int i = dayOfWeek; i <= dayOfWeek + lastDate - 1; i++) {
			btn_table[i].setText(String.valueOf(day));
			btn_table[i].setTextSize(15);
			day = day + 1;
		}

		// 初日より前のボタンと、末日より後のボタンには空白文字を入れる処理
		for (int i = dayOfWeek + lastDate; i <= 42; i++) {
			btn_table[i].setText("");
		}

		// 日付ボタンが押されたときの処理
		for (int i = dayOfWeek; i <= dayOfWeek + lastDate - 1; i++) {
			if (v == btn_table[i]) {
				// monthとdayをString型に変換
				String Month = String.valueOf(month + 1);
				String Day = String.valueOf(day - lastDate);

				// 月日を渡して該当する有名人を検索
				List<RegistData> registList = registDao.findAll(Month, Day);

				// 検索した有名人をセットする
				RegistDataList registdatalist = new RegistDataList();
				for (RegistData registdata : registList) {
					registdatalist.setRegistDataList(registdata);
				}

				// 画面遷移するときに使う処理
				Intent intent = new Intent();
				intent.setClass(CalendarActivity.this, BirthList.class);
				intent.putExtra("MONTH", String.valueOf(month + 1));
				intent.putExtra("DAY", String.valueOf(day - lastDate));
				intent.putExtra("RegistDataList", registdatalist);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
			}
			day = day + 1;
		}
	}
}