package com.example.newtaskcmss.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DatabaseTable(tableName = "item_user")
public class UserItemDB {

    @Getter
    @Setter
    @DatabaseField(generatedId = true)
    private Long id;

    @Getter
    @Setter
    @DatabaseField
    private String index;

    @Getter
    @Setter
    @DatabaseField
    private String name;

    @Getter
    @Setter
    @DatabaseField
    private String age;

    public UserItemDB() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
