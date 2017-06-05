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
@Table(name="t_Jiaoyi")
public class Jiaoyi implements Serializable{

	private static final long serialVersionUID = 2429695896963809305L;

	private long id;
	
	private User userfrom;
	
	private User userto;
	
	private Product product;
	
	private String content;
	
	private Date createtime;
	
	private String status;
	
	
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name="userfromid")
	public User getUserfrom() {
		return userfrom;
	}

	public void setUserfrom(User userfrom) {
		this.userfrom = userfrom;
	}

	@ManyToOne
	@JoinColumn(name="usertoid")
	public User getUserto() {
		return userto;
	}

	public void setUserto(User userto) {
		this.userto = userto;
	}

	@ManyToOne
	@JoinColumn(name="productid")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name="content", columnDefinition="TEXT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}


	
}
