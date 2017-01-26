package com.sap.cloudwave.caspott.DataObjectsHandler;

import java.util.List;

public class RawLogDataHandler {

	private String _index;
	private String _type;
	private String _id;
	private String _score;
	private RawLogDataSource _source;
	private RawLogFields fields;
	private List<String> sort;

	public RawLogDataHandler() {

	}

	public String get_index() {
		return _index;
	}

	public void set_index(String _index) {
		this._index = _index;
	}

	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_score() {
		return _score;
	}

	public void set_score(String _score) {
		this._score = _score;
	}

	public RawLogDataSource get_source() {
		return _source;
	}

	public void set_source(RawLogDataSource _source) {
		this._source = _source;
	}

	public RawLogFields getFields() {
		return fields;
	}

	public void setFields(RawLogFields fields) {
		this.fields = fields;
	}

	public List<String> getSort() {
		return sort;
	}

	public void setSort(List<String> sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		String logData = "\n _index:" + this._index + "\n _type:" + this._type + "\n _id:" + this._id + "\n _score:"
				+ this._score + "\n" + _source.toString() + "\n" + fields.toString() + "\n" + "\n Sort:" + this.sort;
		return logData;

	}

}
