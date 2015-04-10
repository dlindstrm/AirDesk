package pt.ulisboa.tecnico.cmov.airdesk;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddFileActivityNetwork extends  AddFileActivity{

    private int _Ws_Id=0;

    // Called when the user clicks the save button
    @Override
    public void saveFile(View view) {
        UserRepo userrepo = new UserRepo(this);
        User user = userrepo.getUser();

        File file = new File();

        final EditText editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        EditText editTextContent = (EditText) findViewById(R.id.editTextContent);
        file.title = editTextTitle.getText().toString();
        file.content = editTextContent.getText().toString();
        file.author = user.email;
        file.setSize();

        file.ws = _Ws_Id;
        WorkspaceRepo wsRepo = new WorkspaceRepo(this);
        Workspace workspace = wsRepo.getWorkspaceById(_Ws_Id);

        FileRepo repo = new FileRepo(this);
        if(workspace.sizeLimit-repo.getFileSizes(_Ws_Id) >= file.size) {
            repo.insert(file);
            Toast.makeText(this, "File added", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MyWorkspaceActivity.class);
            intent.putExtra("ws_Id", _Ws_Id);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "File is too big.", Toast.LENGTH_SHORT).show();
        }
    }

}
