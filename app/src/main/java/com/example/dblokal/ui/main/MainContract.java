package com.example.dblokal.ui.main;

import android.view.View;
import com.example.dblokal.entity.AppDatabase;
import com.example.dblokal.entity.DataSekolah;
import java.util.List;

public interface MainContract {
    interface view extends View.OnClickListener{
        void sukses();
        void resetForm();
        void editData(DataSekolah item);
    }
    interface presenter{
        void editData(String namasekolah, String alamatsekolah, String jumlahguru, String jumlahsiswa, int id, AppDatabase database);
        void deleteData(DataSekolah dataSekolah, AppDatabase database);
    }

    interface tampil extends View.OnClickListener{
        void getData(List<DataSekolah> list);
    }

    interface hapus{
        void sukses();
        void deleteData(DataSekolah item);
    }
}
