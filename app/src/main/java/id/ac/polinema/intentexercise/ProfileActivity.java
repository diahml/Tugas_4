package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private TextView tv_about, tv_fullname, tv_email, tv_hp;
    private ImageView viewBitmap;
    private Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bitmap bitmap =this.getIntent().getParcelableExtra("Bitmap");
        String fullname=getIntent().getExtras().getString("fullname");
        String email = getIntent().getExtras().getString("email");
        String homepage = getIntent().getExtras().getString("homepage");
        String about = getIntent().getExtras().getString("about");

        viewBitmap=findViewById(R.id.image_profile);
        tv_about=findViewById(R.id.label_about);
        tv_fullname=findViewById(R.id.label_fullname);
        tv_email=findViewById(R.id.label_email);
        tv_hp=findViewById(R.id.label_homepage);

        viewBitmap.setImageBitmap(bitmap);
        tv_about.setText(about);
        tv_fullname.setText(fullname);
        tv_email.setText(email);
        tv_hp.setText(homepage);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://github.com/diahml");
                Intent web = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(web);
            }
        });
    }
}
