package memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enrique on 08/08/14.
 */


public class CareTaker {

    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state,int currentState){

        if(currentState == mementoList.size() ){
            mementoList.add(state);
            return;
        }


        if(currentState -1  < mementoList.size()) {
            mementoList.set(currentState, state);
            return ;
        }

    }
    public Memento get(int index){
        return mementoList.get(index);
    }
    public void update(){

    }

}
