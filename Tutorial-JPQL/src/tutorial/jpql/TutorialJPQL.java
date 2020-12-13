/*
 * BASIS DATA BERORIENTASI OBJEK KELAS
 * NADA SALSABILA IMARI
 */
package tutorial.jpql;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.persistence.*;

/**
 *
 * @author w8
 */
public class TutorialJPQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Tutorial-JPQLPU");
        EntityManager em = emf.createEntityManager();
        Scanner input = new Scanner(System.in);
        char lanjut ='n';
        
        System.out.println("PERCOBAAN SATU");
        Query query = em.createQuery("SELECT c FROM Customer c");
        List<Customer> customers = query.getResultList();
        System.out.println("No  namaDepan   namaBelakang");
        for (Customer c : customers){
            System.out.println(c.getId() + "\t" + c.getFirstName() + "\t" + c.getLastName());
        }
        System.out.println("");
        System.out.println("PERCOBAAN DUA");
        Query query_b = em. createQuery("SELECT c.lastName, a.addressLine" +
                " FROM Customer c INNER JOIN c.addressCollection a" );
        List<Object[]> result = query_b.getResultList();
        System.out.println("namaBelakang    alamatJalan");
        for (Object[] obj : result){
            System.out.println(obj[0] + " " + obj[1]);
        }
        
        do{
            Scanner in = new Scanner(System.in);
            System.out.println("");
            System.out.println("JAWABAN TUGAS");
            System.out.println("1. Menampilkan daftar alamat");
            System.out.println("2. Menampilkan customer yang nama depannya Niko");
            System.out.println("3. Menampilkan customer yang nama depannya diawali huruf N");
            System.out.println("4. Menampilkan customer yang tinggal di kota Bandung");
            System.out.println("5. Menampilkan kota-kota yang bukan alamatnya customer Niko");
            System.out.println("6. Menampilkan jumlah customer yang ada di database");
            System.out.println("7. Menampilkan customer yang nama depannya diinputkan");
            System.out.println("8. Menampilkan customer yang tinggal di kota diinputkan");
            System.out.print("PILIH NO : ");
            char no = input.next().charAt(0);
            System.out.println("");
            
            if (no == '1' || no == '1') {
                System.out.println("NOMOR SATU : menampilkan daftar alamat");
                try{
                    EntityTransaction entr = em.getTransaction();
                    entr.begin();
                    Query query_satu = em.createNamedQuery("Address.findAll");
                    List<Address> satu = query_satu.getResultList();
                    if(satu.isEmpty()){
                        System.out.println("TIDAK ADA DATA TERKAIT");
                    }
                    else{
                        Iterator satuIterator = satu.listIterator();
                        while(satuIterator.hasNext()){
                            Address ad = (Address)satuIterator.next();
                            System.out.print("ID : " + ad.getId());
                            System.out.print("\t alamatJalan : " + ad.getAddressLine());
                            System.out.print("\t Kota : " + ad.getCity());
                            System.out.print("\t Negara : " + ad.getCountry());
                            System.out.print("\t kodePos : " + ad.getPostCode());
                            System.out.println("");
                        }
                        entr.commit();
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else if (no == '2' || no == '2') {
                System.out.println("NOMOR DUA : menampilkan customer yang nama depannya Niko");
                try{
                    EntityTransaction entr = em.getTransaction();
                    entr.begin();
                    Query query_dua = em.createNamedQuery("Customer.findByFirstName");
                    List<Customer> dua = query_dua.setParameter("firstName", "Niko").getResultList();
                    if(dua.isEmpty()){
                        System.out.println("TIDAK DITEMUKAN DATA TERKAIT");
                    }
                    else{
                        Iterator duaIterator = dua.listIterator();
                        while(duaIterator.hasNext()){
                            Customer cu = (Customer)duaIterator.next();
                            System.out.print("ID : " + cu.getId());
                            System.out.print("\t namaDepan : " + cu.getFirstName());
                            System.out.print("\t namaBelakang : " + cu.getLastName());
                            System.out.println("");
                        }
                        entr.commit();
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else if (no == '3' || no == '3') {
                System.out.println("NOMOR TIGA : Menampilkan customer yang nama depannya diawali huruf N");
                try{
                    EntityTransaction entr = em.getTransaction();
                    entr.begin();
                    Query query_tiga = em.createQuery("SELECT c FROM Customer c WHERE" +
                        " c.firstName LIKE 'N%'");
                    List<Customer> tiga = query_tiga.getResultList();
                    if(tiga.isEmpty()){
                        System.out.println("TIDAK ADA CUSTOMER DENGAN NAMA DEPAN DIAWALI HURUF N");
                    }
                    else{
                        Iterator tigaIterator = tiga.listIterator();
                        while(tigaIterator.hasNext()){
                            Customer cu = (Customer)tigaIterator.next();
                            System.out.print("ID : " + cu.getId());
                            System.out.print("\t namaDepan : " + cu.getFirstName());
                            System.out.print("\t namaBelakang : " + cu.getLastName());
                            System.out.println("");
                        }
                        entr.commit();
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }          
            }
            else if (no == '4' || no == '4') {
                System.out.println("NOMOR EMPAT : Menampilkan customer yang tinggal di kota Bandung");
                try{
                    Query query_empat = em. createQuery("SELECT c.id, c.firstName, c.lastName" +
                            " FROM Customer c INNER JOIN c.addressCollection a" +
                            " WHERE a.city = 'Bandung' ");
                    List<Object[]> empat = query_empat.getResultList();
                    if(empat.isEmpty()){
                        System.out.println("TIDAK ADA CUSTOMER YANG TINGGAL DI KOTA BANDUNG");
                    }
                    else{
                        System.out.println("ID          namaDepan      namaBelakang");
                        for (Object[] obj : empat){
                            System.out.println(obj[0] + "\t" + "\t" +obj[1]+ "\t" + "\t" +obj[2]);
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                
            }
            else if (no == '5' || no == '5') {
                System.out.println("NOMOR LIMA : Menampilkan kota-kota yang bukan alamatnya customer Niko");
                try{
                    Query query_empat = em. createQuery("SELECT DISTINCT a.city" +
                            " FROM Customer c INNER JOIN c.addressCollection a" +
                            " WHERE a.city NOT IN (SELECT a.city FROM Customer c INNER JOIN c.addressCollection a WHERE c.firstName = 'Niko' )");
                    List<Object> empat = query_empat.getResultList();
                    if(empat.isEmpty()){
                        System.out.println("TIDAK ADA CUSTOMER YANG TINGGAL DI KOTA BANDUNG");
                    }
                    else{
                        System.out.println("KOTA");
                        for (Object obj : empat){
                            System.out.println(obj);
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else if (no == '6' || no == '6') {
                System.out.println("NOMOR ENAM : Menampilkan jumlah customer yang ada di database");
                try{
                    Query query_enam = em.createQuery("SELECT COUNT (c) FROM Customer c");
                    Object enam = query_enam.getSingleResult();
                    if(enam.equals(0)){
                        System.out.println("TIDAK ADA DATA CUSTOMER DI DATABASE");
                    }
                    else{
                        System.out.println("JUMLAH CUSTOMER : " + enam);
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else if (no == '7' || no == '7') {
                System.out.println("NOMOR TUJUH : Menampilkan customer yang nama depannya diinputkan");
                try{
                    EntityTransaction entr = em.getTransaction();
                    entr.begin();
                    System.out.print("Input Nama Depan Customer : ");
                    String name = in.nextLine();
                    Query query_tujuh = em.createNamedQuery("Customer.findByFirstName");
                    List<Customer> tujuh = query_tujuh.setParameter("firstName", name).getResultList();
                    if(tujuh.isEmpty()){
                        System.out.println("TIDAK ADA CUSTOMER DENGAN NAMA DEPAN " + name);
                    }
                    else{
                        Iterator tujuhIterator = tujuh.listIterator();
                        while(tujuhIterator.hasNext()){
                            Customer cu = (Customer)tujuhIterator.next();
                            System.out.print("ID : " + cu.getId());
                            System.out.print("\t namaDepan : " + cu.getFirstName());
                            System.out.print("\t namaBelakang : " + cu.getLastName());
                            System.out.println("");
                        }
                        entr.commit();
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else if (no == '8' || no == '8') {
                System.out.println("NOMOR DELAPAN : Menampilkan customer yang tinggal di kota diinputkan");
                try{
                    System.out.print("Input Kota : ");
                    String city = in.nextLine();
                     Query query_delapan = em. createQuery("SELECT c.id, c.firstName, c.lastName" +
                            " FROM Customer c INNER JOIN c.addressCollection a" +
                            " WHERE a.city = :city ");
                    List<Object[]> delapan = query_delapan.setParameter("city", city).getResultList();
                    if(delapan.isEmpty()){
                        System.out.println("TIDAK ADA CUSTOMER TINGGAL DI KOTA " + city);
                    }
                    else {
                        System.out.println("No  namaDepan   namaBelakang");
                        for (Object[] obj : delapan){
                            System.out.println(obj[0] + "\t" + obj[1] + "\t" + obj[2]);
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else System.out.println("Nomor tidak valid");
            System.out.println("");
            System.out.print("Lanjutkan ? (y/n) = ");
                    char lagi = input.next().charAt(0);
                    lanjut = lagi;
            System.out.println("------------------------------------------------------------------------------------");
        }while(lanjut == 'y' || lanjut == 'Y');
    }
    
}
