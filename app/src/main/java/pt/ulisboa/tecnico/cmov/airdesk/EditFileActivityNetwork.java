package pt.ulisboa.tecnico.cmov.airdesk;

import android.os.Bundle;

public class EditFileActivityNetwork extends EditFileActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Edit File");

        setContentView(R.layout.activity_add_file);
    }
}