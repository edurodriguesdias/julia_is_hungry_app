package stringdevelopment.com.br.ju_app;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

//Declarando objetos

    private TextView lblScore;
    private TextView lblStart;
    private ImageView box;
    private ImageView orange;
    private ImageView black;
    private ImageView pink;

    //Altura do Frame

    private int frameHeight;
    private int boxSize;

    //Posição
    private int boxY;

    //Inicializa Classe
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    //Status
    private boolean action_flg = false;
    private boolean start_flg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblScore = (TextView) findViewById(R.id.lblScore);
        lblStart = (TextView) findViewById(R.id.lblStart);

        box = (ImageView) findViewById(R.id.box);
        orange = (ImageView) findViewById(R.id.orange);
        pink = (ImageView) findViewById(R.id.pink);
        black = (ImageView) findViewById(R.id.black);
        black = (ImageView) findViewById(R.id.black);

        //Move para fora da tela.
        orange.setX(-80.0f);
        black.setX(-80.0f);
        pink.setX(-80.0f);

        orange.setY(-80.0f);
        black.setY(-80.0f);
        pink.setY(-80.0f);
    }


    public void changePos(){
        //Move o img box
        if(action_flg == true){
            //quando toca
            boxY -= 20;
        }else{
            //touch solto
            boxY += 20;
        }
        if(boxY < 0) boxY = 0;
        if(boxY > frameHeight - boxSize) boxY = frameHeight - boxSize;


        box.setY(boxY);
    }

    public boolean onTouchEvent(MotionEvent me){
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               handler.post(new Runnable() {
                                   @Override
                                   public void run() {
                                       changePos();
                                   }
                               });
                           }
                       },0,20
        );


        if(start_flg == false){
            start_flg = true;

            FrameLayout frame = (FrameLayout) findViewById(R.id.framLayout);
            frameHeight = frame.getHeight();

            boxY = (int)box.getY();

            boxSize = box.getHeight();


            lblStart.setVisibility(View.GONE);

        }else{

            if(me.getAction() == MotionEvent.ACTION_DOWN){
                action_flg = true;
            }else if(me.getAction() == MotionEvent.ACTION_UP){
                action_flg = false;
            }
        }

        return true;
    }
}
