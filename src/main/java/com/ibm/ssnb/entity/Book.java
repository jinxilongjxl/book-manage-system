package com.ibm.ssnb.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "t_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10)
    private String name;

    // 最大精度是这个样子1234567890.00
    @NotNull(message = "单价不能为空")
    @Column(precision = 10, scale = 2)
    private BigDecimal danjia;

    // 作者名称
    @NotNull(message = "作者名称不能为空")
    @Column(length = 20)
    private String author;

    // 出版社
    @NotNull(message = "出版社不能为空")
    @Column(length = 20)
    private String press;

    // 图书编号
    @NotNull(message = "图书编号不能为空")
    @Column(length = 20)
    private String bianhao;

    // 图书封面
    @Column(length = 200)
    private String imageUrl;

    // 图书数量
    @NotNull(message = "图书数量不能为空")
    @Column(length = 10)
    private Integer num;

    // 图书类型
    @ManyToOne
    @JoinColumn(name = "bookTypeId")
    private BookType bookType;

    @NotNull(message = "排序号不能为空")
    @Column(length = 10)
    private Integer orderNo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;


    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDanjia() {
        return danjia;
    }

    public void setDanjia(BigDecimal danjia) {
        this.danjia = danjia;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
