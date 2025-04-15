package main;

import java.util.Date;

public class Student {

	private int studno;
	private String name;
	private String id;
	private int grade;
	private String jumin;
	private Date birthday;
	private int height;
	private int weight;
	private int major1;
	private int major2;
	private int profno;
	public int getStudno() {
		return studno;
	}
	public void setStudno(int studno) {
		this.studno = studno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getMajor1() {
		return major1;
	}
	public void setMajor1(int major1) {
		this.major1 = major1;
	}
	public int getMajor2() {
		return major2;
	}
	public void setMajor2(int major2) {
		this.major2 = major2;
	}
	public int getProfno() {
		return profno;
	}
	public void setProfno(int profno) {
		this.profno = profno;
	}
	@Override
	public String toString() {
		return "Student [studno=" + studno + ", name=" + name + ", id=" + id + ", grade=" + grade + ", jumin=" + jumin
				+ ", birthday=" + birthday + ", height=" + height + ", weight=" + weight + ", major1=" + major1
				+ ", major2=" + major2 + ", profno=" + profno + "]";
	}
}
