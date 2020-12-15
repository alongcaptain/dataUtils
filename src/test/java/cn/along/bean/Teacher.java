package cn.along.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Teacher {

    String id;

    String name;

    String telNumber;

}
