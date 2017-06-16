package com.example.asus.sampleblki;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asus.sampleblki.Api.AccessServiceAPI;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 09/05/2017.
 */

public class FormPelatihan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText txtNama;
    private EditText txtJK;
    private EditText txtTTL;
    private EditText txtAlamat;
    private EditText txtProvinsi;
    private EditText txtKab;
    private EditText txtNoTelp;
    private EditText txtEmail;
    private EditText txtAgama;
    private EditText txtPendidikan;
    private EditText txtJurusan;
    private EditText txtAsal;
    private EditText txtKejuruan;
    private EditText txtSubKejuruan;
    private EditText txtProgram;
    private EditText txtUrlPhoto;
    private ProgressDialog m_ProgressDialog;
    private AccessServiceAPI m_AccessServiceAPI;
    private Spinner spnProgram, spnProvinsi, spnAgama,
            spnKabupaten, spnPendidikan, spnJurusan, spnKejuruan, spnSubKejuruan;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pelatihan);
//        txtId = (EditText)findViewById(R.id.txt_id);
        txtNama = (EditText) findViewById(R.id.txt_nama);
//        txtJK = (EditText) findViewById(R.id.txt_jk);
        txtTTL = (EditText) findViewById(R.id.txt_ttl);
        txtAlamat = (EditText) findViewById(R.id.txt_alamat);
//        txtProvinsi = (EditText) findViewById(R.id.txt_provinsi);
        spnProvinsi = (Spinner) findViewById(R.id.spnProvinsi);
//        txtKab = (EditText) findViewById(R.id.txt_kab_kota);
        spnKabupaten = (Spinner) findViewById(R.id.spnKabupaten);
        txtNoTelp = (EditText) findViewById(R.id.txt_notelp);
        txtEmail = (EditText) findViewById(R.id.txt_email);
//        txtAgama = (EditText) findViewById(R.id.txt_agama);
        spnAgama = (Spinner) findViewById(R.id.spnAgama);
//        txtPendidikan = (EditText) findViewById(R.id.txt_pendidikan);
        spnPendidikan = (Spinner) findViewById(R.id.spnPendidikan);
//        txtJurusan = (EditText) findViewById(R.id.txt_jurusan);
        spnJurusan = (Spinner) findViewById(R.id.spnJurusan);
        txtAsal = (EditText) findViewById(R.id.txt_asal_sekolah);
//        txtKejuruan = (EditText) findViewById(R.id.txt_kejuruan);
        spnKejuruan = (Spinner) findViewById(R.id.spnKejuruan);
//        txtSubKejuruan = (EditText) findViewById(R.id.txt_sub_kejuruan);
        spnSubKejuruan = (Spinner) findViewById(R.id.spnSubKejuruan);
        spnProgram = (Spinner) findViewById(R.id.spnProgram);
//        txtProgram = (EditText) findViewById(R.id.txt_program);
        txtUrlPhoto = (EditText) findViewById(R.id.txt_url_photo);
        m_AccessServiceAPI = new AccessServiceAPI();

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);

        spnProgram.setOnItemSelectedListener(this);
//        spnProvinsi.setOnItemSelectedListener(this);
//        spnAgama.setOnItemSelectedListener(this);
//        spnKabupaten.setOnItemSelectedListener(this);
//        spnPendidikan.setOnItemSelectedListener(this);
//        spnJurusan.setOnItemSelectedListener(this);
//        spnKejuruan.setOnItemSelectedListener(this);
//        spnSubKejuruan.setOnItemSelectedListener(this);

//        ArrayList categories = new ArrayList();
//        categories.add("Automobile");
//        categories.add("Business Services");
//        categories.add("Computers");
//        categories.add("Education");
//        categories.add("Personal");
//        categories.add("Travel");

//        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories);
        ArrayAdapter dataProgram = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        dataProgram.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProgram.setAdapter(dataProgram);

        ArrayAdapter dataProvinsi = ArrayAdapter.createFromResource(this, R.array.provinsi, android.R.layout.simple_spinner_item);
        dataProvinsi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProvinsi.setAdapter(dataProvinsi);

        ArrayAdapter dataAgama = ArrayAdapter.createFromResource(this, R.array.agama, android.R.layout.simple_spinner_item);
        dataAgama.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAgama.setAdapter(dataAgama);

        ArrayAdapter dataKabupaten = ArrayAdapter.createFromResource(this, R.array.kabupaten, android.R.layout.simple_spinner_item);
        dataKabupaten.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnKabupaten.setAdapter(dataKabupaten);

        ArrayAdapter dataPendidikan = ArrayAdapter.createFromResource(this, R.array.pendidikan, android.R.layout.simple_spinner_item);
        dataPendidikan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPendidikan.setAdapter(dataPendidikan);

        ArrayAdapter dataJurusan = ArrayAdapter.createFromResource(this, R.array.jurusan, android.R.layout.simple_spinner_item);
        dataJurusan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnJurusan.setAdapter(dataJurusan);

        ArrayAdapter dataKejuruan = ArrayAdapter.createFromResource(this, R.array.kejuruan, android.R.layout.simple_spinner_item);
        dataKejuruan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnKejuruan.setAdapter(dataKejuruan);

        ArrayAdapter dataSubKejuruan = ArrayAdapter.createFromResource(this, R.array.sub_kejuruan, android.R.layout.simple_spinner_item);
        dataSubKejuruan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSubKejuruan.setAdapter(dataSubKejuruan);
    }

    public void btnRegister_Click(View v) {
        //Validate Input
        if ("".equals(txtNama.getText().toString())) {
            txtNama.setError("Nama Tidak Boleh Kosong");
            return;

        }

        int selectedId = radioSexGroup.getCheckedRadioButtonId();

        radioSexButton = (RadioButton) findViewById(selectedId);

        new TaskRegister().execute(txtNama.getText().toString(),
                radioSexButton.getText().toString(), txtTTL.getText().toString(), txtAlamat.getText().toString(),
                spnProgram.getSelectedItem().toString(), spnKabupaten.getSelectedItem().toString(), txtNoTelp.getText().toString(),
                txtEmail.getText().toString(), spnAgama.getSelectedItem().toString(), spnPendidikan.getSelectedItem().toString(),
                spnJurusan.getSelectedItem().toString(), txtAsal.getText().toString(), spnKejuruan.getSelectedItem().toString(),
                spnSubKejuruan.getSelectedItem().toString(), spnProgram.getSelectedItem().toString(), txtUrlPhoto.getText().toString());

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public class TaskRegister extends AsyncTask<String, Void, Integer> {


        protected void onPreExecute() {
            super.onPreExecute();
            m_ProgressDialog = ProgressDialog.show(FormPelatihan.this, "Please Wait", "Registration processing...", true);
        }


        @Override
        protected Integer doInBackground(String... params) {
            Map<String, String> postParam = new HashMap<>();
            postParam.put("action", "add");
//            postParam.put("id", params[0]);
            postParam.put("nama", params[0]);
            postParam.put("jk", params[1]);
            postParam.put("ttl", params[2]);
            postParam.put("alamat", params[3]);
            postParam.put("provinsi", params[4]);
            postParam.put("kab_kota", params[5]);
            postParam.put("notelp", params[6]);
            postParam.put("email", params[7]);
            postParam.put("agama", params[8]);
            postParam.put("pendidikan", params[9]);
            postParam.put("jurusan", params[10]);
            postParam.put("asal_sekolah", params[11]);
            postParam.put("kejuruan", params[12]);
            postParam.put("sub_kejuruan", params[13]);
            postParam.put("program", params[14]);
            postParam.put("urlphoto", params[15]);
            try {

                String jsonString = m_AccessServiceAPI.getJSONStringWithParam_POST(Common.SERVICE_API_URL_PELATIHAN, postParam);
                JSONObject jsonObject = new JSONObject(jsonString);
                return jsonObject.getInt("result");
            } catch (Exception e) {
                e.printStackTrace();
                return Common.RESULT_ERROR;
            }
        }


        public void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            m_ProgressDialog.dismiss();
            if (integer == Common.RESULT_SUCCESS) {
                Toast.makeText(FormPelatihan.this, "Registration success", Toast.LENGTH_LONG).show();
                Intent i = new Intent();
//                i.putExtra("id", txtId.getText().toString());
                i.putExtra("nama", txtNama.getText().toString());
//                i.putExtra("jjk", txtJK.getText().toString());
                i.putExtra("jjk", radioSexButton.getText().toString());
                i.putExtra("ttl", txtTTL.getText().toString());
                i.putExtra("alamat", txtAlamat.getText().toString());
//                i.putExtra("provinsi", txtProvinsi.getText().toString());
                i.putExtra("provinsi", spnProvinsi.getSelectedItem().toString());
//                i.putExtra("kab_kota", txtKab.getText().toString());
                i.putExtra("kab_kota", spnKabupaten.getSelectedItem().toString());
                i.putExtra("notelp", txtNoTelp.getText().toString());
                i.putExtra("email", txtEmail.getText().toString());
//                i.putExtra("agama", txtAgama.getText().toString());
                i.putExtra("agama", spnAgama.getSelectedItem().toString());
//                i.putExtra("pendidikan", txtPendidikan.getText().toString());
                i.putExtra("pendidikan", spnPendidikan.getSelectedItem().toString());
//                i.putExtra("jurusan", txtJurusan.getText().toString());
                i.putExtra("jurusan", spnJurusan.getSelectedItem().toString());
                i.putExtra("asal_sekolah", txtAsal.getText().toString());
//                i.putExtra("kejuruan", txtKejuruan.getText().toString());
                i.putExtra("kejuruan", spnKejuruan.getSelectedItem().toString());
//                i.putExtra("sub_kejuruan", txtSubKejuruan.getText().toString());
                i.putExtra("sub_kejuruan", spnSubKejuruan.getSelectedItem().toString());
//                i.putExtra("program", txtProgram.getText().toString());
                i.putExtra("program", spnProgram.getSelectedItem().toString());
                i.putExtra("urlphoto", txtUrlPhoto.getText().toString());
                setResult(1, i);
                finish();
            } else {
                Toast.makeText(FormPelatihan.this, "Registration fail!", Toast.LENGTH_LONG).show();

            }
        }
    }
}
