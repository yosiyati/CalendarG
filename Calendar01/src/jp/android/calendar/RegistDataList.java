package jp.android.calendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegistDataList implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<RegistData> registdatalist = new ArrayList<RegistData>();

	public void setRegistDataList(RegistData registdata) {
		registdatalist.add(registdata);
	}

	public List<RegistData> getRegistDataList() {
		return registdatalist;
	}

}
