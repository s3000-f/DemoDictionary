package ir.s3000.demodictionary;

import android.content.Context;
import android.text.Editable;
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
public class CardAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;
        private final String[] deffs;

        public CardAdapter (Context context, String[] values,String[] deffs) {
            super(context,-1,values);
            this.context = context;
            this.values = values;
            this.deffs=deffs;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_layout, parent, false);
            //Create a Card
            TextView deff=(TextView) convertView.findViewById(R.id.list_card_body_text);
                deff.setText(deffs[position]);
            TextView tit = (TextView) convertView.findViewById(R.id.list_card_header_text);
                tit.setText(values[position]);
            Card card = new Card(getContext(),R.layout.list_card_body);

            //Create a CardHeader
            CustomHeaderInnerCard header = new CustomHeaderInnerCard(getContext());
            //Add header to card
            CardViewNative cardView = (CardViewNative) convertView.findViewById(R.id.cardList);
            card.addCardHeader(header);


            return rowView;
        }
    public class CustomHeaderInnerCard extends CardHeader {

        public CustomHeaderInnerCard(Context context) {
            super(context, R.layout.list_card_header);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view ) {






        }
    }


}
