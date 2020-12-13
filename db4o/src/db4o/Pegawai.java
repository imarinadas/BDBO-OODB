package db4o;
public class Pegawai {
    private String _nip, _nama;
    private int _umur, _gaji, _nohp;
    public Pegawai(){}

    public Pegawai(String nip, String nama, int umur, int gaji, int nohp){
        this._nip = nip;
        this._nama = nama;
        this._umur = umur;
        this._gaji = gaji;
        this._nohp = nohp;
    }

    public int getNohp(){
       return _nohp;
    }

    public void setNohp(int value){
      this._nohp = value;
    }
    
    public int getGaji(){
       return _gaji;
    }

    public void setGaji(int value){
      this._gaji = value;
    }
    
    public int getUmur(){
       return _umur;
    }

    public void setUmur(int value){
      this._umur = value;
    }

    public String getNama(){
      return _nama;
    }

    public void setNama(String value){
       this._nama = value;
    }
    
    public String getNip(){
      return _nip;
    }

    public void setNip(String value){
       this._nip = value;
    }

    @Override
    public String toString(){
        return "[" + _nip + ";"+ _nama + ";"+ _umur 
                + ";"+ _gaji + ";"+ _nohp + "]";
    }
}
