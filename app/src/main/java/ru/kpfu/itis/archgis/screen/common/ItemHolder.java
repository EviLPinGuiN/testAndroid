package ru.kpfu.itis.archgis.screen.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kpfu.itis.archgis.R;


/**
 * Created by DNS1 on 27.05.2017.
 */
public class ItemHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.tv_author)
    TextView mTvAuthor;
    @BindView(R.id.author)
    TextView mAuthor;
    @BindView(R.id.tv_year)
    TextView mTvYear;
    @BindView(R.id.year)
    TextView mYear;


    @NonNull
    public static ItemHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.search_item, null);
        ItemHolder holder = new ItemHolder(view);
        ButterKnife.bind(holder, view);
        return holder;
    }

    public ItemHolder(View itemView) {
        super(itemView);
    }

    public void bind(@NonNull ListItem item) {
        mName.setText(item.getName());
        //**monument**/
        if (item.getEpoch() != null && item.getType() != null) {
            mTvAuthor.setText("Эпоха");
            mTvYear.setText("Тип");
            mAuthor.setText(item.getEpoch());
            mYear.setText(item.getType());
            //**research**//
        } else if (item.getYear() != null && item.getType() != null) {
            mTvAuthor.setText("Тип");
            mAuthor.setText(item.getType());
            mYear.setText(item.getYear());
            //**other**//
        } else {
            if (item.getYear() != null)
                mYear.setText(item.getYear());
            else {
                mYear.setVisibility(View.GONE);
                mTvYear.setVisibility(View.GONE);
            }
            if (item.getAuthor() != null) {
                mAuthor.setText(item.getAuthor());
            } else {
                mAuthor.setVisibility(View.GONE);
                mTvAuthor.setVisibility(View.GONE);
            }
        }
    }

}
