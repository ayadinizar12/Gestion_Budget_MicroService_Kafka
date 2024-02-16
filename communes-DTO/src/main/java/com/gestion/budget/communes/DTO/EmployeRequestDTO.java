package com.gestion.budget.communes.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeRequestDTO {
	
	    private Integer idEmploye;
	    private String fullName;
	    private String grade;
		
	    public Integer getIdEmploye() {
			return idEmploye;
		}
		public void setIdEmploye(Integer idEmploye) {
			this.idEmploye = idEmploye;
		}
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}
	
	    

}
