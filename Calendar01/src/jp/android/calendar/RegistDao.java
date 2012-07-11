package jp.android.calendar;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RegistDao {

	private static final String TABLE_NAME = "birth";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_JOB = "job";

	private SQLiteDatabase db;

	public RegistDao(SQLiteDatabase db) {
		this.db = db;
	}

	/*
	 * 全レコード取得処理
	 */
	public List<RegistData> findAll(String month, String day) {
		List<RegistData> addressList = new ArrayList<RegistData>();

		// SQL
		String sql = "select " + COLUMN_NAME + ", " + COLUMN_JOB + " from "
				+ TABLE_NAME + " where birth.month = " + month
				+ " and birth.day = " + day + ";";

		// SQLを実行して該当データを抽出
		Cursor cursor = db.rawQuery(sql, null);

		// 取得できたレコードの数だけ繰り返す
		// moveToNext()は次のレコードが取得出来たらtrueを返す
		while (cursor.moveToNext()) {
			RegistData registdata = new RegistData();
			registdata.setName(cursor.getString(0));
			registdata.setJob(cursor.getString(1));

			// 取得したレコードをリストに詰めていく
			addressList.add(registdata);
		}

		// テーブルから取得したレコードリストを返す
		return addressList;
	}
}
