Hello In this docs i will show you How to Filter Recycler with Toolbar Search widget

1. Create a menu file in menu directory..I name it as search.xml

<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context="com.coxtunes.recyclearviewoffline.recyclearviewoffiline.MainActivity">

    <item
        android:id="@+id/search"
        android:orderInCategory="300"
        android:title="Seach"
        android:icon="@drawable/ic_search_black_24dp"
        app:actionViewClass = "android.support.v7.widget.SearchView"
        app:showAsAction="ifRoom|collapseActionView" />

</menu>

2. In Recycler adaper we have creat a method which will help us to filter list. 
Here userlist inside method is the object of global list variable.

    public void setfilter(List<User> uitem)
    {
        userlist = new ArrayList<>();
        userlist.addAll(uitem);
        notifyDataSetChanged();
    }

3. We have creat a list type method which help to filter data in recyclearviewoffiline

  private List<User> filter(List<User> d1, String query)
    {
        query = query.toLowerCase();
        final List<User> newfilteredlist = new ArrayList<>();

        for (User m : d1)
        {

            final String text = String.valueOf(m.getAccountNumber());


// If we want match only first character with recycler item we will use it
//            if (text.startsWith(query))
//            {
//                newfilteredlist.add(m);
//            }

// We use contains here for search full item  match with any character
            if (text.contains(query))
            {
                newfilteredlist.add(m);

            }
            else
            {
                if (newfilteredlist.size() > 0)
                {
                    NODATATEXT.setVisibility(View.GONE);
                }
                else
                {
                    NODATATEXT.setVisibility(View.VISIBLE);
                }





            }


        }

        return newfilteredlist;
    }


4. In MainActivity.java or your activity we have to implement onCreateOptionsMenu
for toolbar search. Here We initialize SEARCH widget inside this method. & we call 3no procedure 
method here.


  @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);
        final MenuItem mymitem = menu.findItem(R.id.search);
        SEARCH = (android.support.v7.widget.SearchView) mymitem.getActionView();

        SEARCH.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (!SEARCH.isIconified())
                {
                    SEARCH.setIconified(true);

                }
                mymitem.collapseActionView();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                final List<User> nml = filter(ALLUSER,newText);
                USERADAPTER.setfilter(nml);

                return true;
            }
        });


        return true;
    }   

    Done :) This filter can be use in both online or offline recyclearview.
    