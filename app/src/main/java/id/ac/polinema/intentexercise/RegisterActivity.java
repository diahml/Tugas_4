package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout ti_fullname, ti_email, ti_password, ti_confirm_pass, ti_homepage, ti_about;
    private TextInputEditText et_fullname, et_email, et_password, et_confirm_pass, et_homepage, et_about;
    private Button btnOk;

    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE =1;
    private ImageView avatarImage;

    public void hadleChangeAvatar(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ti_fullname = findViewById(R.id.layout_fullname);
        ti_email = findViewById(R.id.layout_email);
        ti_password = findViewById(R.id.layout_password);
        ti_confirm_pass = findViewById(R.id.layout_confirm_password);
        ti_homepage = findViewById(R.id.layout_homepage);
        ti_about = findViewById(R.id.layout_about);
        et_fullname = findViewById(R.id.text_fullname);
        et_email = findViewById(R.id.text_email);
        et_password = findViewById(R.id.text_password);
        et_confirm_pass = findViewById(R.id.text_confirm_password);
        et_homepage = findViewById(R.id.text_homepage);
        et_about = findViewById(R.id.text_about);
        btnOk = findViewById(R.id.button_ok);
        avatarImage = findViewById(R.id.image_profile);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = ti_password.getEditText().getText().toString();
                String confpass = ti_confirm_pass.getEditText().getText().toString();
                boolean check = validateinfo(password, confpass);

                if (check == true) {
                    Toast.makeText(getApplicationContext(), "Data is Valid", Toast.LENGTH_SHORT).show();
                    avatarImage.setDrawingCacheEnabled(true);
                    Bitmap b = avatarImage.getDrawingCache();
                    Intent move = new Intent(RegisterActivity.this, ProfileActivity.class);
                    move.putExtra("fullname", ti_fullname.getEditText().getText().toString());
                    move.putExtra("email", ti_email.getEditText().getText().toString());
                    move.putExtra("homepage", ti_homepage.getEditText().getText().toString());
                    move.putExtra("about", ti_about.getEditText().getText().toString());
                    move.putExtra("Bitmap", b);
                    startActivity(move);
                } else {
                    Toast.makeText(getApplicationContext(), "Please check your password again", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private Boolean validateinfo(String password, String confpass) {
        if(!password.equals(confpass)){
            et_confirm_pass.requestFocus();
            et_confirm_pass.setError("Password Unmatches");
            return false;
        }else{
            return true;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_CANCELED){
            return;
        }
        if(requestCode==GALLERY_REQUEST_CODE){
            if(data!=null){
                try{
                    Uri imageUri= data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                    avatarImage.setImageBitmap(bitmap);
                }catch (IOException e){
                    Toast.makeText(this, "Cant Load Image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG,e.getMessage());
                }
            }
        }
    }
}
