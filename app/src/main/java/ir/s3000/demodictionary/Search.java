package ir.s3000.demodictionary;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardViewNative;


public class Search extends AppCompatActivity {
    ListView mList;
    private TextWatcher tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        Card card = new Card(getApplicationContext(),R.layout.carddemo_example_inner_header);
        CustomHeaderInnerCard header = new CustomHeaderInnerCard(getApplicationContext());        //header.setTitle("Search");
        card.addCardHeader(header);
        CardViewNative cardView = (CardViewNative) findViewById(R.id.carddemo);
        cardView.setCard(card);


        SharedPreferences prefs = this.getSharedPreferences(
                "ir.s3000.demodictionary", Context.MODE_PRIVATE);
        boolean flag = prefs.getBoolean("firstLaunch", true);
        if (flag) {
            BackgroundTask task = new BackgroundTask(Search.this, getApplicationContext(),true,null);
            task.execute();
            //prefs.edit().putBoolean("firstLaunch", false);
        }
        DictDataSource dictDataSource=new DictDataSource(getApplicationContext());
        dictDataSource.open();
        List<Word> words=dictDataSource.getAllWords();
        dictDataSource.close();
        final Word[] word=words.toArray(new Word[words.size()]);
        BackgroundTask task = new BackgroundTask(Search.this, getApplicationContext(),false,word);
        task.execute();

        if (!(mList==null))
        {
            mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i=new Intent(getApplicationContext(),WordView.class);
                    i.putExtra("wordToDisplay",word[position].getWord());
                    i.putExtra("deffToDisplay",word[position].getDeff());
                    startActivity(i);
                }
            });
        }




    }


    public class CustomHeaderInnerCard extends CardHeader {

        public CustomHeaderInnerCard(Context context) {
            super(context, R.layout.card_header);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {

            if (view!=null){
                TextView t1 = (TextView) view.findViewById(R.id.textViewCard);

                final AppCompatEditText t2 = (AppCompatEditText) view.findViewById(R.id.editTextCard);
                 tw = new TextWatcher() {

                    public void afterTextChanged(Editable s) {
                    }

                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    public void onTextChanged(CharSequence s, int start, int before,
                                              int count) {
                        if (t2.getText().toString().equals("")) {
                            mList.clearTextFilter();
                        } else {
                            mList.setFilterText(t2.getText().toString());
                        }

                    }
                };
                   // t2.addTextChangedListener(tw);


            }
        }
    }
    private class BackgroundTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;
        private Context context;
        private boolean type;
        private Word[] word;
        public BackgroundTask(Search activity,Context context,boolean type,Word[] word) {
            dialog = new ProgressDialog(activity);
            this.context=context;
            this.type=type;
            this.word=word;
        }

        @Override
        protected void onPreExecute() {
            dialog.setTitle("First Launch");
            if (type)
            dialog.setMessage("Deploying Databases, please wait.");
            else dialog.setMessage("Getting Data");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.e("kk","kk");
            if (type){
                Log.e("kk","kk2");
                WordToDB wtdb=new WordToDB(context);

                wtdb.start();

            }else
            {
                Log.e("kk","kk3");
                CardAdapter cardAdapter=new CardAdapter(context,word);
                mList.setAdapter(cardAdapter);
            }

            return null;
        }

    }
}
