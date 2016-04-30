package com.example.monota.spring.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Sample implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String mail;

	private Date createdAt;

	private Date createdAtTimezone;
}
