package com.system.web.form;

import java.io.Serializable;

public class DatatableForm implements Serializable {

	private static final long serialVersionUID = -2346849485192864428L;

	private Integer draw;

	private Integer start;

	private Integer length;
	

	@Override
	public String toString() {
		return "DatatableForm [draw=" + draw + ", start=" + start + ", length=" + length + "]";
	}

	public DatatableForm() {
		super();
	}

	public DatatableForm(Integer draw, Integer start, Integer length) {
		super();
		this.draw = draw;
		this.start = start;
		this.length = length;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

}
