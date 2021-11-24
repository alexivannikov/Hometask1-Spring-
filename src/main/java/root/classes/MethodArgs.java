package root.classes;

import java.util.LinkedList;

public class MethodArgs extends Object{
    private LinkedList<Object> argsList;

    public MethodArgs(LinkedList<Object> argsList){
        this.argsList = argsList;
    }

    public LinkedList<Object> getArgsList(){
        return argsList;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }

        if(o == null || getClass() != o.getClass()){
            return false;
        }

        MethodArgs that = (MethodArgs) o;

        return argsList.equals(that.argsList);
    }

    @Override
    public int hashCode(){
        return argsList.hashCode();
    }
}
