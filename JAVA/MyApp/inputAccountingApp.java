public class inputAccountingApp {
	public static void main(String[] args) {
		
		//double valueOfSupply = Double.parseDouble(args[0]); //아규먼트로 입력 받기
    
    //콘솔에서 입력 받기
    Scanner sc = new Scanner(System.in);
    double valueOfSupply = sc.next();
    
		double vatRate = 0.1;
		double expenseRate = 0.3;
		
		double vat = valueOfSupply *vatRate;
		double total = valueOfSupply + valueOfSupply * vatRate;
		double expense = valueOfSupply * expenseRate;
		double income = valueOfSupply - expense;
		double dividend = income;
		
		System.out.println("Value of supply: " + valueOfSupply);
		System.out.println("VAT: " + vat);
		System.out.println("Total: " + total);
		System.out.println("Expense: " + expense);
		System.out.println("Income: " + income);
		System.out.println("Dividend 1: " + dividend * 0.5);
		System.out.println("Dividend 2: " + dividend * 0.3);
		System.out.println("Dividend 3: " + dividend * 0.2);
	}
}
