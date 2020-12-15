package cn.along.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Classroom {

    String id;
    String name;

    Teacher leader;
    List<Student> studentList;
}
