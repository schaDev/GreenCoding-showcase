package org.greencoding.showcase.serialization.json.testdata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class SmallExamplePojo {

    private String name;
    private int age;
    private List<String> hobbies;
    private Date birthday;

}
