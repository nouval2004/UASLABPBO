import java.util.*;

/**
 * interface yang merepresentasikan sebuah metode pembayaran.
 */
interface PaymentMethod {
    /**
     * Mendapatkan nama dari metode pembayaran.
     *
     * @return Nama dari metode pembayaran dalam bentuk String.
     */
    String getMethodName();
}


/**
 * Kelas yang mewakili metode pembayaran melalui bank.
 * Implementasi dari interface PaymentMethod.
 */
class BankPayment implements PaymentMethod {
     /**
     * Mendapatkan nama dari metode pembayaran.
     *
     * @return Nama dari metode pembayaran dalam bentuk String (dalam hal ini "Bank").
     */
    @Override
    public String getMethodName() {
        return "Bank";
    }
}
/**
 * Kelas yang mewakili metode pembayaran Cash on Delivery (COD).
 * Implementasi dari interface PaymentMethod.
 */
class CODPayment implements PaymentMethod {
    @Override
    public String getMethodName() {
         /**
     * Mendapatkan nama dari metode pembayaran.
     *
     * @return Nama dari metode pembayaran dalam bentuk String (dalam hal ini "COD").
     */
        return "COD";
    }
}
/**
 * Kelas yang mewakili metode pembayaran menggunakan QRIS (Quick Response Code Indonesian Standard).
 * Implementasi dari antarmuka PaymentMethod.
 */
class QRISPayment implements PaymentMethod {
    /**
     * Mendapatkan nama dari metode pembayaran.
     *
     * @return Nama dari metode pembayaran dalam bentuk String (dalam hal ini "QRIS").
     */
    @Override
    public String getMethodName() {
        return "QRIS";
    }
}
/**
 * Kelas yang mewakili metode pembayaran yang tidak diketahui atau belum diidentifikasi.
 * Implementasi dari antarmuka PaymentMethod.
 */
class UnknownPayment implements PaymentMethod {
    /**
     * Mendapatkan nama dari metode pembayaran.
     *
     * @return Nama dari metode pembayaran dalam bentuk String (dalam hal ini "Unknown").
     */
    @Override
    public String getMethodName() {
        return "Unknown";
    }
}
/**
 * Kelas untuk menguji sistem toko online.
 * Memulai aplikasi toko online dengan menjalankan sistem.
 */
public class Main {
    /**
     * Metode utama untuk menjalankan sistem toko online.
     * Membuat instansi dari sistem aplikasi toko online dan menjalankannya.
     *
     * @param args Argumen baris perintah (tidak digunakan dalam implementasi ini).
     */
    public static void main(String[] args) {
        OnlineShoppingAppSystem appSystem = new OnlineShoppingAppSystem();
         // Menjalankan sistem aplikasi toko online
        appSystem.run();
    }
}

abstract class Driver {
    protected Scanner scanner;
/**
     * Metode abstrak untuk menampilkan menu berdasarkan jenis pengguna.
     *
     * @param scanner Scanner untuk masukan pengguna.
     */
    public abstract void displayMenu(Scanner scanner);
/**
     * Menampilkan header menu berdasarkan tipe pengguna.
     *
     * @param userType Tipe pengguna untuk ditampilkan di header menu.
     */
    protected void printMenuHeader(String userType) {
        System.out.println("\n" + userType + " Menu:");
    }
/**
     * Menampilkan opsi menu.
     *
     * @param options Array yang berisi opsi-opsi yang akan ditampilkan.
     */
    protected void printMenuOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }
/**
     * Mengambil pilihan pengguna dari menu.
     *
     * @param scanner    Scanner untuk masukan pengguna.
     * @param maxOption  Jumlah maksimal opsi yang tersedia.
     * @return           Pilihan yang dipilih oleh pengguna.
     */
    protected int getUserChoice(Scanner scanner, int maxOption) {
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        if (choice < 1 || choice > maxOption) {
            System.out.println("Invalid choice. Please try again.");
            return getUserChoice(scanner, maxOption);
        }
        return choice;
    }
}
/**
 * Kelas yang mewakili pengemudi dengan peran admin dalam aplikasi toko online.
 * Turunan dari kelas Driver.
 */
class AdminDriver extends Driver {
    /**
     * Konstruktor untuk menginisialisasi AdminDriver dengan scanner.
     *
     * @param scanner Scanner untuk masukan pengguna.
     */
    public AdminDriver(Scanner scanner) {
        this.scanner = scanner;
    }
    /**
     * Menampilkan menu admin dan mengelola operasi-operasi admin.
     *
     * @param scanner Scanner untuk masukan pengguna.
     */

    @Override
    public void displayMenu(Scanner scanner) {
        boolean isAdminMenuActive = true;
        while (isAdminMenuActive) {
            printMenuHeader("Admin");
            String[] options = {"Add Item", "Remove Item", "Edit Item", "View Transactions", "Exit", "Logout"};
            printMenuOptions(options);

            int choice = getUserChoice(scanner, options.length);

            switch (choice) {
                case 1:
                    addItem(scanner);
                    break;
                case 2:
                    removeItem(scanner);
                    break;
                case 3:
                    editItem(scanner);
                    break;
                case 4:
                    viewTransactions();
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    isAdminMenuActive = false;
                    break;
                case 6:
                    OnlineShoppingAppSystem app = new OnlineShoppingAppSystem();
                    app.run();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
/**
     * Menambahkan item baru ke dalam daftar item.
     *
     * @param scanner Scanner untuk masukan pengguna.
     */
    private void addItem(Scanner scanner) {
        System.out.print("Enter Item ID: ");
        String itemId = scanner.nextLine();
        System.out.print("Enter Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter Item Price: ");
        double itemPrice = scanner.nextDouble();
        scanner.nextLine();

        Item newItem = new Item(itemId, itemName, itemPrice);
        ItemList.getInstance().addItem(newItem);
        System.out.println("Item added successfully!");
    }
/**
     * Menghapus item dari daftar item.
     *
     * @param scanner Scanner untuk masukan pengguna.
     */
    private void removeItem(Scanner scanner) {
        System.out.print("Enter Item ID to remove: ");
        String itemIdToRemove = scanner.nextLine();

        ItemList.getInstance().removeItem(itemIdToRemove);
    }
 /**
     * Mengedit item yang ada dalam daftar item.
     *
     * @param scanner Scanner untuk masukan pengguna.
     */
    private void editItem(Scanner scanner) {
        System.out.print("Enter Item ID to edit: ");
        String itemIdToEdit = scanner.nextLine();

        ItemList.getInstance().editItem(itemIdToEdit, scanner);
    }
 /**
     * Melihat daftar transaksi.
     */
    private void viewTransactions() {
        TransactionList.getInstance().viewTransactions();
    }
}
/**
 * Kelas yang mewakili pengemudi dengan peran pengguna reguler dalam aplikasi toko online.
 * Turunan dari kelas Driver.
 */
class RegularUserDriver extends Driver {
    private RegularUser regularUser;
/**
     * Konstruktor untuk menginisialisasi RegularUserDriver dengan scanner dan pengguna reguler.
     *
     * @param scanner      Scanner untuk masukan pengguna.
     * @param regularUser  Pengguna reguler yang sedang aktif.
     */
    public RegularUserDriver(Scanner scanner, RegularUser regularUser) {
        this.scanner = scanner;
        this.regularUser = regularUser;
    }
 /**
     * Menampilkan menu pengguna reguler dan mengelola operasi-operasi yang dapat dilakukan oleh pengguna.
     *
     * @param scanner Scanner untuk masukan pengguna.
     */
    @Override
    public void displayMenu(Scanner scanner) {
        boolean isUserMenuActive = true;
        while (isUserMenuActive) {
            printMenuHeader("User");
            String[] options = {"View Available Items", "Add Item to Cart", "Checkout", "View Shopping History", "Exit", "Logout"};
            printMenuOptions(options);

            int choice = getUserChoice(scanner, options.length);

            switch (choice) {
                case 1:
                    displayAvailableItems();
                    break;
                case 2:
                    addItemToCart(scanner);
                    break;
                case 3:
                    checkout(scanner);
                    break;
                case 4:
                    viewShoppingHistory();
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    isUserMenuActive = false;
                    break;
                case 6:
                    OnlineShoppingAppSystem app = new OnlineShoppingAppSystem();
                    app.run();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
 /**
     * Menampilkan item-item yang tersedia.
     */
    private void displayAvailableItems() {
        System.out.println("Available Items:");
        Set<String> printedItemIds = new HashSet<>(); 

        for (Item item : OnlineShoppingAppSystem.getItems()) {
            String itemId = item.getId();

            if (!printedItemIds.contains(itemId)) {
                System.out.println("ID: " + itemId + " | Name: " + item.getName() + " | Price: " + item.getPrice());

                printedItemIds.add(itemId);
            }
        }
    }
 /**
     * Menambahkan item ke dalam keranjang belanja.
     *
     * @param scanner Scanner untuk masukan pengguna.
     */
    private void addItemToCart(Scanner scanner) {
        System.out.print("Enter Item ID to add to cart: ");
        String itemIdToAdd = scanner.nextLine();

        ItemList.getInstance().addItemToCart(itemIdToAdd, regularUser);
    }
 /**
     * Melakukan proses checkout untuk membeli item yang ada dalam keranjang belanja.
     *
     * @param scanner Scanner untuk masukan pengguna.
     */
    private void checkout(Scanner scanner) {
        if (getCart().isEmpty()) {
            System.out.println("Your cart is empty. Cannot proceed to checkout.");
            return;
        }
/**
     * Melihat riwayat belanja pengguna.
     */
        double totalPrice = calculateTotalPrice(getCart());

        System.out.println("Items in Cart:");
        for (Item item : getCart()) {
            System.out.println("ID: " + item.getId() + " | Name: " + item.getName() + " | Price: " + item.getPrice());
        }

        System.out.println("Total Price: " + totalPrice);

        System.out.println("available payment method :");
        System.out.println("1. Bank");
        System.out.println("2. COD");
        System.out.println("3. QRIS");
        System.out.print("Enter payment method : ");
        int paymentMethodChoice = scanner.nextInt();
        scanner.nextLine(); 

        PaymentMethod paymentMethod;
        switch (paymentMethodChoice) {
            case 1:
                paymentMethod = new BankPayment();
                break;
            case 2:
                paymentMethod = new CODPayment();
                break;
            case 3:
                paymentMethod = new QRISPayment();
                break;
            default:
                System.out.println("Invalid payment method choice. Defaulting to Unknown.");
                paymentMethod = new UnknownPayment();
                break;
        }

        Transaction transaction = new Transaction(generateTransactionId(), new ArrayList<>(getCart()), totalPrice, paymentMethod);
        TransactionList.getInstance().addTransaction(transaction);

        getCart().clear();
        System.out.println("Checkout successful! Thank you for shopping.");
    }

    private void viewShoppingHistory() {
        TransactionList.getInstance().viewUserTransactions(regularUser);
    }
 /**
     * Menghitung total harga dari item-item yang ada dalam keranjang belanja.
     *
     * @param items Daftar item dalam keranjang belanja.
     * @return Total harga dari semua item dalam keranjang belanja.
     */
    private double calculateTotalPrice(List<Item> items) {
        double totalPrice = 0;
        for (Item item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
/**
     * Menghasilkan ID transaksi secara acak.
     *
     * @return ID transaksi dalam bentuk String.
     */
    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
/**
     * Mendapatkan daftar item dalam keranjang belanja pengguna.
     *
     * @return Daftar item dalam keranjang belanja pengguna.
     */
    public List<Item> getCart() {
        return ItemList.getInstance().getItemsInCart(regularUser);
    }
}
/**
 * Kelas yang mewakili sistem aplikasi toko online.
 */
class OnlineShoppingAppSystem {
    private static ItemList itemList = ItemList.getInstance();
    private Scanner scanner = new Scanner(System.in);
    private User loggedInUser;
 /**
     * Konstruktor untuk menginisialisasi sistem toko online dengan item dan pengguna awal.
     */
    public OnlineShoppingAppSystem() {
        initializeItems();
        initializeUsers();
    }
/**
     * Memulai sistem aplikasi toko online dan mengelola proses login serta akses menu pengguna.
     */
    public void run() {
        boolean isLoggedIn = false;
        while (!isLoggedIn) {
            System.out.println("Choose user type:");
            System.out.println("1. Admin");
            System.out.println("2. Regular User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int userTypeChoice = scanner.nextInt();
            scanner.nextLine();  

            switch (userTypeChoice) {
                case 1:
                    isLoggedIn = loginAsAdmin();
                    if (isLoggedIn) {
                        AdminDriver adminDriver = new AdminDriver(scanner);
                        adminDriver.displayMenu(scanner);
                    }
                    break;
                case 2:
                    isLoggedIn = loginAsRegularUser();
                    if (isLoggedIn) {
                        RegularUserDriver regularUserDriver = new RegularUserDriver(scanner, (RegularUser) loggedInUser);
                        regularUserDriver.displayMenu(scanner);
                    }
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0); 
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 for Admin or 2 for Regular User.");
            }

            if (!isLoggedIn) {
                System.out.println("Login failed. Please try again.");
            }
        }
    }
/**
     * Mengambil daftar semua item yang tersedia dalam aplikasi.
     *
     * @return Daftar item yang tersedia.
     */
    public static List<Item> getItems() {
        return itemList.getItems();
    }

    private boolean loginAsAdmin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        User user = UserList.getInstance().getUser(username);
        if (user != null && user instanceof Admin && user.getPassword().equals(password)) {
            loggedInUser = user;
            System.out.println("Admin login successful!");
            return true;
        } else {
            System.out.println("Invalid admin username or password. Try again.");
            return false;
        }
    }

    private boolean loginAsRegularUser() {
        System.out.print("Enter regular user username: ");
        String username = scanner.nextLine();
        System.out.print("Enter regular user password: ");
        String password = scanner.nextLine();

        User user = UserList.getInstance().getUser(username);
        if (user != null && user instanceof RegularUser && user.getPassword().equals(password)) {
            loggedInUser = user;
            System.out.println("Regular user login successful!");
            return true;
        } else {
            System.out.println("Invalid regular user username or password. Try again.");
            return false;
        }
    }

    private void initializeItems() {
        Item item1 = new Item("001", "Kemeja", 150.0);
        Item item2 = new Item("002", "Celana", 120.0);
        Item item3 = new Item("003", "Sepatu", 200.0);

        itemList.addItem(item1);
        itemList.addItem(item2);
        itemList.addItem(item3);
    }

    private void initializeUsers() {
        Admin admin1 = new Admin("admin1", "adminpassword");
        User user1 = new RegularUser("user1", "userpassword");
        User user2 = new RegularUser("user2", "userpassword2");
        Admin admin2 = new Admin("admin2", "adminpassword2");

        UserList.getInstance().addUser(admin1);
        UserList.getInstance().addUser(user1);
        UserList.getInstance().addUser(user2);
        UserList.getInstance().addUser(admin2);
    }
}
/**
 * Kelas abstrak yang merepresentasikan pengguna dalam aplikasi toko online.
 */
abstract class User {
    private String username;
    private String password;
/**
     * Konstruktor untuk membuat instance pengguna dengan username dan password.
     *
     * @param username Username dari pengguna.
     * @param password Password dari pengguna.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
/**
     * Metode abstrak untuk menampilkan menu yang sesuai dengan peran pengguna.
     *
     * @param scanner Scanner untuk masukan pengguna.
     */
    public abstract void displayMenu(Scanner scanner);
 /**
     * Mendapatkan username dari pengguna.
     *
     * @return Username dari pengguna.
     */
    public String getUsername() {
        return username;
    }
 /**
     * Mendapatkan password dari pengguna.
     *
     * @return Password dari pengguna.
     */
    public String getPassword() {
        return password;
    }
}
/**
 * Kelas yang merepresentasikan pengguna dengan peran admin dalam aplikasi toko online.
 * Turunan dari kelas User.
 */
class Admin extends User {
    /**
     * Konstruktor untuk membuat instance admin dengan username dan password.
     *
     * @param username Username dari admin.
     * @param password Password dari admin.
     */
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void displayMenu(Scanner scanner) {
        AdminDriver adminDriver = new AdminDriver(scanner);
        adminDriver.displayMenu(scanner);
    }
}
/**
 * Kelas yang merepresentasikan pengguna dengan peran pengguna reguler dalam aplikasi toko online.
 * Turunan dari kelas User.
 */
class RegularUser extends User {
     /**
     * Konstruktor untuk membuat instance pengguna reguler dengan username dan password.
     *
     * @param username Username dari pengguna reguler.
     * @param password Password dari pengguna reguler.
     */
    public RegularUser(String username, String password) {
        super(username, password);
    }
/**
     * Menampilkan menu yang sesuai dengan peran pengguna reguler.
     *
     * @param scanner Scanner untuk masukan pengguna.
     */
    @Override
    public void displayMenu(Scanner scanner) {
        RegularUserDriver regularUserDriver = new RegularUserDriver(scanner, this);
        regularUserDriver.displayMenu(scanner);
    }
}
/**
 * Kelas yang merepresentasikan item dalam aplikasi toko online.
 */
class Item {
    private String id;
    private String name;
    private double price;
 /**
     * Konstruktor untuk membuat instance item dengan ID, nama, dan harga.
     *
     * @param id    ID dari item.
     * @param name  Nama dari item.
     * @param price Harga dari item.
     */
    public Item(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
/**
     * Mendapatkan ID dari item.
     *
     * @return ID dari item.
     */
    public String getId() {
        return id;
    }
/**
     * Mendapatkan harga dari item.
     *
     * @return Harga dari item.
     */
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    /**
     * Mengubah nama dari item.
     *
     * @param name Nama baru untuk item.
     */

    public void setName(String name) {
        this.name = name;
    }
/**
     * Mengubah harga dari item.
     *
     * @param price Harga baru untuk item.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
/**
 * Kelas yang menyimpan daftar item dan keranjang belanja pengguna dalam aplikasi toko online.
 */
class ItemList {
    private static ItemList instance = new ItemList();
    private List<Item> items;
    private Map<RegularUser, List<Item>> cartMap;

    private ItemList() {
        items = new ArrayList<>();
        cartMap = new HashMap<>();
    }
 /**
     * Mengembalikan instance tunggal dari kelas ItemList (Singleton).
     *
     * @return Instance tunggal dari kelas ItemList.
     */
    public static ItemList getInstance() {
        return instance;
    }
// method lainya sudah dijelaskan diatas
    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(String itemId) {
        items.removeIf(item -> item.getId().equals(itemId));
        System.out.println("Item removed successfully!");
    }

    public void editItem(String itemId, Scanner scanner) {
        Item itemToEdit = getItemById(itemId);
        if (itemToEdit != null) {
            System.out.println("Editing Item - Current Details:");
            System.out.println("ID: " + itemToEdit.getId() + " | Name: " + itemToEdit.getName() + " | Price: " + itemToEdit.getPrice());

            System.out.print("Enter new Item Name (press Enter to keep the current name): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                itemToEdit.setName(newName);
            }

            System.out.print("Enter new Item Price (press Enter to keep the current price): ");
            String newPriceInput = scanner.nextLine();
            if (!newPriceInput.isEmpty()) {
                try {
                    double newPrice = Double.parseDouble(newPriceInput);
                    itemToEdit.setPrice(newPrice);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format. Item price remains unchanged.");
                }
            }

            System.out.println("Item edited successfully!");
        } else {
            System.out.println("Item not found with ID: " + itemId);
        }
    }

    public void addItemToCart(String itemId, RegularUser user) {
        Item itemToAdd = getItemById(itemId);
        if (itemToAdd != null) {
            cartMap.computeIfAbsent(user, k -> new ArrayList<>()).add(itemToAdd);
            System.out.println("Item added to cart successfully!");
        } else {
            System.out.println("Item not found with ID: " + itemId);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Item> getItemsInCart(RegularUser user) {
        return cartMap.getOrDefault(user, Collections.emptyList());
    }

    private Item getItemById(String itemId) {
        return items.stream().filter(item -> item.getId().equals(itemId)).findFirst().orElse(null);
    }
}
/**
 * Kelas yang mengelola daftar pengguna dalam aplikasi toko online.
 */
class UserList {
    private static UserList instance = new UserList();
    private Map<String, User> users;

    private UserList() {
        users = new HashMap<>();
    }
/**
     * Mengembalikan instance tunggal dari kelas UserList (Singleton).
     *
     * @return Instance tunggal dari kelas UserList.
     */
    public static UserList getInstance() {
        return instance;
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }
 /**
     * Mendapatkan pengguna berdasarkan username-nya.
     *
     * @param username Username dari pengguna yang dicari.
     * @return Pengguna yang sesuai dengan username yang diberikan.
     */
    public User getUser(String username) {
        return users.get(username);
    }
}
/**
 * Kelas yang merepresentasikan transaksi dalam aplikasi toko online.
 */
class Transaction {
    private String id;
    private List<Item> items;
    private double totalPrice;
    private PaymentMethod paymentMethod;
/**
     * Konstruktor untuk membuat instance transaksi dengan ID, daftar item, total harga, dan metode pembayaran.
     */
    public Transaction(String id, List<Item> items, double totalPrice, PaymentMethod paymentMethod) {
        this.id = id;
        this.items = items;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
    }
/**
     * Mendapatkan ID dari transaksi.
     */
    public String getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
/**
     * Mendapatkan metode pembayaran yang digunakan dalam transaksi.
     *
     * @return Metode pembayaran dalam transaksi.
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}
/**
 * Kelas yang mengelola daftar transaksi dalam aplikasi toko online.
 */
class TransactionList {
    private static TransactionList instance = new TransactionList();
    private List<Transaction> transactions;
    private Set<String> printedItemIds; 

    private TransactionList() {
        transactions = new ArrayList<>();
        printedItemIds = new HashSet<>();
    }
/**
     * Mengembalikan instance tunggal dari kelas TransactionList (Singleton).
     *
     * @return Instance tunggal dari kelas TransactionList.
     */
    public static TransactionList getInstance() {
        return instance;
    }
/**
     * Menambahkan transaksi ke dalam daftar transaksi.
     *
     * @param transaction Transaksi yang akan ditambahkan.
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
/**
     * Melihat riwayat transaksi untuk pengguna reguler tertentu.
     *
     * @param user Pengguna reguler yang riwayat transaksinya akan dilihat.
     */
    public void viewUserTransactions(RegularUser user) {
        printedItemIds.clear(); 
        System.out.println("Shopping History for User: " + user.getUsername());
        for (Transaction transaction : transactions) {
            if (transaction.getItems().stream().anyMatch(item -> user instanceof RegularUser && item.getPrice() > 0)) {
                printTransactionDetails(transaction);
            }
        }
    }
/**
     * Melihat semua transaksi yang terjadi.
     */
    public void viewTransactions() {
        printedItemIds.clear(); 
        System.out.println("All Transactions:");
        for (Transaction transaction : transactions) {
            printTransactionDetails(transaction);
        }
    }

    private void printTransactionDetails(Transaction transaction) {
        System.out.println("Transaction ID: " + transaction.getId());
        System.out.println("Total Price: " + transaction.getTotalPrice());
        System.out.println("Payment Method: " + transaction.getPaymentMethod().getMethodName());
        System.out.println("Items Purchased:");

        Set<String> printedItemIdsForTransaction = new HashSet<>(); 

        boolean isRegularUser = transaction.getItems().stream().anyMatch(item -> item.getPrice() > 0);

        for (Item item : transaction.getItems()) {
            String itemId = item.getId();

            if (isRegularUser && !printedItemIdsForTransaction.contains(itemId)) {
                System.out.println("  ID: " + itemId + " | Name: " + item.getName() + " | Price: " + item.getPrice());
                
                printedItemIdsForTransaction.add(itemId);
            }
        }

        printedItemIds.addAll(printedItemIdsForTransaction);

        System.out.println("-------------------------");
    }

    public void clearPrintedItemIds() {
        printedItemIds.clear();
    }
}

   
