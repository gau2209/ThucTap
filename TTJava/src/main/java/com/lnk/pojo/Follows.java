/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jackie's PC
 */
@Entity
@Table(name = "follows")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Follows.findAll", query = "SELECT f FROM Follows f"),
    @NamedQuery(name = "Follows.findByFollowId", query = "SELECT f FROM Follows f WHERE f.followId = :followId"),
    @NamedQuery(name = "Follows.findByFollowDate", query = "SELECT f FROM Follows f WHERE f.followDate = :followDate")})
public class Follows implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "follow_id")
    private Integer followId;
    @Column(name = "follow_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date followDate;
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    @ManyToOne
    private Stores storeId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users userId;

    public Follows() {
    }

    public Follows(Integer followId) {
        this.followId = followId;
    }

    public Integer getFollowId() {
        return followId;
    }

    public void setFollowId(Integer followId) {
        this.followId = followId;
    }

    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
    }

    public Stores getStoreId() {
        return storeId;
    }

    public void setStoreId(Stores storeId) {
        this.storeId = storeId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (followId != null ? followId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Follows)) {
            return false;
        }
        Follows other = (Follows) object;
        if ((this.followId == null && other.followId != null) || (this.followId != null && !this.followId.equals(other.followId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lnk.pojo.Follows[ followId=" + followId + " ]";
    }
    
}
