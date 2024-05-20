/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jackie's PC
 */
@Entity
@Table(name = "foods")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foods.findAll", query = "SELECT f FROM Foods f"),
    @NamedQuery(name = "Foods.findByFoodId", query = "SELECT f FROM Foods f WHERE f.foodId = :foodId"),
    @NamedQuery(name = "Foods.findByName", query = "SELECT f FROM Foods f WHERE f.name = :name"),
    @NamedQuery(name = "Foods.findByImgfood", query = "SELECT f FROM Foods f WHERE f.imgfood = :imgfood"),
    @NamedQuery(name = "Foods.findByPrice", query = "SELECT f FROM Foods f WHERE f.price = :price"),
    @NamedQuery(name = "Foods.findByFoodType", query = "SELECT f FROM Foods f WHERE f.foodType = :foodType"),
    @NamedQuery(name = "Foods.findByStatus", query = "SELECT f FROM Foods f WHERE f.status = :status")})
public class Foods implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "food_id")
    private Integer foodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    // @Size(min = 1, max = 255)
    @Column(name = "imgfood")
    private String imgfood;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private Long price;

    @Size(max = 50)
    @Column(name = "food_type")
    private String foodType;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "status")
    private String status;

    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    @ManyToOne
    private Stores storeId;

    @OneToMany(mappedBy = "foodId")
    @JsonIgnore
    private Set<Reviews> reviewsSet;

    @OneToMany(mappedBy = "foodId")
    @JsonIgnore
    private Set<OrderDetail> orderDetailSet;

    @Transient
    private MultipartFile file;

    public Foods() {
    }

    public Foods(Integer foodId) {
        this.foodId = foodId;
    }

    public Foods(Integer foodId, String name, String imgfood, Long price, String status, String type) {
        this.foodId = foodId;
        this.name = name;
        this.imgfood = imgfood;
        this.price = price;
        this.status = status;
        this.foodType = type;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgfood() {
        return imgfood;
    }

    public void setImgfood(String imgfood) {
        this.imgfood = imgfood;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Stores getStoreId() {
        return storeId;
    }

    public void setStoreId(Stores storeId) {
        this.storeId = storeId;
    }

    @XmlTransient
    public Set<Reviews> getReviewsSet() {
        return reviewsSet;
    }

    public void setReviewsSet(Set<Reviews> reviewsSet) {
        this.reviewsSet = reviewsSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foodId != null ? foodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foods)) {
            return false;
        }
        Foods other = (Foods) object;
        if ((this.foodId == null && other.foodId != null) || (this.foodId != null && !this.foodId.equals(other.foodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lnk.pojo.Foods[ foodId=" + foodId + " ]";
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    /**
     * @return the orderDetailSet
     */
    public Set<OrderDetail> getOrderDetailSet() {
        return orderDetailSet;
    }

    /**
     * @param orderDetailSet the orderDetailSet to set
     */
    public void setOrderDetailSet(Set<OrderDetail> orderDetailSet) {
        this.orderDetailSet = orderDetailSet;
    }
}
