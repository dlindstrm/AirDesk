package pt.ulisboa.tecnico.cmov.airdesk;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class SharedWorkspace extends ActionBarActivity {
    private int _Ws_Id=0;
    TextView file_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_workspace);

        Intent intent = getIntent();
        _Ws_Id =intent.getIntExtra("ws_Id", 0);

        //Network get list of files in workspace
        NetworkHandlerRequest nwHdl = new NetworkHandlerRequest();
        ArrayList<HashMap<String, String>> fileList = nwHdl.getWsById(_Ws_Id);


        if(fileList.size()!=0) {
            ListView lv = (ListView) findViewById(R.id.list);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    file_Id = (TextView) view.findViewById(R.id.file_Id);
                    String fileId = file_Id.getText().toString();


                    //Network get the file
                    Intent objIndent = new Intent(getApplicationContext(),ReadFileActivityNetwork.class);
                    objIndent.putExtra("file_Id", Integer.parseInt( fileId));
                    startActivity(objIndent);

                }});
            ListAdapter adapter = new SimpleAdapter( SharedWorkspace.this,fileList, R.layout.view_file_entry, new String[] { "id","title"}, new int[] {R.id.file_Id, R.id.file_title});
            lv.setAdapter(adapter);
        }
        else{
            Toast.makeText(this, "No files!", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shared_workspace, menu);
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
