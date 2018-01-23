package org.dcode.magang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = (ListView) findViewById(R.id.listTiket);
        List<Tiket> listTiket = new ArrayList<Tiket>();

        Tiket tiket1, tiket2, tiket3;
        tiket1 = new Tiket();
        tiket1.setPerbaikan("Komputer");
        tiket1.setNote("Komputer tiba-tiba mati total");
        tiket2 = new Tiket();
        tiket2.setPerbaikan("PC");
        tiket2.setNote("Bluescreen");
        tiket3 = new Tiket();
        tiket3.setPerbaikan("Printer");
        tiket3.setNote("Tinta bocor");

        listTiket.add(tiket1);
        listTiket.add(tiket2);
        listTiket.add(tiket3);

        listView.setAdapter(new TiketAdapter(this, R.layout.list_tiket, listTiket));

    }
}
