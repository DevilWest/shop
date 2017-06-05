package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_product")
public class Product implements Serializable{

	private static final long serialVersionUID = 2429695896963809305L;

	private long id;
	
	private String name ;
	
	private double price;
	
	private Date createtime;
	
	private String info;

	private int productlock;

	private String path;
	
	private User user;
	
	private int deletestatus;
	
	public int getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(int deletestatus) {
		this.deletestatus = deletestatus;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}


	@Column(name="info", columnDefinition="TEXT")
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	

	public int getProductlock() {
		return productlock;
	}

	public void setProductlock(int productlock) {
		this.productlock = productlock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@ManyToOne
	@JoinColumn(name="userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
