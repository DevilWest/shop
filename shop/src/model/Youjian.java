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
@Table(name="t_Youjian")
public class Youjian implements Serializable{

	private static final long serialVersionUID = 2429695896963809305L;

	private long id;
	
	private User userfrom;
	
	private User userto;
	
	private String content;
	
	private Date createtime;
	
	


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
