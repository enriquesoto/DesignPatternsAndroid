package enrique.tpapatterndesign;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import memento.CareTaker;
import memento.Memento;
import memento.Originator;
import shapes.Shape;
import shapes.ShapeFactory;
import tools.Generator;


public class MainActivity extends ActionBarActivity {

    private RelativeLayout mFrame;
    int mDisplayWidth;
    int mDisplayHeight;
    private GestureDetector mGestureDetector;
    private ImageButton bttnRectangle;
    private ImageButton bttnCircle;
    private ImageButton bttnTriangle;
    private  Originator mOriginator = new Originator();
    private CareTaker aCaretaker = new CareTaker();
    private Button bttnUndo;
    private int currentState = 0;


    int whatShape=-1;

    private static final ShapeFactory.ShapeType shapes[] = { ShapeFactory.ShapeType.RECTANGLE,
            ShapeFactory.ShapeType.CIRCLE, ShapeFactory.ShapeType.TRIANGLE };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrame = (RelativeLayout) findViewById(R.id.frame);
        bttnCircle = (ImageButton) findViewById(R.id.bttnCircle);
        bttnRectangle = (ImageButton) findViewById(R.id.bttnRectangle);
        bttnTriangle = (ImageButton) findViewById(R.id.bttnTriangle);
        bttnUndo = (Button) findViewById(R.id.bttnUndo);

        bttnCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whatShape = 1; //
            }
        });
        bttnRectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whatShape = 0;
            }
        });
        bttnTriangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whatShape = 2;
            }
        });
        bttnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mOriginator.getStateFromMemento(aCaretaker.get(0));
                //mFrame = mOriginator.getState();


                if(currentState == 0){
                    return ;
                }
                currentState--;

                reDrawLayout();

            }
        });

        //mOriginator.setState(mFrame,getApplicationContext());
        //aCaretaker.add(mOriginator.save2Memento());

        setupGestureDetector();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {

            // Get the size of the display so this view knows where borders are
            mDisplayWidth = mFrame.getWidth();
            mDisplayHeight = mFrame.getHeight();

        }
    }

    private void setupGestureDetector() {

        mGestureDetector = new GestureDetector(this,

                new GestureDetector.SimpleOnGestureListener() {

                    // If a fling gesture starts on a BubbleView then change the
                    // BubbleView's velocity

                    // If a single tap intersects a BubbleView, then pop the BubbleView
                    // Otherwise, create a new BubbleView at the tap's location and add
                    // it to mFrame. You can get all views from mFrame with ViewGroup.getChildAt()

                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent event) {

                        // TODO - Implement onSingleTapConfirmed actions.
                        // You can get all Views in mFrame using the
                        // ViewGroup.getChildCount() method

                        if(whatShape == -1){
                            return false;
                        }




                        Shape myShape = ShapeFactory.getShape(shapes[whatShape]);
                        int aColor = Generator.generateColor();
                        int aWidth = Generator.generateWidth();



                        myShape.draw(mFrame,event.getX(),event.getY(),aWidth, aColor,getApplicationContext());

                        mOriginator.setState(mFrame.getChildAt(mFrame.getChildCount()-1)); //agregando el ultimo view que inserte al frame
                        Memento currentMemento = mOriginator.save2Memento(); //guardar estado

                        aCaretaker.add(currentMemento,currentState); //guargar a la lista de estados

                        currentState++;
                        return false;
                    }
                });
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // TODO - delegate the touch to the gestureDetector

        return mGestureDetector.onTouchEvent(event);

    }

    public void reDrawLayout(){
        mFrame.removeAllViews();
        for (int i = 0 ; i< currentState;i++){
            mFrame.addView(aCaretaker.get(i).getState());
        }
        aCaretaker.get(currentState).switchUndone(); //le pongo flag en undone = true, al ultimo que hice undo
    }

}
