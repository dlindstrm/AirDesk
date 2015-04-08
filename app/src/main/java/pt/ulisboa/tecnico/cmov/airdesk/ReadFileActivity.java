package pt.ulisboa.tecnico.cmov.airdesk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ReadFileActivity extends ActionBarActivity {
    TextView fileContent, fileCreatedAt, fileAuthor;
    private int _File_Id=0;
    private int _Ws_Id=0;
    FileRepo repo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file);

        fileContent = (TextView) findViewById(R.id.file_content);
        fileCreatedAt = (TextView) findViewById(R.id.file_createdAt);
        fileAuthor = (TextView) findViewById(R.id.file_author);


        _File_Id =0;
        Intent intent = getIntent();
        _File_Id =intent.getIntExtra("file_Id", 0);
        repo = new FileRepo(this);
        File file = repo.getFileById(_File_Id);

        _Ws_Id = file.ws;
        setTitle(file.title);

        fileContent.setText(file.content);
        fileCreatedAt.setText(file.createdAt);
        fileAuthor.setText(file.author);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read_file, menu);
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

        else if (id == R.id.action_delete) {

            //popup window

            repo.delete(_File_Id);
            Intent intent = new Intent(this, MyWorkspaceActivity.class);
            intent.putExtra("ws_Id",_Ws_Id);
            startActivity(intent);
        }
        else if (id == R.id.action_edit) {

            Intent intent = new Intent(this, EditFileActivity.class);
            intent.putExtra("file_Id",_File_Id);
            startActivity(intent);
        }

        else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


//    public class DeleteDialogFragment extends DialogFragment {
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            // Use the Builder class for convenient dialog construction
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setMessage("Are you sure you want to delete the file")
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // FIRE ZE MISSILES!
//                        }
//                    })
//                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // User cancelled the dialog
//                        }
//                    });
//            // Create the AlertDialog object and return it
//            return builder.create();
//        }
//    }



}
