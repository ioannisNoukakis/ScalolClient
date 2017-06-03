package scalol.com.scalolclient.UI;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import scalol.com.scalolclient.DAO.models.Post;
import scalol.com.scalolclient.R;
import scalol.com.scalolclient.services.PostLoader;

/**
 * Created by durza9390 on 02.06.2017.
 */

public class PostListView extends ListFragment implements AdapterView.OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_post_view, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Post> posts = null;
        try {
            posts = new PostLoader().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        setListAdapter(new PostListAdapter(getContext(), R.layout.post_view, posts));
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
}