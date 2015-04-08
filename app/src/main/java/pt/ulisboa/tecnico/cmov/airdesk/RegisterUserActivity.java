package pt.ulisboa.tecnico.cmov.airdesk;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterUserActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Called when the user clicks the sharedWs button
    public void insertUser(View view) {

        User user = new User();
        EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        EditText editTextFullName = (EditText) findViewById(R.id.editTextFullName);
        if (!checkIfMail(editTextEmail.getText().toString())){
            findViewById(R.id.textViewFormat).setVisibility(View.VISIBLE);
        }
        else {

            user.email = editTextEmail.getText().toString();
            user.fullName = editTextFullName.getText().toString();
            UserRepo repo = new UserRepo(this);
            repo.insert(user);
            Toast.makeText(this, "Account successfully created", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public static boolean checkIfMail(String m){
        if (!m.contains("@")) return false; //email must contain an @

        String[] mArray = m.split("@");
        if (!mArray[1].contains(".")) return false; //There must be a dot after the @


        return true;
    }
}
