package org.artoolkit.ar.samples.ARSimpleInteraction;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import io.deepstream.DeepstreamClient;
import io.deepstream.LoginResult;
import io.deepstream.Record;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, MyQuestionsFragment.OnFragmentInteractionListener, NewQuestionFragment.OnFragmentInteractionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    SQLiteDatabase mydatabase;
    DBHandler dbHandler;
    DBHandlerNoSql dataHandler;
    private LoginTask AuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init DataHandler
        dataHandler = new DBHandlerNoSql(this);

        //Init Deepstream
        attemptLogin();
        //dataHandler.emitInactive();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        mydatabase = openOrCreateDatabase("questionsDB",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Questions(Question TEXT,Answer1 TEXT,Answer2 TEXT,Answer3 TEXT,Answer4 TEXT,Answer5 TEXT);");
        dbHandler = new DBHandler(this);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        Fragment fragment = null;
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                fragment = new NewQuestionFragment();
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                fragment = new MyQuestionsFragment();
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                Intent i = new Intent(getApplicationContext(), ARSimpleInteraction.class);
                startActivity(i);
                break;
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // Do different stuff
        CharSequence text = "hey!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

       @Override
       public void onAttach(Activity activity) {
           super.onAttach(activity);
           ((MainActivity) activity).onSectionAttached(
                   getArguments().getInt(ARG_SECTION_NUMBER));
       }
    }

    public void onButtonClicked(Question question) {
        String myquestion = question.getQuestion();
        String answer1 = question.getAnswer1();
        String answer2 = question.getAnswer2();
        String answer3 = question.getAnswer3();
        String answer4 = question.getAnswer4();
        String answer5 = question.getAnswer5();

        dbHandler.addQuestion(new Question(dbHandler.getQuestionsCount()+1, myquestion, answer1, answer2, answer3, answer4, answer5));
        //mydatabase.execSQL("INSERT INTO Questions VALUES ('" + question.getQuestion() + "','" + testvar2 + "','\" + testvar3 + \"','\" + testvar4 + \"','\" + testvar5 + \"','\" + testvar6 + \"');");
    }

    private void attemptLogin() {
        if (AuthTask != null) {
            return;
        }

        // Store values at the time of the login attempt.
        String user ="devuser";
        String password = "";

        boolean cancel = false;
        View focusView = null;

        AuthTask = new LoginTask(user, password);
        AuthTask.execute((Void) null);
    }


    public class LoginTask extends AsyncTask<Void, Void, Boolean> {
        private final String mEmail;
        private final String mPassword;

        LoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            DeepstreamClient client = DeepstreamService.getInstance().getDeepstreamClient();
            try {
                JsonObject authData = new JsonObject();
                authData.addProperty( "username", mEmail );
                authData.addProperty( "password", mPassword );
                DeepstreamService.getInstance().setUserName( mEmail );
                LoginResult loginResult = client.login( authData );
//                Log.d("PLSFIX", "about to getrecord 1");
//                client.record.getRecord("results");
//                Log.d("PLSFIX","got record 1");
                return loginResult.loggedIn();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            AuthTask = null;
        }

        @Override
        protected void onCancelled() {
            AuthTask = null;
        }
    }
}
