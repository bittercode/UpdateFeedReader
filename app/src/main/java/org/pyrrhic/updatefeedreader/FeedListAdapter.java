package org.pyrrhic.updatefeedreader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndFeed;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndCategory;
import java.util.List;

public class FeedListAdapter
        extends BaseAdapter
{
    private SyndFeed feed;

    private Activity context;

    public FeedListAdapter(Activity context, SyndFeed feed)
    {
        this.feed = feed;
        this.context = context;
    }

    public int getCount()
    {
        return feed.getEntries().size();
    }

    public SyndEntry getItem( int index )
    {
        return (SyndEntry) feed.getEntries().get( index );
    }

    public long getItemId( int index )
    {
        return index;
    }

    public View getView( int index, View cellRenderer, ViewGroup viewGroup )
    {
        //NewsEntryCellView newsEntryCellView = (NewsEntryCellView) cellRenderer;
        View convertView = cellRenderer;

        if ( convertView == null )
        {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.item_feed,viewGroup,false);
        }
        //TableLayout item_feed = (TableLayout) context.findViewById(R.id.item_feed);

        ImageView categoryImageView = (ImageView) convertView.findViewById(R.id.categoryImageView);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
        TextView dateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
        TextView summaryTextView = (TextView) convertView.findViewById(R.id.summaryTextView);

        //if (getItem(index) != null) {
        SyndEntry entry = getItem(index);
        //}

        titleTextView.setText( entry.getTitle() );
        titleTextView.setTextColor(Color.BLUE);
        dateTextView.setText( entry.getPublishedDate().toString() );
        summaryTextView.setText( entry.getDescription().getValue() );
        List<SyndCategory> categories = entry.getCategories();
        SyndCategory category = categories.get(0); //there is only one
        int catnum = Integer.parseInt(category.getName());
        //String catname="";
        switch (catnum) {
            case 1: categoryImageView.setImageResource(R.drawable.praise); //Praise
                break;
            case 2: categoryImageView.setImageResource(R.drawable.prayer); //Prayer
                break;
            case 3: categoryImageView.setImageResource(R.drawable.update); //Update
                break;
            case 4: categoryImageView.setImageResource(R.drawable.family); //Family
                break;
        }

        return convertView;

        //newsEntryCellView.display( index );
        //return newsEntryCellView;
    }

    public void click( int position )
    {
        String uri = getItem( position ).getUri();
        Intent webIntent = new Intent( "android.intent.action.VIEW", Uri.parse( uri ) );
        context.startActivity( webIntent );
    }

    /*private class NewsEntryCellView
        extends TableLayout
    {
        //private TextView titleTextView;
        //private TextView dateTextView;
        //private TextView summaryTextView;
        //private TextView categoryTextView;
        //private ImageView categoryImageView;

        public NewsEntryCellView()
        {
            super( context );
            createUI();
        }

        private void createUI()
        {
             setColumnShrinkable( 0, false );
            setColumnStretchable( 0, false );
            setColumnShrinkable( 1, false );
            setColumnStretchable( 1, false );
            setColumnShrinkable( 2, false );
            setColumnStretchable( 2, true );
            setColumnStretchable( 3, true);
            setColumnShrinkable( 3, false );

            setPadding( 10, 10, 10, 10 );

            //TableLayout item_feed = (TableLayout)findViewById(R.id.item_feed);
            //item_feed.setStretchAllColumns(true);

            //TableRow row1 = new TableRow(context);

            ImageView categoryImageView = (ImageView)context.findViewById(R.id.categoryImageView);

            //row1.addView(categoryImageView);

            TextView titleTextView = (TextView)context.findViewById(R.id.titleTextView);
            //titleTextView.setPadding( 10, 10, 10, 10 );
            //titleTextView.setTextColor(Color.RED);
            //row1.addView(titleTextView);
            //MainLayout.addView(row1);

            //TableRow row2 = new TableRow(context);

            TextView dateTextView = (TextView)context.findViewById(R.id.dateTextView);
            //dateTextView.setPadding( 10, 10, 10, 10 );
            //row2.addView( dateTextView );

            TextView summaryTextView = (TextView)context.findViewById(R.id.summaryTextView);
            //summaryTextView.setPadding( 10, 10, 10, 10 );
            //row2.addView( summaryTextView );

            //MainLayout.addView(row2);
           // addView(item_feed);
            //categoryTextView = new TextView( context );
            //categoryTextView.setPadding(10,10,10,10);
            //addView( categoryTextView );
        }


        public void display( int index )
        {
            SyndEntry entry = getItem( index );
            titleTextView.setText( entry.getTitle() );
            titleTextView.setTextColor(Color.BLUE);
            dateTextView.setText( entry.getPublishedDate().toString() );
            summaryTextView.setText( entry.getDescription().getValue() );
            List<SyndCategory> categories = entry.getCategories();
            SyndCategory category = categories.get(0); //there is only one
            int catnum = Integer.parseInt(category.getName());
            //String catname="";
            switch (catnum) {
                case 1: categoryImageView.setImageResource(R.drawable.praise); //Praise
                    break;
                case 2: categoryImageView.setImageResource(R.drawable.prayer); //Prayer
                    break;
                case 3: categoryImageView.setImageResource(R.drawable.update); //Update
                    break;
                case 4: categoryImageView.setImageResource(R.drawable.family); //Family
                    break;
            }

            //categoryTextView.setText(catname);
        }
    } */
}
