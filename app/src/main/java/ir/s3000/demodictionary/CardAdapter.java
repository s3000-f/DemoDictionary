package ir.s3000.demodictionary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardViewNative;

/**
 * Created by S3000 on 10/29/2015.
 */
public class CardAdapter extends ArrayAdapter<Word> {
        private final Context context;
        private final Word[] values;

        public CardAdapter (Context context, Word[] values) {
            super(context,-1,values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_layout, parent, false);
            View rowBody = inflater.inflate(R.layout.list_card_body, null);
            //Create a Card
            TextView deff=(TextView) rowBody.findViewById(R.id.list_card_body_text);
                deff.setText(values[position].getDeff());
            //TextView tit = (TextView) rowHeader.findViewById(R.id.list_card_header_text);
              //  tit.setText(values[position].getWord());


            Card card = new Card(getContext(),R.layout.list_card_body);
            //Create a CardHeader
            CustomHeaderInnerCard header = new CustomHeaderInnerCard(getContext(),values[position]);
            //Add header to card
            card.addCardHeader(header);
            CardViewNative cardView = (CardViewNative) rowView.findViewById(R.id.cardList);
            cardView.setCard(card);

            return rowView;
        }
    public class CustomHeaderInnerCard extends CardHeader {
        private Word value;
        public CustomHeaderInnerCard(Context context,Word word) {
            super(context, R.layout.list_card_header);
            this.value=word;
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view ) {

            TextView tit = (TextView) view.findViewById(R.id.list_card_header_text);
            tit.setText(value.getWord());




        }
    }


}
