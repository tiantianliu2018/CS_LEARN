package basic.reflection;

/**
 * @author Kelly
 * @create 2020-05-28 11:38
 */
public class TargetObject {
    private String value;

    public TargetObject() {
        this.value = "Java";
    }
    public void publicMethod(String s){
        System.out.println("I love " + s);
    }
    private void privateMethod(){
        System.out.println("This is private method, value is " + value);
    }
}
