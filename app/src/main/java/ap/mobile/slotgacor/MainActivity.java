package ap.mobile.slotgacor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private Handler handler;

  private static int[] images = {R.drawable.slot_1,R.drawable.slot_2,R.drawable.slot_3,R.drawable.slot_4,R.drawable.slot_5,R.drawable.slot_6,
          R.drawable.slot_7, R.drawable.slot_8, R.drawable.slot_9};
  private int current1, current2, current3;
  private ImageView img1;
  private ImageView img2;
  private ImageView img3;
  private Thread thread1;
  private Thread thread2;
  private Thread thread3;
  private TextView title;
  private Button btRoll;
  Random random;
  int count = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btRoll = (Button) findViewById(R.id.btStartStop);
    btRoll.setOnClickListener(this);

    title = (TextView) findViewById(R.id.textView);
    title.setText("Muhammad Ariq Farhan - 215150400111009 \n" +
            "Alif Muhammad Abrar - 215150400111001");
    
    this.img1 = (ImageView) findViewById(R.id.image1);
    this.img2 = (ImageView) findViewById(R.id.image2);
    this.img3 = (ImageView) findViewById(R.id.image3);

    this.handler = new Handler(Looper.getMainLooper());
    createThread();

    random = new Random();

  }

  

  private void createThread() {
    this.thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
       try {
         while (true){
           final int index = random.nextInt(images.length);
           current1 = index;
           handler.post(new Runnable() {
             @Override
             public void run() {
               img1.setImageResource(images[index]);
             }
           });
           Thread.sleep(50);
         }
       } catch (InterruptedException e) {
       }
      }
    });
    
    this.thread2 = new Thread(new Runnable() {
      @Override
      public void run() {
       try {
         while (true){
           final int index = random.nextInt(images.length);
           current2 = index;
           handler.post(new Runnable() {
             @Override
             public void run() {
               img2.setImageResource(images[index]);
             }
           });
           Thread.sleep(50);
         }
       } catch (InterruptedException e) {
       }
      }
    });
    
    this.thread3 = new Thread(new Runnable() {
      @Override
      public void run() {
       try {
         while (true){
           final int index = random.nextInt(images.length);
           current3 = index;
           handler.post(new Runnable() {
             @Override
             public void run() {
               img3.setImageResource(images[index]);
             }
           });
           Thread.sleep(50);
         }
       } catch (InterruptedException e) {
       }
      }
    });

  }


  @Override
  public void onClick(View view) {
    if (this.thread1.isAlive()) {
      this.thread1.interrupt();
    } else if (this.thread2.isAlive()) {
      this.thread2.interrupt();
    } else if (this.thread3.isAlive()) {
      this.thread3.interrupt();
      if (current1 == current2 && current2 == current3) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("GACOR BOSKUH");
        builder.setMessage("Maxwin++++");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
          }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
      } else if(current1 != current2 || current2!=current3 || current1!=current3){
        Toast.makeText(this,"NT BANG", Toast.LENGTH_SHORT).show();
      }
      btRoll.setText("Roll");
    }else {
      this.createThread();
      this.thread1.start();
      this.thread2.start();
      this.thread3.start();
      btRoll.setText("Stop");
    }
  }
}
