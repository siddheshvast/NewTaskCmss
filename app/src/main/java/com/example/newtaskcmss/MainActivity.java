package com.example.newtaskcmss;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtaskcmss.models.Nearbyplaces;
import com.example.newtaskcmss.ormlite.Database.DatabaseManager;
import com.example.newtaskcmss.ormlite.ListActivity;
import com.example.newtaskcmss.ormlite.UserItemDB;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private final String TAG = MainActivity.this.getClass().getSimpleName();



    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private final static int ALL_PERMISSIONS_RESULT = 101;
    private final long UPDATE_INTERVAL = 15000;  /* 15 secs */
    private final long FASTEST_INTERVAL = 5000; /* 5 secs */
    private final ArrayList permissionsRejected = new ArrayList();
    private final ArrayList<String> permissions = new ArrayList<>();
    ApiInterface apiService;
    RecyclerView recyclerView;
    EditText editText;
    Button button;
    Context context;
    Location mLocation;
    GoogleApiClient mGoogleApiClient;
    String searchh;
    private LocationRequest mLocationRequest;
    private ArrayList<Object> permissionsToRequest;
    private ProgressBar progressBar;


    List<UserItemDB> userItemDBList = new ArrayList<>();


    double unit ;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {




        int id = item.getItemId();


        if (id == R.id.searchmenu) {


            startActivity(new Intent(MainActivity.this, ListActivity.class));


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);



        return super.onCreateOptionsMenu(menu);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = APIClient.getAPI_Client().create(ApiInterface.class);
        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        progressBar = findViewById(R.id.progressBar);


        permissions.add(ACCESS_FINE_LOCATION);
        permissions.add(ACCESS_COARSE_LOCATION);

        permissionsToRequest = findUnAskedPermissions(permissions);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            if (permissionsToRequest.size() > 0)
                requestPermissions((String[]) permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


    }

    private void newtest() {


        searchh = editText.getText().toString();

        Call<Nearbyplaces> call = apiService.getNearByLocations(searchh, mLocation.getLongitude(), mLocation.getLatitude(), getString(R.string.apikey));


        progressBar.setVisibility(View.VISIBLE);


        call.enqueue(new Callback<Nearbyplaces>() {
            @Override
            public void onResponse(Call<Nearbyplaces> call, Response<Nearbyplaces> response) {

                success(response.body());


                double theta = response.body().getResults().get(0).getPosition().getLon()
                        - response.body().getResults().get(1).getPosition().getLon();
                double dist = Math.sin(deg2rad(response.body().getResults().get(0).getPosition().getLat()))
                        * Math.sin(deg2rad(response.body().getResults().get(1).getPosition().getLat()))
                        + Math.cos(deg2rad(response.body().getResults().get(0).getPosition().getLat()))
                        * Math.cos(deg2rad(response.body().getResults().get(1).getPosition().getLat()))
                        * Math.cos(deg2rad(theta));
                dist = Math.acos(dist);
                dist = rad2deg(dist);
                dist = dist * 60 * 1.1515;
                unit = dist * 1.609344;



                String name =    "Point Name :- " + response.body().getResults().get(0).getPoi().getName()
                        +"\n"  + "Longitude :- " + response.body().getResults().get(0).getPosition().getLat().toString()
                        + "\n" + "Latitude :- " + response.body().getResults().get(0).getPosition().getLon().toString()
                        + "\n" +   "Distance between them   :- " + unit + " (KM) ";


                String index =  "Point Name :- " + response.body().getResults().get(1).getPoi().getName()
                        +"\n"  + "Longitude :- " + response.body().getResults().get(1).getPosition().getLat().toString()
                        + "\n" + "Latitude :- " + response.body().getResults().get(1).getPosition().getLon().toString()
                        + "\n" +   "Distance between them   :- " + unit + " (KM) ";


               // String index = "Distance between them   :- " + unit + " (KM) "  ;


//                String name =    "Point Name :- " + response.body().getResults().get(0).getPoi().getName()
//                        +"\n"  + "Point Name :- " +  response.body().getResults().get(1).getPoi().getName();
//
//
//                String age = "Longitude :- " + response.body().getResults().get(0).getPosition().getLat().toString()
//                        + "\n" + "Latitude :- " + response.body().getResults().get(0).getPosition().getLon().toString()
//                        + "\n" +"Longitude :- " + response.body().getResults().get(1).getPosition().getLat().toString()
//                        + "\n" + "Latitude :- " + response.body().getResults().get(1).getPosition().getLon().toString();
//
//
//                String index =   "Distance between them   :- " + unit + " (KM) " ;



                if ((name != null && !name.isEmpty())  && (index != null && !index.isEmpty())) {
                    UserItemDB userItemDB = new UserItemDB();
                    userItemDB.setIndex(index);
                    userItemDB.setName(name);
                   // userItemDB.setAge(age);
                    addUserToDB(userItemDB);
                    userItemDBList = getAllUsers();
                    for (UserItemDB itemDB : userItemDBList) {
                        Log.i(TAG, "name: " + itemDB.getName());
                    }
                }


                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<Nearbyplaces> call, Throwable t) {

                // progressDoalog.dismiss();
                progressBar.setVisibility(View.GONE);

                Toast.makeText(MainActivity.this, "error" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }


    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


    private void success(Nearbyplaces body) {

        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(body, context, mLocation.getLatitude(), mLocation.getLongitude());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    private ArrayList<Object> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<Object> result = new ArrayList<>();

        for (Object perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!checkPlayServices()) {
            // latLng.setText("Please install Google Play services.");
            Toast.makeText(context, "Please install Google Play services.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        setLocation();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {


        //            latLng.setText("Latitude : "+location.getLatitude()+" " +
//                    ", Longitude : "+location.getLongitude());


    }

    @SuppressLint("MissingPermission")
    private void setLocation() {

        button.setOnClickListener(v -> newtest());


        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);


        startLocationUpdates();
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else
                finish();

            return false;
        }
        return true;
    }

    protected void startLocationUpdates() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Enable Permissions", Toast.LENGTH_LONG).show();
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);


    }

    private boolean hasPermission(Object permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(String.valueOf(permission)) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }


    @SuppressLint("MissingPermission")
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (Object perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(String.valueOf(permissionsRejected.get(0)))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions((String[]) permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }

                } else {

                    setLocation();

                }

                break;
        }

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
    }


    public void stopLocationUpdates() {
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi
                    .removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }



    public int addUserToDB(UserItemDB userItemDB){
        int isSuccess;
        isSuccess = DatabaseManager.getInstance(getApplicationContext()).insertUserItem(userItemDB,false);
        if(isSuccess == 0){
            Toast.makeText(getApplicationContext(),"Location saved",Toast.LENGTH_SHORT).show();
        }else if(isSuccess == 1){
            Toast.makeText(getApplicationContext(),"Location already exist ",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Location adding failed",Toast.LENGTH_SHORT).show();
        }
        return isSuccess;
    }


    public List<UserItemDB> getAllUsers() {
        return DatabaseManager.getInstance(getApplicationContext()).getAllUsers();
    }


}



