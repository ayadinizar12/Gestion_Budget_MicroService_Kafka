package com.gestion.budget.communes.manager.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Entity
public class Notification {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
    private Integer projetId;
    private String client;
    private String content;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    
	public Notification(Integer projetId,  String content, NotificationType type) {
		this.projetId = projetId;
		this.content = content;
		this.type = type;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getProjetId() {
		return projetId;
	}

	public void setProjetId(Integer projetId) {
		this.projetId = projetId;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public Notification(Integer projetId, String client, String content, NotificationType type) {
		super();
		this.projetId = projetId;
		this.client = client;
		this.content = content;
		this.type = type;
	}
	
	
    
    
    
}
