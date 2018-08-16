package com.example.user.contentprovider;

public class AppStudent {


    private Long id;
    private String family;
    private String name;
    private Integer phone;

    public AppStudent(Long id, String family, String name, Integer phone) {
        this.id=id;
        this.family=family;
        this.name=name;
        this.phone=phone;
    }
    public Long getId() {return id; }

    public void setId(Long id) {
        this.id = id;
    }
    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}
