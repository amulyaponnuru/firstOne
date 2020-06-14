package com.example.firstone;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {
    String TAG="GenerateQrCode";
    String inputval;
    EditText edittext;
    ImageView qrimg;
    Button start;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qrimg = (ImageView)findViewById(R.id.qrCode);
        edittext = (EditText)findViewById(R.id.editText);
        start = (Button)findViewById(R.id.generateQR);
        Toast.makeText(MainActivity.this, "Firebase Connection Success", Toast.LENGTH_LONG).show();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputval = edittext.getText().toString().trim();
                if(inputval.length()>0){
                    WindowManager manager = (WindowManager)getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int smallerDimension = width<height ? width : height;
                    smallerDimension = smallerDimension*3/4;
                    qrgEncoder = new QRGEncoder(inputval,null, QRGContents.Type.TEXT, smallerDimension);
                    try
                    {
                        bitmap = qrgEncoder.encodeAsBitmap();
                        qrimg.setImageBitmap(bitmap);
                    }
                    catch(WriterException e){
                        Log.v(TAG,toString());
                    }
                }
                else{
                    edittext.setError("Required");
                }
            }
        });

    }
}