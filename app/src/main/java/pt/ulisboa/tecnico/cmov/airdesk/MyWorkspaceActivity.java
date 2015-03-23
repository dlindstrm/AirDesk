package pt.ulisboa.tecnico.cmov.airdesk;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MyWorkspaceActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workspace);

        FileRepo repo = new FileRepo(this);

        ArrayList<HashMap<String, String>> fileList =  repo.getFileList();
        if(fileList.size()!=0) {
            ListAdapter adapter = new SimpleAdapter( MyWorkspaceActivity.this,fileList, R.layout.view_file_entry, new String[] { "id","title"}, new int[] {R.id.file_Id, R.id.file_title});
            setListAdapter(adapter);
        }
        else{
            Toast.makeText(this, "No files!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_workspace, menu);
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
}
