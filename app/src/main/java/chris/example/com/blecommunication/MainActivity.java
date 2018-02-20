package chris.example.com.blecommunication;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by chris on 2/20/2018.
 */

public class MainActivity extends AppCompatActivity implements DeviceListFragment.OnFragmentInteractionListener
{
    private DeviceListFragment mDeviceListFragment;
    private BluetoothAdapter BTAdapter;
    
    
    public static int REQUEST_BLUETOOTH = 1;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        BTAdapter = BluetoothAdapter.getDefaultAdapter();
        
        // check to see if BT is supported
        if (BTAdapter == null) {
            new AlertDialog.Builder(this)
                    .setTitle("Not compatible")
                    .setMessage("Your phone does not support Bluetooth")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        
        if (!BTAdapter.isEnabled()) {
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT, REQUEST_BLUETOOTH);
        }
        
        FragmentManager fragmentManager = getSupportFragmentManager();
        
        mDeviceListFragment = DeviceListFragment.newInstance(BTAdapter);
        fragmentManager.beginTransaction().replace(R.id.container, mDeviceListFragment).commit();
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onFragmentInteraction(String id) {
    
    }
}
