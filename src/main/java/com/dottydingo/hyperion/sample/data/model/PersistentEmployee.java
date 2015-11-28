package com.dottydingo.hyperion.sample.data.model;


import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 */
@Entity
@Table(name = "employees")
@DynamicUpdate
@AttributeOverrides(
        {@AttributeOverride(name = "id",column = @Column(name = "employee_id"))}
)
public class PersistentEmployee extends BaseSamplePersistentObject
{
    @Column(name = "first_name",length = 64)
    private String firstName;
    @Column(name = "last_name",length = 64)
    private String lastName;
    @Column(name = "title",length = 64)
    private String title;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @Column(name = "date_of_hire")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfHire;

    @Column(name = "address",length = 128)
    private String address;
    @Column(name = "city",length = 64)
    private String city;
    @Column(name = "state",length = 2)
    private String state;
    @Column(name = "postal_code",length = 10)
    private String postalCode;
    @Column(name = "home_phone",length = 10)
    private String homePhone;

    @Column(name = "supervisor_id",nullable = true)
    private Long supervisorId;

    @Column(name="department_id")
    private Long departmentId;

    @BatchSize(size = 50)
    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name = "territory_id")
    @JoinTable(name = "employee_territories",joinColumns = @JoinColumn(name = "employee_id"))
    private List<Long> territoryIds;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfHire()
    {
        return dateOfHire;
    }

    public void setDateOfHire(Date dateOfHire)
    {
        this.dateOfHire = dateOfHire;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getHomePhone()
    {
        return homePhone;
    }

    public void setHomePhone(String homePhone)
    {
        this.homePhone = homePhone;
    }

    public Long getSupervisorId()
    {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId)
    {
        this.supervisorId = supervisorId;
    }

    public Long getDepartmentId()
    {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId)
    {
        this.departmentId = departmentId;
    }

    public List<Long> getTerritoryIds()
    {
        return territoryIds;
    }

    public void setTerritoryIds(List<Long> territoryIds)
    {
        this.territoryIds = territoryIds;
    }
}
