/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.ae2017practica2.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rh1n0
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findByProductid", query = "SELECT p FROM Product p WHERE p.productid = :productid")
    , @NamedQuery(name = "Product.findByCode", query = "SELECT p FROM Product p WHERE p.code = :code")
    , @NamedQuery(name = "Product.findByProductname", query = "SELECT p FROM Product p WHERE p.productname = :productname")
    , @NamedQuery(name = "Product.findByBrand", query = "SELECT p FROM Product p WHERE p.brand = :brand")
    , @NamedQuery(name = "Product.findByPurchprice", query = "SELECT p FROM Product p WHERE p.purchprice = :purchprice")
    , @NamedQuery(name = "Product.findByStock", query = "SELECT p FROM Product p WHERE p.stock = :stock")
    , @NamedQuery(name = "Product.findBySalepricemin", query = "SELECT p FROM Product p WHERE p.salepricemin = :salepricemin")
    , @NamedQuery(name = "Product.findByReorderpoint", query = "SELECT p FROM Product p WHERE p.reorderpoint = :reorderpoint")
    , @NamedQuery(name = "Product.findByCurrency", query = "SELECT p FROM Product p WHERE p.currency = :currency")
    , @NamedQuery(name = "Product.findBySalepricemax", query = "SELECT p FROM Product p WHERE p.salepricemax = :salepricemax")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "productid")
    private Integer productid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "productname")
    private String productname;
    @Size(max = 40)
    @Column(name = "brand")
    private String brand;
    @Basic(optional = false)
    @NotNull
    @Column(name = "purchprice")
    private BigInteger purchprice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock")
    private int stock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salepricemin")
    private BigInteger salepricemin;
    @Column(name = "reorderpoint")
    private Integer reorderpoint;
    @Size(max = 3)
    @Column(name = "currency")
    private String currency;
    @Column(name = "salepricemax")
    private BigInteger salepricemax;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productid", fetch = FetchType.LAZY)
    private List<Salesline> saleslineList;
    @JoinColumn(name = "categoryid", referencedColumnName = "categoryid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category categoryid;

    public Product() {
    }

    public Product(Integer productid) {
        this.productid = productid;
    }

    public Product(Integer productid, String code, String productname, BigInteger purchprice, int stock, BigInteger salepricemin) {
        this.productid = productid;
        this.code = code;
        this.productname = productname;
        this.purchprice = purchprice;
        this.stock = stock;
        this.salepricemin = salepricemin;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigInteger getPurchprice() {
        return purchprice;
    }

    public void setPurchprice(BigInteger purchprice) {
        this.purchprice = purchprice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigInteger getSalepricemin() {
        return salepricemin;
    }

    public void setSalepricemin(BigInteger salepricemin) {
        this.salepricemin = salepricemin;
    }

    public Integer getReorderpoint() {
        return reorderpoint;
    }

    public void setReorderpoint(Integer reorderpoint) {
        this.reorderpoint = reorderpoint;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigInteger getSalepricemax() {
        return salepricemax;
    }

    public void setSalepricemax(BigInteger salepricemax) {
        this.salepricemax = salepricemax;
    }

    @XmlTransient
    public List<Salesline> getSaleslineList() {
        return saleslineList;
    }

    public void setSaleslineList(List<Salesline> saleslineList) {
        this.saleslineList = saleslineList;
    }

    public Category getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Category categoryid) {
        this.categoryid = categoryid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productid != null ? productid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productid == null && other.productid != null) || (this.productid != null && !this.productid.equals(other.productid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.edu.ittepic.ae2017practica2.entities.Product[ productid=" + productid + " ]";
    }
    
}
