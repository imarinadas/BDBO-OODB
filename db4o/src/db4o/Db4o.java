/*
*   Basis Data Berorientasi Objek
*   Implementasi Db4O Dasar
*   Nada Salsabila Imari
*/
package db4o;
import java.io.File;
import java.util.*;
import com.db4o.*;
import com.db4o.query.Query;
import com.db4o.query.*;
public class Db4o {
    public static void main(String[] args) {
        new File("Orang.yap").delete(); // reset the database orang.yap
        com.db4o.Db4o.configure().messageLevel(0); // 0=silent, 3=loud
        ObjectContainer db= com.db4o.Db4o.openFile("Orang.yap");
try{
        storePegawai(db);
        updateNamaNip(db);
        updateNama(db);
        updateUmur(db);
        updateGaji(db);
        updateNohp(db);
        
        retrieveAllPegawai(db);
        retrieveNama(db);
        retrieveNip(db);
        retrieveUmur(db);
        retrieveGaji(db);
        deletePegawai(db);
      }
finally{
   db.close();
  }
}
    public static void listResult(ObjectSet result){
        while (result.hasNext()){
            System.out.println(result.next());
        }
        System.out.println("--------------------------------------------\n");
    }
    public static void retrieveAllPegawai(ObjectContainer db) {
        Query query=db.query();
        query.constrain(Pegawai.class);
        ObjectSet result=query.execute();
        System.out.println("----retrieve semua Pegawai----");
        listResult(result);
    }
    
    public static void listNama(List<Pegawai> pegawai){
        Iterator iterPegawai=pegawai.iterator();
        System.out.println("----retrieve berdasarkan Nama berawalan S----");
        while (iterPegawai.hasNext()){
        System.out.println(iterPegawai.next()); }
    }
    public static void retrieveNama(ObjectContainer db) {
        List <Pegawai> pegawai = db.query(new Predicate<Pegawai>() {
            @Override
            public boolean match(Pegawai pegawai) {
                return pegawai.getNama().startsWith("S");
             }
         });
        System.out.println("\nResult hasil = "+pegawai.size());
        listNama(pegawai);
    }
    
    public static void listNip(List<Pegawai> pegawai){
        Iterator iterPegawai=pegawai.iterator();
        System.out.println("----retrieve berdasarkan NIP berawalan 1----");
        while (iterPegawai.hasNext()){
        System.out.println(iterPegawai.next()); }
    }
    public static void retrieveNip(ObjectContainer db) {
        List <Pegawai> pegawai = db.query(new Predicate<Pegawai>() {
            @Override
            public boolean match(Pegawai pegawai) {
                return pegawai.getNip().startsWith("1");
            }
        });
        System.out.println("\nResult hasil = "+pegawai.size());
        listNip(pegawai);
    }
    
    public static void listUmur(List<Pegawai> pegawai){
        Iterator iterPegawai=pegawai.iterator();
        System.out.println("----retrieve berdasarkan Umur > 20----");
        while (iterPegawai.hasNext()){
        System.out.println(iterPegawai.next()); }
    }
    public static void retrieveUmur(ObjectContainer db) {
        List <Pegawai> pegawai = db.query(new Predicate<Pegawai>() {
            @Override
            public boolean match(Pegawai pegawai) {
                return pegawai.getUmur()>20;   
            }
        });
        System.out.println("\nResult hasil = "+pegawai.size());
        listUmur(pegawai);
    }
    
    public static void listGaji(List<Pegawai> pegawai){
        Iterator iterPegawai=pegawai.iterator();
        System.out.println("----retrieve berdasarkan Gaji < 4000000----");
        while (iterPegawai.hasNext()){
        System.out.println(iterPegawai.next()); }
    }
    public static void retrieveGaji(ObjectContainer db) {
        List <Pegawai> pegawai = db.query(new Predicate<Pegawai>() {
            @Override
            public boolean match(Pegawai pegawai) {
                return pegawai.getGaji()<4000000;   
            }
         });
        System.out.println("\nResult hasil = "+pegawai.size());
        listGaji(pegawai);
    }


    public static void updateNamaNip(ObjectContainer db) {
        ObjectSet result=db.queryByExample(new Pegawai("221",null,0,0,0));
        Pegawai found=(Pegawai)result.next();
        found.setNama(String.valueOf("Lincoln BLUE"));
        found.setNip(String.valueOf("322"));
        db.store(found);

      System.out.println("\nMengupdate Nama dan NIP untuk "+found);
        retrieveAllPegawai(db);
    }
    public static void updateNama(ObjectContainer db) {
        ObjectSet result=db.queryByExample(new Pegawai("201",null,0,0,0));
        Pegawai found=(Pegawai)result.next();
        found.setNama(String.valueOf("SONY STARK"));
        db.store(found);

      System.out.println("\nMengupdate Nama untuk "+found);
        retrieveAllPegawai(db);
    }
    public static void updateUmur(ObjectContainer db) {
        ObjectSet result=db.queryByExample(new Pegawai("102",null,0,0,0));
        Pegawai found=(Pegawai)result.next();
        found.setUmur(found.getUmur()-60);
        db.store(found);

      System.out.println("\nMengupdate Umur untuk "+found);
        retrieveAllPegawai(db);
    }
    public static void updateGaji(ObjectContainer db) {
        ObjectSet result=db.queryByExample(new Pegawai("131",null,0,0,0));
        Pegawai found=(Pegawai)result.next();
        found.setGaji(found.getGaji()+2000000);
        db.store(found);

      System.out.println("\nMengupdate Gaji untuk "+found);
        retrieveAllPegawai(db);
    }
    public static void updateNohp(ObjectContainer db) {
        ObjectSet result=db.queryByExample(new Pegawai("202",null,0,0,0));
        Pegawai found=(Pegawai)result.next();
        found.setNohp(Integer.valueOf("62888888"));
        db.store(found);

      System.out.println("\nMengupdate No HP untuk "+found);
        retrieveAllPegawai(db);
    }
    
    public static void deletePegawai(ObjectContainer db) {
        ObjectSet result=db.queryByExample(new Pegawai("202",null,0,0,0));
        Pegawai found=(Pegawai)result.next();
        db.delete(found);
        System.out.println("\nDeleted "+found);
         retrieveAllPegawai(db);
    }
    
    public static void storePegawai(ObjectContainer db) {
        Pegawai p1 = new Pegawai("102", "Gandhi", 79, 5000000, 628100);
        Pegawai p2 = new Pegawai("221", "Lincoln", 56, 4000000, 628200);
        Pegawai p3 = new Pegawai("131", "Suneo", 51, 2000000, 628300);
        Pegawai p4 = new Pegawai("201", "Sony", 54, 3000000, 628400);
        Pegawai p5 = new Pegawai("202", "Sanyo", 46, 1000000, 628500);
        db.store(p1);
        db.store(p2);
        db.store(p3);
        db.store(p4);
        db.store(p5);
        System.out.println("\nStored Pegawai dengan NIP " + p1.getNip()
            + ", " + p2.getNip()+ ", " + p3.getNip()+ ", " + p4.getNip()
            + ", " + p5.getNip());
             
        ObjectSet result = (ObjectSet) db.queryByExample(new Pegawai());
        listResult(result);
    }
}
