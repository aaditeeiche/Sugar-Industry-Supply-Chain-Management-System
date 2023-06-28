package miniproject;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		int choice, ans;
		int task;
		Scanner in = new Scanner(System.in);

		Supplier objs = new Supplier();
		Manufacturer objm = new Manufacturer();
		Wholesaler objw = new Wholesaler();

		System.out.println("====== SUGAR SUPPLY CHAIN =======");
		
		do {
			System.out.println("\n========== PROFILE PAGE =========");
			System.out.println(":\t1\tSUPPLIER\t:\n:\t2\tMANUFACTURER\t:\n:\t3\tWHOLESALER\t:");
			System.out.print("=================================\n==>\tLOG IN AS:\t");
			choice = in.nextInt();

			switch (choice) {

			case 1: // SUPPLIER
				int cont = 1;
				while (cont == 1) {
					System.out.println(
							"\n============= SUPPLIER MENU =============\n1. Add Sugarcane Stock\n2. Deliver Pending Order from Queue\nChoose a Task ==>\t");
					task = in.nextInt();
					switch (task) {
					case 1: // Add Sugarcane Stock
						objs.sugarcaneStock();
						break;
					case 2: // Deliver Pending Order

						if (objs.queueSM.isEmpty()) {
							System.out.println("No Pending Deliveries");
						} else {
							objs.deliverPending();
						}
						break;
					default:
						System.out.println("! INVALID INPUT");
					}
					System.out.print("\n==> Press 1 - Go to Supplier Menu | 0 - Log Out: \n==> ");
					cont = in.nextInt();
				}
				break;

			case 2: // MANUFACTURER
				cont = 1;
				while (cont == 1) {
					System.out.println("\n=========== MANUFACTURER MENU ===========");
					System.out.print(
							"1. Place Order for Sugarcane\n2. Run Factory\n3. Check Current Produce Stock\n4. Request Bill Invoice\nChoose a Task ==>\t");
					task = in.nextInt();
					switch (task) {
					case 1: // Place Order to Supplier

						objm.placeOrder(objs);
						break;
					case 2: // Run Factory to Generate Products
						objm.sugarcaneProducts();
						break;
					case 3: // Display Product List
						objm.display();
						break;
						
					case 4:
						objs.bill(objm);
						break;
					default:
						System.out.println("! INVALID INPUT");
					}
					System.out.print("\n==> Press 1 - Go to Manufacturer Menu | 0 - Log Out: \n==> ");
					cont = in.nextInt();
				}
				break;

			case 3: // WHOLESALER
				cont = 1;
				while (cont == 1) {
					System.out.println("\n============ WHOLESALER MENU =============");
					System.out.print(
							"1. Place Order to Manufacturer for Products\n2. Check Current Wholesaler Inventory\n3. Request Bill Invoice\nChoose a Task ==>\t");
					task = in.nextInt();
					switch (task) {
					case 1: // Place Order to Manufacturer
						objw.placeOrder(objm, objs);
						break;
					case 2: // Display Product List
						objw.display();
						break;
					case 3: //Request Bill Invoice
						System.out.println("\n======== BILL FOR MANFACTURER =========");
						objm.bill(objw);
						System.out.println("\n======== BILL FOR SUPPLIER =========");
						objs.bill(objw);
						break;
					default:
						System.out.println("! INVALID INPUT");
					}
					System.out.print("\n==> Press 1 - Go to Manufacturer Menu | 0 - Log Out: \n==> ");
					cont = in.nextInt();
				}
				break;

			default:
				System.out.println("! INVALID INPUT");
			}
			System.out.print("\n==> Press 1 - Go to Profile Page | 0 - Quit Application: \n==> ");
			ans = in.nextInt();

		} while (ans == 1);
		in.close();
	}
}