package pt.ulisboa.tecnico.cmov.airdesk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class EditWsActivity extends ActionBarActivity {
    private int _Ws_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ws);

        Intent intent = getIntent();
        _Ws_Id = intent.getIntExtra("ws_Id", 0);
        WorkspaceRepo repo = new WorkspaceRepo(this);
        Workspace ws = repo.getWorkspaceById(_Ws_Id);
        EditText editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        CheckBox checkBoxPublic = (CheckBox) findViewById(R.id.checkBoxPublic);
        editTextTitle.setText(ws.title);

        Toast.makeText(this, " " + ws.publicWs , Toast.LENGTH_SHORT).show();

//        boolean publicWs;
//        if(ws.publicWs != 0){publicWs = true;}
//        else{publicWs = false;}
//        checkBoxPublic.setChecked(publicWs);

        InviteRepo repoInvite = new InviteRepo(this);
        ArrayList<HashMap<String, String>> inviteList =  repoInvite.getInviteListByWsId(_Ws_Id);


        if(inviteList.size()!=0) {
            ListView lv = (ListView) findViewById(R.id.listViewInvite);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                }});
            ListAdapter adapter = new SimpleAdapter( EditWsActivity.this,inviteList, R.layout.view_file_entry, new String[] { "id","title"}, new int[] {R.id.file_Id, R.id.file_title});
            lv.setAdapter(adapter);
        }
        else{
            Toast.makeText(this, "No workspaces!", Toast.LENGTH_SHORT).show();
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_ws, menu);
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

        else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveWs(View view) {

        Workspace ws = new Workspace();

        EditText editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        ws.title = editTextTitle.getText().toString();
        WorkspaceRepo repoWs = new WorkspaceRepo(this);
        int wsID = repoWs.update(ws);

        //add to Invite list
//        InviteRepo repoInvite = new InviteRepo(this);
//        repoInvite.insert(InviteList, wsID);

        //add to Keword list
//        ws.publicWs = publicWs;
        KeywordsRepo repoKeyword = new KeywordsRepo(this);
        EditText editTextKeyword = (EditText) findViewById(R.id.editTextKeyWords);
        String[] Keywords = editTextKeyword.getText().toString().split(" ");
        repoKeyword.insert(Keywords, wsID);

        Toast.makeText(this, "Workspace added", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MyWorkspacesActivity.class);
        startActivity(intent);
    }

    // Called when the user clicks the cancel button
    public void cancel(View view) {
        Intent intent = new Intent(this, MyWorkspacesActivity.class);
        startActivity(intent);
    }


}
