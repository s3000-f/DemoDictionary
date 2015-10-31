package ir.s3000.demodictionary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardViewNative;


public class WordView extends AppCompatActivity {
    Word word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_view);
        Intent i=getIntent();
        word.setDeff(i.getStringExtra("deffToDisplay"));
        word.setWord(i.getStringExtra("wordToDisplay"));
        Card cardWord = new Card(getApplicationContext(),R.layout.card_view_word_body);
        AppCompatTextView text=(AppCompatTextView)findViewById(R.id.card_view_word_header_text);
        text.setText(word.getDeff());
        CustomHeaderInnerCard headerWord = new CustomHeaderInnerCard(getApplicationContext());        //header.setTitle("Search");
        cardWord.addCardHeader(headerWord);
        CardViewNative cardViewWord = (CardViewNative) findViewById(R.id.card_view_word);
        cardViewWord.setCard(cardWord);

        Card cardDeff = new Card(getApplicationContext(),R.layout.card_view_deff_body);
        CardHeader headerDeff = new CardHeader(getApplicationContext());        //header.setTitle("Search");
        cardDeff.addCardHeader(headerDeff);
        CardViewNative cardViewDeff = (CardViewNative) findViewById(R.id.card_view_deff);
        cardViewDeff.setCard(cardDeff);

    }
    public class CustomHeaderInnerCard extends CardHeader {

        public CustomHeaderInnerCard(Context context) {
            super(context, R.layout.card_view_word_header);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {
            AppCompatTextView text=(AppCompatTextView)findViewById(R.id.card_view_word_header_text);
            text.setText(word.getWord());



        }
    }


}
