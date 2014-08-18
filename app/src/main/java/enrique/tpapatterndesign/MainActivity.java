package enrique.tpapatterndesign;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.HashMap;

import memento.CareTaker;
import memento.Memento;
import memento.Originator;
import shapes.Shape;
import shapes.ShapeFactory;
import shapes.ShapeView;
import tools.Generator;


public class MainActivity extends ActionBarActivity {

    private static final float YOFFSET = 100;
    private final static int MAX_STREAMS = 10; //mio

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
    private final int duration = Toast.LENGTH_SHORT;
    private AudioManager mAudioManager;
    private float mStreamVolume;
    // SoundPool
    private SoundPool mSoundPool;
    // ID for the bubble popping sound
    private int mSoundID;


    int whatShape=-1;

    private static final ShapeFactory.ShapeType shapes[] = { ShapeFactory.ShapeType.RECTANGLE,
            ShapeFactory.ShapeType.CIRCLE, ShapeFactory.ShapeType.TRIANGLE };

    private HashMap<String,Integer> shapeMapping = new HashMap<String, Integer>();

    private final String CIRCLECLASS= "shapes.Circle$CircleView";
    private final String RECTANGLECLASS= "shapes.Rectangle$RectangleView";
    private final String TRIANGLECLASS= "shapes.Triangle$TriangleView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrame = (RelativeLayout) findViewById(R.id.frame);
        bttnCircle = (ImageButton) findViewById(R.id.bttnCircle);
        bttnRectangle = (ImageButton) findViewById(R.id.bttnRectangle);
        bttnTriangle = (ImageButton) findViewById(R.id.bttnTriangle);
        bttnUndo = (Button) findViewById(R.id.bttnUndo);
        shapeMapping.put(CIRCLECLASS,1);
        shapeMapping.put(RECTANGLECLASS,0);
        shapeMapping.put(TRIANGLECLASS,2);


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

    @Override
    public void onResume(){
        super.onResume();

        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        mStreamVolume = (float) mAudioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC)
                / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        mSoundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC,0);


        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // sellama cuando el sonido se carga totalmente
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                if (status == 0)
                    setupGestureDetector();
            }
        });
        mSoundID = mSoundPool.load(this, R.raw.bubble_pop,1);

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
                        float xPos = event.getX();
                        float yPos = event.getY()-YOFFSET;
                        if(whatShape == -1){
                            return false;
                        }

                        Shape myShape = ShapeFactory.getShape(shapes[whatShape]);

                        for (int i=0;i<mFrame.getChildCount();i++){
                            ShapeView myViewTmp = (ShapeView) mFrame.getChildAt(i);
                            /*
                            Log.i("posicion","xpos(evento)="+xPos +"ypos(evento) = " + yPos + "x view = "+
                                    myViewTmp.getX()+"y view =" + myViewTmp.getY() + "ancho =" +myViewTmp.getWidth());
                                    */

                            if(myViewTmp.intersects(xPos,yPos) ){
                                CharSequence text = "estas en la posicion" + myViewTmp.getClass().getName();
                                Log.i("REFLECTION",myViewTmp.getClass().getName());
                                Toast toast = Toast.makeText(getApplicationContext(),text,duration);
                                toast.show();
                                int index = shapeMapping.get(myViewTmp.getClass().getName());
                                myShape = ShapeFactory.getShape(shapes[index]);
                                myShape.setColor(Generator.generateColor());
                                myShape.setWidth(Generator.randInt(ShapeFactory.MINWIDTH,ShapeFactory.MAXWIDTH));
                                reDrawLayout();
                                return true;
                            }

                        }





                        myShape.draw(mFrame,xPos,yPos,getApplicationContext());
                        mSoundPool.play(mSoundID, (float)mStreamVolume , (float)mStreamVolume, 1, 0,1.0f);


                        mOriginator.setState(mFrame.getChildAt(mFrame.getChildCount()-1)); //agregando el ultimo view que inserte al frame
                        Memento currentMemento = mOriginator.save2Memento(); //guardar estado

                        aCaretaker.add(currentMemento, currentState); //guargar a la lista de estados

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
        //aCaretaker.get(currentState).switchUndone(); //le pongo flag en undone = true, al ultimo que hice undo
    }

}
