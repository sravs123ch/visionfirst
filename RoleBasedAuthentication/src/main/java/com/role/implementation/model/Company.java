package com.role.implementation.model;
import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "company")

public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "company_address", nullable = false)
    private String companyAddress;

//    // Many companies can be created by one user (admin or normal user)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "created_by", nullable = false)
//    private User createdBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.UNAPPROVED; // Default status for normal users

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Equals and hashCode to compare Company objects

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) &&
                Objects.equals(companyName, company.companyName) &&
                Objects.equals(companyAddress, company.companyAddress) &&
                Objects.equals(createdBy, company.createdBy) &&
                status == company.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, companyAddress, createdBy, status);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", createdBy=" + createdBy +
                ", status=" + status +
                '}';
    }

	public void setStatus(String status2) {
		// TODO Auto-generated method stub
		
	}
}
