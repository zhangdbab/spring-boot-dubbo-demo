package com.sans.base.dto;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;


import java.io.Serializable;

@Data
public class User  implements Serializable {
    // ID
    private int id;
    // 名字
    private String name;
    // 序号
    private int number;


    public User() {
    }

    public User(int id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }
}
