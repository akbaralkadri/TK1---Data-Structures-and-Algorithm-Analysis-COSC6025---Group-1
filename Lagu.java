/*
 * Tugas Kelompok 1 - Data Structures and Algorithm Analysis (COSC6025)
 * Group 1 : 
 * DANISCH ASWANDA MAULANA SUBKHAN (2902772113)
 * JUSTIN DERYL FERDINAND (2902786781)
 * MUHAMMAD AKBAR ALKADRI (2902768192)
 * REHO KURNIA (2902763260)
 *
 * Nomor 1: Class Lagu, merepresentasikan satu objek lagu dalam sistem playlist musik.
 */
public class Lagu {

    // atribut sesuai soal:
    private String judul;
    private String artis;
    private double durasi;

    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    public String getJudul() {
        return judul;
    }

    public String getArtis() {
        return artis;
    }

    public double getDurasi() {
        return durasi;
    }

    public void setJudul(String judul) {
        if (judul != null && !judul.trim().isEmpty()) {
            this.judul = judul;
        }
    }

    public void setArtis(String artis) {
        if (artis != null && !artis.trim().isEmpty()) {
            this.artis = artis;
        }
    }

    public void setDurasi(double durasi) {
        if (durasi > 0) {
            this.durasi = durasi;
        }
    }

    private String formatDurasi() {
        int totalDetik = (int) Math.round(durasi * 60);
        int menit = totalDetik / 60;
        int detik = totalDetik % 60;
        return String.format("%02d:%02d", menit, detik);
    }

    /**
     * menampilkan seluruh informasi lagu (judul, artis, durasi)
     */
    public void tampilkanInfo() {
        System.out.println("Judul  : " + judul);
        System.out.println("Artis  : " + artis);
        System.out.printf("Durasi : %.2f menit (%s)%n", durasi, formatDurasi());
        System.out.println("-----------------------------");
    }
}