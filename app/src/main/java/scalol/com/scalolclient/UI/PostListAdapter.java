package scalol.com.scalolclient.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;

import java.util.List;

import scalol.com.scalolclient.DAO.models.Post;
import scalol.com.scalolclient.R;

/**
 * Created by durza9390 on 02.06.2017.
 */

public class PostListAdapter  extends ArrayAdapter<Post> {

    public PostListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public PostListAdapter(Context context, int resource, List<Post> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.post_view, null);
        }

        Post p = getItem(position);

        if (p != null) {
            TextView title = (TextView) v.findViewById(R.id.title);
            ImageView image = (ImageView) v.findViewById(R.id.img);
            TextView score = (TextView) v.findViewById(R.id.score);
            CheckBox nsfw = (CheckBox) v.findViewById(R.id.nsfw);

            if (title != null) {
                title.setText(p.getTitle());
            }

            if (image != null) {
                Glide.with(getContext())
                        .load(p.getImage_path())
                        .into(image);
            }

            if (score != null) {
                score.setText(""+ p.getScore());
            }

            if (nsfw != null) {
                nsfw.setClickable(false);
                nsfw.setChecked(p.isNsfw());
            }
        }

        return v;
    }

}
