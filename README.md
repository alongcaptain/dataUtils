# 1. dataUtils 是什么？
偶然间在加工数据的时候，遇到一些从外部传入的数据，发现这些数据需要进行一些处理，看了jdk和apache的工具包，都没有顺手的工具（因为一个对象可能需要多种维度的比较方法，但是现有工具只能通过重写equals来判断，那一个对象就只能使用一种固定维度来操作了），只好自己写一个了。专门针对对象数据，根据想要的属性进行减法，去重。这样，同一个对象，可以使用不同属性进行不同操作，也不限于同一个对象，针对不同的对象，只要比较的是函数对应，一样可以进行比较。
# 使用方法
使用方法已经写在 测试类的 cn.along.utils.AppTest#main 方法中。应该比较容易理解。
```java
BeanMathUtils.substract(wholeClassRoomList, partClassRoomList, 
                          (w)->{return w.getId() + w.getName();}, (p)->{return p.getId()+ p.getName();});
```
就是使用 wholeClassRoomList(id+name) - partClassRoomList(id+name) 。
