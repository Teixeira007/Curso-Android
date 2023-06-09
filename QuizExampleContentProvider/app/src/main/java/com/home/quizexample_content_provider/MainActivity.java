package com.home.quizexample_content_provider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private Cursor cursor;
    private EditText searchWord;
    private ListView wordList;
    private Button button;
    private SimpleCursorAdapter cursorAdapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchWord = findViewById(R.id.searchWord);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentProvider();
            }
        });

        String[] wordListColumns = {
                UserDictionary.Words.WORD,
                UserDictionary.Words.LOCALE
        };

        int[] wordListItems = {
                R.id.dictWorld, R.id.locale
        };

        wordList = findViewById(R.id.wordList);

        cursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.item,
                null,
                wordListColumns,
                wordListItems,
                0);


        wordList.setAdapter(cursorAdapter);


    }

    public void contentProvider(){
        String[] projection = {
                UserDictionary.Words._ID,
                UserDictionary.Words.WORD,
                UserDictionary.Words.LOCALE
        };

        String selectionClause = null;
        String[] selectionArgs;

        String searchString = searchWord.getText().toString();

        if(TextUtils.isEmpty(searchString)){
            Log.d("MainActivity", "string vazia ");

            selectionClause = null;
            selectionArgs = null;
        }else{
            Log.d("MainActivity", "string "+searchString);

            selectionClause = UserDictionary.Words.WORD + " = ?";
            selectionArgs = new String[]{searchString};
        }

        Log.d("MainActivity", "Before query");
        cursor = getContentResolver().query(
                UserDictionary.Words.CONTENT_URI,
                projection, selectionClause, selectionArgs, null
        );
        Log.d("MainActivity", "After query");


        // Cria um cursor de teste com dados fictícios
        MatrixCursor testCursor = new MatrixCursor(new String[] {
                UserDictionary.Words._ID,
                UserDictionary.Words.WORD,
                UserDictionary.Words.LOCALE
        });
        testCursor.addRow(new Object[] { 1, "Teste", "en_US" });
        testCursor.addRow(new Object[] { 2, "Exemplo", "pt_BR" });

        // Substitui a consulta ao provedor de conteúdo pelo cursor de teste
        cursor = testCursor;
        cursorAdapter.swapCursor(cursor);
    }
}