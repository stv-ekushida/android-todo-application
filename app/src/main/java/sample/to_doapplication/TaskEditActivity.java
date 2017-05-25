package sample.to_doapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TaskEditActivity extends AppCompatActivity {

    EditText mDetailEdit;
    EditText mTitleEdit;
    EditText mDeadlineEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_edit);

        mDeadlineEdit = (EditText)findViewById(R.id.deadlineEdit);
        mTitleEdit = (EditText)findViewById(R.id.titleEdit);
        mDeadlineEdit = (EditText)findViewById(R.id.detailEdit);
    }

    public void onSavaTapped(View view) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date deadline = new Date();

        try {
            deadline = sdf.parse(mDeadlineEdit.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm realm = Realm.getInstance(realmConfig);

        realm.beginTransaction();
        Number maxId = realm.where(Task.class).max("id");
        long nextId = 1;
        if (maxId != null) {
            nextId = maxId.longValue() + 1;
        }

        Task task = realm.createObject(Task.class);
        task.setId(nextId);
        task.setDeadline(deadline);
        task.setTitle(mTitleEdit.getText().toString());
        task.setDetal(mDeadlineEdit.getText().toString());
        realm.commitTransaction();

        Toast.makeText(this, "登録しました。", Toast.LENGTH_SHORT).show();
        finish();
    }
}
