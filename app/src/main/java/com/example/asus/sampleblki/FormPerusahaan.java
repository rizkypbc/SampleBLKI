package com.example.asus.sampleblki;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.sampleblki.Api.AccessServiceAPI;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASUS on 16/05/2017.
 */

public class FormPerusahaan extends AppCompatActivity {

    private EditText txtUsernamePerusahaan;
    private EditText txtPassword1Perusahaan;
    private EditText txtPassword2Perusahaan;
    private EditText txtNamaPerusahaan;
    private EditText txtSektor;
    private EditText txtBidang;
    private EditText txtAlamatPerusahaan;
    private ProgressDialog m_ProgressDialog;
    private AccessServiceAPI m_AccessServiceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_perusahaan);
        txtUsernamePerusahaan = (EditText) findViewById(R.id.txt_username_perusahaan);
        txtPassword1Perusahaan = (EditText) findViewById(R.id.txt_pwd_perusahaan);
        txtPassword2Perusahaan = (EditText) findViewById(R.id.txt_konfirmasi_perusahaan);
        txtNamaPerusahaan = (EditText) findViewById(R.id.txt_nama_perusahaan);
//        txtSektor = (EditText) findViewById(R.id.txt_sektor);
//        txtBidang = (EditText) findViewById(R.id.txt_bidang);
//        txtAlamatPerusahaan = (EditText) findViewById(R.id.txt_alamat_perusahaan);
        m_AccessServiceAPI = new AccessServiceAPI();
    }

    public void btnPerusahaan_Click(View v) {

        if ("".equals(txtUsernamePerusahaan.getText().toString())) {
            txtUsernamePerusahaan.setError("Username is required");
            return;
        }
        if (txtPassword1Perusahaan.getText().toString().equals(txtPassword2Perusahaan.getText().toString())) {

            new FormPerusahaan.TaskPerusahaan().execute(txtUsernamePerusahaan.getText().toString(), txtPassword1Perusahaan.getText().toString(),
                    txtNamaPerusahaan.getText().toString(), txtSektor.getText().toString(),
                    txtBidang.getText().toString(), txtAlamatPerusahaan.getText().toString());
        } else {
            txtPassword2Perusahaan.setError("Konfirmasi Password tidak sama");
        }
    }

    public class TaskPerusahaan extends AsyncTask<String, Void, Integer> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            m_ProgressDialog = ProgressDialog.show(FormPerusahaan.this, "Harap Tunggu", "Sedang Berlangsung Proses Registrasi", true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Map<String, String> postParam = new HashMap<>();
            postParam.put("action", "add");
            postParam.put("username", params[0]);
            postParam.put("password", params[1]);
            postParam.put("nama_perusahaan", params[2]);
            postParam.put("sektor", params[3]);
            postParam.put("bidang", params[4]);
            postParam.put("alamat_perusahaan", params[5]);

            try {

                String jsonString = m_AccessServiceAPI.getJSONStringWithParam_POST(Common.SERVICE_API_URL_PERUSAHAAN, postParam);
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
                Toast.makeText(FormPerusahaan.this, "Registrasi Berhasil", Toast.LENGTH_LONG).show();
                Intent i = new Intent();
                i.putExtra("username", txtUsernamePerusahaan.getText().toString());
                i.putExtra("password", txtPassword1Perusahaan.getText().toString());
                i.putExtra("nama_perusahaan", txtNamaPerusahaan.getText().toString());
                i.putExtra("sektor", txtSektor.getText().toString());
                i.putExtra("bidang", txtBidang.getText().toString());
                i.putExtra("alamat_perusahaan", txtAlamatPerusahaan.getText().toString());
                setResult(1, i);
                finish();
            } else if (integer == Common.RESULT_USER_EXITS) {
                Toast.makeText(FormPerusahaan.this, "Username Telah digunakan", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(FormPerusahaan.this, "Registrasi Gagal", Toast.LENGTH_LONG).show();
            }
        }
    }
}
