package model.comment;

import java.util.Date;

public class Comment {
	private int num;
	private int seq;
	private String writer;
	private String content;
	private Date regdate;
	//getter,setter, toString
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "Comment [num=" + num + ", seq=" + seq + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + "]";
	}
	

}
