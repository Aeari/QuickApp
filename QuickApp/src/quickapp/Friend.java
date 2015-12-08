package quickapp;

/*
I'm not crazy confident with how this works
I know that Builders are useful when we have a constructor 
with multiple parameters when some of them are being defaulted and others are not
 */
public class Friend {
    private final int age;
    private final int weight;
    private final int swagpoints;
    private final String name;
    
    public static class Builder{
        
    private int age =0;
    private int weight=0;
    private int swagpoints=0;
    private String name= null;
    
    public Builder age(int val)
    {
        age = val;
        return this;
    }
    public Builder weight(int val)
    {
        weight = val;
        return this;
    }
    public Builder swagpoints(int val)
    {
        swagpoints = val;
        return this;
    }
    public Builder name(String val)
    {
        name = val;
        return this;
    }
    public Friend build(){
        return new Friend(this);
    }
    }
    private Friend(Builder builder){
        age = builder.age;
        weight = builder.weight;
        swagpoints = builder.swagpoints;
        name = builder.name;
    }
    public void Speak(){
System.out.println("You've sent" +name + " he's" +age +" and weights " +weight + " with "+swagpoints +"swag points");
    }
}
