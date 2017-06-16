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

public class FormPencariKerja extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword1;
    private EditText txtPassword2;
    private EditText txtNoktp;
    private EditText txtNamaPencariKerja;
    private EditText txtJkPencari;
    private EditText txtTtlPencari;
    private ProgressDialog m_ProgressDialog;
    private AccessServiceAPI m_AccessServiceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pencari_kerja);
        txtUsername = (EditText) findViewById(R.id.txt_username);
        txtPassword1 = (EditText) findViewById(R.id.txt_pwd);
        txtPassword2 = (EditText) findViewById(R.id.txt_konfirmasi);
        txtNoktp = (EditText) findViewById(R.id.txt_noktp);
        txtNamaPencariKerja = (EditText) findViewById(R.id.txt_nama_pencari_kerja);
//        txtJkPencari = (EditText) findViewById(R.id.txt_jk_pencari_kerja);
        txtTtlPencari = (EditText) findViewById(R.id.txt_ttl_pencari_kerja);
        m_AccessServiceAPI = new AccessServiceAPI();
    }

    public void btnPencariKerja_Click(View v) {

        if ("".equals(txtUsername.getText().toString())) {
            txtUsername.setError("Username is required");
            return;
        }
        if (txtPassword1.getText().toString().equals(txtPassword2.getText().toString())) {

            new TaskPencariKerja().execute(txtUsername.getText().toString(), txtPassword1.getText().toString(),
                    txtNoktp.getText().toString(), txtNamaPencariKerja.getText().toString(),
                    txtJkPencari.getText().toString(), txtTtlPencari.getText().toString());
        } else {
            txtPassword2.setError("Konfirmasi Password tidak sama");
        }
    }

    public class TaskPencariKerja extends AsyncTask<String, Void, Integer> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            m_ProgressDialog = ProgressDialog.show(FormPencariKerja.this, "Harap Tunggu", "Sedang Berlangsung Proses Registrasi", true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Map<String, String> postParam = new HashMap<>();
            postParam.put("action", "add");
            postParam.put("username", params[0]);
            postParam.put("password", params[1]);
            postParam.put("noktp", params[2]);
            postParam.put("nama_pencari", params[3]);
            postParam.put("jk_pencari", params[4]);
            postParam.put("ttl_pencari", params[5]);

            try {

                String jsonString = m_AccessServiceAPI.getJSONStringWithParam_POST(Common.SERVICE_API_URL_KERJA, postParam);
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
                Toast.makeText(FormPencariKerja.this, "Registrasi Berhasil", Toast.LENGTH_LONG).show();
                Intent i = new Intent();
                i.putExtra("username", txtUsername.getText().toString());
                i.putExtra("password", txtPassword1.getText().toString());
                i.putExtra("noktp", txtNoktp.getText().toString());
                i.putExtra("nama_pencari", txtNamaPencariKerja.getText().toString());
                i.putExtra("jk_pencari", txtJkPencari.getText().toString());
                i.putExtra("ttl_pencari", txtTtlPencari.getText().toString());
                setResult(1, i);
                finish();
            } else if (integer == Common.RESULT_USER_EXITS) {
                Toast.makeText(FormPencariKerja.this, "Username Telah digunakan", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(FormPencariKerja.this, "Registrasi Gagal", Toast.LENGTH_LONG).show();
            }
        }
    }
}

