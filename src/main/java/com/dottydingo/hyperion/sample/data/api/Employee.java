package com.dottydingo.hyperion.sample.data.api;

import com.dottydingo.hyperion.api.BaseAuditableApiObject;
import com.dottydingo.hyperion.api.Endpoint;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;
import java.util.List;

/**
 */
@JsonPropertyOrder({"id","firstName","lastName","title","dateOfBirth","dateOfHire","address","city","state",
        "postalCode","homePhone","supervisorId","departmentId","territoryIds",
        "created","createdBy","modified","modifiedBy"})
@Endpoint("Employee")
public class Employee extends BaseAuditableApiObject<Long>
{
    private String firstName;
    private String lastName;
    private String title;
    private Date dateOfBirth;
    private Date dateOfHire;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String homePhone;
    private Long supervisorId;
    private Long departmentId;
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
