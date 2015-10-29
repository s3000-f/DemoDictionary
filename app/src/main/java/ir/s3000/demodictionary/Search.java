package ir.s3000.demodictionary;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardViewNative;


public class Search extends ActionBarActivity {
    ListView mList;
    private TextWatcher tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        //Create a Card
        Card card = new Card(getApplicationContext(),R.layout.carddemo_example_inner_header);

        //Create a CardHeader
        CustomHeaderInnerCard header = new CustomHeaderInnerCard(getApplicationContext());        //header.setTitle("Search");
        //Add Header to card
        card.addCardHeader(header);
        //Set card in the cardView
        CardViewNative cardView = (CardViewNative) findViewById(R.id.carddemo);
        cardView.setCard(card);

    }


    public class CustomHeaderInnerCard extends CardHeader {

        public CustomHeaderInnerCard(Context context) {
            super(context, R.layout.card_header);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {

            if (view!=null){
                TextView t1 = (TextView) view.findViewById(R.id.textViewCard);

                final EditText t2 = (EditText) view.findViewById(R.id.editTextCard);
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
                    t2.addTextChangedListener(tw);


            }
        }
    }
}
