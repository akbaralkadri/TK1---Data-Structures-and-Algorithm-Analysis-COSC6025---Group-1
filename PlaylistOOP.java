/*
 * Tugas Kelompok 1 - Data Structures and Algorithm Analysis (COSC6025)
 * Group 1 : 
 * DANISCH ASWANDA MAULANA SUBKHAN (2902772113)
 * JUSTIN DERYL FERDINAND (2902786781)
 * MUHAMMAD AKBAR ALKADRI (2902768192)
 * REHO KURNIA (2902763260)
 *
 * Nomor 2: Class User sebagai parent class, kemudian Class Admin dan Member sebagai child class 
 */
import java.util.Scanner;

// parent class
class User {

    private String nama;

    public User(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama != null && !nama.trim().isEmpty()) {
            this.nama = nama;
        }
    }

    // di-override turunan
    public void tampilkanAkses() {
        System.out.println(nama + " adalah user umum, belum punya hak akses khusus.");
    }
}


class Admin extends User {

    public Admin(String nama) {
        super(nama);
    }

    @Override
    public void tampilkanAkses() {
        System.out.println("Login sebagai Admin " + getNama() + " (bisa menambah & melihat lagu).");
    }

    // tambah lagu ke slot kosong
    public void tambahLagu(Lagu[] playlist, Lagu laguBaru) {
        for (int i = 0; i < playlist.length; i++) {
            if (playlist[i] == null) {
                playlist[i] = laguBaru;
                System.out.println("Lagu \"" + laguBaru.getJudul() + "\" berhasil ditambahkan.");
                return;
            }
        }
        System.out.println("Playlist penuh, lagu gagal ditambahkan.");
    }

    // tampilkan semua lagu
    public void lihatDaftarLagu(Lagu[] playlist) {
        System.out.println("=== Daftar Lagu di Playlist ===");
        int nomor = 1;
        for (int i = 0; i < playlist.length; i++) {
            if (playlist[i] != null) {
                System.out.println(nomor + ". " + playlist[i].getJudul()
                        + " - " + playlist[i].getArtis());
                nomor++;
            }
        }
        System.out.println("-----------------------------");
    }
}


class Member extends User {

    public Member(String nama) {
        super(nama);
    }

    @Override
    public void tampilkanAkses() {
        System.out.println("Login sebagai Member " + getNama() + " (bisa menelusuri & melihat detail).");
    }

    // tampilkan judul lagu
    public void telusuriLagu(Lagu[] playlist) {
        System.out.println("=== " + getNama() + " sedang menelusuri playlist ===");
        for (int i = 0; i < playlist.length; i++) {
            if (playlist[i] != null) {
                System.out.println("- " + playlist[i].getJudul());
            }
        }
        System.out.println("-----------------------------");
    }

    // cari lagu berdasarkan judul
    public void lihatDetailLagu(Lagu[] playlist, String judul) {
        boolean ketemu = false;
        for (int i = 0; i < playlist.length; i++) {
            if (playlist[i] != null && playlist[i].getJudul().equalsIgnoreCase(judul)) {
                System.out.println("Detail lagu yang dicari:");
                playlist[i].tampilkanInfo();
                ketemu = true;
                break;
            }
        }
        if (!ketemu) {
            System.out.println("Lagu dengan judul \"" + judul + "\" tidak ditemukan.");
        }
    }

    // rata-rata durasi
    public double hitungRataRataDurasi(Lagu[] playlist) {
        double totalDurasi = 0;
        int jumlahLagu = 0;

        for (int i = 0; i < playlist.length; i++) {
            if (playlist[i] != null) {
                totalDurasi += playlist[i].getDurasi();
                jumlahLagu++;
            }
        }

        if (jumlahLagu == 0) {
            return 0;
        }
        return totalDurasi / jumlahLagu;
    }
}


public class PlaylistOOP {
    static void clearScreen() { //cls
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    static void pause(Scanner input) { //cls
        System.out.print("\nTekan Enter untuk lanjut...");
        input.nextLine();
    }

    public static void main(String[] args) {
        Lagu[] playlist = new Lagu[10];
        Scanner input = new Scanner(System.in);

        Admin admin = new Admin("Justin");
        Member member = new Member("Reho");
        //default song ls 
        admin.tambahLagu(playlist, new Lagu("Bohemian Rhapsody", "Queen", 5.55));
        admin.tambahLagu(playlist, new Lagu("Shape of You", "Ed Sheeran", 3.90));

        int login;
        do {
            clearScreen();
            System.out.println("===== LOGIN =====");
            System.out.println("1. Admin");
            System.out.println("2. Member");
            System.out.println("0. Keluar");
            System.out.print("Login sebagai: ");
            login = Integer.parseInt(input.nextLine());

            if (login == 1) {
                admin.tampilkanAkses();   // polymor
                pause(input);
                menuAdmin(admin, playlist, input);
            } else if (login == 2) {
                member.tampilkanAkses();  // polymor
                pause(input);
                menuMember(member, playlist, input);
            } else if (login != 0) {
                System.out.println("Pilihan tidak valid.");
                pause(input);
            }
        } while (login != 0);

        System.out.println("Program selesai.");
        input.close();
    }

    // menu khusus admin
    static void menuAdmin(Admin admin, Lagu[] playlist, Scanner input) {
        int pilih;
        do {
            clearScreen();
            System.out.println("--- MENU ADMIN (" + admin.getNama() + ") ---");
            System.out.println("1. Tambah lagu");
            System.out.println("2. Lihat daftar lagu");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            pilih = Integer.parseInt(input.nextLine());

            if (pilih == 1) {
                System.out.print("Judul  : ");
                String judul = input.nextLine();
                System.out.print("Artis  : ");
                String artis = input.nextLine();
                System.out.print("Durasi (menit, contoh 4.30) : ");
                double durasi = Double.parseDouble(input.nextLine());
                admin.tambahLagu(playlist, new Lagu(judul, artis, durasi));
                pause(input);
            } else if (pilih == 2) {
                admin.lihatDaftarLagu(playlist);
                pause(input);
            } else if (pilih != 0) {
                System.out.println("Pilihan tidak valid.");
                pause(input);
            }
        } while (pilih != 0);
    }

    // menu khusus member
    static void menuMember(Member member, Lagu[] playlist, Scanner input) {
        int pilih;
        do {
            clearScreen();
            System.out.println("--- MENU MEMBER (" + member.getNama() + ") ---");
            System.out.println("1. Telusuri lagu");
            System.out.println("2. Lihat detail lagu");
            System.out.println("3. Hitung rata-rata durasi");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            pilih = Integer.parseInt(input.nextLine());

            if (pilih == 1) {
                member.telusuriLagu(playlist);
                pause(input);
            } else if (pilih == 2) {
                System.out.print("Judul lagu yang dicari: ");
                String cari = input.nextLine();
                member.lihatDetailLagu(playlist, cari);
                pause(input);
            } else if (pilih == 3) {
                double rataRata = member.hitungRataRataDurasi(playlist);
                System.out.printf("Rata-rata durasi lagu: %.2f menit%n", rataRata);
                pause(input);
            } else if (pilih != 0) {
                System.out.println("Pilihan tidak valid.");
                pause(input);
            }
        } while (pilih != 0);
    }
}
