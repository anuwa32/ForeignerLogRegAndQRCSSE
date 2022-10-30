package com.example.foreignerloginreg;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateQRCodeActivity extends AppCompatActivity {


    private TextView qrCodeTV;
    private ImageView qrCodeIV;
    private TextInputEditText dataEdt,dataEdt2;
    private Button generateQRBtn;
    private QRGEncoder qrgEncoder;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qrcode);

        qrCodeTV = findViewById(R.id.idGenerateQR);
        qrCodeIV = findViewById(R.id.idIMQRCode);
        dataEdt = findViewById(R.id.idEdtData);
        generateQRBtn = findViewById(R.id.idBtnGenerate);
        //dataEdt2 = findViewById(R.id.idEdtData2);

//        DAOForeigner dao = new DAOForeigner();
//        generateQRBtn.setOnClickListener(v->{
//
//            Foreigner fog = new Foreigner(dataEdt.getText().toString());
//            dao.add(fog).addOnSuccessListener(suc ->{
//
//                Toast.makeText(this,"Record is inserted",Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er->{
//
//                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
//            });
//
//        });

        generateQRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data = dataEdt.getText().toString();
                //String data2 = dataEdt2.getText().toString();

                if(data.isEmpty()){
                    Toast.makeText(GenerateQRCodeActivity.this, "Enter the data please.", Toast.LENGTH_SHORT).show();
//               }else if (data2.isEmpty()){
//                   Toast.makeText(GenerateQRCodeActivity.this, "Enter the data please.", Toast.LENGTH_SHORT).show();

                }else{
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point =  new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int dimen = width<height ? width:height;
                    dimen = dimen * 3/4;


                    qrgEncoder = new QRGEncoder(dataEdt.getText().toString(), null, QRGContents.Type.TEXT,dimen);
                    //qrgEncoder2 = new QRGEncoder(dataEdt2.getText().toString(), null, QRGContents.Type.TEXT,dimen);
                    try{
                        bitmap = qrgEncoder.encodeAsBitmap();
                        qrCodeTV.setVisibility(View.GONE);
                        qrCodeIV.setImageBitmap(bitmap);

                    }catch(WriterException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}