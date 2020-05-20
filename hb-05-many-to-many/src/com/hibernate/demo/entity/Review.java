package com.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "comment" )
	private String comment;

	public Review() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComments() {
		return comment;
	}

	public void setComments(String comments) {
		this.comment = comments;
	}

	public Review(String comments) {
		super();
		this.comment = comments;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", comments=" + comment + "]";
	}
	
}
